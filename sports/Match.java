/*
 * Match.java
 * 승부예측기능을 제공한다.
 * 메인화면에서 승부예측 버튼을 클릭시 사용된다.
 */
package sports;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.swing.JOptionPane;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


/**
 *
 * @author hwan
 */
public class Match extends javax.swing.JFrame {
	private int date2;
	public Match() throws IOException {
		initComponents();
	}

	//swing components 초기화
	private void initComponents() throws IOException {

		buttonGroup1 = new javax.swing.ButtonGroup();
		buttonGroup2 = new javax.swing.ButtonGroup();
		buttonGroup3 = new javax.swing.ButtonGroup();
		buttonGroup4 = new javax.swing.ButtonGroup();
		buttonGroup5 = new javax.swing.ButtonGroup();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		jLabel8 = new javax.swing.JLabel();
		jLabel9 = new javax.swing.JLabel();
		jRadioButton1 = new javax.swing.JRadioButton();
		jRadioButton2 = new javax.swing.JRadioButton();
		jRadioButton3 = new javax.swing.JRadioButton();
		jRadioButton4 = new javax.swing.JRadioButton();
		jRadioButton5 = new javax.swing.JRadioButton();
		jRadioButton6 = new javax.swing.JRadioButton();
		jRadioButton7 = new javax.swing.JRadioButton();
		jRadioButton8 = new javax.swing.JRadioButton();
		jRadioButton9 = new javax.swing.JRadioButton();
		jRadioButton10 = new javax.swing.JRadioButton();
		jRadioButton11 = new javax.swing.JRadioButton();
		jRadioButton12 = new javax.swing.JRadioButton();
		jRadioButton13 = new javax.swing.JRadioButton();
		jRadioButton14 = new javax.swing.JRadioButton();
		jRadioButton15 = new javax.swing.JRadioButton();
		jButton1 = new javax.swing.JButton();

	
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		jComboBox1 = new javax.swing.JComboBox<>();
		
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("MATCH");
		setPreferredSize(new java.awt.Dimension(540, 560));
		setResizable(false);
		getContentPane().setLayout(null);
		pack();
		jLabel8.setFont(new java.awt.Font("맑은고딕", 1 , 16));
        jLabel8.setForeground(new java.awt.Color(77, 77, 77));
        jLabel8.setText("M A T C H");
        jLabel8.setForeground(new java.awt.Color(77, 77, 77));
        getContentPane().add(jLabel8);
        jLabel8.setBounds(220, 20, 200, 30);
		
		jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(date2==0){
					JOptionPane.showMessageDialog(null, "날짜를 선택하세요.","", JOptionPane.WARNING_MESSAGE);//////////////////여기수정함 (날짜선택안했을경우)
					return;
				}
				try { 
					PrintWriter pw = new PrintWriter(date2+".txt");
					String data1=getMatch1();   				         
					pw.println(data1);
					String data2=getMatch2();
					pw.println(data2);
					String data3=getMatch3();
					pw.println(data3);
					String data4=getMatch4();
					pw.println(data4);
					String data5=getMatch5();
					pw.println(data5);
					pw.close();
				
					JOptionPane.showMessageDialog(null, "저장되었습니다.", "성공", JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					Main frame = new Main();
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
			}
		});

		buttonGroup1.add(jRadioButton1);
		buttonGroup1.add(jRadioButton2);
		buttonGroup1.add(jRadioButton3);

		buttonGroup2.add(jRadioButton4);
		buttonGroup2.add(jRadioButton5);
		buttonGroup2.add(jRadioButton6);

		buttonGroup3.add(jRadioButton7);
		buttonGroup3.add(jRadioButton8);
		buttonGroup3.add(jRadioButton9);

		buttonGroup4.add(jRadioButton10);
		buttonGroup4.add(jRadioButton11);
		buttonGroup4.add(jRadioButton12);

		buttonGroup5.add(jRadioButton13);
		buttonGroup5.add(jRadioButton14);
		buttonGroup5.add(jRadioButton15);
		
		jComboBox1=new javax.swing.JComboBox();
		jComboBox1.addItem("날짜를 선택해주세요");
		calendar.add(Calendar.DATE, 1);
		jComboBox1.addItem(calendar.get(Calendar.DATE)+"일");
		calendar.add(Calendar.DATE, 1);
		jComboBox1.addItem(calendar.get(Calendar.DATE)+"일");
		calendar.add(Calendar.DATE, 1);
		jComboBox1.addItem(calendar.get(Calendar.DATE)+"일");
		jComboBox1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jComboBox1ActionPerformed(evt);
			}
		});
		getContentPane().add(jComboBox1);
		jComboBox1.setBounds(43, 78, 200, 30);

		jButton1.setIcon(new javax.swing.ImageIcon("./image/확인.jpg"));
        jButton1.setPressedIcon(new javax.swing.ImageIcon("./image/확인 눌림.jpg"));
        getContentPane().add(jButton1);
        jButton1.setBounds(400,440, 79, 39);
        jButton1.setBorderPainted(false);
        
        java.text.SimpleDateFormat sd=new java.text.SimpleDateFormat("yyyy년 09월 dd일", java.util.Locale.KOREA);
        jLabel9.setText("오늘날짜 : "+sd.format(new java.util.Date())); // NOI18N
        System.out.println(sd.format(new java.util.Date()));
        getContentPane().add(jLabel9);
        jLabel9.setBounds(320, 90, 205, 15);
        jLabel9.setFont(new java.awt.Font("맑은고딕", 0 , 13));
        jLabel9.setForeground(new java.awt.Color(77, 77, 77));
        
		
		jLabel6.setIcon(new javax.swing.ImageIcon("./image/배경.jpg"));
        getContentPane().add(jLabel6);
        jLabel6.setBounds(0, 0, 540, 560);
	}
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Match frame = new Match();
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
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
	String getMatch1()
	{
		if(jRadioButton1.isSelected())
		{
			return "1";
		}
		else if(jRadioButton2.isSelected())
		{
			return "3";
		}
		else if(jRadioButton3.isSelected())
		{
			return "2";
		}
		else
		{
			return null;
		}
	}
	String getMatch2()
	{
		if(jRadioButton4.isSelected())
		{
			return "1";
		}
		else if(jRadioButton5.isSelected())
		{
			return "3";
		}
		else if(jRadioButton6.isSelected())
		{
			return "2";
		}
		else
		{
			return null;
		}
	}
	String getMatch3()
	{
		if(jRadioButton7.isSelected())
		{
			return "1";
		}
		else if(jRadioButton8.isSelected())
		{
			return "3";
		}
		else if(jRadioButton9.isSelected())
		{
			return "2";
		}
		else
		{
			return null;
		}
	}
	String getMatch4()
	{
		if(jRadioButton10.isSelected())
		{
			return "1";
		}
		else if(jRadioButton11.isSelected())
		{
			return "3";
		}
		else if(jRadioButton12.isSelected())
		{
			return "2";
		}
		else
		{
			return null;
		}
	}
	String getMatch5()
	{
		if(jRadioButton13.isSelected())
		{
			return "1";
		}
		else if(jRadioButton14.isSelected())
		{
			return "3";
		}
		else if(jRadioButton15.isSelected())
		{
			return "2";
		}
		else
		{
			return null;
		}
	}

	/*
	 *  getMatches : calendar의 날짜 정보에 해당하는 경기의 리스트를 파싱해온뒤
	 * 리스트로 반환한다.
	 */
	public static List<String> getMatches(Calendar calendar) {

		Document doc;

		int DAY = calendar.get(Calendar.DATE);
		Set<Integer> set = new HashSet<>();
		List<String> matches = new ArrayList<>();
		//파싱을 위한 소스를 나타내는 conStr
		String conStr = getDocumentStr(calendar);

		try {
			doc = Jsoup.connect(conStr).get();

			Elements empty = doc.select(".cont_empty");
			Elements date = doc.select(".time_date");
			Elements home = doc.select(".txt_home");
			Elements away = doc.select(".txt_away");


			setEmptyDate(empty, set);
			int index = 0;
			int tmp;

			for (Element e : date) {
				tmp = Integer.parseInt(e.text().split("\\(")[0]);
				if (tmp > DAY)
					break;
				if (!(set.contains(tmp))) {
					if (tmp==DAY) {
						matches.add(home.get(index).text());
						matches.add(away.get(index).text());
					}
					index++;
				} else {
					// do nothing
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return matches;//list의 size를 2로 나눈 수가 경기수 경기가 없을경우 size는 0
	}

	/*
	 *  getResults : cal의 날짜정보에 해당하는 경기의 결과를 리스트로 반환한다.
	 *  (리스트의 사이즈는 경기의 수와 같다)
	 */
	public static List<String> getResults(Calendar cal){
		Document doc;

		//int DAY = day_of_month;
		int DAY = cal.get(Calendar.DATE);//DAY정보를 얻는다.
		
		Set<Integer> set = new HashSet<>();
		List<String> results = new ArrayList<>();

		String conStr = getDocumentStr();
		try {
			doc = Jsoup.connect(conStr).get();

			Elements empty = doc.select(".cont_empty");
			Elements date = doc.select(".time_date");
			Elements score = doc.select(".cont_score");

			setEmptyDate(empty, set);
			int index = 0;
			int tmp;

			for (Element e : date) {
				tmp = Integer.parseInt(e.text().split("\\(")[0]);
				if (tmp > DAY)
					break;
				if (!(set.contains(tmp))) {
					if (tmp==DAY) {
						results.add(getWinner(setMatchResults(score.get(index).text())));
					}
					index++;
				} else {
					// do nothing
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return results;//list의 size를 로 나눈 수가 경기수 경기가 없을경우 size는 0
	}

	
	/*
	 * getDocumentStr : calendar의 날짜정보에 해당하는 파싱소스를 만들어서 반환한다.
	 */
	public static String getDocumentStr(Calendar calendar){
		String YEAR = Integer.toString(calendar.get(Calendar.YEAR));
		String MONTH;
		if ((calendar.get(Calendar.MONTH) + 1) < 10)
			MONTH = "0" + Integer.toString(calendar.get(Calendar.MONTH) + 1);
		else
			MONTH = Integer.toString(calendar.get(Calendar.MONTH) + 1);
		return "http://score.sports.media.daum.net/schedule/baseball/kbo/main.daum?game_year="
		+ YEAR + "&game_month=09";//원래라면 + MONTH 여야함
	}
	
	/*
	 * getDocumentStr : 인자가 없는 getDocumentStr은 getResults내부에서 결과확인하는날에 해당하는
	 * 파싱소스를 만들어서 반환한다.
	 */
	public static String getDocumentStr(){
		final Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		String YEAR = Integer.toString(calendar.get(Calendar.YEAR));
		String MONTH;
		if ((calendar.get(Calendar.MONTH) + 1) < 10)
			MONTH = "0" + Integer.toString(calendar.get(Calendar.MONTH) + 1);
		else
			MONTH = Integer.toString(calendar.get(Calendar.MONTH) + 1);
		return "http://score.sports.media.daum.net/schedule/baseball/kbo/main.daum?game_year="
		+ YEAR + "&game_month=09";//원래라면 + MONTH 여야함
	}

	/*
	 * setEmptyDate : 경기가 없는 날에 대한 정보를 저장
	 */
	public static void setEmptyDate(Elements x, Set<Integer> s) {
		for (Element e : x) {
			s.add(Integer.parseInt(e.text().split("\\(")[0]));
		}
	}

	/*
	 * setMatchResults : 경기 결과 점수에 대한 정보를 가져온다
	 */
	public static String setMatchResults(String match) {
		String tmp = match.replaceAll("\\s+", "");
		if (tmp.contains("취소")) {
			tmp = "취소";
			return tmp;
		}
		if (tmp.contains("DH")) {
			tmp = tmp.substring(0, tmp.length() - 5);
			if (tmp.contains("KIA")) {
				tmp = handleKia(tmp);
				return tmp;// 점수정보만 리턴된다.
			}
			tmp = tmp.substring(2, tmp.length() - 2);
			return tmp;

		}
		if (tmp.contains("KIA")) {
			tmp = handleKia(tmp);
			return tmp;
		}
		tmp = tmp.substring(2, tmp.length() - 2);
		return tmp;
	}

	// hadleKia : 팀 '기아'의 경기의 데이터를 처리한다
	public static String handleKia(String match) {
		if (match.charAt(0) == 'K') {
			match = match.substring(3, match.length() - 2);
			return match;
		} else {
			match = match.substring(2, match.length() - 3);
			return match;
		}
	}

	/*
	 * getWinner : winner 설정
	 * (0: 취소 , 1: 왼쪽팀이 이김 , 2: 오른쪽팀이 이김, 3: 비김)
	 */
	public static String getWinner(String resultScore){
		if(resultScore.contains("취소"))
			return "0";

		String[] score = resultScore.split(":");
		int x = Integer.parseInt(score[0]);
		int y = Integer.parseInt(score[1]);
		if(x > y)
			return "1";
		else if(y > x)
			return "2";
		else
			return "3";//draw
	}
	private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {
	
		javax.swing.JComboBox sel_com = (javax.swing.JComboBox)evt.getSource();
		String select = (String)sel_com.getSelectedItem();
		
		//현재날짜 기준으로 selectIndex를 더해준다.
		int selectIndex = sel_com.getSelectedIndex();
		
		if(select.equals("날짜를 선택해주세요")) return;
		Visible_false();
		
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		calendar.add(Calendar.DATE, selectIndex);//날짜 수정
		
		date2 = calendar.get(Calendar.DATE);
		
		List<String> matches = getMatches(calendar);
		System.out.println(matches.size()/2);
				
		for(int countMatchNum = 0;countMatchNum<matches.size()/2;countMatchNum++){

			if(countMatchNum==0){
				jLabel1.setVisible(true);
				jLabel1.setText(matches.get(0)+"          vs          "+matches.get(1));
				getContentPane().add(jLabel1);
				jLabel1.setBounds(56, 152, 200, 23);
				
				jRadioButton1.setVisible(true);
				jRadioButton2.setVisible(true);
				jRadioButton3.setVisible(true);
				getContentPane().add(jRadioButton1);
				jRadioButton1.setBounds(276, 152, 79, 23);
				jRadioButton1.setText(matches.get(0));
				jRadioButton1.setOpaque(false);
				getContentPane().add(jRadioButton2);
				jRadioButton2.setBounds(355, 152, 55, 23);
				jRadioButton2.setText("무");
				jRadioButton2.setOpaque(false);
				getContentPane().add(jRadioButton3);
				jRadioButton3.setBounds(428, 152, 79, 23);
				jRadioButton3.setText(matches.get(1));
				jRadioButton3.setOpaque(false);
			}
			else if(countMatchNum==1){
				jLabel2.setVisible(true);
				jLabel2.setText(matches.get(2)+"          vs          "+matches.get(3));
				getContentPane().add(jLabel2);
				 jLabel2.setBounds(56, 203, 200, 23);
				
				jRadioButton4.setVisible(true);
				jRadioButton5.setVisible(true);
				jRadioButton6.setVisible(true);
				getContentPane().add(jRadioButton4);
				jRadioButton4.setBounds(276, 203, 79, 23);
				jRadioButton4.setText(matches.get(2));
				jRadioButton4.setOpaque(false);
				getContentPane().add(jRadioButton5);
				jRadioButton5.setBounds(355, 203, 55, 23);
				jRadioButton5.setText("무");
				jRadioButton5.setOpaque(false);
				getContentPane().add(jRadioButton6);
				jRadioButton6.setBounds(428, 203, 79, 23);
				jRadioButton6.setText(matches.get(3));
				jRadioButton6.setOpaque(false);
			}
			else if(countMatchNum==2){
				jLabel3.setVisible(true);
				jLabel3.setText(matches.get(4)+"          vs          "+matches.get(5));
				getContentPane().add(jLabel3);
				jLabel3.setBounds(56, 254, 200, 23);
				
				jRadioButton7.setVisible(true);
				jRadioButton8.setVisible(true);
				jRadioButton9.setVisible(true);
				getContentPane().add(jRadioButton7);
				jRadioButton7.setBounds(276, 254, 79, 23);
				jRadioButton7.setText(matches.get(4));
				jRadioButton7.setOpaque(false);
				getContentPane().add(jRadioButton8);
				jRadioButton8.setBounds(355, 254, 55, 23);
				jRadioButton8.setText("무");
				jRadioButton8.setOpaque(false);
				getContentPane().add(jRadioButton9);
				jRadioButton9.setBounds(428, 254, 79, 23);
				jRadioButton9.setText(matches.get(5));
				jRadioButton9.setOpaque(false);
			}
			else if(countMatchNum==3){
				jLabel4.setVisible(true);
				jLabel4.setText(matches.get(6)+"           vs          "+matches.get(7));
				getContentPane().add(jLabel4);
				jLabel4.setBounds(56, 305, 200, 23);
				
				jRadioButton10.setVisible(true);
				jRadioButton11.setVisible(true);
				jRadioButton12.setVisible(true);
				
				getContentPane().add(jRadioButton10);
				jRadioButton10.setBounds(276, 305, 79, 23);
				jRadioButton10.setText(matches.get(6));
				jRadioButton10.setOpaque(false);
				getContentPane().add(jRadioButton11);
				 jRadioButton11.setBounds(355, 305, 55, 23);
				jRadioButton11.setText("무");
				jRadioButton11.setOpaque(false);
				getContentPane().add(jRadioButton12);
				jRadioButton12.setBounds(428, 305, 79, 23);
				jRadioButton12.setText(matches.get(7));
				jRadioButton12.setOpaque(false);
			}	
			else if(countMatchNum==4){
				jLabel5.setVisible(true);
				jLabel5.setText(matches.get(8)+"          vs          "+matches.get(9));
				getContentPane().add(jLabel5);
				 jLabel5.setBounds(56, 356, 200, 23);
				
				jRadioButton13.setVisible(true);
				jRadioButton14.setVisible(true);
				jRadioButton15.setVisible(true);
				
				getContentPane().add(jRadioButton13);
				jRadioButton13.setBounds(276, 356, 79, 23);
				jRadioButton13.setText(matches.get(8));
				jRadioButton13.setOpaque(false);
				getContentPane().add(jRadioButton14);
				jRadioButton14.setBounds(355, 356, 55, 23);
				jRadioButton14.setText("무");
				jRadioButton14.setOpaque(false);
				getContentPane().add(jRadioButton15);
				jRadioButton15.setBounds(428, 356, 79, 23);
				jRadioButton15.setText(matches.get(9));
				jRadioButton15.setOpaque(false);
				//countMatchNum++;
			}
		}
		
		jLabel7.setIcon(new javax.swing.ImageIcon("./image/배경.jpg"));
        getContentPane().add(jLabel7);
        jLabel7.setBounds(0, 0, 540, 560);
	}

	public void Visible_false(){

		jLabel1.setVisible(false);
		jLabel2.setVisible(false);
		jLabel3.setVisible(false);
		jLabel4.setVisible(false);
		jLabel5.setVisible(false);
		jLabel6.setVisible(false);
		//jLabel7.setVisible(false);
		jRadioButton1.setVisible(false);
		jRadioButton2.setVisible(false);
		jRadioButton3.setVisible(false);
		jRadioButton4.setVisible(false);
		jRadioButton5.setVisible(false);
		jRadioButton6.setVisible(false);
		jRadioButton7.setVisible(false);
		jRadioButton8.setVisible(false);
		jRadioButton9.setVisible(false);
		jRadioButton10.setVisible(false);
		jRadioButton11.setVisible(false);
		jRadioButton12.setVisible(false);
		jRadioButton13.setVisible(false);
		jRadioButton14.setVisible(false);
		jRadioButton15.setVisible(false);
		buttonGroup1.clearSelection();
		buttonGroup2.clearSelection();
		buttonGroup3.clearSelection();
		buttonGroup4.clearSelection();
		buttonGroup5.clearSelection();
	}
	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JComboBox jComboBox1;
	private javax.swing.ButtonGroup buttonGroup1;
	private javax.swing.ButtonGroup buttonGroup2;
	private javax.swing.ButtonGroup buttonGroup3;
	private javax.swing.ButtonGroup buttonGroup4;
	private javax.swing.ButtonGroup buttonGroup5;
	private javax.swing.JButton jButton1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JRadioButton jRadioButton1;
	private javax.swing.JRadioButton jRadioButton10;
	private javax.swing.JRadioButton jRadioButton11;
	private javax.swing.JRadioButton jRadioButton12;
	private javax.swing.JRadioButton jRadioButton13;
	private javax.swing.JRadioButton jRadioButton14;
	private javax.swing.JRadioButton jRadioButton15;
	private javax.swing.JRadioButton jRadioButton2;
	private javax.swing.JRadioButton jRadioButton3;
	private javax.swing.JRadioButton jRadioButton4;
	private javax.swing.JRadioButton jRadioButton5;
	private javax.swing.JRadioButton jRadioButton6;
	private javax.swing.JRadioButton jRadioButton7;
	private javax.swing.JRadioButton jRadioButton8;
	private javax.swing.JRadioButton jRadioButton9;
	// End of variables declaration//GEN-END:variables
}