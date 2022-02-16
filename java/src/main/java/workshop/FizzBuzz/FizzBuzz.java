package workshop.FizzBuzz;

import java.util.List;

/**
 * Requirements:
 * For factor of three print Fizz instead of the number
 * For factor of five print Buzz instead of the number
 * For numbers which are factors of both three and five print FizzBuzz instead of the number
 */
public class FizzBuzz {
    private List<PatternMatcher> patternMatchers;
    private PatternMatcher nullObjectPattern;
    private ResponseGeneratorInterface responseGenerator;

    public FizzBuzz(List<PatternMatcher> patternMatchers, PatternMatcher nullObjectPattern, ResponseGeneratorInterface responseGenerator) {
        super();
        this.patternMatchers = patternMatchers;
        this.nullObjectPattern = nullObjectPattern;
        this.responseGenerator = responseGenerator;
    }

    public String say(int number) {
        String strReturn = responseGenerator.generateResponse();

        for (PatternMatcher patternMatcher: patternMatchers) {
            if (patternMatcher.matches(number))
                strReturn = responseGenerator.generateResponse();
        }

        return String.valueOf(number);
    }

}
