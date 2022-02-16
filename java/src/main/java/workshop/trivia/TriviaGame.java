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
    boolean isGettingOutOfPenaltyBox;

    public TriviaGame() {
        for (Category c: CATEGORIES) {
            questionsByCategory.put(c, new LinkedList<>());
        }
        for (int i = 0; i < 50; i++) {
            for (Category c:CATEGORIES) {
                questionsByCategory.get(c).add(c.toString() + " Question " + i);
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

//    public int howManyPlayers() {
//        return players.size();
//    }

    public void roll(int roll) {
        announce(players.get(currentPlayer) + " is the current player");
        announce("They have rolled a " + roll);

        if (inPenaltyBox[currentPlayer]) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;

                announce(players.get(currentPlayer) + " is getting out of the penalty box");


                move(roll);
                announce(players.get(currentPlayer)
                        + "'s new location is "
                        + currentPosition());
                announce("The category is " + currentCategory());
                askQuestion();
            } else {
                announce(players.get(currentPlayer) + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }

        } else {

            move(roll);
            announce(players.get(currentPlayer)
                    + "'s new location is "
                    + currentPosition());
            announce("The category is " + currentCategory());
            askQuestion();
        }

    }

    private void move(int roll) {
        places[currentPlayer] = (currentPosition() + roll) % NB_CELLS;
    }

    private Integer currentPosition() {
        return places[currentPlayer];
    }

    private int nextPlayer(){
        return (currentPlayer + 1) % players.size();
    }

    private void askQuestion() {
        announce(questionsByCategory.get(currentCategory()).remove(0));
    }


    public boolean wasCorrectlyAnswered() {
        if (inPenaltyBox[currentPlayer]) {
            if (isGettingOutOfPenaltyBox) {
                announce("Answer was correct!!!!");
                purses[currentPlayer]++;
                announce(players.get(currentPlayer)
                        + " now has "
                        + purses[currentPlayer]
                        + " Gold Coins.");

                boolean winner = didPlayerWin();
                currentPlayer++;
                if (currentPlayer == players.size()) currentPlayer = 0;

                return winner;
            } else {
                currentPlayer++;
                if (currentPlayer == players.size()) currentPlayer = 0;
                return true;
            }


        } else {

            announce("Answer was correct!!!!");
            purses[currentPlayer]++;
            announce(players.get(currentPlayer)
                    + " now has "
                    + purses[currentPlayer]
                    + " Gold Coins.");

            boolean winner = didPlayerWin();
            currentPlayer++;
            if (currentPlayer == players.size()) currentPlayer = 0;

            return winner;
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

    private Category currentCategory() {

        return categoriesByPosition.get(currentPosition());
    }

    private boolean didPlayerWin() {
        return purses[currentPlayer] != 6;
    }

    protected void announce(Object message) {
        System.out.println(message);
    }
}