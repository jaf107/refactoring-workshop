package java.src.main.java.workshop.trivia;

public class Player {
    private String name;
    private int place;
    private int purse;
    private boolean penaltyBox;

//    int score;


    public Player() {
        this.place = 0;
    }

    public Player(String name, int pos) {
        this.name = name;
        this.place = pos;
        this.purse = 0;
        this.penaltyBox = false;
    }

    public String getName() {
        return name;
    }

    public int getPlace() {
        return place;
    }

    public int getPurse() {
        return purse;
    }

    public boolean getPenaltyBox() {
        return penaltyBox;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public void setPurse(int purse) {
        this.purse = purse;
    }

    public void setPenaltyBox(boolean penaltyBox) {
        this.penaltyBox = penaltyBox;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", place=" + place +
                ", purse=" + purse +
                ", penaltyBox=" + penaltyBox +
                '}';
    }
}
