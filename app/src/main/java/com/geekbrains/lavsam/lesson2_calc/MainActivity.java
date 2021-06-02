package com.geekbrains.lavsam.lesson2_calc;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private final int[] numberButtonIds = new int[]{
            R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3,
            R.id.btn_4, R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9,
            R.id.btn_dot, R.id.btn_minus, R.id.btn_plus, R.id.btn_mult, R.id.btn_div
    };
    private TextView screenMessage;
    private CalcPilum calc = new CalcPilum();
    private String screenEval = new String();
    private boolean eraseScreen = false;

    private void setNumberButtonListeners() {
        for (int i = 0; i < numberButtonIds.length; i++) {
            findViewById(numberButtonIds[i]).setOnClickListener(v -> addAndShow(((Button) v).getText().toString()));
        }
        findViewById(R.id.btn_clearAll).setOnClickListener(v -> {
            screenEval = getString(R.string.screen_empty);
            screenMessage.setText(screenEval);
        });
        findViewById(R.id.btn_clear).setOnClickListener(v -> {
            if (screenEval.length() > 1) {
                screenEval = screenEval.substring(0, screenEval.length() - 2);
            } else {
                screenEval = getString(R.string.screen_empty);
            }
            screenMessage.setText(screenEval);
        });
        findViewById(R.id.btn_eq).setOnClickListener(v -> {
            eraseScreen = true;
            double result = calc.Calculate(screenEval);
            screenEval += "\n" + result;
            screenMessage.setText(screenEval);
        });
    }

    private void addAndShow(String s) {
        if (eraseScreen) {
            doEraseScreen();
            screenEval = s;
        } else {
            if (screenEval == getString(R.string.screen_empty)) doEraseScreen();
            screenEval += s;
        }
        screenMessage.setText(screenEval);
    }

    private void doEraseScreen() {
        screenEval = "";
        eraseScreen = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        screenMessage = findViewById(R.id.tv_screen);
        screenMessage.setText(getString(R.string.screen_empty));

        setNumberButtonListeners();

    }

}