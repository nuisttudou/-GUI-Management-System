
import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
public class SimpleTableTest extends JFrame{

    private static final long serialVersionUID = -4172876583187222326L;
    protected JTable table;
    public SimpleTableTest(){
        Container pane = getContentPane();
        pane.setLayout(new BorderLayout());

        TableValues tv =  new TableValues();//
        table= new JTable(tv);

        //设置行高
        table.setRowHeight(30);
        //必须把table放入JScrollPane才会有列名出现
        JScrollPane jsp = new JScrollPane(table);
        pane.add(jsp,BorderLayout.CENTER);
    }
    public static void main(String[] args) {
        SimpleTableTest stt = new SimpleTableTest();
        stt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        stt.setSize(400,200);
        stt.setVisible(true);
    }}
