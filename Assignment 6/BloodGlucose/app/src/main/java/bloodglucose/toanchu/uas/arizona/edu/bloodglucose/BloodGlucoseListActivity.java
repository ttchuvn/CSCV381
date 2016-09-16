package bloodglucose.toanchu.uas.arizona.edu.bloodglucose;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import java.util.UUID;

public class BloodGlucoseListActivity extends SingleFragmentActivity {


    @Override
    protected Fragment createFragment(){
        //finish();
        return  new BloodGlucoseListFragment();
    }

}
