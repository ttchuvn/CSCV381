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
        double fasting, average;
        String date = BloodGlucoseFragment.mMyAppsBundle.getString("key_Date");
        average = Double.parseDouble(BloodGlucoseFragment.mMyAppsBundle.getString("key_Average"));
        fasting = Double.parseDouble(BloodGlucoseFragment.mMyAppsBundle.getString("key_Fasting"));
        mBloodGlucose = new ArrayList<>();
        for(int i=0;i<10;i++){
            BloodGlucose bloodglucose = new BloodGlucose();
            bloodglucose.setDate(date);
            bloodglucose.setAverage(average);
            if(fasting < 70){
                bloodglucose.setAbnormal(true);
            }
            else {
                bloodglucose.setAbnormal(false);
            }
            mBloodGlucose.add(bloodglucose);
        }
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

