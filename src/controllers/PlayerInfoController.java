package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Player;
import utilities.DBConn;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PlayerInfoController implements Initializable {

    @FXML
    private TableView<Player> playersTableView;

    @FXML
    private TableColumn<Player,Integer> playerIdColumn;

    @FXML
    private TableColumn<Player,String> firstNameColumn;

    @FXML
    private TableColumn<Player,String> lastNameColumn;

    @FXML
    private TableColumn<Player,Double> battingAvgColumn;

    @FXML
    private TextArea battingAvgTextArea;

    @FXML
    private TextArea battingScoreTextArea;

    @FXML
    private TextArea allBattingAverageTextArea;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField playerIdTextField;

    @FXML
    private Label errMsgLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set up table columns
        playerIdColumn.setCellValueFactory(new PropertyValueFactory("playerID"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory("lastName"));
        battingAvgColumn.setCellValueFactory(new PropertyValueFactory("battingAverage"));

    }

    /**
     * This method pulls data from the DB based on the Search By text field
     * If the Search By First Name field is filled, search the table for players with that name
     * If the Search By PlayerID field is filled, search the table for the player with either playerID
     * If both fields are filled. Search by top-dowm, seach by First Name
     * If NO fields are filled, output error msg
     * @throws SQLException
     */
    @FXML
    private void searchButton() throws SQLException {
        clearTableViewResults();

        // Will be used to determine what column and parameter to search the DB table by
        String searchBy = "";
        String parameter = "";

        // Get player data based on which text field the user input
        // Options are: Search By First Name, Search By PlayerID
        if (!firstNameTextField.getText().isBlank()) {
            searchBy = "firstName";
            parameter = firstNameTextField.getText();
        } else if (!playerIdTextField.getText().isBlank()) {
            searchBy = "playerID";
            parameter = playerIdTextField.getText();
        } else {
            // Show error message
            errMsgLabel.setVisible(true);
            errMsgLabel.setText("Please fill in either first name or player id to use the search.");
            return;
        }

        // Set up ObservableList of Player type and fill it with the ArrayList pulled from the DB
        ObservableList<Player> baseballPlayers = FXCollections.observableArrayList(DBConn.getPlayersBySearchFromDB(searchBy, parameter));
        playersTableView.setItems(baseballPlayers);
    }

    /**
     * This method displays all the player info from the DB onto the TableView
     * @throws SQLException
     */
    @FXML
    private void displayAllButton() throws SQLException {
        clearTableViewResults();

        // Set up ObservableList of Player type and fill it with the ArrayList pulled from the DB
        ObservableList<Player> baseballPlayers = FXCollections.observableArrayList(DBConn.getPlayersFromDB());
        playersTableView.setItems(baseballPlayers);
    }

    /**
     * This method clears the table view and errorMsg
     */
    private void clearTableViewResults() {
        // Clear already populated data
        playersTableView.getItems().clear();

        // Hide error message
        errMsgLabel.setVisible(false);
    }

    /**
     * This method clears all the text areas, text fields, and table view
     */
    @FXML
    private void clearSearchButton() {
        clearTableViewResults();

        firstNameTextField.setText("");
        playerIdTextField.setText("");
        battingScoreTextArea.setText("");
        battingAvgTextArea.setText("");
        allBattingAverageTextArea.setText("");
    }

    /**
     * This method displays the total batting average of all the players in the appropriate text area
     * @throws SQLException
     */
    @FXML
    private void displayBattingAverageButton() throws SQLException {
        // Get all baseball players from DB
        ArrayList<Player> listOfPlayers = DBConn.getPlayersFromDB();

        // Stores data used to get batting average of all players
        int playerCount = 0;
        double sumOfAverages = 0;
        double battingAverageOfAllPlayers = 0;

        // parse through the arraylist of players and get the batting average of the players
        for (Player p : listOfPlayers) {
            playerCount += 1;
            sumOfAverages += p.getBattingAverage();
        }

        // Calculate the batting average of all players
        battingAverageOfAllPlayers = sumOfAverages / playerCount;

        // Display info on the appropriate text area
        String displayText = "Batting average of all players\n";
        displayText += String.format("Batting Average = %.3f", battingAverageOfAllPlayers);
        battingAvgTextArea.setText(displayText);
    }

    /**
     * This method displays the player with the highest batting average in the appropriate text area
     * @throws SQLException
     */
    @FXML
    private void highestBattingScoreButton() throws SQLException {
        // Get all baseball players from DB
        ArrayList<Player> listOfPlayers = DBConn.getPlayersFromDB();

        // used to find the highest batting average and to store player first and last name
        String playerFName = "";
        String playerLName = "";
        double highestBattingAvg = 0;

        // parse through the arraylist of players and get the batting average of the players
        for (Player p : listOfPlayers) {
            if (highestBattingAvg == 0)
                highestBattingAvg = p.getBattingAverage();

            if (highestBattingAvg < p.getBattingAverage()) {
                highestBattingAvg = p.getBattingAverage();
                playerFName = p.getFirstName();
                playerLName = p.getLastName();
            }
        }

        // Display info on the appropriate text area
        String displayText = "Player with Highest Batting Average is:\n";
        displayText += String.format("Player Name: %s %s\n", playerFName, playerLName);
        displayText += String.format("Batting Average = %.3f", highestBattingAvg);
        battingScoreTextArea.setText(displayText);
    }

    /**
     * This method displays the full name and batting average for ALL players in the text field
     * @throws SQLException
     */
    @FXML
    private void allPlayersBattingAverageButton() throws SQLException {
        // Get all baseball players from DB
        ArrayList<Player> listOfPlayers = DBConn.getPlayersFromDB();

        // Holds the string that will be used for the text area
        String displayText = "List of all Players and Batting Average\n";

        for (Player p : listOfPlayers) {
            displayText += String.format("Player Name: \n\t\t%s %s\n", p.getFirstName(), p.getLastName());
            displayText += String.format("Batting Average: \n\t\t%.3f\n\n", p.getBattingAverage());
        }

        // Display text
        allBattingAverageTextArea.setText(displayText);
    }
}
