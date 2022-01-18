import java.util.*;
public class Pig {

    public static void main(String[] args) {

        System.out.println("The Game of Pig");
        System.out.println("--------------------------------------");
        System.out.println("1. Human vs. Human");
        System.out.println("2. Human vs. Computer");
        System.out.println("3. Computer vs. Computer");
        System.out.println("");
        System.out.print("What kind of game do you want to play? ");
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        System.out.println("");
        if (choice == 1) {
            playGame(true, true);
        }
        else if (choice == 2) {
            playGame(true, false);
        }
        else if (choice == 3) {
            playGame(false, false);
        }
    }
    public static void playGame( boolean player1, boolean player2 ) {
        int player1Score = 0;
        int player2Score = 0;
        int counter = 0;
        while(player1Score < 100 && player2Score < 100) {
            System.out.println("Player 1 Score: " + player1Score);
            System.out.println("Player 2 Score: " + player2Score);
            // Code to switch between Player 1 and Player 2 turns.
            if (counter % 2 == 0) {
                // Variable to assign player's score after the playTurn method has run.
                player1Score = player1Score + playTurn(player1,1,player1Score);
            }
            else {
                player2Score = player2Score + playTurn(player2,2,player2Score);
            }
            counter += 1;
        }
        // Outputs each player's score after a hold or a roll of 1.
        System.out.println("Player 1 Score: " + player1Score);
        System.out.println("Player 2 Score: " + player2Score);
        // once either player reaches 100 or more the program the loop stops and outputs a winner.
        if (player1Score > player2Score) {
            System.out.println("Player 1 Wins!");
        }
        else {
            System.out.println("Player 2 Wins!");
        }
    }

    public static int playTurn( boolean player, int number, int totalScore ) {
        // a variable to hold the decision to roll or hold. Roll = true, Hold = false.
        boolean decision = true;
        int dieCount = 0;
        int turnScore = 0;
        String identifier = "";
        if (player == true) {
            identifier = "(Human)";
        }
        else {
            identifier = "(Computer)";
        }
        if (number == 1) {
            System.out.println("");
            System.out.println("Player 1's Turn " + identifier);
        }
        else{
            System.out.println("");
            System.out.println("Player 2's Turn " + identifier);
        }
        // Loop through while the player continues to roll and not hold.
        while (decision == true) {
            // variable to hold the number of the die roll.
            dieCount = (int) (Math.random() * 6 + 1);
            if (dieCount == 1) {
                turnScore = 0;
                System.out.println("Rolled a " + dieCount);
                System.out.println("Turn Over");
                System.out.println("");
                return turnScore;
            }
            System.out.println("Rolled a " + dieCount);
            turnScore = turnScore + dieCount;
            System.out.println("Turn Score: " + turnScore);
            // Call the getDecsion method to see if the player wants to hold or roll.
            decision = getDecision(player, turnScore, totalScore);
        }

        System.out.println("Turn over");
        System.out.println("");

        return turnScore;

    }

    public static boolean getDecision( boolean player, int turnScore, int totalScore) {
        boolean decision = true;
        String choice = "";
        Scanner in = new Scanner(System.in);
        if (player == true) {
            System.out.print("Hold or Roll? (h or r) ");

            choice = in.next();


            if (choice.equals("r")) {
                System.out.println("Human player rolls");
                decision = true;
            }
            else {
                System.out.println("Human player holds");
                decision = false;
            }
        }
        // If the player is a computer and he has scored 20 or more in a turn he will hold.
        if (player == false) {
            if (turnScore >= 20 || turnScore+totalScore >=100) {
                System.out.println("Computer player holds");
                decision = false;
            }
            else {
                System.out.println("Computer player rolls");
            }
        }
        return decision;
    }
}
