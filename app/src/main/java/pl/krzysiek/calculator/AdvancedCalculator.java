package pl.krzysiek.calculator;

import android.widget.Button;

import androidx.core.util.Pair;

import java.util.List;

public abstract class AdvancedCalculator extends BasicCalculator {

    Button btnSin;
    Button btnCos;
    Button btnTan;
    Button btnLn;
    Button btnSqrt;
    Button btnSquared;
    Button btnPowerToY;
    Button btnLog;
    Button btnOpen;
    Button btnClose;
    Button btnComma;


    @Override
    void initialButtons() {
        super.initialButtons();
        btnSin = findViewById(R.id.btnSin);
        btnCos = findViewById(R.id.btnCos);
        btnTan = findViewById(R.id.btnTan);
        btnLn = findViewById(R.id.btnLn);
        btnSqrt = findViewById(R.id.btnSqrt);
        btnSquared = findViewById(R.id.btnSquared);
        btnPowerToY = findViewById(R.id.btbPowerY);
        btnLog = findViewById(R.id.btnLog);
        btnOpen = findViewById(R.id.btnOpen);
        btnClose = findViewById(R.id.btnClose);
    }

    @Override
    void initialList() {
        super.initialList();
        List<Pair<Button, String>> tempList = List.of(
                new Pair<>(btnSin, "sin"),
                new Pair<>(btnCos, "cos"),
                new Pair<>(btnTan, "tan"),
                new Pair<>(btnLn, "ln"),
                new Pair<>(btnSqrt, "sqrt"),
                new Pair<>(btnSquared, "^2"),
                new Pair<>(btnPowerToY, "^"),
                new Pair<>(btnLog, "log"),
                new Pair<>(btnOpen, "("),
                new Pair<>(btnClose, ")")
        );
        listBtnWithValue.addAll(tempList);
    }

    @Override
    void runInOnCreateFun() {
        super.runInOnCreateFun();
    }
}
