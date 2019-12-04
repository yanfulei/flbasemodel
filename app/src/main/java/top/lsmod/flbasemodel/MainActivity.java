package top.lsmod.flbasemodel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import top.lsmod.basemodel.constom.LoadingDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoadingDialog dialog = new LoadingDialog(this);
        dialog.show();
    }
}
