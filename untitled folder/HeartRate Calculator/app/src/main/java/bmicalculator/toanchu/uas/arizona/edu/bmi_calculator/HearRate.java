//Toan Chu
//Assignment 3 - Hear Rate Calculator
//06/16/2016

package bmicalculator.toanchu.uas.arizona.edu.bmi_calculator;

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

public class HearRate extends AppCompatActivity {
    //declare variables types
    private Button btn_Clear1;
    private Button btn_Calculate;
    private TextView result;
    private EditText field_age;
    private Button btn_BMI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hear_rate);
        btn_BMI = (Button) findViewById(R.id.btn_BMI);
        //go back to BMI Calculator by command finish()
        btn_BMI.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Intent intent = new Intent(BMI.this, HearRate.class);
                //startActivity(intent);
                Toast.makeText(HearRate.this, "you clicked on BMI Calculator!",Toast.LENGTH_LONG).show();
                finish();
            }
        });
        btn_Calculate =(Button) findViewById(R.id.btn_HeartRate);
        btn_Clear1 = (Button) findViewById(R.id.btn_Clr);
        field_age = (EditText) findViewById(R.id.field_Age);
        result = (TextView) findViewById(R.id.txt_Result) ;
        //Clear Button
        btn_Clear1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                field_age.setText("");
                result.setText("");
            }
        });
        //Heart Rate Calculate button
        btn_Calculate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DecimalFormat df = new DecimalFormat("#.00");
                //result.setText("testing");
                int age=0;
                age = Integer.parseInt(field_age.getText().toString());
                if(age <= 0 || age >= 120){
                    result.setTextColor(Color.parseColor("#e15258"));
                    result.setText("Invalid age");
                }
                else if(age < 18){
                    result.setTextColor(Color.parseColor("#e15258"));
                    result.setText("Persons under the age of 18 should consult a physician for this information");
                } else if(age >72) {
                    result.setTextColor(Color.parseColor("#e15258"));
                    result.setText("Persons over the age of 72 should consult a physician for this information");
                }else{
                    int max=0;
                    double max50=0, max85=0;
                    max = 220 - age;//formular for maximum heart rate
                    max50 = max/2;
                    max85 = max * .85;
                    result.setTextColor(Color.parseColor("#f9845b"));
                    result.setText("Your target heart rate is " + df.format(max50) + " - " + df.format(max85) + " beats per minute.");
                }
            }
            });
    }
}
