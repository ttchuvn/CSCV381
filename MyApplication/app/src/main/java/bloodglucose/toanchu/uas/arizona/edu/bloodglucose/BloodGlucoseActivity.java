package bloodglucose.toanchu.uas.arizona.edu.bloodglucose;


import android.support.v4.app.Fragment;

public class BloodGlucoseActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new BloodGlucoseFragment();
    }
}
