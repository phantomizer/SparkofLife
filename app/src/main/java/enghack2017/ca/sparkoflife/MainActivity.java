package enghack2017.ca.sparkoflife;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.prompt_button)
    public void onPromptClicked() {
        openEmergencyPrompt(this);
    }

    public void openEmergencyPrompt(Activity activity) {
        new AlertDialog.Builder(activity)
                .setTitle("Heartbeat not Detected")
                .setPositiveButton("Yes", (dialog, which) ->
                dialog.dismiss())
                .setNegativeButton("No", (dialog, which) -> {
                    dialog.dismiss();
                    makeEmergencyCall();
                }).show();
    }

    public void makeEmergencyCall() {
        // Here, thisActivity is the current activity

        if (android.os.Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PERMISSION);
        }

        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:6475305426"));
        startActivity(intent);
    }

    public static String TAG = "MAINACTIVITY";
    public static final int REQUEST_CALL_PERMISSION = 3;

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CALL_PERMISSION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG,"Premission granted");
                } else {
                    Log.d(TAG,"Premission denied");
                }
                break;
        }
    }

}
