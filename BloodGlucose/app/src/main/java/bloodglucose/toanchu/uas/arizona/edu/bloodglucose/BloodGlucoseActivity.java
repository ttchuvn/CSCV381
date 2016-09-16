package bloodglucose.toanchu.uas.arizona.edu.bloodglucose;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;

import java.util.UUID;


public class BloodGlucoseActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
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
