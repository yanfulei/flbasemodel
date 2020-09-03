package top.lsmod.basemodel.base;

import android.content.Context;

import com.alibaba.sdk.android.oss.model.OSSObjectSummary;

import java.util.List;

public interface IFileUploadFactory {

    void init(Context context, String AccessKeyId, String AccessKeySecret, String Endpoint, FileUploadInitMonitor monitor);

    void enableLog();

    void enableDNS();

    void listObjects(String BucketName, String Prefix, FileUploadListObjectsMonitor monitor);

    void downloadFile(String BucketName, String ObjectKey, String DownloadFilePath, FileUploadDownLoadMonitor monitor);

    void uploadFile(String bucketName, String objectName, String uploadFilePath, FileUploadMonitor monitor);

    interface FileUploadInitMonitor {
        void success(String msg);

        void error(String msg);
    }

    interface FileUploadListObjectsMonitor {
        void success(List<OSSObjectSummary> files);

        void error(String msg);
    }

    interface FileUploadDownLoadMonitor {
        void success(String msg);

        void error(String msg);
    }

    interface FileUploadMonitor {
        void success(String msg);

        void error(String msg);

        void progress(long currentSize, long totalSize);
    }
}
