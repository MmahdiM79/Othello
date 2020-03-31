import java.util.ArrayList;
import java.util.HashMap;

import jdk.nashorn.internal.ir.Block;


/**
 * @author Mohammad Mahdi Malmasi
 * @version 0.0.9
 */
public class Rules
{
            /* Feilds */

    
    private static HashMap<Integer, ArrayList<String>> playersSelectableBlocks = new HashMap<>();
    private static HashMap<Integer, ArrayList<String>> playersBlocks = new HashMap<>();


    public static void initialization()
    {
        playersBlocks.put(1, new ArrayList<String>());
        playersBlocks.put(-1, new ArrayList<String>());

        playersSelectableBlocks.put(1, new ArrayList<String>());
        playersSelectableBlocks.put(-1, new ArrayList<String>());
    }
    

    public static void findSelectableBlocks(Board gameBoard, int player)
    {
        for (int j = 0; j < 8; j++)
            for (int i = 0; i < 8; i++)
            {
                if (gameBoard.getMainBoard()[j][i] == (-player) && isEmptyAround(gameBoard, j, i))
                {
                    for (int deltaY = -1; deltaY < 2; deltaY++)
                        for (int deltaX = -1; deltaX < 2; deltaX ++)
                        {
                            if (gameBoard.getMainBoard()[j+deltaY][i+deltaX] == 0)
                                selectBlock(gameBoard, j+deltaY, i+deltaX, player);

                        }
                        
                }
            }
    }


    private static boolean isEmptyAround(Board gameBoard, int y, int x)
    {
        for (int j = -1; j < 2; j++)
            for (int i = -1; i < 2; i++)
                if (gameBoard.getMainBoard()[y+j][x+i] == 0)
                    return true;

        return false;
    }


    private static void selectBlock(Board gameBoard, int y, int x, int player)
    {
        for (int j = -2; 0 <= y+j; j--)
        {
            if (gameBoard.getMainBoard()[y+j][x] == 0)
                break;

            else if (gameBoard.getMainBoard()[y+j][x] == player)
            {
                playersSelectableBlocks.get(player).add("" + (y+1) + ((char) (x+65)));
                gameBoard.changeBoard(y, x, 2);
                return;
            }

            else
                continue;
        }

        for (int j = 2 ; y+j < 8; j++)
        {
            if (gameBoard.getMainBoard()[y+j][x] == 0)
                break;

            else if (gameBoard.getMainBoard()[y+j][x] == player)
            {
                playersSelectableBlocks.get(player).add("" + (y+1) + ((char) (x+65)));
                gameBoard.changeBoard(y, x, 2);
                return;
            }

            else
                continue;
        }

        for (int i = -2; 0 <= x+i; i--)
        {
            if (gameBoard.getMainBoard()[y][x+i] == 0)
                break;

            else if (gameBoard.getMainBoard()[y][x+i] == player)
            {
                playersSelectableBlocks.get(player).add("" + (y+1) + ((char) (x+65)));
                gameBoard.changeBoard(y, x, 2);
                return;
            }

            else
                continue;
        }

        for (int i = 2; x+i < 8; i++)
        {
            if (gameBoard.getMainBoard()[y][x+i] == 0)
                break;

            else if (gameBoard.getMainBoard()[y][x+i] == player)
            {
                playersSelectableBlocks.get(player).add("" + (y+1) + ((char) (x+65)));
                gameBoard.changeBoard(y, x, 2);
                return;
            }

            else
                continue;
        }

        for (int j = 2, i = 2; (y+j < 8)&&(x+i < 8); j++, i++)
        {
            if (gameBoard.getMainBoard()[y+j][x+i] == 0)
                break;

            else if (gameBoard.getMainBoard()[y+j][x+i] == player)
            {
                playersSelectableBlocks.get(player).add("" + (y+1) + ((char) (x+65)));
                gameBoard.changeBoard(y, x, 2);
                return;
            }

            else
                continue;
        }

        for (int j = -2, i = -2; (0 <= y+j)&&(0 <= x+i); j--, i--)
        {
            if (gameBoard.getMainBoard()[y+j][x+i] == 0)
                break;

            else if (gameBoard.getMainBoard()[y+j][x+i] == player)
            {
                playersSelectableBlocks.get(player).add("" + (y+1) + ((char) (x+65)));
                gameBoard.changeBoard(y, x, 2);
                return;
            }

            else
                continue;
        }

        for (int j = -2, i = 2; (0 <= y+j)&&(x+i < 8); j--, i++)
        {
            if (gameBoard.getMainBoard()[y+j][x+i] == 0)
                break;

            else if (gameBoard.getMainBoard()[y+j][x+i] == player)
            {
                playersSelectableBlocks.get(player).add("" + (y+1) + ((char) (x+65)));
                gameBoard.changeBoard(y, x, 2);
                return;
            }

            else
                continue;
        }

        for (int j = 2, i = -2; (y+j < 8)&&(0 <= x+i); j++, i--)
        {
            if (gameBoard.getMainBoard()[y+j][x+i] == 0)
                break;

            else if (gameBoard.getMainBoard()[y+j][x+i] == player)
            {
                playersSelectableBlocks.get(player).add("" + (y+1) + ((char) (x+65)));
                gameBoard.changeBoard(y, x, 2);
                return;
            }

            else
                continue;
        }
    }


    public static boolean isPossible(int player, String choosenBlock)
    {
        return playersSelectableBlocks.get(player).contains(choosenBlock);
    }

     public static applyChoose(Board gameBoard, int player, String choosenBlock)
     {  
        int y = (int)choosenBlock.charAt(0) - 1;
        int x = (int)choosenBlock.charAt(1) - 65;

        gameBoard.changeBoard(y, x, player);


        int j = 0, i = 0;

        j = -1, i = 0;
        while (gameBoard.getMainBoard()[y+j][x+i] == (-player))
        {
            gameBoard.changeBoard(y+j, x+i, player);
            j--;
        }

        j = -1, i = 0;
        while (gameBoard.getMainBoard()[y+j][x+i] == (-player))
        {
            gameBoard.changeBoard(y+j, x+i, player);
            j--;
        }

        j = -1, i = 0;
        while (gameBoard.getMainBoard()[y+j][x+i] == (-player))
        {
            gameBoard.changeBoard(y+j, x+i, player);
            j--;
        }

        j = -1, i = 0;
        while (gameBoard.getMainBoard()[y+j][x+i] == (-player))
        {
            gameBoard.changeBoard(y+j, x+i, player);
            j--;
        }

        j = -1, i = 0;
        while (gameBoard.getMainBoard()[y+j][x+i] == (-player))
        {
            gameBoard.changeBoard(y+j, x+i, player);
            j--;
        }

        j = -1, i = 0;
        while (gameBoard.getMainBoard()[y+j][x+i] == (-player))
        {
            gameBoard.changeBoard(y+j, x+i, player);
            j--;
        }

        j = -1, i = 0;
        while (gameBoard.getMainBoard()[y+j][x+i] == (-player))
        {
            gameBoard.changeBoard(y+j, x+i, player);
            j--;
        }

        j = -1, i = 0;
        while (gameBoard.getMainBoard()[y+j][x+i] == (-player))
        {
            gameBoard.changeBoard(y+j, x+i, player);
            j--;
        }

        j = -1, i = 0;
        while (gameBoard.getMainBoard()[y+j][x+i] == (-player))
        {
            gameBoard.changeBoard(y+j, x+i, player);
            j--;
        }
     }
}