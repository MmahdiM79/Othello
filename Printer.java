
/**
 * @author Mohammad Mahdi Malmsi
 * @version 0.0.2
 */
public class Printer
{

            /* Feilds */


    // Reset the color of the terminal
   private static final String RESET = "\033[0m"; 


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

    public static void printVisualBoard(char[][] visualBoard, int y_len, int x_len)
    {
        for (int j = 0; j < y_len; j++)
        {
            for (int i = 0; i < x_len; i++)
            {
                if (visualBoard[j][i] == '•' || visualBoard[j][i] == '-' || visualBoard[j][i] == '|')
                    System.out.print(GREEN_BRIGHT  + visualBoard[j][i]);

                else if (visualBoard[j][i] == 'X')
                    System.out.print(RED_BACKGROUND_BRIGHT + " ");

                else if (visualBoard[j][i] == 'O')
                    System.out.print(CYAN_BACKGROUND_BRIGHT + " ");

                else
                    System.out.print(visualBoard[j][i]);

                System.out.print(RESET);
            }

            System.out.print("\n");
        }
    }
}
