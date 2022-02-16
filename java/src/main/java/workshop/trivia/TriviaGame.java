package java.src.main.java.workshop.trivia;

import java.util.*;

public class TriviaGame {
    private static final int NB_CELLS = 12;
    private static final Category[] CATEGORIES = new Category[]{Category.POP,Category.SCIENCE,Category.SPORTS,Category.ROCK};

    private final Map<Integer, Category> categoriesByPosition = new HashMap<>(NB_CELLS);
    private final Map<Category, List<String>> questionsByCategory = new HashMap<>();

    private final ArrayList<String> players = new ArrayList<>();
    private final int[] places = new int[6];
    private final int[] purses = new int[6];
    private final boolean[] inPenaltyBox = new boolean[6];

    int currentPlayer = 0;
//    boolean isGettingOutOfPenaltyBox;

    public TriviaGame() {
        for (Category c: CATEGORIES) {
            questionsByCategory.put(c, new LinkedList<>());
        }
        for (int i = 0; i < 50; i++) {
            for (Category c:CATEGORIES) {
                questionsByCategory.get(c).add(c + " Question " + i);
            }
        }

        for (int i = 0; i < NB_CELLS; i++) {
            categoriesByPosition.put(i, CATEGORIES[i % CATEGORIES.length]);
        }
    }

    public void add(String playerName) {


        players.add(playerName);
        places[players.size()] = 0;
        purses[players.size()] = 0;
        inPenaltyBox[players.size()] = false;

        announce(playerName + " was added");
        announce("They are player number " + players.size());

    }


    public void roll(int roll) {
        String playerName = players.get(currentPlayer);
        announce(playerName + " is the current player");
        announce("They have rolled a " + roll);

        if (inPenaltyBox[currentPlayer]) {
            if (roll % 2 != 0) {
                inPenaltyBox[currentPlayer] = false;
                announce(playerName + " is getting out of the penalty box");
            } else {
                announce(playerName + " is not getting out of the penalty box");
                return;
            }

        }

        int currentPosition = postionOf(currentPlayer);
        moveTo(currentPlayer, newPosition(currentPosition, roll));
        Category currentCategory = categoryOf(currentPosition);


        announce(playerName + "'s new location is " + postionOf(currentPlayer));
        announce("The category is " + categoryOf(currentPosition));
        announce(nextQuestionIsAbout(currentCategory));
    }

    private int newPosition(int currentPosition, int roll) {
        return (currentPosition + roll) % NB_CELLS;
    }

    private void moveTo(int currentPlayer, int roll) {
        places[currentPlayer] = (postionOf(currentPlayer) + roll) % NB_CELLS;
    }

    private Integer postionOf(int currentPlayer) {
        return places[currentPlayer];
    }

    private int nextPlayer(){
        return (currentPlayer + 1) % players.size();
    }

    private String nextQuestionIsAbout(Category currentCategory) {
        return questionsByCategory.get(currentCategory).remove(0);
    }


    public boolean wasCorrectlyAnswered() {
        if (inPenaltyBox[currentPlayer]) {
            currentPlayer = nextPlayer();
            return true;
        } else {

            announce("Answer was correct!!!!");
            purses[currentPlayer]++;
            announce(players.get(currentPlayer) + " now has " + purses[currentPlayer] + " Gold Coins.");

            boolean gameContinues = !hasWon(currentPlayer);
            currentPlayer = nextPlayer();

            return gameContinues;
        }
    }

    public boolean wrongAnswer() {
        announce("Question was incorrectly answered");
        announce(players.get(currentPlayer) + " was sent to the penalty box");
        inPenaltyBox[currentPlayer] = true;

        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
        return true;
    }

    private Category categoryOf(int currentPosition) {
        return categoriesByPosition.get(currentPosition);
    }

    private boolean hasWon(int currentPlayer) {
        return purses[currentPlayer] == 6; // true for game continue, false for stop
    }

    protected void announce(Object message) {
        System.out.println(message);
    }
}