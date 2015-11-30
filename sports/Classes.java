/*9월의 오늘날짜에 해당하는 경기를 추출하는 예제 */

package sports;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Classes {
	public static void main(String[] args){
		//checkDate 및 setEmptyDate혼합 테스트
		//time_date 와 txt.home txt.away를 처리(출력)하는 예제를 만들어 보자.
		Document doc;
		
		//conStr 만들려면 어쨌든 calender는 써야하니 객체를 만들어두자.<< 그리고 오늘날짜보다 큰 파싱결과는
		//순회하지 않게 하기위하여 day정보를 사용
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		int day = calendar.get(Calendar.DATE);
		String conStr="http://score.sports.media.daum.net/schedule/baseball/kbo/main.daum?game_year=2015&game_month=09";
		Set<Integer> set = new HashSet<Integer>();
		try {
			doc=Jsoup.connect(conStr).get();
			
			
			Elements empty = doc.select(".cont_empty");
			Elements date = doc.select(".time_date");
			Elements home = doc.select(".txt_home");
			Elements away = doc.select(".txt_away");
			Elements score = doc.select(".cont_score");
			
			//경기가 없는 날을 설정한다.
			setEmptyDate(empty,set);
			int index=0;
			int tmp;
			//countMatchNum은 오늘의 경기 수
			int countMatchNum=0;
			for(Element e : date){//date 의 숫자(경기안하는날도 포함) 만큼 루핑을 하기 위해서(ex: 124회)
				tmp = Integer.parseInt(e.text().split("\\(")[0]);//날짜의 숫자 추출
				//쓸모 없는 정보의 생략을 위해서.
				if(tmp>day)
					break;
				if(!(set.contains(tmp))){//빈 날이 아니면~~
					if(checkDate(e.text())){
						countMatchNum++;
						//GUI에는 아래의 팀1과 팀2정보를 넣으면 되며 
						//매일 벌어지는 경기는 가변적이므로 위의 countMatchNum을 사용해서 경기 수를 파악한다.
						System.out.print(home.get(index).text());//팀 1
						System.out.print(" vs ");
						System.out.print(away.get(index).text());//팀2
						//밑에는 점수정보 파싱 (아래 파싱된 점수는 split(":")로 간단히 팀1이 이겼는지 졌는지 비겼는지 파악가능)
						System.out.println(" "+setMatchResults(score.get(index).text()));
					}
					index++;
				}
				else{
					//do nothing
				}
			}

			System.out.println("오늘의 경기 수는 "+countMatchNum +"경기 입니당");
			//이제 오늘 날짜 찾고 출력하고 바로 break함
			//따라서 122가 아님
			//System.out.println("총 갯수122여야 한다. : " + index);//제대로 수행 되었는지 를 확인하기위해..
			
			//System.out.println(score.size());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static boolean checkDate(String s){//version 1.2 : time_date를 사용하는 케이스
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		int day = calendar.get(Calendar.DATE);
		
		//String[] s1 = s.split(" ");
		//s1 = s1[0].split("\\(");
		String[] s1=s.split("\\(");
		int date = Integer.parseInt(s1[0]);
		//System.out.print("날짜 : "+date+" ");
		return (date == day);
	}
	
	public static void setEmptyDate(Elements x,Set<Integer> s){//empty날짜 를 Set으로 만드는 모듈
		//Set<Integer> seti = new HashSet<Integer>();
		int i=0;
		for(Element e : x){
			s.add(Integer.parseInt(e.text().split("\\(")[0]));
		}
//		for(Integer xx:s){
//			System.out.println(xx);
//		}
	}
	
	//아래의 기능 두가지는 정확한 점수 결과를 뽑기위한 삽질
	public static String setMatchResults(String match){
		//1.먼저 공백제거
		String tmp = match.replaceAll("\\s+","");
		if(tmp.contains("취소")){
			tmp = "취소";
			return tmp;
		}//취소 인거 거르고..
		if(tmp.contains("DH")){
			//뒤에 5개 잘라내야한다.
			tmp = tmp.substring(0,tmp.length()-5);//뒤에 (DH1)이거 잘림
			if(tmp.contains("KIA")){
				//HANDLEKIA
				tmp = handleKia(tmp);
				return tmp;//점수정보만 리턴된다.
			}
			tmp = tmp.substring(2,tmp.length()-2);	
			//바깥의 match가 이걸로 바뀌면 이렇게하고 아니면 반환하는 식으로 변경한다.
			//match = tmp;
			return tmp;
			
		}
		//취소도 아니고 DH도 아닌 일반 경우(kia만 처리)
		if(tmp.contains("KIA")){
			//HANDLEKIA
			tmp = handleKia(tmp);
			return tmp;
		}
		
		tmp = tmp.substring(2,tmp.length()-2);
		return tmp;
	}
	public static String handleKia(String match){//contains(KIA)가 트루인경우ㅋㅋ
		if(match.charAt(0)=='K'){
			match = match.substring(3,match.length()-2);
			return match;
		}
		else{
			match = match.substring(2,match.length()-3);
			return match;
		}
	}
}
