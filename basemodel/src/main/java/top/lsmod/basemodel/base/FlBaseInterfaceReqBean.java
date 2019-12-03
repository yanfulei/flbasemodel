package top.lsmod.basemodel.base;

public class FlBaseInterfaceReqBean {
    // 接口名称
    private String interfaceName;
    // 接口唯一编号
    private int interfaceId;
    // 接口请求方式
    private String interfaceType;
    // 请求的参数
    private Object param;

    public FlBaseInterfaceReqBean(Object[] interfaces, Object param) {
        this.interfaceName = String.valueOf(interfaces[0]);
        this.interfaceId = Integer.valueOf(String.valueOf(interfaces[1]));
        this.interfaceType = String.valueOf(interfaces[2]);
        this.param = param;
    }

    public Object getParam() {
        return param;
    }

    public void setParam(Object param) {
        this.param = param;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public int getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(int interfaceId) {
        this.interfaceId = interfaceId;
    }

    public String getInterfaceType() {
        return interfaceType;
    }

    public void setInterfaceType(String interfaceType) {
        this.interfaceType = interfaceType;
    }
}
