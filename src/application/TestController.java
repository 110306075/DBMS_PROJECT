package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;

import com.mysql.cj.xdevapi.Statement;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TestController {

	private String cname;
	private int cid;
	private String status;
	
	private int credit;
	private String PName;
	private String loca;
	private int id;

	private int ID;
	@FXML
	private Button appendbtn;

	@FXML
	private Label name;

	@FXML
	private Button searchbtn;

	@FXML
	private Label timeteach;

	private String sql;

	private String resulta;

	private String resultb;
	
	private ArrayList<String> temp = new ArrayList<>();
	private ArrayList<String> temp1 = new ArrayList<>();



	@FXML
	void searchevent(ActionEvent event) throws IOException, ClassNotFoundException {
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("現狀結果2.fxml"));

		AnchorPane pane = (AnchorPane) loader.load();
		AppendRecordController controller = loader.<AppendRecordController>getController();
		
//		controller;
		
		Connection con = null;
		String url = "jdbc:mysql://127.0.0.1:3306/DBMS_Final";
		String username = "root";
		String password = "00000000";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
			System.out.println("Connected!!!!!");

			try (PreparedStatement st = con.prepareStatement("call searchRecord(?)")) {
				st.setInt(1, ID);
				ResultSet rs = st.executeQuery();
				while (rs.next()) {
					temp.clear();
					temp1.clear();
					temp.add(rs.getString(1));
					temp1.add(rs.getString(2));
					System.out.print(temp);
					System.out.println("success121");
					
					ScrollPane scrollpane1 = new ScrollPane();
					ScrollPane scrollpane2 = new ScrollPane();

					scrollpane1.setPrefSize(209, 140);
					scrollpane2.setPrefSize(209, 140);

					
					pane.getChildren().add(scrollpane1);
					pane.getChildren().add(scrollpane2);
					
					scrollpane1.setLayoutX(94);
					scrollpane1.setLayoutY(224);
					scrollpane2.setLayoutX(302);
					scrollpane2.setLayoutY(224);
					
					VBox v1 = new VBox(temp.size());
					VBox v2 = new VBox(temp.size());
					v1.getChildren().clear();
					v2.getChildren().clear();
					scrollpane1.setContent(v1);
					scrollpane2.setContent(v2);

					for(int i = 0;i < temp.size();i++) {
						Label l1 = new Label(temp.get(i));
						Label l2 = new Label(temp1.get(i));
						v1.getChildren().add(l1);
						v2.getChildren().add(l2);
//					
					}
					Scene scene = new Scene(pane);
					scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
					stage.setScene(scene);
					stage.show();
						
				}
				
				rs.close();
			}
//
//			
			
			
		} catch (SQLException ex) {
			System.out.print("failed");
			throw new RuntimeException("Error Executing sql" + ex);
		}
	
	}

	@FXML
	void appendAct(ActionEvent event) throws ClassNotFoundException, IOException {
//		System.out.print(false);
		Connection con = null;
		String url = "jdbc:mysql://127.0.0.1:3306/DBMS_Final";
		String username = "root";
		String password = "00000000";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);

			System.out.println("Connected!");
			try (PreparedStatement st4 = con.prepareStatement("call credit_checking(?,?)")) {
				st4.setInt(1, id);
				st4.setInt(2, ID);
				System.out.println(id);
				ResultSet rsa = st4.executeQuery();
				while (rsa.next()) {
					resulta = rsa.getString(1);
					System.out.println("22222222");
					System.out.println(resulta);

				}

			} catch (SQLException ex) {
				System.out.print("failed");
				throw new RuntimeException("Error Executing sql" + ex);
			}
			try (PreparedStatement st5 = con.prepareStatement("call PC_checking(?,?)")) {
				st5.setInt(1, ID);
				st5.setInt(2, id);
				ResultSet rsb = st5.executeQuery();
				while (rsb.next()) {
					resultb = rsb.getString(1);
					System.out.println("333333");
					System.out.println(resultb);
				}
			} catch (SQLException ex) {
				System.out.print("failed");
				throw new RuntimeException("Error Executing sql" + ex);
			}
			try (PreparedStatement st = con.prepareStatement("call getCourseStatus2(?,?)")) {
				System.out.println("success11");
				st.setInt(1, ID);
				st.setInt(2, id);
				ResultSet rs = st.executeQuery();
				while (rs.next()) {
					System.out.println(rs.getString(1));
					if (rs.getString(1).equals("Yes")) {
						java.sql.Statement st3 = con.createStatement();
						String sql2 = "call deletion_applying(" + ID + "," + id + ")";
						((java.sql.Statement) st3).executeUpdate(sql2);
						appendbtn.setStyle("-fx-background-color:  #ffc900;");
						appendbtn.setText("加簽");
					} else if (rs.getString(1).equals("No") ) {
						System.out.println("肉");
						if (resulta.equals("true")) {
							String s = "abc";
							String b = s+resultb;
							if (b.length()==4) {
								java.sql.Statement st2 = con.createStatement();
								String sql = "call Applying(" + ID + "," + id + ")";
								((java.sql.Statement) st2).executeUpdate(sql);
								appendbtn.setStyle("-fx-background-color: gainsboro;");
								appendbtn.setText("申請中");
								System.out.print(b);
								System.out.print(resultb);
								
							} else {
								FXMLLoader loader = new FXMLLoader(getClass().getResource("學分超出上限警告.fxml"));

								AnchorPane pane = (AnchorPane) loader.load();
								PopOutController controller = loader.<PopOutController>getController();

								controller.setLabel("此課程未達修課標準");
								controller.initialize();
								Stage stage = new Stage();
//									
								Pane p = new Pane();
								p.getChildren().add(pane);
								Scene scene = new Scene(p);
								scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
								stage.setScene(scene);
								stage.show();
//										stage = (Stage) button.getScene().getWindow();
//										stage.close();
							}
						} else {
							FXMLLoader loader = new FXMLLoader(getClass().getResource("學分超出上限警告.fxml"));

							AnchorPane pane = (AnchorPane) loader.load();
							PopOutController controller = loader.<PopOutController>getController();

							controller.setLabel("學分超出上限");
							controller.initialize();
							Stage stage = new Stage();
//								
							Pane p = new Pane();
							p.getChildren().add(pane);
							Scene scene = new Scene(p);
							scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
							stage=(Stage) searchbtn.getScene().getWindow();
							stage.setScene(scene);
							stage.show();
						}

					}

				}

			} catch (SQLException ex) {
				System.out.print("failed");
				throw new RuntimeException("Error Executing sql" + ex);
			}

		} catch (SQLException ex) {
			throw new RuntimeException("Error Executing sql" + ex);
		}
//    
	}

	public TestController() {

	}

	public void setCname(String a) {
		cname = a;
	}

	public String getName() {
		return cname;
	}
	
	public void setCID(int i) {
		cid = i;
	}

	public void setID(int id) {
		this.id = id;
	}

	public int getID() {
		return id;
	}

	public void setSql(String a) {
		this.sql = a;
	}

	public String getSql() {
		return sql;
	}
	
	public void setCredit(int i) {
		this.credit = i;
	}
	
	public void setPName(String i) {
		this.PName = i;
	}
	public void setLoca(String i) {
		this.loca = i;
	}

	public void initialize() {
		name.setText(getName());
		timeteach.setText(String.format("課程ID: %d / 學分: %d / 地點: %s / 教授: %s", id, credit,loca, PName));
	}

	public void setAppendBtn() {
		appendbtn.setStyle("-fx-background-color: gainsboro;");
		appendbtn.setText("申請中");
	}

	public void setStuID(int x) {
		this.ID = x;
	}

	public int getStuID() {
		return ID;
	}

	public void setStatus(String s) {
		status = s;
	}
}
