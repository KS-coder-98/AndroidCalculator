package pl.krzysiek.calculator;

import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.function.Function;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculateTest {

    private Calculate calculate = new Calculate();

    @Before
    public void init() {
        calculate = new Calculate();
    }

    @Test
    public void addPlus() {
        calculate.addSign("+");
        assertEquals(calculate.getStringExpression(), "+");
    }

    @Test
    public void addMinus() {
        calculate.addSign("-");
        assertEquals(calculate.getStringExpression(), "-");
    }

    @Test
    public void addMul() {
        calculate.addSign("*");
        assertEquals(calculate.getStringExpression(), "0");
    }

    @Test
    public void addDev() {
        calculate.addSign("/");
        assertEquals(calculate.getStringExpression(), "0");
    }

    @Test
    public void changeSign_v1() {
        calculate.setStringExpression("4+5");
        calculate.addSign("+/-");
        assertEquals(calculate.getStringExpression(), "4-5");
    }

    @Test
    public void changeSign_v2() {
        calculate.setStringExpression("4-5");
        calculate.addSign("+/-");
        assertEquals(calculate.getStringExpression(), "4+5");
    }

    @Test
    public void changeSign_v3() {
        calculate.setStringExpression("4+1234");
        calculate.addSign("+/-");
        assertEquals(calculate.getStringExpression(), "4-1234");
    }

    @Test
    public void changeSign_v4() {
        calculate.setStringExpression("4+-1234");
        calculate.addSign("+/-");
        assertEquals(calculate.getStringExpression(), "4+1234");
    }

    @Test
    public void changeSign_v5() {
        calculate.setStringExpression("4-+1234");
        calculate.addSign("+/-");
        assertEquals(calculate.getStringExpression(), "4+1234");
    }

    @Test
    public void changeSign_v6() {
        calculate.setStringExpression("4");
        calculate.addSign("+/-");
        assertEquals(calculate.getStringExpression(), "-4");
    }

    @Test
    public void changeSign_v7() {
        calculate.setStringExpression("4.0");
        calculate.addSign("+/-");
        assertEquals(calculate.getStringExpression(), "-4.0");
    }

    @Test
    public void changeSign_v8() {
        calculate.setStringExpression("-4.0");
        calculate.addSign("+/-");
        assertEquals(calculate.getStringExpression(), "+4.0");
    }

    @Test
    public void changeSign_v9() {
        calculate.setStringExpression("2+4.0");
        calculate.addSign("+/-");
        assertEquals(calculate.getStringExpression(), "2-4.0");
    }

    @Test
    public void changeSignFail() {
        calculate.setStringExpression("4+5+");
        calculate.addSign("+/-");
        assertEquals(calculate.getStringExpression(), "4+5+");
    }

    @Test
    public void addB_v1() {
        calculate.setStringExpression("4+5+");
        calculate.addSign("B");
        assertEquals(calculate.getStringExpression(), "4+5");
    }

    @Test
    public void addB_v2() {
        calculate.setStringExpression("4+5.");
        calculate.addSign("B");
        assertEquals(calculate.getStringExpression(), "4+5");
    }

    @Test
    public void addB_v3() {
        calculate.setStringExpression("0");
        calculate.addSign("B");
        assertEquals(calculate.getStringExpression(), "0");
    }

    @Test
    public void addB_v4() {
        calculate.setStringExpression("1");
        calculate.addSign("B");
        assertEquals(calculate.getStringExpression(), "0");
    }

    @Test
    public void addB_v5() {
        calculate.setStringExpression("ERROR");
        calculate.addSign("B");
        assertEquals(calculate.getStringExpression(), "0");
    }

    @Test
    public void addB_v6() {
        calculate.setStringExpression(".3+2*cos");
        calculate.addSign("B");
        assertEquals(calculate.getStringExpression(), ".3+2*");
    }


    @Test
    public void addBAdvancedMAthOperationThree_v1() {
        calculate.setStringExpression("cos");
        calculate.addSign("B");
        assertEquals(calculate.getStringExpression(), "0");
    }

    @Test
    public void addBAdvancedMAthOperationThree_v2() {
        calculate.setStringExpression("sin");
        calculate.addSign("B");
        assertEquals(calculate.getStringExpression(), "0");
    }

    @Test
    public void addBAdvancedMAthOperationThree_v3() {
        calculate.setStringExpression("tan");
        calculate.addSign("B");
        assertEquals(calculate.getStringExpression(), "0");
    }

    @Test
    public void addBAdvancedMAthOperationThree_v4() {
        calculate.setStringExpression("log");
        calculate.addSign("B");
        assertEquals(calculate.getStringExpression(), "0");
    }

    @Test
    public void addBAdvancedMAthOperationTwo() {
        calculate.setStringExpression("ln");
        calculate.addSign("B");
        assertEquals(calculate.getStringExpression(), "0");
    }

    @Test
    public void addBAdvancedMAthOperationWithSth_v1() {
        calculate.setStringExpression("2*cos");
        calculate.addSign("B");
        assertEquals(calculate.getStringExpression(), "2*");
    }

    @Test
    public void addBAdvancedMAthOperationWithSth_v2() {
        calculate.setStringExpression("2*sin");
        calculate.addSign("B");
        assertEquals(calculate.getStringExpression(), "2*");
    }

    @Test
    public void addBAdvancedMAthOperationWithSth_v3() {
        calculate.setStringExpression("2*tan");
        calculate.addSign("B");
        assertEquals(calculate.getStringExpression(), "2*");
    }

    @Test
    public void divideByZero() {
        calculate.setStringExpression("2/0");
        calculate.addSign("=");
        assertEquals(calculate.getStringExpression(), "ERROR");
    }

    @Test
    public void divideByZero_v1() {
        calculate.setStringExpression("2.3/0");
        calculate.addSign("=");
        assertEquals(calculate.getStringExpression(), "ERROR");
    }

    @Test
    public void addC_v1() {
        calculate.setStringExpression("2.3/0");
        calculate.addSign("C");
        assertEquals(calculate.getStringExpression(), "2.3/");
    }

    @Test
    public void addC_v2() {
        calculate.setStringExpression("2.3/");
        calculate.addSign("C");
        assertEquals(calculate.getStringExpression(), "2.3/");
    }

    @Test
    public void addC_v3() {
        calculate.setStringExpression("2.3");
        calculate.addSign("C");
        assertEquals(calculate.getStringExpression(), "0");
    }

    @Test
    public void addC_v4() {
        calculate.setStringExpression("2.0");
        calculate.addSign("C");
        assertEquals(calculate.getStringExpression(), "0");
    }

    @Test
    public void addC_v5() {
        calculate.setStringExpression("-2.0");
        calculate.addSign("C");
        assertEquals(calculate.getStringExpression(), "0");
    }

    @Test
    public void addC_v6() {
        calculate.setStringExpression("-2");
        calculate.addSign("C");
        assertEquals(calculate.getStringExpression(), "0");
    }

    @Test
    public void addC_v7() {
        calculate.setStringExpression("-2.");
        calculate.addSign("C");
        assertEquals(calculate.getStringExpression(), "0");
    }

    @Test
    public void addC_v8() {
        calculate.setStringExpression("-0.3");
        calculate.addSign("C");
        assertEquals(calculate.getStringExpression(), "0");
    }

    @Test
    public void addC_v9() {
        calculate.setStringExpression(".3");
        calculate.addSign("C");
        assertEquals(calculate.getStringExpression(), "0");
    }

    @Test
    public void addC_v10() {
        calculate.setStringExpression(".3+2");
        calculate.addSign("C");
        assertEquals(calculate.getStringExpression(), ".3+");
    }

    @Test
    public void addC_v11() {
        calculate.setStringExpression(".3+2*");
        calculate.addSign("C");
        assertEquals(calculate.getStringExpression(), ".3+2*");
    }

    @Test
    public void addC_v12() {
        calculate.setStringExpression(".3+2*cos");
        calculate.addSign("C");
        assertEquals(calculate.getStringExpression(), ".3+2*");
    }

    @Test
    public void ln_v1() {
        //todo
        Function logb = new Function("logb", 2) {
            @Override
            public double apply(double... args) {
                return Math.log(args[0]) / Math.log(args[1]);
            }
        };
        double result = new ExpressionBuilder("logb(10," + String.valueOf(Math.exp(1)) + ")")
                .function(logb)
                .build()
                .evaluate();
        double expected = 2.302585092994046;
        assertEquals(expected, result, 0d);
    }

    @Test
    public void testFunToConvert() {
        String input = "2*ln(2)+3*ln(2+3)+5";
        String res = calculate.changeLnToLogB(input);
        assertEquals("2*logb(2, 2.718281828459045)+3*logb(2+3, 2.718281828459045)+5", res);
    }

    @Test
    public void testDot() {
        calculate.addSign("3");
        calculate.addSign(".");
        calculate.addSign("2");
        calculate.addSign(".");
        calculate.addSign("2");

        assertEquals("3.22", calculate.getStringExpression());
    }

    @Test
    public void testDot_v1() {
        calculate.addSign("3");
        calculate.addSign(".");
        calculate.addSign("2");
        calculate.addSign("+");
        calculate.addSign("2");
        calculate.addSign(".");
        calculate.addSign("2");
        assertEquals("3.2+2.2", calculate.getStringExpression());
    }

    @Test
    public void testDot_v2() {
        calculate.addSign("3");
        calculate.addSign(".");
        calculate.addSign("2");
        calculate.addSign("+");
        calculate.addSign("2");
        calculate.addSign(".");
        calculate.addSign(".");
        calculate.addSign("2");
        assertEquals("3.2+2.2", calculate.getStringExpression());
    }

    @Test
    public void numerical_calculation_error() {
        calculate.addSign("sqrt");
        calculate.addSign("2");
        calculate.addSign("=");
        calculate.addSign("^");
        calculate.addSign("2");
        calculate.addSign("=");
        assertEquals("2.0", calculate.getStringExpression());
    }


    @Test
    public void numerical_calculation_error_v2() {
        calculate.addSign("sqrt");
        calculate.addSign("5");
        calculate.addSign("=");
        calculate.addSign("^");
        calculate.addSign("2");
        calculate.addSign("=");
        assertEquals("5.0", calculate.getStringExpression());
    }

    @Test
    public void numerical_calculation_error_v3() {
        calculate.addSign("sqrt");
        calculate.addSign("8");
        calculate.addSign("=");
        calculate.addSign("^");
        calculate.addSign("2");
        calculate.addSign("=");
        assertEquals("8.0", calculate.getStringExpression());
    }

    @Test
    public void numerical_calculation_error_vol4() {
        calculate.addSign("sqrt");
        calculate.addSign("2");
        calculate.addSign("=");
        calculate.addSign("^");
        calculate.addSign("2");
        calculate.addSign("=");
        assertEquals("2.0", calculate.getStringExpression());
    }

    @Test
    public void numerical_calculation_error_v5() {
        calculate.addSign("sqrt");
        calculate.addSign("167");
        calculate.addSign("=");
        calculate.addSign("^");
        calculate.addSign("2");
        calculate.addSign("=");
        assertEquals("167.0", calculate.getStringExpression());
    }

    @Test
    public void numerical_calculation_error_v6() {
        calculate.addSign("sqrt");
        calculate.addSign("57");
        calculate.addSign("=");
        calculate.addSign("^");
        calculate.addSign("2");
        calculate.addSign("=");
        assertEquals("57.0", calculate.getStringExpression());
    }

    @Test
    public void numerical_calculation_error_v7() {
        calculate.addSign("sqrt");
        calculate.addSign("43");
        calculate.addSign("=");
        calculate.addSign("^");
        calculate.addSign("2");
        calculate.addSign("=");
        assertEquals("43.0", calculate.getStringExpression());
    }

    @Test
    public void numerical_calculation_error_v8() {
        calculate.addSign("sqrt");
        calculate.addSign("4139");
        calculate.addSign("=");
        calculate.addSign("^");
        calculate.addSign("2");
        calculate.addSign("=");
        assertEquals("4139.0", calculate.getStringExpression());
    }

    @Test
    public void numerical_calculation_error_v9() {
        calculate.addSign("sqrt");
        calculate.addSign("434");
        calculate.addSign("=");
        calculate.addSign("^");
        calculate.addSign("2");
        calculate.addSign("=");
        assertEquals("434.0", calculate.getStringExpression());
    }

    @Test
    public void numerical_calculation_error_v10() {
        calculate.addSign("sqrt");
        calculate.addSign("777");
        calculate.addSign("=");
        calculate.addSign("^");
        calculate.addSign("2");
        calculate.addSign("=");
        assertEquals("777.0", calculate.getStringExpression());
    }

    @Test
    public void numerical_calculation_error_v11() {
        calculate.addSign("sqrt");
        calculate.addSign("5463");
        calculate.addSign("=");
        calculate.addSign("^");
        calculate.addSign("2");
        calculate.addSign("=");
        assertEquals("5463.0", calculate.getStringExpression());
    }

    @Test
    public void numerical_calculation_error_v12() {
        calculate.addSign("sqrt");
        calculate.addSign("797");
        calculate.addSign("=");
        calculate.addSign("^");
        calculate.addSign("2");
        calculate.addSign("=");
        assertEquals("797.0", calculate.getStringExpression());
    }

    @Test
    public void numerical_calculation_error_v13() {
        calculate.addSign("sqrt");
        calculate.addSign("473");
        calculate.addSign("=");
        calculate.addSign("^");
        calculate.addSign("2");
        calculate.addSign("=");
        assertEquals("473.0", calculate.getStringExpression());
    }

    @Test
    public void numerical_calculation_error_v14() {
        calculate.addSign("sqrt");
        calculate.addSign("99");
        calculate.addSign("=");
        calculate.addSign("^");
        calculate.addSign("2");
        calculate.addSign("=");
        assertEquals("99.0", calculate.getStringExpression());
    }

    @Test
    public void numerical_calculation_error_v15() {
        calculate.addSign("sqrt");
        calculate.addSign("51");
        calculate.addSign("=");
        calculate.addSign("^");
        calculate.addSign("2");
        calculate.addSign("=");
        assertEquals("51.0", calculate.getStringExpression());
    }

    @Test
    public void numerical_calculation_error_v16() {
        calculate.addSign("sqrt");
        calculate.addSign("67");
        calculate.addSign("=");
        calculate.addSign("^");
        calculate.addSign("2");
        calculate.addSign("=");
        assertEquals("67.0", calculate.getStringExpression());
    }

    @Test
    public void numerical_calculation_error_v17() {
        calculate.addSign("sqrt");
        calculate.addSign("52456");
        calculate.addSign("=");
        calculate.addSign("^");
        calculate.addSign("2");
        calculate.addSign("=");
        assertEquals("52456.0", calculate.getStringExpression());
    }

    @Test
    public void numerical_calculation_error_v19() {
        for (int i = 1; i < 9999999; i++) {
            calculate.setStringExpression("0");
            calculate.addSign("sqrt");
            calculate.addSign(String.valueOf(i));
            calculate.addSign("=");
            calculate.addSign("^");
            calculate.addSign("2");
            calculate.addSign("=");
            assertEquals(i + ".0", calculate.getStringExpression());
        }
    }
}
