import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;



/**
 * This class is monitoring on the enforcement of game rules
 * 
 * @author Mohammad Mahdi Malmasi
 * @version 0.1.0
 */
public class Rules
{
    // the players selectable blocks
    private static HashMap<Integer, ArrayList<String>> playersSelectableBlocks = new HashMap<>();




            /* Methods */


    /**
     * This method make a new HashMap Object for this class feild
     */ 
    public static void initialization()
    {
        playersSelectableBlocks.put(1, new ArrayList<String>());
        playersSelectableBlocks.put(-1, new ArrayList<String>());
    }
    

    /**
     * This method reset the player selectable blocks
     * 
     * @param player
     */
    public static void reset(int player)
    {
        playersSelectableBlocks.remove(player);
        playersSelectableBlocks.put(player, new ArrayList<String>());
    }


    /**
     * This method find the player selectable blocks
     * 
     * @param gameBoard : the board of the game
     * @param player
     */
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


    /**
     * This method checks whether a player's move is possible
     * 
     * @param player 
     * @param choosenBlock : the player choosen block
     * 
     * @return {@code true} if the player can choose the given block
     */
    public static boolean isPossible(int player, String choosenBlock)
    {
        return playersSelectableBlocks.get(player).contains(choosenBlock);
    }
    

    /**
     * This method does the chosen player choosen move
     * 
     * @param gameBoard : the board game
     * @param player
     * @param choosenBlock : the player choosen block
     * 
     * @return the player gained score of this move
     */
    public static int applyChoose(Board gameBoard, int player, String choosenBlock)
    {  
        int gainedScore = 0;
        
        int y = deCode(choosenBlock, 'Y');
        int x = deCode(choosenBlock, 'X');
        
        
        gameBoard.changeBoard(y, x, player);
        gainedScore++;
        
        
        int j, increasement_j, i, increasement_i;
        boolean check = false;
        
        for (int J = -1; J < 2; J++)
        for (int I = -1; I < 2; I++)
        {
            increasement_j = (int)Math.signum(J);
            increasement_i = (int)Math.signum(I);
            
            j = J; 
            i = I;
            while(isInRange(y+j, x+i) && gameBoard.getMainBoard()[y+j][x+i] == (-player))
            {
                j += increasement_j;
                i += increasement_i;
                
                if (!isInRange(y+j, x+i))
                continue;
                
                if (gameBoard.getMainBoard()[y+j][x+i] == player)
                {
                    check = true;
                    break;
                }
            }
            
            
            if (check)
            {
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
            
            check = false;
        }
        
        
        return gainedScore;
    }
    
    
    /**
     * This method check that the given player can choose any block or not 
     * 
     * @param player
     * @return {@code false} if the player can't choose any block
     */
    public static boolean isPassed(Player player)
    {
        if (playersSelectableBlocks.get(player.getPlayerID()).size() == 0)
        return true;
        
        //  else
        return false;
    }
    
    
    /**
     * This method check that the game is ended or not
     * 
     * @param gameBoard : the board of the game
     * @return {@code ture} if the game was ended
     */
    public static boolean isGameEnded(Board gameBoard)
    {
        findSelectableBlocks(gameBoard, 1);
        gameBoard.reset();
        
        findSelectableBlocks(gameBoard, -1);
        gameBoard.reset();
        
        
        
        int check = playersSelectableBlocks.get(1).size() + playersSelectableBlocks.get(-1).size();
        reset(1);
        reset(-1);
        
        if (check == 0)
        return true;
        
        return false;
    }
    
    
    /**
     * This method determine the winner
     * 
     * @param player1 
     * @param player2
     * @param finish : the plyers input source
     */
    public static void winner(Player player1, Player player2, Scanner finish)
    {
        if (player1.getScore() > player2.getScore())
        Printer.printWinner(player1, finish);
        
        else if (player1.getScore() < player2.getScore())
        Printer.printWinner(player2, finish);
        
        else 
        {
            Printer.printWinner(player1, finish);
            Printer.printWinner(player2, finish);
        }
    }
    
    
    





    
    // this methed select the player selectable blocks
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


    // this method check around a block
    // it returns true if there is at least a empty block
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


    // this method decode the given player input string to (x, y)
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

    
    // this method chacks to see if the givren Coordinates are is in range
    private static boolean isInRange(int y, int x)
    {
        return ((y < 8 && x < 8) && (0 <= y && 0 <= x));
    }
}