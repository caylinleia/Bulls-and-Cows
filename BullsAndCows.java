import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

/**
 * Caylin Leia (Student ID: 261125917)
 */

public class BullsAndCows {
    /**
     * This program will have a four-digit number input
     * and will determine if the four-digit number input
     * is the correct guess for the secret number,
     * as well as indicating where the errors occurred
     */

    public static void main(String[] args) {
//        int[] x = {-2, 7};
//        int[] y = {9, 0, 2, 6};
//        int[] z = {};
//        contains(x, -3);
//        contains(y, 2);
//        contains(z, 5);
//
//        System.out.println(Arrays.toString(generateSecretDigits(123)));
//        System.out.println(Arrays.toString(generateSecretDigits(45)));
//        System.out.println(Arrays.toString(generateSecretDigits(987)));

//        int[] secret = {2, 0, 6, 9};
//        int[] guessOne = {9, 5, 6, 2};
//        int[] guessTwo = {1, 0, 6, 2};
//        int[] guessThree = {1, 2, 3, 4, 5, 6};
//        int[] guessFour = {1, 3, 4, 4, 0, 5};
//        System.out.println(getNumOfCows(secret, guessOne));
//        System.out.println(getNumOfCows(secret, guessTwo));
//        System.out.println(getNumOfCows(secret, guessThree));
//        System.out.println(getNumOfCows(secret, guessFour));

        //System.out.println(Arrays.toString(integerToArray(2345)));

        playBullsAndCows(1);
    }

    // Method to check if an element is contained in a given array
    public static boolean contains(int[] array, int element) {
        boolean success = false; // Verifying to determine if a specified integer is an element of a given array

        for (int i = 0; i < array.length; i++) { // Loop used to go through each digit within the given array
            if (array[i] == element) { // Verifying to determine if a given array contains an element
                success = true; // Returns value if a given array contains an element
            }
        }
        return success; // Returns value if an element is contained in a given array
    }

    // Method to generate the secret number
    public static int[] generateSecretDigits(int seed) {
        Random random = new Random(seed); // Class used to generate a stream of pseudorandom numbers within a seed
        int[] secretSequence = new int[4]; // Integer used to determine that the random number sequence contains only four digits

        for (int i = 0; i < secretSequence.length; i++) { // Loop used to go through every digit within the secret number
            int randomNumber = random.nextInt(10); // Integer used to ensure that the random number contains numbers less than 10
            if (!contains(secretSequence, randomNumber)) { // Verifying to determine if the element contained in a given array contains four unique and randomized digits within the secret number
                secretSequence[i] = randomNumber; // Determines that the secret number is a randomly generated number
            } else {
                secretSequence[i] = random.nextInt(10); // Determines that the secret number has a unique digit within the secret number that is less than 10
            }
        }
        return secretSequence; // Returns value once the random, secret digit is created
    }

    // Method to get the number of bulls in a guess
    public static int getNumOfBulls(int[] secretArray, int[] guessArray) {
        int count = 0; // Integer used to determine the occurrence of each digit within the inputted number

        for (int i = 0; i < secretArray.length; i++) { // Loop used to go through each digit within the inputted number
            try { // Defining any errors within the inputted number
                if (secretArray[i] == guessArray[i]) { // Verifying to determine if the inputted number contains any digits that are also within the secret number
                    count++; // Post increment for count if the user's inputted number contains any digits that are also found in the secret number
                }
            } catch (ArrayIndexOutOfBoundsException e) { // Defining any executions within the inputted number
                System.out.println("Error!" + e +"\nTry again!\n"); // Prints a message containing the reasoning of error
            }
        }
        return count; // Returns value if the user receives a bull
    }

    // Method to get the number of cows in a guess
    public static int getNumOfCows(int[] secretArray, int[] guessArray) {
        int count = 0; // Integer used to determine the occurrence of each digit within the inputted number

        for (int i = 0; i < secretArray.length; i++) { // Loop used to go through each digit within the inputted number
            if (contains(guessArray, secretArray[i])) { // Verifying to determine if the inputted number contained any digits that matched the secret number
                count++; // Post increment for count if the user's inputted number contains a digit that matched with the secret number
            }
        }
        return count - getNumOfBulls(secretArray, guessArray); // Returns value if the user receives a cow without double counting the number of bulls
    }

    // Method to simulate a game
    public static void playBullsAndCows(int seed) {
        Scanner scan = new Scanner(System.in); // Scanner used to obtain the input of a number used for guessing
        int guessNumber = 1; // Integer used to track how many guesses or rounds the user has completed
        boolean playAgain = true; // Verifying to determine if the user wants to continue playing
        int[] secretArray = generateSecretDigits(seed); // Integer used to determine the secret number by generating an array of secret digits
        // System.out.println(Arrays.toString(secretArray)); // Used to display the secret digits for testing purposes

        System.out.println("Welcome to Bulls and Cows! Have fun!\n"); // Prints a message to welcome the user to the game

        while (playAgain) { // Loop used for the user to continuously interact with the game
            System.out.println("Guess# " + guessNumber + ", Enter a four-digit number, each digit should be unique: "); // Prints a prompt for the user to enter a four-digit number, all digits being unique
            int guess = scan.nextInt(); // Integer used to scan each digit in the inputted number
            guessNumber++; // Post increment for count once the user has made their guess to determine the next move of the game

            if (guess < 0 || guess > 9999) { // Verifying if the inputted number contains digits that are less than 0 or greater than 9
                System.out.println("Invalid input: Please enter a four-digit number.\n" + "Try again!\n"); // Prints a message to notify the user that their input is invalid
                if (guessNumber > 5) { // Verifying if the number of guesses is more than five attempts
                    System.out.println("Would you like to exit the game? (y/n)"); // Prints a prompt for the user to decide and determine if they would like to exit the game
                    String userResponse = scan.next(); // String used for the user to input a response to the message
                    if (userResponse.equals("y")) { // Verifying if the user has decided to exit the game
                        System.out.println("Thank you for playing Bulls and Cows!"); // Prints a message to thank the user for playing the game
                        playAgain = false; // Returns value if the user would like to exit the game
                        break; // Exits the game
                    }
                }
            }

            try { // Defining any length errors in the user's inputted number
                if (integerToArray(guess).length != 4) { // Verifying if the inputted number is not a four-digit number
                    throw new IllegalArgumentException("Your guess was not a four-digit number.\n" + "Try again!\n"); // Prints a message indicating that the inputted number is an incorrect length
                } else {
                    System.out.println("Number of Bulls: " + getNumOfBulls(secretArray, integerToArray(guess))); // Prints a message displaying the number of bulls the user has obtained
                    System.out.println("Number of Cows: " + getNumOfCows(secretArray, integerToArray(guess)) + "\n"); // Prints a message displaying the number of cows the user has obtained
                }
            } catch (IllegalArgumentException e) { // Defining any executions if any errors occurred with the length of the inputted number
                System.out.println("Error! " + e); // Prints an error message to notify the player that their inputted number contained more than four digits
            }

            try { // Defining any repeated digit errors in the user's inputted number
                if (hasRepeatDigits(integerToArray(guess))) { // Verifying if the inputted number contains any repeated digits
                    throw new IllegalArgumentException("Your guess contained repeating digits.\n" + "Try again!\n"); // Prints an error message to notify the player that their inputted number contained repeating digits
                }
            } catch (IllegalArgumentException e) { // Defining any executions if any errors occurred with the repeating digits of the inputted number
                System.out.println("Error! " + e); // Prints an error message to notify  the player that their inputted number contained repeating digits
            }

            try { // Defining any successful attempts in the user's inputted number
                if (getNumOfBulls(secretArray, integerToArray(guess)) == 4) { // Verifying if the inputted number matches with the secret number
                    System.out.println("Congratulations, you won!"); // Prints a congratulatory message to the user for winning the game
                    System.out.println("Total Attempts: " + (guessNumber - 1)); // Prints a message to let the user know how many attempts they made to win the game
                    playAgain = false; // Returns value once the game is finished for the user to exit the game or replay
                    break; // Ends the game
                }
            } catch (IllegalArgumentException e) { // Defining any executions if the inputted number contains any illegal or unsuitable arguments
                System.out.println(e); // Prints a message containing the reasoning of error
            }

        }
    }

    // Method to convert an integer of digits into an array of digits
    public static int[] integerToArray(int input) {
        String temp = Integer.toString(input); // String used for the user to input a number to guess
        int[] numbers = new int[temp.length()]; // Integer used to convert an integer of digits

        for (int i = 0; i < temp.length(); i++) { // Loop used to go through every digit in the inputted number
            numbers[i] = temp.charAt(i) - '0'; // Verifying to determine if the digits in the inputted number are converted into a String
        }
        return numbers; // Returns value if the digits are converted into a String
    }

    // Method to check if an input contains any repeated digits
    public static boolean hasRepeatDigits(int[] input) {
        boolean result = false; // Verifying to determine if the inputted number has any repeated digits
        for (int i = 0; i < input.length; i++) { // Loop used to go through every digit in within the inputted number to check for any repeated digits for the first time
            for (int j = i + 1; j < input.length; j++) { // Loop used to go through every digit within the inputted number to check for any repeated digits for the second time
                if (input[i] == input[j]) { // Verifying to determine if there are any repeated digits within the inputted number
                    result = true; // Returns value if the inputted number contains any repeated digits
                }
            }
        }
        return result; // Returns result if the inputted number has any repeated digits
    }
}
