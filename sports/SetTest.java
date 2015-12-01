/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sports;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 *
 * @author hwan
 */
public class SetTest extends javax.swing.JFrame  {
	
	
	Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
	String day = Integer.toString(calendar.get(Calendar.DATE));
	static int point;
	static List<String> labelText;
	

	public SetTest() {

		initComponents();
	}


	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jButton1 = new javax.swing.JButton();
		jLabel3 = new JLabel();
		jLabel4 = new JLabel();
		jLabel5 = new JLabel();
		jLabel6 = new JLabel();
		jLabel7 = new JLabel();
		jLabel8 = new JLabel();
		jLabel9 = new JLabel();

		//setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		
		this.addWindowListener( 
			      new java.awt.event.WindowAdapter() 
			      {
			        public void windowClosing( java.awt.event.WindowEvent e ) 
			        {
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
			          dispose() ;
			         // System.exit( 0 );
			        }
			      }
			    );
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("결과확인");
        setPreferredSize(new java.awt.Dimension(380	, 430));
        setResizable(false);
        getContentPane().setLayout(null);
        
        
		jLabel1.setText(day + "일 예측 결과");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(40, 50, 124, 15);
        
		jLabel2.setText("획득 포인트(결과) : " + point);
        getContentPane().add(jLabel2);
        jLabel2.setBounds(40, 320, 200, 15);

	
		getContentPane().add(jButton1);
        jButton1.setBounds(260, 320, 79, 39);
        jButton1.setIcon(new javax.swing.ImageIcon("./image/확인.jpg"));
        jButton1.setPressedIcon(new javax.swing.ImageIcon("./image/확인 눌림.jpg"));
        jButton1.setBorderPainted(false);
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		if (labelText.size() == 0)
			jLabel3.setText("결과 없음");
		for (int i = 0; i < labelText.size(); i++) {
			if (i == 0)
				jLabel3.setText(labelText.get(i));
			else if (i == 1)
				jLabel4.setText(labelText.get(i));
			else if (i == 2)
				jLabel5.setText(labelText.get(i));
			else if (i == 3)
				jLabel6.setText(labelText.get(i));
			else if (i == 4)
				jLabel7.setText(labelText.get(i));
		}
		
              
        getContentPane().add(jLabel3);
        jLabel3.setBounds(40, 110,200, 15);

        getContentPane().add(jLabel4);
        jLabel4.setBounds(40, 150,200, 15);

        getContentPane().add(jLabel5);
        jLabel5.setBounds(40, 190, 200, 15);

        getContentPane().add(jLabel6);
        jLabel6.setBounds(40, 230, 200, 15);

        getContentPane().add(jLabel7);
        jLabel7.setBounds(40, 270, 200, 15);
        
        
        java.text.SimpleDateFormat sd=new java.text.SimpleDateFormat("yyyy년 9월 dd일", java.util.Locale.KOREA); /////////////MM월 이어야함
        
        jLabel8.setText(sd.format(new java.util.Date()));
        getContentPane().add(jLabel8);
        jLabel8.setBounds(200, 50,150, 15);
        
        jLabel9.setIcon(new javax.swing.ImageIcon("./image/배경.jpg"));
        getContentPane().add(jLabel9);
        jLabel9.setBounds(0, 0, 380	, 430);



		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
		// TODO add your handling code here:

		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://165.229.125.12:3306/members", "root", "sos123");

			System.out.println("mysql connect");
			point += LogIn.gPoint;
			LogIn.gPoint = point;
			System.out.println(point);
			String sql = "update member set Point=? where uID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, point);
			pstmt.setString(2, LogIn.gID);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(SetTest.class.getName()).log(Level.SEVERE, null, ex);
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				} // PreparedStatement 객체 해제
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException ex) {
				}

			new Main().setVisible(true);
			dispose();
			// 포인트 초기화
			point = 0;
		}
	}// GEN-LAST:event_jButton1ActionPerformed

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		String day = Integer.toString(calendar.get(Calendar.DATE));
		File myfile = new File(".");
		List<String> file = new ArrayList<>();
		labelText = new ArrayList<String>();
		
		// myfile.listFile
		// .txt 즉 승부예측 파일들의 이름을 가진 ArrayList 생성
		for (File x : myfile.listFiles()) {
			if (x.getName().contains(".txt"))
				file.add(x.getName());
		}

		// .txt파일 몇개있는지 test용으로 출력
		file.forEach(x -> System.out.println(x));

		// gui에서 아무것도 출력할게 없는 경우가 아래의 idx값이 -1일때와 matches.size() > 0 조건을 만족하지
		// 못하는경우?
		// 생각해보면 경기가 없다면 승부예측 한 결과도 없을테고 그렇다면 txt파일도 당연히 없을듯
		////////////////////////////////////////////////////////////////////////////////////////////////
		int idx = file.indexOf(day + ".txt");// 특정 날짜의 파일 찾기 -1 이면 없다 -- 파일이..
		if (idx != -1) {
			System.out.println("index : " + idx + " " + file.get(idx));
			List<String> results = Match.getResults(calendar);
			List<String> matches = Match.getMatches(calendar);

			if (matches.size() > 0)
				matches = decoMatch(matches);
			////////////////////////////////////////////////////////////////////////////////////////////////

			// 아래의 리스트중 userChoices는 단순히 예측 파일의 값을 담아두기위한건데 여기서는 안쓰고 한줄 읽을때 마다
			// 바로 비교를 함
			// resultText는 O/X/없음 중 하나를 저장한다.
			List<String> userChoices = new ArrayList<>();
			List<String> resultText = new ArrayList<>();

			BufferedReader fr;
			// int point = 0; // 맞추면 +100 틀리면 -100 so simple
			// 파일에서 한 라인씩 읽어 (파일의 값이 null 이거나 승부 결과가 0이면 무시
			// case 1 파일에서 읽으면서 바로 처리
			try {
				fr = new BufferedReader(new FileReader(day + ".txt"));
				String line;
				int i = 0;
				while ((line = fr.readLine()) != null) {
					userChoices.add(line);
					if (!(line.equals("null") || results.get(i).equals("0"))) {
						if (line.equals(results.get(i))) {
							point += 100;
							resultText.add("O");
						} else {
							point -= 100;
							resultText.add("X");
						}

					} else
						resultText.add("없음");
					i++;
				}
				fr.close();

				// 출력 형태
				System.out.println(day + "일 예측 결과");
				// 경기의 수만큼
				for (i = 0; i < matches.size(); i++) {
					System.out.println(matches.get(i) + " " + resultText.get(i));
					labelText.add(matches.get(i) + "        " + resultText.get(i));
				}

				System.out.println("획득 포인트(결과) : " + point);
				

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			
		}
		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new SetTest().setVisible(true);
			}
		});
	}

	public static List<String> decoMatch(List<String> matches) {
		List<String> deco = new ArrayList<>();
		int size = matches.size() / 2;
		for (int i = 0; i < size; i++) {
			if (i == 0) {
				deco.add(matches.get(0) + "    vs    " + matches.get(1));
			} else if (i == 1) {
				deco.add(matches.get(2) + "    vs    " + matches.get(3));
			} else if (i == 2) {
				deco.add(matches.get(4) + "    vs    " + matches.get(5));
			} else if (i == 3) {
				deco.add(matches.get(6) + "    vs    " + matches.get(7));
			} else if (i == 4) {
				deco.add(matches.get(8) + "    vs    " + matches.get(9));
			}
		}
		return deco;
	}
	
	

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton jButton1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JLabel jLabel6;
	private JLabel jLabel7;
	private JLabel jLabel8;
	private JLabel jLabel9;

}
