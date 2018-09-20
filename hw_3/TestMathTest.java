import org.junit.Test;

import static org.junit.Assert.*;

public class TestMathTest {

    @Test
    public void absShouldReturnZeroWhenInputIsZero() {
        Double delta = 0.0;
        Double input = 0.0;
        Double result = TestMath.abs(input);
        assertEquals("",result,input,delta);
    }

    @Test
    public void absShouldReturnPositiveResultWhenInputIsNegative(){
        Double delta = 0.0;
        Double input = -7.67;
        Double result = TestMath.abs(input);
        assertEquals("",result,(input * -1),delta);
    }

    @Test
    public void absShouldReturnPositiveResultWhenInputIsPositive(){
        Double delta = 0.0;
        Double input = 7.67;
        Double result = TestMath.abs(input);
        assertEquals("",result,(input),delta);
    }

    @Test
    public void maxShouldReturnTheMaxOfTheGivenTwoInputs(){
        //+ +
        int numberOne = 9;
        int numberTwo = 1;
        int result = TestMath.max(numberOne,numberTwo);
        assertEquals("for case max(9, 1), 1 was returned",result,numberOne);

        // + +
        result = TestMath.max(numberTwo,numberOne);
        assertEquals("for case max(1 ,9), 1 was returned",result,numberOne);

        // - +
        numberOne = -9;
        numberTwo = 1;
        result = TestMath.max(numberTwo,numberOne);
        assertEquals("for case max(-9, 1), -9 was returned",result,numberTwo);

        // + -     -> Associativity
        numberOne = 9;
        numberTwo = -1;
        result = TestMath.max(numberTwo,numberOne);
        assertEquals("for case max(9, -1), -1 was returned",result,numberOne);

        //- -
        numberOne = -9;
        numberTwo = -1;
        result = TestMath.max(numberTwo,numberOne);
        assertEquals("for case max(-9, -1), -9 was returned",result,numberTwo);


        //= =
        numberOne = 9;
        numberTwo = 9;
        result = TestMath.max(numberTwo,numberOne);
        assertEquals("for case max(9, 9), returned",result,numberOne);


        //0 -
        numberOne = 0;
        numberTwo = -1;
        result = TestMath.max(numberTwo,numberOne);
        assertEquals("for case max(-1, 0), -1 was returned",result,numberOne);


        //0 +
        numberOne = 0;
        numberTwo = 1;
        result = TestMath.max(numberTwo,numberOne);
        assertEquals("for case max(0, 1), 0 was returned",result,numberTwo);

    }

    @Test
    public  void sqrt(){
        Double Result = TestMath.sqrt(765*4.0);
        System.out.println(Result);
    }
}