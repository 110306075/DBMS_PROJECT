package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class RECORDController {

    @FXML
    private Button back;

    @FXML
    private Button home;

    @FXML
    void Backact(ActionEvent event) {
    	Stage stage = new Stage();
    	stage = (Stage) back.getScene().getWindow();
    }

    @FXML
    void homeact(ActionEvent event) {

    }

}
