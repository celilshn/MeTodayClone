package com.cengcelil.metodayclone;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;

public class Utils {
    public static final String READ_PERMISSION = Manifest.permission.READ_EXTERNAL_STORAGE;
    public static void showDialogPermission(final Activity activity) {
        new AlertDialog.Builder(activity)
                .setTitle(R.string.dialog_permission_title)
                .setMessage(R.string.dialog_permission_message)
                .setIcon(R.drawable.ic_settings_black_24dp)
                .setCancelable(false)
                .setNegativeButton(R.string.exit_app, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        activity.finish();
                    }
                })
                .setPositiveButton(R.string.allow, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ActivityCompat.requestPermissions(activity, new String[]{READ_PERMISSION}, 1);
                    }
                }).show();
    }

}
