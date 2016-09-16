package bloodglucose.toanchu.uas.arizona.edu.bloodglucose;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.UUID;

public class BloodGlucoseListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return  new BloodGlucoseListFragment();
    }
}
