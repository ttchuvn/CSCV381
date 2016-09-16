package bloodglucose.toanchu.uas.arizona.edu.bloodglucose;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;;

import java.util.Date;

public class BloodGlucoseActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return  new BloodGlucoseFragment();
    }
    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_glucose);
    }*/

}
