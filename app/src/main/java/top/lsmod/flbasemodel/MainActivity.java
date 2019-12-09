package top.lsmod.flbasemodel;

import android.content.Intent;
import android.widget.Button;

import butterknife.BindView;
import top.lsmod.basemodel.FlBaseAppActivity;
import top.lsmod.flbasemodel.testui.SmartRefreshActivity;

public class MainActivity extends FlBaseAppActivity {

    @BindView(R.id.btn_tfb)
    Button btnTfb;
    @BindView(R.id.btn_sm)
    Button btnSm;

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        btnSm.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, SmartRefreshActivity.class)));
    }

    @Override
    protected void initData() {

    }
}
