package java.src.main.java.workshop.plaintext2html;

import java.util.HashMap;
import java.util.Map;

public class SpecialCharacters {
    Map<String, String> specialCharacters = new HashMap<>();

    public Map<String, String> getSpecialCharacters() {
        return specialCharacters;
    }

    public SpecialCharacters() {
        specialCharacterMapConstruction();
    }

    void specialCharacterMapConstruction() {
        specialCharacters.put("<","&lt;" );
        specialCharacters.put(">","&gt;" );
        specialCharacters.put("&","&amp;" );
        specialCharacters.put("\n","\n" );
    }
}
