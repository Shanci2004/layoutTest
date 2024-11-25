package com.example.layouttest;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv_result;
    private String firstNum = "";
    private String operator = "";
    private String secondNum = "";
    private String result = "";
    private String showText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        tv_result = findViewById(R.id.tv_result);

        findViewById(R.id.btn_0).setOnClickListener(this);
        findViewById(R.id.btn_1).setOnClickListener(this);
        findViewById(R.id.btn_2).setOnClickListener(this);
        findViewById(R.id.btn_3).setOnClickListener(this);
        findViewById(R.id.btn_4).setOnClickListener(this);
        findViewById(R.id.btn_5).setOnClickListener(this);
        findViewById(R.id.btn_6).setOnClickListener(this);
        findViewById(R.id.btn_7).setOnClickListener(this);
        findViewById(R.id.btn_8).setOnClickListener(this);
        findViewById(R.id.btn_9).setOnClickListener(this);
        findViewById(R.id.btn_little).setOnClickListener(this);
        findViewById(R.id.btn_equals).setOnClickListener(this);
        findViewById(R.id.btn_add).setOnClickListener(this);
        findViewById(R.id.btn_sub).setOnClickListener(this);
        findViewById(R.id.btn_mul).setOnClickListener(this);
        findViewById(R.id.btn_div).setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        String inputText;
        inputText = ((TextView)view).getText().toString();
        if(view.getId() == R.id.btn_equals){
            double cal_result = calculate();
            refreshOperate(String.valueOf(cal_result));
            refreshText(showText + "=" + result);
        }else if(view.getId() == R.id.btn_add){
            operator = inputText;
            refreshText(showText + operator);
        }else if(view.getId() == R.id.btn_sub){
            operator = inputText;
            refreshText(showText + operator);
        }else if(view.getId() == R.id.btn_mul){
            operator = inputText;
            refreshText(showText + operator);
        }else if(view.getId() == R.id.btn_div){
            operator = inputText;
            refreshText(showText + operator);
        }else{
            if(result.length() > 0 && operator.equals("")){
                clear();
            }
            if(operator.equals("")){
                firstNum = firstNum + inputText;
            }else{
                secondNum = secondNum + inputText;
            }
            if(showText.equals("0.0") && !inputText.equals(".")){
                refreshText(inputText);
            }else{
                refreshText(showText + inputText);
            }

        }
    }

    private void clear(){
        refreshOperate("");
        refreshText("");
    }

    private void refreshText(String text){
        showText = text;
        tv_result.setText(showText);
    }

    private double calculate(){
        switch (operator) {
            case "+":
                return Double.parseDouble(firstNum) + Double.parseDouble(secondNum);
            case "-":
                return Double.parseDouble(firstNum) - Double.parseDouble(secondNum);
            case "x":
                return Double.parseDouble(firstNum) * Double.parseDouble(secondNum);
            default:
                return Double.parseDouble(firstNum) / Double.parseDouble(secondNum);
        }
    }

    private void refreshOperate(String new_result){
        result = new_result;
        firstNum = new_result;
        secondNum = "";
        operator = "";
    }
}