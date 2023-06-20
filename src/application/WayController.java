package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.ChoiceBox;


public class WayController {

	private int id;

	private int PID;
	
	String department;

	@FXML
	private Button btn;

	@FXML
	private ChoiceBox<String> cb;
	
	private ChoiceBox<String> cb1;

	@FXML
	private Label l1;

	@FXML
	private TextField t1;
	
	private String[] data = {"隨機","按時間","按年級","系優先","大四優先"};

	private ArrayList<String> temp1 = new ArrayList<>();
	private ArrayList<String> temp2 = new ArrayList<>();
	private ArrayList<String> temp3 = new ArrayList<>();
	private ArrayList<Integer> temp4 = new ArrayList<>();
	
	

	@FXML
	void btnact(ActionEvent event) throws ClassNotFoundException, IOException {
		Stage stage = new Stage();
//		System.out.print(cb.getSelectionModel().getSelectedItem()); 
		System.out.print(cb.getSelectionModel().getSelectedItem());
		System.out.print(PID);
		System.out.print(id);

		String url = "jdbc:mysql://127.0.0.1:3306/DBMS_Final";
		String username = "root";
		String password = "00000000";
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
			System.out.println();
//			 System.out.print(userID);
			System.out.println("Connected!");

			if (cb.getSelectionModel().getSelectedItem().equals(null) || t1.getText().equals(null)) {
				
			} else {
				if (cb.getSelectionModel().getSelectedItem().equals("隨機")) {
					System.out.println("tttt");
					try (PreparedStatement st = con.prepareStatement("call exe_random(?,?,?)")) {
						int k = Integer.parseInt(t1.getText());
						st.setInt(1, PID);
						st.setInt(2, id);
						st.setInt(3, k);

						ResultSet rs = st.executeQuery();
						while (rs.next()) {

							temp1.add(rs.getString(3));
							temp2.add(rs.getString(4));
							temp3.add(rs.getString(5));
							temp4.add(rs.getInt(6));
							

						}
						System.out.print(temp1.get(0));
						FXMLLoader loader = new FXMLLoader(
				    			  getClass().getResource(
				    			    "加簽結果2.fxml"
				    			  )
				    			);
					    		Pane pane = (Pane) loader.load();
	
					    		ResultController controller = loader.<ResultController>getController();
					    			
					    		controller.sett1(temp1);
					    		controller.setPID(PID);
					    		controller.setCID(id);
//					    		controller.sett4(temp4);
//					    		controller.initialize();
					    		VBox v = new VBox(temp1.size());
					    		for(int i = 0; i<temp1.size(); i++) {
					    			Label l = new Label(String.format("%s  %s  %s  %s",temp1.get(i),temp2.get(i),temp3.get(i),Integer.toString(temp4.get(i))));
					    			v.getChildren().add(l);
					    		}
					    		
								pane.getChildren().add(v);
								v.setLayoutX(117);
								v.setLayoutY(175);
					    		
					    		Scene scene = new Scene(pane, 600, 400);
								scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
								stage=(Stage) btn.getScene().getWindow();

								stage.setScene(scene);
//								stage.show();
//								stage = (Stage) btn.getScene().getWindow();
						rs.close();
					} catch (SQLException ex) {
						throw new RuntimeException("Error Executing sql" + ex);
					}

				}

				else if (cb.getSelectionModel().getSelectedItem().equals("按時間")) {

					try (PreparedStatement st = con.prepareStatement("call exe_time(?,?,?)")) {
						int k = Integer.parseInt(t1.getText());
						st.setInt(1, PID);
						st.setInt(2, id);
						st.setInt(3, k);

						ResultSet rs = st.executeQuery();
						while (rs.next()) {
							temp1.add(rs.getString(3));
							temp2.add(rs.getString(4));
							temp3.add(rs.getString(5));
							temp4.add(rs.getInt(6));

						}
						System.out.print(temp1.get(0));
						FXMLLoader loader = new FXMLLoader(
				    			  getClass().getResource(
				    			    "加簽結果2.fxml"
				    			  )
				    			);
					    		Pane pane = (Pane) loader.load();
	
					    		ResultController controller = loader.<ResultController>getController();
					    			
					    		controller.sett1(temp1);
					    		controller.setPID(PID);
					    		controller.setCID(id);
//					    		controller.sett1(temp1);
//					    		controller.sett2(temp2);
//					    		controller.sett3(temp3);
//					    		controller.sett4(temp4);
//					    		controller.initialize();
					    		VBox v = new VBox(temp1.size());
					    		for(int i = 0; i<temp1.size(); i++) {
					    			Label l = new Label(String.format("%s  %s  %s  %s",temp1.get(i),temp2.get(i),temp3.get(i),Integer.toString(temp4.get(i))));
					    			v.getChildren().add(l);
					    		}
					    		
								pane.getChildren().add(v);
								v.setLayoutX(117);
								v.setLayoutY(175);
					    		
					    		Scene scene = new Scene(pane, 600, 400);
								scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
								stage.setScene(scene);
								stage.show();
								stage = (Stage) btn.getScene().getWindow();

						rs.close();
					} catch (SQLException ex) {
						throw new RuntimeException("Error Executing sql" + ex);
					}

				} else if (cb.getSelectionModel().getSelectedItem().equals("按年級")) {

					try (PreparedStatement st = con.prepareStatement("call exe_grade(?,?,?)")) {
						int k = Integer.parseInt(t1.getText());
						st.setInt(1, PID);
						st.setInt(2, id);
						st.setInt(3, k);

						ResultSet rs = st.executeQuery();
						while (rs.next()) {
							temp1.add(rs.getString(3));
							temp2.add(rs.getString(4));
							temp3.add(rs.getString(5));
							temp4.add(rs.getInt(6));

						}
						FXMLLoader loader = new FXMLLoader(
				    			  getClass().getResource(
				    			    "加簽結果2.fxml"
				    			  )
				    			);
					    		Pane pane = (Pane) loader.load();
	
					    		ResultController controller = loader.<ResultController>getController();
					    		
					    		controller.sett1(temp1);
					    		controller.setPID(PID);
					    		controller.setCID(id);
//					    		controller.sett1(temp1);
//					    		controller.sett2(temp2);
//					    		controller.sett3(temp3);
//					    		controller.sett4(temp4);
//					    		controller.initialize();
					    		VBox v = new VBox(temp1.size());
					    		for(int i = 0; i<temp1.size(); i++) {
					    			Label l = new Label(String.format("%s  %s  %s  %s",temp1.get(i),temp2.get(i),temp3.get(i),Integer.toString(temp4.get(i))));
					    			v.getChildren().add(l);
					    		}
					    		
								pane.getChildren().add(v);
								v.setLayoutX(117);
								v.setLayoutY(175);
					    		
					    		Scene scene = new Scene(pane, 600, 400);
								scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
								stage.setScene(scene);
								stage.show();
								stage = (Stage) btn.getScene().getWindow();
						rs.close();
					} catch (SQLException ex) {
						throw new RuntimeException("Error Executing sql" + ex);
					}

				} else if (cb.getSelectionModel().getSelectedItem().equals("系優先")) {
					if(department == null) {
						Stage s = new Stage();
						FXMLLoader loader = new FXMLLoader(
				    			  getClass().getResource(
				    			    "選系.fxml"
				    			  )
				    			);
					    Pane pane = (Pane) loader.load();
	
					    選系Controller controller = loader.<選系Controller>getController();
					    
//						VBox v = new VBox(2);
//						v.setPrefSize(200, 100);
//						TextField t = new TextField();
//						t.setMaxSize(10, 10);
						Button b = new Button("OK");
						b.setMinWidth(60);
						b.setMinHeight(20);
						b.setLayoutX(111);
						b.setLayoutY(112);
						b.setStyle("-fx-background-color: #ffc900;");
						b.setText("OK");
						pane.getChildren().add(b);
//						t.setMaxWidth(60);
//						v.getChildren().add(t);
//						v.getChildren().add(b);

						b.setOnAction(new EventHandler<ActionEvent>() {
							
							@Override
							public void handle(ActionEvent arg0) {
								// TODO Auto-generated method stub
								Stage s = new Stage();
								department = controller.ReturnDep();
								System.out.print(department);
								s = (Stage) b.getScene().getWindow();
								s.close();
							}
						});
						
					
						
						Scene scene = new Scene(pane);
						scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	
						s.setScene(scene);
						s.show();
					}else {
					try (PreparedStatement st = con.prepareStatement("call exeT_major(?,?,?,?)")) {
						int k = Integer.parseInt(t1.getText());
						st.setInt(2, PID);
						st.setInt(1, id);
						st.setString(3,department);
						st.setInt(4, k);

						ResultSet rs = st.executeQuery();
						while (rs.next()) {
							temp1.add(rs.getString(3));
							temp2.add(rs.getString(4));
							temp3.add(rs.getString(5));
							temp4.add(rs.getInt(6));
							System.out.print("肉次方");

						}
						System.out.print(temp1.get(0));
						FXMLLoader loader = new FXMLLoader(
				    			  getClass().getResource(
				    			    "加簽結果2.fxml"
				    			  )
				    			);
					    		Pane pane = (Pane) loader.load();
	
					    		ResultController controller = loader.<ResultController>getController();
					    		
					    		controller.sett1(temp1);
					    		controller.setPID(PID);
					    		controller.setCID(id);
//					    		controller.sett1(temp1);
//					    		controller.sett2(temp2);
//					    		controller.sett3(temp3);
//					    		controller.sett4(temp4);
//					    		controller.initialize();
					    		VBox v = new VBox(temp1.size());
					    		for(int i = 0; i<temp1.size(); i++) {
					    			Label l = new Label(String.format("%s  %s  %s  %s",temp1.get(i),temp2.get(i),temp3.get(i),Integer.toString(temp4.get(i))));
					    			v.getChildren().add(l);
					    		}
					    		
								pane.getChildren().add(v);
								v.setLayoutX(117);
								v.setLayoutY(175);
					    		
					    		Scene scene = new Scene(pane, 600, 400);
								scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
								stage.setScene(scene);
								stage.show();
								stage = (Stage) btn.getScene().getWindow();
						rs.close();
					} catch (SQLException ex) {
						throw new RuntimeException("Error Executing sql" + ex);
					}}

				} else if (cb.getSelectionModel().getSelectedItem().equals("大四優先")) {

					try (PreparedStatement st = con.prepareStatement("call exeT_grade(?,?,?)")) {
						int k = Integer.parseInt(t1.getText());
						st.setInt(2, PID);
						st.setInt(1, id);
						st.setInt(3, k);

						ResultSet rs = st.executeQuery();
						while (rs.next()) {
							temp1.add(rs.getString(3));
							temp2.add(rs.getString(4));
							temp3.add(rs.getString(5));
							temp4.add(rs.getInt(6));

						}
						System.out.print(temp1.get(0));
						FXMLLoader loader = new FXMLLoader(
				    			  getClass().getResource(
				    			    "加簽結果2.fxml"
				    			  )
				    			);
					    		Pane pane = (Pane) loader.load();
	
					    		ResultController controller = loader.<ResultController>getController();
					    		
					    		controller.sett1(temp1);
					    		controller.setPID(PID);
					    		controller.setCID(id);
//					    		controller.sett1(temp1);
//					    		controller.sett2(temp2);
//					    		controller.sett3(temp3);
//					    		controller.sett4(temp4);
//					    		controller.initialize();
					    		VBox v = new VBox(temp1.size());
					    		for(int i = 0; i<temp1.size(); i++) {
					    			Label l = new Label(String.format("%s  %s  %s  %s",temp1.get(i),temp2.get(i),temp3.get(i),Integer.toString(temp4.get(i))));
					    			v.getChildren().add(l);
					    		}
					    		
								pane.getChildren().add(v);
								v.setLayoutX(117);
								v.setLayoutY(175);
					    		
					    		Scene scene = new Scene(pane, 600, 400);
								scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
								stage.setScene(scene);
								stage.show();
								stage = (Stage) btn.getScene().getWindow();
						rs.close();
					} catch (SQLException ex) {
						throw new RuntimeException("Error Executing sql" + ex);
					}

//						

				}
			}
		} catch (SQLException ex) {
			throw new RuntimeException("Error Executing sql" + ex);
		}

	}

	public void setPID(int x) {
		this.PID = x;
	}

	public int getPID() {
		return PID;
	}

	public void setID(int id) {
		this.id = id;
	}

	public int getID() {
		return id;
	}

	public void initialize() {
		cb.getItems().addAll(data);
//	
		}
	
	public void setDep(String s) {
		department = s;
	}
	
	public String getDep() {
		return department;
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
//	@Override
//	public void initialize(URL arg0, ResourceBundle arg1) {
////		 TODO Auto-generated method stub
//		cb.getItems().addAll(data);
//		
//
//	}
}
