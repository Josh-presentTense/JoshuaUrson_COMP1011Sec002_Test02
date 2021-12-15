package models;

public class Player {
    private int playerID;
    private String firstName;
    private String lastName;
    private double battingAverage;

    /**
     * Constructor for the Player class that takes 4 parameters
     * @param playerID : int
     * @param firstName : String
     * @param lastName : String
     * @param battingAverage : double
     */
    public Player(int playerID, String firstName, String lastName, double battingAverage) {
        setPlayerID(playerID);
        setFirstName(firstName);
        setLastName(lastName);
        setBattingAverage(battingAverage);
    }

    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getBattingAverage() {
        return battingAverage;
    }

    public void setBattingAverage(double battingAverage) {
        this.battingAverage = battingAverage;
    }
}
