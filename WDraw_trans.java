/**
 * @(#)WDraw_trans.java
 *
 *
 * @author 
 * 
 */


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class WDraw_trans extends JFrame implements ActionListener{
	// JDBC driver name and database URL 
	   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";   
	   static final String DB_URL = "jdbc:mysql://localhost/atm";  
	   //  Database credentials 
	   static final String USER = "root"; 
	   static final String PASS = ""; 
	    
	
	JPasswordField txtuser=new JPasswordField(25);
	JTextField txtpass=new JTextField(25);
	JTextField txtwid=new JTextField(25);
	JLabel lbluser=new JLabel("Pin Number: ");
	JLabel lblpass=new JLabel("WithDraw: ");
	JLabel lblwid=new JLabel("Balanced: ");
	JButton btnOk=new JButton("Back to Menu");
	JButton btnRegister=new JButton("Withdraw");
	

	Connection cn;
	//ResultSet rs;
	Statement st;
	PreparedStatement ps;
	public WDraw_trans(){
		super("Withdraw Transaction");
		
		JPanel pane=new JPanel();
		pane.setLayout(null);
		//----Adding Components into your Frame
		pane.add(txtuser);
 		pane.add(txtpass);
 		pane.add(txtwid);
		pane.add(lbluser);
 		pane.add(lblpass);
 		pane.add(lblwid);	
		pane.add(btnOk);
		pane.add(btnRegister);
		//-----Setting the location of the components
		lbluser.setBounds(10,20,80,20);
 		lblpass.setBounds(10,40,80,20);
 		lblwid.setBounds(600,60,80,20);
		txtuser.setBounds(100,20,100,20);
 		txtpass.setBounds(600,60,100,20);
 		txtwid.setBounds(100,40,100,20);
 		
		btnOk.setBounds(80,120,140,20);
		btnRegister.setBounds(100,90,100,20);
		//-----Adding the an actionlistener to the buttons
		btnOk.addActionListener(this);
		btnRegister.addActionListener(this);
		
		txtpass.setEditable(false);
		btnOk.setToolTipText("Click To Back to Menu");
		btnOk.setForeground(Color.white);
		btnRegister.setForeground(Color.white);
		btnOk.setBackground(Color.black);
		btnRegister.setBackground(Color.black);

		setContentPane(pane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		
		pane.setBorder(BorderFactory.createTitledBorder(
           BorderFactory.createEtchedBorder(), "Withdraw Transaction"));
		
				JLabel lbl = new JLabel();
		
		lbl.setBounds(0,0,250,200);
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
			
			
	third_atm panel = new third_atm();
	panel.setSize(330,300);
	panel.setVisible(true);
	panel.setResizable(false);
	panel.setLocation(400,250);
	dispose();

		}else if(source==btnRegister){
		//	btnRegister.setText("Withdraw");
			
			try{
				try{
					st= cn.createStatement();	
					ResultSet rs=st.executeQuery("SELECT * FROM tbl_list WHERE Password ='"+txtuser.getText()+"'");
						while(rs.next()){
							txtpass.setText(rs.getString(9));
							int a = Integer.parseInt(txtpass.getText());
							int b = Integer.parseInt(txtwid.getText());
							
							
							if(a<b){
							//	if(b<10){
								
							//	if(b>100)
								
									JOptionPane.showMessageDialog(null,"Transaction cannot process","ATM",JOptionPane.INFORMATION_MESSAGE);
							txtwid.setText("");
							//}else{
							//	JOptionPane.showMessageDialog(null,"Transaction c s","ATM",JOptionPane.INFORMATION_MESSAGE);
							//}
							
							}else{
								if(b<10){
									JOptionPane.showMessageDialog(null,"The Minimum cash you can withdraw is 10","ATM",JOptionPane.INFORMATION_MESSAGE);
								
								}else{
								
							
							int sum = a-b;
						
							txtpass.setText((sum+""));
							txtwid.setText("");
							JOptionPane.showMessageDialog(null,"You WithDraw "+b,"ATM",JOptionPane.INFORMATION_MESSAGE);
								
				ps = cn.prepareStatement("UPDATE tbl_list SET Password = '" + txtuser.getText() + "',ct = '" + sum +  "'WHERE Password = '" + txtuser.getText() + "'");
				ps.executeUpdate();
				txtuser.requestFocus(true);
							}
							}
							}
					st.close();
					}catch(NumberFormatException nfe){
								JOptionPane.showMessageDialog(null,"Enter now the amount to withdraw","ATM",JOptionPane.INFORMATION_MESSAGE);
							
							}
				}
				catch(SQLException s){
					System.out.println("No record found!\n\n\n");
					System.out.println("SQL Error" + s.toString() + " " + s.getErrorCode() + " " + s.getSQLState());
									}
				catch(Exception x){
					System.out.println("Error" + x.toString()+" " + x.getMessage());
								}
			
		}
	}
	
	public static void main(String[]args){
		WDraw_trans log=new WDraw_trans();
		
		log.setLocation(400,250);
		log.setSize(250,200);
		log.setResizable(false);
		log.setVisible(true);
		
	
	}
}