//Toan Chu
//Assignment 3 - Hear Rate Calculator
//06/16/2016
package bmicalculator.toanchu.uas.arizona.edu.bmi_calculator;
//import libraries
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class BMI extends AppCompatActivity {

    Button btn_HeartRate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        btn_HeartRate = (Button) findViewById(R.id.btn_HeartRate);
        btn_HeartRate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(BMI.this, HearRate.class);
                Toast.makeText(BMI.this, "you clicked on Heart Rate Calculator!",Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });
        //delacre button, edit text fileds types for button and display text fileds
        Button button_calc =(Button) findViewById(R.id.btn_BMI);
        Button button_clear = (Button) findViewById(R.id.btn_Clear1);
        final EditText field_weight = (EditText) findViewById(R.id.field_Weight);
        final EditText field_height = (EditText) findViewById(R.id.field_Height);
        final TextView view_result = (TextView) findViewById(R.id.txt_Result);
        final TextView view_msg = (TextView) findViewById(R.id.txt_MSG);
        //action when user click on Calculate BMI
        button_calc.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            //weight, height and bmi as double type
            double weight;
            double height;
            double bmi;
            String msg="";
            //condition if the user miss the input for field height and weight
            if(field_weight.getText().toString().equals("")){
                view_msg.setTextColor(Color.parseColor("#e15258"));
                view_msg.setText("Missing weight");
            }else if(field_height.getText().toString().equals("")) {
                view_msg.setTextColor(Color.parseColor("#3079ab"));
                view_msg.setText("Missing height");
            }
            else{
                //format type for decimal up to 2 numbers after the decimal
            DecimalFormat df = new DecimalFormat("#.00");
            weight = Double.parseDouble(field_weight.getText().toString());
            height = Double.parseDouble((field_height.getText().toString()));
            bmi = (weight * 703) / (height * height);
            // bmi = weight/bmi;
            view_result.setText("BMI is " + df.format(bmi));
                //conditions for bmi
            if (bmi < 18.5) {
                view_msg.setTextColor(Color.parseColor("#f9845b"));//set color for text ouput
                msg = "Underweight :(";
            } else if (bmi >= 18.5 && bmi <= 24.9) {
                view_msg.setTextColor(Color.parseColor("#51b46d"));
                msg = "Normal :)";
            } else if (bmi > 24.9 && bmi < 30) {
                view_msg.setTextColor(Color.parseColor("#f092b0"));
                msg = "Overweight :((";
            } else if (bmi >= 30) {
                view_msg.setTextColor(Color.parseColor("#7d669e"));
                msg = "Obese :(((";
            }
            view_msg.setText(msg);
        }}
        });
        //clear button: clear all the text in the fileds or reset button
        button_clear.setOnClickListener(new View.OnClickListener(){
            //@Override;
            public void onClick(View v){
                field_height.setText("");
                field_weight.setText("");
                view_msg.setText("");
                view_result.setText("");
            }
        }
        );
    }
}
