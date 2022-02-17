package java.src.main.java.workshop.plaintext2html;

public enum SpecialCharacter {
    LESS("<"), GREATER(">"), AMPERSAND("&"), NEWLINE("\n");

    private final String character;

    SpecialCharacter(String character) {
        this.character = character;
    }

    @Override
    public String toString() {
        return character;
    }
}
