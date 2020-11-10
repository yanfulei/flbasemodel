package top.lsmod.basemodel.base;

public interface IHttpFactory {

    void sendPost(String serverUrl, FlBaseInterfaceReqBean interfaceBean, IhttpFactoryMonitor ihttpFactoryMonitor);

    void sendGet(String serverUrl, FlBaseInterfaceReqBean interfaceBean, IhttpFactoryMonitor ihttpFactoryMonitor);

    void sendFile(String serverUrl, FlBaseInterfaceReqBean interfaceBean, IhttpFactoryMonitor ihttpFactoryMonitor);

    interface IhttpFactoryMonitor {

        void onNetWorkResponse(FlBaseInterfaceRspBean interfaceRspBean);
    }
}
