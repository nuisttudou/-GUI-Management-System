
import java.util.List;

import javax.swing.table.AbstractTableModel;


public class UserTableModel extends AbstractTableModel{
	private List<User>users;
	public UserTableModel(List users) {this.users=users;
	}
	public int getColumnCount() {return 5;}
	public int getRowCount() {
		return users.size();
	}
	public Object getValueAt(int row,int col) {
		User user=(User)users.get(row);
		switch(col) {
		case 0:return row+1+"";
		case 1:return user.getEmail();
		case 2:return user.getUserName();
		case 3:return user.getSex();
		case 4:return user.getHobbies();
		}
		return null;
	}
	public String getColumnName(int col) {
		switch(col) {
		case 0:return "序号";
		case 1:return "email";
		case 2:return "用户名";
		case 3:return "性别";
		case 4:return "爱好";
		}
		return null;
	}
	
	

}
