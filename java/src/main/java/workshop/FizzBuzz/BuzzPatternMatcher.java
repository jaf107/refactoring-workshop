package workshop.FizzBuzz;

public class BuzzPatternMatcher implements PatternMatcher, ResponseGeneratorInterface{

    @Override
    public boolean matches(int number) {
        return number % 5 == 0;
    }

    @Override
    public String generateResponse() {
        return "Buzz";
    }
}
