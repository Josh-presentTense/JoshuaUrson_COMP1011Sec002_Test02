package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Player;

import java.net.URL;
import java.util.ResourceBundle;

public class PlayerInfoController implements Initializable {

    @FXML
    private TableView<Player> playersTableView;

    @FXML
    private TableColumn<Player, Integer> playerIdColumn;

    @FXML
    private TableColumn<Player, String> firstNameColumn;

    @FXML
    private TableColumn<Player, String> lastNameColumn;

    @FXML
    private TableColumn<Player, Double> battingAvgColumn;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set up the columns of the TableView
        playerIdColumn.setCellFactory(new PropertyValueFactory("playerID"));
        firstNameColumn.setCellFactory(new PropertyValueFactory("firstName"));
        lastNameColumn.setCellFactory(new PropertyValueFactory("lastName"));
        battingAvgColumn.setCellFactory(new PropertyValueFactory("battingAverage"));
    }
}
