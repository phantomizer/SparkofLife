package enghack2017.ca.sparkoflife;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.content.ContextCompat;

/**
 * Created by mitchell on 2/4/17.
 */

public class EmergencyCallUtils {

    private static final int REQUEST_CALL_PERMISSION = 3;
    private static final String EMERGENCY_PHONE_NUMBER = "6475305426";

    public static void openEmergencyPrompt(Activity activity) {
        new AlertDialog.Builder(activity)
                .setTitle("Heartbeat not Detected")
                .setMessage("Your heartbeat is not detected, please confirm that you are okay.")
                .setPositiveButton("Yes", (dialog, which) ->
                        dialog.dismiss())
                .setNegativeButton("No", (dialog, which) -> {
                    dialog.dismiss();
                    makeEmergencyCall(activity);
                }).show();
    }

    private static void makeEmergencyCall(Activity activity) {
        // Here, thisActivity is the current activity

        if (android.os.Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            activity.requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PERMISSION);
        }

        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + EMERGENCY_PHONE_NUMBER.trim()));
        activity.startActivity(intent);
    }
}
