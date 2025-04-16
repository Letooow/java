package markup;

import java.util.List;
public abstract class AbstractMarks {
    private final List<Marks> text;

    protected abstract String getDownMark();

    protected abstract String getBbMark();

    public AbstractMarks(List<Marks> text) {
        this.text = text;
    }

    public void toMarkdown(StringBuilder result) {
        result.append(getDownMark());
        for (Marks text1 : text) {
            text1.toMarkdown(result);
        }
        result.append(getDownMark());
    }


    public void toBBCode(StringBuilder result) {
        if (!getBbMark().isEmpty()) {
            result.append("[").append(getBbMark()).append("]");
            inner(result);
            result.append("[").append("/").append(getBbMark()).append("]");
        } else {
            inner(result);
        }
    }


    private void inner(StringBuilder result) {
        for (Marks text2 : text) {
            text2.toBBCode(result);
        }
    }
}
