package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUpController {

	@FXML
	private TextField account;

	@FXML
	private Button back;

	@FXML
	private Button button;

	@FXML
	private TextField password;

	@FXML
	private Button signup;

	@FXML
	private CheckBox isp;

	private String type;

	@FXML
	void LogINBtn(ActionEvent event) throws IOException, ClassNotFoundException {
		if (isp.isSelected()) {
			type = "professor";
		} else {
			type = "student";
		}
		
		System.out.println(type);

		Connection con = null;
		String url = "jdbc:mysql://127.0.0.1:3306/DBMS_Final";
		String username = "root";
		String password = "00000000";
		int sid = 0;
		int pid = 0;

		sid = Integer.parseInt(account.getText());
		pid = Integer.parseInt(this.password.getText());
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
			System.out.println("Connected!!!!!");
		try {
			java.sql.Statement st2 = con.createStatement();
			String sql = "call signup(" + sid + "," + pid + "," + "'"+type +"'"+ ")";
			((java.sql.Statement) st2).executeUpdate(sql);
		} catch (SQLException ex) {
			System.out.print("failed");
			throw new RuntimeException("Error Executing sql" + ex);
		}
		}catch (SQLException ex) {
			System.out.print("failed");
			throw new RuntimeException("Error Executing sql" + ex);
		}
		Stage Stage = new Stage();
		Parent root = FXMLLoader.load(getClass().getResource("登入頁面2.fxml"));

		Stage.setTitle("政大加簽系統");
		Stage.setScene(new Scene(root, 600, 400));
		Stage.show();
		Stage = (Stage) back.getScene().getWindow();
		Stage.show();
		Stage.close();

	}

	@FXML
	void isPact(ActionEvent event) {
//    	type = 
	}

	@FXML
	void signupbtn(ActionEvent event) {

	}

}
