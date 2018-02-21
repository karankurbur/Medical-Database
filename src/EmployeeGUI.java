import java.sql.*;
import java.util.Random;

import javax.swing.JLabel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Owner
 */
public class EmployeeGUI extends javax.swing.JFrame {

	/**
	 * Creates new form EmployeeGUI
	 */

	public Connection myConn;
	int Login;

	public EmployeeGUI(Connection _myConn, int logIN) {
		initComponents();
		myConn = _myConn;
		Login = logIN;
		jLabel24.setText("LoginID:" + Login);

		employeeCRUDButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				employeeCRUD(evt);
			}
		});
		patientCRUDButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				patientCRUD(evt);
			}
		});
		doctorCRUDButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				doctorCRUD(evt);
			}
		});

	}

	public void doctorCRUD(java.awt.event.ActionEvent evt) {
		String firstname = jTextField10.getText();
		String lastname = jTextField11.getText();
		String phoneNumber = jTextField12.getText();
		String email = jTextField13.getText();
		String address = jTextField14.getText();
		String city = jTextField15.getText();
		String state = jTextField16.getText();
		// String zip = jTextField17.getText();
		String speciality = jTextField18.getText();

		String query = "insert into doctor (FirstName, LastName, PhoneNumber, Email, Speciality, Address, City, State, Zipcode) values ('"
				+ firstname + "', '" + lastname + "', '" + phoneNumber + "', '" + email + "', '" + speciality + "', '"
				+ address + "', '" + city + "', '" + state + "', 0";
		String extra = ")";

		query = query + extra;
		System.out.println(query);

		try {
			Statement statement = myConn.createStatement();
			statement.execute(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String doctorID = "";
		try {
			Statement statement = myConn.createStatement();
			// select * from employee where FirstName = firstname AND LastName = lastname;
			String q = "Select * from doctor where Firstname = '" + firstname + "' AND LastName = '" + lastname + "'";
			ResultSet resultSet = statement.executeQuery(q);
			while (resultSet.next()) {
				doctorID = resultSet.getString("Doctor_ID");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String pass = getSaltString();
		String insertIntoLogin = "insert into login (Doctor_ID, Password, Credentials) values (" + doctorID + ", '"
				+ pass + "', 3);";
		try {
			Statement statement = myConn.createStatement();
			statement.execute(insertIntoLogin);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// select * from login where Password = ' '
		String getLoginID = "select * from login where Password = '" + pass + "'";
		String diplayLoginId = "";
		System.out.println(getLoginID);
		try {
			Statement statement = myConn.createStatement();
			ResultSet set = statement.executeQuery(getLoginID);
			while (set.next()) {
				diplayLoginId = set.getString("Login_ID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jLabel24.setText("You created a new doctor with loginID : " + diplayLoginId + " and password " + pass);
	}

	public void patientCRUD(java.awt.event.ActionEvent evt) {
		String firstname = jTextField26.getText();
		String lastname = jTextField1.getText();
		String phoneNumber = jTextField2.getText();
		String email = jTextField3.getText();
		String address = jTextField4.getText();
		String city = jTextField5.getText();
		String state = jTextField6.getText();
		String zip = jTextField7.getText();
		String gender = jTextField8.getText();
		String insurance = jTextField9.getText();

		// INSERT NEW patient -> DONE
		String query = "insert into patient (FirstName, LastName, PhoneNumber, Email, Address, City, State, Zipcode, Gender, Insurance) values ('"
				+ firstname + "', '" + lastname + "', '" + phoneNumber + "', '" + email + "', '" + address + "', '"
				+ city + "', '" + state + "', '" + zip + "', '" + gender + "', '" + insurance;
		String extra = "');";
		query = query + extra;

		try {
			Statement statement = myConn.createStatement();
			statement.execute(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String doctorID = "";
		try {
			Statement statement = myConn.createStatement();
			// select * from employee where FirstName = firstname AND LastName = lastname;
			String q = "Select * from patient where Firstname = '" + firstname + "' AND LastName = '" + lastname + "'";
			ResultSet resultSet = statement.executeQuery(q);
			while (resultSet.next()) {
				doctorID = resultSet.getString("Patient_ID");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String pass = getSaltString();
		String insertIntoLogin = "insert into login (Patient_ID, Password, Credentials) values (" + doctorID + ", '"
				+ pass + "', 1);";
		try {
			Statement statement = myConn.createStatement();
			statement.execute(insertIntoLogin);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// select * from login where Password = ' '
		String getLoginID = "select * from login where Password = '" + pass + "'";
		String diplayLoginId = "";
		System.out.println(getLoginID);
		try {
			Statement statement = myConn.createStatement();
			ResultSet set = statement.executeQuery(getLoginID);
			while (set.next()) {
				diplayLoginId = set.getString("Login_ID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		jLabel24.setText("You created a new patient with loginID : " + diplayLoginId + " and password " + pass);

	}

	public void employeeCRUD(java.awt.event.ActionEvent evt) {
		String firstname = jTextField22.getText();
		String lastname = jTextField23.getText();
		String email = jTextField25.getText();
		System.out.println(firstname + lastname + email);

		// INSERT NEW EMPLOYEe -> DONE
		String query = "insert into employee (FirstName, LastName, EmailAddress) values ('" + firstname + "', '"
				+ lastname + "', '" + email;
		String extra = "');";
		query = query + extra;
		// System.out.println(query);
		try {
			Statement statement = myConn.createStatement();
			statement.execute(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String doctorID = "";
		try {
			Statement statement = myConn.createStatement();
			// select * from employee where FirstName = firstname AND LastName = lastname;
			String q = "Select * from employee where Firstname = '" + firstname + "' AND LastName = '" + lastname + "'";
			ResultSet resultSet = statement.executeQuery(q);
			while (resultSet.next()) {
				doctorID = resultSet.getString("Employee_ID");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String pass = getSaltString();
		String insertIntoLogin = "insert into login (Employee_ID, Password, Credentials) values (" + doctorID + ", '"
				+ pass + "', 2);";
		try {
			Statement statement = myConn.createStatement();
			statement.execute(insertIntoLogin);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// select * from login where Password = ' '
		String getLoginID = "select * from login where Password = '" + pass + "'";
		String diplayLoginId = "";
		System.out.println(getLoginID);
		try {
			Statement statement = myConn.createStatement();
			ResultSet set = statement.executeQuery(getLoginID);
			while (set.next()) {
				diplayLoginId = set.getString("Login_ID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jLabel24.setText("You created a new employee with loginID : " + diplayLoginId + " and password " + pass);
	}

	protected String getSaltString() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 8) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jLabel24 = new javax.swing.JLabel();
		jLabel25 = new javax.swing.JLabel();
		jLabel26 = new javax.swing.JLabel();
		jLabel27 = new javax.swing.JLabel();
		jLabel28 = new javax.swing.JLabel();
		jTextField22 = new javax.swing.JTextField(5);
		jLabel29 = new javax.swing.JLabel();
		jTextField23 = new javax.swing.JTextField(5);
		jLabel30 = new javax.swing.JLabel();
		choice3 = new java.awt.Choice();
		jLabel31 = new javax.swing.JLabel();
		jTextField25 = new javax.swing.JTextField(5);
		choice1 = new java.awt.Choice();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel32 = new javax.swing.JLabel();
		jTextField26 = new javax.swing.JTextField(5);
		jLabel3 = new javax.swing.JLabel();
		jTextField1 = new javax.swing.JTextField(5);
		jLabel4 = new javax.swing.JLabel();
		jTextField2 = new javax.swing.JTextField(5);
		jLabel5 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		jTextField3 = new javax.swing.JTextField(5);
		jLabel7 = new javax.swing.JLabel();
		jTextField4 = new javax.swing.JTextField(5);
		jLabel8 = new javax.swing.JLabel();
		jTextField5 = new javax.swing.JTextField(5);
		jLabel9 = new javax.swing.JLabel();
		jTextField6 = new javax.swing.JTextField(5);
		jLabel10 = new javax.swing.JLabel();
		jTextField7 = new javax.swing.JTextField(5);
		jLabel11 = new javax.swing.JLabel();
		jTextField8 = new javax.swing.JTextField(5);
		jLabel12 = new javax.swing.JLabel();
		jTextField9 = new javax.swing.JTextField(5);
		jLabel13 = new javax.swing.JLabel();
		choice2 = new java.awt.Choice();
		jLabel14 = new javax.swing.JLabel();
		jTextField10 = new javax.swing.JTextField(5);
		jLabel15 = new javax.swing.JLabel();
		jTextField11 = new javax.swing.JTextField(5);
		jLabel16 = new javax.swing.JLabel();
		jTextField12 = new javax.swing.JTextField(5);
		jTextField13 = new javax.swing.JTextField(5);
		jLabel18 = new javax.swing.JLabel();
		jTextField14 = new javax.swing.JTextField(5);
		jLabel17 = new javax.swing.JLabel();
		jTextField15 = new javax.swing.JTextField(5);
		jLabel19 = new javax.swing.JLabel();
		jTextField16 = new javax.swing.JTextField(5);
		jLabel20 = new javax.swing.JLabel();
		jTextField17 = new javax.swing.JTextField(5);
		jLabel21 = new javax.swing.JLabel();
		jLabel22 = new javax.swing.JLabel();
		jTextField18 = new javax.swing.JTextField(5);
		employeeCRUDButton = new javax.swing.JButton();
		patientCRUDButton = new javax.swing.JButton();
		doctorCRUDButton = new javax.swing.JButton();
		jPanel4 = new javax.swing.JPanel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jLabel24.setText("LoginID: ");

		jLabel28.setText("FirstName");

		// jTextField22.setText("jTextField21");

		jLabel29.setText("LastName");

		// jTextField23.setText("jTextField21");

		choice3.add("Create");
		choice3.add("Update");
		choice3.add("Delete");

		choice1.add("Create");
		choice1.add("Update");
		choice1.add("Delete");
		
		choice2.add("Create");
		choice2.add("Update");
		choice2.add("Delete");

		jLabel31.setText("Email");

		// jTextField25.setText("jTextField21");

		jLabel1.setText("Patient");

		jLabel2.setText("Employee");

		jLabel32.setText("FirstName");

		// jTextField26.setText("jTextField21");

		jLabel3.setText("LastName");

		// jTextField1.setText("jTextField1");

		jLabel4.setText("Phone Number");

		// jTextField2.setText("jTextField2");

		jLabel6.setText("Email");

		// jTextField3.setText("jTextField3");

		jLabel7.setText("Address");

		// jTextField4.setText("jTextField4");

		jLabel8.setText("City");

		// jTextField5.setText("jTextField5");

		jLabel9.setText("State");

		// jTextField6.setText("jTextField6");

		jLabel10.setText("Zip");

		// jTextField7.setText("jTextField7");

		jLabel11.setText("Gender");

		// jTextField8.setText("jTextField8");

		jLabel12.setText("Insurance");

		// jTextField9.setText("jTextField9");

		jLabel13.setText("Doctor");

		jLabel14.setText("FirstName");

		// jTextField10.setText("jTextField10");

		jLabel15.setText("LastName");

		// jTextField11.setText("jTextField1");

		jLabel16.setText("Phone Number");

		// jTextField12.setText("jTextField2");

		// jTextField13.setText("jTextField3");

		jLabel18.setText("Email");

		// jTextField14.setText("jTextField4");

		jLabel17.setText("City");

		// jTextField15.setText("jTextField5");

		jLabel19.setText("State");

		// jTextField16.setText("jTextField6");

		jLabel20.setText("Zip");

		// jTextField17.setText("jTextField7");

		jLabel21.setText("Address");

		jLabel22.setText("Speciality");

		// jTextField18.setText("jTextField18");

		employeeCRUDButton.setText("Go!");

		patientCRUDButton.setText("Go!");

		doctorCRUDButton.setText("Go!");

		javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
		jPanel4.setLayout(jPanel4Layout);
		jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 0, Short.MAX_VALUE));
		jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 53, Short.MAX_VALUE));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
										.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(
												jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGroup(javax.swing.GroupLayout.Alignment.LEADING,
												layout.createSequentialGroup().addGap(21, 21, 21).addGroup(layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(choice2, javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel13)
														.addGroup(layout.createSequentialGroup().addGroup(layout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.LEADING,
																		false)
																.addGroup(layout.createSequentialGroup()
																		.addComponent(
																				jLabel26,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(jLabel2))
																.addComponent(
																		jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(jLabel25))
														.addGroup(layout.createSequentialGroup().addGap(2, 2, 2)
																.addGroup(layout.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.LEADING)
																		.addGroup(layout.createSequentialGroup()
																				.addComponent(jLabel27)
																				.addPreferredGap(
																						javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																				.addGroup(layout.createParallelGroup(
																						javax.swing.GroupLayout.Alignment.LEADING)
																						.addGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING,
																								layout.createSequentialGroup()
																										.addGap(0, 0,
																												Short.MAX_VALUE)
																										.addComponent(
																												jLabel22)
																										.addPreferredGap(
																												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																										.addComponent(
																												jTextField18,
																												javax.swing.GroupLayout.PREFERRED_SIZE,
																												javax.swing.GroupLayout.DEFAULT_SIZE,
																												javax.swing.GroupLayout.PREFERRED_SIZE)
																										.addGap(397,
																												397,
																												397))
																						.addGroup(layout
																								.createSequentialGroup()
																								.addGroup(layout
																										.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING)
																										.addComponent(
																												employeeCRUDButton)
																										.addGroup(layout
																												.createSequentialGroup()
																												.addComponent(
																														choice3,
																														javax.swing.GroupLayout.PREFERRED_SIZE,
																														javax.swing.GroupLayout.DEFAULT_SIZE,
																														javax.swing.GroupLayout.PREFERRED_SIZE)
																												.addGap(55,
																														55,
																														55)
																												.addComponent(
																														jLabel28)
																												.addPreferredGap(
																														javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																												.addComponent(
																														jTextField22,
																														javax.swing.GroupLayout.PREFERRED_SIZE,
																														javax.swing.GroupLayout.DEFAULT_SIZE,
																														javax.swing.GroupLayout.PREFERRED_SIZE)
																												.addGap(18,
																														18,
																														18)
																												.addComponent(
																														jLabel29)
																												.addPreferredGap(
																														javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																												.addComponent(
																														jTextField23,
																														javax.swing.GroupLayout.PREFERRED_SIZE,
																														javax.swing.GroupLayout.DEFAULT_SIZE,
																														javax.swing.GroupLayout.PREFERRED_SIZE)
																												.addGap(18,
																														18,
																														18)
																												.addComponent(
																														jLabel30)
																												.addPreferredGap(
																														javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)

																												.addComponent(
																														jLabel31)
																												.addPreferredGap(
																														javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																												.addComponent(
																														jTextField25,
																														javax.swing.GroupLayout.PREFERRED_SIZE,
																														javax.swing.GroupLayout.DEFAULT_SIZE,
																														javax.swing.GroupLayout.PREFERRED_SIZE)))
																								.addGap(0, 0,
																										Short.MAX_VALUE))))
																		.addGroup(layout.createSequentialGroup()
																				.addGroup(layout.createParallelGroup(
																						javax.swing.GroupLayout.Alignment.LEADING)
																						.addGroup(layout
																								.createSequentialGroup()
																								.addGap(10, 10, 10)
																								.addComponent(
																										choice1,
																										javax.swing.GroupLayout.PREFERRED_SIZE,
																										javax.swing.GroupLayout.DEFAULT_SIZE,
																										javax.swing.GroupLayout.PREFERRED_SIZE)
																								.addGap(54, 54, 54))
																						.addGroup(
																								javax.swing.GroupLayout.Alignment.TRAILING,
																								layout.createSequentialGroup()
																										.addGroup(layout
																												.createParallelGroup(
																														javax.swing.GroupLayout.Alignment.LEADING)
																												.addComponent(
																														patientCRUDButton,
																														javax.swing.GroupLayout.Alignment.TRAILING)
																												.addComponent(
																														doctorCRUDButton,
																														javax.swing.GroupLayout.Alignment.TRAILING))
																										.addGap(18, 18,
																												18)))
																				.addGroup(layout.createParallelGroup(
																						javax.swing.GroupLayout.Alignment.LEADING)
																						.addGroup(layout
																								.createSequentialGroup()
																								.addGroup(layout
																										.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING,
																												false)
																										.addGroup(layout
																												.createSequentialGroup()
																												.addComponent(
																														jLabel7)
																												.addGap(18,
																														18,
																														18)
																												.addComponent(
																														jTextField4))
																										.addGroup(layout
																												.createSequentialGroup()
																												.addComponent(
																														jLabel32)
																												.addPreferredGap(
																														javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																												.addComponent(
																														jTextField26,
																														javax.swing.GroupLayout.PREFERRED_SIZE,
																														javax.swing.GroupLayout.DEFAULT_SIZE,
																														javax.swing.GroupLayout.PREFERRED_SIZE)))
																								.addGap(18, 18, 18)
																								.addGroup(layout
																										.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING,
																												false)
																										.addGroup(layout
																												.createSequentialGroup()
																												.addComponent(
																														jLabel3)
																												.addPreferredGap(
																														javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																												.addComponent(
																														jTextField1,
																														javax.swing.GroupLayout.PREFERRED_SIZE,
																														javax.swing.GroupLayout.DEFAULT_SIZE,
																														javax.swing.GroupLayout.PREFERRED_SIZE))
																										.addGroup(layout
																												.createSequentialGroup()
																												.addComponent(
																														jLabel8)
																												.addPreferredGap(
																														javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																														javax.swing.GroupLayout.DEFAULT_SIZE,
																														Short.MAX_VALUE)
																												.addComponent(
																														jTextField5,
																														javax.swing.GroupLayout.PREFERRED_SIZE,
																														javax.swing.GroupLayout.DEFAULT_SIZE,
																														javax.swing.GroupLayout.PREFERRED_SIZE)))
																								.addGap(18, 18, 18)
																								.addGroup(layout
																										.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING)
																										.addComponent(
																												jLabel4)
																										.addComponent(
																												jLabel9))
																								.addPreferredGap(
																										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																								.addGroup(layout
																										.createParallelGroup(
																												javax.swing.GroupLayout.Alignment.LEADING)
																										.addGroup(layout
																												.createSequentialGroup()
																												.addComponent(
																														jTextField2,
																														javax.swing.GroupLayout.PREFERRED_SIZE,
																														javax.swing.GroupLayout.DEFAULT_SIZE,
																														javax.swing.GroupLayout.PREFERRED_SIZE)
																												.addGap(18,
																														18,
																														18)
																												.addComponent(
																														jLabel6)
																												.addPreferredGap(
																														javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																												.addComponent(
																														jLabel5)
																												.addPreferredGap(
																														javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																												.addComponent(
																														jTextField3,
																														javax.swing.GroupLayout.PREFERRED_SIZE,
																														javax.swing.GroupLayout.DEFAULT_SIZE,
																														javax.swing.GroupLayout.PREFERRED_SIZE))
																										.addGroup(layout
																												.createParallelGroup(
																														javax.swing.GroupLayout.Alignment.TRAILING,
																														false)
																												.addGroup(
																														layout.createSequentialGroup()
																																.addGap(69,
																																		69,
																																		69)
																																.addComponent(
																																		jLabel18)
																																.addPreferredGap(
																																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																																		javax.swing.GroupLayout.DEFAULT_SIZE,
																																		Short.MAX_VALUE)
																																.addComponent(
																																		jTextField13,
																																		javax.swing.GroupLayout.PREFERRED_SIZE,
																																		javax.swing.GroupLayout.DEFAULT_SIZE,
																																		javax.swing.GroupLayout.PREFERRED_SIZE))
																												.addGroup(
																														layout.createSequentialGroup()
																																.addComponent(
																																		jTextField6,
																																		javax.swing.GroupLayout.PREFERRED_SIZE,
																																		javax.swing.GroupLayout.DEFAULT_SIZE,
																																		javax.swing.GroupLayout.PREFERRED_SIZE)
																																.addGap(18,
																																		18,
																																		18)
																																.addComponent(
																																		jLabel10)
																																.addGap(18,
																																		18,
																																		18)
																																.addComponent(
																																		jTextField7,
																																		javax.swing.GroupLayout.PREFERRED_SIZE,
																																		javax.swing.GroupLayout.DEFAULT_SIZE,
																																		javax.swing.GroupLayout.PREFERRED_SIZE)))))
																						.addGroup(layout
																								.createSequentialGroup()
																								.addComponent(jLabel11)
																								.addGap(18, 18, 18)
																								.addComponent(
																										jTextField8,
																										javax.swing.GroupLayout.PREFERRED_SIZE,
																										javax.swing.GroupLayout.DEFAULT_SIZE,
																										javax.swing.GroupLayout.PREFERRED_SIZE)
																								.addGap(34, 34, 34)
																								.addComponent(jLabel12)
																								.addPreferredGap(
																										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																								.addComponent(
																										jTextField9,
																										javax.swing.GroupLayout.PREFERRED_SIZE,
																										javax.swing.GroupLayout.DEFAULT_SIZE,
																										javax.swing.GroupLayout.PREFERRED_SIZE))
																						.addGroup(layout
																								.createSequentialGroup()
																								.addComponent(jLabel14)
																								.addPreferredGap(
																										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																								.addComponent(
																										jTextField10,
																										javax.swing.GroupLayout.PREFERRED_SIZE,
																										javax.swing.GroupLayout.DEFAULT_SIZE,
																										javax.swing.GroupLayout.PREFERRED_SIZE)
																								.addGap(18, 18, 18)
																								.addComponent(jLabel15)
																								.addPreferredGap(
																										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																								.addComponent(
																										jTextField11,
																										javax.swing.GroupLayout.PREFERRED_SIZE,
																										javax.swing.GroupLayout.DEFAULT_SIZE,
																										javax.swing.GroupLayout.PREFERRED_SIZE)
																								.addGap(18, 18, 18)
																								.addComponent(jLabel16)
																								.addPreferredGap(
																										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																								.addComponent(
																										jTextField12,
																										javax.swing.GroupLayout.PREFERRED_SIZE,
																										javax.swing.GroupLayout.DEFAULT_SIZE,
																										javax.swing.GroupLayout.PREFERRED_SIZE))
																						.addGroup(layout
																								.createSequentialGroup()
																								.addComponent(jLabel21)
																								.addGap(18, 18, 18)
																								.addComponent(
																										jTextField14)
																								.addGap(18, 18, 18)
																								.addComponent(jLabel17)
																								.addPreferredGap(
																										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																										javax.swing.GroupLayout.DEFAULT_SIZE,
																										Short.MAX_VALUE)
																								.addComponent(
																										jTextField15,
																										javax.swing.GroupLayout.PREFERRED_SIZE,
																										javax.swing.GroupLayout.DEFAULT_SIZE,
																										javax.swing.GroupLayout.PREFERRED_SIZE)
																								.addGap(18, 18, 18)
																								.addComponent(jLabel19)
																								.addPreferredGap(
																										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																								.addComponent(
																										jTextField16,
																										javax.swing.GroupLayout.PREFERRED_SIZE,
																										javax.swing.GroupLayout.DEFAULT_SIZE,
																										javax.swing.GroupLayout.PREFERRED_SIZE)
																								.addGap(18, 18, 18)
																								.addComponent(jLabel20)
																								.addGap(18, 18, 18)
																								.addComponent(
																										jTextField17,
																										javax.swing.GroupLayout.PREFERRED_SIZE,
																										javax.swing.GroupLayout.DEFAULT_SIZE,
																										javax.swing.GroupLayout.PREFERRED_SIZE))))
																		.addGroup(layout.createSequentialGroup()
																				.addComponent(jLabel1)
																				.addGap(0, 0, Short.MAX_VALUE)))))))
								.addGap(143, 143, 143)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel24)
						.addComponent(jLabel25))
				.addGap(11, 11, 11).addComponent(jLabel2)
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel26)
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel27).addComponent(jLabel28)
								.addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel29)
								.addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel30).addComponent(jLabel31).addComponent(jTextField25,
										javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addComponent(choice3, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(employeeCRUDButton)
				.addGap(37, 37, 37)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
						.addGroup(layout.createSequentialGroup().addComponent(jLabel1).addGap(8, 8, 8).addComponent(
								choice1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel32).addComponent(jLabel3)
								.addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel4)
								.addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel5).addComponent(jLabel6)
								.addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel7)
						.addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(jLabel8)
						.addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(jLabel9)
						.addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(jLabel10)
						.addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(patientCRUDButton))
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel11)
						.addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(jLabel12).addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGap(27, 27, 27)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
						.addGroup(layout.createSequentialGroup().addComponent(jLabel13)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(
										choice2, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel14)
								.addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel15)
								.addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel16)
								.addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel18)))
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel21)
						.addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(jLabel17)
						.addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(jLabel19)
						.addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(jLabel20)
						.addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addComponent(doctorCRUDButton))
				.addGap(18, 18, 18)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel22)
						.addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE)
				.addContainerGap(64, Short.MAX_VALUE)));

		pack();
	}// </editor-fold>

	// Variables declaration - do not modify
	private java.awt.Choice choice1;
	private java.awt.Choice choice2;
	private java.awt.Choice choice3;
	private javax.swing.JButton employeeCRUDButton;
	private javax.swing.JButton patientCRUDButton;
	private javax.swing.JButton doctorCRUDButton;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel11;
	private javax.swing.JLabel jLabel12;
	private javax.swing.JLabel jLabel13;
	private javax.swing.JLabel jLabel14;
	private javax.swing.JLabel jLabel15;
	private javax.swing.JLabel jLabel16;
	private javax.swing.JLabel jLabel17;
	private javax.swing.JLabel jLabel18;
	private javax.swing.JLabel jLabel19;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel20;
	private javax.swing.JLabel jLabel21;
	private javax.swing.JLabel jLabel22;
	private javax.swing.JLabel jLabel24;
	private javax.swing.JLabel jLabel25;
	private javax.swing.JLabel jLabel26;
	private javax.swing.JLabel jLabel27;
	private javax.swing.JLabel jLabel28;
	private javax.swing.JLabel jLabel29;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel30;
	private javax.swing.JLabel jLabel31;
	private javax.swing.JLabel jLabel32;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JTextField jTextField1;
	private javax.swing.JTextField jTextField10;
	private javax.swing.JTextField jTextField11;
	private javax.swing.JTextField jTextField12;
	private javax.swing.JTextField jTextField13;
	private javax.swing.JTextField jTextField14;
	private javax.swing.JTextField jTextField15;
	private javax.swing.JTextField jTextField16;
	private javax.swing.JTextField jTextField17;
	private javax.swing.JTextField jTextField18;
	private javax.swing.JTextField jTextField2;
	private javax.swing.JTextField jTextField22;
	private javax.swing.JTextField jTextField23;
	private javax.swing.JTextField jTextField25;
	private javax.swing.JTextField jTextField26;
	private javax.swing.JTextField jTextField3;
	private javax.swing.JTextField jTextField4;
	private javax.swing.JTextField jTextField5;
	private javax.swing.JTextField jTextField6;
	private javax.swing.JTextField jTextField7;
	private javax.swing.JTextField jTextField8;
	private javax.swing.JTextField jTextField9;
	// End of variables declaration
}
