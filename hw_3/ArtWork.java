

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.File;

/**
 * this program is a game, where in a user has to guess a  given word, you get 9 attempts to guess the word
 * @author ameya nagnur
 * @author ketan kokane
 */

public class ArtWork {
    private int allowedAttempts;
    private String fileName ;
    private List<String> wordList ;
    private Vector<String> artWorkPicture;
    private int [] displayedArtWorkIndexes;

    /**
     * constructor to initialise attempts count and file name of the file to fetch words from
     * @param attemptsCount
     * @param fileName
     */
    public ArtWork(int attemptsCount, String fileName){
        allowedAttempts = attemptsCount;
        this.fileName = fileName;
        artWorkPicture = readArtWorkFile();

    }

    /**
     * reads the words from the file and stores it in a list
     * @return false if the file is not found, else return true
     */
    private boolean initialiseGame() {
        Scanner scanner = null;
        try {
            wordList = new ArrayList<>();
            scanner = new Scanner(new FileReader(new File("").getCanonicalPath() + "/"+fileName));
            while (scanner.hasNext()){
                wordList.add(scanner.next());
            }
        } catch (FileNotFoundException e) {
            System.out.println("please enter a correct file name");
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally{
        if(scanner != null){
            scanner.close();
            return  true;
        }
        else {
            return false;
        }
        }
    }

    /**
     * is the main driver function of the class, which reads a rondom word from the created wordList, displays to the user
     * and waits for the user's reply, once user types the guessed letter, checks if the correct char was entered or not.
     * also Initialises the Artwork that is to be displayed when the user enters the a letter
     */
    private void play() {
        String continueGame;
        do {
            String wordToBeDisplayed = chooseRandomWordFromWordList();
            System.out.println();
            initialiseArtWorkToBeDisplayed();
            continueGame = playGame(wordToBeDisplayed);
        }while(continueGame.equalsIgnoreCase("yes"));
    }

    /**
     * creates a array to keep track of rows of picture which are already shown
     */
    private void initialiseArtWorkToBeDisplayed() {
        displayedArtWorkIndexes = new int[artWorkPicture.size()];
    }

    /**
     *
     * @returns a random word from the generated wordlist read from the file
     */
    private String chooseRandomWordFromWordList() {
        return wordList.get(new Random().nextInt(wordList.size()));
    }

    /**
     * reads the artwork file and return array representation of it
     * @return
     */
    private Vector<String> readArtWorkFile() {
        Scanner scanner = null;
        Vector<String> artWork = new Vector<>();
        try {

            scanner = new Scanner(new FileReader(new File("").getCanonicalPath() + "/pic.txt"));
            while (scanner.hasNext()){
                artWork.add(scanner.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            if(scanner != null){
                scanner.close();
            }
            return artWork;
        }
    }


    /**
     * recieves the word on which the entire game is to be played
     * macthces the response given by the player and comapres it with the word
     * based on the match decide if the user selected word or no
     * @param wordToGuess
     * @return  yes if the user wants to play the game again
     */
    private String playGame(String wordToGuess) {
        Scanner scanner = new Scanner(System.in);
        int remainingGameAttempts = allowedAttempts;
        int [] guessedWordIndexes = new int[wordToGuess.length()];
        do {
            printGuessedString(wordToGuess, guessedWordIndexes);
            System.out.print(allowedAttempts-remainingGameAttempts + " :Guess a letter : ");
            char guessedChar = scanner.next().charAt(0);
            if(playerGuessedCorrectChar(guessedChar,wordToGuess,guessedWordIndexes)){
                if( playerGuessedEntireWord(guessedWordIndexes)){
                    break;
                }
            }
            else {
                remainingGameAttempts--;
            }
            displayArtWork(false);
        }while (remainingGameAttempts > 0);
        displayArtWork(true);
        System.out.println("The word was : " + wordToGuess);
        System.out.println("Do you want to continue (yes/no)?");
        return scanner.next();
    }

    /**
     * checks if the letter entered by the user is correct or not
     * if its correct finds all the instances of the letter in the word and displays it to the user
     * @param guessedChar
     * @param wordToGuess
     * @param guessedWordIndexes
     * @return
     */
    private boolean playerGuessedCorrectChar(char guessedChar,String wordToGuess,int [] guessedWordIndexes) {
        int index = wordToGuess.indexOf(guessedChar);
        boolean charGuessed = false;
        while (index != -1){
            charGuessed = true;
            guessedWordIndexes[index]=1;
            index = wordToGuess.indexOf(guessedChar,index+1);
        }
        return charGuessed;
    }

    /*
    checks if the player before all the attempts are done, has guessed the word or not
     */
    private boolean playerGuessedEntireWord(int[] guessedWord) {
        int guessedCounter = 0;
        for(int guessedIndex = 0; guessedIndex < guessedWord.length; guessedIndex++) {
            if(guessedWord[guessedIndex] == 1)
                guessedCounter++;
        }
        return (guessedCounter == guessedWord.length);
    }

    /**
     * helper function to print the word that is to be guessed, but if the user has already guessed letter of the words then that
     * letters needs to be displayed to the user
     * @param wordToBeDisplayed
     * @param guessedCharIndexes
     */
    public static void printGuessedString(String wordToBeDisplayed, int [] guessedCharIndexes)
    {
        StringBuilder stringBuilder = new StringBuilder();
        int charIndex = 0;
        while(charIndex < wordToBeDisplayed.length()) {
            if(guessedCharIndexes[charIndex] == 1) {
                stringBuilder.append(wordToBeDisplayed.charAt(charIndex) + " ");
            }
            else {
                stringBuilder.append("_ ");
            }
            charIndex++;
        }
        System.out.println(stringBuilder.toString());
    }

    /**
     * starts the game by telling where to find the file which contains the words
     * and also specifies the number of attempts that are allowed in the game
     * @param args
     * args[0] can contain the name of the file
     */
    public static void main( String args[] ) {
        try {
            String fileName ;
            if (args.length > 0){
                fileName = args[0];
            }
            else{
                fileName = "wordList.txt";
            }
            ArtWork artWork = new ArtWork(9,fileName);
            if(artWork.initialiseGame()){
                artWork.play();
            }

        }
        catch (Exception exc) {
            System.out.println(exc.toString());
        }
    }

    /**
     * heler function to display the art work to the user,
     * uses the array representation of the artwork and randomly shows the few lines of the image at each attempt
     * to keep the row shown at every instance, uses the array to store the row which are already shown
     * @param showEntirePicture
     */
    private void displayArtWork(boolean showEntirePicture) {

        Random random = new Random();
        int rowsOfArtWorkToShowOnEachAttempt = artWorkPicture.size() / allowedAttempts;
        while (rowsOfArtWorkToShowOnEachAttempt >= 0){
            int randomNumber = random.nextInt(artWorkPicture.size());
            displayedArtWorkIndexes[randomNumber] = 1;
            rowsOfArtWorkToShowOnEachAttempt --;
        }
        for (int index = 0; index < artWorkPicture.size(); index++) {
            if(displayedArtWorkIndexes[index]==1 || showEntirePicture){
                System.out.println(artWorkPicture.get(index));
            }
            else {
                System.out.println();
            }
        }
    }


}
