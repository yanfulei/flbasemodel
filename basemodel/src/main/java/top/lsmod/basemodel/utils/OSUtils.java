package top.lsmod.basemodel.utils;

import android.text.TextUtils;

import java.io.IOException;

public class OSUtils {
    public enum ROM_TYPE {

        MIUI_ROM,

        FLYME_ROM,

        EMUI_ROM,

        OTHER_ROM

    }

    /**
     * MIUI ROM标识
     * <p>
     * "ro.miui.ui.version.code" -> "5"
     * <p>
     * "ro.miui.ui.version.name" -> "V7"
     * <p>
     * "ro.miui.has_handy_mode_sf" -> "1"
     * <p>
     * "ro.miui.has_real_blur" -> "1"
     * <p>
     * <p>
     * <p>
     * Flyme ROM标识
     * <p>
     * "ro.build.user" -> "flyme"
     * <p>
     * "persist.sys.use.flyme.icon" -> "true"
     * <p>
     * "ro.flyme.published" -> "true"
     * <p>
     * "ro.build.display.id" -> "Flyme OS 5.1.2.0U"
     * <p>
     * "ro.meizu.setupwizard.flyme" -> "true"
     * <p>
     * <p>
     * <p>
     * EMUI ROM标识
     * <p>
     * "ro.build.version.emui" -> "EmotionUI_1.6"
     */

    //MIUI标识
    private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";

    //EMUI标识
    private static final String KEY_EMUI_VERSION_CODE = "ro.build.version.emui";

    //Flyme标识
    private static final String KEY_FLYME_ID_FALG_KEY = "ro.build.display.id";
    private static final String KEY_FLYME_ID_FALG_VALUE_KEYWORD = "Flyme";
    private static final String KEY_FLYME_ICON_FALG = "persist.sys.use.flyme.icon";
    private static final String KEY_FLYME_SETUP_FALG = "ro.meizu.setupwizard.flyme";
    private static final String KEY_FLYME_PUBLISH_FALG = "ro.flyme.published";

    /**
     * @param
     * @return ROM_TYPE ROM类型的枚举
     * @datecreate at 2016/5/11 0011 9:46
     * @mehtodgetRomType
     * @description获取ROM类型，MIUI_ROM, *FLYME_ROM,    * EMUI_ROM,    * OTHER_ROM
     */
    public static ROM_TYPE getRomType() {
        ROM_TYPE rom_type = ROM_TYPE.OTHER_ROM;
        try {
            BuildProperties buildProperties = BuildProperties.getInstance();
            if (buildProperties.containsKey(KEY_EMUI_VERSION_CODE)) {
                return ROM_TYPE.EMUI_ROM;
            }
            if (buildProperties.containsKey(KEY_MIUI_VERSION_CODE) || buildProperties.containsKey(KEY_MIUI_VERSION_NAME)) {
                return ROM_TYPE.MIUI_ROM;
            }
            if (buildProperties.containsKey(KEY_FLYME_ICON_FALG) || buildProperties.containsKey(KEY_FLYME_SETUP_FALG) || buildProperties.containsKey(KEY_FLYME_PUBLISH_FALG)) {
                return ROM_TYPE.FLYME_ROM;
            }
            if (buildProperties.containsKey(KEY_FLYME_ID_FALG_KEY)) {
                String romName = buildProperties.getProperty(KEY_FLYME_ID_FALG_KEY);
                if (!TextUtils.isEmpty(romName) && romName.contains(KEY_FLYME_ID_FALG_VALUE_KEYWORD)) {
                    return ROM_TYPE.FLYME_ROM;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rom_type;
    }
}
