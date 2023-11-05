package com.atharva.thermoconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    TextView t1,t2,t3,t4,t5;
    EditText ed1;
    DecimalFormat decimalFormat = new DecimalFormat("#.##");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1=findViewById(R.id.title);
        t2=findViewById(R.id.inputtxt);
        t3=findViewById(R.id.outputtxt);
        t4=findViewById(R.id.ans1);
        t5=findViewById(R.id.ans2);
        ed1=findViewById(R.id.tempinp);


        ed1.setOnEditorActionListener(new EditText.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || event.getAction() == KeyEvent.ACTION_DOWN) {
                    // Hide the keyboard
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(ed1.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });

        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        ed1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // This method is called to notify you that somewhere within `charSequence`, the characters `i` to `i + i1 - 1` are about to be replaced with new text with a length of `i2`
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // This method is called to notify you that somewhere within `charSequence`, the characters `i` to `i + i1 - 1` have just been replaced with new text with a length of `i2`
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // This method is called when the text in the `EditText` has been changed
                t4.setText("");
                t5.setText("");
                radioGroup.clearCheck(); // Unselect radio buttons
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                RadioButton rb = findViewById(checkedId);

                if (rb != null && rb.isChecked()) {
                    //radioGroup.clearCheck();
                    View view = getCurrentFocus();
                    if (view != null) {
                        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                        if (imm != null) {
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }
                    }
                    String input =ed1.getText().toString();
                    if(input.isEmpty()){
                        Toast.makeText(MainActivity.this,"Please enter Temperature",Toast.LENGTH_SHORT).show();
                    }
                    // Handle the case when a radio button is checked
                    if (checkedId == R.id.rbcelcius) {
                        if(ed1.getText().toString().isEmpty()){
                            Toast.makeText(MainActivity.this,"Please enter Temperature",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            double celsiusValue = Double.parseDouble(ed1.getText().toString()); // Replace this with the actual Celsius temperature

                            double fahrenheitValue = (celsiusValue * 9/5) + 32; // Celsius to Fahrenheit
                            double kelvinValue = celsiusValue + 273.15; // Celsius to Kelvin
                            t4.setText(decimalFormat.format(fahrenheitValue)+"°F");
                            t5.setText(decimalFormat.format(kelvinValue)+"K");

                        }

                    } else if (checkedId == R.id.rbfahrenheit) {
                        if(ed1.getText().toString().isEmpty()){
                            Toast.makeText(MainActivity.this,"Please enter Temperature",Toast.LENGTH_SHORT).show();
                            t4.setText("");
                            t5.setText("");
                        }
                        else{
                            double fahrenheitValue = Double.parseDouble(ed1.getText().toString()); // Replace this with the actual Fahrenheit temperature

                            double celsiusValue = (fahrenheitValue - 32) / 1.8; // Fahrenheit to Celsius
                            double kelvinValue = (fahrenheitValue - 32) * 5/9 + 273.15; // Fahrenheit to Kelvin
                            t4.setText(decimalFormat.format(celsiusValue)+"°C");
                            t5.setText(decimalFormat.format(kelvinValue)+"K");

                        }

                    } else if (checkedId == R.id.rbkelvin) {
                        if(ed1.getText().toString().isEmpty()){
                            Toast.makeText(MainActivity.this,"Please enter Temperature",Toast.LENGTH_SHORT).show();
                            t4.setText("");
                            t5.setText("");
                        }
                        else{
                            double kelvinValue = Double.parseDouble(ed1.getText().toString()); // Replace this with the actual Kelvin temperature

                            double celsiusValue = kelvinValue - 273.15; // Kelvin to Celsius
                            double fahrenheitValue = (kelvinValue * 9/5) - 459.67; // Kelvin to Fahrenheit

                            t4.setText(decimalFormat.format(celsiusValue)+"°C");
                            t5.setText(decimalFormat.format(fahrenheitValue)+"°F");

                        }

                    }
                }

            }

        });

    }



}