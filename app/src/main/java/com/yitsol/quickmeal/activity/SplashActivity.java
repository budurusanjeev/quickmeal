package com.yitsol.quickmeal.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import com.yitsol.quickmeal.R;
import com.yitsol.quickmeal.utilities.AppConstants;
import com.yitsol.quickmeal.utilities.CartDatabase;
import com.yitsol.quickmeal.utilities.NetworkUtils;
import com.yitsol.quickmeal.utilities.PreferenceUtils;

import java.util.Timer;
import java.util.TimerTask;


public class SplashActivity extends AppCompatActivity {

    Timer timer;
    Context mcontext;
    AlertDialog alertDialog;
    PreferenceUtils preferenceUtils;
    Boolean loginstatus;
    private LayoutInflater minflater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        mcontext = SplashActivity.this;
        minflater = this.getLayoutInflater();
        CartDatabase.init(mcontext);
        timer = new Timer();
        preferenceUtils = new PreferenceUtils(mcontext);

        loginstatus = preferenceUtils.isLoggedIn();

    }

    protected void onPause() {
        super.onPause();

        if (alertDialog != null) {
            alertDialog.dismiss();
        }

        System.gc();
    }


    protected void onResume() {
        super.onResume();
        if (NetworkUtils.isNetworkConnectionAvailable(mcontext)) {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (loginstatus) {
                        CartDatabase.clearDatabase();
                        Intent dashbordactivity = new Intent(mcontext, Dashboard.class);
                        startActivity(dashbordactivity);
                        finish();
                    } else {
                        Intent loginActivity = new Intent(mcontext, LoginActivity.class);
                        startActivity(loginActivity);
                        finish();
                    }
                }
            }, 1000);
        } else {
            // getCustomToast(AppConstant.CHECK_NETWORK_CONN);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    showAlertDialog(mcontext, "INTERNET CONNECTION", AppConstants.CHECK_NETWORK_CONN, false);
                }
            });

        }


    }

    private void showAlertDialog(Context context, String title, String message,
                                 Boolean status) {
        alertDialog = new AlertDialog.Builder(context).create();

        // Setting Dialog Title
        alertDialog.setTitle(title);

        // Setting Dialog Message
        alertDialog.setMessage(message);

        // disable touch outside dialog.
        alertDialog.setCanceledOnTouchOutside(false);

        if (status != null)
            // Setting alert dialog icon
            alertDialog.setIcon((status) ? R.mipmap.ic_launcher : R.mipmap.ic_launcher);

        // Setting OK Button
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Setting", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                open_device_setting_screen();

            }
        });

        // Showing Alert Message
        alertDialog.show();
    }

    private void open_device_setting_screen() {

        startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


}