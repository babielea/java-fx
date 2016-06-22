package com.project6;

import com.project6.config.ConfigLoader;
import com.project6.model.dao.DetailFieldLoader;
import com.project6.model.domain.DataForCover;
import com.project6.model.domain.Lernfeld;
import com.project6.pdf.PdfCreator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.util.Properties;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
        primaryStage.setTitle("Autentifizierung");
        primaryStage.initStyle(StageStyle.UTILITY);
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.setResizable(false);
        primaryStage.show();

        PdfCreator pdf = new PdfCreator();
        try {
            DataForCover t = new DetailFieldLoader().getDataForCover(1, 1);
            //List<Integer> a = new DetailFieldLoader().getAvailableLernfelder(1, 1);
            List<Lernfeld> b = new DetailFieldLoader().getLernfelder(1, 1);
            pdf.CreatePDF(b, t);

            String br = "";
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
