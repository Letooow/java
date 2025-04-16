import java.io.*;

class MyScanner {
    
    private Reader reader;
    private int indexOfReader = 0;
    private char[] buffer;
    private int lenghtOfBuf = 0;
    private final int sizeBuf = 100;
    private boolean space;
    private String tranf = "";
    private StringBuilder builder = new StringBuilder();


    public MyScanner(String str) throws IOException {
        this.reader = new InputStreamReader(new ByteArrayInputStream(str.getBytes("utf-8")));
        initBuf();
    }

    public MyScanner(InputStream stream) throws IOException {
        this.reader = new InputStreamReader(stream, "utf-8");
        buffer = new char[sizeBuf];
        lenghtOfBuf = reader.read(buffer);
    }
    

    private void initBuf() {
        try {
            if (reader.ready()) {

                buffer = new char[36];
                lenghtOfBuf = reader.read(buffer);

            }
        } catch (IOException e) {
            System.err.println("ERRORRRR " + e.getMessage()) ;
        }
    }
    
    public boolean hasNext() throws IOException  {
        return ((indexOfReader < lenghtOfBuf) || reader.ready()) && lenghtOfBuf != -1;
    }

    public int nextChar() throws IOException {

        if (indexOfReader >= buffer.length) {
            
            initBuf();
            
            if (lenghtOfBuf == -1) {
                return -1;
            }
            
            indexOfReader = 0;
            return buffer[indexOfReader++];
        }

        return buffer[indexOfReader++];
    }
    
    public boolean hasNextInt() throws IOException {
        
        if (builder.length() > 0) {
            return true;
        }
        int charCodePoint = 0;
        boolean bNumb = false;
        while (hasNext()) {
            if (Character.isDigit(charCodePoint = nextChar()) || charCodePoint == '-') {
                builder.append((char) charCodePoint);
                bNumb = true;
            } else if (bNumb) {
                break;
            }
        }
        return bNumb;
    }

    public int nextInt() throws IOException {
        if (hasNextInt()) {

            String tranf = String.valueOf(builder);
            builder.setLength(0);

            return Integer.parseInt(tranf);

        } else {
            throw new IOException("No numbers in input");
        }
    }

    public boolean hasNextWord() throws IOException {
        
        if (builder.length() > 0) {
            return true;
        }
        space = false;
        char ch;
        while (hasNext()) {
            ch = (char) nextChar();
            boolean dash = (Character.getType(ch) == Character.DASH_PUNCTUATION || ch == '\'');
            if (Character.isLetter(ch) || dash) {
                builder.append(ch);
                space = true;
            } else if (space) {
                break;
            }
        }
        if (space) {
            return true;
        } else {
            return false;
        }
    }

    public String nextWord() throws IOException{
        if (hasNextWord()) {
            String tranf = String.valueOf(builder);
            builder.setLength(0);
            return tranf;

        } else {
            throw new IOException("Words not found");
        }
    }
    
    public boolean hasNextElement() throws IOException {
        
        if (builder.length() > 0) {
            return true;
        }
        space = false;
        char ch;
        while (hasNext()) {
            ch = (char) nextChar();
            boolean digit = Character.isDigit(ch) || ch == '-';
            if (Character.isLetter(ch) || digit) {
                space = true;
                builder.append(ch);
            } else if (space) {
                break;
            }
        }
        if (space) {
            return true;
        } else {
            return false;
        }
    }

    public String nextElement() throws IOException{
        if (hasNextElement()) {
            tranf = String.valueOf(builder);
            builder.setLength(0);
            return tranf;
        } else {
            throw new IOException("No such element");
        }
    }

    public boolean hasNextLine() throws IOException {
        if (!builder.isEmpty() || space) {
            return true;
        }
        space = false;
        char ch;
        while(hasNext()) {
            if ((ch = (char) nextChar()) == System.lineSeparator().charAt(System.lineSeparator().length() - 1)) {
                if (builder.isEmpty()) {
                    space = true;
                }
                break;
            } else {
                builder.append((char) ch);
            }
        }
        if (!builder.isEmpty() || space) {
            return true;
        } else {
            return false;
        }
    
    }
    public String nextLine() throws IOException {
        if (hasNextLine()) {
            tranf = String.valueOf(builder);
            space = false;
            builder.setLength(0);
            return tranf; 
        } else {
            throw new IOException("No such Elements");
        }
    }
    public void close() throws IOException{
        reader.close();
    }

}  
