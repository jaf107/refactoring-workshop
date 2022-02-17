package java.src.main.java.workshop.FizzBuzz;

import java.util.List;

/**
 * Requirements:
 * For factor of three print Fizz instead of the number
 * For factor of five print Buzz instead of the number
 * For numbers which are factors of both three and five print FizzBuzz instead of the number
 */
public class FizzBuzz {
    private final List<PatternMatcher> patternMatchers;
    private final PatternMatcher nullObjectPattern;
    private String nameOfPattern = "";

    public FizzBuzz(List<PatternMatcher> patternMatchers, PatternMatcher nullObjectPattern) {
        super();
        this.patternMatchers = patternMatchers;
        this.nullObjectPattern = nullObjectPattern;
    }

    public String say(int number) {
        String strReturn = nullObjectPattern.generateResponse();

        for (PatternMatcher patternMatcher: patternMatchers) {
            if (patternMatcher.matches(number))
                strReturn = patternMatcher.generateResponse();
        }
        setNameOfPattern(strReturn);
        return String.valueOf(number);
    }

    public void setNameOfPattern(String nameOfPattern) {
        this.nameOfPattern = nameOfPattern;
    }
}
