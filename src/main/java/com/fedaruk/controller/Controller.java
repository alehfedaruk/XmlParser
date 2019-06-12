package com.fedaruk.controller;

import com.fedaruk.model.InvoicesHolder;
import com.fedaruk.strategy.SerializationStrategy;
import com.fedaruk.strategy.XMLSerializationStrategyImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class Controller {
    @FXML
    private Label sourceActionStatus;
    @FXML
    private Label destinationActionStatus;

    private File source;
    private File destination;

    @FXML
    private double primaryDeviation;
    @FXML
    private double secondaryDeviation;

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
            sourceActionStatus.setText(source.getName() + " was selected");
        } else {
            sourceActionStatus.setText("You haven't chosen a file");
        }
    }

    public void setSavingFile (ActionEvent event) {
        FileChooser chooser = new FileChooser();
        destination = chooser.showSaveDialog(null);

        if (destination != null) {
            destinationActionStatus.setText(destination.getName() + " is chosen");
        } else {
            destinationActionStatus.setText("Please, choose where to save file");
        }
    }

    public void convertFile(ActionEvent event) throws IOException {
        SerializationStrategy strategy = new XMLSerializationStrategyImpl();
        if (source != null) {
            InvoicesHolder holder = strategy.deserializeObject(source);
            if (destination != null)  {
                strategy.serializeObject(destination, holder, primaryDeviation, secondaryDeviation);
                System.out.println(primaryDeviation);
                System.out.println(secondaryDeviation);
            }
        }
    }

    public void setPrimaryDeviation(InputMethodEvent event) {
        if (primaryDeviationTextField.getText() != null) {
            primaryDeviation = Double.parseDouble(primaryDeviationTextField.getText().replaceAll(",","."));
        }
    }

    public void setSecondaryDeviation(InputMethodEvent event) {
        if (secondaryDeviationTextField.getText() != null) {
            secondaryDeviation = Double.parseDouble(secondaryDeviationTextField.getText().replaceAll(",","."));
        }
    }
}
