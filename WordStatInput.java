import java.io.*;
import java.util.Arrays;
import java.util.Objects;

public class WordStatInput {
    public static void main(String[] args) {
        String fileInputName = args[0];
        String fileOutputName = args[1];
        String[] namesOfWords = new String[1];
        int[] countsOfWords = new int[1];
        int indexOfWords = 0;
        try { 
            BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(fileInputName), "utf8"));
            String line;
            while ((line = file.readLine())  != null) {     //line - одна строка из файла
                line += " ";
                int indexStart = 0;
                boolean space = true;

                for (int indexEnd = 0; indexEnd < line.length(); indexEnd++) {      //Перебор на добавление слов

                    int punctuation = Character.getType(line.charAt(indexEnd));
                    punctuation = line.charAt(indexEnd) == '\'' ? 0 : punctuation;  //Выявление ' как символ
                    boolean character = (punctuation > 2 & punctuation != Character.DASH_PUNCTUATION);      //Проверка знака, чем он является

                    if ((character) & !space) {
                        boolean searchIndex = true;
                        String word = line.substring(indexStart, indexEnd).toLowerCase();   //Инициализация слова из строки

                        if (indexOfWords == namesOfWords.length) {                              //Увеличение массива, когда он становится предельным
                            namesOfWords = Arrays.copyOf(namesOfWords, indexOfWords * 2);
                            countsOfWords = Arrays.copyOf(countsOfWords, indexOfWords * 2);
                        }
                        for (int i = 0; i <= indexOfWords; i++) {           //Поиск слова в массиве, если оно есть то колличество слов +1
                            if (Objects.equals(namesOfWords[i], word)) {
                                countsOfWords[i] += 1;
                                searchIndex = true;
                                break;
                            }
                            searchIndex = false;
                        }
                        if (!searchIndex) {
                            namesOfWords[indexOfWords] = word;              //Если нет, то слово добавляется в массив
                            countsOfWords[indexOfWords] += 1;
                            indexOfWords++;
                        }
                        space = true;
                    } else if ((!character) & space) { 
                        indexStart = indexEnd;                              //Индекс начала слова
                        space = false;
                    }               
                }
            }
            file.close();
            
            namesOfWords = Arrays.copyOf(namesOfWords, indexOfWords);       //Срезание массивов до нужного размера
            countsOfWords = Arrays.copyOf(countsOfWords, indexOfWords);        
            
            try (BufferedWriter outFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileOutputName), "utf8"))) {   //Запись в файл
                for (int i = 0; i < indexOfWords; i++) {       //Вывод
                    outFile.write(namesOfWords[i] + " " + countsOfWords[i] + "\n");
                }
                outFile.close();
            } catch (IOException o) {                                   // Вывод ошибок          
                System.err.println("Error out: " + o.getMessage());
            }
        } catch (IOException e) {      
            System.err.println("error: " + e.getMessage());
        }
    }
}