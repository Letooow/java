import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class Wspp {
    public static void main(String[] args) {
        String fileInputName = args[0];
        String fileOutputName = args[1];
        Map<String, ArrayList<Integer>> linkedHashMap = new LinkedHashMap<String, ArrayList<Integer>>();
        MyScanner scanner = null;
        try { 
            scanner = new MyScanner(new FileInputStream(fileInputName));
            int line = 0;
                // System.err.println(line);
            String word = "";
            int index = 1;    
            // int indexEnd = 0;
            // int indexOfFile = 0;
            // int lineOfFile = 0; 
                while (scanner.hasNext()) {
                    line = scanner.nextChar();
                    char w = (char)line;
                    // boolean separator = (w == System.lineSeparator().charAt(System.lineSeparator().length() - 1));
                    // if ((separator) && scanner.hasNext()) {
                    //     lineOfFile += 1;
                    //     indexEnd = indexOfFile;
                    // }
                    int punctuation = Character.getType(w);
                    punctuation = w == '\'' ? 0 : punctuation; 
                    boolean character = (punctuation > 2 & punctuation != Character.DASH_PUNCTUATION);
                    // System.err.println((char) line);
                    if (!character ) {
                        word += w;
                    } else {
                        if (!word.isEmpty()) {
                            word = word.toLowerCase();
                            if (linkedHashMap.containsKey(word)) {

                                linkedHashMap.get(word).add(index++);
                                int count = linkedHashMap.get(word).get(0);
                                linkedHashMap.get(word).set(0, count + 1);

                            } else {

                                linkedHashMap.put(word, new ArrayList<Integer>());
                                linkedHashMap.get(word).add(1);
                                linkedHashMap.get(word).add(index++);
                    
                            }
                            // System.err.println(linkedHashMap);
                        }
                        word = "";
                    }
                    // indexOfFile++;
                }

        } catch (IOException e) {      
            System.err.println("Error in reader: " + e.getMessage());
        } finally {
            try {
                scanner.close();
            } catch (IOException e) {
                System.err.println("Error in scanner close: " + e.getMessage());
            }
        }
        BufferedWriter outFile = null;
        try {  
            outFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileOutputName), "utf8"));
            for (String names : linkedHashMap.keySet()) {
                ArrayList<Integer> lis = linkedHashMap.get(names);
                outFile.write(names);
                for (int value : lis) {
                    outFile.write(" " + value);
                }
                outFile.write(System.lineSeparator());
            }
            outFile.close();
            } catch (IOException o) {                                   // Вывод ошибок          
                System.err.println("Error in writer: " + o.getMessage());
            } finally {
                try {
                    outFile.close();
                } catch (IOException e) {
                    System.err.println("Error in outFile.close " + e);
                }
            }
    }
}
       