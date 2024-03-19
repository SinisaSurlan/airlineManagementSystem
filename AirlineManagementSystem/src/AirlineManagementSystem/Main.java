package AirlineManagementSystem;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            Database database = new Database();
            Scanner s = new Scanner(System.in);
            int i = 0;
            do {
                System.out.println("Welcome to Airline Management System!");
                System.out.println("1. Add new passenger");
                System.out.println("2. Quit");

                i = s.nextInt();
                switch (i) {
                    case 1:
                        PassengerController.addNewPassanger(database, s);
                        break;
                }
            } while (i != 2);
            s.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
