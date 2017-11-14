package com.example.finance.permissionmanager;

import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

/**
 * Description:
 * Creator: Yanghj
 * Email: yanghj11@163.com
 * Date: 2017/11/13
 */

public class BaseActivity extends FragmentActivity {


    /**
     *
     * @param permission
     * @return  true 代表获得权限，false代表没有非允许
     */
    public boolean checkPermission(String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ToolPermissions.checkHasPermission(this, permission)) {
            ToolPermissions.requestPermission(this, permission);
            return false;
        }

        permissionGranted(permission);
        return true;
    }

    /**
     *
     * @param permissions   多个权限
     * @return true 代表获得权限，false代表没有非允许
     */
    public boolean checkPermissions(String[] permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            String[] needPermission = ToolPermissions.checkHasPermissions(this, permissions);
            //对没有开启的权限再提醒
            if (0 < needPermission.length) {
                ToolPermissions.requestPermission(this, needPermission);
                return false;
            }
        }

        permissionGranted(permissions[0]);
        return true;
    }


    //
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (ToolPermissions.PERMISSION_REQUEST != requestCode || 0 == grantResults.length) {
            return;
        }

        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            permissionGranted(permissions[0]);
        } else if (!ToolPermissions.showPermissionRationale(this, permissions[0])){
            permissionDenied(permissions[0]);
            Toast.makeText(this, "开通权限请通过 设置->权限管理 打开", Toast.LENGTH_LONG).show();
        } else {
            permissionDenied(permissions[0]);
        }
    }

    protected void permissionGranted(String permission) {

    }


    protected void permissionDenied(String permission) {

    }
}
