package top.lsmod.basemodel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import butterknife.ButterKnife;
import top.lsmod.basemodel.base.FlBaseInterfaceReqBean;
import top.lsmod.basemodel.base.FlBaseInterfaceRspBean;
import top.lsmod.basemodel.base.IHttpFactory;
import top.lsmod.basemodel.base.impl.OkHttpImpl;
import top.lsmod.basemodel.constom.LoadingDialog;
import top.lsmod.basemodel.utils.HttpUtils;

public abstract class FlBaseFragment extends Fragment {
    // loading组件
    private LoadingDialog dialog;
    // 列表空布局
    private ImageView imageView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //设置布局
        View root = inflater.inflate(R.layout.fragment_fl_base, container, false);
        imageView = root.findViewById(R.id.iv_list_empty);
        FrameLayout frameLayout = root.findViewById(R.id.fl_all_view);
        View childView = getLayoutInflater().inflate(initLayout(), null);
        frameLayout.addView(childView);
        // 初始化loading
        dialog = new LoadingDialog(getActivity());
        ButterKnife.bind(this, root);
        //初始化控件
        initView();
        //设置数据
        initData();
        return root;
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int initLayout();


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
     * 获取空布局
     *
     * @return
     */
    public ImageView getEmptyView() {
        return imageView;
    }

    /**
     * 发送网络请求
     *
     * @param interfaceBean
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
     *
     * @param interfaceRspBean
     */
    public void onNetWorkResponse(FlBaseInterfaceRspBean interfaceRspBean) {
        hideLoading();
    }
}
