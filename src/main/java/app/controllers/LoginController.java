package app.controllers;
import app.CRUDApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
public class LoginController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    private final Map<String, String> users = new HashMap<>() {{
        put("Celine", "C123");
        put("Charbel", "C321");
        put("Joe", "J123");
        put("Michael", "M123");
    }};

    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (users.containsKey(username) && users.get(username).equals(password)) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/views/views/home-view.fxml"));
            Stage stage = (Stage) usernameField.getScene().getWindow();
            stage.setScene(new Scene(loader.load()));
        } else {
            errorLabel.setText("Invalid username or password!");
        }
    }
}

