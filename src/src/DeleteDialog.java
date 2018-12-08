import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteDialog extends JDialog {
    private JLabel emailLabel;
    private JTextField userEmail;
    private JButton deteleButton;
    private int windowHeight=280;
    private int windowWidth=400;
    private UserDao userDao;
    public DeleteDialog(JFrame parent, String msg, UserDao userDao){
        super(parent,msg,true);
        this.userDao=userDao;
    }
    private void init(){

        JPanel panel=new JPanel();
        emailLabel=new JLabel("用户email");
        panel.add(emailLabel);
        userEmail=new JTextField(20);
        panel.add(userEmail);
        deteleButton=new JButton("删除");
        panel.add(deteleButton);
        this.add(panel);

        deteleButton.addActionListener(new deteleHandler());
    }
    public void showMe(JFrame parent){
        this.init();
        setPosition(parent);
        this.validate();//?
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
    private class deteleHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = userEmail.getText();
            if (email.length() == 0) {
                JOptionPane.showMessageDialog(null, "请输入email", "提示", JOptionPane.PLAIN_MESSAGE);
                userEmail.grabFocus();
            } else if (!email.matches("[a-zA-Z0-9_+\\.-]+@([a-zA-Z0-9-]+\\.)+[a-zA-Z0-9]{2,4}")) {
                JOptionPane.showMessageDialog(null, "email格式有误", "提示", JOptionPane.PLAIN_MESSAGE);
                userEmail.setText("");
                userEmail.grabFocus();
            } else {
                if (userDao.selectByEmail(email) != null) {
                    userDao.remove(email);
                    JOptionPane.showMessageDialog(null, "删除成功", "提示", JOptionPane.PLAIN_MESSAGE);

                } else {
                    JOptionPane.showMessageDialog(null, "该邮箱不存在", "提示", JOptionPane.PLAIN_MESSAGE);
                    userEmail.grabFocus();
                }
            }
        }
    }

}
