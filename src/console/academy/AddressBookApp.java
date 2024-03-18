package console.academy;

import addressbook.AddressBookLogic;

public class AddressBookApp {


	public static void main(String[] args) {
		
		AddressBookLogic logic = new AddressBookLogic();
		
		while(true) {
			//1.메인 메뉴 출력
			logic.printMainMenu();
			//2.메인 메뉴 번호 입력받기
			int mainMenu = logic.getMenuNumber();
			//3. 메인메뉴에 따른 분기
			logic.seperateMainMenu(mainMenu);
		}
		

	}////main

	




	

}////class
