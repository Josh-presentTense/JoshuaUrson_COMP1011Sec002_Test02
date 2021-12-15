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
            parameter = playerIdTextField.getText().toString();
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
}
