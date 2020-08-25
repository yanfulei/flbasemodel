package top.lsmod.basemodel.utils;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by yanfulei on 2018/10/1
 * Email yanfulei1990@gmail.com
 */
public class ToastUtils {

    public static int ERROR = 1;
    public static int INFO = 2;
    public static int WARNING = 3;
    public static int SUCCESS = 4;

    /**
     * 展示Toast
     *
     * @param ctx
     * @param msg
     * @param state
     */
    public static void showToast(final Activity ctx, final String msg, int state) {
        // 判断是在子线程，还是主线程
        if ("main".equals(Thread.currentThread().getName())) {
            Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
        } else {
            // 子线程
            ctx.runOnUiThread(() -> Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show());
        }
    }
}
