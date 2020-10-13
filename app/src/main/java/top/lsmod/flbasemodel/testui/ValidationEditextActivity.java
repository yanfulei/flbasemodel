package top.lsmod.flbasemodel.testui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import top.lsmod.basemodel.FlBaseAppActivity;
import top.lsmod.flbasemodel.R;

public class ValidationEditextActivity extends FlBaseAppActivity {
    @NotEmpty(message = "请输入信息")
    @BindView(R.id.et_test1)
    EditText etTest1;

    @BindView(R.id.btn_subbmit)
    Button btnSubbmit;
    @Password(min = 6, scheme = Password.Scheme.ALPHA_NUMERIC_MIXED_CASE_SYMBOLS, message = "错误的密码")
    @BindView(R.id.et_password)
    EditText etPassword;

    @Override
    protected Object initLayout() {
        return R.layout.activity_validation_editext;
    }

    @Override
    protected void initView() {
        btnSubbmit.setOnClickListener(new OnMultiClickListener() {
            @Override
            public void onMultiClick(View view) {
                validator.validate();
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onValidationSucceeded() {
        Toast.makeText(this, "Yay! we got it right!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
