package bloodglucose.toanchu.uas.arizona.edu.bloodglucose;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AlertDialog;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import java.util.UUID;


public class BloodGlucoseActivity extends SingleFragmentActivity {
    SharedPreferences prefs = null;
    static final String BROADCAST_ACTIVITY_CLOSE = "bloodglucose.toanchu.uas.arizona.edu.bloodglucose.ACTIVITY_CLOSE_BROADCAST";
    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(BROADCAST_ACTIVITY_CLOSE)) {
                //terminateCleanly();
                finish();
            }
        }
    };

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
       // sendNotification();
        // UUID bloodglucoseId = (UUID) getArguments().getSerializable(ARG_BLOODGLUCOSE_ID);
        //mBloodGlucose = new BloodGlucose();
        prefs = getSharedPreferences("bloodglucose.toanchu.uas.arizona.edu.bloodglucose", MODE_PRIVATE);
        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BROADCAST_ACTIVITY_CLOSE);
        broadcastManager.registerReceiver(broadcastReceiver, intentFilter);
    }
    ///check if first time running the app and update version features
    @Override
    protected void onResume(){
        super.onResume();

        if (prefs.getBoolean("firstrun",true)) {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

            // set title
            alertDialogBuilder.setTitle("Blood Glucose Monitor version 3");

            // set dialog message
            alertDialogBuilder
                    .setMessage("Features: Date Picker and Add Toobar, Wake up daily at 9pm, Notification")
                    .setCancelable(false)
                    .setPositiveButton("Agree",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            // if this button is clicked, close the dialog and running current activity
                            dialog.cancel();
                        }
                    })
                    .setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            // if this button is clicked, just close the app
                            BloodGlucoseActivity.this.finish();
                        }
                    });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();

            prefs.edit().putBoolean("firstrun", false).commit();
        }
    }

    @Override
    protected Fragment createFragment(){
        //finish();
        return  new BloodGlucoseFragment();
    }
    /*private static final String EXTRA_BLOODGLUCOSE_ID =
            "bloodglucose.toanchu.uas.arizona.edu.bloodglucose.bloodglucose_id";
    public static Intent newIntent(Context packageContext, UUID bloodglucoseId){
        Intent intent = new Intent(packageContext, BloodGlucoseActivity.class);
        intent.putExtra(EXTRA_BLOODGLUCOSE_ID, bloodglucoseId);
        return intent;
    }
    @Override
    protected Fragment createFragment(){
        UUID bloodglucoseId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_BLOODGLUCOSE_ID);
        return BloodGlucoseFragment.newInstance(bloodglucoseId);
    }*/

}
