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

public class MineItemView extends LinearLayout {

    //上下文
    private Context mContext;
    //左边文字
    private String leftText;
    //右边文字
    private String rightText;
    //右边文字hint
    private String rightHint;
    //左边图标
    private int leftIcon;
    //左边图标是否可见
    private int leftIconVisibility;
    //左边文字颜色
    private int leftTextColor;
    //右边文字颜色
    private int rightTextColor;
    //是否显示箭头
    private boolean showArrow;
    //是否显示横线
    private boolean showLine;

    private ImageView ivLefIcon;
    private TextView tvLeft;
    private TextView tvRight;
    private ImageView ivRightArrow;
    private View viewLine;

    public MineItemView(Context context) {
        super(context);
        initAttrs(context, null);
        init();
    }

    public MineItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        init();
    }

    public MineItemView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
        init();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        mContext = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomMineItemView);
        leftText = typedArray.getString(R.styleable.CustomMineItemView_left_text);
        leftIcon = typedArray.getResourceId(R.styleable.CustomMineItemView_left_icon_src, 0);
        leftIconVisibility = typedArray.getInteger(R.styleable.CustomMineItemView_left_icon_visibility, 1);
        rightText = typedArray.getString(R.styleable.CustomMineItemView_right_text);
        leftTextColor = typedArray.getResourceId(R.styleable.CustomMineItemView_left_text_color, R.color.gray_46);
        rightTextColor = typedArray.getResourceId(R.styleable.CustomMineItemView_right_text_color, R.color.gray_9b);
        showArrow = typedArray.getBoolean(R.styleable.CustomMineItemView_show_arrow, true);
        showLine = typedArray.getBoolean(R.styleable.CustomMineItemView_show_line, true);
        rightHint = typedArray.getString(R.styleable.CustomMineItemView_right_hint);
        typedArray.recycle();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_mine_item, this, true);
        ivLefIcon = (ImageView) findViewById(R.id.iv_left_icon);
        tvLeft = (TextView) findViewById(R.id.tv_left);
        tvRight = (TextView) findViewById(R.id.tv_right);
        ivRightArrow = (ImageView) findViewById(R.id.iv_right_arrow);
        viewLine = findViewById(R.id.view_line);
        tvRight.setHint(rightHint);
        ivLefIcon.setVisibility(leftIcon == 0 ? GONE : VISIBLE);
        ivLefIcon.setImageResource(leftIcon);
        switch (leftIconVisibility) {
            case 0:
                ivLefIcon.setVisibility(GONE);
                break;
            case 1:
                ivLefIcon.setVisibility(VISIBLE);
                break;
            case 2:
                ivLefIcon.setVisibility(INVISIBLE);
                break;
            default:
                break;
        }
        tvLeft.setText(TextUtils.isEmpty(leftText) ? "" : leftText);
        tvLeft.setTextColor(ContextCompat.getColor(mContext, leftTextColor));
        tvRight.setText(TextUtils.isEmpty(rightText) ? "" : rightText);
        tvRight.setTextColor(ContextCompat.getColor(mContext, rightTextColor));
        ivRightArrow.setVisibility(showArrow ? VISIBLE : GONE);
        viewLine.setVisibility(showLine ? VISIBLE : GONE);
    }

    /**
     * 设置左边图标
     *
     * @param leftIcon
     */
    public void setLeftIconImg(int leftIcon) {
        ivLefIcon.setVisibility(VISIBLE);
        ivLefIcon.setImageResource(leftIcon);
    }

    /**
     * 设置左边文字
     *
     * @param leftText 左边文字
     */
    public void setLeftText(String leftText) {
        tvLeft.setText(leftText);
    }

    /**
     * 设置右边文字
     *
     * @param rightText 右边文字
     */
    public void setRightText(String rightText) {
        tvRight.setText(rightText);
    }

    public TextView getTvRight() {
        return tvRight;
    }

    /**
     * 设置右边文字颜色
     *
     * @param rightTextColor
     */
    public void setRightTextColor(int rightTextColor) {
        tvRight.setTextColor(mContext.getResources().getColor(rightTextColor));
    }
}
