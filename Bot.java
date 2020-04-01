import java.util.HashMap;

/**
 * @author Mohammad Mahdi Malmasi
 * @version 0.0.0
 */
public class Bot extends Player
{

    private HashMap<String, Integer> whiteBlocksRank;

    public Bot()
    {
        super("BOT", "", 0, -1, 'O', "");

        whiteBlocksRank = new HashMap<>();
    }

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

    private void findWhiteBlocks(Board gameBoard)
    {
        gameBoard.reset();

        Rules.reset(-1);
        Rules.findSelectableBlocks(gameBoard, -1);

        for (int j = 0; j < 8; j++)
        {
            for (int i = 0; i < 8; i++)
            {
                if (Rules.isPossible(-1, makeCode(j, i)));
                {
                    whiteBlocksRank.put(makeCode(j, i), 0);
                }
            }
        }

        gameBoard.reset();
    }

    private void whiteBlocksRanking()
    {
        int x, y, rank;
        for (String whiteBlock: whiteBlocksRank.keySet())
        {
            x = deCode(whiteBlock, 'X');
            y = deCode(whiteBlock, 'Y');

            rank = 16 - (y + x) - (Math.abs(y - x));

            whiteBlocksRank.replace(whiteBlock, 0, rank);
        }
    }

    private void reset()
    {
        for (String whiteBlock: whiteBlocksRank.keySet())
        {
            whiteBlocksRank.replace(whiteBlock, whiteBlocksRank.get(whiteBlock), 0);
        }

        Rules.reset(-1);
    }

    public void botChoose(Board gameBoard)
    {
        if (Rules.isPassed(this))
            return;

        findWhiteBlocks(gameBoard);
        whiteBlocksRanking();

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

        Rules.applyChoose(gameBoard, -1, choosenBlock);

       reset(); 
    }
}