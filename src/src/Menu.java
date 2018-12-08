import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.*;

public class Menu extends JFrame{//？？？

	private UserDao userDao;
	
	public Menu() {
		super("用户管理系统");
		this.setBounds(100,100,400,300);

		userDao=new UserDaoImplForList();
		createMenuBar();
		createToolBar();

		ButtonGroup sexRadioGroup=new ButtonGroup();
		JRadioButton male=new JRadioButton();
		JRadioButton female=new JRadioButton();
		sexRadioGroup.add(male);
		sexRadioGroup.add(female);
		male.setSelected(true);

		btnSearch.addActionListener(new SearchAllHandler());
		//...
		
		
	}


	//...
	/*
	 * private class RegisteHandler implements ActionListener {
		public void actionPerformed(ActionEvent events) {
			new RegisterDialog(Menu.this, "注册用户", userDao);
		}
	}*/
	private class RegisteHandler implements ActionListener{
		public void actionPerformed(ActionEvent events) {
			new RegisterDialog(Menu.this,"注册用户",userDao).showMe(Menu.this);
		}
	}

	private class RemoveHandler implements ActionListener{
		public void actionPerformed(ActionEvent events) { new DeleteDialog(Menu.this,"删除用户信息",userDao).showMe(Menu.this);
		}
	}
	private class EditHandler implements ActionListener{
		public void actionPerformed(ActionEvent events) { new EditDialog(Menu.this,"修改用户信息",userDao).showMe(Menu.this);
		}
	}
	private class SearchHandler implements ActionListener{
		public void actionPerformed(ActionEvent events) { new SearchDialog(Menu.this,"按email查询",userDao).showMe(Menu.this);
		}
	}
	
	private JMenuItem jmiModify;
	private JMenuItem jmiRemove;
	private JMenuItem jmiRegiste;
	private JMenuBar menuBar;
	private JMenu registeMenu;
	private JMenuItem jmiSearch;
	private JMenuItem jmiSearchOne;
	private void createMenuBar(){
		jmiRegiste=new JMenuItem("注册用户");
		
		menuBar=new JMenuBar();
		registeMenu=new JMenu("注册（N）");
		registeMenu.setMnemonic(KeyEvent.VK_N);
		registeMenu.add(jmiRegiste=new JMenuItem("注册用户"));
		jmiRegiste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
		menuBar.add(registeMenu);
		JMenu editmenu=new JMenu("编辑用户信息（E）");

		editmenu.setMnemonic(KeyEvent.VK_E);
		menuBar.add(editmenu);
		editmenu.add(jmiModify=new JMenuItem("修改用户信息（M）",'M'));
		editmenu.add(jmiRemove=new JMenuItem("删除用户信息（R）",'R'));
		//...


		JMenu searchmenu=new JMenu("查询用户信息（S）");
		searchmenu.setMnemonic(KeyEvent.VK_S);
		menuBar.add(searchmenu);

		searchmenu.add(jmiSearch=new JMenuItem("浏览用户信息"));
		jmiSearch.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));

		searchmenu.add(jmiSearchOne=new JMenuItem("查询用户信息"));
		jmiSearchOne.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,ActionEvent.CTRL_MASK));
		this.setJMenuBar(menuBar);
	}
	
	JButton btnEdit;
	JButton btnRegiste;
	JButton btnSearch;
	JButton btnRemove;
	JButton btnSearchOne;

	private void createToolBar(){
		JToolBar toolBar=new JToolBar();
		//btnRegiste=new JButton("注册");
		btnRegiste=new JButton("",new ImageIcon(this.getClass().getResource("./ico/add.gif")));//ico/add.gif
		btnRegiste.setToolTipText("注册用户");
		btnEdit=new JButton("",new ImageIcon(this.getClass().getResource("./ico/edit.gif")));//btnEdit=new JButton("修改");//btnEdit=new JButton("",new ImageIcon(this.getClass().getResource("../ico/modify.gif")));

		btnEdit.setToolTipText("修改用户信息");
		btnRemove=new JButton("",new ImageIcon(this.getClass().getResource("./ico/delete.gif")));//btnRemove=new JButton("删除");//btnRemove=new JButton("",new ImageIcon(this.getClass().getResource("../ico/remove.gif")));
		btnRemove.setToolTipText("删除用户信息");
		btnSearch=new JButton("",new ImageIcon(this.getClass().getResource("./ico/search.gif")));//btnSearch=new JButton("浏览");//btnSearch=new JButton("",new ImageIcon(this.getClass().getResource("../ico/search.gif")));
		btnSearch.setToolTipText("浏览用户信息");
		btnSearchOne=new JButton("",new ImageIcon(this.getClass().getResource("./ico/read.gif")));//btnSearchOne=new JButton("查询用户信息");//
		btnSearchOne.setToolTipText("查询单条用户信息");

		toolBar.add(btnRegiste);
		toolBar.add(btnEdit);
		toolBar.add(btnRemove);
		toolBar.add(btnSearch);
		toolBar.add(btnSearchOne);
		this.add("North",toolBar);


		jmiRegiste.addActionListener(new RegisteHandler());
		btnRegiste.addActionListener(new RegisteHandler());
		jmiRemove.addActionListener(new RemoveHandler());
		btnRemove.addActionListener(new RemoveHandler());
		jmiModify.addActionListener(new EditHandler());
		btnEdit.addActionListener(new EditHandler());
		jmiSearch.addActionListener(new SearchAllHandler());
		jmiSearchOne.addActionListener(new SearchHandler());

		btnSearchOne.addActionListener(new SearchHandler());
	}
	private class SearchAllHandler implements ActionListener{
		//@Override
		public void actionPerformed(ActionEvent arg0) {
			List<User>users=userDao.selectAll();
			if(users.size()!=0) {
				new ShowDataTableDialog(Menu.this,"查询结果", users).showMe(Menu.this);
			}else {
				JOptionPane.showMessageDialog(null, "没有用户的信息", "提示", JOptionPane.PLAIN_MESSAGE);
			}
			
		}
	}
}
