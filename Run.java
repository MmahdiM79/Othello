import java.util.Scanner;


/**
 * @author Mohammad Mahdi Malmasi
 * @version 0.0.1
 */
public class Run
{
    private static Scanner inputs = new Scanner(System.in);
    public static void main(String[] args) 
    {
        Rules.initialization();
        Board gameBoard = new Board();
        Player player1, player2;

        int operand;
        String firstName, lastName;

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
                case 1:
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