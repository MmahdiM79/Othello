import java.util.ArrayList;

interface BoardActs
{

}

/**
 * @author Mohammad Mahdi Malmasi
 * @version 0.0.2
 */
public class Board implements BoardActs
{

            /* Feilds */


    // the dimensions of the game board 
    private final int BOARD_X = 8;
    private final int BOARD_Y = 8;

    // the dimensions of the game visual board 
    private final int VISUAL_BOARD_X = (BOARD_X * 7) + 1;
    private final int VISUAL_BOARD_Y = (BOARD_Y * 4) + 1;


    // this array will be showen in standard output (terminal).
    // X: player 1,  O: player 2
    private char[][] visualBoard; 

    // this array is for perform calculations and decisions
    private int[][] board;





          /* Constructor */

    public Board()
    {
        visualBoard = new char[VISUAL_BOARD_Y][VISUAL_BOARD_X];
        makeVisualBoard();

        applyToVisualBoard(3, 3, 'X');
        applyToVisualBoard(4, 4, 'X');

        applyToVisualBoard(3, 4, 'O');
        applyToVisualBoard(4, 3, 'O');

    }



            /* Methods */

    /**
     * @return the visualBoard
     */
    public char[][] getVisualBoard() 
    {
        return visualBoard;
    }
    /**
     * @return the vISUAL_BOARD_X
     */
    public int getVisualX() 
    {
        return VISUAL_BOARD_X;
    }
    /**
     * @return the vISUAL_BOARD_Y
     */
    public int getVisualY() 
    {
        return VISUAL_BOARD_Y;
    }


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