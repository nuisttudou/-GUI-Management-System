import java.util.ArrayList;
import java.util.List;

public class UserDaoImplForList implements UserDao{
	private List<User>users;
	public UserDaoImplForList() {
		users=new ArrayList<User>();
	}
	public void insert(User user) {
		users.add(user);
	}
	public List<User>selectAll(){
		return users;
	}
	public User selectByEmail(String email) {

		//System.out.println(users.size());
		for(int i=0;i<users.size();i++) {
			User user=users.get(i);
			if(user.getEmail().equals(email)) {
				return user;
			}
		}
		return null;
	}

	public void remove(String email) {//确定可删除的
		int i=0;
		for(;i<users.size();i++){
			User user=users.get(i);
			if(user.getEmail().equals(email))break;
		}
		users.remove(i);
	}
}
