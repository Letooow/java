import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


public class ReverseMinR {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int[][] reverse = new int[1][];
        int line = 0;
        while (in.hasNextLine()) {
            
            if (line == reverse.length) {
                reverse = Arrays.copyOf(reverse, line * 2);
            }
            String str = in.nextLine();
            // System.err.println(str);
            Scanner numb = new Scanner(str);
            int index = 0;
            
            reverse[line] = (str.length() == 0) ? new int[0] : new int[1];

            while (numb.hasNextInt()) {
                if (index == reverse[line].length) {
                    reverse[line] = Arrays.copyOf(reverse[line], index + 1);
                }
                reverse[line][index] = numb.nextInt();
                index += 1;
                
                }
                numb.close();
            line++;
            }
        in.close();
        
        for (int i = 0; i < line; i++) {
            int minEl = Integer.MAX_VALUE;
            for (int j = 0; j < reverse[i].length; j++) {   //Вывод чисел
                minEl = (reverse[i][j] < minEl) ? (reverse[i][j]) : (minEl);
                System.out.print(minEl + " ");
                // System.err.print(reverse[i][j] + " ");
            }
            System.out.println();
            // System.err.println();
        }
    }
}