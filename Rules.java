import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author Mohammad Mahdi Malmasi
 * @version 0.0.0
 */
public class Rules
{
            /* Feilds */

    
    private HashMap<Character, ArrayList<Stirng>> playersSelectableBlocks;

    private HashMap<Character, ArrayList<String>> playersBlocks;




    public Rules()
    {
        playersBlocks = new HashMap<>();
        playersSelectableBlocks = new HashMap<>();
    }



    public void findSelectableBlocks(Board gameBoard, int player)
    {
        for (int j = 0; j < 8; j++)
            for (int i = 0; i < 8; i++)
            {
                if (gameBoard.getMainBoard()[j][i] == 0 || gameBoard.getMainBoard()[j][i] == 2)
                    continue;

                if (gameBoard.getMainBoard()[j][i] == player && isEmptyAround(gameBoard, j, i))
                {
                    for (int deltaY = -1; deltaY < 2; deltaY++)
                        for (int deltaX = -1; deltaX < 2; deltaX ++)
                        {
                            
                        }
                        
                }
            }
    }


    private boolean isEmptyAround(Board gameBoard, int y, int x)
    {
        for (int j = -1; j < 2; j++)
            for (int i = -1; i < 2; i++)
                if (gameBoard.getMainBoard()[y+j][x+i] == 0)
                    return true;

        return false;
    }
}