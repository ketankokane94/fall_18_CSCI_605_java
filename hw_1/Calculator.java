/**
 * Calculator.java
 * 
 * Version :
 *          1
 * Revisions : 
 *          1
 */


import java.util.Vector;
import java.util.Stack;
 /**
  * This program evaluates the a given mathematical infix expression 
  *
  * @author Ketan Balbhim Kokane
  * @author Ameya Deepak Nagnur
  */

/*
Program uses 3 main data stores
1. Operand Stack, 2. Operator Stack, 3. string accessed as char array to provide order to the precedence to the operator

The main method of the program simply calls the performCalculation method and passes a string array (which is the expression to be evaluated)
performCalculation only converts the string array to vector and prints the input string and makes call to the calculate method and passes the generated vector of input

Calculate Method:
calulate method iterates through the vector, character by character
1.if the current character is operator then call the performOperator method and pass the operator
else the current character must be an operand so call performNumber method and pass the operand
2.then remove current processed character from the input vector (so that we are shorting the input vector), which is always the first element in the vector
keep repeating 1 and 2 while something is there to process in the input vector

Once the expression is processed and two stacks i.e. number stack and operator stack are filled with numbers and operators respectively
To begin or continue evaluating the expression, there must atleast one operator on the opeartor stack and two numbers on the number stack
if both the above condition are true then a call is made to evaluate method,  else a print statement is made to signal dangling operand and program stops execution
Evaluate Method:
	evaluate is the method where both the data stores, operand stack and number stack are used to carry out the calculation
	it gets the operand from the stack, gets two numbers from number stack, based on current operator decides using if, else if cases
	which mathematical operation to perform, for exponential operator programs uses Math class. The result generated after doing the operation, is again pushed on the number stack
As evaluate method only, calculates the result and pushes it on the number stack, after all evaluation is done the only number on the numberstack is the final result of the expression

Other Helper Functions: PerformOperator
checks for the operator precedence based on the index of operator in the operators string
if the operatorStack is empty or precedence of the current operator is greater than the top operator on the stack
	then simply push the current operator to the operand stack
else while operator stack is not empty and current operator's precedence is less than the precedence of the stack top operator
	then call evaluate method
Number stack is populated by the performNumber and operatorStack is populated by performOperator method
*/

public class Calculator {

    // See https://docs.oracle.com/javase/10/docs/api/java/util/Stack.html
    static Stack<Double> numberStack = new Stack<Double>();
    static Stack<String> operatorStack = new Stack<String>();
    // See https://docs.oracle.com/javase/10/docs/api/java/lang/String.html
    static String operators =  "+-%*/^" ;

    public static void main (String args []) {
        performCalculation("2", "+", "3");
        performCalculation("2", "+", "3", "*", "3");
        performCalculation("2", "+", "3", "*", "4", "+", "5");
        performCalculation("2", "*", "3", "+", "3");
        performCalculation("2", "+", "3", "^", "4");
        performCalculation("2", "^", "3", "+", "4");
        performCalculation("2", "^", "3", "^", "4");
    }

    // See https://docs.oracle.com/javase/8/docs/technotes/guides/language/varargs.html
    public static void performCalculation (String ... valuesAndOperators)	{
        Vector<String> aLine = new Vector<String>();
        for ( String valuesAndOperator: valuesAndOperators )	{
            aLine.add(valuesAndOperator);
            System.out.print(valuesAndOperator + " ");
        }
        System.out.println(" =  " + calculate(aLine) );
    }
    /** drives the calculation and returns the result
     */

    public static double calculate (Vector<String> inputLine) {
        while ( inputLine.size() >= 1 )	{
            if ( operator( inputLine.firstElement() )	)
                performOperator(inputLine.firstElement());

            else
                performNumber(inputLine.firstElement());

            inputLine.removeElementAt(0);
        }

        while ( !  operatorStack.empty() )	{
            if ( numberStack.size() > 1 )
                evaluate();
            else	{
                System.out.println("dangling operand ....");
                System.out.println(numberStack);
                System.exit(1);

            }
        }

        return numberStack.pop();
    }

    /** perform the required operation based on precedence of the operators on the stack
     */
    public static boolean operator (String op) {
        return ( operators.indexOf(op) >= 0 );
    }

    /** deteremence a precedence level for the operator
     */
    public static int precedence (String op) {
        return operators.indexOf(op);
    }

    /** perform the required operation based on precedence on the stack
     */

    public static void performOperator (String op) {
        while (! operatorStack.empty()  &&
                (  precedence(op) > precedence(operatorStack.peek() ) )
        )
            evaluate();
        operatorStack.push(op);
    }

    /** pushes the number on the number stack
     */
    public static void performNumber (String number) {
        numberStack.push(Double.valueOf(number));
    }

    /** get the number of the stack, if a number is available, else RIP
     */
    public static double  getNumber () {
        if ( numberStack.empty() ){
            System.out.println("not enough numbers ...");
            System.exit(2);
        }
        return numberStack.pop();
    }

    /* perform the required ovperation based on the operator in the stack
     */
    public static void evaluate () {
        String currentOp = operatorStack.pop();
        double right = getNumber();
        double left = getNumber();
        if ( currentOp.equals("+") )
            numberStack.push( left + right );
        else if ( currentOp.equals("-") )
            numberStack.push( left - right );
        else if ( currentOp.equals("*") )
            numberStack.push( left * right );
        else if ( currentOp.equals("%") )
            numberStack.push( left % right );
        else if ( currentOp.equals("/") )
            numberStack.push( left / right );
        else if ( currentOp.equals("^") )
            numberStack.push( Math.pow(left , right ) );
        else
            System.out.println("Unknow Operator");
    }
}