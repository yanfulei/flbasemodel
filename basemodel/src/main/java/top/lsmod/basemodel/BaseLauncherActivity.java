package top.lsmod.basemodel;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;

import butterknife.ButterKnife;
import top.lsmod.basemodel.base.FlBaseInterfaceReqBean;
import top.lsmod.basemodel.base.FlBaseInterfaceRspBean;
import top.lsmod.basemodel.base.IHttpFactory;
import top.lsmod.basemodel.base.impl.OkHttpImpl;
import top.lsmod.basemodel.constom.LoadingDialog;
import top.lsmod.basemodel.utils.ActivityCollector;
import top.lsmod.basemodel.utils.HttpUtils;

public abstract class BaseLauncherActivity extends Activity {
    private Handler mHandler = new Handler();
    // loading组件
    private LoadingDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置布局
        setContentView(initLayout());
        // 初始化loading
        dialog = new LoadingDialog(this);
        ButterKnife.bind(this);
        initView();
        setTimer();
    }

    private void setTimer() {
        mHandler.postDelayed(runnable, 3000);
    }

    Runnable runnable = () -> runOnUiThread(this::initData);

    /**
     * 初始化布局
     *
     * @return 布局id
     */
    protected abstract int initLayout();

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 设置数据
     */
    protected abstract void initData();


    /**
     * 展示loading
     */
    public void showLoading() {
        if (null != dialog && !dialog.isShowing()) {
            dialog.show();
        }
    }

    /**
     * 隐藏loading
     */
    public void hideLoading() {
        if (null != dialog && dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    /**
     * 发送网络请求
     */
    public void sendRequest(String serverUrl, FlBaseInterfaceReqBean interfaceBean) {
        showLoading();
        IHttpFactory httpFactory = new OkHttpImpl();
        if (interfaceBean.getInterfaceType().toLowerCase().contains("get")) {
            String param = HttpUtils.parseURLPair(null != interfaceBean.getParam() ? interfaceBean.getParam() : "");
            interfaceBean.setInterfaceName(param.isEmpty() ? interfaceBean.getInterfaceName() : interfaceBean.getInterfaceName() + "?" + param);
            httpFactory.sendGet(serverUrl, interfaceBean, this::onNetWorkResponse);
        } else if (interfaceBean.getInterfaceType().toLowerCase().contains("post")) {
            httpFactory.sendPost(serverUrl, interfaceBean, this::onNetWorkResponse);
        }
    }

    /**
     * 网络请求返回信息
     */
    public void onNetWorkResponse(FlBaseInterfaceRspBean interfaceRspBean) {
        hideLoading();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //activity管理
        ActivityCollector.removeActivity(this);
    }
}
