package com.example.finance.permissionmanager;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/6/12.
 */

public class ToolPermissions {
    public static final int PERMISSION_REQUEST = 10;

    //当选择了“不再提醒”对话框时候，return true;
    @TargetApi(Build.VERSION_CODES.M)
    public static boolean showPermissionRationale(Activity activity, String permission) {
        return activity.shouldShowRequestPermissionRationale(permission);
    }

    // 判断是否缺少权限
    @TargetApi(Build.VERSION_CODES.M)
    public static boolean checkHasPermission(Activity activity, String permission) {
        return activity.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED;
    }

    // 判断是否缺少权限，并返回缺少的权限
    @TargetApi(Build.VERSION_CODES.M)
    public static String[] checkHasPermissions(Activity activity, String[] permissions) {
        List<String> peedPermissions = new ArrayList<>();
        for (String permission : permissions) {
            if (activity.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                peedPermissions.add(permission);
            }
        }

        return peedPermissions.toArray(new String[0]);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static boolean checkShowPermissionDialog(Activity activity, String permission) {
        return activity.shouldShowRequestPermissionRationale(permission);
    }

    //请求单个权限
    @TargetApi(Build.VERSION_CODES.M)
    public static void requestPermission(Activity activity, String permission) {
        activity.requestPermissions(new String[] {permission}, PERMISSION_REQUEST);
    }

    //请求多个权限
    @TargetApi(Build.VERSION_CODES.M)
    public static void requestPermission(Activity activity, String[] permission) {
        activity.requestPermissions(permission, PERMISSION_REQUEST);
    }
}
