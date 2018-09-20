/**
 * Sticks.java
 * 
 * Version :
 *          2.1
 * Revisions : 
 *          1.1
 */


 /**
  *
  * This program checks whether there exists any subset of given set of sticks whose length sums up to
  * a given stick length.
  *
  * @author Ketan Balbhim Kokane
  * @author Ameya Deepak Nagnur
  */

public class Sticks
{
    // Fixed values to compare with returned result of recursive function
    public static int LengthFound = 0, LengthNotFound = -1;
    // Counter for no. of used stick lengths at that moment
    public static int usedSticksCount = 0;
    // Flag for atleast one combination found
    public static int combinationFound = 0;
    // Variable for smallest length amongst all combinations
    public static int minCombLength = 0;
    // Array of known sticks to be used
    public static int knownSticks [] = { 1, 2, 3, 5, 8 };
    // Array of stick lengths to be formed using knownSticks
    public static int [] unknownStickLengths = { 1, 5, 6, 7, 8, 9 };
    // Final used status array
    public static int [] finalUsedArray = new int[knownSticks.length];
    // Variable to store sum of all known stick lengths
    public static int sumOfKnownSticks = 0;

    /**
     * The main Program
     *
     * @params  args     command Line arguments(ignored)
     */

    public static void main(String [] args){

        //Calculate sum of all known sticks
        for(int knownStickLength : knownSticks) {
            sumOfKnownSticks += knownStickLength;
        }

        for(int unknownLength : unknownStickLengths) {
            //If value is greater than sum of all known stick lengths then it is not possible
            if(unknownLength > sumOfKnownSticks)
            {
                System.out.println(unknownLength + " inch:\t No;");
                continue;
            }

            //Set to highest possible to avoid comparison issues
            minCombLength = knownSticks.length;

            combinationFound = 0;
            usedSticksCount = 0;

            //If function returns result as -1, no combination works and hence display No
            if(findStickLengthFromKnowSticks(knownSticks, unknownLength, new int [knownSticks.length]) == LengthNotFound)
            {
                System.out.println(unknownLength + " inch:\t No;");
            }
            else
            {
                int usedSticksSum = 0;
                //Print result in required format Eg. 6 inch: yes; used stickLengths = 1, 5 inch
                for(int usedSticksIndex = 0; usedSticksIndex < finalUsedArray.length; usedSticksIndex++) {
                    if(finalUsedArray[usedSticksIndex] == 1) {
                        //Sum of stick length combination found
                        usedSticksSum += knownSticks[usedSticksIndex];
                    }
                }
                //System.out.println(usedSticksSum);

                System.out.print(usedSticksSum + " inch:\t yes; used stickLengths = " );

                int usedSticksSum1 = -1;
                for(int usedSticksIndex = 0; usedSticksIndex < finalUsedArray.length; usedSticksIndex++){
                    usedSticksSum1++;
                    if(finalUsedArray[usedSticksIndex] == 1)
                    {
                        if(usedSticksSum1 > 0)
                            System.out.print(", ");
                        System.out.print(knownSticks[usedSticksIndex]);

                    }
                }

                System.out.print(" inch");

                System.out.println();

            }

        }
    }

    /**
     * Starting with the last element of the array, call the function recursively for the next element in
     * the array that is not larger than the remaining stick length.
     *
     * @params   knownSticks             array of known sticks set to be used to form required length
     * @param    stickLengthToMatch      length of stick to be formed
     * @params   usedSticks              array showing the used status of every known stick(0 or 1)
     */

    public static int findStickLengthFromKnowSticks(int knownSticks[], int stickLengthToMatch, int usedSticks[])
    {

        /**
         *
         * The variable stickLengthFound storing the result is assigned a random value in the beginning,
         * the only value that matters for the result is 0 and -1
         * which is assigned when subset is found
         * and not found respectively
         *
         */

        int remainingStickLength = stickLengthToMatch, stickLengthFound = 2;

        /**
         *
         * As the knownSticks array is sorted, it is traversed in reverse order to tackle the bigger
         * numbers first. Using this way we avoid computation of some combinations of sticks.
         * Can also be changed to random order to provide same time complexity to all the elements of the array
         *
         * An Array usedSticks is used to keep the track of the sticks that are already being used
         *
         */

        for(int index = knownSticks.length -1; index >= 0; index--)
        {
            /**
             *
             * If remaining stick length is greater than the length of stick under consideration
             * then that stick cannot be used in the combination to form initial stick length.
             *
             * Check if the stick length to match is more than the current stick length and also the
             * current stick should not be used already
             * Then call the function recursively to find the next stick length in the array for the
             * remaining stick length.
             *
             */

            if(stickLengthToMatch > knownSticks[index] && usedSticks[index]!=1) {
                remainingStickLength = stickLengthToMatch - knownSticks[index];
                usedSticks[index] = 1;
                usedSticksCount++;

                /**
                 *
                 * If the number of stick lengths used is equal to minimum length found so far then
                 * calling the function again is avoided since even if we find a combination, it's
                 * length will be more than current minimum
                 *
                 */
                if(usedSticksCount < minCombLength) {
                    // Recursive call to the function
                    stickLengthFound = findStickLengthFromKnowSticks(knownSticks, remainingStickLength, usedSticks);
                }

                /**
                 *
                 * We do not check the return value here since we have to find a combination irrespective
                 * of whether the used stick length gave a combination or not so that we check all
                 * possible combinations
                 *
                 */

                // Set current stick length to unused whether it gave a combination or not
                // and continue to next iteration of the loop to try next stick length and find a combination
                usedSticks[index]= 0;
                usedSticksCount--;
                continue;

            }
            else if(stickLengthToMatch == knownSticks[index]  && usedSticks[index]!=1) {//the current stick can be used to exactly make up the remaining stick length
                usedSticks[index]=1;// Since we have used this stick length
                usedSticksCount++;

                int lengthOfCombination = 0;// Counter for the length of the combination
                for(int usedSticksIndex = 0; usedSticksIndex < usedSticks.length; usedSticksIndex++) {

                    if(usedSticks[usedSticksIndex] == 1) {
                        // usedSticks array has 1 for every stick length used for obtained combination
                        lengthOfCombination++;
                    }
                }

                if(lengthOfCombination <= minCombLength)
                {
                    combinationFound = 1;
                    minCombLength = lengthOfCombination;
                    for(int finalArrayIndex = 0; finalArrayIndex < usedSticks.length; finalArrayIndex++) {
                        finalUsedArray[finalArrayIndex] = usedSticks[finalArrayIndex];
                    }
                }

                usedSticks[index] = 0;
                usedSticksCount--;

                //return 0 since combination is complete.
                return LengthFound;
            }

            /**
             *
             * When the entire array of known stick lengths has been traversed but the remaining
             * stick length is still not zero means you have exhausted all the options and the
             * subset of sticks for a given number cannot be found
             *
             */
            if(remainingStickLength != 0 && index ==0){
                return LengthNotFound;
            }
        }

        if(combinationFound == 0)
            stickLengthFound = -1;
        else
            stickLengthFound = 0;

        // return stickLengthFound which is 0 if there is atleast one combination found so far
        // and -1 if no combination found yet which is only checked in the main function.
        return stickLengthFound;
    }

}
