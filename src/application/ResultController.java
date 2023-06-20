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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ResultController {

	@FXML
	private Button btn;

	@FXML
	private VBox vb;

	private int PID;

	private int CID;

	private ArrayList<String> temp1 = new ArrayList<>();
	private ArrayList<String> temp2 = new ArrayList<>();
	private ArrayList<String> temp3 = new ArrayList<>();
	private ArrayList<Integer> temp4 = new ArrayList<>();

	@FXML
	void btnact(ActionEvent event) throws ClassNotFoundException {
		System.out.print(PID);
		System.out.print(temp1);
		System.out.print(CID);
		String url = "jdbc:mysql://127.0.0.1:3306/DBMS_Final";
		String username = "root";
		String password = "00000000";
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
			System.out.println();
//			 System.out.print(userID);
			System.out.println("Connected!22");

			try (PreparedStatement stt = con.prepareStatement("call search_all(?,?)")) {
//				int k = Integer.parseInt(t1.getText());
				String sql = "search_all(" + PID + "," + CID + ")";
				stt.setInt(1, PID);
				stt.setInt(2, CID);
				System.out.println("dd!");

				ResultSet rs = stt.executeQuery();
				while (rs.next()) {
					System.out.println("ddd!");

					temp2.add(rs.getString(1));
//					break;
				}
				rs.close();
				
			} catch (SQLException ex) {
				System.out.print("failed");
				throw new RuntimeException("Error Executing sql" + ex);
			}
			System.out.println(temp2);
			for (String i : temp2) {
				System.out.print("ll");
				try (PreparedStatement stt = con.prepareStatement("call update_status2(?,?,?)")) {
//				
					int s = Integer.parseInt(i);

					stt.setInt(1, PID);
					stt.setInt(2, CID);
					stt.setInt(3, s);
					stt.executeUpdate();

					System.out.println("dd!");
				} catch (SQLException ex) {
					System.out.print("failed");
					throw new RuntimeException("Error Executing sql" + ex);
				}
			
				for(int k=0;k<temp1.size();k++) {
					if(i.equals(temp1.get(k))) {
						System.out.print("l");
						try (PreparedStatement stt = con.prepareStatement("call update_status(?,?,?)")) {
//						int k = Integer.parseInt(t1.getText());
//						String sql = "search_all("+PID+","+CID +")";
							int s = Integer.parseInt(i);

							stt.setInt(1, PID);
							stt.setInt(2, CID);
							stt.setInt(3, s);
							stt.executeUpdate();
							System.out.println(i);

						} catch (SQLException ex) {
							System.out.print("failed");
							throw new RuntimeException("Error Executing sql" + ex);
						}
					}
				}
//				
			}

		} catch (SQLException ex) {
			System.out.print("failed");
			throw new RuntimeException("Error Executing sql" + ex);
		}

//		FXMLLoader loader = new FXMLLoader(getClass().getResource( "教授授課課程2.fxml" )	);
////		Scene scene = (Scene) loader.load();
//		Pane pane = (Pane) loader.load();
//		Professor2Controller controller = 
//		    loader.<Professor2Controller>getController();
//		
//
////			controller.
//		ScrollPane scrollpane = new ScrollPane();
//		scrollpane.setPrefSize(400, 200);
//		VBox v = new VBox(num);
//		pane.getChildren().add(scrollpane);
//		scrollpane.setLayoutX(100);
//		scrollpane.setLayoutY(154);
////		controller;
//		scrollpane.setContent(v);
//		for(int i = 0; i<num;i++) {
//		 FXMLLoader loader2 = new FXMLLoader(getClass().getResource( "教授授課課程.fxml" )	);
//    			Pane pane2 = (Pane) loader2.load();
//
//    			ProfessorController controller2 = 
//    			    loader2.<ProfessorController>getController();
//    			controller2.setPID(a);
//    			controller2.setID(temp1.get(i));
//    			controller2.setCname(temp2.get(i));
//    			controller2.initialize();
////    			controller.inputPane(pane2);
//    			v.getChildren().add(pane2);
	}

	public void initialize() {
//    	System.out.print(temp1.get(0));
//    	for(int i= 0; i <temp1.size();i++) {
//    		Label l = new Label(String.format("%s,%s,%s,%s",temp1.get(i),temp2.get(i),temp3.get(i),Integer.toString(temp4.get(i))));
//    		vb.getChildren().add(l);
//    	}
	}

	public void sett1(ArrayList<String> t1) {
		for (String s : t1) {
			this.temp1.add(s);
		}

	}

	public void setPID(int i) {
//    	for(String s :t1) {
//    		this.temp2.add(s);
		PID = i;
//    	}    
	}

	public void setCID(int i) {
//    	for(String s :t1) {
//    		this.temp3.add(s);
		CID = i;
//    	}   
	}

	public void sett4(ArrayList<Integer> t1) {
//    	for(int s :t1) {
//    		this.temp4.add(s);
//    	}  
	}
	
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

}
