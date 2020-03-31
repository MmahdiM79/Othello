import java.util.Scanner;


/**
 * @author Mohammad Mahdi Malmsi
 * @version 0.0.7
 */
public class Printer
{

            /* Feilds */


    // Reset the color of the terminal
   private static final String RESET = "\033[0m"; 

   // indent
   private static final String indent = "\t\t\t\t  ";


    // Text colors
   private static final String BLACK_BRIGHT = "\033[90m";  
   private static final String RED_BRIGHT = "\033[91m";   
   private static final String GREEN_BRIGHT = "\033[92m";  
   private static final String YELLOW_BRIGHT = "\033[93m"; 
   private static final String BLUE_BRIGHT = "\033[94m";   
   private static final String PURPLE_BRIGHT = "\033[95m"; 
   private static final String CYAN_BRIGHT = "\033[96m";   
   private static final String WHITE_BRIGHT = "\033[97m";  


    // High Intensity backgrounds
   private static final String BLACK_BACKGROUND_BRIGHT = "\033[100m";
   private static final String RED_BACKGROUND_BRIGHT = "\033[101m";
   private static final String GREEN_BACKGROUND_BRIGHT = "\033[102m";
   private static final String YELLOW_BACKGROUND_BRIGHT = "\033[103m";
   private static final String BLUE_BACKGROUND_BRIGHT = "\033[104m";
   private static final String PURPLE_BACKGROUND_BRIGHT = "\033[105m"; 
   private static final String CYAN_BACKGROUND_BRIGHT = "\033[106m";  
   private static final String WHITE_BACKGROUND_BRIGHT = "\033[107m"; 
   
   




            /* Methods */

    /**
     * This method print the visual board in standard output(termianl)
     * Run this code in an Unix-base OS to see it colorfull =)
     * 
     * @param visualBoard : the visual board of the game
     * @param y_len : the width of the visual board
     * @param x_len : the lenght of the visual board
     */
    public static void printVisualBoard(char[][] visualBoard, int y_len, int x_len)
    {
        System.out.print(indent);


        System.out.print("      ");
        for (char k = 'A'; k <= 'H'; k++)
            System.out.print(YELLOW_BRIGHT + k + "      ");

        System.out.print("\n");



        for (int j = 0; j < y_len; j++)
        {
            System.out.print(indent);
            

            if (j%4 == 2)
                System.out.print(YELLOW_BRIGHT + (j/4 + 1) + " " + RESET);
            else
                System.err.print("  ");


            for (int i = 0; i < x_len; i++)
            {
                if (visualBoard[j][i] == '•' || visualBoard[j][i] == '-' || visualBoard[j][i] == '|')
                    System.out.print(GREEN_BRIGHT  + visualBoard[j][i]);

                else if (visualBoard[j][i] == 'X')
                    System.out.print(RED_BACKGROUND_BRIGHT + " ");
 
                else if (visualBoard[j][i] == 'O')
                    System.out.print(CYAN_BACKGROUND_BRIGHT + " ");

                else if (visualBoard[j][i] == 'W')
                    System.err.print(WHITE_BACKGROUND_BRIGHT + " ");

                else
                    System.out.print(visualBoard[j][i]);

                System.out.print(RESET);
            }

            System.out.print("\n");
        }
    }


    public static void printWrongChoose(Scanner finish)
    {
        System.out.println(indent + 
                            "\t     " + 
                                YELLOW_BACKGROUND_BRIGHT + 
                                    RED_BRIGHT + "<@ ! YOU CAN NOT CHOOSE THAT BLOCK ! @>" + RESET);

        System.out.println(indent + "\t\t    " + "(press enter to continue)");
        finish.nextLine();
    }


    public static void printTurn(Player player)
    {
        System.out.print("Hey " + player.getFirstName() + " it's your turn. choose a block: ");
    }

    public static void printMenu()
    {
        clear();

        System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println(indent + "\t\t  " + "🔵 <@~~~ Othello Game ~~~@> 🔴");
        System.out.print("\n\n");
        System.out.println(indent + "\t\t  " + "    1. new Two Player game");
        System.out.println(indent + "\t\t  " + "   2. new Single Player game");
        System.out.println(indent + "\t\t  " + "        3. game Rules");
        System.out.println(indent + "\t\t  " + "            4. help");
        System.out.print("\n");
        System.out.println(indent + "\t\t  " + "            5. exit");
        System.err.println(indent + "\t\t  " + "🔴 <@~~~~~~~~~~~~~~~~~~~~~@> 🔵");
    }

    private static void clear()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
