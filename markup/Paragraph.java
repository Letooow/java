package markup;

import java.util.List;

public class Paragraph extends AbstractMarks implements Marks {
    public Paragraph(List<Marks> text) {
        super(text);
    }

    @Override
    protected String getDownMark() {
        return "";
    }
    protected String getBbMark() {
        return "";
    }
}