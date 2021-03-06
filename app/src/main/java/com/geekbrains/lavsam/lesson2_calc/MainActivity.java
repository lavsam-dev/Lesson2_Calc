package com.geekbrains.lavsam.lesson2_calc;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private final int[] numberButtonIds = new int[]{
            R.id.key_0, R.id.key_1, R.id.key_2, R.id.key_3,
            R.id.key_4, R.id.key_5, R.id.key_6, R.id.key_7, R.id.key_8, R.id.key_9,
            R.id.key_dot, R.id.key_dec, R.id.key_sum, R.id.key_multi, R.id.key_div
    };
    private TextView screenMessage;
    private CalcPilum calc = new CalcPilum();
    private String screenEval = new String();
    private boolean eraseScreen = false;
    private static final String ARG_EVAL = "ARG_EVAL";
    private static final String ARG_ERASE_SCREEN = "ARG_ERASE_SCREEN";
    private static final String ARG_ACTIVITY_CURRENT = "ARG_ACTIVITY_CURRENT";

    private int activityCurrent = R.layout.activity_main_g;

    private void setNumberButtonListeners() {
        for (int i = 0; i < numberButtonIds.length; i++) {
            findViewById(numberButtonIds[i]).setOnClickListener(v -> addAndShow(((Button) v).getText().toString()));
        }
        findViewById(R.id.key_CE).setOnClickListener(v -> {
            screenEval = getString(R.string.screen_empty);
            screenMessage.setText(screenEval);
        });
        findViewById(R.id.key_C).setOnClickListener(v -> {
            if (screenEval.length() > 1) {
                screenEval = screenEval.substring(0, screenEval.length() - 1);
            } else {
                screenEval = getString(R.string.screen_empty);
            }
            screenMessage.setText(screenEval);
        });
        findViewById(R.id.key_result).setOnClickListener(v -> {
            eraseScreen = true;
            double result = calc.Calculate(screenEval);
            screenEval += getString(R.string.key_eq) + "\n" + String.format(getString(R.string.resultFormat), result);
            screenMessage.setText(screenEval);
        });
        findViewById(R.id.key_theme).setOnClickListener(v -> {
            if (activityCurrent == R.layout.activity_main_black) {
                activityCurrent = R.layout.activity_main_g;
            } else {
                activityCurrent = R.layout.activity_main_black;
            }

            setContentView(activityCurrent);
            recreate();
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
        setContentView(activityCurrent);

        screenMessage = findViewById(R.id.resultTextView);
        screenMessage.setText(getString(R.string.screen_empty));
        setNumberButtonListeners();

        if (savedInstanceState != null) {
            screenEval = savedInstanceState.getString(ARG_EVAL);
            screenMessage.setText(screenEval);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(ARG_EVAL, screenEval);
        outState.putBoolean(ARG_ERASE_SCREEN, eraseScreen);
        outState.putInt(ARG_ACTIVITY_CURRENT, activityCurrent);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        screenEval = savedInstanceState.getString(ARG_EVAL);
        eraseScreen = savedInstanceState.getBoolean(ARG_ERASE_SCREEN);
        activityCurrent = savedInstanceState.getInt(ARG_ACTIVITY_CURRENT);
        screenMessage.setText(screenEval);
        super.onRestoreInstanceState(savedInstanceState);
    }
}