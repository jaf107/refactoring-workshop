package workshop.FizzBuzz;

public class FizzPatternMatcher implements PatternMatcher, ResponseGeneratorInterface{
    @Override
    public boolean matches(int number) {
        return number % 3 == 0;
    }

    @Override
    public String generateResponse() {
        return "Fizz";
    }
}
