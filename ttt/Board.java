public class Board {
    private byte[] board;
    private byte currentPlayer;
    private boolean isComplete;
    private String alertMessage;
    private int winner;
    public Board() {
        board = new byte[9];
        currentPlayer = 1; // 1 == x 2 == o
        winner = 0; // 0 == nobody, 1 == x, 2 == o, 3 == Stalemate
        alertMessage = "";
    }

    public byte[] getBoard() {
        return board;
    }

    public void drawBoard() {
        System.out.println(" " + drawNumOrPlay(board[0], 1) + " | " + drawNumOrPlay(board[1], 2) + " | " + drawNumOrPlay(board[2],3));
        System.out.println("-----------");
        System.out.println(" " + drawNumOrPlay(board[3],4) + " | " + drawNumOrPlay(board[4],5) + " | " + drawNumOrPlay(board[5],6));
        System.out.println("-----------");
        System.out.println(" " + drawNumOrPlay(board[6], 7) + " | " + drawNumOrPlay(board[7], 8) + " | " + drawNumOrPlay(board[8], 9));
    }

    public char drawChar(byte type) {
        switch (type) {
            case 0:
                return ' ';
            case 1:
                return 'X';
            case 2:
                return 'O';
            default:
                return '?';
        }
    }

    public String drawNumOrPlay(byte state, int index) {
        if (state == 0) {
            return Integer.toString(index);
        }
        return Character.toString(drawChar(state));
    }

    public String getCurrentPlayer() {
        return Character.toString(drawChar(currentPlayer));
    }

    // Is the board in a finished (win or tie) state?
    public boolean isComplete() {
        return isComplete;
    }

    public void setPiece(int square) {
        if (square < 1 || square > 9) {
            alertMessage = ("Number must be between 1 and 9");
            return;
        }
        if (board[square-1] == 0) {
            board[square-1] = currentPlayer;
            setNextPlayer();
        } else {
            alertMessage = ("Choose another number, " + square + " is already taken."); 
        }
    }

    public String getAlertMessage() {
        return alertMessage;
    }

    private void setNextPlayer() {
        // Reset alert message as we must have been successful
        alertMessage = "";
        if (currentPlayer == 1) {
            currentPlayer = 2;
        } else {
            currentPlayer = 1;
        }
    }

    public void checkBoardState() { 
        for (int i = 1; i <= 2; i++) { // Loop thru both players
            for (int j = 0; j < 3*3; j += 3) {
                if (board[j] == i && board[j+1] == i && board[j+2] == i) { // Rows
                    winner = i;
                }
            }   

            for (int j = 0; j < 3; j++) {
                if (board[j] == i && board[j+3] == i && board[j+6] == i) { // cols
                    winner = 1;
                }
            }

            if (board[0] == i && board[4] == i && board[8] == i) { // Tl -> Br diag
                winner = i;
            } else if (board[2] == i && board[4] == i && board[6] == i) { // Tr -> Bl diag
                winner = i;
            }
        }
        
        int sum = 0;

        for (byte values:board) {
            sum += values;
        }
        // if adds up to 13 all have been filled / stalemate
        if (sum == 13) {
            winner = 3; // Stalemate
        }

        if (winner != 0) {
            isComplete = true;
        }
    }
    
    public int getWinner() {
        return winner;
    }
}
