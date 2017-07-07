package sms.sft.com.appbase.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;

import sms.sft.com.appbase.R;


/**
 * Created by ABadretdinov
 * 13.10.2015
 * 15:47
 */
public class Utils {
    public static final int PHONE = 0;
    public static final int TABLET_PORTRAIT = 1;
    public static final int TABLET_LANDSCAPE = 2;

    public static int dpSize(Context context, int px) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px * scale + 0.5f);
    }

    public static String getApplicationName(Context context) {
        int stringId = context.getApplicationInfo().labelRes;
        return context.getString(stringId);
    }

    public static String getApplicationPackage(Context context) {
        return context.getApplicationContext().getPackageName();
    }



    public static boolean IsPackageInstalled(Context context, String PackageUri) {
        final PackageManager pm = context.getPackageManager();
        boolean IsPackageInstalled;
        try {
            pm.getPackageInfo(PackageUri, PackageManager.GET_ACTIVITIES);
            IsPackageInstalled = true;
        } catch (PackageManager.NameNotFoundException e) {
            IsPackageInstalled = false;
        }
        return IsPackageInstalled;
    }
}
