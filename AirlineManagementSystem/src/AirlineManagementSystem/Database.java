package AirlineManagementSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Database {
    private String JDBC_URL = "jdbc:sqlserver://localhost:1433;databaseName=AirlineManagementSystem;trustServerCertificate=true";
    private String USERNAME = "sa";
    private String PASSWORD = "Radijator123!";
    private Connection connection;
    private Statement statement;

    public Database() throws SQLException {
        try {
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void close() throws SQLException {
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    public void addPassenger(Passenger p) throws SQLException {
        String insert = "INSERT INTO Passengers (firstName, lastName, email, tel) VALUES ('"
                        + p.getFirstName() + "', '" + p.getLastName() + "', '"
                        + p.getEmail() + "', '" + p.getTel() + "')";
        statement.execute(insert);
    }

    public ArrayList<Passenger> getAllPassengers() throws SQLException {
        ArrayList<Passenger> passengers = new ArrayList<>();
        try (ResultSet rs = statement.executeQuery("SELECT * FROM Passengers")) {
            while (rs.next()) {
                Passenger p = new Passenger();
                p.setId(rs.getInt("id"));
                p.setFirstName(rs.getString("firstName"));
                p.setLastName(rs.getString("lastName"));
                p.setTel(rs.getString("tel"));
                p.setEmail(rs.getString("email"));
                passengers.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return passengers;
    }
}
