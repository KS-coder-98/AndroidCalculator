package pl.krzysiek.calculator;

import android.os.Bundle;

import androidx.annotation.NonNull;

public class SimpleCalculatorActivity extends BasicCalculator {

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("stringExpression", calculate.getStringExpression());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_calculator);
        runInOnCreateFun();
        if ( savedInstanceState != null ){
            calculate.setStringExpression(savedInstanceState.getString("stringExpression"));
            textViewExpression.setText(calculate.getStringExpression());
        }
    }

}