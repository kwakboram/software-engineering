/*
 * MultiChatClient.java
 * 채팅클라이언트에 대한 기능을 제공한다.
 * 메인 화면에서 채팅 버튼을 클릭시 사용된다.
 * 자바의 정석에서 나온 멀티채팅에  GUI를 입힘.
 * GUI제작 : 아둔한사 (http://adunhansa.tistory.com)
 * 
 */
package sports;
import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.rmi.ConnectException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class MultiChatClient {
	//닉네임은 main과 GUI 클래스에서 쓰이며 
	static String nick=null;
	// ta, tf 는 쓰레드와 GUI
	static JTextArea ta;
	static JTextField tf;
	//socket 은 
	//static 
	static DataOutputStream out;
	
	public static void main(String args[]){
		nick = JOptionPane.showInputDialog("당신의 대화명은?");
		FrClient f = new FrClient(nick);
		Socket socket;
		try{
			String serverIp = "165.229.125.46";
			//소켓을 생성하여 연결을 요청
			socket = new Socket(serverIp, 7777);
			System.out.println("서버에 연결되었습니다");
			
			//(궁금) 왜 여기서 뉴 쓰레드안에 새 쓰레드를 넣는지 이해가 안감..
			Thread receiver = new ClientReceiver(socket);
			receiver.start();
			Thread sender = new ClientSender(socket, nick);
			sender.start();
		}catch(ConnectException e){
			e.printStackTrace();
			System.out.println("접속에러");
		}catch(Exception e){
			System.out.println(" 에러는 에러");
		}
		
	}//main
	
	static class ClientSender extends Thread{
		Socket socket;
		
		String name;
		
		ClientSender(Socket socket, String name){
			this.socket = socket;
			try{
				out = new DataOutputStream(socket.getOutputStream());
				this.name = name;
			}catch(Exception e){
				System.out.println("클라이언트 생성자 중 에러");
			}
		}
		
		public void run(){
			Scanner scanner = new Scanner(System.in);
			try{
				//이것은 접속하면서 대화명 날리는 if문이다.
				if(out!=null){
					out.writeUTF(name);
				}
				while(out!=null){
					out.writeUTF("["+name+"]"+scanner.nextLine());	
				}
			}catch(IOException e){
				System.out.println(" 클라이언트 센더 실행중 io 에러");
			}
		}//run
	}//clientSender
	
	static class ClientReceiver extends Thread{
		Socket socket;
		DataInputStream in;
		
		ClientReceiver(Socket socket){
			this.socket = socket;
			
			try{
				in = new DataInputStream(socket.getInputStream());
			}catch(IOException e){ 
				System.out.println("클라이언트 리시버 실행중 입출력에러");
			}
		}
		
		public void run(){
			while(in!=null){
				try{
					String s=in.readUTF();
					System.out.println(s);
					ta.append(s+"\n");
				}catch(IOException e){
					System.out.println("클라이언트 리시버 메소드 실행중 입출력 에러");
				}
			}
		}
		
	}// 클라이언트 리시버
	
	
	//gui 클래스
	@SuppressWarnings("serial")
	static class FrClient extends JFrame implements ActionListener{
		
		
		public FrClient(String nick){
			setSize(600, 400);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setTitle(nick+"의 채팅방"); 
			setLayout(new BorderLayout());
			
			JLabel label = new JLabel("승부예측 채팅방");
			ta= new JTextArea(25, 40);
			tf = new JTextField(25);
			tf.addActionListener(this);
			
			JPanel panel = new JPanel();
			panel.setLayout(new BorderLayout());
			//(궁금) 여기서 왼쪽에 붙여서 정렬할려면.. 어떻게..
			panel.setComponentOrientation(
	                ComponentOrientation.LEFT_TO_RIGHT);
			JLabel label_name = new JLabel(nick+"님 ");
			panel.add(label_name,BorderLayout.WEST);
			panel.add(tf, BorderLayout.CENTER);
			
			
			
			add(label, BorderLayout.NORTH);
			add(ta, BorderLayout.CENTER);
			add(panel, BorderLayout.SOUTH);
			
			setVisible(true);
			
			
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==tf){
				//이거는 그냥 gui 에 나오게 하기 위한 것이다~ 
				//ta.append(nick + " : "+tf.getText()+"\n");
				try {
					out.writeUTF("["+nick+"]"+tf.getText());
					tf.setText("");
				} catch (IOException e1) {
					System.out.println("gui  상에서 메시지 보내는게 에러");
				}
			}
		}
	} 
}//class
