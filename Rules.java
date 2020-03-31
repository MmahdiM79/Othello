import java.util.ArrayList;
import java.util.HashMap;



/**
 * @author Mohammad Mahdi Malmasi
 * @version 0.0.10
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
        for (int j = -1, len = 0; 0 <= y+j; j--)
        {
            if (gameBoard.getMainBoard()[y+j][x] == 0)
                break;

            else if (gameBoard.getMainBoard()[y+j][x] == player)
            {
                if (len == 0)
                    break;
                
                playersSelectableBlocks.get(player).add("" + (y+1) + ((char) (x+65)));
                gameBoard.changeBoard(y, x, 2);
                return;
            }

            len++;
        }

        for (int j = 1, len = 0; y+j < 8; j++)
        {
            if (gameBoard.getMainBoard()[y+j][x] == 0)
                break;

            else if (gameBoard.getMainBoard()[y+j][x] == player)
            {
                if (len == 0)
                    break;
                
                playersSelectableBlocks.get(player).add("" + (y+1) + ((char) (x+65)));
                gameBoard.changeBoard(y, x, 2);
                return;
            }

            len++;
        }

        for (int i = -1, len = 0; 0 <= x+i; i--)
        {
            if (gameBoard.getMainBoard()[y][x+i] == 0)
                break;

            else if (gameBoard.getMainBoard()[y][x+i] == player)
            {
                if (len == 0)
                    break;
                
                playersSelectableBlocks.get(player).add("" + (y+1) + ((char) (x+65)));
                gameBoard.changeBoard(y, x, 2);
                return;
            }

            len++;
        }

        for (int i = 1, len = 0; x+i < 8; i++)
        {
            if (gameBoard.getMainBoard()[y][x+i] == 0)
                break;

            else if (gameBoard.getMainBoard()[y][x+i] == player)
            {
                if (len == 0)
                    break;
                
                playersSelectableBlocks.get(player).add("" + (y+1) + ((char) (x+65)));
                gameBoard.changeBoard(y, x, 2);
                return;
            }

            len++;
        }

        for (int j = 1, i = 1, len = 0; (y+j < 8)&&(x+i < 8); j++, i++)
        {
            if (gameBoard.getMainBoard()[y+j][x+i] == 0)
                break;

            else if (gameBoard.getMainBoard()[y+j][x+i] == player)
            {
                if (len == 0)
                    break;
                
                playersSelectableBlocks.get(player).add("" + (y+1) + ((char) (x+65)));
                gameBoard.changeBoard(y, x, 2);
                return;
            }

            len++;
        }

        for (int j = -1, i = -1, len = 0; (0 <= y+j)&&(0 <= x+i); j--, i--)
        {
            if (gameBoard.getMainBoard()[y+j][x+i] == 0)
                break;

            else if (gameBoard.getMainBoard()[y+j][x+i] == player)
            {
                if (len == 0)
                    break;
                
                playersSelectableBlocks.get(player).add("" + (y+1) + ((char) (x+65)));
                gameBoard.changeBoard(y, x, 2);
                return;
            }

            len++;
        }

        for (int j = -1, i = 1, len = 0; (0 <= y+j)&&(x+i < 8); j--, i++)
        {
            if (gameBoard.getMainBoard()[y+j][x+i] == 0)
                break;

            else if (gameBoard.getMainBoard()[y+j][x+i] == player)
            {
                if (len == 0)
                    break;
                
                playersSelectableBlocks.get(player).add("" + (y+1) + ((char) (x+65)));
                gameBoard.changeBoard(y, x, 2);
                return;
            }

            len++;
        }

        for (int j = 1, i = -1, len = 0; (y+j < 8)&&(0 <= x+i); j++, i--)
        {
            if (gameBoard.getMainBoard()[y+j][x+i] == 0)
                break;

            else if (gameBoard.getMainBoard()[y+j][x+i] == player)
            {
                if (len == 0)
                    break;
                
                playersSelectableBlocks.get(player).add("" + (y+1) + ((char) (x+65)));
                gameBoard.changeBoard(y, x, 2);
                return;
            }

           len++;
        }
    }


    public static boolean isPossible(int player, String choosenBlock)
    {
        return playersSelectableBlocks.get(player).contains(choosenBlock);
    }

    private static int deCode(String choosenBlock, char which)
    {
        switch (which)
        {
            case 'X':
                return (int)choosenBlock.charAt(1) - 65;

            case 'Y':
                switch (choosenBlock.charAt(0))
                {
                    case '1':
                        return 0;

                    case '2':
                        return 1;

                    case '3':
                        return 2;

                    case '4':
                        return 3;

                    case '5':
                        return 4;

                    case '6':
                        return 5;

                    case '7':
                        return 6;

                    case '8':
                        return 7;
                }
        }

        return -1;
    }

    public static void applyChoose(Board gameBoard, int player, String choosenBlock)
    {  
        int y = deCode(choosenBlock, 'Y');
        int x = deCode(choosenBlock, 'X');

        System.out.println(y + " " + x);

        gameBoard.changeBoard(y, x, player);


        int j = 0, i = 0;

        j = -1;
        i = 0;
        while (gameBoard.getMainBoard()[y+j][x+i] == (-player))
        {
            gameBoard.changeBoard(y+j, x+i, player);
            j--;
        }

        j = 1;
        i = 0;
        while (gameBoard.getMainBoard()[y+j][x+i] == (-player))
        {
            gameBoard.changeBoard(y+j, x+i, player);
            j++;
        }

        j = 0;
        i = -1;
        while (gameBoard.getMainBoard()[y+j][x+i] == (-player))
        {
            gameBoard.changeBoard(y+j, x+i, player);
            i--;
        }

        j = 0;
        i = 1;
        while (gameBoard.getMainBoard()[y+j][x+i] == (-player))
        {
            gameBoard.changeBoard(y+j, x+i, player);
            i++;
        }

        j = -1;
        i = -1;
        while (gameBoard.getMainBoard()[y+j][x+i] == (-player))
        {
            gameBoard.changeBoard(y+j, x+i, player);
            j--;
            i--;
        }

        j = 1;
        i = 1;
        while (gameBoard.getMainBoard()[y+j][x+i] == (-player))
        {
            gameBoard.changeBoard(y+j, x+i, player);
            j++;
            i++;
        }

        j = -1;
        i = 1;
        while (gameBoard.getMainBoard()[y+j][x+i] == (-player))
        {
            gameBoard.changeBoard(y+j, x+i, player);
            j--;
            i++;
        }

        j = 1;
        i = -1;
        while (gameBoard.getMainBoard()[y+j][x+i] == (-player))
        {
            gameBoard.changeBoard(y+j, x+i, player);
            j++;
            i--;
        }
     }
}