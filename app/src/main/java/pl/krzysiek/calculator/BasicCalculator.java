package pl.krzysiek.calculator;

import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Pair;

import java.util.ArrayList;
import java.util.List;

public abstract class BasicCalculator extends AppCompatActivity {

    Calculate calculate = new Calculate();
    TextView textViewExpression;


    Button btnZero;
    Button btnOne;
    Button btnTwo;
    Button btnThree;
    Button btnFour;
    Button btnFive;
    Button btnSix;
    Button btnSeven;
    Button btnEight;
    Button btnNine;

    //button with sign
    Button btnPlus;
    Button btnMinus;
    Button btnMul;
    Button btnDiv;
    Button btnEqual;
    Button btnDot;

    //functional buttons
    Button btnC;
    Button btnPlusMinus;
    Button btnBksp;

    List<Pair<Button, String>> listBtnWithValue;



    void initialButtons() {
        //        button with numbers
        btnZero = findViewById(R.id.buttonZero);
        btnOne = findViewById(R.id.buttonOne);
        btnTwo = findViewById(R.id.buttonTwo);
        btnThree = findViewById(R.id.buttonThree);
        btnFour = findViewById(R.id.buttonFour);
        btnFive = findViewById(R.id.buttonFive);
        btnSix = findViewById(R.id.buttonSix);
        btnSeven = findViewById(R.id.buttonSeven);
        btnEight = findViewById((R.id.buttonEight));
        btnNine = findViewById(R.id.buttonNine);

        //button with sign
        btnPlus = findViewById(R.id.buttonPlus);
        btnMinus = findViewById(R.id.buttonSub);
        btnMul = findViewById(R.id.buttonMul);
        btnDiv = findViewById(R.id.buttonDiv);
        btnEqual = findViewById(R.id.buttonEqual);
        btnDot = findViewById(R.id.buttonDot);

        //functional buttons
        btnC = findViewById(R.id.buttonC);
        btnPlusMinus = findViewById((R.id.btnPlusMinus));
        btnBksp = findViewById(R.id.btnB);
    }

    void initialList() {
        listBtnWithValue = new ArrayList<>(
                List.of(
                        new Pair<>(btnZero, "0"),
                        new Pair<>(btnOne, "1"),
                        new Pair<>(btnTwo, "2"),
                        new Pair<>(btnThree, "3"),
                        new Pair<>(btnFour, "4"),
                        new Pair<>(btnFive, "5"),
                        new Pair<>(btnSix, "6"),
                        new Pair<>(btnSeven, "7"),
                        new Pair<>(btnEight, "8"),
                        new Pair<>(btnNine, "9"),
                        new Pair<>(btnPlus, "+"),
                        new Pair<>(btnDiv, "/"),
                        new Pair<>(btnMul, "*"),
                        new Pair<>(btnMinus, "-"),
                        new Pair<>(btnEqual, "="),
                        new Pair<>(btnDot, "."),
                        new Pair<>(btnC, "C"),
                        new Pair<>(btnPlusMinus, "+/-"),
                        new Pair<>(btnBksp, "B")
                )
        );
    }

    private void click(String s) {
        if (!calculate.addSign(s)) {
            Context context = getApplicationContext();
            CharSequence text = "Wrong operation!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
        textViewExpression.setText(calculate.getStringExpression());
    }

    void runInOnCreateFun() {
        textViewExpression = findViewById(R.id.textViewExpression);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.hide();

        initialButtons();
        initialList();

        listBtnWithValue.forEach(e -> {
            e.first.setOnClickListener(o -> {
                click(e.second);
            });
        });
        textViewExpression.setMovementMethod(new ScrollingMovementMethod());
    }
}
