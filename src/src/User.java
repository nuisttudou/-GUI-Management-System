
public class User {
	private String email;
	private String userName;
	private String sex;
	private String hobbies;
	public User(String email,String name,String sex,String hobby) {
		this.email=email;
		this.userName=name;
		this.sex=sex;
		this.hobbies=hobby;
	}
	public String getEmail() {return email;}
	public String getUserName() {return userName;}
	public String getSex() {return sex;}
	public String getHobbies() {return hobbies;}
}
