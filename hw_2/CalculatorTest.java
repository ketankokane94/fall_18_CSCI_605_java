import org.junit.Assert;
import org.junit.Test;

import java.util.Vector;

import static org.junit.Assert.*;

public class CalculatorTest {

    @Test
    public void createInputVectorShouldConvertGivenArrayOfStringsToAVector() {

        Vector<String> result = Calculator.createInputVector("1 * ( 2 + 3 - ( 1 * ( 2 - 1 ) ) + 3 )");
        assertTrue("returned result is not a vector", result instanceof Vector);
    }

    public void createInputVectorShouldReplaceANyBracketsIntheGivenInputToParanthesis(){
        Vector<String> Result = Calculator.createInputVector("{{}}[[]](())");
        for (String result: Result){
            assertFalse("Method did not convert the all the brackets to paranthesis",!result.equals("("));
        }
    }
}