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


    public static void initialization()
    {
        playersSelectableBlocks.put(1, new ArrayList<String>());
        playersSelectableBlocks.put(-1, new ArrayList<String>());
    }
    
    public static void reset(int player)
    {
        playersSelectableBlocks.remove(player);
        playersSelectableBlocks.put(player, new ArrayList<String>());
    }

    public static void findSelectableBlocks(Board gameBoard, int player)
    {
        for (int j = 0; j < 8; j++)
            for (int i = 0; i < 8; i++)
            {
                if (gameBoard.getMainBoard()[j][i] == (-player) && isEmptyAround(gameBoard, j, i))
                {
                    for (int deltaY = -1; deltaY < 2; deltaY++)
                        for (int deltaX = -1; deltaX < 2; deltaX++)
                        {
                            if (!isInRange(j+deltaY, i+deltaX))
                                continue;
                                
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
            {
                if (!isInRange(y+j, x+i))
                    continue;

                if (gameBoard.getMainBoard()[y+j][x+i] == 0)
                    return true;
            }

        return false;
    }



    private static void selectBlock(Board gameBoard, int y, int x, int player)
    {
        int increasement_j, increasement_i;
        
        for (int J = -1; J < 2; J++)
            for (int I = -1; I < 2; I++)
            {
                increasement_j = (int)Math.signum(J);
                increasement_i = (int)Math.signum(I);

                for (int j = J, i = I, len = 0; isInRange(y+j, x+i); j+=increasement_j, i+=increasement_i)
                {
                    if (gameBoard.getMainBoard()[y+j][x+i] == 0 || gameBoard.getMainBoard()[y+j][x+i] == 2)
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

    private static boolean isInRange(int y, int x)
    {
        return ((y < 8 && x < 8) && (0 <= y && 0 <= x));
    }

    public static int applyChoose(Board gameBoard, int player, String choosenBlock)
    {  
        int gainedScore = 0;

        int y = deCode(choosenBlock, 'Y');
        int x = deCode(choosenBlock, 'X');

        System.out.println(y + " " + x);

        gameBoard.changeBoard(y, x, player);
        gainedScore++;


        int j, increasement_j, i, increasement_i;

        for (int J = -1; J < 2; J++)
            for (int I = -1; I < 2; I++)
            {
                increasement_j = (int)Math.signum(J);
                increasement_i = (int)Math.signum(I);

                j = J; 
                i = I;
                while (isInRange(y+j, x+i) && gameBoard.getMainBoard()[y+j][x+i] == (-player))
                {
                    gameBoard.changeBoard(y+j, x+i, player);
                    gainedScore++;

                    j += increasement_j;
                    i += increasement_i;
                }
            }

        
        return gainedScore;
     }
}