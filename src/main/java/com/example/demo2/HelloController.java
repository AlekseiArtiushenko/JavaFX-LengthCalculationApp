package com.example.demo2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class HelloController {

    @FXML
    private Button button;

    @FXML
    private ImageView imgView;

    @FXML
    void button(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open a file");
        fileChooser.setInitialDirectory(new File("C:\\Users\\Алексей\\Pictures"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPEG img", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG img", "*.png"), new FileChooser.ExtensionFilter("All img", "*.jpg", "*.png"));
        Stage stage = (Stage) button.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            Image image = new Image("file:\\\\\\" + selectedFile.getPath());
            imgView.setImage(image);
        } else {
            System.out.println("No file has been selected");
        }
    }
}
