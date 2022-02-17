package java.src.main.java.workshop.plaintext2html;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PlaintextToHtmlConverter {
//    String source;
    int characterIndex;

    SpecialCharacters specialCharacters = new SpecialCharacters();

    String fileName = "sample.txt";
    List<String> result = new ArrayList<>();
    List<String> convertedLine = new ArrayList<>();
    String characterToConvert;


    public String toHtml() throws Exception {
        String text = read();
        return basicHtmlEncode(text);
    }

    protected String read() throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }



    private String basicHtmlEncode(String source) {
        characterIndex = 0;
        characterToConvert = stashNextCharacterAndAdvanceThePointer(source);

        while (characterIndex < source.length()) {
            convertedLine.add(specialCharacters.getSpecialCharacters().get(characterToConvert));
            pushACharacterToTheOutput();
            characterToConvert = stashNextCharacterAndAdvanceThePointer(source);
        }
        addANewLine();
        return String.join("<br />", result);
    }

    private String stashNextCharacterAndAdvanceThePointer(String source) {
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
