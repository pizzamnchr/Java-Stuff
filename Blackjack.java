import java.util.Scanner;

class Blackjack {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        P1Random rng = new P1Random();

        // Game
        int gameNumber = 1;
        int choice;
        boolean gameStatus = true;

        // Statistics
        int totalGames = 0, yourWins = 0, dealerWins = 0, totalTies = 0;
        double yourPercentage;

        // You
        int yourCard;
        int yourHand = 0;

        // Dealer
        int dealerHand;

        // While loop to keep the game running till you enter 4
        while(true) {
            if(gameStatus) {
                System.out.println("START GAME #" + gameNumber);
                gameNumber++;
                gameStatus = false;
                yourHand = 0;

                // Initial card and hand
                yourCard = rng.nextInt(13)+1;
                yourHand += cardCheck(yourCard);
                System.out.println("Your hand is: " + yourHand);
            }

            // Print menu and take in input
            System.out.println("\n1. Get another card\n2. Hold hand\n3. Print statistics\n4. Exit");
            System.out.print("\nChoose an option: ");
            choice = scnr.nextInt();

            // Switch statement that depends on what you pick from the menu
            switch(choice) {

                // You get a new card and it checks your hand to determine if you win/lose
                case 1:
                    yourCard = rng.nextInt(13)+1;
                    yourHand += cardCheck(yourCard);

                    System.out.println("Your hand is: " + yourHand);

                    // You win if your hand equals 21
                    if(yourHand == 21) {
                        System.out.println("\nBLACKJACK! You win!\n");
                        gameStatus = true;
                        totalGames++;
                        yourWins++;
                    }
                    // You lose if your hand is greater than 21
                    else if(yourHand > 21){
                        System.out.println("\nYou exceeded 21! You lose.\n");
                        gameStatus = true;
                        totalGames++;
                        dealerWins++;
                    }
                    break;

                // You hold and the dealer is dealt their hand
                case 2:
                    dealerHand = rng.nextInt(11)+16;
                    System.out.println("\nDealer's hand: " + dealerHand);
                    System.out.println("Your hand is: " + yourHand);

                    // Compares your hand and dealer's hand to determine the winner
                    // If dealer's hand is greater than 21 or your hand is greater than the dealer's hand, you win
                    if(dealerHand > 21 || yourHand > dealerHand) {
                        System.out.println("\nYou win!\n");
                        gameStatus = true;
                        totalGames++;
                        yourWins++;
                    }
                    // If your hand is less than dealer's hand, you lose
                    else if(yourHand < dealerHand) {
                        System.out.println("\nDealer wins!\n");
                        gameStatus = true;
                        totalGames++;
                        dealerWins++;
                    }
                    // Else, it's a tie and no one wins
                    else {
                        System.out.println("\nIt's a tie! No one wins!\n");
                        gameStatus = true;
                        totalGames++;
                        totalTies++;
                    }
                    break;
                // Prints out the statistics
                case 3:
                    System.out.println("\nNumber of Player wins: " + yourWins);
                    System.out.println("Number of Dealer wins: " + dealerWins);
                    System.out.println("Number of tie games: " + totalTies);
                    System.out.println("Total # of games played is: " + totalGames);
                    yourPercentage = 1.0 * yourWins / totalGames;
                    System.out.printf("Percentage of Player wins: %.1f%%\n", yourPercentage * 100);
                    break;
                // Ends the program
                case 4:
                    System.exit(0);
                    break;
                // When the player types in an invalid input
                default:
                    System.out.println("\nInvalid input!\nPlease enter an integer value between 1 and 4.");
                    break;
            }
        }
    }

    // Method that checks if your card is a special like an Ace, Jack, Queen, or King
    public static int cardCheck(int yourCard) {
        if (yourCard == 1) {
            System.out.println("\nYour card is a ACE!");
            return 1;
        }
        if (yourCard < 11) {
            System.out.println("\nYour card is a " + yourCard + "!");
            return yourCard;
        }
        if (yourCard == 11) {
            System.out.println("\nYour card is a JACK!");
        }
        if (yourCard == 12) {
            System.out.println("\nYour card is a QUEEN!");
        }
        if (yourCard == 13) {
            System.out.println("\nYour card is a KING!");
        }
        return 10;
    }
}


