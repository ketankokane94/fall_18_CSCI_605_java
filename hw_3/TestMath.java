
/**
 * This Program implements the abs, max and sqrt methods of Math class
 * @author ketan kokane
 * @author Ameya Nagnur
 */
public class TestMath {
    static private int testCaseCount = 0;
    static private int failedTestCaseCount = 0;
    final static private Double ZERO = 0.0;
    /**
     * @param input
     * @return the absolute value of the given input
     */
    public static Double abs(Double input){

        if (input == ZERO){
            return ZERO;
        }
        if (input < ZERO){
            return input * -1.0;
        }
        else {
            return input;
        }
    }

    /**
     * @param numberOne
     * @param numberTwo
     * @return max of numberOne and numberTwo
     */
    public static int max(int numberOne, int numberTwo){
        if (numberOne >= numberTwo){
            return numberOne;
        }
        else {
            return numberTwo;
        }
    }

    /**
     * @param number
     * @return finds the sqrt of the given number using newtons method
     */
    public  static Double sqrt(Double number){
        Double InitialEstimation;
        Double result = getInitialEstimate(number);
        int i =0;
        do{
            InitialEstimation = result;
            result = 0.5 * (result + (number/result));
            i++;
        } while (InitialEstimation - result > 0.000000001 );
        return result;
    }

    /**
     *
     * @param number
     * @return an initial guess of the sqrt of the given number, better the initial estimate better is the performance of the sqrt function
     */
    private static Double getInitialEstimate(Double number) {
        return 10000000000000.0;
    }

    /**
     * main function to run various test cases on the implemented methods of the math class
     * @param args
     */
    public static void main(String args[]){
        absShouldReturnZeroWhenInputIsZero();
        absShouldReturnPositiveResultWhenInputIsNegative();
        absShouldReturnPositiveResultWhenInputIsPositive();
        maxShouldReturnTheMaxOfTheGivenTwoInputs();
        testSqrtFunction();
        System.out.println("Total "+testCaseCount+" test cases were executed: "+failedTestCaseCount+" failed");

    }

    /**
     * function to test the sqrt function of the TestMath class
     */
    private static void testSqrtFunction() {
        double result;
        double theDoubles[] = {1, 2, 3, 4, 5 };


        for ( int index = 0; index < theDoubles.length; index ++ ) {
            result = sqrt(theDoubles[index]);
            if ( abs( result * result - theDoubles[index] ) > 0.000000001 ){
                System.out.println("Test : sqrt failed: " + ( result * result - theDoubles[index] ));
            }else {
                System.out.println("Test : sqrt Passed: " + (int)( result * result - theDoubles[index] ) );
            }

        }
    }

    /**
     * function to test the possible scenarios of the absolute function of the implemented TestMath class
     */
    public static void absShouldReturnZeroWhenInputIsZero() {
        testCaseCount ++;
        Double input = 0.0;
        Double result = TestMath.abs(input);
        if(result != (input)){
            System.out.println("for abs( "+input+" ), "+ result+" was returned");
            failedTestCaseCount++;
        }
    }

    /**
     * function to test the possible scenarios of the absolute function of the implemented TestMath class
     */
    public static  void absShouldReturnPositiveResultWhenInputIsNegative(){
        testCaseCount ++;
        Double input = -7.67;
        Double result = TestMath.abs(input);
        if(result != (input * -1)){
            System.out.println("for abs( "+input+" ), "+ result+" was returned");
            failedTestCaseCount++;
        }
    }

    /**
     * function to test the possible scenarios of the absolute function of the implemented TestMath class
     */
    public static  void absShouldReturnPositiveResultWhenInputIsPositive(){
        testCaseCount ++;
        Double input = 7.67;
        Double result = TestMath.abs(input);
        if(result != (input)){
            System.out.println("for abs( "+input+" ), "+ result+" was returned");
            failedTestCaseCount++;
        }
    }

    /**
     * function to test the possible scenarios of the maximum function of the implemented TestMath class
     */

    public static  void maxShouldReturnTheMaxOfTheGivenTwoInputs(){
        testCaseCount ++;
        //+ +
        int numberOne = 9;
        int numberTwo = 1;
        int result = TestMath.max(numberOne,numberTwo);

        if(result != (numberOne)){
            System.out.println("for max( "+numberOne+", "+numberTwo+" ), "+ result+" was returned");
            failedTestCaseCount++;
        }


        testCaseCount ++;
        // + +
        result = TestMath.max(numberTwo,numberOne);
        if(result != (numberOne)){
            System.out.println("for max( "+numberTwo+", "+numberOne+" ), "+ result+" was returned");
            failedTestCaseCount++;
        }


        testCaseCount ++;
        // - +
        numberOne = -9;
        numberTwo = 1;
        result = TestMath.max(numberTwo,numberOne);
        if(result != (numberTwo)){
            System.out.println("for max( "+numberTwo+", "+numberOne+" ), "+ result+" was returned");
            failedTestCaseCount++;
        }

        testCaseCount ++;
        // + -     -> Associativity
        numberOne = 9;
        numberTwo = -1;
        result = TestMath.max(numberTwo,numberOne);
        if(result != (numberOne)){
            System.out.println("for max( "+numberTwo+", "+numberOne+" ), "+ result+" was returned");
            failedTestCaseCount++;
        }


        testCaseCount ++;
        //- -
        numberOne = -9;
        numberTwo = -1;
        result = TestMath.max(numberTwo,numberOne);
        if(result != (numberTwo)){
            System.out.println("for max( "+numberTwo+", "+numberOne+" ), "+ result+" was returned");
            failedTestCaseCount++;
        }


        testCaseCount ++;
        //= =
        numberOne = 9;
        numberTwo = 9;
        result = TestMath.max(numberTwo,numberOne);
        if(result != (numberOne)){
            System.out.println("for max( "+numberTwo+", "+numberOne+" ), "+ result+" was returned");
            failedTestCaseCount++;
        }


        testCaseCount ++;
        //0 -
        numberOne = 0;
        numberTwo = -1;
        result = TestMath.max(numberTwo,numberOne);
        if(result != (numberOne)){
            System.out.println("for max( "+numberTwo+", "+numberOne+" ), "+ result+" was returned");
            failedTestCaseCount++;
        }


        testCaseCount ++;
        //0 +
        numberOne = 0;
        numberTwo = 1;
        result = TestMath.max(numberTwo,numberOne);
        if(result != (numberTwo)){
            System.out.println("for max( "+numberTwo+", "+numberOne+" ), "+ result+" was returned");
            failedTestCaseCount++;
        }


    }

}
