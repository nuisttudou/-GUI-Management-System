import javax.swing.*;
import java.util.List;

public class ShowDataTableDialog extends JDialog {
    private int windowHeight=300;
    private int windowWidth=450;
    private JTable table;
    public ShowDataTableDialog(JFrame parent, String msg, List<User> users) {

        super(parent,"用户列表",true);

        table=new JTable();

        UserTableModel model=new UserTableModel(users);

        table.setModel(model);

    }

    public void showMe(JFrame parent) {
        table.getColumn("序号").setPreferredWidth(30);
        table.getColumn("email").setPreferredWidth(100);
        table.getColumn("用户名").setPreferredWidth(60);
        table.getColumn("性别").setPreferredWidth(60);
        table.getColumn("爱好").setPreferredWidth(200);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        JScrollPane pane=new JScrollPane(table);
        this.add(pane);
        setPosition(parent);
        setVisible(true);
        validate();
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    }

    public void setPosition(JFrame parent) {
        int parentX=parent.getX();
        int parentY=parent.getY();
        int parentWidth=parent.getWidth();
        int parentHeight=parent.getHeight();
        int dialogX=parentX+(parentWidth-windowWidth)/2;
        int dialogY=parentY+(parentHeight-windowHeight)/2;
        this.setBounds(dialogX,dialogY,windowWidth,windowHeight);
    }


}