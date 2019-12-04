package top.lsmod.flbasemodel;

import butterknife.BindView;
import top.lsmod.basemodel.FlBaseAppActivity;
import top.lsmod.basemodel.tfb.ExtendedEditText;
import top.lsmod.basemodel.tfb.TextFieldBoxes;

public class MainActivity extends FlBaseAppActivity {

    @BindView(R.id.ee_server)
    ExtendedEditText eeServer;
    @BindView(R.id.tfb_server)
    TextFieldBoxes tfbServer;

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        eeServer.setText("asdfds");
    }

    @Override
    protected void initData() {

    }
}
