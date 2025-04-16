package md2html;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class Md2Html {

    public static void main(String[] args) throws IOException {
//        String fileName = "test2.txt";
//        String fileOutName = "test.out";
        String fileName = args[0];
        String fileOutName = args[1];
        Scanner scanner = new Scanner(new File(fileName), StandardCharsets.UTF_8);
        BufferedWriter outFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileOutName), StandardCharsets.UTF_8));
        StringBuilder result = new StringBuilder();
        StringBuilder builder = new StringBuilder();

        boolean latticeWas = false;
        boolean title = false;
        boolean paragraph = false;

        boolean isEmphasisStart = false;
        boolean isStrongStart = false;
        boolean isStrikeoutStart = false;
        boolean isCode = false;
        boolean isSpace = false;
        boolean starWas = false;
        boolean strikeWas = false;
        boolean backSlashWas = false;
        boolean isQuoteStart = false;
        boolean quoteWas = false;
        boolean isAppend = false;
        StringBuilder mark1 = new StringBuilder();
        int countOfLattice = 0;
        int countOfEmptyLines = 0;
        int indexLines = 0;
        ArrayList<String> mark = new ArrayList<>();
        mark.add("*");
        mark.add("--");
        mark.add("`");
        mark.add("**");
        mark.add("_");
        mark.add("__");
        mark.add("''");
        while (scanner.hasNextLine()) {
            char[] line = scanner.nextLine().toCharArray();
//            System.err.println(line);
            if (line.length == 0 && indexLines == 0) {
                continue;
            }

            if (line.length == 0) { //Если на вход пришла строка с размером -
                if (countOfEmptyLines > 0) {
                    continue;
                }
                countOfEmptyLines += 1;

                if (title) {
                    builder.append("</h").append(countOfLattice).append(">");
                    countOfLattice = 0;
                } else if (paragraph) {
                    builder.append("</p>");
                }
                title = false;
                paragraph = false;
                result.append(builder).append(System.lineSeparator());
//                System.err.print(result);
                result.setLength(0);
                 try {
                    outFile.write(String.valueOf(builder));
                    outFile.write(System.lineSeparator());
                } catch (IOException e) {
                    System.err.println("Error: " + e.getMessage());
                }
                builder.setLength(0);

            } else if (!builder.isEmpty() && (title || paragraph)) {
                builder.append(System.lineSeparator());
            } else {
                countOfEmptyLines = 0;
            }

            int index = 0;

            while (index < line.length) {
                if (builder.isEmpty()) { //Если первый новый абзац

                    if (line[index] == '#') { //Если первый символ решетка то добавить в колличество решеток
                        countOfLattice++;
                        latticeWas = true;
                        index++;

                    } else if (latticeWas && line[index] == '#') {
                        countOfLattice++;
                        index++;

                    } else if (latticeWas && line[index] == ' ') {
                        latticeWas = false;
                        builder.append("<h").append(countOfLattice).append(">");
                        isSpace = true;

                        title = true;
                        index++;

                    } else if (latticeWas && line[index] != ' ') {
                        builder.append("<p>");

                        builder.append("#".repeat(Math.max(0, countOfLattice)));
                        countOfLattice = 0;
                        paragraph = true;

                        switch (line[index]) {
                            case '*', '_' -> {
                                starWas = true;
                                mark1.append(line[index]);
                            }
                            case '-' -> {
                                strikeWas = true;
                                mark1.append(line[index]);
                            }
                            case '\\' -> backSlashWas = true;
                            case '`' -> {
                                isCode = true;
                                mark1.append(line[index]);
                            }
                            case '\'' -> {
                                quoteWas = true;
                                mark1.append(line[index]);
                            }
                            default -> builder.append(line[index]);
                        }
                        index++;

                    } else if (!latticeWas) {
                        builder.append("<p>");
                        paragraph = true;

                        switch (line[index]) {
                            case '*', '_' -> {
                                starWas = true;
                                mark1.append(line[index]);
                            }
                            case '-' -> {
                                strikeWas = true;
                                mark1.append(line[index]);
                            }
                            case '\\' -> backSlashWas = true;
                            case '`' -> {
                                isCode = true;
                                mark1.append(line[index]);
                            }
                            case '\'' -> {
                                quoteWas = true;
                                mark1.append(line[index]);
                            }
                            default -> builder.append(line[index]);
                        }
                        index++;
                    }

                } else {

                    if (!backSlashWas) {

                        if (line[index] == '-') {
                            mark1.append(line[index]);
                            if (strikeWas && !isStrikeoutStart) {
                                isStrikeoutStart = true;
                                builder.append("<s>");
                            } else if (strikeWas) {
                                isStrikeoutStart = false;
                                strikeWas = false;
                                builder.append("</s>");
                                index++;
                                continue;
                            }
                            strikeWas = true;
                            index++;

                        } else if (line[index] == '\'') {
                            mark1.append(line[index]);
                            if (quoteWas && !isQuoteStart) {
                                isQuoteStart = true;
                                builder.append("<q>");
                            } else if (quoteWas) {
                                isQuoteStart = false;
                                quoteWas = false;
                                builder.append("</q>");
                                index++;
                                continue;
                            }
                            quoteWas = true;
                            index++;

                        } else if (line[index] == '`') {
                            mark1.append(line[index]);
                            if (!isCode) {
                                builder.append("<code>");
                                isCode = true;
                            } else {
                                builder.append("</code>");
                                isCode = false;
                            }
                            index++;

                        } else if (line[index] == '*' || line[index] == '_') {
                            mark1.append(line[index]);
                            if (starWas) {

                                if (!isStrongStart) {
                                    isStrongStart = true;
                                    starWas = false;
                                    builder.append("<strong>");
                                } else {
                                    isStrongStart = false;
                                    starWas = false;
                                    builder.append("</strong>");
                                }
                                index++;
                                continue;
                            }
                            isAppend = false;
                            starWas = true;
                            index++;

                        } else {
                            if (line[index] == '\\') {
                                if (quoteWas) {
                                    builder.append("'");
                                }
                                backSlashWas = true;
                                index++;
                                continue;
                            }
                            if (line[index] == ' ' && mark.contains(mark1.toString()) && isSpace) {
                                builder.append(mark1).append(line[index++]);

                                switch (mark1.toString()) {
                                    case "*", "_", "**", "__" -> starWas = false;
                                    case "--" -> isStrikeoutStart = false;
                                    case "`" -> isCode = false;
                                    case "''" -> isQuoteStart = false;
                                }
                                mark1.setLength(0);
                                continue;
                            }

                            if (starWas) {
                                if (!isEmphasisStart && !isAppend) {
                                    builder.append("<em>");
                                    isEmphasisStart = true;
                                    isAppend = true;
                                } else if (isEmphasisStart && !isAppend){
                                    builder.append("</em>");
                                    isEmphasisStart = false;
                                    isAppend = true;
                                }
                            }

                            isSpace = (line[index] == ' ');

                            starWas = false;
                            if (mark1.toString().equals("-")) {
                                builder.append('-');
                            } else if (mark1.toString().equals("'")) {
                                builder.append('\'');
                                quoteWas = false;
                            }
                            mark1.setLength(0);

                            switch (line[index]) {
                                case '&' -> builder.append("&amp;");
                                case '>' -> builder.append("&gt;");
                                case '<' -> builder.append("&lt;");
                                default -> builder.append(line[index]);
                            }
                            index++;
                        }
                    } else {
                        backSlashWas = false;
                        builder.append(line[index++]);
                    }
                }

            }
            indexLines++;
            if (line.length > 1 && line[index - 2] == ' ') {
                switch (line[index - 1]) {
                    case '*' -> {
                        builder.append('*');
                        isEmphasisStart = false;
                    }
                    case '-' -> builder.append('-');
                    case '_' -> {
                        builder.append('_');
                        isEmphasisStart = false;
                    }
                    case '`' -> {
                        builder.append('`');
                        isCode = false;
                    }
                    case '\'' -> builder.append('\'');
                }
            }
            if (isEmphasisStart && starWas) {
                builder.append("</em>");
                isEmphasisStart = false;
            }
            starWas = false;
            strikeWas = false;
            quoteWas = false;
        }
        scanner.close();
        if (title) {
            builder.append("</h").append(countOfLattice).append(">");
        } else if (paragraph) {
            builder.append("</p>");
        }
        result.append(builder).append(System.lineSeparator());
//        System.err.print(result);
        result.setLength(0);
        try {
            outFile.write(String.valueOf(builder));
            outFile.write(System.lineSeparator());
        } catch (IOException e) {
            System.err.println("Error" + e.getMessage());
        } finally {
            try {
                outFile.close();
            } catch (IOException e) {
                System.err.println("Error" + e.getMessage());
            }
        }
        result.setLength(0);
        builder.setLength(0);
        long usedBytes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.err.println(usedBytes / (1024 * 1024));
    }
}
