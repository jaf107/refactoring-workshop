package workshop.FizzBuzz;

public class NullResponse implements PatternMatcher, ResponseGeneratorInterface{

    @Override
    public boolean matches(int number) {
        return false;
    }

    @Override
    public String generateResponse() {
        return "";
    }
}
