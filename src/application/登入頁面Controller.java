package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.xdevapi.Statement;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class 登入頁面Controller {
	public 登入頁面Controller() {

	}

	@FXML
    private Button signup;
	
	public int userID;

	@FXML
	private TextField account;

	@FXML
	private Button button;

	@FXML
	private TextField password;

	private String result;

	public int num;
	
	private ArrayList<Integer> temp1 = new ArrayList<>();
	
	private ArrayList<String> temp2 = new ArrayList<>();


    @FXML
    void signupbtn(ActionEvent event) throws IOException {
    	Stage stage = new Stage();
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("SignUp.fxml"));

		Pane pane = (Pane) loader.load();

//		SearchController controller = loader.<SearchController>getController();
		

		Scene scene = new Scene(pane, 600, 400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		stage=(Stage) button.getScene().getWindow();
		stage.setScene(scene);
		stage.show();
    }
    
	public void initialize() {

		button.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				button.setStyle("-fx-background-color: #C6A300;");
			}

		});

		button.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				button.setStyle("-fx-background-color: #ffc900;");
			}

		});
	}

	@FXML
	void LogINBtn(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
		Connection con = null;

		String url = "jdbc:mysql://127.0.0.1:3306/DBMS_Final";
		String username = "root";
		String password = "00000000";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);

			System.out.println("Connected!");
			try (PreparedStatement stt = con.prepareStatement("call getPcourse(?)")) {
				int a = Integer.parseInt(this.account.getText());
				stt.setInt(1, a);
				ResultSet rsss = stt.executeQuery();
				while (rsss.next()) {
					temp1.add(rsss.getInt(1));
					temp2.add(rsss.getString(2));
				}

			} catch (SQLException ex) {
				System.out.print("failed");
				throw new RuntimeException("Error Executing sql" + ex);
			}
			try (PreparedStatement stt = con.prepareStatement("call classification(?,?)")) {
				int a = Integer.parseInt(this.account.getText());
				int b = Integer.parseInt(this.password.getText());
				stt.setInt(1, a);
				stt.setInt(2, b);
				ResultSet rsss = stt.executeQuery();
				while (rsss.next()) {
					result = rsss.getString(1);
				}

			} catch (SQLException ex) {
				System.out.print("failed");
				throw new RuntimeException("Error Executing sql" + ex);
			}
			try (PreparedStatement stt = con.prepareStatement("call classification(?,?)")) {
				int a = Integer.parseInt(this.account.getText());
				int b = Integer.parseInt(this.password.getText());
				stt.setInt(1, a);
				stt.setInt(2, b);
				ResultSet rsss = stt.executeQuery();
				while (rsss.next()) {
					result = rsss.getString(1);
				}

			} catch (SQLException ex) {
				System.out.print("failed");
				throw new RuntimeException("Error Executing sql" + ex);
			}
			try (PreparedStatement stt1 = con.prepareStatement("call CountPnum(?)")) {
				int a1 = Integer.parseInt(this.account.getText());
				stt1.setInt(1, a1);
				ResultSet rs5 = stt1.executeQuery();
				while (rs5.next()) {
					num=rs5.getInt(1);
				}

			} catch (SQLException ex) {
				System.out.print("failed");
				throw new RuntimeException("Error Executing sql" + ex);
			}
			try (PreparedStatement st = con.prepareStatement("call password_checking(?,?)")) {
				System.out.println("success1");
				int a = Integer.parseInt(this.account.getText());
				int b = Integer.parseInt(this.password.getText());
				st.setInt(1, a);
				st.setInt(2, b);
				ResultSet rs = st.executeQuery();
				userID = a;
				System.out.print(userID);

				while (rs.next()) {
					int val = rs.getInt(1);
					if (val == 1) {
						System.out.println("success");

						Stage stage = new Stage();
//						AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getResource("查詢頁面.fxml"));
//
//						Scene scene = new Scene(root, 700, 500);
//
//						scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
						if (result.equals("s")) {
							FXMLLoader loader = new FXMLLoader(getClass().getResource("查詢頁面2.fxml"));

							Pane pane = (Pane) loader.load();

							SearchController controller = loader.<SearchController>getController();
							controller.setStuID(a);

							Scene scene = new Scene(pane, 600, 400);
							scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
							stage=(Stage) button.getScene().getWindow();
							stage.setScene(scene);
							stage.show();
							
						} else {
							
							FXMLLoader loader = new FXMLLoader(getClass().getResource( "教授授課課程2.fxml" )	);
//			    			Scene scene = (Scene) loader.load();
			    			Pane pane = (Pane) loader.load();
			    			Professor2Controller controller = 
			    			    loader.<Professor2Controller>getController();
			    			

//			    				controller.
			    			ScrollPane scrollpane = new ScrollPane();
			    			scrollpane.setPrefSize(400, 200);
							VBox v = new VBox(num);
							pane.getChildren().add(scrollpane);
							scrollpane.setLayoutX(100);
							scrollpane.setLayoutY(154);
//							controller;
							scrollpane.setContent(v);
							for(int i = 0; i<num;i++) {
							 FXMLLoader loader2 = new FXMLLoader(getClass().getResource( "教授授課課程.fxml" )	);
					    			Pane pane2 = (Pane) loader2.load();

					    			ProfessorController controller2 = 
					    			    loader2.<ProfessorController>getController();
					    			controller2.setPID(a);
					    			controller2.setID(temp1.get(i));
					    			controller2.setCname(temp2.get(i));
					    			controller2.initialize();
//					    			controller.inputPane(pane2);
					    			v.getChildren().add(pane2);
							}
//			    			pane.getChildren().set(5, v);

							controller.initialize();
							Scene scene = new Scene(pane, 600, 400);
							scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//							stage.setScene(scene);
							stage=(Stage) button.getScene().getWindow();
							stage.setScene(scene);
							stage.sizeToScene();
							stage.show();
//							stage = (Stage) button.getScene().getWindow();
								      
//							      				    

						}
						rs.close();


					} else {

						System.out.println("wrong ps");
					}
				}

			} catch (SQLException ex) {
				System.out.print("failed");
				throw new RuntimeException("Error Executing sql" + ex);
			}

		} catch (SQLException ex) {
			throw new RuntimeException("Error Executing sql" + ex);
		}
	}

}
