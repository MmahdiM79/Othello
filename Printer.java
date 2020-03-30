
/**
 * @author Mohammad Mahdi Malmsi
 * @version 0.0.1
 */
public class Printer
{

            /* Feilds */


    // Reset the color of the terminal
   private static final String RESET = "\033[0m"; 

    
    // Underline
   private static final String BLACK_UNDERLINED = "\033[4;30m"; 
   private static final String RED_UNDERLINED = "\033[4;31m"; 
   private static final String GREEN_UNDERLINED = "\033[4;32m"; 
   private static final String YELLOW_UNDERLINED = "\033[4;33m"; 
   private static final String BLUE_UNDERLINED = "\033[4;34m"; 
   private static final String PURPLE_UNDERLINED = "\033[4;35m"; 
   private static final String CYAN_UNDERLINED = "\033[4;36m"; 
   private static final String WHITE_UNDERLINED = "\033[4;37m"; 


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
   
   



   
    public static void printVisualBoard(char[][] visualBoard, int y_len, int x_len)
    {
        for (int j = 0; j < y_len; j++)
        {
            for (int i = 0; i < x_len; i++)
            {
                if (visualBoard[j][i] == '•' || visualBoard[j][i] == '-' || visualBoard[j][i] == '|')
                    System.out.print(RED_BACKGROUND_BRIGHT + YELLOW_BRIGHT + visualBoard[j][i] + RESET);
                else
                    System.out.print(visualBoard[j][i]);
            }

            System.out.print("\n");
        }
    }
}
