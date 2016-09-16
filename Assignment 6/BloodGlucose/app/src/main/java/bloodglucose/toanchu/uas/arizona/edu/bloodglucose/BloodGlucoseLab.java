package bloodglucose.toanchu.uas.arizona.edu.bloodglucose;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLClientInfoException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
/**
 * Created by ttchuvn on 6/27/16.
 */
public class BloodGlucoseLab {
    private static BloodGlucoseLab sBloodGlucoseLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;
    private ArrayList<BloodGlucose> mBloodGlucose;
    public static BloodGlucoseLab get(Context context){
        if(sBloodGlucoseLab == null){
            sBloodGlucoseLab = new BloodGlucoseLab(context);
        }
        return sBloodGlucoseLab;
    }
    private BloodGlucoseLab(Context context){
        mContext = context.getApplicationContext();
        //mDatabase = new BloodGlucoseBaseHelper(context)
              //  .getWritableDatabase();
        double average;
        boolean abnormal;
        String date1 = BloodGlucoseFragment.mMyAppsBundle.getString("key_Date");
        average = Double.parseDouble(BloodGlucoseFragment.mMyAppsBundle.getString("key_Average"));
        abnormal= BloodGlucoseFragment.mMyAppsBundle.getBoolean("key_Abnormal");
        mBloodGlucose = new ArrayList<>();
            BloodGlucose bloodglucose = new BloodGlucose();
            bloodglucose.setDate1(date1);
            bloodglucose.setAverage(average);
            bloodglucose.setAbnormal(abnormal);
            mBloodGlucose.add(bloodglucose);
    }
    /*private static ContentValues getContentsValues(BloodGlucose bloodglucose){
        ContentValues values = new ContentValues();
        values.put(BloodGlucoseTable.Cols.UUID, bloodglucose.getId().toString());
        values.put(BloodGlucoseTable.Cols.AVERAGE, Double.toString(bloodglucose.getAverage()));
        values.put(BloodGlucoseTable.Cols.DATE, bloodglucose.getDate().toString());
        values.put(BloodGlucoseTable.Cols.ABNORMAL, bloodglucose.isAbnormal() ? 1 : 0);
        return values;
    }
    public void addBloodGlucose(BloodGlucose b){
        ContentValues values = getContentsValues(c);
        mDatabase.insert(BloodGlucoseTable.NAME, null, values);
    }
    public void updateBloodGlucose(BloodGlucose bloodglucose){
        String uuidString = bloodglucose.getId().toString();
        ContentValues values = getContentsValues(bloodglucose);
        mDatabase.update(BloodGlucoseTable.NAME, values, BloodGlucoseTable.Cols.UUID + " = ?",
                new String[]{uuidString});
    }
    private BloodGlucoseCursoWrapper querryBloodGlucose(String whereClause, String [] whereArgs){
        Cursor cursor = mDatabase.query(
                BlooGlucoseTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new BloodGlucoseCursoWrapper(cursor);
    }*/
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

