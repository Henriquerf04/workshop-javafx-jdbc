package gui.util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Utils {

    // acessa o Stage onde o Controller que recebeu o evento está
    public static Stage CurrentStage(ActionEvent event) {
        return (Stage) ((Node) event.getSource()).getScene().getWindow();
    }
}
