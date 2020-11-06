package loginapp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import admin.AdminController;
import doctors.DoctorsFXMLController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class LoginController implements Initializable {
	LoginModel loginModel = new LoginModel();
	@FXML
	private Label loginStatus;
	@FXML
	private Label dbstatus;
	@FXML
	private Button loginButton;
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	@FXML
	private ComboBox<Option> combobox;

	public void initialize(URL url, ResourceBundle rb) {
		if (this.loginModel.isDatabaseConnected()) {
			this.dbstatus.setText("Connected");
		} else {
			this.dbstatus.setText("Not Connected");
		}
		this.combobox.setItems(FXCollections.observableArrayList(Option.values()));
	}

	@FXML
	public void Login(ActionEvent event) {
		try {
			if (this.loginModel.isLogin(this.username.getText(), this.password.getText(),((Option) this.combobox.getValue()).toString())) {
				Stage stage = (Stage)this.loginButton.getScene().getWindow();
		        stage.close();
		        switch (((Option)this.combobox.getValue()).toString())
		        {
		        case "Admin": 
		          adminLogin();
		          break;
		        case "Doctor": 
		          doctorLogin();
		        }
		      }
		      else
		      {
		        this.dbstatus.setText("Wrong Creditials");
		      }
		
		} catch (Exception localException) {
			localException.printStackTrace();
		}
	}

	public void doctorLogin() {
		try {
			Stage userStage = new Stage();
			FXMLLoader loader = new FXMLLoader();
			Pane root = (Pane) loader.load(getClass().getResource("/doctors/doctorsFXML.fxml").openStream());
			@SuppressWarnings("unused")
			DoctorsFXMLController doctorController = (DoctorsFXMLController) loader.getController();

			Scene scene = new Scene(root);
			userStage.setScene(scene);
			userStage.setTitle("Doctor Dashboard");
			userStage.setResizable(false);
			userStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void adminLogin() {
		try {
			Stage adminStage = new Stage();
			FXMLLoader adminLoader = new FXMLLoader();
			Pane adminroot = (Pane) adminLoader.load(getClass().getResource("/admin/Admin.fxml").openStream());
			@SuppressWarnings("unused")
			AdminController adminController = (AdminController) adminLoader.getController();

			Scene adminscene = new Scene(adminroot);

			adminStage.setScene(adminscene);
			adminStage.setTitle("Admin Dashboard");
			adminStage.setResizable(true);
			adminStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}