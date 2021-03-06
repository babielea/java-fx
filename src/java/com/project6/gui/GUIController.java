package com.project6.gui;

import com.project6.gui.util.ProgressBarHelper;
import com.project6.model.dao.UserDAO;
import com.project6.model.domain.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    ProgressBar pdfProgress;

    @FXML
    Tooltip errorMessage;

    @FXML
    Button confirmButton;

    @FXML
    public void onAnyKeyPressedHandler(KeyEvent event) {
        KeyCode pressedKey = event.getCode();
        if (pressedKey == KeyCode.F1) {
            System.out.println("PRESSED F1 FOR HELP");
        }
    }


    public void onConfirmClick() {
        System.out.println("Login with credentials: Username=" + valueUsername.getText() + ";Password=" + valuePassword.getText());
        User user = new User(valueUsername.getText(), valuePassword.getText());
        UserDAO peter = new UserDAO(user);

        if(peter.checkForLogin()) {
            Stage stage = new Stage();
            stage.setTitle("Autentifizierung");
            stage.initStyle(StageStyle.UTILITY);
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/fxml/generate.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            stage.setScene(new Scene(root, 600, 400));
            stage.setResizable(false);
            stage.show();

            Stage main = (Stage) container.getScene().getWindow();
            main.close();
        } else {
            resetInputFields();
            errorMessage.show(confirmButton, confirmButton.getTranslateX(), confirmButton.getTranslateY() );
        }


    }

    @FXML
    public void onResetClick() {
        resetInputFields();
    }

    private void resetInputFields() {
        valuePassword.clear();
        valueUsername.clear();
    }

    @FXML
    public void onPdfGenerateClick() {
        System.out.println(pdfProgress);
/*    Timeline task = new Timeline(
        new KeyFrame(
            Duration.ZERO,
            new KeyValue(pdfProgress.progressProperty(), 0)
        ),
        new KeyFrame(
            Duration.seconds(2),
            new KeyValue(pdfProgress.progressProperty(), 1)
        )
    );
    task.playFromStart();*/

        ProgressBarHelper.setProgressBar(pdfProgress);

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
