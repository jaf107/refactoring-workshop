package java.src.main.java.workshop.trivia;

import java.src.main.java.workshop.trivia.Player;
import java.util.ArrayList;

public class Playerlist {
    ArrayList<Player> playerlist = new ArrayList<>();
    Player currentPlayer;

    Playerlist()
    {

    }

    public Playerlist(ArrayList<Player> playerlist) {
        this.playerlist = playerlist;
        this.currentPlayer = playerlist.get(0);
    }

    public void addPlayer(Player newPlayer){
        this.playerlist.add(newPlayer);
    }

}
