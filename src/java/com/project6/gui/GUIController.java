package com.project6.gui;

import com.project6.model.domain.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class GUIController {

    public GUIController() {
    }

    @FXML
    GridPane container;

    @FXML
    TextField valueUsername;

    @FXML
    PasswordField valuePassword;

    @FXML
    public void onAnyKeyPressedHandler(KeyEvent event) {
        KeyCode pressedKey = event.getCode();
        if (pressedKey == KeyCode.F1) {
            System.out.println("PRESSED F1 FOR HELP");
        }
    }


    @FXML
    public void onConfirmClick() {
        System.out.println("Login with credentials: Username=" + valueUsername.getText() + ";Password=" + valuePassword.getText());
        User user = new User(valueUsername.getText(), valuePassword.getText());
    }

    @FXML
    public void onResetClick() {
        valuePassword.clear();
        valueUsername.clear();
    }

    @FXML
    public void onHelpClick() {
        System.out.println("CLICKED HELP");
        Stage stage = new Stage();
        stage.setTitle("Autentifizierung");
        stage.initStyle(StageStyle.UTILITY);
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/help.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        stage.setScene(new Scene(root, 200, 200));
        stage.setResizable(false);
        stage.show();
    }
}
