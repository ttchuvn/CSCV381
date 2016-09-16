package bloodglucose.toanchu.uas.arizona.edu.bloodglucose;

/**
 * Created by ttchuvn on 6/27/16.
 */
import java.util.Date;
import java.util.UUID;

public class BloodGlucose {
    private UUID mId;
    private String mNote;
    private String mDate1;
    private Date mDate;
    private double mFasting, mBreakfast, mLunch, mDinner;
    private boolean mAbnormal;
    private double mAverage;
    public BloodGlucose(){
        mId = UUID.randomUUID();
        mDate = new Date();
    }
    //getters
    public UUID getId() {
        return mId;
    }
    public Date getDate() {
        return mDate;

    }
    public String getNote(){
        return mNote;
    }
    public String getDate1(){
        return mDate1;
    }
    public double getFasting(){
        return mFasting;
    }
    public double getBreakfast(){
        return mBreakfast;
    }
    public double getLunch(){
        return mLunch;
    }
    public double getDinner(){
        return mDinner;
    }
    public boolean isAbnormal(){
        return mAbnormal;
    }
    public double getAverage(){
        return mAverage;
    }
    //setters
    public void setAverage(double average){
        mAverage = average;
    }
    public void setNote(String note){
        mNote = note;
    }
    public void setFasting(double fasting){
        mFasting = fasting;
    }
    public void setBreakfast(double breakfast){
        mBreakfast = breakfast;
    }
    public void setLunch(double lunch){
        mLunch = lunch;
    }
    public void setDinner(double dinner){
        mDinner = dinner;
    }
    public void setDate(Date date){
        mDate = date;
    }
    public void setDate1(String date1){
        mDate1 = date1;
    }
    public void setAbnormal(boolean abnormal){
        mAbnormal = abnormal;
    }
}
