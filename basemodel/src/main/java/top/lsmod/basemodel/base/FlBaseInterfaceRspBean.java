package top.lsmod.basemodel.base;

public class FlBaseInterfaceRspBean {
    // 请求的ID
    private int interfaceId;
    // 请求http返回code
    private int httpCode;
    // 请求http返回信息
    private String httpResult;

    public int getHttpCode() {
        return httpCode;
    }

    public int getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(int interfaceId) {
        this.interfaceId = interfaceId;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }

    public String getHttpResult() {
        return httpResult;
    }

    public void setHttpResult(String httpResult) {
        this.httpResult = httpResult;
    }
}
