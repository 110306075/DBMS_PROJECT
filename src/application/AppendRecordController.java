package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AppendRecordController {

    @FXML
    private Button back;

    @FXML
    private Button home;

    @FXML
    void Backact(ActionEvent event) throws IOException {
    	
    	Stage stage = (Stage) back.getScene().getWindow();
    	stage.show();
    	stage.close();
    }

    @FXML
    void homeact(ActionEvent event) throws IOException {
    	 Stage Stage = new Stage();
    	 Parent root = FXMLLoader.load(getClass().getResource("登入頁面2.fxml"));
         
         Stage.setTitle("政大加簽系統");
         Stage.setScene(new Scene(root, 600, 400));
         Stage.show();
         Stage = (Stage) back.getScene().getWindow();
         Stage.show();
         Stage.close();
         
    }
    
    public void initialize() {
    	
    }

}
