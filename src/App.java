import java.text.MessageFormat;
import java.util.Scanner;

public class App {
    static String[][] board = {
        {"-", "-", "-"},
        {"-", "-", "-"},
        {"-", "-", "-"}
    };
    public static void main(String[] args) throws Exception {
        
        /*
            make a board
        */
        Scanner input = new Scanner(System.in);
        while (true) {

            redisplay();
            boolean isWon = false;
            boolean turnOfPlayer1 = true;
            String symbol, place;
            for (int move = 1; move <= 9; move++) {
                System.out.println("Moves remaining: " + (10 - move));
                System.out.println("\n!!PLAYER " + (turnOfPlayer1 ? "1" : "2") + " PLACING!!\n");
                symbol = turnOfPlayer1 ? "X" : "O";
                System.out.println("Reffer the number of placing above!");
                System.out.print("Place \"" + symbol + "\" at number: ");
                place = input.nextLine();
                turnOfPlayer1 = !turnOfPlayer1;
                if (isOccupied(place)) {
                    redisplay();
                    continue;
                }
                placeASymbol(place, symbol);
                redisplay();
                if (gameIsFinished("X")) {
                    System.out.println("\nPLAYER " + (!turnOfPlayer1 ? "1" : "2") + " Won!\n");
                    System.out.println("X symbol WON! \nX symbol WON! \nX symbol WON! \n");
                    isWon = !isWon;
                    break;
                }
                if (gameIsFinished("O")) {
                    System.out.println("\nPLAYER " + (!turnOfPlayer1 ? "1" : "2") + " Won!\n");
                    System.out.println("O symbol WON! \nO symbol WON! \nO symbol WON! \n");
                    isWon = !isWon;
                    break;
                }
            }
            resetBoard();

            System.out.println(isWon ? "Congrats hehe" : "Game over Mga bobo!\nPress spacebar and try again!");
            input.nextLine();
        }
    }
    static void resetBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = "-";
            }
        }
    }
    static void redisplay() {
        clearScreen();
            System.out.println("  " + 1 + "  |  " + 2 + "  |  " + 3);
            System.out.println("-----|-----|-----");
            System.out.println("  " + 4 + "  |  " + 5 + "  |  " + 6);
            System.out.println("-----|-----|-----");
            System.out.println("  " + 7 + "  |  " + 8 + "  |  " + 9);
            System.out.println("=============================");

            updateBoard();
            System.out.println("=============================");
    }
    static void updateBoard() {

        System.out.println("  " +board[0][0] + "  |  " + board[0][1] + "  |  " + board[0][2]);
        System.out.println("-----|-----|-----");
        System.out.println("  " +board[1][0] + "  |  " + board[1][1] + "  |  " + board[1][2]);
        System.out.println("-----|-----|-----");
        System.out.println("  " +board[2][0] + "  |  " + board[2][1] + "  |  " + board[2][2]);
    }

    static boolean gameIsFinished(String ASymbol) {
        /*
            winning combinations:
            00 && 01 && 02 = X
            10 && 11 && 12 = X
            20 && 21 && 22 = X
            ||
            00 && 10 && 20 = X
            01 && 11 && 21 = X
            02 && 12 && 22 = X
            ||
            00 && 11 && 22 = X
            20 && 11 && 02 = X
        */
        if (straightRow(ASymbol) || straightCol(ASymbol) || straightDiagonal(ASymbol)) return true;
        return false;
    }

    static boolean straightRow(String ASymbol) {
        if (board[0][0] == ASymbol && board[0][1] == ASymbol && board[0][2] == ASymbol) return true;
        if (board[1][0] == ASymbol && board[1][1] == ASymbol && board[1][2] == ASymbol) return true;
        if (board[2][0] == ASymbol && board[2][1] == ASymbol && board[2][2] == ASymbol) return true;
        return false;
    }
    static boolean straightCol(String ASymbol) {
        if (board[0][0] == ASymbol && board[1][0] == ASymbol && board[2][0] == ASymbol) return true;
        if (board[0][1] == ASymbol && board[1][1] == ASymbol && board[2][1] == ASymbol) return true;
        if (board[0][2] == ASymbol && board[1][2] == ASymbol && board[2][2] == ASymbol) return true;
        return false;
    }
    static boolean straightDiagonal(String ASymbol) {
        if (board[0][0] == ASymbol && board[1][1] == ASymbol && board[2][2] == ASymbol) return true;
        if (board[2][0] == ASymbol && board[1][1] == ASymbol && board[0][2] == ASymbol) return true;
        return false;
    }

    static void placeASymbol(String APlace, String ASymbol) {
        switch (APlace) {
            case "1":
            case "2":
            case "3":
                board[0][Integer.parseInt(APlace) - 1] = ASymbol;
                break;
            case "4":
            case "5":
            case "6":
                board[1][Integer.parseInt(APlace) - 4] = ASymbol;
                break;
            case "7":
            case "8":
            case "9":
                board[2][Integer.parseInt(APlace) - 7] = ASymbol;
                break;
        }
    }

    static boolean isOccupied(String APlace) {

        if (APlace.length() == 1) {
            switch (APlace) {
                case "1":
                    if (board[0][0] == "-") return false; break;
                case "2":
                    if (board[0][1] == "-") return false;break;
                case "3":
                    if (board[0][2] == "-") return false;break;
                case "4":
                    if (board[1][0] == "-") return false;break;
                case "5":
                    if (board[1][1] == "-") return false;break;
                case "6":
                    if (board[1][2] == "-") return false;break;
                case "7":
                    if (board[2][0] == "-") return false;break;
                case "8":
                    if (board[2][1] == "-") return false;break;
                case "9":
                    if (board[2][2] == "-") return false;break;
                    default:
                    return true;
            }
        }
        return true;
    }
    public static void clearScreen() {  

        System.out.print("\033[H\033[2J");  
     
        System.out.flush();  
     
    }
}