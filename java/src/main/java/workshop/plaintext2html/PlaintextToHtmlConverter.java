package java.src.main.java.workshop.plaintext2html;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class PlaintextToHtmlConverter {
//    String source;
    int characterIndex;
    List<SpecialCharacter> specialCharacters = asList(SpecialCharacter.LESS,SpecialCharacter.GREATER,SpecialCharacter.AMPERSAND,SpecialCharacter.AMPERSAND);

    String fileName = "sample.txt";
    List<String> result = new ArrayList<>();
    List<String> convertedLine = new ArrayList<>();
    String characterToConvert;

    public String toHtml() throws Exception {
        String text = read();
        String htmlLines = basicHtmlEncode(text);
        return htmlLines;
    }

    protected String read() throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    private String basicHtmlEncode(String source) {
        characterIndex = 0;
        characterToConvert = stashNextCharacterAndAdvanceThePointer(source,characterIndex);

        while (characterIndex <= source.length()) {
            switch (characterToConvert) {
                case "<":
                    convertedLine.add("&lt;");
                    break;
                case ">":
                    convertedLine.add("&gt;");
                    break;
                case "&":
                    convertedLine.add("&amp;");
                    break;
                case "\n":
                    addANewLine();
                    break;
                default:
                    pushACharacterToTheOutput();
            }

            if (characterIndex >= source.length())
                break;
            characterToConvert = stashNextCharacterAndAdvanceThePointer(source, characterIndex);
        }
        addANewLine();
        String finalResult = String.join("<br />", result);
        return finalResult;
    }

    //pick the character from source string
    //and increment the pointer
    private String stashNextCharacterAndAdvanceThePointer(String source, int characterIndex) {
        char c = source.charAt(characterIndex);
        this.characterIndex += 1;
        return String.valueOf(c);
    }

    //stringfy convertedLine array and push into result
    //reset convertedLine
    private void addANewLine() {
        String line = String.join("", convertedLine);
        result.add(line);
    }

    private void pushACharacterToTheOutput() {
        convertedLine.add(characterToConvert);
    }
}
