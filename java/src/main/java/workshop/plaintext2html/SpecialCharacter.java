package java.src.main.java.workshop.plaintext2html;

public enum SpecialCharacter {
    LESS("<", "&lt"), GREATER(">", "&gt"), AMPERSAND("&", "&amp");//, NEWLINE("\n", "");

    private final String character;
    private final String value;

    SpecialCharacter(String character, String value) {
        this.character = character;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return getValue();
    }
}
