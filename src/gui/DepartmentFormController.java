package gui;

import java.net.URL;
import java.util.ResourceBundle;

import gui.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DepartmentFormController implements Initializable {

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private Label errorNameLabel;

    @FXML 
    private Button saveButton;

    @FXML 
    private Button cancelButton;

    @FXML
    public void onSaveButtonAction() {
        System.out.println("Save");
    }

    @FXML
    public void onCancelButtonAction() {
        System.out.println("Cancel");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeNodes();
        
    }

    private void initializeNodes() {
        Constraints.setTextFieldInteger(txtId);
        Constraints.setTextFieldMaxLength(txtName, 30);
    }
    
}
