package top.lsmod.basemodel.base;

public interface IHttpFactory {

    void sendPost(FlBaseInterfaceReqBean interfaceBean);

    void sendGet(String serverUrl, FlBaseInterfaceReqBean interfaceBean, IhttpFactoryMonitor ihttpFactoryMonitor);

    interface IhttpFactoryMonitor {

        void onNetWorkResponse(FlBaseInterfaceRspBean interfaceRspBean);
    }
}
