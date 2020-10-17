package top.lsmod.flbasemodel;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.mobsandgeeks.saripaar.ValidationError;

import java.util.List;

import butterknife.BindView;
import top.lsmod.basemodel.FlBaseAppActivity;
import top.lsmod.basemodel.constom.RequiredTextView;
import top.lsmod.flbasemodel.testui.BtnActivity;
import top.lsmod.flbasemodel.testui.NiceImageViewAcitvity;
import top.lsmod.flbasemodel.testui.PanMainActivity;
import top.lsmod.flbasemodel.testui.SmartRefreshActivity;
import top.lsmod.flbasemodel.testui.SmoothCheckBoxActivity;
import top.lsmod.flbasemodel.testui.TfbActivity;
import top.lsmod.flbasemodel.testui.ValidationEditextActivity;

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
    @BindView(R.id.btn_scb)
    Button btnScb;
    @BindView(R.id.btn_vea)
    Button btnVea;
    @BindView(R.id.rtv_reque)
    RequiredTextView rtvReque;
    @BindView(R.id.btn_zhbt)
    Button btnZhbt;
    @BindView(R.id.btn_wc)
    Button btnWc;

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
        btnScb.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SmoothCheckBoxActivity.class)));
        btnVea.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View view) {
                startActivity(new Intent(MainActivity.this, ValidationEditextActivity.class));
            }
        });
        btnZhbt.setOnClickListener(v -> rtvReque.setRequired(!rtvReque.isRequired()));
    }

    @Override
    protected void initData() {
        setShowStatusBar(false);
        setShowTitle(false);
    }

    @Override
    public void onValidationSucceeded() {

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {

    }
}
