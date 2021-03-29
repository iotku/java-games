public class Board {
    private byte[] board;
    private byte currentPlayer;
    private boolean isComplete;
    private int winner;
    public Board() {
        board = new byte[9];
        currentPlayer = 1; // 1 == x 2 == o
        winner = 0; // 0 == nobody, 1 == x, 2 == o, 3 == Stalemate
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
                return 'x';
            case 2:
                return 'o';
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
            System.out.println("Number must be between 1 and 9");
            return;
        }
        if (board[square-1] == 0) {
            board[square-1] = currentPlayer;
            setNextPlayer();
        } else {
            System.out.println("Choose another number, " + square + " is already taken."); 
        }
    }

    private void setNextPlayer() {
        if (currentPlayer == 1) {
            currentPlayer = 2;
        } else {
            currentPlayer = 1;
        }
    }

    public void checkBoardState() { // do this smarter?
        if (board[0] == 1 && board[1] == 1 && board[2] == 1) {
            // top row
            winner = 1;
        } else if (board[3] == 1 && board[4] == 1 && board[5] == 1) {
            // middle row
            winner = 1;
        } else if (board[6] == 1 && board[7] == 1 && board[8] == 1) {
            // bottom row
            winner = 1;
        } else if (board[0] == 1 && board[3] == 1 && board[6] == 1) {
            // 1st col
            winner = 1;
        } else if (board[1] == 1 && board[4] == 1 && board[7] == 1) {
            // 2nd col
            winner = 1;
        } else if (board[2] == 1 && board[5] == 1 && board[8] == 1) {
            // 3rd col
            winner = 1;
        } else if (board[0] == 1 && board[4] == 1 && board[8] == 1) {
            // Tl -> Br diag
            winner = 1;
        } else if (board[2] == 1 && board[4] == 1 && board[6] == 1){
            // Tr -> Bl diag
            winner = 1;
        }
        

        if (board[0] == 2 && board[1] == 2 && board[2] == 2) {
            // top row
            winner = 2;
        } else if (board[3] == 2 && board[4] == 2 && board[5] == 2) {
            // middle row
            winner = 2;
        } else if (board[6] == 2 && board[7] == 2 && board[8] == 2) {
            // bottom row
            winner = 2;
        } else if (board[0] == 2 && board[3] == 2 && board[6] == 2) {
            // 1st col
            winner = 2;
        } else if (board[1] == 2 && board[4] == 2 && board[7] == 2) {
            // 2nd col
            winner = 2;
        } else if (board[2] == 2 && board[5] == 2 && board[8] == 2) {
            // 3rd col
            winner = 2;
        } else if (board[0] == 2 && board[4] == 2 && board[8] == 2) {
            // Tl -> Br diag
            winner = 2;
        } else if (board[2] == 2 && board[4] == 2 && board[6] == 2){
            // Tr -> Bl diag
            winner = 2;
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
