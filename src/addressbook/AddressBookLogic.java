package addressbook;

import java.io.FileInputStream; 
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;
import common.utils.CommonUtils;
import console.academy.Person;
import console.academy.Student;
import console.academy.Teacher;



public class AddressBookLogic {
	Scanner sc = new Scanner(System.in);
	
	Map<Character,List<Person>> addressBook =new HashMap<>();
	List<Person> valueList = null;
	
	public AddressBookLogic() {
		valueList = new Vector<>();
		addressBook = new HashMap<>();

		loadPerson();
	}
	
	
	public void printMainMenu() {
		System.out.println("================주소록 메뉴====================");
		System.out.println(" 1.입력 2.출력 3.수정 4.삭제 5.검색 6.파일저장 9.종료");
		System.out.println("============================================");
		System.out.println("메인 메뉴번호를 입력하세요?");
	}//printMainMenu()
	
	
	public void printMainMenu1() {
		System.out.println("================주소록 메뉴====================");
		System.out.println(" 1.학생/교사 구분 2.학생/교사 구분없이 3.EXIT");
		System.out.println("============================================");
		System.out.println("정렬방법을 입력하세요");
	}
	
	private void printSort() {
		System.out.println("================주소록 메뉴====================");
		System.out.println(" 1.이름  2.나이  3.주소  4.번호  6.종료");
		System.out.println("============================================");
		System.out.println("정렬방법을 입력하세요");
	}
		
	private void insert(String text) {
		System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");	
		System.out.println("	  "+text+"를(을) 입력하세요");
		System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
	}
	
	private void printSubMenu() {
		System.out.println("++++++++++서브 메뉴++++++++++");
		System.out.println("1.학생 2.교사 3.메인메뉴로 이동");
		System.out.println("+++++++++++++++++++++++++++");
		System.out.println("서브 메뉴번호를 입력하세요?");
		
	}
	
	private void upDateList() {
		System.out.println("+++++++++++++수정할 항목+++++++++++++");
		System.out.println("1.나이 2.주소 3.전화번호 4.메인메뉴로 이동");
		System.out.println("++++++++++++++++++++++++++++++++++");
		System.out.println("서브 메뉴번호를 입력하세요?");
	}
	
	
	private void sortMenu(int subMenu, List<Person> List) {
		switch(subMenu){
			case 1:
			case 2:
			case 3:
			case 4:sorting(subMenu, List);break;
			default:System.out.println("메뉴에 없는 번호 입니다");
		
		}
	}
	
	public static void sorting(int menu,List<Person> sortList) {
		Collections.sort(sortList,new Comparator<Person>() {
			@Override
			public int compare(Person src, Person target) {
				switch(menu) {
				case 1:
					return src.getName().compareTo(target.getName());
				case 2:
					return src.getAge()-target.getAge();
				case 3:
					return src.getAddr().compareTo(target.getAddr());
				default:
					return src.getPnum().compareTo(target.getPnum());
				}
			}/////compare
			
		});
	}///////////
	
	private void check(String text) {
		System.out.println(text+"할 사람이 없습니다.");
	}
	
	public int getMenuNumber() {
		String menuStr;
		while(true) {
			try {
				menuStr= sc.nextLine().trim();
				if(!CommonUtils.isNumber(menuStr)) {
					System.out.println("메뉴번호는 숫자만!!");
					continue;
				}
				return Integer.parseInt(menuStr);
			}
			catch (NumberFormatException e) {
				System.out.println("메뉴번호는 숫자만!!");
			}
			
			
		}//while
		
	}//getMenuNumber
	
	public void seperateMainMenu(int mainMenu) {
		switch(mainMenu) {
			case 1://입력 
				while(true) {
					//서브메뉴 출력]
					printSubMenu();
					//서브메뉴번호 입력받기]
					int subMenu=getMenuNumber();
					if(subMenu==3) break;
					switch(subMenu) {
						case 1:
						case 2:setPerson(subMenu);break;
						default:System.out.println("서브메뉴에 없는 번호입니다");
					}//switch
					
				}///while
				break;
			case 2://출력
				if(addressBook.size()==0) {
					check("출력");
					break;
				}
				printMainMenu1();
				int printMenu=getMenuNumber();
				if(printMenu==3)break;
				switch(printMenu) {
					case 1:
					case 2: printPerson(printMenu); break;
					default:System.out.println("서브메뉴에 없는 번호입니다");
				}break;
				
			case 3://수정 /
				if(addressBook.size()==0) {
					check("수정");
					break;
				}
				updatePerson();
				break;
			case 4://삭제 
				if(addressBook.size()==0) {
					check("삭제");
					break;
				}
				deletePerson();
				break;
			case 5://검색 
				if(addressBook.size()==0) {
					check("검색");
					break;
				}
				findPersonByName();
				break;
			case 6://파일저장 
				savePerson();
				break;
			case 9://종료
				System.out.println("프로그램을 종료합니다");
				System.exit(0);
				break;
			default:
				System.out.println("메뉴에 없는 번호입니다");
		}//switch
		
	}//seperateMainMenu(int mainMenu)

	
	
//-----------------------------입력-----------------------------
	
	private void setPerson(int subMenu) {
		Scanner sc = new Scanner(System.in);
		List<Person> valueList=null;
		String name;
		
		while(true) {
			insert("이름");
			name= sc.nextLine().trim();
			if("EXIT".equalsIgnoreCase(name)) break;
			//2]입력한 이름에서 자음 (ㄱ,ㄴ,ㄷ,.....ㅎ)얻기
			char consonant = CommonUtils.getInitialConsonant(name);
			if(consonant=='0') {
				System.out.println("한글명이 아닙니다");
				continue;
			}
			if(!addressBook.containsKey(consonant)) {
				valueList = new Vector<>();
			}
			else {
				valueList=addressBook.get(consonant);
			}
			insert("나이");
			int age = getAges(sc);
			
			insert("주소");
			String addr= sc.nextLine().trim();
			
			insert("번호");
			String pnum= sc.nextLine().trim();
			
			switch(subMenu) {
				case 1:
					insert("학번");
					String stNumber= sc.nextLine().trim();
					valueList.add(new Student(name, age, addr, pnum, stNumber));
					addressBook.put(consonant, valueList);
					break;
				default:
					insert("과목");
					
					String subject= sc.nextLine().trim();
					valueList.add(new Teacher(name, age, addr, pnum, subject));
					addressBook.put(consonant, valueList);
					break;
			}
			name="EXIT";break;
		}
		
	}
//-----------------------------입력-----------------------------


//-----------------------------출력-----------------------------
	private void printPerson(int printMenu) {
		Set<Character> keys= addressBook.keySet();
		List<Person> teacherList =  new Vector<Person>();
		List<Person> studentList =  new Vector<Person>();
		
		
		if(printMenu==1) {
			for(Character key:keys) {
				List<Person> values= addressBook.get(key);
				for(Person value:values) {
					if(value instanceof Student) {
						studentList.add(value);
					}
					else
						teacherList.add(value);
				}///for(Person value:values)	
			}
			
//-----------------------------정렬-----------------------------
			printSort();
			int subMenu=getMenuNumber();
			switch(subMenu) {
				case 1:
				case 2:
				case 3:
				case 4:
				case 5:sortMenu(subMenu,teacherList); sortMenu(subMenu,studentList);
				case 6:System.out.println("메뉴 나감");break;
				default:System.out.println("없는 메뉴 번호입니다.");break;
			}
			
			
			if(teacherList.size()!=0) {
				System.out.println("[교사 목록]");
				for(Person teacher:teacherList) teacher.print();
			}
			if(studentList.size()!=0) {
				System.out.println("[학생 목록]");
				for(Person student:studentList) student.print();
			}

		}
		
		if(printMenu==2) {
			
			printSort();
			int subMenu=getMenuNumber();
			switch(subMenu) {
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:sortMenu(subMenu,teacherList); sortMenu(subMenu,studentList);
			case 6:System.out.println("메뉴 나감");break;
			default:System.out.println("없는 메뉴 번호입니다.");break;
			}
			

			List<Person> values = new Vector<Person>();
			for(Character key:keys) {
				values= addressBook.get(key);
				sorting(subMenu,values);
			}
			
			for(Character key:keys) {
				values= addressBook.get(key);
			System.out.println(String.format("[%c로 시작하는 명단]",key));
			for (Person person : values) {
			    // 각 Person 객체에 접근하여 원하는 작업 수행
			    person.print();
			}
			}
		}
//-----------------------------정렬-----------------------------
	}////printPerson

//-----------------------------출력-----------------------------------

//-----------------------------검색메소드(수정/검색/삭제용 타이틀)-----------------------------
	
	private Person searchPerson(String title) {
		System.out.println(title+"할 사람의 이름을 입력하세요?");
		String name = sc.nextLine().trim();
		Set<Character> keys= addressBook.keySet();
		for(Character key:keys) {
			List<Person> values= addressBook.get(key);
			for(Person p:values)			
					if(p.name.equals(name))
						return p;
		}
		
		System.out.println(name+"로(으로) 검색된 정보가 없어요");
		return null;
	}
//-----------------------------검색메소드(수정/검색/삭제용 타이틀)-----------------------------
		
//-----------------------------수정-----------------------------
	
	private void updatePerson() {
		Person findPerson=searchPerson("수정");
		if(findPerson !=null) {
			while(true) {
				upDateList();								
				int updateNum=getMenuNumber();
				if(updateNum==4) break;
				//나이 수정
				if(updateNum==1) {
					System.out.printf("(현재 %s살)몇 살로 수정할래?%n",findPerson.age);
					findPerson.age = getAges(sc);}
				else if(updateNum==2) {
					System.out.printf("(현재 주소 %s)어디로 수정할래?%n",findPerson.addr);
					findPerson.addr = sc.nextLine().trim(); }
				else if(updateNum==3) {
					System.out.printf("(현재 번호 %s)전화번호 수정할래?%n",findPerson.pnum);
					findPerson.pnum = sc.nextLine().trim(); }
				System.out.println("[아래와 같이 수정되었습니다]");
				savePerson();
				findPerson.print();//수정 내용을 확인하기 위한 출력				
				break;
			}
		}
		
	}
	
	
//-----------------------------수정-----------------------------


//-----------------------------삭제-----------------------------
	
	
	private void deletePerson() {
		try {
			Person findPerson=searchPerson("삭제");
			
			 char consonant = CommonUtils.getInitialConsonant(findPerson.name);
	
			List<Person> values= addressBook.get(consonant);
				for(Person p:values) {
					if(findPerson.equals(p)) {
						values.remove(findPerson);
						System.out.println(String.format("[%s가 삭제되었습니다]", findPerson.name));
						savePerson();
						break;
					}
				}
				if(addressBook.get(consonant).size()==0) {
					addressBook.remove(consonant);
				}
		}
		catch (NullPointerException e) {
			System.out.println("검색한 사람이 존재하지않습니다.");
		}
		
	}
	
	
//-----------------------------삭제-----------------------------
	
//-----------------------------검색-----------------------------
	
	private void findPersonByName() {
		Person findPerson=searchPerson("검색");
		if(findPerson !=null) {
			System.out.println(String.format("[%s로 검색한 결과]", findPerson.name));
			findPerson.print();
		}
	}//findPersonByName()

//-----------------------------검색-----------------------------
	
//-----------------------------try/catch-----------------------------
	private int getAges(Scanner sc) {
		int years;
		while(true) {
			try {
				years=Integer.parseInt(sc.nextLine().trim());
				if(years<=0 || years>=150) {
					System.out.println("나이가 부적절 합니다.");
					continue;
				}
				break;
			}
			catch(NumberFormatException e) {
				System.out.println("나이는 숫자만!!");
			}
		}
		return years;
	}
	
	
//-----------------------------try/catch-----------------------------
	

//-----------------------------파일저장-----------------------------
	
	
	private void savePerson() {
		if(addressBook.isEmpty()) {
			System.out.println("[파일로 저장할 명단이 없어요]");
			return;
		}
		
		ObjectOutputStream out=null;
		try {
			out= new ObjectOutputStream(
				new FileOutputStream("/Persons.dat"));
		out.writeObject(addressBook);
		System.out.println("[파일이 저장되었습니다]");
		}
		catch(IOException e) {
			System.out.println("파일 저장시 오류 입니다:"+e.getMessage());
		}
		finally {
			try {
				if(out !=null) out.close();
			}
			catch(Exception e) {}
		}
	}

	
//-----------------------------파일저장-----------------------------
//-----------------------------파일로드-----------------------------
	private void loadPerson() {
		ObjectInputStream ois=null;
		try {
			ois = 
					new ObjectInputStream(
								new FileInputStream("/Persons.dat"));
			
			addressBook=(Map<Character,List<Person>>)ois.readObject();
		}
		catch(Exception e) {}
		finally {
			try {
				if(ois !=null) ois.close();
			}
			catch(Exception e) {}
		}
	}
//-----------------------------파일로드-----------------------------
	

	

}/////class

/////////////////////////////정리 다함///