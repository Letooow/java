public class Sum1 {
    public static void main(String[] args) {
        int sum = 0;
        for (String arg : args) {
            char[] array = arg.toCharArray();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i <= array.length; i++) {
                if (i == array.length || Character.isWhitespace(array[i])) {
                    sum += Integer.parseInt(sb.toString());
                    sb.setLength(0);
                } else {
                    sb.append(array[i]);
                }
            }
        }
        System.out.println(sum);
    }
}
