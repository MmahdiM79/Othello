
/**
 * This class repersent a simple Othello board.
 * It holds to board: 
 *        1.mainBoard: a simple 8x8 int array that shown the blocks status.
 *        2.visualBoard a 33x57 char array which is synchronized with the board. this board will be showen to the client
 * 
 * 
 * @author Mohammad Mahdi Malmasi
 * @version 0.1.3
 */
public class Board
{

            /* Feilds */


    // the dimensions of the game board 
    private final int BOARD_X = 8;
    private final int BOARD_Y = 8;

    // the dimensions of the game visual board 
    private final int VISUAL_BOARD_X = (BOARD_X * 7) + 1;
    private final int VISUAL_BOARD_Y = (BOARD_Y * 4) + 1;



    // this array will be showen in standard output (terminal).
    // X: player 1 -> red color,  O: player 2 -> cyan color
    private char[][] visualBoard; 

    // this array is for perform calculations and decisions
    // 1 refers to player1 (X: Red) 
    // -1 refers to player2 (O: Cyan)  
    // 0 refers to a empty block
    // 2 refers to a selectable block
    private int[][] mainBoard;





          /* Constructor */

    /**
     * Creat a new Board
     */
    public Board()
    {
        // fill the main board
        mainBoard = new int[8][8];
        for (int j = 0; j < 8; j++)
            for (int i = 0; i < 8; i++)
                mainBoard[j][i] = 0;


        visualBoard = new char[VISUAL_BOARD_Y][VISUAL_BOARD_X];
        makeVisualBoard();

        changeBoard(3, 3, 1);
        changeBoard(4, 4, 1);

        changeBoard(3, 4, -1);
        changeBoard(4, 3, -1);
    }






            /* Methods */


    // * getter methods *

    /**
     * @return the board 
     */
    public int[][] getMainBoard() 
    {
        return mainBoard;
    }
    /**
     * @return the visual board
     */
    public char[][] getVisualBoard() 
    {
        return visualBoard;
    }
    /**
     * @return the visual board's x
     */
    public int getVisualX() 
    {
        return VISUAL_BOARD_X;
    }
    /**
     * @return the visual board's y
     */
    public int getVisualY() 
    {
        return VISUAL_BOARD_Y;
    }




    /**
     * This method change the board status
     * 
     * @param y : the y of the block that you want to change it
     * @param x : the x ot the block that you want to change it
     * @param blockKind : the new status of the block. 1 refers to player1 (X: Red),  -1 refers to player2 (O: Cyan),  0 refers to a empty block 
     */
    public void changeBoard(int y, int x, int blockKind)
    {
        mainBoard[y][x] = blockKind;


        char player;
        switch (blockKind)
        {
            case 1:
                player = 'X';
            break;

            case -1:
                player = 'O';
            break;

            case 2:
                player = 'W';
            break;

            default:
                player = ' ';
        }
        applyToVisualBoard(y, x, player);
    }


    /**
     * This method remve the white blocks from the board
     */
    public void reset()
    {
        for (int j = 0; j < 8; j++)
            for (int i = 0; i < 8; i++)
                if (mainBoard[j][i] == 2)
                    changeBoard(j, i, 0);
    }


    // this method make changes in visual board
    // this method used in changeBoard
    private void applyToVisualBoard(int y, int x, char player)
    {
        y = (4 * y) + 1;
        x = (7 * x) + 1;

        for (int j = 0; j < 3; j++)
        {
            for (int i = 0; i < 6; i++)
                visualBoard[y+j][x+i] = player;
        }
    }


    // this method fill the visual board for the first time
    private void makeVisualBoard()
    {
        for (int j = 0; j < VISUAL_BOARD_Y; j++)
        {
            for (int i = 0; i < VISUAL_BOARD_X; i++)
            {
                if (j%4 == 0 && i%7 == 0)
                    visualBoard[j][i] = '•';

                else if (j%4 == 0 && i%7 != 0)
                    visualBoard[j][i] = '-';

                else if (j%4 != 0 && i%7 == 0)
                    visualBoard[j][i] = '|';

                else
                    visualBoard[j][i] = ' ';
            }
        }
    }
}