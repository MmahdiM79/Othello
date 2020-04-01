import java.util.Scanner;


/**
 * @author Mohammad Mahdi Malmasi
 * @version 0.1.0
 */
public class Run
{
    private static boolean isValid(String choosenBlock)
    {
        return ((int)choosenBlock.charAt(0) < 58 && 48 < (int)choosenBlock.charAt(0))
                &&
                ((int)choosenBlock.charAt(1) < 73 && 64 < (int)choosenBlock.charAt(1));
    }


    private static Scanner inputs = new Scanner(System.in);
    public static void main(String[] args) 
    {
        Rules.initialization();
        Board gameBoard = new Board();
        Player player1, player2;

        int operand;
        String firstName, choosenBlock;


        while(true)
        {
            while (true)
            {
                Printer.printMenu();

                operand = inputs.nextInt();
                inputs.nextLine();

                if (operand < 6 && 0 < operand)
                    break;
            }

            switch (operand)
            {
                case 1: // new Two Player game

                    Printer.getPlayerName(1);
                    firstName = inputs.nextLine();
                    player1 = new Player(firstName, "NEVERMIND", 0, 1, 'X', "NEVERMIND");
                    
                    Printer.getPlayerName(2);
                    firstName = inputs.nextLine();
                    player2 = new Player(firstName, "NEVERMIND", 0, -1, 'O', "NEVERMIND");


                    while (!Rules.isGameEnded(gameBoard))
                    {
                        while (true)
                        {
                            Rules.findSelectableBlocks(gameBoard, player1.getPlayerID());
                            Printer.printVisualBoard(gameBoard.getVisualBoard(), gameBoard.getVisualY(), gameBoard.getVisualX());
                            Printer.printTurn(player1);

                            choosenBlock = inputs.nextLine();

                            if (choosenBlock.length() > 2 || choosenBlock.length() < 2 || !isValid(choosenBlock))
                            {
                                Printer.inValidInputError(inputs);

                                Rules.reset(player1.getPlayerID());
                                gameBoard.reset();

                                continue;
                            }

                            if (!Rules.isPossible(player1.getPlayerID(), choosenBlock))
                            {
                                Printer.wrongChooseError(inputs);

                                Rules.reset(player1.getPlayerID());
                                gameBoard.reset();

                                continue;
                            }


                            Rules.applyChoose(gameBoard, player1.getPlayerID(), choosenBlock);
                            Rules.reset(player1.getPlayerID());
                            gameBoard.reset();
                            break;
                        }

                        while (true)
                        {
                            Rules.findSelectableBlocks(gameBoard, player2.getPlayerID());
                            Printer.printVisualBoard(gameBoard.getVisualBoard(), gameBoard.getVisualY(), gameBoard.getVisualX());
                            Printer.printTurn(player2);

                            choosenBlock = inputs.nextLine();

                            if (choosenBlock.length() > 3 || choosenBlock.length() == 0 || !isValid(choosenBlock))
                            {
                                Printer.inValidInputError(inputs);

                                Rules.reset(player2.getPlayerID());
                                gameBoard.reset();

                                continue;
                            }

                            if (!Rules.isPossible(player2.getPlayerID(), choosenBlock))
                            {
                                Printer.wrongChooseError(inputs);

                                Rules.reset(player2.getPlayerID());
                                gameBoard.reset();

                                continue;
                            }


                            Rules.applyChoose(gameBoard, player2.getPlayerID(), choosenBlock);
                            Rules.reset(player2.getPlayerID());
                            gameBoard.reset();
                            break;
                        }
                    }

                break;
                
                case 2:
                break;

                case 3:
                break;

                case 4:
                break;

                case 5:
                break;
            }
        }
    }

}