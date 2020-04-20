package top.lsmod.basemodel.pen;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import top.lsmod.basemodel.R;
import top.lsmod.basemodel.pen.util.StatusBarCompat;


/***
 * Activity基类
 *
 * @since 2018-06-25
 * @author king
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected View actionbar;
    protected TextView tvCancel;
    protected TextView tvSave;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initTitleBar();
        initView();
        initData();
    }

    /**
     * 初始化标题栏
     */
    protected void initTitleBar() {
        actionbar = findViewById(R.id.actionbar);
        tvCancel = findViewById(R.id.tv_cancel);
        tvSave = findViewById(R.id.tv_ok);
    }


    /**
     * 获取布局
     */
    protected abstract int getLayout();

    /**
     * 初始化视图
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 设置主题颜色
     *
     * @param color 主题颜色
     */
    protected void setThemeColor(int color) {
        try {
            if (actionbar != null) {
                actionbar.setBackgroundColor(color);
            }
            StatusBarCompat.compat(this, color);
        } catch (Exception e) {

        }
    }

}
