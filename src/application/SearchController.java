package application;

import java.awt.Component;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

public class SearchController extends 登入頁面Controller{
		private int ID;
	  @FXML
	    private Button searchbtn;
	 
	  @FXML
	    private Pane pane;
	  
	  @FXML
	    private FlowPane flowpane;
	  @FXML
	  	private TextField searchField;
	  
	  @FXML
	    private Button back;

	  @FXML
	    private Button home;
	    
	   private ArrayList<String> temp = new ArrayList<>();
	   private ArrayList<Integer> temp1 = new ArrayList<>();
	   private ArrayList<String> temp2= new ArrayList<>();
	   private ArrayList<String> temp3= new ArrayList<>();
	   private ArrayList<String> temp4= new ArrayList<>();
	   private ArrayList<Integer> temp5 = new ArrayList<>();



	    @FXML
	    void backbtn(ActionEvent event) throws IOException {
	    	
	    	Stage stage = (Stage) back.getScene().getWindow();
	    	stage.show();
	    	stage.close();
	    }
	    
	    public void initialize() {
	    	
	    }

	    @FXML
	    void homebtn(ActionEvent event) throws IOException {
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
	    void searchevent(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
	    	
	    	Stage stage = new Stage();
	    	Connection con = null;
	    	int num=0;
	    	
			String url = "jdbc:mysql://127.0.0.1:3306/DBMS_Final";
			String username = "root";
			String password = "00000000";

			try {
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection(url, username, password);
				 System.out.println();
//				 System.out.print(userID);


				System.out.println("Connected!");
				try (PreparedStatement st = con.prepareStatement("call count_number(?)")) {
					String keyword=	searchField.getText();
					st.setString(1, keyword);
					System.out.println("success1");
					ResultSet rs = st.executeQuery();
					while (rs.next()) {
						 num = rs.getInt(1);
						
					}
					
					rs.close();
				} 
				try (PreparedStatement st0 = con.prepareStatement("call getCourseStatus(?,?)")) {
					String keyword=	searchField.getText();
					st0.setInt(1, getStuID());
					st0.setString(2, keyword);
					System.out.println("success1");
					ResultSet rss = st0.executeQuery();
					while (rss.next()) {
						temp2.add(rss.getString(1));
					}
					for(String s :temp2) {
						System.out.println(s);
					}
					
					rss.close();
				} 
				FXMLLoader loader2 = new FXMLLoader(getClass().getResource( "查詢頁面2.fxml" )	);
//    			Scene scene = (Scene) loader.load();
    			Pane pane2 = (Pane) loader2.load();
    			SearchController controller2 = 
    			    loader2.<SearchController>getController();
				controller2.setStuID(ID);
				ScrollPane scrollpane = new ScrollPane();
    			scrollpane.setPrefSize(400, 200);
    			
				VBox v = new VBox(num);
				scrollpane.setPrefSize(530, 150);
				scrollpane.setLayoutX(55);
				scrollpane.setLayoutY(200);
				scrollpane.setContent(v);
				pane2.getChildren().add(scrollpane);
//				pane2.getChildren().add(v);
					for(int i=0;i<num;i++) {
						try (PreparedStatement st1 = con.prepareStatement("call select_one(?,?)")) {
							
							String keyword2=searchField.getText();
							st1.setString(1, keyword2);
							st1.setInt(2, i);
							System.out.println("success1");
							ResultSet rs1 = st1.executeQuery();
							while (rs1.next()) {
								
								 temp.add(rs1.getString(2));
								 temp1.add(rs1.getInt(1));
								 temp3.add(rs1.getString(7));
								 temp4.add(rs1.getString(3));
								 temp5.add(rs1.getInt(4));

								 FXMLLoader loader = new FXMLLoader(
						    			  getClass().getResource(
						    			    "課程查詢結果.fxml"
						    			  )
						    			);

						    			Pane pane = (Pane) loader.load();

						    			TestController controller = 
						    			    loader.<TestController>getController();
						    			if(temp2.get(i).equals("Yes")) {
						    				controller.setAppendBtn();
						    			}
						    			controller.setID(temp1.get(i));
						    			controller.setLoca(temp4.get(i));
						    			controller.setCredit(temp5.get(i));
						    			controller.setPName(temp3.get(i));
						    			controller.setStatus(temp2.get(i));
						    			controller.setStuID(getStuID());
						    			controller.setCname(temp.get(i));
						    			controller.initialize();
						    			v.getChildren().add(pane);
						    	    	
						    	
								 
							}
							System.out.print(temp1);
							rs1.close();
					}catch  (SQLException ex) {
					System.out.print("failed");
					throw new RuntimeException("Error Executing sql"+ex);
				}
					}
					Scene scene = new Scene(pane2,600,400);
					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					stage=(Stage) searchbtn.getScene().getWindow();
					stage.setScene(scene);
					stage.show();
					
			}catch (SQLException ex) {
				throw new RuntimeException("Error Executing sql"+ex);
			}
		
	    }

		public void setStuID(int x) {
			this.ID = x;
		}
		
		public int getStuID() {
			return ID;
		}
		
}
