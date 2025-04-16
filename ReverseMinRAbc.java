import java.io.IOException;
import java.util.Arrays;


public class ReverseMinRAbc {

    public static String decode(int x) {
        StringBuilder numb = new StringBuilder();
        char[] array = String.valueOf(x).toCharArray();
        for (char ch : array) {
            if (ch == '-') {
                numb.append(ch);
            } else if (Integer.valueOf(ch) > 96 && Integer.valueOf(ch) < 107) {
                numb.append(ch);
            } else if (Character.isDigit(ch)) {
                numb.append(Character.toChars(Integer.valueOf(ch) + 49));
            }

        }      
        return String.valueOf(numb);
    }
    
    public static int undecode(String x) {
        StringBuilder numb = new StringBuilder();
        char[] array = x.toLowerCase().toCharArray();
        for (char ch : array) {
            if (ch == '-') {
                numb.append(ch);
            } else if (Integer.valueOf(ch) > 96 && Integer.valueOf(ch) < 107) {
                numb.append(Character.toChars(Integer.valueOf(ch) - 49));
            } else if (Character.isDigit(ch)) {
                numb.append(ch);
            }

        }
        return Integer.parseInt(String.valueOf(numb));
    }
    public static void main(String[] args) {
        MyScanner in = null;
        int[][] reverse = new int[1][];
        int line = 0;
        try {
            in = new MyScanner(System.in);
            while (in.hasNextLine()) {
                
                if (line == reverse.length) {
                    reverse = Arrays.copyOf(reverse, line * 2);
                }
                String str = in.nextLine();
                // System.err.println(str);
                MyScanner number = null;
                int index = 0;
                try {

                    number = new MyScanner(str);
                
                    
                    reverse[line] = new int[0];
                    // System.err.println(str.length() + " " + reverse[line].length); 
                    while (number.hasNextElement()) {
                        if (index == reverse[line].length) {
                            reverse[line] = Arrays.copyOf(reverse[line], (index + 1) * 2);
                        }
                        reverse[line][index] = undecode(number.nextElement());
                        
                        // System.err.println(Arrays.toString(reverse[line]));
                        index += 1;
                        
                    }

                } catch (IOException e) {
                    System.err.println("Error in scanner2: " + e.getMessage());
                } finally {
                    try {
                        number.close();
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }
                }
                reverse[line] = Arrays.copyOf(reverse[line], index);
                line++;
                
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
        
        for (int i = 0; i < line; i++) {
            int minEl = Integer.MAX_VALUE;
            for (int j = 0; j < reverse[i].length; j++) {   //Вывод чисел
                minEl = (reverse[i][j] < minEl) ? (reverse[i][j]) : (minEl);
                System.out.print(decode(minEl) + " ");
                // System.err.print(reverse[i][j] + " ");
            }
            System.out.println();
            // System.err.println();
        }
    }
}