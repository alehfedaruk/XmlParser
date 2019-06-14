package com.fedaruk.controller;

import com.fedaruk.model.InvoicesHolder;
import com.fedaruk.strategy.SerializationStrategy;
import com.fedaruk.strategy.XMLSerializationStrategyImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class Controller {

    @FXML
    private Label sourceActionStatus;

    private File source;
    private File destination;

    @FXML
    private TextField primaryDeviationTextField;
    @FXML
    private TextField secondaryDeviationTextField;

    public void setXMLFile(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add((new FileChooser.ExtensionFilter("XML", "*.xml")));
        chooser.setInitialDirectory(new File(System.getProperty("user.home")));
        source = chooser.showOpenDialog(null);

        if (source != null) {
            sourceActionStatus.setText(source.getAbsolutePath() + " is chosen");
        } else {
            sourceActionStatus.setText("You haven't chosen a file");
        }
    }

    public void setSavingFile (ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.getExtensionFilters().add((new FileChooser.ExtensionFilter("XML", "*.xml")));
        chooser.setInitialDirectory(new File(System.getProperty("user.home")));
        destination = chooser.showSaveDialog(null);

        if (destination != null) {
            sourceActionStatus.setText(destination.getAbsolutePath() + " was selected");
        } else {
            sourceActionStatus.setText("Please, choose where to save file");
        }
    }

    public void convertFile(ActionEvent event) throws IOException {
        SerializationStrategy strategy = new XMLSerializationStrategyImpl();
        if (source != null) {
            InvoicesHolder holder = strategy.deserializeObject(source);
            if (destination != null)  {
                if (!(primaryDeviationTextField.getText().isEmpty() && secondaryDeviationTextField.getText().isEmpty())
                        && (primaryDeviationTextField.getText().matches("0+[.,]?([0-9]+)?")) && secondaryDeviationTextField.getText().matches("([0-9])+([.,])([0-9]+)")) {
                    double primaryDeviation = Double.valueOf(primaryDeviationTextField.getText().replaceAll(",","."));
                    double secondaryDeviation = Double.valueOf(secondaryDeviationTextField.getText().replaceAll(",","."));
                    strategy.serializeObject(destination, holder, primaryDeviation, secondaryDeviation);
                    sourceActionStatus.setText("The file was successfully converted");
                } else {
                    handleAlert("Please check if deviation is set.\n Type format of numbers is XXXX[comma or dot]XXXX", Alert.AlertType.WARNING);
                    sourceActionStatus.setText("Converting was unsuccessful");
                }
            }
        }
    }

    private void handleAlert(String alertMessage, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setContentText(alertMessage);
        alert.show();
    }
}
