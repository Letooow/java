import java.io.IOException;
import java.util.Arrays;

public class Reverse {

    public static void main(String[] args) throws IOException {
        MyScanner in = new MyScanner(System.in);
        int[][] reverse = new int[1][];
        int line = 0;
        while (in.hasNextLine()) {
            
            if (line == reverse.length) {
                reverse = Arrays.copyOf(reverse, line * 2);
            }
            String str = in.nextLine();
//            System.err.println(str);
            MyScanner numb = new MyScanner(str);
            
            int index = 0;
            
            // reverse[line] = (str.length() == 1) ? new int[0] : new int[1];
            reverse[line] = new int[0];
            // System.err.println(str.length() + " " + reverse[line].length); 
            while (numb.hasNextInt()) {
                if (index == reverse[line].length) {
                    reverse[line] = Arrays.copyOf(reverse[line], (index + 1) * 2);
                }
                reverse[line][index] = numb.nextInt();
                
                // System.err.println(Arrays.toString(reverse[line]));
                index += 1;
                
            }
            numb.close();
            reverse[line] = Arrays.copyOf(reverse[line], index);
            line++;
            }

        in.close();
        
        for (int i = line - 1; i > - 1; i--) {
            for (int j = 0; j < reverse[i].length; j++) {   //Вывод чисел
                System.out.print(reverse[i][reverse[i].length - 1 - j] + " ");
                // System.err.print(reverse[i][reverse[i].length - 1 - j] + " ");
            }
                System.out.println();
                // System.err.println();
        }
    }
}