import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
//注册用户

public class RegisterDialog extends JDialog{
	private JLabel labelEmail=new JLabel("email");
	private JTextField userEmail=new JTextField(20);
	private JLabel labelName=new JLabel("用户名");
	private JTextField userName=new JTextField(20);
	private JLabel labelSex=new JLabel("性别:  ");
	private JLabel labelMale=new JLabel("男");
	private JLabel labelFemale=new JLabel("女");
	private JRadioButton male=new JRadioButton();
	private JRadioButton female=new JRadioButton();
	private JLabel labelHobby=new JLabel("爱好:  ");
	private String[] strHobbies= {"体育运动","上网","看书","打游戏"};
	private JCheckBox hobbies[]=new JCheckBox[4];
	private JLabel labelHobbies[]=new JLabel[4];
	private JButton buttonSave=new JButton("保存");
	//private JButton buttonModify =new JButton("修改");
	private JButton buttonExit=new JButton("退出");
	private int windowHeight=280;
	private int windowWidth=400;
	
	private UserDao userDao;
	public RegisterDialog(JFrame parent,String msg,UserDao userDao) {
		super(parent,msg,true);
		this.userDao=userDao;
	}
	
	
	private void init() {
		JPanel pEmail=new JPanel();
		pEmail.add(labelEmail);pEmail.add(userEmail);
		JPanel pName=new JPanel();
		pName.add(labelName);pName.add(userName);
		JPanel pSex=new JPanel();
		ButtonGroup sexRadioGroup=new ButtonGroup();
		sexRadioGroup.add(male);sexRadioGroup.add(female);
		pSex.add(labelSex);pSex.add(labelMale);pSex.add(male);pSex.add(labelFemale);pSex.add(female);
		male.setSelected(true);
		
		JPanel pHobby=new JPanel();
		pHobby.add(labelHobby);
		for(int i=0;i<hobbies.length;i++) {
			hobbies[i]=new JCheckBox();
			labelHobbies[i]=new JLabel();
			labelHobbies[i].setText(strHobbies[i]);
			pHobby.add(hobbies[i]);
			pHobby.add(labelHobbies[i]);
		}
		JPanel pButton=new JPanel();
		pButton.add(buttonSave);pButton.add(buttonExit);//pButton.add(buttonModify);
		JPanel panel=new JPanel();
		panel.add(pEmail);
		panel.add(pName);
		panel.add(pSex);
		panel.add(pHobby);
		panel.add(pButton);
		this.add(panel);
		
		//buttonExit.addActionListener(new );
		buttonSave.addActionListener(new SaveHandler());
		buttonExit.addActionListener(new ExitHandler());
	}

	public void showMe(JFrame parent) {
		this.init();
		setPosition(parent);
		this.validate();
		setVisible(true);
	}
	private void setPosition(JFrame parent) {
		int parentX=parent.getX();
		int parentY=parent.getY();
		int parentWidth=parent.getWidth();
		int parentHeight=parent.getHeight();
		int dialogX=parentX+(parentWidth-windowWidth)/2;
		int dialogY=parentY+(parentHeight-windowHeight)/2;
		this.setBounds(dialogX,dialogY,windowWidth,windowHeight);
	}
	
	private String getHobbiesInfo() {
		StringBuilder strHobbies=new StringBuilder();
		for(int i=0;i<hobbies.length;i++) {
			if(hobbies[i].isSelected()) {
				String hobbyText=labelHobbies[i].getText();
				strHobbies.append("  "+hobbyText+"  ");
			}
		}
		//System.out.println(strHobbies.toString()+"\n");
		return strHobbies.toString();
	}
	
	/*
	//p234
	private UserDao userDao;
	//...
	public RegisterDialog(JFrame parent,String msg,UserDao userDao) {
		super(parent,msg,true);
		this.userDao=userDao;
		//...
	}
	//...
	*/
	private class ExitHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}
	
	
	private class SaveHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			String email=userEmail.getText();
			String name=userName.getText();
			String sex=male.isSelected()?"男":"女";
			String hobby=getHobbiesInfo();
			if(email.length()==0) {
				JOptionPane.showMessageDialog(null,"请输入email", "提示", JOptionPane.PLAIN_MESSAGE);
				userEmail.grabFocus();
			}else if(!email.matches("[a-zA-Z0-9_+\\.-]+@([a-zA-Z0-9-]+\\.)+[a-zA-Z0-9]{2,4}")) {
				JOptionPane.showMessageDialog(null, "email格式有误", "提示", JOptionPane.PLAIN_MESSAGE);
				userEmail.setText("");
				userEmail.grabFocus();
			}else if(name.length()==0) {
				JOptionPane.showMessageDialog(null, "请输入用户名", "提示", JOptionPane.PLAIN_MESSAGE);
				userName.grabFocus();
			}else {
				if(userDao.selectByEmail(email)!=null) {
					JOptionPane.showMessageDialog(null, "该邮箱已存在", "提示", JOptionPane.PLAIN_MESSAGE);
					userEmail.setText("");
					userEmail.grabFocus();
				}else {
					User user=new User(email,name,sex,hobby);
					userDao.insert(user);
					clear();
				}
			}
		}
		private void clear() {
			userEmail.setText("");
			userName.setText("");
			male.setSelected(true);
			for(int i=0;i<hobbies.length;i++)
			{
				hobbies[i].setSelected(false);
			}
		}

	}
		
}
