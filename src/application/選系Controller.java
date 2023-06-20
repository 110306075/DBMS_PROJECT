package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class 選系Controller {
	
	
    @FXML
    private Button btn;

    @FXML
    private Label label;

    @FXML
    private TextField td;
    
    

    @FXML
    void btnAct(ActionEvent event) {
    	
    }
    
    public String ReturnDep() {
    	return td.getText();
    }
}
