package markup;

import java.util.List;

public class Emphasis extends AbstractMarks implements Marks {
    public Emphasis(List<Marks> text) {
        super(text);
    }

    @Override
    protected String getDownMark() {
        return "*";
    }

    @Override
    protected String getBbMark() {
        return "i";
    }
}
