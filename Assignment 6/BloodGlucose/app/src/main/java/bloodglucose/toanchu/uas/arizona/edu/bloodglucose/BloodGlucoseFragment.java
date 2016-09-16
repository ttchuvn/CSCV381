package bloodglucose.toanchu.uas.arizona.edu.bloodglucose;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;

//package com.javatechig.alarmservice;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.text.TextUtils;
import android.widget.Toast;
import android.net.ConnectivityManager;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class BloodGlucoseFragment extends Fragment {
    private PendingIntent pendingIntent;
    private static final String ARG_BLOODGLUCOSE_ID = "bloodglucose_id";
    private static final String DIALOG_DATE = "DialogDate";
    private static final int REQUEST_DATE = 0;

    private BloodGlucose mBloodGlucose;
    private Button mClearButton,mDateButton,mRetrieveButton, mStartAlarm, mCancelAlarm, mNotification;
    private EditText mFastingField, mBreakfastField, mLunchField, mDinnerField, mNoteField;
    private TextView mFastingText, mBreakfastText, mLunchText,mDinnerText;
    private boolean mAbnormalCheckBox;
    //private Date mdate;
    public static Bundle mMyAppsBundle = new Bundle();
    private Double doube_Count = 4.0, double_Fasting=0.0, double_Breakfast=0.0,
            double_Lunch=0.0, double_Dinner=0.0, double_Average=0.0;

    /*public static BloodGlucoseFragment newInstance(UUID bloodglucoseId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_BLOODGLUCOSE_ID, bloodglucoseId);
        BloodGlucoseFragment fragment = new BloodGlucoseFragment();
        fragment.setArguments(args);
        return fragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
       // UUID bloodglucoseId = (UUID) getArguments().getSerializable(ARG_BLOODGLUCOSE_ID);
        mBloodGlucose = new BloodGlucose();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_blood_glucose, container, false);
        mStartAlarm = (Button) v.findViewById(R.id.btn_Reminder);
        mCancelAlarm = (Button) v.findViewById(R.id.btn_Cancel);
        mNotification = (Button) v.findViewById(R.id.btn_Notification);
        View.OnClickListener handler = new View.OnClickListener(){
            public void onClick(View v){
                switch (v.getId()){
                    case R.id.btn_Notification:
                        showNotification();
                        break;
                }
            }

        };
        mNotification.setOnClickListener(handler);

        Intent alarmIntent = new Intent(getActivity(), AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(getActivity(), 0, alarmIntent, 0);
        //start the Alarm at 9pm and repeat30 min after that
        mStartAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });

        mCancelAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });


        mClearButton = (Button) v.findViewById(R.id.button_Clear);
        mRetrieveButton = (Button) v.findViewById(R.id.button_Retrieve);
        mDateButton = (Button)v.findViewById(R.id.button_Date);
        //mBloodGlucose = new BloodGlucose();
        //Textview field
        mFastingText = (TextView) v.findViewById(R.id.textView_Fasting);
        mBreakfastText = (TextView) v.findViewById(R.id.textView_Breakfast);
        mLunchText = (TextView) v.findViewById(R.id.textView_Lunch);
        mDinnerText = (TextView) v.findViewById(R.id.textView_Dinner);
        //editText field
        mFastingField = (EditText) v.findViewById(R.id.editText_Fasting);
        //mFastingField.setText(Double.toString(mBloodGlucose.getFasting()));
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
                    //mBloodGlucose.setFasting(Double.parseDouble(s.toString()));
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
        mBreakfastField = (EditText) v.findViewById(R.id.editText_Breakfast);
        //mBreakfastField.setText(Double.toString(mBloodGlucose.getBreakfast()));
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
                    //mBloodGlucose.setBreakfast(Double.parseDouble(s.toString()));

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
        mLunchField = (EditText) v.findViewById(R.id.editText_Lunch);
       // mLunchField.setText(Double.toString(mBloodGlucose.getLunch()));
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
                    //mBloodGlucose.setLunch(Double.parseDouble((s.toString())));
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
        mDinnerField = (EditText) v.findViewById(R.id.editText_Dinner);
        //mDinnerField.setText(Double.toString(mBloodGlucose.getDinner()));
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
                    //mBloodGlucose.setDinner(Double.parseDouble((s.toString())));
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
        mNoteField  = (EditText) v.findViewById(R.id.editText_Note);
        //mNoteField.setText(mBloodGlucose.getNote());
        mNoteField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //mNoteField.setText(s.toString());
                mBloodGlucose.setNote(s.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        //button field
        ////declare Button type with id getCurrentDate in layout
        updateDate();
       mDateButton.setOnClickListener(new View.OnClickListener() {//listener function for bi=utton
            @Override
            public void onClick(View v){//when the button is pressed display new changes of time
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment
                        .newInstance(mBloodGlucose.getDate());
                dialog.setTargetFragment(BloodGlucoseFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
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
                if (mBloodGlucose.getDate() == null){
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
                    BloodGlucoseFragment.mMyAppsBundle.putBoolean("key_Abnormal",mAbnormalCheckBox);
                    BloodGlucoseFragment.mMyAppsBundle.putString("key_Average",double_Average.toString());
                    BloodGlucoseFragment.mMyAppsBundle.putString("key_Date",mBloodGlucose.getDate().toString());
                    BloodGlucoseListFragment fragment = new BloodGlucoseListFragment();
                    getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment);

                        Intent intent = new Intent(getActivity(),BloodGlucoseListActivity.class);
                    startActivity(intent);
                }
            }
        });
        return v;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data
                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mBloodGlucose.setDate(date);
            updateDate();
        }
    }
    private void updateDate(){
        mDateButton.setText(mBloodGlucose.getDate().toString());
    }
    public void start() {
        AlarmManager manager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        int interval = 8000;

        manager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval, pendingIntent);
        Toast.makeText(getActivity(), "Alarm Set", Toast.LENGTH_SHORT).show();
    }

    public void cancel() {
        AlarmManager manager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        manager.cancel(pendingIntent);
        Toast.makeText(getActivity(), "Alarm Canceled", Toast.LENGTH_SHORT).show();
    }

    public void startAt10() {
        AlarmManager manager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        int interval = 1000 * 60 * 20;

        /* Set the alarm to start at 9pm AM */
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 21);
        calendar.set(Calendar.MINUTE, 00);
        //start the notification with the alarm
        if(double_Dinner == 0.0 || double_Fasting == 0.0 || double_Lunch == 0.0 || double_Breakfast == 0.0) {
            showNotification();
        }
        /* Repeating on every 30 minutes interval */
        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                1000 * 60 * 30, pendingIntent);
    }

    public void showNotification(){

        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent intent = new Intent(getActivity(), BloodGlucoseActivity.class);

        PendingIntent pIntent = PendingIntent.getActivity(getActivity(), 0, intent, 0);

        Notification mNotification = new Notification.Builder(getActivity())

                .setContentTitle("Blood Glucose Monitor")

                .setContentText("Please record you glucose level input")

                .setSmallIcon(R.drawable.ic_menu_add)

                .setContentIntent(pIntent)

                .setSound(soundUri)

                .addAction(R.drawable.ic_menu_add, "View", pIntent)

                .addAction(1, "Remind",pIntent)

                .build();

        NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);

        // If you want to hide the notification after it was selected, do the code below
        // myNotification.flags |= Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, mNotification);
    }
    public void cancelNotification(int notificationId){

        if (Context.NOTIFICATION_SERVICE!=null) {
            String ns = Context.NOTIFICATION_SERVICE;
            NotificationManager nMgr = (NotificationManager) getActivity().getApplicationContext().getSystemService(ns);
            nMgr.cancel(notificationId);

        }

    }

}
