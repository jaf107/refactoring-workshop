package java.src.main.java.workshop.trivia;

import java.util.ArrayList;
import java.util.LinkedList;

public class TriviaGame {
    ArrayList<Player> players;
    int[] places = new int[6];
    int[] purses = new int[6];
    boolean[] inPenaltyBox = new boolean[6];

    Question questionSet;

    int currentPlayer = 0;
//    Player currentPlayer;
    boolean isGettingOutOfPenaltyBox;

    public TriviaGame() {
        questionSet = new Question();
        players = new ArrayList();
    }

    public boolean isPlayable() {
        return (howManyPlayers() >= 2);
    }

    public boolean add(String playerName) {
        addPlayerToList(playerName);
        announce(playerName + " was added");
        announce("They are player number " + players.size());
        return true;
    }

    private void addPlayerToList(String playerName) {
        players.add(new Player(playerName, howManyPlayers()));
        places[howManyPlayers()] = 0;
        purses[howManyPlayers()] = 0;
        inPenaltyBox[howManyPlayers()] = false;
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        Player curPlayer = players.get(currentPlayer);
        announce(curPlayer.getName()+ " is the current player");
        announce("They have rolled a " + roll);

        if (curPlayer.getPenaltyBox()) {
            if (roll % 2 != 0) {
                isGettingOutOfPenaltyBox = true;

                announce(players.get(currentPlayer)  + " is getting out of the penalty box");

                curPlayer.setPlace(curPlayer.getPlace() + roll);
                if(curPlayer.getPlace() > 11)
                {
                    curPlayer.setPlace(curPlayer.getPlace() - 12);
                }
                announce(curPlayer.getName()
                        + "'s new location is "
                        + places[currentPlayer]);
                announce("The category is " + currentCategory());
                askQuestion();
            } else {
                announce(curPlayer.getName() + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }

        } else {

            curPlayer.setPlace(curPlayer.getPlace() + roll);
            if(curPlayer.getPlace() > 11)
            {
                curPlayer.setPlace(curPlayer.getPlace() - 12);
            }
            announce(curPlayer.getName()
                    + "'s new location is "
                    + places[currentPlayer]);
            announce("The category is " + currentCategory());
            askQuestion();
        }

    }

    private void askQuestion() {
        if (currentCategory() == "Pop")
            announce(questionSet.popQuestions.removeFirst());
        if (currentCategory() == "Science")
            announce(questionSet.scienceQuestions.removeFirst());
        if (currentCategory() == "Sports")
            announce(questionSet.sportsQuestions.removeFirst());
        if (currentCategory() == "Rock")
            announce(questionSet.rockQuestions.removeFirst());
    }


    private String currentCategory() {
        if (places[currentPlayer] == 0 || places[currentPlayer] == 4 || places[currentPlayer] == 8) {
            return "Pop";
        }
        else if (places[currentPlayer] == 1 || places[currentPlayer] == 5 || places[currentPlayer] == 9){
            return "Science";
        }else if (places[currentPlayer] == 2 || places[currentPlayer] == 6 || places[currentPlayer] == 10){
            return "Sports";
        }else {
            return "Rock";
        }
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

    private boolean didPlayerWin() {
        return !(purses[currentPlayer] == 6);
    }

    protected void announce(Object message) {
        System.out.println(message);
    }
}