package top.lsmod.basemodel.base.impl;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.lazy.library.logging.Logcat;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import top.lsmod.basemodel.base.FlBaseInterfaceReqBean;
import top.lsmod.basemodel.base.FlBaseInterfaceRspBean;
import top.lsmod.basemodel.base.IHttpFactory;
import top.lsmod.basemodel.utils.ACache;

public class OkHttpImpl implements IHttpFactory {

    private static Handler mMainHandler = new Handler(Looper.getMainLooper());
    private static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");

    @Override
    public void sendPost(String serverUrl, FlBaseInterfaceReqBean interfaceBean, IhttpFactoryMonitor ihttpFactoryMonitor) {
        OkHttpClient client = new OkHttpClient();
        client.newBuilder().connectTimeout(180, TimeUnit.SECONDS).readTimeout(180, TimeUnit.SECONDS).build();
        RequestBody requestBody = RequestBody.create(new Gson().toJson(interfaceBean.getParam()), MEDIA_TYPE_JSON);
        Request request = new Request.Builder()
                .addHeader("Authorization", "Bearer " + ACache.get(interfaceBean.getContext()).getAsString("token"))
                .url(serverUrl + interfaceBean.getInterfaceName())
                .post(requestBody)
                .build();
        Logcat.d("【" + interfaceBean.getInterfaceName() + "】入参==>>" + interfaceBean.getInterfaceName());
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                mMainHandler.post(() -> {
                    FlBaseInterfaceRspBean interfaceRspBean = new FlBaseInterfaceRspBean();
                    interfaceRspBean.setHttpCode(504);
                    interfaceRspBean.setHttpResult(e.getMessage());
                    interfaceRspBean.setInterfaceId(interfaceBean.getInterfaceId());
                    ihttpFactoryMonitor.onNetWorkResponse(interfaceRspBean);
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String responseStr = Objects.requireNonNull(response.body()).string();
                mMainHandler.post(() -> {
                    Logcat.d("【" + interfaceBean.getInterfaceName() + "】出参==>>" + responseStr);
                    FlBaseInterfaceRspBean interfaceRspBean = new FlBaseInterfaceRspBean();
                    interfaceRspBean.setHttpCode(response.code());
                    interfaceRspBean.setHttpResult(responseStr);
                    interfaceRspBean.setInterfaceId(interfaceBean.getInterfaceId());
                    ihttpFactoryMonitor.onNetWorkResponse(interfaceRspBean);
                });
            }
        });
    }

    @Override
    public void sendGet(String serverUrl, FlBaseInterfaceReqBean interfaceBean, IhttpFactoryMonitor ihttpFactoryMonitor) {
        OkHttpClient client = new OkHttpClient();
        client.newBuilder().connectTimeout(180, TimeUnit.SECONDS).readTimeout(180, TimeUnit.SECONDS).build();
        Request request = new Request.Builder()
                .addHeader("Authorization", "Bearer " + ACache.get(interfaceBean.getContext()).getAsString("token"))
                .url(serverUrl + interfaceBean.getInterfaceName())
                .build();
        Logcat.d("【" + interfaceBean.getInterfaceName() + "】入参==>>" + interfaceBean.getInterfaceName());
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                mMainHandler.post(() -> {
                    FlBaseInterfaceRspBean interfaceRspBean = new FlBaseInterfaceRspBean();
                    interfaceRspBean.setHttpCode(504);
                    interfaceRspBean.setHttpResult(e.getMessage());
                    interfaceRspBean.setInterfaceId(interfaceBean.getInterfaceId());
                    ihttpFactoryMonitor.onNetWorkResponse(interfaceRspBean);
                });
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                final String responseStr = Objects.requireNonNull(response.body()).string();
                mMainHandler.post(() -> {
                    Logcat.d("【" + interfaceBean.getInterfaceName() + "】出参==>>" + responseStr);
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
