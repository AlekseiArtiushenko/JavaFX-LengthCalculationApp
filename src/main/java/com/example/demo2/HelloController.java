package com.example.demo2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HelloController {

    @FXML
    private TextField lineLength;

    @FXML
    private TextArea linesLengthsOutput;

    @FXML
    private ImageView imgView;

    @FXML
    private Button button;

    @FXML
    private AnchorPane anchorPane;

    List<MovableLine> lines = new ArrayList<>();
    double valueInCm;
    double difference;

    @FXML
    void addLine(ActionEvent event) {
        MovableLine newLine = new MovableLine(anchorPane);
        lines.add(newLine);
    }

    @FXML
    void removeLine(ActionEvent event) {
        MovableLine currentLine = lines.get(lines.size() - 1);
        lines.remove(lines.size() - 1);
        currentLine.removeLineFrom(anchorPane);
    }

    @FXML
    void checkInput(KeyEvent event) {
        lineLength.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*")) return;
            lineLength.setText(newValue.replaceAll("[^\\d]", ""));
        });
    }

    @FXML
    void button(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open a file");
        fileChooser.setInitialDirectory(new File("C:\\"));
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

    @FXML
    void calculate(MouseEvent event) {
        MovableLine getFirstValue = lines.get(0);
        valueInCm = Double.parseDouble(lineLength.getText());
        difference = valueInCm / getFirstValue.getMainValue();

        linesLengthsOutput.setText("Значение основного отрезка: " + difference * getFirstValue.getMainValue() + "\n");


        for(int i = 1; i < lines.size(); i++) {
            MovableLine getOtherValue = lines.get(i);
            linesLengthsOutput.setText(linesLengthsOutput.getText() + "\n" + "Значение отрезка: " + i + " "
                    + difference * getOtherValue.getMainValue());

        }

    }
}
