package top.lsmod.basemodel.base.impl;

import android.content.Context;
import android.util.Log;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSPlainTextAKSKCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.GetObjectRequest;
import com.alibaba.sdk.android.oss.model.GetObjectResult;
import com.alibaba.sdk.android.oss.model.ListObjectsRequest;
import com.alibaba.sdk.android.oss.model.ListObjectsResult;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;

import java.io.FileOutputStream;

import top.lsmod.basemodel.base.IFileUploadFactory;

public class AliOssImpl implements IFileUploadFactory {

    private ClientConfiguration conf;
    private OSS oss;

    @Override
    public void init(Context context, String AccessKeyId, String AccessKeySecret, String Endpoint, FileUploadInitMonitor monitor) {
        try {
            OSSCredentialProvider credentialProvider = new OSSPlainTextAKSKCredentialProvider(AccessKeyId, AccessKeySecret);
            conf = new ClientConfiguration();
            conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒。
            conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒。
            conf.setMaxConcurrentRequest(5); // 最大并发请求数，默认5个。
            conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次。
            oss = new OSSClient(context, Endpoint, credentialProvider);
            monitor.success("初始化阿里云OSS成功");
        } catch (Exception e) {
            monitor.error(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void enableLog() {
        //日志的样式
        //通过调用OSSLog.enableLog()开启可以在控制台看到日志，
        //并且会支持写入手机sd卡中的一份日志文件位置在内置sd卡路径\OSSLog\logs.csv  默认不开启
        //日志会记录oss操作行为中的请求数据，返回数据，异常信息
        //例如requestId,response header等，下边是一个日志记录case
        //android_version：5.1  android版本
        //mobile_model：XT1085  android手机型号
        //network_state：connected  网络状况
        //network_type：WIFI 网络连接类型
        //具体的操作行为信息:
        //[2017-09-05 16:54:52] - Encounter local execpiton: //java.lang.IllegalArgumentException: The bucket name is invalid.
        //A bucket name must:
        //1) be comprised of lower-case characters, numbers or dash(-);
        //2) start with lower case or numbers;
        //3) be between 3-63 characters long.
        //------>end of log
        OSSLog.enableLog();  //调用此方法即可开启日志
    }

    @Override
    public void enableDNS() {
        conf.setHttpDnsEnable(true);//默认为true 表开启，需要关闭可以设置为false。
    }

    /**
     * 枚举指定bucket内指定文件夹下的文件
     *
     * @param BucketName oss-test
     * @param Prefix     "test/47383/"
     * @param monitor    回调函数
     */
    @Override
    public void listObjects(String BucketName, String Prefix, FileUploadListObjectsMonitor monitor) {
        ListObjectsRequest listObjects = new ListObjectsRequest(BucketName);
        listObjects.setPrefix(Prefix);
        // 设置成功、失败回调，发送异步列举请求。
        oss.asyncListObjects(listObjects, new OSSCompletedCallback<ListObjectsRequest, ListObjectsResult>() {
            @Override
            public void onSuccess(ListObjectsRequest request, ListObjectsResult result) {
                Log.d("AyncListObjects", "Success!");
                monitor.success(result.getObjectSummaries());
                for (int i = 0; i < result.getObjectSummaries().size(); i++) {
                    Log.d("AyncListObjects", "object: " + result.getObjectSummaries().get(i).getKey() + " " + result.getObjectSummaries().get(i).getETag() + " " + result.getObjectSummaries().get(i).getLastModified());
                }
            }

            @Override
            public void onFailure(ListObjectsRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // 请求异常。
                if (clientExcepion != null) {
                    // 本地异常，如网络异常等。
                    monitor.error(clientExcepion.getMessage());
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常。
                    monitor.error(serviceException.getMessage());
                    Log.e("ErrorCode", serviceException.getErrorCode());
                    Log.e("RequestId", serviceException.getRequestId());
                    Log.e("HostId", serviceException.getHostId());
                    Log.e("RawMessage", serviceException.getRawMessage());
                }
            }
        }).waitUntilFinished();
    }

    /**
     * 下载文件
     *
     * @param BucketName       oss-test
     * @param ObjectKey        abc/efg/123.jpg
     * @param DownloadFilePath /mnt/oss/
     * @param monitor          监听
     */
    @Override
    public void downloadFile(String BucketName, String ObjectKey, String DownloadFilePath, FileUploadDownLoadMonitor monitor) {
        // objectKey等同于objectname，表示从OSS下载文件时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
        GetObjectRequest get = new GetObjectRequest(BucketName, ObjectKey);
        oss.asyncGetObject(get, new OSSCompletedCallback<GetObjectRequest, GetObjectResult>() {
            @Override
            public void onSuccess(GetObjectRequest request, GetObjectResult result) {
                //开始读取数据。
                long length = result.getContentLength();
                byte[] buffer = new byte[(int) length];
                int readCount = 0;
                while (readCount < length) {
                    try {
                        readCount += result.getObjectContent().read(buffer, readCount, (int) length - readCount);
                    } catch (Exception e) {
                        monitor.error(e.toString());
                        OSSLog.logInfo(e.toString());
                    }
                }
                // 将下载后的文件存放在指定的本地路径。
                try {
                    FileOutputStream fout = new FileOutputStream(DownloadFilePath);
                    fout.write(buffer);
                    fout.close();
                    monitor.success("文件【" + ObjectKey + "】下载成功");
                } catch (Exception e) {
                    monitor.error(e.toString());
                    OSSLog.logInfo(e.toString());
                }
            }

            @Override
            public void onFailure(GetObjectRequest request, ClientException clientException, ServiceException serviceException) {
                monitor.error(clientException.toString());
            }
        });
    }

    @Override
    public void uploadFile(String bucketName, String objectName, String uploadFilePath, FileUploadMonitor monitor) {
        // 构造上传请求。
        PutObjectRequest put = new PutObjectRequest(bucketName, objectName, uploadFilePath);
        // 设置进度回调函数打印进度条。
        put.setProgressCallback((request, currentSize, totalSize) -> {
            monitor.progress(currentSize, totalSize);
            Log.d("PutObject", "currentSize: " + currentSize + " totalSize: " + totalSize);
        });
        // 异步上传。
        OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                monitor.success(result.getServerCallbackReturnBody());
                Log.d("PutObject", "UploadSuccess");
                Log.d("ETag", result.getETag());
                Log.d("RequestId", result.getRequestId());
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // 请求异常。
                if (clientExcepion != null) {
                    // 本地异常，如网络异常等。
                    monitor.error(clientExcepion.toString());
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常。
                    monitor.error(serviceException.toString());
                    Log.e("ErrorCode", serviceException.getErrorCode());
                    Log.e("RequestId", serviceException.getRequestId());
                    Log.e("HostId", serviceException.getHostId());
                    Log.e("RawMessage", serviceException.getRawMessage());
                }
            }
        });
        // task.cancel(); // 可以取消任务。
        task.waitUntilFinished(); // 等待任务完成。
    }
}
