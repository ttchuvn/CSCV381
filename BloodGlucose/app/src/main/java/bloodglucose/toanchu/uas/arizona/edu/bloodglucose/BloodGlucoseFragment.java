package bloodglucose.toanchu.uas.arizona.edu.bloodglucose;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.text.TextUtils;

import java.util.Date;
import java.util.UUID;

public class BloodGlucoseFragment extends Fragment {
    private BloodGlucose mBloodGlucose;
    private Button mClearButton,mDateButton,mRetrieveButton;
    private EditText mFastingField, mBreakfastField, mLunchField, mDinnerField, mNoteField;
    private TextView mFastingText, mBreakfastText, mLunchText,mDinnerText;
    private boolean mAbnormalCheckBox;
    private Date mdate;
    public static Bundle mMyAppsBundle = new Bundle();
    private Double doube_Count = 4.0, double_Fasting=0.0, double_Breakfast=0.0,
            double_Lunch=0.0, double_Dinner=0.0, double_Average=0.0;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mBloodGlucose = new BloodGlucose();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_blood_glucose, container, false);
        //mBloodGlucose = new BloodGlucose();
        //button field
        mRetrieveButton = (Button) v.findViewById(R.id.button_Retrieve);
        mClearButton = (Button) v.findViewById(R.id.button_Clear);
        //editText field
        mFastingField = (EditText) v.findViewById(R.id.editText_Fasting);
        mBreakfastField = (EditText) v.findViewById(R.id.editText_Breakfast);
        mLunchField = (EditText) v.findViewById(R.id.editText_Lunch);
        mDinnerField = (EditText) v.findViewById(R.id.editText_Dinner);
        mNoteField  = (EditText) v.findViewById(R.id.editText_Note);
        //Textview field
        mFastingText = (TextView) v.findViewById(R.id.textView_Fasting);
        mBreakfastText = (TextView) v.findViewById(R.id.textView_Breakfast);
        mLunchText = (TextView) v.findViewById(R.id.textView_Lunch);
        mDinnerText = (TextView) v.findViewById(R.id.textView_Dinner);
        mFastingField.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int after) {
                if (TextUtils.isEmpty(s.toString())){
                    double_Fasting = 0.0;
                }
                else{
                    double_Fasting = Double.parseDouble(s.toString());
                }
                if(double_Fasting == 0.0){
                    mFastingText.setText("[No Data]");
                }
                else if(double_Fasting >0.0 && double_Fasting<70){
                    mFastingText.setText("[Hypoglycemic:(( ]");
                    mAbnormalCheckBox = true;
                }
                else if(double_Fasting >= 70 &&double_Fasting <= 99 ){
                    mFastingText.setText("[Normal:) ]");
                }
                else {
                    mFastingText.setText("[Abormal:( ]");
                    mAbnormalCheckBox = true;
                }
                //mBloodGlucose.setFasting(Double.parseDouble((s.toString())));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mBreakfastField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int after) {
                if (TextUtils.isEmpty(s.toString())){
                    double_Breakfast = 0.0;
                }
                else{
                    double_Breakfast = Double.parseDouble(s.toString());
                }
                if(double_Breakfast == 0.0){
                    mBreakfastText.setText("[No Data]");
                }
                else if(double_Breakfast > 0.0 && double_Breakfast<70){
                    mBreakfastText.setText("[Hypoglycemic:(( ]");
                    mAbnormalCheckBox = true;
                }
                else if(double_Breakfast > 140 ){
                    mBreakfastText.setText("[Abnormal:( ]");
                    mAbnormalCheckBox = true;
                }
                else {
                    mBreakfastText.setText("[Normal:) ]");
                }
                //mBloodGlucose.setBreakfast(Double.parseDouble((s.toString())));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        mLunchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int after) {
                if (TextUtils.isEmpty(s.toString())){
                    double_Lunch = 0.0;
                }
                else{
                    double_Lunch = Double.parseDouble(s.toString());
                }
                if(double_Lunch == 0.0){
                    mLunchText.setText("[No Data]");
                }
                else if(double_Lunch > 0.0 && double_Lunch < 70){
                    mLunchText.setText("[Hypoglycemic:(( ]");
                    mAbnormalCheckBox = true;
                }
                else if(double_Lunch > 140 ){
                    mLunchText.setText("[Abnormal:( ]");
                    mAbnormalCheckBox = true;
                }
                else {
                    mLunchText.setText("[Normal:) ]");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        mDinnerField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int after) {
                if (TextUtils.isEmpty(s.toString())){
                    double_Dinner = 0.0;
                }
                else{
                    double_Dinner = Double.parseDouble(s.toString());
                }
                if(double_Dinner == 0.0){
                    mDinnerText.setText("[No Data]");
                }
                else if(double_Dinner > 0.0 && double_Dinner < 70){
                    mDinnerText.setText("[Hypoglycemic:(( ]");
                    //checkBoxAbnormal = true;
                }
                else if(double_Dinner > 140 ){
                    mDinnerText.setText("[Abnormal:( ]");
                    mAbnormalCheckBox = true;
                }
                else {
                    mDinnerText.setText("[Normal:) ]");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }

        });

        mNoteField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //mNoteField.setText(s.toString());
                //mBloodGlucose.setNote(s.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        //Date date = new Date();//declare date as Date tupe
        mDateButton = (Button)v.findViewById(R.id.button_Date);//declare Button type with id getCurrentDate in layout
        //btn_Date.setText(date.toString());//convert date to string then display when the app first launch
        mDateButton.setOnClickListener(new View.OnClickListener() {//listener function for bi=utton
            @Override
            public void onClick(View v){//when the button is pressed display new changes of time
                mdate = new Date();
                //btn_Date = (Button)findViewById(R.id.btn_Date);
                mDateButton.setText(mdate.toString());
            }
        });
        mClearButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mFastingField.setText("");
                mBreakfastField.setText("");
                mLunchField.setText("");
                mDinnerField.setText("");
                mNoteField.setText("");

                mFastingText.setText("");
                mBreakfastText.setText("");
                mLunchText.setText("");
                mDinnerText.setText("");

                mDateButton.setText("<<PICK THE DATE>>");
            }
        });
        mRetrieveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (mdate == null){
                    mDateButton.setText("<<PICK THE DATE>>");
                    mDateButton.setError("");
                    return;
                }
                else    {
                    if(double_Fasting == 0.0){
                        doube_Count --;
                    }
                    if(double_Breakfast == 0.0){
                        doube_Count --;
                    }
                    if(double_Lunch == 0.0){
                        doube_Count --;
                    }
                    if(double_Dinner == 0.0){
                        doube_Count --;
                    }
                    double_Average = (double_Fasting + double_Breakfast + double_Lunch + double_Dinner)/doube_Count;
                    Intent intent = new Intent(getActivity(),BloodGlucoseListActivity.class);
                    BloodGlucoseFragment.mMyAppsBundle.putBoolean("key_Abnormal",mAbnormalCheckBox);
                    BloodGlucoseFragment.mMyAppsBundle.putString("key_Average",double_Average.toString());
                    BloodGlucoseFragment.mMyAppsBundle.putString("key_Date",mdate.toString());

                    startActivity(intent);
                }



            }
        });
        return v;
    }

}
