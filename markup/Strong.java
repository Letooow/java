package markup;

import java.util.List;

public class Strong extends AbstractMarks implements Marks {
    public Strong(List<Marks> text) {
        super(text);
    }

    @Override
    protected String getDownMark() {
        return "__";
    }

    @Override
    protected String getBbMark() {
        return "b";
    }
}
