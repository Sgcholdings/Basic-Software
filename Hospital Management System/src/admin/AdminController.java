package admin;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import dbUtil.dbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminController implements Initializable {
	@FXML
	private TextField id;
	@FXML
	private TextField firstname;

	@FXML
	private TextField lastname;

	@FXML
	private TextField email;

	@FXML
	private DatePicker dob;
	@FXML
	private TableView<DoctorData> doctortable;
	@FXML
	private TableColumn<DoctorData, String> idcolumn;
	@FXML
	private TableColumn<DoctorData, String> firstnamecolumn;
	@FXML
	private TableColumn<DoctorData, String> lastnamecolumn;
	@FXML
	private TableColumn<DoctorData, String> emailcolumn;
	@FXML
	private TableColumn<DoctorData, String> dobcolumn;
	private ObservableList<DoctorData> data;
	@SuppressWarnings("unused")
	private dbConnection dc;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		this.dc = new dbConnection();

	}

	@FXML
	private void loadDoctorData(ActionEvent event) throws SQLException {
		try {
			Connection conn = dbConnection.getConnection();
			this.data = FXCollections.observableArrayList();
			ResultSet rs = conn.createStatement().executeQuery("SELECT *FROM doctors");
			while (rs.next()) {
				this.data.add(new DoctorData(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5)));

			}
		} catch (SQLException e) {
			System.err.println("Error" + e);
		}

		this.idcolumn.setCellValueFactory(new PropertyValueFactory<DoctorData, String>("ID"));
		this.firstnamecolumn.setCellValueFactory(new PropertyValueFactory<DoctorData, String>("FirstName"));
		this.lastnamecolumn.setCellValueFactory(new PropertyValueFactory<DoctorData, String>("LastName"));
		this.emailcolumn.setCellValueFactory(new PropertyValueFactory<DoctorData, String>("Email"));
		this.dobcolumn.setCellValueFactory(new PropertyValueFactory<DoctorData, String>("DOB"));

		this.doctortable.setItems(null);
		this.doctortable.setItems(this.data);
	}

	@FXML
	private void addDoctor(ActionEvent event) {
		String sqlInsert = "INSERT INTO doctors(id,fname,lname,email,DOB)VALUE(?,?,?,?,?)";

		try {
			Connection conn = dbConnection.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sqlInsert);
			stmt.setString(1, this.id.getText());
			stmt.setString(2, this.firstname.getText());
			stmt.setString(3, this.lastname.getText());
			stmt.setString(4, this.email.getText());
			stmt.setString(5, this.dob.getEditor().getText());
			stmt.executeUpdate();
			conn.close();

		} catch (SQLException e) {
			System.err.println("Got an exception!");
		      System.err.println(e.getMessage());
		}
	}

	@FXML
	private void clearFields(ActionEvent event) {
		this.id.setText("");
		this.firstname.setText("");
		this.lastname.setText("");
		this.email.setText("");
		this.dob.setValue(null);
	}
}
