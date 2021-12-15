package utilities;

import models.Player;

import java.sql.*;
import java.util.ArrayList;

public class DBConn {
    // ADD CONNECTION INFO TO RUN
    private static String user = "root";
    private static String password = "Josh@9891";
    private static String connString = "jdbc:mysql://localhost:3306/test02comp1011";

    /**
     * This method pulls baseball players from players table in the DB
     * @return : ArrayList
     * @throws SQLException
     */
    public static ArrayList<Player> getPlayersFromDB() throws SQLException{
        ArrayList<Player> listOfPlayers = new ArrayList<>(); // ArrayList of baseball player pulled from DB table
        String queryString = "SELECT * FROM players"; // Holds the query that will be executed

        try (
                Connection conn = DriverManager.getConnection(connString, user, password); // Connect to DB
                Statement statement = conn.createStatement(); // Create a statement object
                ResultSet resultSet = statement.executeQuery(queryString); // Create and execute the query
        ) {
            // Parse through the content of the players table pulled from the Database
            while (resultSet.next()) {
                // Add content from the table to create Player objects
                Player baseballPlayer = new Player(
                        resultSet.getInt("playerID"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getDouble("battingAverage")
                );
                listOfPlayers.add(baseballPlayer); // add the baseball player info to the arraylist
            }
        } catch (SQLException e) {
            System.out.println(String.format("Database access issue: %s", e.getMessage()));
        } catch (Exception e) {
            System.out.println(String.format("Exception caught: %s", e.getMessage()));
        }
        return listOfPlayers;
    }

    /**
     * This method pulls baseball players from players table in the DB based on the submitted 'Search By' Button
     * @param searchBySpecification : String
     * @param parameter : String
     * @return : ArrayList
     * @throws SQLException
     */
    public static ArrayList<Player> getPlayersBySearchFromDB(String searchBySpecification, String parameter) throws SQLException{
        ArrayList<Player> listOfPlayers = new ArrayList<>(); // ArrayList of baseball player pulled from DB table
        String queryString = "";

        // Change query based on the searchBySpecification variable
        if (searchBySpecification.equals("firstName")) {
            queryString = String.format("SELECT * " +
                    "FROM players " +
                    "WHERE %s = '%s';", searchBySpecification, parameter); // Holds the query that will be executed
        }
        if (searchBySpecification.equals("playerID")) {
            queryString = String.format("SELECT * " +
                    "FROM players " +
                    "WHERE %s = %s;", searchBySpecification, parameter); // Holds the query that will be executed
        }

        try (
                Connection conn = DriverManager.getConnection(connString, user, password); // Connect to DB
                Statement statement = conn.createStatement(); // Create a statement object
                ResultSet resultSet = statement.executeQuery(queryString); // Create and execute the query
        ) {
            // Parse through the content of the players table pulled from the Database
            while (resultSet.next()) {
                // Add content from the table to create Player objects
                Player baseballPlayer = new Player(
                        resultSet.getInt("playerID"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getDouble("battingAverage")
                );
                listOfPlayers.add(baseballPlayer); // add the baseball player info to the arraylist
            }
        } catch (SQLException e) {
            System.out.println(String.format("Database access issue: %s", e.getMessage()));
        } catch (Exception e) {
            System.out.println(String.format("Exception caught: %s", e.getMessage()));
        }
        return listOfPlayers;
    }
}
