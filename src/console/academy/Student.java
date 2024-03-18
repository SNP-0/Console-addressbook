package console.academy;

public class Student extends Person{
	//멤버변수
	public String stNumber;//새롭게 확장한 멤버
	//인자생성자

	public Student(String name, int age,String addr, String pnum, String stNumber) {
		super(name, age, addr, pnum);
		this.stNumber=stNumber;
	}
	//멤버메소드

	@Override
	public String get() {
		
		return String.format("%s,학번:%s", super.get(),stNumber);
	}

	@Override
	public void print() {
		System.out.println(get());
		
	}
	
	
}
