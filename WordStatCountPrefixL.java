import java.io.*;
import java.util.Arrays;
import java.util.Objects;

public class WordStatCountPrefixL {
    public static void main(String[] args) {
        String fileInputName = args[0];
        String fileOutputName = args[1];
        String[] namesOfPrefix = new String[1];
        int[] countsOfPrefix = new int[1];
        int indexOfPrefix = 0;
        BufferedReader file = null;
        try { 
            file = new BufferedReader(new InputStreamReader(new FileInputStream(fileInputName), "utf8"));
            int line = 0;
                // System.err.println(line);
                String word = "";
                
                while (true) {
                    line = file.read();
                    if (line == -1) {
                        break;
                    }
                    char w = (char)line;
                    int punctuation = Character.getType(w);
                    
                    punctuation = w == '\'' ? 0 : punctuation; 
                    boolean character = (punctuation > 2 & punctuation != Character.DASH_PUNCTUATION);
                    boolean searchIndex = false;
                    if (!character) {
                        word += w;
                    } else {
                        if (word.length() > 2) {
                            for (int i = 0; i <= countsOfPrefix.length - 1; i++) {           
                                if (Objects.equals(namesOfPrefix[i], word.substring(0, 3).toLowerCase())) {
                                    countsOfPrefix[i] += 1;
                                    searchIndex = true;
                                    break;
                                }
                            }
                            if (!searchIndex) {                                
                                namesOfPrefix[indexOfPrefix] = word.substring(0, 3).toLowerCase();   
                                countsOfPrefix[indexOfPrefix] += 1;
                                indexOfPrefix++;
                                if (indexOfPrefix == namesOfPrefix.length) { //Увеличение массива                            
                                    namesOfPrefix = Arrays.copyOf(namesOfPrefix, indexOfPrefix * 2);
                                    countsOfPrefix = Arrays.copyOf(countsOfPrefix, indexOfPrefix * 2);
                                }
                            }
                        }
                        word = "";
                    }
                }
            
            namesOfPrefix = Arrays.copyOf(namesOfPrefix, indexOfPrefix); //Срезание лишних элементов массива  
            countsOfPrefix = Arrays.copyOf(countsOfPrefix, indexOfPrefix);
    
            int transfer;
            String buffer = "";
            for (int i = 0; i <= indexOfPrefix - 1; i++) {
                for (int j = 0; j < indexOfPrefix - i - 1; j++) {
                    if (countsOfPrefix[j] > countsOfPrefix[j + 1]) {

                        transfer = countsOfPrefix[j];
                        countsOfPrefix[j] = countsOfPrefix[j + 1];
                        countsOfPrefix[j + 1] = transfer;

                        buffer = namesOfPrefix[j];
                        namesOfPrefix[j] = namesOfPrefix[j+1];
                        namesOfPrefix[j + 1] = buffer;
                    }                         
                }
            }
    
        } catch (IOException e) {      
            System.err.println("Error in reader: " + e.getMessage());
        } finally {
            try {
                file.close();
            } catch (IOException e) {
                System.err.println("Error in file.close " + e.getMessage());
            }
        }
        BufferedWriter outFile = null;
        try {  
            outFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileOutputName), "utf8"));
            for (int z = 0; z < indexOfPrefix; z++) {
                outFile.write(namesOfPrefix[z] + " " + countsOfPrefix[z] + "\n");
                }
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
       