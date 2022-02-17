package java.src.main.java.workshop.FizzBuzz;

public class NullResponse implements PatternMatcher{

    @Override
    public boolean matches(int number) {
        return false;
    }

    @Override
    public String generateResponse() {
        return "";
    }
}
