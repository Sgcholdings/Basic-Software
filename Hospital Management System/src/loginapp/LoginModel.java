package loginapp;

import dbUtil.dbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginModel {
	Connection connection;

	public LoginModel() {
		try {
			this.connection = dbConnection.getConnection();
		} catch (SQLException ex) {
			Logger.getLogger(LoginModel.class.getName()).log(Level.SEVERE, null, ex);
		}
		if (this.connection == null) {
			System.exit(1);
		}
	}

	public boolean isDatabaseConnected() {
		return this.connection != null;
	}

	public boolean isLogin(String user, String pass, String Option) throws SQLException {
		PreparedStatement pr = null;
		ResultSet rs = null;

		String sql = "select * from  hospital_system.login where username = \"" + user + "\" and password = \"" + pass
				+ "\" and division =\"" + Option + "\";";
		try {
			pr = this.connection.prepareStatement(sql);
			/*
			 * pr.setString(1, user); pr.setString(2, pass); pr.setString(3, Option);
			 */

			rs = pr.executeQuery();
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {

			if (rs != null) {
				rs.close();
			}
			if (pr != null) {
				pr.close();
			}

		}
	}
}
