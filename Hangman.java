/**
* Gina Hall
* 10/17/13
*
* This is a little toy to let a person play a game of HangMan with the computer. 
* The player gets 5 lives. Every wrong answer will kill a life.
* The list of words comes from a word bank in a separate text file.
**/


import java.util.Scanner;

public class Hangman {

	public static void main(String[] args) {
		Scanner playerInput = new Scanner(System.in);
		int wrong = 5;								//player gets up to 5 wrong guesses
		int guessed = 0;
		boolean isPlaying = true;	

		String message = "Penelope is cute";

		String alphaPool = makeAlphaPool();
		
		intro(wrong);
		while (isPlaying) {
			//gat and hide the secret message; notify player
			String secret = makeSecret(message);
			System.out.println("Your secret message is: " + secret); 
			message(wrong, guessed);
			
			System.out.println("You can choose from these letters: " + alphaPool);
			isPlaying = askKeepPlaying(playerInput);
		}
	}

	//make the alphabet/pool of letters that the player starts with
	public static String makeAlphaPool() {
		String alpha = "";
		for (int i = 65; i < 91; i++) {
			alpha += (char)i;
			alpha += " ";
		}
		return alpha;
	}


	//hide the message fromthe player, retain all spaces
	public static String makeSecret(String message) {
		String secret = "";
		for (int i = 0; i < message.length(); i ++) {
			if (message.charAt(i) == ' ') {
				secret += " ";
			} else {
				secret += "-";
			}
		}
		return secret;
	}


	//ask player if they would like to play
	public static boolean askKeepPlaying(Scanner playerInput) {
		System.out.println("Would you like to play hangman with me? Y/y for yes, anything else for no.");
		String answer = playerInput.nextLine().substring(0,1);
		if (answer.toLowerCase().equals("y")) {
			System.out.println("Let's play.");
			return true;
		} else {
			System.out.println("I don't want to play with you, either.");
			return false;
		}
	}





	//message welcoming player to game + rules
	public static void intro(int wrong) {
		System.out.println("\n   Welcome to this game of Hangman! I will think of a word");
		System.out.println(" and you will have " + wrong + " chances to guess the correct letters");
		System.out.println(" in the word. The game will end when there are either no");
		System.out.println(" letters left to guess or you have run out of lives.");
		System.out.println("     This game is case insensitive and it is letters only.");
		System.out.println("\n Good Luck!\n ----------------------------------------------------");
	}

	public static void message(int wrong, int guessed) {
		System.out.println(" You have " + (wrong - guessed) + " guesses remaining. Choose wisely.\n");
	}



	
}








// public class Hangman {
//     public static final int WRONG = 5; 

//     public static void main(String[] args) {
//         Scanner input = new Scanner(System.in);
//         int wrongGuesses = 0;
//         boolean isPlaying = true;
        
//         intro();
//         while (isPlaying) {
//             // obtains new phrase from the PhraseBank and creates an encrypted version
//             PhraseBank phrases = new PhraseBank();
//             String originalPhrase = phrases.getNextPhrase();
//             String hiddenPhrase = hidePhrase(originalPhrase);
            
//             System.out.println("I am thinking of a Movie ...");
//             System.out.println();
//             wrongGuesses = roundsAvailable(hiddenPhrase, originalPhrase, input, wrongGuesses);
//             isPlaying = playAgain(input);
//             wrongGuesses = 0;
//         }
//     }
      
//     // show the intro to the program
//     public static void intro() {
//         System.out.println("This program plays the game of hangman.");
//         System.out.println("When the window opens select the file");
//         System.out.println("with the phrases you want to use.\n");
//         System.out.println("The computer will pick a random phrase.");
//         System.out.println("Enter capital letters as your guesses.");
//         System.out.println("After " + WRONG + " wrong guesses you lose.");
//     }
    
//     // hide the secret phrase and send it back to be used by other methods
//     public static String hidePhrase(String phrase) {
//         int length = phrase.length();    // start with the original phrase and determine number of letters
//         String hiddenPhrase = "";          // let hiddenPhrase be altered by the following loop
//         for (int i = 0; i < length; i++)  {     // for every letter in the originalPhrase
//             if (i <= length)  {        // do this for each letter until last letter
//                 //String letter = phrase.charAt(i);
//                 if (phrase.charAt(i) == '_') {
//                     hiddenPhrase += "_";       // if the letter is a space then "_"
//                 } else {
//                     hiddenPhrase += "*";         // if the letter is NOT a space then "*"
//                   }
//             }
//         }
//         return hiddenPhrase;
//     }
    
//     // shows the current hidden phrase for the round
//     public static String currentPhrase(String hiddenPhrase, String originalPhrase, String userLetter, String alphabet) {
//         String changePhrase = "";
        
//         // if user letter is not in the phrase- current hidden phrase remains the same
//         if (!originalPhrase.contains(userLetter)) {
//             //System.out.println("The current phrase is " + hiddenPhrase);
//             changePhrase = hiddenPhrase;
//         } else {
//             // otherwise current phrase is rebuilt to expose new userLetter
//             for (int i = 0; i < originalPhrase.length(); i++)  {     // for every letter in the phrase
//                 if (i <= hiddenPhrase.length())  {        // do this for each letter until last letter
//                     //check if the originalPhrase at index == userLetter
//                     if (originalPhrase.substring(i, i+1).equals(userLetter)) {
//                         changePhrase += (originalPhrase.substring(i, i+1));  // if letters match- swap
//                     } else {
//                         changePhrase += (hiddenPhrase.substring(i, i+1));    // if the letters don't match- insert from hiddenPhrase
//                       }
//                 }
//             }
//         }
//         return changePhrase;
//     }
    
//     // user chooses a letter, letter is checked to see if it is a real letter
//     public static String guessLetter (Scanner input, String alphaPool) {
//         System.out.print("Enter your next guess: ");
//         String letter= input.nextLine().substring(0,1);
                
//         while (!alphaPool.contains(letter)) {
//             System.out.println(letter + " is not a valid guess.");
//             System.out.print("Enter your next guess: ");
//             letter= input.nextLine().substring(0,1);
//         }
//         System.out.println();
//         return letter;
//     }
    
//     // displays the letters of the alphabet that the user has not yet used
//     public static String notGuessed (String alphaPool, String userLetter) {
//         String newAlphaPool = "";
//         String displayAlphaPool = "";
//         String alphaLetter = "";
        
//         System.out.println("The letters you have not guessed yet are:");
        
//         // if the pool contains the user letter...
//         if (alphaPool.contains(userLetter)) {
//             // update the pool of possible guesses
//             for (int i = 0; i < alphaPool.length(); i++) {          // for each letter in alphaPool
//                 // rebuild the pool without the user letter
//                 alphaLetter = (alphaPool.substring(i, i+1)); // each letter from alphaPool at i
//                 if (i <= alphaPool.length())  {                     // do this for each letter until last letter
//                     if (userLetter.equals(alphaLetter)) {           // if userLetter = alphaLetter
//                         newAlphaPool += "";                         // enter an empty
//                     } else {                                        // otherwise...
//                         newAlphaPool += alphaLetter;                // enter the original letter at that index
//                     }
//                 }
//             }
//         } else {  // otherwise rebuild using the same letters
//             for (int i = 0; i < alphaPool.length(); i++) {          // for each letter in alphaPool
//                 // rebuild the pool without the user letter
//                 alphaLetter = (alphaPool.substring(i, i+1)); // each letter from alphaPool at i
//                 if (i <= alphaPool.length())  {                     // do this for each letter until last letter
//                     newAlphaPool += alphaLetter;                    // enter the original letter at that index
//                 }
//             }
//         }
        
//         int newLength = newAlphaPool.length();
//         // rebuilds the pool of letters to display with a space between each letter
//         for (int i = 0; i < (newLength-1); i++) {
//             alphaLetter = (newAlphaPool.substring(i, i+1)); // each letter from newAlphaPool at i
//             displayAlphaPool += (alphaLetter + " ");                    // enter the letter at that index and then a " " space
//         }
        
//         displayAlphaPool += (newAlphaPool.substring(newLength-1));
//         System.out.println(displayAlphaPool);
//         return newAlphaPool;
//     }
    
//     //is the userLetter present in the phrase? 
//     public static int letterPresent(String userLetter, String originalPhrase) {
//         int wrongCount = 0;
//         System.out.println("You guessed " + userLetter + ".");
//         if (originalPhrase.contains(userLetter)) {                        // yes: update the hidden phrase
//             System.out.println("That is present in the secret phrase.");
//         } else {                                                  // no: increase the wrong
//             System.out.println("That is not present in the secret phrase.");
//             wrongCount += 1;
//         }
//         return wrongCount;
//     }
    
//     // display number of wrong guesses
//     public static void displayWrong(int wrongGuesses) {
//         System.out.println("You have made " + wrongGuesses + " wrong guesses.");
//         System.out.println(); 
//     }
    
//     // asks user if they would like to play again- returns either true or false
//     public static boolean playAgain (Scanner input) {
//         System.out.println("Do you want to play again?");
//         System.out.print("Enter 'Y' or 'y' to play again: ");
//         String letter= input.nextLine().substring(0,1);
        
//         if ((letter.equals("Y")) || (letter.equals("y"))) {
//             return true; 
//         } else {
//             return false; 
//         }
//     }
    
//     // are there still rounds available to play? wrong<WRONG && hiddenPhrase.contains("*");
//     public static int roundsAvailable(String hiddenPhrase, String originalPhrase, Scanner input, int wrongGuesses) {
//         String userLetter = "";
//         String alphaPool = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//         while ((wrongGuesses < WRONG) && (!hiddenPhrase.equals(originalPhrase))) {
//             System.out.println("The current phrase is " + hiddenPhrase);
//             alphaPool = notGuessed(alphaPool, userLetter);
//             userLetter = guessLetter(input, alphaPool);
//             hiddenPhrase = currentPhrase(hiddenPhrase, originalPhrase, userLetter, alphaPool);
//             wrongGuesses += letterPresent(userLetter, originalPhrase);
//             displayWrong(wrongGuesses);
//         } 
//         winOrLose(hiddenPhrase, originalPhrase);
//         return wrongGuesses;
//     }
    
//     // declares whether the player wins or loses
//     public static void winOrLose(String hiddenPhrase, String originalPhrase) {
//         if (!hiddenPhrase.contains("*")) {
//             System.out.println("The phrase is " + originalPhrase + ".");
//             System.out.println("YOU WIN!!!!");
//         } else {
//             System.out.println("You lose. The secret phrase was " + originalPhrase);
//         }
//         System.out.println();
//         }   
// }
