package top.lsmod.basemodel.constom;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import top.lsmod.basemodel.R;

/**
 * Created by huangminzheng on 2017/9/3 上午10:25.
 * Email:ahtchmz@gmail.com
 */

public class MineSwitchView extends LinearLayout implements View.OnClickListener {

    //上下文
    private Context mContext;
    //左边文字
    private String leftText;
    //左边文字颜色
    private int leftTextColor;
    //是否显示横线
    private boolean showLine;

    private TextView tvLeft;
    private View viewLine;
    private String leftBottomText;
    private TextView tvLeftBottom;
    private boolean isOpen;
    private ImageView ivSwitch;
    private OnSwitchClickListener onSwitchClickListener;
    private TextView tvRight;
    private String rightText;
    private LinearLayout llItem;
    private int flag;

    public void setOnSwitchClickListener(OnSwitchClickListener onSwitchClickListener) {
        this.onSwitchClickListener = onSwitchClickListener;
    }

    public MineSwitchView(Context context) {
        super(context);
        initAttrs(context, null);
        init();
    }

    public MineSwitchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        init();
    }

    public MineSwitchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        init();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        mContext = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomMineSwitchView);
        leftText = typedArray.getString(R.styleable.CustomMineSwitchView_left_text);
        rightText = typedArray.getString(R.styleable.CustomMineSwitchView_right_text);
        leftBottomText = typedArray.getString(R.styleable.CustomMineSwitchView_left_bottom_text);
        leftTextColor = typedArray.getResourceId(R.styleable.CustomMineSwitchView_left_text_color, R.color.gray_46);
        showLine = typedArray.getBoolean(R.styleable.CustomMineSwitchView_show_line, true);
        isOpen = typedArray.getBoolean(R.styleable.CustomMineSwitchView_open, true);
        flag = typedArray.getInteger(R.styleable.CustomMineSwitchView_flag, 0);
        typedArray.recycle();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_custom_mine_switch, this, true);
        llItem = (LinearLayout) findViewById(R.id.ll_item);
        tvLeft = (TextView) findViewById(R.id.tv_left);
        tvRight = (TextView) findViewById(R.id.tv_right);
        tvLeftBottom = (TextView) findViewById(R.id.tv_left_bottom);
        viewLine = findViewById(R.id.view_line);
        tvLeft.setText(TextUtils.isEmpty(leftText) ? "" : leftText);
        tvLeft.setTextColor(ContextCompat.getColor(mContext, leftTextColor));
        tvRight.setText(TextUtils.isEmpty(rightText) ? "" : rightText);
        ivSwitch = (ImageView) findViewById(R.id.iv_switch);
        ivSwitch.setSelected(isOpen);
        ivSwitch.setOnClickListener(this);
        if (TextUtils.isEmpty(leftBottomText)) {
            tvLeftBottom.setVisibility(GONE);
        } else {
            tvLeftBottom.setVisibility(VISIBLE);
            tvLeftBottom.setText(leftBottomText);
        }
        viewLine.setVisibility(showLine ? VISIBLE : GONE);
    }

    public void openSwitch(boolean isOpen) {
        ivSwitch.setSelected(isOpen);
        if (ivSwitch.isSelected() && !TextUtils.isEmpty(leftBottomText)) {
            tvLeftBottom.setVisibility(VISIBLE);
        } else {
            tvLeftBottom.setVisibility(GONE);
        }
    }

    /**
     * 开启开关
     */
    public void openSwitch() {
        ivSwitch.setSelected(true);
        if (ivSwitch.isSelected() && !TextUtils.isEmpty(leftBottomText)) {
            tvLeftBottom.setVisibility(VISIBLE);
        } else {
            tvLeftBottom.setVisibility(GONE);
        }
    }

    /**
     * 关闭开关
     */
    public void closeSwitch() {
        ivSwitch.setSelected(false);
        if (ivSwitch.isSelected() && !TextUtils.isEmpty(leftBottomText)) {
            tvLeftBottom.setVisibility(VISIBLE);
        } else {
            tvLeftBottom.setVisibility(GONE);
        }
    }

    /**
     * 获取开关是否开启
     */
    public boolean isSwitchOpen() {
        return ivSwitch.isSelected();
    }

    /**
     * 获取开关是否关闭
     */
    public boolean isSwitchClose() {
        return !ivSwitch.isSelected();
    }


    public void setClickable(boolean clickable) {
        ivSwitch.setClickable(clickable);
        if (clickable) {
            llItem.setBackgroundColor(mContext.getResources().getColor(R.color.white));
        } else {
            llItem.setBackgroundColor(mContext.getResources().getColor(R.color.bg_gray));
        }
    }

    /**
     * 设置左边文字
     *
     * @param leftText 左边文字
     */
    public void setLeftText(String leftText) {
        tvLeft.setText(leftText);
    }

    public void setRightText(String rightText) {
        tvRight.setVisibility(VISIBLE);
        tvRight.setText(rightText);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_switch) {
            ivSwitch.setSelected(!ivSwitch.isSelected());
            isOpen = ivSwitch.isSelected();
            tvLeftBottom.setVisibility(ivSwitch.isSelected() && !TextUtils.isEmpty(tvLeftBottom.getText().toString().trim()) ? VISIBLE : GONE);
            if (onSwitchClickListener != null) {
                onSwitchClickListener.switchClick(flag, ivSwitch.isSelected());
            }
        }
    }

    public interface OnSwitchClickListener {
        void switchClick(int flag, boolean isOpen);
    }
}
