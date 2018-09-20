/**
 * RegExTest.java
 *
 * Version :
 *          1.0
 * Revisions :
 *          1.0
 */

import java.util.Scanner;
import java.io.File;
import java.util.regex.Pattern;

/**
 *
 * This program reads a file and for every line read, uses the Pattern class to check
 * whether the string obtained has character 'a', has a palindrome anchored at start
 * and end of line, contains a palindrome of length 2, contains a palindrome of
 * length 3, has atleast one 'a', consists of only 'a's or 'b's, 'a's and 'b's are
 * not part of it, it is a '.', it includes a '.'
 *
 * @author Ketan Balbhim Kokane
 * @author Ameya Deepak Nagnur
 */

class RegExTest {

    /**
     * The main Program
     *
     * @params  args     command Line arguments(used to get name of file)
     */

    public static void main( String args[] ) {
        try {
            Scanner scanner = new Scanner(System.in);
            String filename = "regExPatternFiles.txt";//scanner.next();

            scanner = new Scanner(new FileReader(new File("").getCanonicalPath() + "/"+fileName));
            // Create a File object using file name stored in String filename
            File file = new File(filename);

            // scanner created using file so that it can read the file
            Scanner scanner = new Scanner(file);
            String fileLine;

            // The loop runs till there is a new line in the file
            while(scanner.hasNextLine()) {
                // Reads the next line in the file
                fileLine = scanner.nextLine();

                // a is part of the word.
                hasA(fileLine);

                // Has palindrome anchored beginning and end of line.
                hasPalinAnchor(fileLine);

                // Consists of palindrome of length 2.
                wordHasPalin(fileLine, 2);

                // Consists of pallindrome of length 3.
                wordHasPalin(fileLine, 3);

                // Has atleast one a.
                wordHasAtleast1a(fileLine);

                // Consists only of a's or b's.
                wordHasOnlyaOrb(fileLine);

                // Does not have any a's or b's.
                wordHasNoaOrb(fileLine);

                // Is a '.'
                wordIsADot(fileLine);

                // Has a '.'
                wordHasADot(fileLine);
            }
        }
        catch (Exception exc)
        {
            // The try catch block is used to catch an exception that might be thrown
            // because of the use of the File object.
            // If the exception is caught then we print it
            System.out.println(exc.toString());
        }
    }

    /**
     * Check if the string word has the character 'a' in it.
     *
     * @param   word             String to be tested
     */

    public static void hasA(String word) {
        // The regular expression looks for any 'a' in the string
        // with any or no characters before or after it
        if(Pattern.matches(".*a.*", word)) {
            System.out.print("String has a : ");
            System.out.println(word);
        }
    }

    /**
     * Check if the string word has a palindrome anchored at the start and
     * end of it.
     *
     * @param   word             String to be tested
     */
    public static void hasPalinAnchor(String word) {
        boolean startAnchor = false;
        boolean endAnchor = false;

        // For finding a palindrome anchored at start

        int anchorIndex = 0;
        int nextIndex = 0;

        nextIndex = word.indexOf(word.charAt(anchorIndex), anchorIndex + 1);

        /**
         * This loop finds all occurences of character at anchorIndex anywhere in the
         * string at an index greater than anchorIndex and sends the substring from
         * anchorIndex to index of new occurence found to a function to check if
         * it is a palindrome
          */
        while(nextIndex != -1) {
            int result = IsPalin(word.substring(anchorIndex, nextIndex + 1));
            if(result == 0) {
                // If result = 0 then the substring passed is a palindrome
                // and hence we have found a palin anchored at start
                startAnchor = true;
                break;
            }
            // We find new occurence of char at anchorIndex at any index greater than
            // index of last occurence
            nextIndex = word.indexOf(word.charAt(anchorIndex), nextIndex + 1);
        }

        // For finding a palindrome anchored at the end
        anchorIndex = word.length() - 1;

        nextIndex = word.indexOf(word.charAt(anchorIndex));

        /**
        * This loop finds all occurences of character at anchorIndex anywhere in the
        * string at an index != anchorIndex and sends the substring from
        * index of new occurence found to anchorIndex to a function to check if
        * it is a palindrome
        */
        // Here we find all occurences from the start hence we will surely find one
        // occurence of char at anchorIndex which is anchorIndex = word.length - 1,
        // hence we run the loop till the next occurence is anchorIndex
        while(nextIndex != anchorIndex) {
            int result = IsPalin(word.substring(nextIndex, anchorIndex + 1));
            if(result == 0) {
                // If result = 0 then the substring passed is a palindrome
                // and hence we have found a palin anchored at the end
                endAnchor = true;
                break;
            }
            // We find new occurence of char at anchorIndex at any index greater than
            // last occurence index
            nextIndex = word.indexOf(word.charAt(anchorIndex), nextIndex + 1);
        }

        // if startAnchor and endAnchor are true then we have found a palin anchored at
        // the start and one anchored at the end which is what we were looking for
        if(startAnchor && endAnchor) {
            System.out.print("String has palindrome anchored start and end of line : ");
            System.out.println(word);
        }
    }

    /**
     * Check if the string word is a palindrome.
     *
     * @param   word             String to be tested
     *
     * @return                   0 or -1 that is result of check for that recursion
     */

    public static int IsPalin(String word) {
        // The regular expression checks if the string has the same
        // first and last character with any or no characters in
        // between
        String regEx = word.charAt(0) + ".*" + word.charAt(0);
        if (Pattern.matches(regEx, word)) {
            // If true then word has the same first and last character.

            /**
             *
             * If length is 2 or 3 then word is two same characters
             * with one or no characters in between
             * We reach next condition as true if the word directly
             * passed had length 2 or 3
             * OR if we checked all substrings till here and they all had
             * same first and last character
             * In both cases we found a palindrome so return 0
             *
             */

            if(word.length() == 2 || word.length() == 3) {
                return 0;
            }
            else {
                // Pass the substring between first and last character and call
                // function recursively
                int result = IsPalin(word.substring(1, word.length() - 1));
                return result;
            }
        }
        else {
            // If we get a string with different first and last characters then
            // return -1 since the initial string cannot be a palindrome.
            return -1;
        }
    }


    /**
     * Check if the string word has a palindrome with number
     * of characters = length in it.
     *
     * @param   word             String to be tested
     * @param   length           Length of palindrome to be found
     */

    public static void wordHasPalin(String word, int length) {
        int charIndex = 0;
        // Atleast 2 characters for length of 2 and atleast 3 chars for length 3
        if(word.length() >= length) {
            // For length = 2, charIndex goes till word.length - 2(inclusive) which means one character after it.
            // Similarly till word.length - 3 for length = 3, so two characters after it
            while (charIndex <= word.length() - length) {
                // The loop runs for all characters in the string word till there are (length - 1) no. of chars
                // after it in the string.
                String regEx = "";
                if(length == 2) {
                    // Regular expression looks for the current character back to back anywhere in the string
                    regEx = ".*" + word.charAt(charIndex) + word.charAt(charIndex) + ".*";
                }
                else if(length == 3) {
                    // Regular expression looks for the current character in the string and then
                    // repeated after exactly one character in the string.
                    regEx = ".*" + word.charAt(charIndex) + "." + word.charAt(charIndex) + ".*";
                }
                else {
                    break;
                }
                if (Pattern.matches(regEx, word)) {
                    System.out.print("String has a palindrome of length " + length + " : ");
                    System.out.println(word);
                    break;
                }
                charIndex ++;
            }
        }
    }

    /**
     * Check if the string word has atleast one 'a' in it.
     *
     * @param   word             String to be tested
     */

    public static void wordHasAtleast1a(String word) {
        // The regular expression looks for any 'a' in the string
        // with any or no characters before or after it
        if(Pattern.matches(".*a.*", word)) {
            System.out.print("String has atleast one a : ");
            System.out.println(word);
        }
    }

    /**
     * Check if the string word has only 'a's and 'b's in it.
     *
     * @param   word             String to be tested
     */

    public static void wordHasOnlyaOrb(String word) {
        // The regular expression looks for any combination of 'a'
        // and 'b' in the string with atleast one 'a' or 'b' in it
        if(Pattern.matches("[ab]+", word)) {
            System.out.print("String has only a's or b's : ");
            System.out.println(word);
        }
    }

    /**
     * Check if the string word does not have the characters 'a' and 'b'
     * in it.
     *
     * @param   word             String to be tested
     */

    public static void wordHasNoaOrb(String word) {
        // The regular expression looks for any combination of 'a'
        // and 'b' in the string with atleast one 'a' or 'b' in it
        // and any or no characters before or after it
        // If that is not true then it has no a's or b's
        if(!(Pattern.matches(".*[ab]+.*", word))) {
            System.out.print("String has no a's or b's : ");
            System.out.println(word);
        }
    }

    /**
     * Check if the string word is a '.'
     *
     * @param   word             String to be tested
     */

    public static void wordIsADot(String word) {
        // The regular expression checks if the string word
        // is a '.'
        if(Pattern.matches("[.]", word)) {
            System.out.print("String is a '.' : ");
            System.out.println(word);
        }
    }

    /**
     * Check if the string word has the character '.' in it.
     *
     * @param   word             String to be tested
     */

    public static void wordHasADot(String word) {
        // The regular expression looks for a '.' with
        // any or no characters before or after it
        if(Pattern.matches(".*[.].*", word)) {
            System.out.print("String has a '.' : ");
            System.out.println(word);
        }
    }

}
