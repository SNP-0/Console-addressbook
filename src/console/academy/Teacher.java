package console.academy;

public class Teacher extends Person{
	//멤버변수
	String subject;//새롭게 확장한 멤버
	//인자생성자

	public Teacher(String name, int age, String addr, String pnum, String subject) {
		super(name, age, addr, pnum);
		this.subject=subject;
	}
	//멤버메소드

	@Override
	public String get() {
		
		return String.format("%s,과목:%s", super.get(),subject);
	}

	@Override
	public void print() {
		System.out.println(get());
		
	}
	
	
}
