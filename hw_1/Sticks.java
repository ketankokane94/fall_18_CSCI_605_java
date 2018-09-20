/**
 * Sticks.java
 * 
 * Version :
 *          1.0
 * Revisions : 
 *          1.0
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
      //Fixed values to compare with returned result of recursive function
      public static int LengthFound = 0, LengthNotFound = -1;
      //Array of known sticks to be used
      public static int knownSticks [] = { 1, 5, 8, 12, 12, 35, 35, 35, 61 };
      //Array of stick lengths to be formed using knownSticks
      public static int [] unknownStickLengths = { 1, 6, 9, 24, 110, 111, 115, 62, 24, 202, 203, 204, 205 };
      //Variable to store sum of all known stick lengths
      public static int sumOfKnownSticks = 0;
      
      /**
       * The main Program
       *
       * @param  args     command Line arguments(ignored)
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
              
              //If function returns result as -1, no combination works and hence display No
              if(findStickLengthFromKnowSticks(knownSticks, unknownLength, new int [knownSticks.length]) == LengthNotFound)
              {
                  System.out.println(unknownLength + " inch:\t No;");
              }
              
          }
      }
  
      /**
       * Starting with the last element of the array, call the function recursively for the next element in
       * the array that is not larger than the remaining stick length.
       *
       * @param   knownSticks             array of known sticks set to be used to form required length
       * @param   stickLengthToMatch      length of stick to be formed
       * @param   usedSticks              array showing the used status of every known stick(0 or 1)
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
              
              if(stickLengthToMatch > knownSticks[index] && usedSticks[index]!=1)
              {
                  remainingStickLength = stickLengthToMatch - knownSticks[index];
                  usedSticks[index] = 1;
                  //Recursive call to the function
                  stickLengthFound = findStickLengthFromKnowSticks(knownSticks,remainingStickLength,usedSticks);
                  if(stickLengthFound == LengthFound)// if true, then we have got the combination required
                      break;
                  if (stickLengthFound == LengthNotFound)//if true, current combination failed
                  {
                      //set current stick length to unused since it did not give the required length sum
                      //and continue to next iteration of the loop to try next stick length
                      usedSticks[index]= 0;
                      continue;
                  }
  
              }
              else if(stickLengthToMatch == knownSticks[index]  && usedSticks[index]!=1)
              {//the current stick can be used to exactly make up the remaining stick length
                  usedSticks[index]=1;
                  int usedSticksSum = 0;
  
                  //Print result in required format Eg. 6 inch: yes; used stickLengths = 1, 5 inch
                  for(int usedSticksIndex = 0; usedSticksIndex < usedSticks.length; usedSticksIndex++){
                      if(usedSticks[usedSticksIndex] == 1)
                      {
                          //Sum of stick length combination found
                          usedSticksSum+= knownSticks[usedSticksIndex];
                      }
                  }
                  
                  System.out.print(usedSticksSum + " inch:\t yes; used stickLengths = " );
                  
                  int usedSticksSum1 = -1;
                  
                  for(int usedSticksIndex = 0; usedSticksIndex < usedSticks.length; usedSticksIndex++){
                      usedSticksSum1++;
                      if(usedSticks[usedSticksIndex] == 1)
                      {
                          if(usedSticksSum1 > 0)
                              System.out.print(", ");
                          System.out.print(knownSticks[usedSticksIndex]);
  
                      }
                  }
                  
                  System.out.print(" inch");
                  
                  System.out.println();
                  
                  //return 0 since combination is complete.
                  return LengthFound;
              }
              
              /**
               *
               * when the entire array of known stick lengths has been traversed but the remaining
               * stick length is still not zero means you have exhausted all the options and the
               * subset of sticks for a given number cannot be found
               *
               */
              if(remainingStickLength != 0 && index ==0){
                  return LengthNotFound;
              }
          }
          
          //return stickLengthFound which is 0 if combination found and -1 if current combination failed which
          //tells the calling function to try the next number.
          return stickLengthFound;
      }
  
  }
  