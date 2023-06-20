package application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

public class ProfessorController {

	
	private String cname;

	private String status;

	private int cid;

	private int PID;
	
	
    @FXML
    private Button appendbtn;

    @FXML
    private Label name;

    @FXML
    void appendAct(ActionEvent event) throws IOException {
    	Stage stage = new Stage();
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("加簽方式2.fxml"));
		Pane pane = (Pane) loader.load();
		WayController controller = loader.<WayController>getController();
		controller.setPID(PID);
		controller.setID(cid);
 
		Scene scene = new Scene(pane, 600, 400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage=(Stage) appendbtn.getScene().getWindow();

		stage.setScene(scene);
//		stage.show();
		
    }

    

    

    

	public void setPID(int x) {
		this.PID = x;
	}

	public int getPID() {
		return PID;
	}

	public void initialize() {
		name.setText(getName());
	}
	
	public void setCname(String a) {
		cname = a;
	}

	public String getName() {
		return cname;
	}

	public void setID(int id) {
		this.cid = id;
	}

	public int getID() {
		return cid;
	}
}
