package java.src.main.java.workshop.plaintext2html;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PlaintextToHtmlConverter {
    String source;
    int characterIndex;

    SpecialCharacters specialCharacters = new SpecialCharacters();

    String fileName = "sample.txt";
    List<String> result = new ArrayList<>();
    List<String> convertedLine = new ArrayList<>();
    String characterToConvert;


    public String toHtml() throws Exception {
        source = read();
        return basicHtmlEncode();
    }

    protected String read() throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }



    private String basicHtmlEncode() {
        characterIndex = 0;
        goToNextChar();

        while (withinSourceLimit()) {
            if(characterToConvert.equals("\n"))
                addANewLine();
            else
                convertedLine.add(specialCharacters.getSpecialCharacters().get(characterToConvert));
            pushACharacterToTheOutput();
            goToNextChar();
        }
        addANewLine();
        return String.join("<br />", result);
    }

    private boolean withinSourceLimit() {
        return characterIndex < source.length();
    }

    private void goToNextChar() {
        characterToConvert = stashNextCharacterAndAdvanceThePointer();
    }

    private String stashNextCharacterAndAdvanceThePointer() {
        char c = source.charAt(characterIndex);
        this.characterIndex += 1;
        return String.valueOf(c);
    }

    private void addANewLine() {
        String line = String.join("", convertedLine);
        result.add(line);
    }

    private void pushACharacterToTheOutput() {
        convertedLine.add(characterToConvert);
    }
}
