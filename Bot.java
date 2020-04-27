import java.util.HashMap;


/**
 * This class is a simple bot that play insted player 2
 * 
 * 
 * @author Mohammad Mahdi Malmasi
 * @version 0.1.0
 */
public class Bot extends Player
{
            /*  Feilds  */

    // the blocks that bot can choose
    // rank is an int that make difrence between selectable blocks
    // the bot will chose the higher rank
    private HashMap<String, Integer> whiteBlocksRank;







            /* Constructor */
    /**
     * Creat a new bot
     */
    public Bot()
    {
        super("BOT", "", 0, -1, 'O', "");
        
        whiteBlocksRank = new HashMap<>();
    }
    
    
    
    
    
    
    

            /*  Methods  */

    /**
     * This mehtod make the bot choose
     * 
     * @param gameBoard : the board of the game
     * @return the bot gained score
     * 
     * @see Rules#applyChoose(Board, int, String)
     */
    public int botChoose(Board gameBoard)
    {
        // reset the board
        Rules.reset(-1);
        Rules.findSelectableBlocks(gameBoard, -1);

        // check that bot can choose any block or not
        if (Rules.isPassed(this))
             return 0;  

        // the bot thinking part =)        
        findWhiteBlocks(gameBoard);
        whiteBlocksRanking();

        // bot make its choose
        String choosenBlock = null;
        for (String whiteBlock : whiteBlocksRank.keySet())
        {
            if (choosenBlock == null)
            {
                choosenBlock = whiteBlock;
                continue;
            }

            if (whiteBlocksRank.get(choosenBlock) < whiteBlocksRank.get(whiteBlock))
                choosenBlock = whiteBlock;
        }

        // reset the bot selectable blocks rank
        reset(); 
        
        // return the bot gained score
        return Rules.applyChoose(gameBoard, -1, choosenBlock);
    }






    // thie method converts the i,j to string
    // example: (3, 4) >>> "5D"
    private String makeCode(int y, int x)
    {
        char X = '\0';
        switch (x)
        {
            case 0:
                X = 'A';
            break;

            case 1:
                X = 'B';
            break;

            case 2:
                X = 'C';
            break;

            case 3:
                X = 'D';
            break;

            case 4:
                X = 'E';
            break;

            case 5:
                X = 'F';
            break;

            case 6:
                X = 'G';
            break;

            case 7:
                X = 'H';
            break;
        }

        return "" + (y+1) + X;
    }
    

    // this method converts string to i,j
    // example: "5D" >>> (3, 4)
    private int deCode(String choosenBlock, char which)
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


    // this method find the select able blocks
    private void findWhiteBlocks(Board gameBoard)
    {
        String choosenBlock;
        for (int j = 0; j < 8; j++)
        {
            for (int i = 0; i < 8; i++)
            {
                choosenBlock = makeCode(j, i);
                if (Rules.isPossible(-1, choosenBlock))
                {
                    whiteBlocksRank.put(makeCode(j, i), 0);
                }
            }
        }

        gameBoard.reset();
    }


    // this method calculate the block rank
    private void whiteBlocksRanking()
    {
        int x, y, rank;
        for (String whiteBlock: whiteBlocksRank.keySet())
        {
            x = deCode(whiteBlock, 'X') + 1;
            y = deCode(whiteBlock, 'Y') + 1;
        
            if (x > 3)
                x = 8 - x; 
            
            if (y > 3)
                y = 8 - y;

            rank = 16 - (y + x) - (2 * (Math.abs(y - x)));

            whiteBlocksRank.replace(whiteBlock, 0, rank);
        }
    }


    // this method reset the rank of the blocks
    private void reset()
    {
        for (String whiteBlock: whiteBlocksRank.keySet())
        {
            whiteBlocksRank.put(whiteBlock, -1);
        }

        Rules.reset(-1);
    }
}