public class SumDouble {
    public static void main(String[] args) {
        double sum = 0.0;
        for (String argument : args) {
            boolean space = true;
            int index_start = 0;
            for (int index_end = 0; index_end < argument.length(); index_end++) {
                if (Character.isWhitespace(argument.charAt(index_end)) & !space) {
                    sum += Double.parseDouble(argument.substring(index_start, index_end));
                    space = true;
                } else if (!Character.isWhitespace(argument.charAt(index_end)) & space) {
                    index_start = index_end;
                    space = false;
                }
            }
            sum = (!space) ? (sum + Double.parseDouble(argument.substring(index_start, argument.length()))) : (sum);
        }
        System.out.println(sum);
    } 
}