// public class Sum {
//     public static void main(String[] args) {
//         int sum = 0;
//         for (String argument : args) {
//             String number = "";
//             char[] characters = argument.toCharArray();  
//             for (char c : characters) {
//                 if (Character.isWhitespace(c)) {
//                     sum += (number.length() == 0) ? (0) : (Integer.parseInt(number));
//                     number = "";
//                 } else {
//                     number += c;
//                 }
//             }
//             sum += (number.length() == 0) ? (0) : (Integer.parseInt(number));
//         }
//         System.out.println(sum);
//     }
// }

public class Sum {
    public static void main(String[] args) {
        int sum = 0;
        for (String argument : args) {
            boolean space = true;
            int index_start = 0;
            for (int index_end = 0; index_end < argument.length(); index_end++) {
                if (Character.isWhitespace(argument.charAt(index_end)) & !space) {
                    sum += Integer.parseInt(argument.substring(index_start, index_end));
                    space = true;
                } else if (!Character.isWhitespace(argument.charAt(index_end)) & space) {
                    index_start = index_end;
                    space = false;
                }
            }
            sum = (!space) ? (sum + Integer.parseInt(argument.substring(index_start, argument.length()))) : (sum);
        }
        System.out.println(sum);
    } 
}
