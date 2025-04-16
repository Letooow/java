package markup;

public class Text implements Marks {
    private final String text;
    public Text(String text) {
        this.text = text;
    }
    @Override
    public void toMarkdown(StringBuilder res) {
        res.append(text);
    }
    @Override
    public void toBBCode(StringBuilder res) {
        res.append(text);
    }
}

