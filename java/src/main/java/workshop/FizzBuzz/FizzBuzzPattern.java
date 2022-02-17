package java.src.main.java.workshop.FizzBuzz;

public class FizzBuzzPattern implements PatternMatcher{
    @Override
    public boolean matches(int number) {
        return number % 15 == 0;
    }

    @Override
    public String generateResponse() {
        return "FizzBuzz";
    }
}
