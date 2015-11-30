/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author hwan
 */
public class Join extends javax.swing.JFrame {

    /**
     * Creates new form Join
     */
    public Join() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jPasswordField2 = new javax.swing.JPasswordField();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("회원가입");
        setPreferredSize(new java.awt.Dimension(400, 400));
        setResizable(false);
        getContentPane().setLayout(null);
        
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jPasswordField1.setText("jPasswordField1");

    
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					jButton1ActionPerformed(evt);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });

        jPasswordField2.setText("jPasswordField2");

  
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        
        jLabel1.setFont(new java.awt.Font("맑은고딕", 0 , 16));
        jLabel1.setForeground(new java.awt.Color(77, 77, 77));
        jLabel1.setText("J O I N");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(170, 20, 200, 30);
        
        jLabel2.setText("ID");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(70, 95, 40, 15);
        
        jLabel3.setText("PW");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(70, 155, 40, 15);

        jLabel4.setText("PW 확인");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(70, 215, 80, 15);

        jTextField1.setText("");
        getContentPane().add(jTextField1);
        jTextField1.setBounds(130, 90, 180, 30);

        jPasswordField1.setText("jPasswordField1");
        getContentPane().add(jPasswordField1);
        jPasswordField1.setBounds(130, 150, 180, 30);

        jPasswordField2.setText("jPasswordField2");
        getContentPane().add(jPasswordField2);
        jPasswordField2.setBounds(130, 210, 180, 30);

        jButton1.setIcon(new javax.swing.ImageIcon("./image/JOIN.jpg"));
        jButton1.setPressedIcon(new javax.swing.ImageIcon("./image/JOIN 눌림.jpg"));
        getContentPane().add(jButton1);
        jButton1.setBounds(230, 270, 79, 39);
        
        jButton2.setIcon(new javax.swing.ImageIcon("./image/취소.jpg"));
        jButton2.setPressedIcon(new javax.swing.ImageIcon("./image/취소 눌림.jpg"));
        getContentPane().add(jButton2);
        jButton2.setBounds(155, 270, 70, 39);
       
        jLabel5.setIcon(new javax.swing.ImageIcon("./image/배경.jpg"));
        getContentPane().add(jLabel5);
        jLabel5.setBounds(0, 0, 400, 400);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) throws ClassNotFoundException {//GEN-FIRST:event_jButton1ActionPerformed
         Connection conn = null; 
         PreparedStatement pstmt = null;
         ResultSet rs = null;
        try {
            // TODO add your handling code here:
            String uID = jTextField1.getText();
            String uPass1;
            String CheckID=null;
            uPass1 = jPasswordField1.getText();
            String uPass2=jPasswordField2.getText();
            
            if(uPass1 == null ? uPass2 != null : !uPass1.equals(uPass2)){
                JOptionPane.showMessageDialog(null, "비밀번호를 다시 입력하세요", "오류", JOptionPane.WARNING_MESSAGE);
                System.exit(0);
            }
        
            conn = DriverManager.getConnection("jdbc:mysql://165.229.125.12:3306/members", "root", "sos123");
            String chck="SELECT uID from member where uID = ?";
            pstmt = conn.prepareStatement(chck);
            pstmt.setString(1,uID);
            rs=pstmt.executeQuery();
            while(rs.next()){
                CheckID=rs.getString("uID");
            }
            if(CheckID == null ? uID == null : CheckID.equals(uID) || uID=="Guest")
            {
                JOptionPane.showMessageDialog(null, "사용할 수 없는 아이디입니다.", "오류", JOptionPane.WARNING_MESSAGE);
                System.exit(0);
            }
            
            
            String sql="INSERT INTO member values(?,?,?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,uID);
            pstmt.setString(2,uPass1);
            pstmt.setInt(3, 1000);
            
            pstmt.executeUpdate();
         
            JOptionPane.showMessageDialog(null, "등록되었습니다.", "등록", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(Join.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if(rs != null) try{rs.close();}catch(SQLException ex){} 
            if(pstmt != null) try{pstmt.close();}catch(SQLException ex){}            // PreparedStatement 객체 해제
            if(conn != null) try{conn.close();}catch(SQLException ex){} 
            
            dispose();
        }
        
            
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Join.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Join.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Join.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Join.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
      
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Join().setVisible(true);
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
