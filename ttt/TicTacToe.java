import java.util.*;
public class TicTacToe {
    public static void main(String[]args) {
        System.out.println("Tick Tack toe");

        Board board = new Board();
        Scanner console = new Scanner(System.in);
        clear();
       do {
            board.drawBoard();
            System.out.println("It is " + board.getCurrentPlayer() + "'s turn");
            if (console.hasNextInt()) {
                board.setPiece(console.nextInt());
            } else if (console.hasNext()) {
                console.nextLine(); // clear the line buffer (it wasn't a number)
            }
            board.checkBoardState();
            clear();
        } while (!board.isComplete());

        board.drawBoard();
        
        switch (board.getWinner()) {
            case 1:
                System.out.println("X is the winner!");
                break;
            case 2:
                System.out.println("O is the winner!");
                break;
            case 3:
                System.out.println("It was a tie!");
                break;
            default:
                System.out.println("It seems there's an error in my logic.");
                System.out.println("Winner was...: " + board.getWinner());
        }
    }
    public static void clear() {
        try{
            new ProcessBuilder("clear").inheritIO().start().waitFor();
        }catch(Exception e2){}
    }

}
