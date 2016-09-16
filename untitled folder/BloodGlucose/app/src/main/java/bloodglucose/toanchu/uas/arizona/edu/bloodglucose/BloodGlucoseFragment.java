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
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import java.util.Date;

public class BloodGlucoseFragment extends Fragment {
    private BloodGlucose mBloodGlucose;
    private Button btn_Clear,btn_Date,btn_Retrieve;
    private EditText field_Fasting, field_Breakfast, field_Lunch, field_Dinner, field_Note;
    private Date date;
    public static Bundle mMyAppsBundle = new Bundle();
    private String str_Note;
    private Double double_Fasting, double_Breakfast, double_Lunch, double_Dinner, double_Date, double_Average;
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
        btn_Retrieve = (Button) v.findViewById(R.id.button_Retrieve);
        btn_Clear = (Button) v.findViewById(R.id.button_Clear);
        field_Fasting = (EditText) v.findViewById(R.id.editText_Fasting);
        field_Breakfast = (EditText) v.findViewById(R.id.editText_Breakfast);
        field_Lunch = (EditText) v.findViewById(R.id.editText_Lunch);
        field_Dinner = (EditText) v.findViewById(R.id.editText_Dinner);
        field_Note  = (EditText) v.findViewById(R.id.editText_Note);
        field_Note = (EditText)v.findViewById(R.id.editText_Note);

        field_Note.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                str_Note = s.toString();
                //mBloodGlucose.setNote(s.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        //Date date = new Date();//declare date as Date tupe
        btn_Date = (Button)v.findViewById(R.id.button_Date);//declare Button type with id getCurrentDate in layout
        //btn_Date.setText(date.toString());//convert date to string then display when the app first launch
        btn_Date.setOnClickListener(new View.OnClickListener() {//listener function for bi=utton
            @Override
            public void onClick(View v){//when the button is pressed display new changes of time
                date = new Date();
                //btn_Date = (Button)findViewById(R.id.btn_Date);
                btn_Date.setText(date.toString());
            }
        });
        btn_Clear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                field_Fasting.setText("");
                field_Breakfast.setText("");
                field_Lunch.setText("");
                field_Dinner.setText("");
                field_Note.setText("");
                btn_Date.setText("<<PICK THE DATE>>");
            }
        });
        btn_Retrieve.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                double_Fasting = Double.parseDouble(field_Fasting.getText().toString());
                double_Breakfast = Double.parseDouble(field_Breakfast.getText().toString());
                double_Lunch = Double.parseDouble(field_Lunch.getText().toString());
                double_Dinner = Double.parseDouble(field_Dinner.getText().toString());
                double_Average = (double_Breakfast+double_Lunch+double_Dinner)/3.0;
                Intent intent = new Intent(getActivity(),BloodGlucoseListActivity.class);
                //BloodGlucoseFragment.mMyAppsBundle.putString("key_Note",str_Note);
                BloodGlucoseFragment.mMyAppsBundle.putString("key_Fasting",double_Fasting.toString());
                BloodGlucoseFragment.mMyAppsBundle.putString("key_Average",double_Average.toString());
                BloodGlucoseFragment.mMyAppsBundle.putString("key_Date",date.toString());
                //mBloodGlucose.setAverage(100);
                startActivity(intent);
            }
        });
        return v;
    }

}
