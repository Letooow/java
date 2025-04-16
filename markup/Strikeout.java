package markup;

import java.util.List;

public class Strikeout extends AbstractMarks implements Marks {
    public Strikeout(List<Marks> text) {
        super(text);
    }

    @Override
    protected String getDownMark() {
        return "~";
    }

    @Override
    protected String getBbMark() {
        return "s";
    }
}
