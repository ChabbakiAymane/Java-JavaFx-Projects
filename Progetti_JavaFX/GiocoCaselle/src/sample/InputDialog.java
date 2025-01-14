package sample;

import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class InputDialog{
    static String getResponse(){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("Dimensione della griglia?");
        Optional<String> result = dialog.showAndWait();
        if(result.isPresent())
            return result.get();
        return null;
    }
}
