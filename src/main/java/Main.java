import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            UserInterface userInterface = new UserInterface(scanner);
            userInterface.startUI();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
