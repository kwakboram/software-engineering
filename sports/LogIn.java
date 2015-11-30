/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class LogIn extends javax.swing.JFrame {
    Join jo;
    static String gID="Guest";
    static String gPW;
    static int gPoint=0;
 
    public LogIn() {
        initComponents();
    }

    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("로그인");
        setPreferredSize(new java.awt.Dimension(400, 250));
        setResizable(false);
        getContentPane().setLayout(null);
        

        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					jButton1ActionPerformed(evt);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
            }
        });

        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("맑은고딕", 0 , 16));
        jLabel4.setForeground(new java.awt.Color(77, 77, 77));
        jLabel4.setText("L O G I N");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(120, 18, 200, 30);
        
        jTextField1.setText("");
        getContentPane().add(jTextField1);
        jTextField1.setBounds(60, 80, 180, 30);

        jPasswordField1.setText("");
        getContentPane().add(jPasswordField1);
        jPasswordField1.setBounds(60, 150, 180, 30);

        jLabel1.setText("ID");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(60, 60, 40, 15);

        jLabel2.setText("PW");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(60, 130, 40, 15);
        
        
        
        
        
        jButton1.setIcon(new javax.swing.ImageIcon("./image/로그인.jpg"));
        jButton1.setPressedIcon(new javax.swing.ImageIcon("./image/로그인 눌림.jpg"));
        getContentPane().add(jButton1);
        jButton1.setBounds(270, 70, 82, 61);
        jButton1.setBorderPainted(false);
        
        jButton2.setIcon(new javax.swing.ImageIcon("./image/회원가입.jpg"));
        jButton2.setPressedIcon(new javax.swing.ImageIcon("./image/회원가입 눌림.jpg"));
        getContentPane().add(jButton2);
        jButton2.setBounds(270, 150, 80, 30);
        jButton2.setBorderPainted(false);
        
        jLabel3.setIcon(new javax.swing.ImageIcon("./image/배경.jpg"));
        getContentPane().add(jLabel3);
        jLabel3.setBounds(0, 0, 400, 250);
        pack();
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        jo.main(null);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) throws ClassNotFoundException {//GEN-FIRST:event_jButton1ActionPerformed
         Connection conn = null; 
         PreparedStatement pstmt = null;
         ResultSet rs = null;
        try {
            // TODO add your handling code here:
            String uID = jTextField1.getText();
            String uPass1;
            uPass1 = jPasswordField1.getText();
           // String url = "jdbc:mysql://165.229.125.113:3306/members";        // 사용하려는 데이터베이스명을 포함한 URL 기술
           // String id = "root";                                                    // 사용자 계정
          //  String pw = "sos123";     
            
            System.out.println("Ready");
          //  Class.forName("com.mysql.jdbc.Driver");                       // 데이터베이스와 연동하기 위해 DriverManager에 등록한다.
           // conn=DriverManager.getConnection(url,id,pw);              // DriverManager 객체로부터 Connection 객체를 얻어온다. 
            conn = DriverManager.getConnection("jdbc:mysql://165.229.125.12:3306/members", "root", "sos123");
            System.out.println("mysql connect");
            
            String getID="SELECT * from member where uID=?";
            pstmt = conn.prepareStatement(getID);
            pstmt.setString(1,uID);
            rs=pstmt.executeQuery();
            
            if(rs.first()==true){            	            
                 gID=rs.getString("uID");
                 gPW=rs.getString("uPass");
                 gPoint=rs.getInt("Point");      
                                	 
                 if(gPW == null ? uPass1 != null : !gPW.equals(uPass1)){
                     JOptionPane.showMessageDialog(null, "올바른 계정이 아닙니다.", "로그인 실패", JOptionPane.WARNING_MESSAGE);
                     gID="Guest";
                     gPW="";
                     gPoint=0;
                 }
                
                 System.out.println("gID :"+gID);
                 System.out.println("gPW :"+gPW);
                 System.out.println("Point :"+gPoint);
             
            }else{
            	JOptionPane.showMessageDialog(null, "올바른 계정이 아닙니다.", "로그인 실패", JOptionPane.WARNING_MESSAGE);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Join.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(rs != null) try{rs.close();}catch(SQLException ex){}  
            if(pstmt != null) try{pstmt.close();}catch(SQLException ex){}            // PreparedStatement 객체 해제
            if(conn != null) try{conn.close();}catch(SQLException ex){} 
            
           
            Main frame = new Main();
        	//창의 중앙 값을 계산한다.
        	Dimension scrnSize = Toolkit.getDefaultToolkit().getScreenSize();
        	int scrnWidth = frame.getSize().width;
        	int scrnHeight = frame.getSize().height;
        	int x = (scrnSize.width - scrnWidth)/2;
        	int y = (scrnSize.height - scrnHeight)/2;
        	//애플리케이션 창을 중앙으로 이동시킨다.
        	frame.setLocation(x,y);
        	//크기를 고정시킨다.
        	frame.setResizable(false);
        	//화면에 표시한다.
        	frame.setVisible(true);
            dispose();
            
           
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws SQLException, ClassNotFoundException {
     
                
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LogIn().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
