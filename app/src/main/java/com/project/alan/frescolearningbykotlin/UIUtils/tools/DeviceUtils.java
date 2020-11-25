package com.project.alan.frescolearningbykotlin.UIUtils.tools;

import android.app.ActivityManager;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.os.StatFs;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.project.alan.frescolearningbykotlin.system.SystemApplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Locale;


public class DeviceUtils {
    private static final String TAG = "DeviceUtils";

    // operation_tips_image_1_.5 sdk
    private static int sSdkVersion = -1;

    private static int screenWidth = 0;
    private static int screenHeight = 0;
    /**
     * 状态栏高度
     */
    private static int statusBarHeight = -1;
    /**
     * 虚拟功能按键高度
     */
    private static int virtualBarHeight = -1;
    private static String IMEI;
    private static String oSVersion;
    /**
     * 设备cpu是几核
     */
    private static int deviceCpuCores = -1;
    private static Boolean canSetStatusFont = null;

    public static int getCpuCoreNum() {
        if (deviceCpuCores != -1) {
            return deviceCpuCores;
        }
        deviceCpuCores = Runtime.getRuntime().availableProcessors();
        return deviceCpuCores;
    }

    /**
     * getProcessor:获取CPU的型号
     *
     * @return
     */
    public static String getCPUProcessor() {

        String result = "";
        BufferedReader br = null;

        try {
            Process process = Runtime.getRuntime().exec("/system/bin/cat/proc/cpuinfo");

            InputStream in = process.getInputStream();
            br = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = br.readLine()) != null) {
                if (line.contains("Processor")) {
                    line = line.substring(line.indexOf(":") + 1);
                    result = line.trim();
                    break;
                }
            }
        } catch (IOException e) {
            LogUtils.e(e);
        } catch (Throwable e) {
            LogUtils.e(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    LogUtils.e(e);
                }
            }
        }
        return result;
    }


    /**
     * getMhz:获取CPU的主频
     *
     * @return
     */

    public static int getCPUMhz() {
        int result = 0;
        BufferedReader br = null;

        try {
            Process process = Runtime.getRuntime().exec("/system/bin/cat /sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq");

            InputStream in = process.getInputStream();
            br = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = br.readLine()) != null) {
                result = Integer.parseInt(line.trim()) / 1000;
                break;
            }
        } catch (IOException e) {
            LogUtils.e(e);
        } catch (Throwable e) {
            LogUtils.e(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    LogUtils.e(e);
                }
            }

        }
        return result;
    }

    /**
     * 获取SDK版本号
     *
     * @return
     */
    public static int getSdkVersion() {
        if (-1 == sSdkVersion)
            sSdkVersion = Build.VERSION.SDK_INT;
        return sSdkVersion;
    }

    /**
     * 获取设备名称
     */
    public static String getDeviceName() {
        return Build.MODEL.replaceAll("[ |\\/|\\_|\\&|\\|]", "");
    }

    /**
     * 获取是否root
     *
     * @return
     */
    public static boolean getIsRootByFile() {
        LogUtils.d(TAG, "getIsRootByFile");
        File f = null;
        final String kSuSearchPaths[] = {"/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/"};
        try {
            for (int i = 0; i < kSuSearchPaths.length; i++) {
                f = new File(kSuSearchPaths[i] + "su");
                if (f != null && f.exists()) {
                    LogUtils.d(TAG, kSuSearchPaths[i]);
                    return true;
                }
            }
        } catch (Exception e) {
            LogUtils.e(e);
        }
        return false;
    }

    /**
     * 获取当前设备的可用内存
     */
    public static long getDeviceAvailableMemory(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (am != null) {
            ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
            am.getMemoryInfo(info);
            return info.availMem / 1024;
        } else {
            return 0;
        }
    }

    public static int getScreenWidth(Context context) {
        if (screenWidth == 0) {
            DisplayMetrics dm = new DisplayMetrics();
            WindowManager wm = (WindowManager) context
                    .getSystemService(Context.WINDOW_SERVICE);
            wm.getDefaultDisplay().getMetrics(dm);
            screenWidth = dm.widthPixels;
        }
        return screenWidth;
    }

    public static int getScreenHeight(Context context) {
        if (screenHeight == 0) {
            DisplayMetrics dm = new DisplayMetrics();
            WindowManager wm = (WindowManager) context
                    .getSystemService(Context.WINDOW_SERVICE);
            wm.getDefaultDisplay().getMetrics(dm);
            screenHeight = dm.heightPixels;
        }
        return screenHeight;
    }


    public static String getSystemInfo() {
        if (null == oSVersion) {
            oSVersion = "SDKV = " + Build.VERSION.RELEASE;
            oSVersion += "_MANUFACTURER = " + Build.MANUFACTURER;
            oSVersion += "_MODEL = " + Build.MODEL;
            oSVersion += "_PRODUCT = " + Build.PRODUCT;
            oSVersion += "_FINGERPRINT = " + Build.FINGERPRINT;
            oSVersion += "_CPU_ABI = " + Build.CPU_ABI;
            oSVersion += "_ID = " + Build.ID;
        }
        return oSVersion;
    }

    public static Signature getSignature(Context context) {
        try {

            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
            if (info.signatures != null && info.signatures.length > 0)
                return info.signatures[0];
        } catch (Exception e) {
            LogUtils.e(e);
        }
        return null;
    }

    public static boolean isHuaWeiP6() {
        return getDeviceName().trim().equalsIgnoreCase("HUAWEIP6S-U06");
    }


    /**
     * 设置状态栏图标为深色和魅族特定的文字风格
     * 可以用来判断是否为Flyme用户
     *
     * @return boolean 成功执行返回true
     */
    private static boolean FlymeSetStatusBarLightMode(boolean isDark, Window window) {
        boolean result = false;
        if (window != null) {
            try {
                WindowManager.LayoutParams lp = window.getAttributes();
                Field darkFlag = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
                Field meizuFlags = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
                darkFlag.setAccessible(true);
                meizuFlags.setAccessible(true);
                int bit = darkFlag.getInt(null);
                int value = meizuFlags.getInt(lp);
                if (isDark) {
                    value |= bit;
                } else {
                    value &= ~bit;
                }
                meizuFlags.setInt(lp, value);
                window.setAttributes(lp);
                result = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 设置状态栏字体图标为深色，需要MIUIV6以上
     *
     * @return boolean 成功执行返回true
     */
    private static boolean MIUISetStatusBarLightMode(boolean isDark, Window window) {
        boolean result = false;
        if (window != null) {
            Class clazz = window.getClass();
            try {
                int darkModeFlag = 0;
                Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
                Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
                darkModeFlag = field.getInt(layoutParams);
                Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
                if (isDark) {
                    extraFlagField.invoke(window, darkModeFlag, darkModeFlag);// 状态栏透明且黑色字体
                } else {
                    extraFlagField.invoke(window, 0, darkModeFlag);// 清除黑色字体
                }
                result = true;
            } catch (Exception e) {
                LogUtils.e(e);
            }
        }
        return result;
    }


    /**
     * @param path
     * @return 单位是M
     */
    public static int getAvailSpace(String path) {
        StatFs statFs = new StatFs(path);

        statFs.getBlockCount();// 获取分区的个数
        int size = statFs.getBlockSize();// 获取分区的大小
        int blocks = statFs.getAvailableBlocks();// 获取可用分区的个数

        long result = blocks * size;
        return (int) (result / 1024 / 1204);
    }


    public static void copyContentToClipboard(String s, Context context) {
        if (TextUtils.isEmpty(s)) {
            return;
        }
        ClipboardManager cm = (ClipboardManager) context
                .getSystemService(Context.CLIPBOARD_SERVICE);
        // 将文本内容放到系统剪贴板里。
        cm.setText(s);
    }
}
