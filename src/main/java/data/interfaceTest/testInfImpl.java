package data.interfaceTest;

public class testInfImpl implements testInf {

	@Override
	public testInf test() {
		System.out.println(this);
		return this;
	}

	@Override
	public String test2() {
		return "1332";
	}
}
