import java.util.List;

public interface UserDao {
	public void insert(User user);
	public User selectByEmail(String email) ;
	//...
	public List<User> selectAll();

	public void remove(String email);
}
