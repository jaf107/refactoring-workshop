package java.src.main.java.workshop.trivia;

import java.util.*;

import static java.util.Arrays.asList;

public class TriviaGame {
    private static final int NUM_CELLS = 12;
    private static final int NUM_QUES = 50;
    private static final List<Category> CATEGORIES = asList(Category.POP,Category.SCIENCE,Category.SPORTS,Category.ROCK);

    private final Map<Integer, Category> categoriesByPosition = new HashMap<>(NUM_CELLS);
    private final Question questionSet = new Question(NUM_QUES, CATEGORIES);

    private final Playerlist players = new Playerlist();

    public TriviaGame() {

        for (int i = 0; i < NUM_CELLS; i++) {
            categoriesByPosition.put(i, CATEGORIES.get(i % CATEGORIES.size()));
        }
    }

    public void add(String playerName) {
        players.add(playerName);
        announce(playerName + " was added");
        announce("They are player number " + players.size());
    }


    public void roll(int roll) {
        Player currentPlayer = players.currentPlayer();
        announce(currentPlayer + " is the current player");
        announce("They have rolled a " + roll);

        if (currentPlayer.isInPenaltyBox()) {
            if (wouldReleaseFromPenaltyBox(roll)) {
                currentPlayer.exitsPenaltyBox();
                announce(currentPlayer + " is getting out of the penalty box");
            } else {
                announce(currentPlayer+ " is not getting out of the penalty box");
                return;
            }

        }

        currentPlayer.moveTo(newPosition(currentPlayer.getPosition(), roll));

        Category currentCategory = categoryOf(currentPlayer.getPosition());


        announce(currentPlayer + "'s new location is " + currentPlayer.getPosition());
        announce("The category is " + currentCategory);
        announce(questionSet.nextQuestionIsAbout(currentCategory) );
    }

    private boolean wouldReleaseFromPenaltyBox(int roll) {
        return roll % 2 != 0;
    }

    private int newPosition(int currentPosition, int roll) {
        return (currentPosition + roll) % NUM_CELLS;
    }


    private Category categoryOf(int currentPosition) {
        return categoriesByPosition.get(currentPosition);
    }


    public boolean wasCorrectlyAnswered() {
        Player currentPlayer = players.currentPlayer();
        players.nextPlayer();
        if (currentPlayer.isInPenaltyBox()) {
            return true;
        } else {
            announce("Answer was correct!!!!");
            currentPlayer.reward(1);
            announce(currentPlayer + " now has " + currentPlayer.getPurse() + " Gold Coins.");

            return !currentPlayer.hasWon();

        }
    }

    public boolean wrongAnswer() {
        Player currentPlayer = players.currentPlayer();
        announce("Question was incorrectly answered");
        announce(currentPlayer + " was sent to the penalty box");
        currentPlayer.entersPenaltyBox();
        players.nextPlayer();
        return true;
    }

    protected void announce(Object message) {
        System.out.println(message);
    }
}