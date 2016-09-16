//Toan Chu
//CSCV381_Assignment1_HelloDate
//06/10/2016
package hellodate.toanchu.uas.arizona.edu.hellodate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;//import packet for view
import android.widget.Button;//import packet for Button

import java.util.Date;//import java packet for Date

public class HelloDate extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello_date);
        Date date = new Date();//declare date as Date tupe
        Button getCurrentDate = (Button)findViewById(R.id.get_current_date);//declare Button type with id getCurrentDate in layout
        getCurrentDate.setText(date.toString());//convert date to string then display when the app first launch
        getCurrentDate.setOnClickListener(new View.OnClickListener() {//listener function for bi=utton
            @Override
            public void onClick(View v){//when the button is pressed display new changes of time
                Date date1 = new Date();
                Button getCurrentDate1 = (Button)findViewById(R.id.get_current_date);
                getCurrentDate1.setText(date1.toString());
            }
        });
    }
}
