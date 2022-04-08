package gui.util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Utils {

    // acessa o Stage onde o Controller que recebeu o evento está
    public static Stage CurrentStage(ActionEvent event) {
        return (Stage) ((Node) event.getSource()).getScene().getWindow();
    }

    public static Integer tryParseToInt(String str) {
        try {
            return Integer.parseInt(str);
        }
        // se não for número inteiro vai retornar null
        catch (NumberFormatException e) {
            return null;
        }
    }
}
