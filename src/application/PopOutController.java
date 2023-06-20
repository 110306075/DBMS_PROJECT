package application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PopOutController {

    @FXML
    private Button btn;

    @FXML
    private Label label;
    
    private String s;

    @FXML
    void btnAct(ActionEvent event) {

    }
    
    public void setLabel(String s) {
    	this.s = s;
    }
    
    public String getLabel() {
    	return s;
    }
    
    public void initialize(){
    	label.setText(s);
    }
}
