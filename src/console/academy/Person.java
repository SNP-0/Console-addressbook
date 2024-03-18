package console.academy;

import java.io.Serializable;

public class Person implements Serializable{

	//필드
	public String name;
	public int age;
	public String addr;
	public String pnum;
	//기본생성자
	public Person() {}
	//인자생성자
	public Person(String name, int age,String addr,String pnum) {
		super();
		this.name = name;
		this.age = age;
		this.addr = addr;
		this.pnum = pnum;
	}
	//멤버메소드
	public String get() {
		return String.format("이름:%s,나이:%s,주소:%s,번호:%s", name,age,addr,pnum);
		
	}
	

	
	public void print() {
		System.out.println(get());
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getPnum() {
		return pnum;
	}
	public void setPnum(String pnum) {
		this.pnum = pnum;
	}

	
}////class
