package com.geekbrains.lavsam.lesson2_calc;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView screenMessage;
    private CalcPilum calc = new CalcPilum();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        screenMessage = findViewById(R.id.tv_screen);
        screenMessage.setText(getString(R.string.screen, 0));

        LayoutInflater inflater = getLayoutInflater();
        FrameLayout l = (FrameLayout) inflater.inflate(R.layout.activity_main, null);

        findViewById(R.id.btn_1).setOnClickListener(this);
        findViewById(R.id.btn_2).setOnClickListener(this);
        findViewById(R.id.btn_3).setOnClickListener(this);
        findViewById(R.id.btn_4).setOnClickListener(this);
        findViewById(R.id.btn_5).setOnClickListener(this);
        findViewById(R.id.btn_6).setOnClickListener(this);
        findViewById(R.id.btn_7).setOnClickListener(this);
        findViewById(R.id.btn_8).setOnClickListener(this);
        findViewById(R.id.btn_9).setOnClickListener(this);
        findViewById(R.id.btn_0).setOnClickListener(this);
        findViewById(R.id.btn_clear).setOnClickListener(this);
        findViewById(R.id.btn_clearAll).setOnClickListener(this);
        findViewById(R.id.btn_div).setOnClickListener(this);
        findViewById(R.id.btn_mult).setOnClickListener(this);
        findViewById(R.id.btn_plus).setOnClickListener(this);
        findViewById(R.id.btn_minus).setOnClickListener(this);
        findViewById(R.id.btn_dot).setOnClickListener(this);
        findViewById(R.id.btn_eq).setOnClickListener(this);

    }


    @Override
    public void onClick(View v)  {
        if (v.getId() == R.id.btn_1) screenMessage.setText(screenMessage.getText() + "1");
        if (v.getId() == R.id.btn_2) screenMessage.setText(screenMessage.getText() + "2");
        if (v.getId() == R.id.btn_3) screenMessage.setText(R.string.key_3);
        if (v.getId() == R.id.btn_4) screenMessage.setText(R.string.key_4);
        if (v.getId() == R.id.btn_5) screenMessage.setText(R.string.key_5);
        if (v.getId() == R.id.btn_6) screenMessage.setText(R.string.key_6);
        if (v.getId() == R.id.btn_7) screenMessage.setText(R.string.key_7);
        if (v.getId() == R.id.btn_8) screenMessage.setText(R.string.key_8);
        if (v.getId() == R.id.btn_9) screenMessage.setText(R.string.key_9);
        if (v.getId() == R.id.btn_0) screenMessage.setText(R.string.key_0);
        if (v.getId() == R.id.btn_clear) screenMessage.setText("");
        if (v.getId() == R.id.btn_clearAll) screenMessage.setText(R.string.key_clearAll);
        if (v.getId() == R.id.btn_div) screenMessage.setText(R.string.key_div);
        if (v.getId() == R.id.btn_mult) screenMessage.setText(R.string.key_mult);
        if (v.getId() == R.id.btn_plus) screenMessage.setText(screenMessage.getText() + "+");
        if (v.getId() == R.id.btn_minus) screenMessage.setText(R.string.key_minus);
        if (v.getId() == R.id.btn_dot) screenMessage.setText(R.string.key_dot);
        if (v.getId() == R.id.btn_eq) {
//            screenMessage.setText(R.string.key_eq);
            double result = calc.Calculate((String)screenMessage.getText());
            screenMessage.setText(screenMessage.getText() + "\n" + result);
//            try {
//                double result = calc.Calculate((String)screenMessage.getText());
//                screenMessage.setText(screenMessage.getText() + "\n=" + result);
//            } catch (NumberFormatException) {
//                screenMessage.setText("ERROR");
//            }
        }
    }
}