package pl.krzysiek.calculator;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.function.Function;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Calculate {
    private String stringExpression;
    Expression expression;
    private List<String> digits = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "-", "+", "*", "/", "cos", "sin", "tan", "tn", "ln", "log", "sqrt"));
    private List<String> digits1 = new ArrayList<>(Arrays.asList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"));
    private List<String> mathOperations = new ArrayList<>(Arrays.asList("sin", "cos", "ln", "log", "tan", "sqrt"));

    Function logb = new Function("logb", 2) {
        @Override
        public double apply(double... args) {
            return Math.log(args[0]) / Math.log(args[1]);
        }
    };

    public Calculate() {
        stringExpression = "0";
    }

    String changeLnToLogB(String input) {
        int index = 0;
        do {
            index = input.indexOf("ln(");
            if (index == -1)
                break;
            String exe = ", " + Math.exp(1) + ")";
            String temp = input.substring(0, index);
            input = input.substring(index).replaceFirst("\\)", exe);
            String first = input.replaceFirst("ln\\(", "logb(");
            input = temp + first;
        } while (index != -1);
        return input;
    }

    public boolean addSign(String sign) {
        if (stringExpression.equals("0") && digits.contains(sign) || stringExpression.equals("ERROR")) {
            if (sign.equals("C") || sign.equals("B") || sign.equals("+/-") || sign.equals("/") || sign.equals("*") || sign.equals("=")) {
                stringExpression = "0";
            } else
                stringExpression = sign;
        } else if (sign.equals("+/-")) {
            if (!addPlusMines())
                return false;
        } else if (sign.equals("B")) {
            addB();
        } else if (sign.equals("C")) {
            addC();
        } else if (sign.equals("CE")) {
            stringExpression = "0";
        } else if (sign.equals("=")) {
            addEqual();
        } else if (sign.equals(".")) {
            addDot();
        } else {
            stringExpression += sign;
        }
        return true;
    }

    private void addDot() {
        int indexSign = lastIndexOfSign();
        String temp = stringExpression;
        if (indexSign != -1)
            temp = stringExpression.substring(indexSign);
        if (!temp.contains("."))
            stringExpression += ".";
    }

    private boolean addPlusMines() {
        if (stringExpression.equals("+"))
            stringExpression = "-";
        else if (stringExpression.equals("-"))
            stringExpression = "+";
        else if (!digits1.contains(stringExpression.substring(stringExpression.length() - 1))) {
            return false;
        } else if (!stringExpression.equals("0")) {
            int lastIndex;
            lastIndex = lastIndexOfSign();
            //only number
            if (lastIndex == -1) {
                stringExpression = "-" + stringExpression;
            } else {
                String temp = stringExpression.substring(0, lastIndex);
                String _char = stringExpression.substring(lastIndex, lastIndex + 1);
                String reside = stringExpression.length() - 1 > lastIndex ? stringExpression.substring(lastIndex + 1) : "";
                if (_char.equals("-")) {
                    _char = "+";
                } else if (_char.equals("+")) {
                    _char = "-";
                } else
                    _char += "-";
                stringExpression = temp + _char + reside;
                stringExpression = stringExpression.replace("++", "+");
                stringExpression = stringExpression.replace("--", "+");
            }
        }
        return true;
    }

    private void addB() {
        for (String s : mathOperations) {
            if (stringExpression.lastIndexOf(s) != -1 && stringExpression.lastIndexOf(s) == stringExpression.length() - s.length()) {
                stringExpression = stringExpression.substring(0, stringExpression.length() - (s.length()));
                stringExpression = stringExpression.length() == 0 ? "0" : stringExpression;
                return;
            }
        }
        if (stringExpression.length() > 1)
            stringExpression = stringExpression.substring(0, stringExpression.length() - 1);
        else
            stringExpression = "0";
    }

    private void addC() {
        int maxIndex = lastIndexOfSign();
        if (maxIndex != -1) {
            stringExpression = stringExpression.substring(0, maxIndex + 1);
            if (stringExpression.length() == 1)
                stringExpression = "0";
        } else
            stringExpression = "0";
    }

    private void addEqual() {
        stringExpression = changeLnToLogB(stringExpression);
        try {
            expression = new ExpressionBuilder(stringExpression)
                    .function(logb)
                    .build();
            double res = expression.evaluate();
            if (Double.isNaN(res)) {
                stringExpression = "ERROR";
            }
            String temp = calculateEpsilon(String.valueOf(res));
            setStringExpression(String.valueOf(temp));
        } catch (Exception e) {
            stringExpression = "ERROR";
        }
    }

    private int lastIndexOfSign() {
        int lastPlus = stringExpression.lastIndexOf("+");
        int lastMinus = stringExpression.lastIndexOf("-");
        int lastSub = stringExpression.lastIndexOf("/");
        int lastMul = stringExpression.lastIndexOf("*");
        List<Integer> indexOfTheLastSign = new ArrayList<>(Arrays.asList(lastMinus, lastMul, lastPlus, lastSub));
        return Collections.max(indexOfTheLastSign);
    }

    public String calculateEpsilon(String s) {
        BigDecimal numberCalculated = new BigDecimal(s);
        double test = Math.rint(Double.parseDouble(s));
        BigDecimal abs = (numberCalculated.subtract(BigDecimal.valueOf(test))).abs();
        BigDecimal epsilon = new BigDecimal("0.00000001");
        if ( abs.compareTo(epsilon) == -1){
            return String.valueOf(test);
        }
        return s;
    }
}
