package top.lsmod.basemodel.base.impl;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import top.lsmod.basemodel.base.FlBaseInterfaceReqBean;
import top.lsmod.basemodel.base.FlBaseInterfaceRspBean;
import top.lsmod.basemodel.base.IHttpFactory;

public class OkHttpImpl implements IHttpFactory {

    private static String TAG = "yfl_OkHttpImpl";
    private static Handler mMainHandler = new Handler(Looper.getMainLooper());
    // Json
    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

    @Override
    public void sendPost(FlBaseInterfaceReqBean interfaceBean) {

    }

    @Override
    public void sendGet(String serverUrl, FlBaseInterfaceReqBean interfaceBean, IhttpFactoryMonitor ihttpFactoryMonitor) {
        OkHttpClient client = new OkHttpClient();
        client.newBuilder().connectTimeout(180, TimeUnit.SECONDS).readTimeout(180, TimeUnit.SECONDS).build();
        Request request = new Request.Builder()
                .url(serverUrl + interfaceBean.getInterfaceName())
                .build();
        Log.d(TAG, interfaceBean.getInterfaceName() + "入参>>" + interfaceBean.getInterfaceName());
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mMainHandler.post(() -> {
                    FlBaseInterfaceRspBean interfaceRspBean = new FlBaseInterfaceRspBean();
                    interfaceRspBean.setHttpCode(504);
                    interfaceRspBean.setHttpResult(e.getMessage());
                    interfaceRspBean.setInterfaceId(interfaceBean.getInterfaceId());
                    ihttpFactoryMonitor.onNetWorkResponse(interfaceRspBean);
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseStr = response.body().string();
                mMainHandler.post(() -> {
                    Log.d(TAG, interfaceBean.getInterfaceName() + "出参>>" + responseStr);
                    FlBaseInterfaceRspBean interfaceRspBean = new FlBaseInterfaceRspBean();
                    interfaceRspBean.setHttpCode(response.code());
                    interfaceRspBean.setHttpResult(responseStr);
                    interfaceRspBean.setInterfaceId(interfaceBean.getInterfaceId());
                    ihttpFactoryMonitor.onNetWorkResponse(interfaceRspBean);
                });
            }
        });
    }
}
