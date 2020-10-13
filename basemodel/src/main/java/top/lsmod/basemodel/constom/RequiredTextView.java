package top.lsmod.basemodel.constom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

import top.lsmod.basemodel.R;


public class RequiredTextView extends androidx.appcompat.widget.AppCompatTextView {

    private String prefix = "";
    private int prefixColor = Color.RED;
    private boolean isRequired;

    public RequiredTextView(Context context) {
        super(context);
    }

    public RequiredTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public RequiredTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RequiredTextView);

        prefix = ta.getString(R.styleable.RequiredTextView_prefix_text);
        prefixColor = ta.getInteger(R.styleable.RequiredTextView_prefix_color, Color.RED);
        String text = ta.getString(R.styleable.RequiredTextView_android_text);
        if (!TextUtils.isEmpty(prefix)) {
            isRequired = true;
        }
        if (TextUtils.isEmpty(text)) {
            text = "";
        }
        ta.recycle();
        setText(text);
    }

    public void setText(String text) {
        Spannable span = new SpannableString((null == prefix ? "" : prefix) + text);
        span.setSpan(new ForegroundColorSpan(prefixColor), 0, null == prefix ? 0 : prefix.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        setText(span);
    }

    public void setRequired(boolean yes) {
        this.isRequired = yes;
        String newText = getText().toString().replaceFirst("\\*", "");
        Spannable span;
        if (yes) {
            span = new SpannableString("*" + newText);
            span.setSpan(new ForegroundColorSpan(prefixColor), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else {
            span = new SpannableString(newText);
            span.setSpan(new ForegroundColorSpan(prefixColor), 0, 0, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        setText(span);
    }

    public boolean isRequired() {
        return isRequired;
    }
}