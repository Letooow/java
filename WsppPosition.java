import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;


public class WsppPosition {
    public static void main(String[] args) {
        String fileInputName = args[0];
        String fileOutputName = args[1];
        Map<String, ArrayList<Integer>> linkedHashMap = new LinkedHashMap<String, ArrayList<Integer>>();
        MyScanner scanner = null;
        try { 
            scanner = new MyScanner(new FileInputStream(fileInputName));
            String line;
            int lineNumber = 1;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                // System.err.println(line);
                MyScanner wordInLine = new MyScanner(line);
                ArrayList<String> words = new ArrayList<String>();

                while (wordInLine.hasNextWord()) {
                    String word = wordInLine.nextWord();
                    // System.err.println(word);
                    words.add(word.toLowerCase());
                }

                for (int i = 0; i < words.size(); i++) {
                    if (linkedHashMap.containsKey(words.get(i))) {

                        linkedHashMap.get(words.get(i)).add(lineNumber);
                        linkedHashMap.get(words.get(i)).add(words.size() - i);
                        // System.err.println(linkedHashMap);
                    } else {
                        linkedHashMap.put(words.get(i), new ArrayList<Integer>());
                        linkedHashMap.get(words.get(i)).add(lineNumber);
                        linkedHashMap.get(words.get(i)).add(words.size() - i);
                        // System.err.println(linkedHashMap);
                    }
                }
                lineNumber++;
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
                outFile.write(names + " " + lis.size()/2);

                for (int i = 0; i < lis.size(); i += 2) {
                    outFile.write(" " + lis.get(i) + ":" + lis.get(i + 1));
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
       