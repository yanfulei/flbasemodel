package top.lsmod.flbasemodel;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import top.lsmod.basemodel.FlBaseAppActivity;
import top.lsmod.flbasemodel.testui.BtnActivity;
import top.lsmod.flbasemodel.testui.NiceImageViewAcitvity;
import top.lsmod.flbasemodel.testui.PanMainActivity;
import top.lsmod.flbasemodel.testui.SmartRefreshActivity;
import top.lsmod.flbasemodel.testui.TfbActivity;

public class MainActivity extends FlBaseAppActivity {

    @BindView(R.id.btn_tfb)
    Button btnTfb;
    @BindView(R.id.btn_sm)
    Button btnSm;
    @BindView(R.id.btn_btn)
    Button btnBtn;
    @BindView(R.id.btn_pan)
    Button btnPan;
    @BindView(R.id.NiceImageView)
    Button NiceImageView;

    @Override
    protected Object initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        btnSm.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, SmartRefreshActivity.class)));
        btnBtn.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, BtnActivity.class)));
        btnTfb.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, TfbActivity.class)));
        btnPan.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, PanMainActivity.class)));
        NiceImageView.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, NiceImageViewAcitvity.class)));
    }

    @Override
    protected void initData() {
        setShowStatusBar(false);
        setShowTitle(false);
    }
}
