package sports;
import java.awt.BorderLayout;
import javax.swing.*;
import java.sql.*;
import java.util.Vector;

public class Ranking{


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
				for(int i = 1; i <= c; i++){
					row.add(rank++);
					row.add(rs.getString(i));
				}
				data.add(row);
			}
			JFrame frame = new JFrame();

			frame.setSize(500,500);
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			JPanel panel = new JPanel();
			JTable table = new JTable(data,column);

			JScrollPane jsp = new JScrollPane(table);

			panel.setLayout(new BorderLayout());
			panel.add(jsp,BorderLayout.CENTER);

			JButton button = new JButton("확인");

			panel.add(button,BorderLayout.PAGE_END);

			frame.setContentPane(panel);
			frame.setVisible(true);

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
