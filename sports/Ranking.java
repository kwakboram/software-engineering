/*
 * Ranking.java
 * 랭킹정보를 제공한다.
 * 메인 화면에서 랭킹버튼을 클릭시 사용된다.
 */

package sports;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;

import java.sql.*;
import java.util.Vector;

public class Ranking{

	//main : DB에서 사용자의 정보를 점수 순으로 정렬하여 테이블에 표시한다.
	public static void main(String[] args){
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String s;

		//connect your app to mysql database
		try{
			con = DriverManager.getConnection("jdbc:mysql://165.229.125.12:3306/members", "root", "sos123");
			st = con.createStatement();
			s = "select uID,Point from member order by Point desc";
			rs = st.executeQuery(s);
			ResultSetMetaData rsmt = rs.getMetaData();
			int c = rsmt.getColumnCount();
			Vector column = new Vector(c);

			column.add("순위");
			for(int i = 1; i <= c; i++)
			{
				column.add(rsmt.getColumnName(i));
			}

			Vector data = new Vector();
			Vector row = new Vector();
			int rank=1;

			while(rs.next())
			{
				row = new Vector(c);
				row.add(rank++);
				for(int i = 1; i <= c; i++){
					//row.add(rank++);
					row.add(rs.getString(i));
				}
				data.add(row);
			}
			JFrame frame = new JFrame();
			
			frame.setSize(350,420);
			frame.setResizable(false);
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			JPanel panel = new JPanel();
			JTable table = new JTable(data,column);
			
			JScrollPane jsp = new JScrollPane(table);
			//jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			panel.setLayout(new BorderLayout());
			panel.add(jsp,BorderLayout.CENTER);
		
			javax.swing.table.DefaultTableCellRenderer tScheduleCellRenderer = new javax.swing.table.DefaultTableCellRenderer(); //가운데정렬
			tScheduleCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
			javax.swing.table.TableColumnModel tcmSchedule = table.getColumnModel();
			for (int i = 0; i < tcmSchedule.getColumnCount(); i++) {
				tcmSchedule.getColumn(i).setCellRenderer(tScheduleCellRenderer);
			}

		
			JButton button = new JButton("확인");
			panel.add(button,BorderLayout.PAGE_END);

			frame.setContentPane(panel);
			//창의 중앙 값을 계산한다.
			Dimension scrnSize = Toolkit.getDefaultToolkit().getScreenSize();
			int scrnWidth = frame.getSize().width;
			int scrnHeight = frame.getSize().height;
			int x = (scrnSize.width - scrnWidth)/2;
			int y = (scrnSize.height - scrnHeight)/2;
			//애플리케이션 창을 중앙으로 이동시킨다.
			frame.setLocation(x,y);
			//화면에 표시한다.
			frame.setVisible(true);
			
			JLabel jLabel1 = new JLabel();
			jLabel1 = new javax.swing.JLabel();
			panel.add(jLabel1,BorderLayout.PAGE_START);
			
			
			button.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent evt) {
					frame.dispose();
				}
			}       		
					);       
		}catch(Exception e){
			JOptionPane.showMessageDialog(null, "ERROR");
		}finally{
			try{
				st.close();
				rs.close();
				con.close();
			}catch(Exception e){
				JOptionPane.showMessageDialog(null, "ERROR CLOSE");
			}
		}
	}
}
