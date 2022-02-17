package java.src.main.java.workshop.plaintext2html;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class PlaintextToHtmlConverter {
//    String source;
    int i;
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
        i = 0;
        result = new ArrayList<>();
        convertedLine = new ArrayList<>();
        characterToConvert = stashNextCharacterAndAdvanceThePointer(source);

        while (i <= source.length()) {
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

            if (i >= source.length())
                break;
            characterToConvert = stashNextCharacterAndAdvanceThePointer(source );
        }
        addANewLine();
        String finalResult = String.join("<br />", result);
        return finalResult;
    }

    //pick the character from source string
    //and increment the pointer
    private String stashNextCharacterAndAdvanceThePointer(String source) {
        char c = source.charAt(i);
        i += 1;
        return String.valueOf(c);
    }

    //stringfy convertedLine array and push into result
    //reset convertedLine
    private void addANewLine() {
        String line = String.join("", convertedLine);
        result.add(line);
        convertedLine = new ArrayList<>();
    }

    private void pushACharacterToTheOutput() {
        convertedLine.add(characterToConvert);
    }
}
