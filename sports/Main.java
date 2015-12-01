/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;


public class Main extends javax.swing.JFrame {

       
       
        MultiChatServer mcs;
        MultiChatClient mcc;
       
        
    public Main() {
        
        initComponents();
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        setTitle("메인화면");
        setPreferredSize(new java.awt.Dimension(600,430));
        setResizable(false);
        getContentPane().setLayout(null);
        
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        if(LogIn.gID=="Guest"){
         getContentPane().add(jButton1);
         jButton1.setBounds(65, 280, 90, 42);
        }
         jButton1.setIcon(new javax.swing.ImageIcon("./image/메인로그인.jpg"));
         jButton1.setPressedIcon(new javax.swing.ImageIcon("./image/메인로그인 눌림.jpg"));
         jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
             public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
             }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        getContentPane().add(jButton2);
        jButton2.setIcon(new javax.swing.ImageIcon("./image/승부예측.jpg"));
        jButton2.setPressedIcon(new javax.swing.ImageIcon("./image/승부예측 눌림.jpg"));
        jButton2.setBounds(410, 105, 152, 52);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });


        getContentPane().add(jButton3);
        jButton3.setIcon(new javax.swing.ImageIcon("./image/랭킹.jpg"));
        jButton3.setPressedIcon(new javax.swing.ImageIcon("./image/랭킹 눌림.jpg"));
        jButton3.setBounds(410, 230, 152, 41);
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	jButton3.setEnabled(false);
            	new SwingWorker<Boolean,String>(){

					protected Boolean doInBackground() throws Exception {
						jButton3MouseClicked(evt);
						return true;
					}
					
					protected void done() {
						jButton3.setEnabled(true);
					}
            		
            	}.execute();
                
            }
        });

           getContentPane().add(jButton4);
        jButton4.setBounds(410, 285, 152, 41);
        jButton4.setIcon(new javax.swing.ImageIcon("./image/채팅.jpg"));
        jButton4.setPressedIcon(new javax.swing.ImageIcon("./image/채팅 눌림.jpg"));
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });
       
        getContentPane().add(jButton5);
        jButton5.setIcon(new javax.swing.ImageIcon("./image/결과확인.jpg"));
        jButton5.setPressedIcon(new javax.swing.ImageIcon("./image/결과확인 눌림.jpg"));
        jButton5.setBounds(410, 175, 152, 41);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        java.text.SimpleDateFormat sd=new java.text.SimpleDateFormat("yyyy년 9월 dd일", java.util.Locale.KOREA);
        /////////////////////////////////////////////////////////////////////MM이어야함
        jLabel1.setText(sd.format(new java.util.Date())); // NOI18N
        System.out.println(sd.format(new java.util.Date()));
        getContentPane().add(jLabel1);
        jLabel1.setBounds(50, 50, 205, 15);
        jLabel1.setFont(new java.awt.Font("맑은고딕", 1 , 14));
        jLabel1.setForeground(new java.awt.Color(77, 77, 77));
        
        
        jLabel2.setText(LogIn.gID+" 님 환영합니다!");
        jLabel2.setFont(new java.awt.Font("맑은고딕", 0 , 12));
        getContentPane().add(jLabel2);
        jLabel2.setBounds(70, 150, 270, 30);

        jLabel3.setText("포인트 : " + LogIn.gPoint);
        jLabel3.setFont(new java.awt.Font("맑은고딕", 0 , 12));
        getContentPane().add(jLabel3);
        jLabel3.setBounds(70, 200, 270, 30);

        jLabel6.setIcon(new javax.swing.ImageIcon("./image/kbo.png"));
        getContentPane().add(jLabel6);
        jLabel6.setBounds(250, 130,90 , 124);
        
        jLabel5.setIcon(new javax.swing.ImageIcon("./image/점선.jpg"));
        getContentPane().add(jLabel5);
        jLabel5.setBounds(40, 90, 335, 260);
        jLabel4.setIcon(new javax.swing.ImageIcon("./image/배경.jpg"));
        getContentPane().add(jLabel4);
        jLabel4.setBounds(0, 0, 600, 430);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
     if(LogIn.gID!="Guest"){
    	MultiChatClient.main(null);
     }else{
         JOptionPane.showMessageDialog(null, "로그인하여야 합니다.", "실패", JOptionPane.WARNING_MESSAGE);
     }
    }//GEN-LAST:event_jButton4MouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        
    }//GEN-LAST:event_formWindowOpened

//------------------------------------------------------------------------------------------------------------
    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        Ranking.main(null);
    }
//-------------------------------------------------------------------------------------------------------------
    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {
            try {
                // TODO add your handling code here:
                dispose();
                LogIn.main(null);
               
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    	 if(LogIn.gID!="Guest"){
         	dispose();
             Match.main(null);
         }else{
             JOptionPane.showMessageDialog(null, "로그인하여야 합니다.", "실패", JOptionPane.WARNING_MESSAGE);
         }
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        if(LogIn.gID!="Guest"){
        	dispose();
            SetTest.main(null);
        }else{
            JOptionPane.showMessageDialog(null, "로그인하여야 합니다.", "실패", JOptionPane.WARNING_MESSAGE);
        }
        
    }//GEN-LAST:event_jButton5ActionPerformed
    
 
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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
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
               
            }
        });
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables
}
