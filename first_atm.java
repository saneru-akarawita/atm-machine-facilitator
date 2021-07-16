//login


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class first_atm extends JFrame implements ActionListener{
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";   
	   static final String DB_URL = "jdbc:mysql://localhost/atm";  
	   //  Database credentials 
	   static final String USER = "root"; 
	   static final String PASS = ""; 
	
	JTextField txtuser=new JTextField(25);
	JPasswordField txtpass=new JPasswordField(25);
	JLabel lbluser=new JLabel("Username: ");
	JLabel lblpass=new JLabel("Pin Number: ");
	JButton btnOk=new JButton("login");
	JButton btnRegister=new JButton("register");
	//JButton btnBlock = new JButton("Lock Account >>");

	Connection cn;
	//ResultSet rs;
	Statement st;
	PreparedStatement ps;
	public first_atm(){
		
		JPanel pane=new JPanel();
		pane.setLayout(null);
		//----Adding Components into your Frame
		pane.add(txtuser);
		pane.add(txtpass);
		pane.add(lbluser);
		pane.add(lblpass);	
		pane.add(btnOk);
		pane.add(btnRegister);
		//pane.add(btnBlock);
		//-----Setting the location of the components
		lbluser.setBounds(10,20,80,20);
		lblpass.setBounds(10,40,80,20);
		txtuser.setBounds(100,20,100,20);
		txtpass.setBounds(100,40,100,20);
		btnOk.setBounds(50,70,75,20);
		btnRegister.setBounds(125,70,83,20);
		
		btnOk.addActionListener(this);
		btnRegister.addActionListener(this);
		
			

		setContentPane(pane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
			JLabel lbl = new JLabel(new ImageIcon("back.jpg"));
		
		lbl.setBounds(0,0,250,150);
		pane.add(lbl);

		
		//connection
				
		try{
			 Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(DB_URL,USER,PASS);
		}catch(ClassNotFoundException e)  {
 			System.err.println("Failed to load driver");
 			e.printStackTrace();
 	
 		}catch(SQLException e){
 			System.err.println("Unable to connect");
 			e.printStackTrace();
 			
 		}
	}
	
	public void actionPerformed(ActionEvent e){
		Object source=e.getSource();
		
		if(source==btnOk){
			try{
				String str1=txtuser.getText();
				String str2=txtpass.getText();
				if((str1.length()==0 || str2.length()==0)){
    					JOptionPane.showMessageDialog(null,"Connot be Process","WARNING",JOptionPane.WARNING_MESSAGE);
    				}
    				else{
				st=cn.createStatement();
				String strUser="";
				String strPass="";
				
				ResultSet rs=st.executeQuery("SELECT * FROM tbl_list WHERE UserName='"+str1+"'");
				while(rs.next()){
					strUser=rs.getString(1);
					strPass=rs.getString(4);
				}
				
				
				
				
				
				st.close();
		
				if(strUser.equals(str1)){
					if(strPass.equals(str2)){
						
					
    					
						JOptionPane.showMessageDialog(null,"Welcome "+txtuser.getText(),"Welcome",JOptionPane.INFORMATION_MESSAGE);
						
						third_atm panel = new third_atm();
						panel.setSize(330,300);
						panel.setVisible(true);
						panel.setResizable(false);
						panel.setLocation(400,250);
						dispose();
						
					
					}else{
						JOptionPane.showMessageDialog(null,"Username found but the password is incorrect!","Security Pass",JOptionPane.WARNING_MESSAGE);
						txtpass.requestFocus(true);
					}
				}else{
					JOptionPane.showMessageDialog(null,"Username is incorrect!","Security Pass",JOptionPane.WARNING_MESSAGE);
					txtuser.requestFocus(true);
				}
    				}	
			}catch(SQLException w){
			}
		}else if(source==btnRegister){
			
				second_atm panel = new second_atm();
				panel.setSize(370,400);
				panel.setVisible(true);
				panel.setResizable(false);
				panel.setLocation(400,250);
				dispose();
		
		}
		
	
	}
	
	public static void main(String[]args){
		first_atm log=new first_atm();
		
		log.setLocation(400,250);
		log.setSize(250,150);
		log.setTitle("Log-In");
		log.setResizable(false);
		log.setVisible(true);
		
	
	}
}