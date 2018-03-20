package com.example.rajiv.permission;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends BaseActivity {

    private Activity mContext = MainActivity.this;
    private int PER_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // askPermission();

        if (checkWhetherAllPermissionsPresentForPhotoTagging()) {

        } else {
            requestRunTimePermissions(this, permissionsNeededForPhotoTagging, MY_PHOTO_TAGGING_PERMISSIONS);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_PHOTO_TAGGING_PERMISSIONS) {

            if (verifyPermissions(grantResults)) {

                Toast.makeText(mContext, "All Granted", Toast.LENGTH_SHORT).show();

            } else {

                Toast.makeText(mContext, "All not granted", Toast.LENGTH_SHORT).show();

            }
        }
    }

    private void askPermission() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {


        } else {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_CONTACTS)) {

                Snackbar.make(findViewById(android.R.id.content), "Permission Needed", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Ask", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                ActivityCompat.requestPermissions(mContext, new String[]{Manifest.permission.READ_CONTACTS}, PER_CODE);

                            }
                        }).show();

            } else {

                ActivityCompat.requestPermissions(mContext, new String[]{Manifest.permission.READ_CONTACTS}, PER_CODE);

            }

        }

    }


}
