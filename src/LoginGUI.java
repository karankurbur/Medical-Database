
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;

/**
 *
 * @author User
 */
public class LoginGUI extends javax.swing.JFrame {

	/**
	 * Creates new form NewJFrame
	 */

	public Connection myConn;

	public LoginGUI(Connection _myConn) {
		initComponents();
		myConn = _myConn;
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	public void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		LoginIDText = new javax.swing.JTextField("");
		passwordText = new javax.swing.JPasswordField("");
		loginButton = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		loginButton.setText("Login");
		loginButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				loginButtonActionPerformed(evt);
			}
		});

		jLabel1.setText("userID:");

		jLabel2.setText("password");

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGap(31, 31, 31)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
								.addComponent(jLabel1).addComponent(jLabel2))
						.addGap(18, 18, 18)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(loginButton)
								.addGroup(jPanel1Layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(passwordText, javax.swing.GroupLayout.DEFAULT_SIZE, 108,
												Short.MAX_VALUE)
										.addComponent(LoginIDText)))
						.addContainerGap(107, Short.MAX_VALUE))
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addContainerGap()));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGap(37, 37, 37)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(LoginIDText, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel1))
						.addGap(18, 18, 18)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(passwordText, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel2))
						.addGap(18, 18, 18).addComponent(loginButton).addGap(18, 18, 18)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		// jButton2.getAccessibleContext().setAccessibleName("Logout");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(204, 204, 204)
						.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(192, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(99, 99, 99)
						.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(79, Short.MAX_VALUE)));

		pack();
	}// </editor-fold>

	public void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {
		// get row of userID, compare string passwords. if true, print out true
		try {
			Statement myStamement = myConn.createStatement();
			String LoginID = LoginIDText.getText();
			String query = "SELECT * FROM Login WHERE Login_ID = " + LoginID;
			System.out.println(query);
			String password = passwordText.getText();
			ResultSet result = myStamement.executeQuery(query);
			String correctPass = "";
			int access = 0;
			int patID = 0;

			while (result.next()) {
				correctPass = result.getString("Password");
				access = result.getInt("Credentials");
				patID = result.getInt("Patient_ID");
			}

			// Login worked, closing login page
			if (correctPass.equals(password)) {
				setVisible(false);
				if (access == 1) {
					System.out.println("Logging in patient" + patID);
					PatientTreatments patientGUI = new PatientTreatments(myConn, patID);
					patientGUI.setVisible(true);
				}
				if (access == 2) {
					System.out.println("Loging in employee");
					int logIN = Integer.parseInt(LoginID);
					EmployeeGUI empGUI = new EmployeeGUI(myConn, logIN);
					empGUI.setVisible(true);
				}
				if (access == 3) {
					System.out.println("Loging in doctor");
					int logIN = Integer.parseInt(LoginID);
					DoctorGUI doc = new DoctorGUI(myConn, logIN);
					doc.setVisible(true);
				}

			}
			passwordText.setText("");
			LoginIDText.setText("");
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}

	// Variables declaration - do not modify
	public javax.swing.JButton loginButton;
	public javax.swing.JLabel jLabel1;
	public javax.swing.JLabel jLabel2;
	public javax.swing.JPanel jPanel1;
	public javax.swing.JTextField LoginIDText;
	public javax.swing.JPasswordField passwordText;
	// End of variables declaration
}