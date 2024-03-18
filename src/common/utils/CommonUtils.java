package common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class CommonUtils {
	
	public static boolean isNumber(String value) {
		//문자열이 숫자형식이면 true 아니면 false
		for(int i=0; i < value.length();i++) {
			int codeValue = Character.codePointAt(value, i);
			if (!(codeValue >= '0' && codeValue<='9'))
				return false;
		}
		return true;
	}///////////////
	
	//두 날짜 차이를 반환하는 메소드]
	//반환타입:long
	//매개변수:String타입의 두 날짜,날짜패턴,구분자(단위)
	public static long getDiffBetweenDates( 
		String stFDate,
		String stSDate,
		String pattern,
		char delm) throws ParseException{
			//1]매개변수에 전달된 pattern으로 SimpleDateFormat객체생성
			SimpleDateFormat dateFormat= new SimpleDateFormat(pattern);
			//1]String -> Date : parse()
			Date fDate=dateFormat.parse(stFDate);
			Date sDate=dateFormat.parse(stSDate);
			//3] tlrksck rngkrl: getime()
			long fTime = fDate.getTime();
			long sTime = sDate.getTime();
			long diff = Math.abs(fTime-sTime);
			//4]매개변수 delim에 따른 날짜 차이 반환
			switch(Character.toUpperCase(delm)) {
			case 'D':return diff/1000/60/60/24;
			case 'H':return diff/1000/60/60;
			case 'M':return diff/1000/60;
			default :return diff/1000;
			}
	}//////delm
		
	
	
	//[문자열을 int[]배열로 변환]
	public static int[] toIntArray(String value) {
		int[] intArray = new int[value.length()];
		for(int i=0; i<value.length(); i++){
				intArray[i]=(int)value.charAt(i);
		}
		return  intArray;
	}////////////
	/*
		한글 음절은 기본적으로 초성, 중성, 종성으로 구성
	    초성은 한글 음절의 첫 번째 자음
	    한글 초성은 총 19개:ㄱㄲㄴㄷㄸㄹㅁㅂㅃㅅㅆㅇㅈㅉㅊㅋㅌㅍㅎ
	    한글 중성은 총 21개:ㅏㅐㅑㅒㅓㅔㅕㅖㅗㅘㅙㅚㅛㅜㅝㅞㅟㅠㅡㅢㅣ
	    종성은 총 27개이나 종성 28개(27개의 종성에 종성이 없을 때를 더해 28개)
	    :''ㄱㄲㄳㄴㄵㄶㄷㄹㄺㄻㄼㄽㄾㄿㅀㅁㅂㅄㅅㅆㅇㅈㅊㅋㅌㅍㅎ
	    한글은 다음과 같은 규칙으로 유니코드값이 생성된다
	    ( 초성인덱스 * 21 + 중성인덱스)*28+종성인덱스 +0xAC00
	    초성 인덱스 추출:(문자유니코드-0xAC00)/28/21
	    중성 인덱스 추출:(문자유니코드-0xAC00)/28%21
	    종성 인덱스 추출:(문자유니코드-0xAC00)%28
		
	*/
	
	//	주어진 문자의 초성을 추출하는 함수
	public static char getInitialConsonant(String value) {
		if(!Pattern.matches("^[가-힣]{2,}$", value.trim())) return '0';
		char lastName= value.charAt(0);
		
		int index =  (lastName-'가')/28/21;//초성의 인덱스 얻기
		char[] initailConsonants= {'ㄱ','ㄲ','ㄴ','ㄷ','ㄸ','ㄹ','ㅁ','ㅂ','ㅃ','ㅅ','ㅆ','ㅇ','ㅈ','ㅉ','ㅊ','ㅋ','ㅌ','ㅍ','ㅎ'};
		
		return initailConsonants[index];
	}
	
	
	
	
	
	
	
}////////////CommonUtils
