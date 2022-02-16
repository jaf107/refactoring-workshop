package java.src.main.java.workshop.trivia;

import java.util.*;

public class TriviaGame {
    private static final int NB_CELLS = 12;
    private static final Category[] CATEGORIES = new Category[]{Category.POP,Category.SCIENCE,Category.SPORTS,Category.ROCK};

    private final Map<Integer, Category> categoriesByPosition = new HashMap<>(NB_CELLS);
    private final Map<Category, List<String>> questionsByCategory = new HashMap<>();

    private final List<Player> players = new ArrayList<>();
    private Player currentPlayer = null;
    private int currentPlayerIndex = 0;

    /*
    private final ArrayList<String> players = new ArrayList<>();
    private final int[] places = new int[6];
    private final int[] purses = new int[6];
    private final boolean[] inPenaltyBox = new boolean[6];

    int currentPlayer = 0;*/
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
        players.add(new Player(playerName));
        announce(playerName + " was added");
        announce("They are player number " + players.size());
        currentPlayer = players.get(currentPlayerIndex);
    }


    public void roll(int roll) {
        announce(currentPlayer + " is the current player");
        announce("They have rolled a " + roll);

        if (currentPlayer.isInPenaltyBox()) {
            if (roll % 2 != 0) {
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
        announce(nextQuestionIsAbout(currentCategory));
    }

    private int newPosition(int currentPosition, int roll) {
        return (currentPosition + roll) % NB_CELLS;
    }

    private String nextQuestionIsAbout(Category currentCategory) {
        return questionsByCategory.get(currentCategory).remove(0);
    }

    private Category categoryOf(int currentPosition) {
        return categoriesByPosition.get(currentPosition);
    }

    private Player nextPlayer(){
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        return players.get(currentPlayerIndex);
    }

    public boolean wasCorrectlyAnswered() {
        if (currentPlayer.isInPenaltyBox()) {
            currentPlayer = nextPlayer();
            return true;
        } else {
            announce("Answer was correct!!!!");
            currentPlayer.reward(1);

            announce(currentPlayer + " now has " + currentPlayer.getPurse() + " Gold Coins.");

            boolean gameContinues = !currentPlayer.hasWon();
            currentPlayer = nextPlayer();

            return gameContinues;
        }
    }

    public boolean wrongAnswer() {
        announce("Question was incorrectly answered");
        announce(currentPlayer + " was sent to the penalty box");
        currentPlayer.entersPenaltyBox();

        currentPlayer = nextPlayer();
        return true;
    }

    protected void announce(Object message) {
        System.out.println(message);
    }
}