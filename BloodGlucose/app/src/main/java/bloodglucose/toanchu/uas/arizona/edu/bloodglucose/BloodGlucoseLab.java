package bloodglucose.toanchu.uas.arizona.edu.bloodglucose;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
/**
 * Created by ttchuvn on 6/27/16.
 */
public class BloodGlucoseLab {
    private static BloodGlucoseLab sBloodGlucoseLab;
    private ArrayList<BloodGlucose> mBloodGlucose;
    public static BloodGlucoseLab get(Context context){
        if(sBloodGlucoseLab == null){
            sBloodGlucoseLab = new BloodGlucoseLab(context);
        }
        return sBloodGlucoseLab;
    }
    private BloodGlucoseLab(Context context){
        double average;
        boolean abnormal;
        String date = BloodGlucoseFragment.mMyAppsBundle.getString("key_Date");
        average = Double.parseDouble(BloodGlucoseFragment.mMyAppsBundle.getString("key_Average"));
        abnormal= BloodGlucoseFragment.mMyAppsBundle.getBoolean("key_Abnormal");
        mBloodGlucose = new ArrayList<>();
            BloodGlucose bloodglucose = new BloodGlucose();
            bloodglucose.setDate(date);
            bloodglucose.setAverage(average);
            bloodglucose.setAbnormal(abnormal);
            mBloodGlucose.add(bloodglucose);
    }
    public List<BloodGlucose> getBloodGlucose(){
        return mBloodGlucose;
    }
    public BloodGlucose getBloodGlucose(UUID id) {
        for (BloodGlucose bloodglucose : mBloodGlucose) {
            if (bloodglucose.getId().equals(id)) {
                return bloodglucose;
            }
        }
        return null;
    }
}

