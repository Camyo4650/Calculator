package view;

public enum Buttons {
    ONE     ("1"),
    TWO     ("2"),
    THREE   ("3"),
    FOUR    ("4"),
    FIVE    ("5"),
    SIX     ("6"),
    SEVEN   ("7"),
    EIGHT   ("8"),
    NINE    ("9"),
    ZERO    ("0"),
    DEC     ("."),

    EQUALS  ("=");

    private String text;

    private Buttons(String text) {
        this.setText(text);
    }

    public String getText() {
        return text;
    }

    private void setText(String text) {
        this.text = text;
    }

    
}
