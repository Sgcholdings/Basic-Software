package loginapp;

public enum Option {
	Admin,Doctor;
	
	private Option() {}
	
	public String value() {
		return name();
	}
	
	public static Option fromvalue(String v) {
		return valueOf(v);
	}
	

}
