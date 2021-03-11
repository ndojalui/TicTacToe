import java.util.*;
/**
 * Author Luigj Ndoja
 */

public class TicTacToe {

    public static void main(String[] args) throws Exception {
        start();
    }

    /**
     * This is where the game runs
     */
    public static void start() {
        char[] board = new char[9];

        resetBoard(board);
        Scanner scanner = new Scanner(System.in); // Read: https://www.w3schools.com/java/java_user_input.asp

        // Setup the players and get their names
        System.out.println("Let's play Tic Tac Toe!");
        System.out.print("Player 1, what is your name? ");
        String p1 = scanner.nextLine();
        System.out.print("Player 2, what is your name? ");
        String p2 = scanner.nextLine();
        
        
        
        char winner = checkForWinner(board);
        while (winner == 'N' && !boardIsFull(board)) {

            // WRITE YOUR CODE HERE FOR THE GAME
            
            // Asks player 1 to choose a tile and records the input.
            System.out.print(p1 + ", it is your turn...");
            printOptions();
            System.out.print("Which tile would you like to select?");
            int tileInput = scanner.nextInt();          
            
            // Places an X where player 1 chose
            playerMove(board, tileInput, true);
            printBoard(board);
            winner = checkForWinner(board);

            // Asks player 2 to choose a tile and records the input.        
            System.out.print(p2 + ", it is your turn...");
            printOptions();
            System.out.print("Which tile would you like to select?");
            tileInput = scanner.nextInt();
            
            // Places an O where player 2 chose            
            playerMove(board, tileInput, false);
            printBoard(board);
            winner = checkForWinner(board);

        }

        // Congratulates the determined winner
        switch(winner){
            case 'O':
                System.out.println(p2 + " Is the winner!");
            case 'X':
                System.out.println(p1 + " Is the winner!");
            case 'C':
                System.out.println("Tie!");
        }


        // close the scanner
        scanner.close();
    }

    /**
     * Moves the game piece
     * @param board board char[]
     * @param input input int, the tile that the player chose
     * @param X X boolean, if true, player 1 is moving, if false, player 2 is moving
     */
    private static void playerMove(char[] board,int input, boolean X) {
        char gamePiece = 't';
        if (X) {
            gamePiece = 'X';
        }
        else{
            gamePiece = 'O';
        }

        board[input-1] = gamePiece;      

    }


    /**
     * Checks for the winner of the game
     * @param board board char[]
     * @return N - none, X - x wins, O - o wins, C - cats game
     */
    private static char checkForWinner(char[] board) {

        // Runs through each row and counts up each game piece in each row
        for(int i = 1; i <= 8; i+=3){
            int XhorizontalWinCounter = 0;
            int OhorizontalWinCounter = 0;
            for(int j = i; j <= i+2; j++ ){
                //System.out.print(j);
                switch(board[j-1]){
                    case 'X':
                        XhorizontalWinCounter++;
                    case 'O':
                        OhorizontalWinCounter++;
                }
            }
            //System.out.println(" ");

            if (XhorizontalWinCounter == 3){
                return('X');
            }
            if (OhorizontalWinCounter == 3){
                return('O');
            }
        }
        // runs through each collumn and counts up each game piece in each collumn 
        for(int i = 1; i <= 3; i++){
            int XverticalWinCounter = 0;
            int OverticalWinCounter = 0;
            for(int j = i; j <= i+6; j+=3 ){
                //System.out.print(j);
                switch(board[j-1]){
                    case 'X':
                        XverticalWinCounter++;
                    case 'O':
                        OverticalWinCounter++;
                }
            }
            //System.out.println(" ");

            if (XverticalWinCounter == 3){
                return('X');
            }
            if (OverticalWinCounter == 3){
                return('O');
            }
        }

        // runs through the diagonals and counts up each tile in both diagonals 
        int XdiagWinCounter = 0;
        int OdiagWinCounter = 0;
        for(int i = 1; i<10; i+=4){
            switch(board[i-1]){
                case 'X':
                    XdiagWinCounter++;
                case 'O':
                    OdiagWinCounter++;
            }

        }
        if (XdiagWinCounter == 3){
            return('X');
        }
        if (OdiagWinCounter == 3){
            return('O');
        }
        XdiagWinCounter = 0;
        OdiagWinCounter = 0;
        for(int i = 3; i<8; i+=2){
            //System.out.print(i);
            switch(board[i-1]){
                case 'X':
                    XdiagWinCounter++;
                case 'O':
                    OdiagWinCounter++;
            }

        }
        if (XdiagWinCounter == 3){
            return('X');
        }
        if (OdiagWinCounter == 3){
            return('O');
        }
        
        if (boardIsFull(board)){
            return('C');
        }

        return 'N';
    }

    /**
     * Checks to see if the board is full
     * use this to detirmine if it is a cats game
     * @param board board char[]
     * @return true if full
     */
    private static boolean boardIsFull(char[] board) {
        for (int i = 0; i < board.length; i++) {
            
            if (board[i] == ' ') {
                return false;
                
            }
        }
        return true;
    }

    /**
     * Prints the board to the console with the current
     * X's, O's, and untaken spots
     * @param board board char[][]
     */
    private static void printBoard(char[] board) {
        System.out.printf(" -----------\n");
        System.out.printf("| %s | %s | %s |\n", board[0], board[1], board[2]);
        System.out.printf(" -----------\n");
        System.out.printf("| %s | %s | %s |\n", board[3], board[4], board[5]);
        System.out.printf(" -----------\n");
        System.out.printf("| %s | %s | %s |\n", board[6], board[7], board[8]);
        System.out.printf(" -----------\n");
    }

    /**
     * Prints the option board to the screen allowing for the user
     * to select from one of the 9 squares
     */
    private static void printOptions() {
        System.out.printf(" -----------\n");
        System.out.printf("| 1 | 2 | 3 |\n");
        System.out.printf(" -----------\n");
        System.out.printf("| 4 | 5 | 6 |\n");
        System.out.printf(" -----------\n");
        System.out.printf("| 7 | 8 | 9 |\n");
        System.out.printf(" -----------\n");
    }

    /**
     * Resets the board
     * @param board board char[]
     */
    private static void resetBoard(char[] board) {
        for (int i = 0; i < board.length; i++) {
            board[i] = ' ';
            
        }
    }

}