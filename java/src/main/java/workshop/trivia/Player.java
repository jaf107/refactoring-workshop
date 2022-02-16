package java.src.main.java.workshop.trivia;

public class Player {
    private final String name;

    private int position = 0;
    private int purse = 0;
    private boolean penaltyBox = false;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    void moveTo(int newPosition){
        setPosition(newPosition);
    }

    public int getPurse() {
        return purse;
    }

    public void setPurse(int purse) {
        this.purse = purse;
    }

    void reward(int earned){
         setPurse(getPurse()+ earned);
    }

    boolean hasWon(){
        return this.purse >= 6;
    }

    void entersPenaltyBox(){
        this.penaltyBox = true;
    }

    void exitsPenaltyBox(){
        this.penaltyBox = false;
    }

    public boolean isInPenaltyBox() {
        return penaltyBox;
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
