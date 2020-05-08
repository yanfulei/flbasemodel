package top.lsmod.basemodel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;
import top.lsmod.basemodel.base.FlBaseInterfaceReqBean;
import top.lsmod.basemodel.base.FlBaseInterfaceRspBean;
import top.lsmod.basemodel.base.IHttpFactory;
import top.lsmod.basemodel.base.impl.OkHttpImpl;
import top.lsmod.basemodel.constom.LoadingDialog;
import top.lsmod.basemodel.utils.ActivityCollector;
import top.lsmod.basemodel.utils.HttpUtils;

public abstract class FlBaseAppActivity extends AppCompatActivity {
    // 获取TAG的activity名称
    protected final String TAG = this.getClass().getSimpleName();
    // 是否显示标题栏
    private boolean isShowTitle = true;
    // 是否显示状态栏
    private boolean isShowStatusBar = true;
    // 是否允许旋转屏幕
    private boolean isAllowScreenRoate = true;
    // 封装Toast对象
    private static Toast toast;
    public Context context;
    // loading组件
    private LoadingDialog dialog;
    // 列表空布局
    private ImageView imageView;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        // 设置数据
        initData();
        //activity管理
        ActivityCollector.addActivity(this);
        if (!isShowTitle) {
            supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        }

        if (!isShowStatusBar) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }

        // 设置布局
        View base = getLayoutInflater().inflate(R.layout.activity_fl_base_app, null);
        imageView = base.findViewById(R.id.iv_list_empty);
        FrameLayout frameLayout = base.findViewById(R.id.fl_all_view);
        View childView;
        if (initLayout() instanceof Integer) {
            childView = getLayoutInflater().inflate(Integer.parseInt(String.valueOf(initLayout())), null);
        } else {
            childView = (View) initLayout();
        }
        frameLayout.addView(childView);
        setContentView(base);
        // 初始化loading
        dialog = new LoadingDialog(this);
        ButterKnife.bind(this);
        // 设置屏幕是否可旋转
        if (!isAllowScreenRoate) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        // 初始化控件
        initView();
    }

    /**
     * 初始化布局
     *
     * @return 布局id
     */
    protected abstract Object initLayout();

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 设置数据
     */
    protected abstract void initData();

    /**
     * 设置是否显示标题栏
     *
     * @param showTitle true or false
     */
    public void setShowTitle(boolean showTitle) {
        isShowTitle = showTitle;
    }

    /**
     * 设置是否显示状态栏
     *
     * @param showStatusBar true or false
     */
    public void setShowStatusBar(boolean showStatusBar) {
        isShowStatusBar = showStatusBar;
    }

    /**
     * 是否允许屏幕旋转
     *
     * @param allowScreenRoate true or false
     */
    public void setAllowScreenRoate(boolean allowScreenRoate) {
        isAllowScreenRoate = allowScreenRoate;
    }

    /**
     * 保证同一按钮在1秒内只会响应一次点击事件
     */
    public abstract class OnSingleClickListener implements View.OnClickListener {
        //两次点击按钮之间的间隔，目前为1000ms
        private static final int MIN_CLICK_DELAY_TIME = 1000;
        private long lastClickTime;

        public abstract void onSingleClick(View view);

        @Override
        public void onClick(View view) {
            long curClickTime = System.currentTimeMillis();
            if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
                lastClickTime = curClickTime;
                onSingleClick(view);
            }
        }
    }

    /**
     * 同一按钮在短时间内可重复响应点击事件
     */
    public abstract class OnMultiClickListener implements View.OnClickListener {
        public abstract void onMultiClick(View view);

        @Override
        public void onClick(View v) {
            onMultiClick(v);
        }
    }

    /**
     * 显示提示  toast
     *
     * @param msg 提示信息
     */
    @SuppressLint("ShowToast")
    public void showToast(String msg) {
        try {
            if (null == toast) {
                toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
            } else {
                toast.setText(msg);
            }
            runOnUiThread(() -> toast.show());
        } catch (Exception e) {
            e.printStackTrace();
            //解决在子线程中调用Toast的异常情况处理
            Looper.prepare();
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            Looper.loop();
        }
    }


    /**
     * 隐藏软键盘
     */
    public void hideSoftInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (getCurrentFocus() != null && null != imm) {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    /**
     * 显示软键盘
     */
    public void showSoftInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (getCurrentFocus() != null && null != imm) {
            imm.showSoftInputFromInputMethod(getCurrentFocus().getWindowToken(), 0);
        }
    }

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
            String param = HttpUtils.parseURLPair(interfaceBean.getParam());
            interfaceBean.setInterfaceName(interfaceBean.getInterfaceName() + "?" + param);
            httpFactory.sendGet(serverUrl, interfaceBean, interfaceRspBean -> onNetWorkResponse(interfaceRspBean));
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //activity管理
        ActivityCollector.removeActivity(this);
    }
}
