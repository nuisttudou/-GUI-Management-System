import javax.swing.table.AbstractTableModel;
/**
 *     注意：一般使用AbstractTableModel创建TableModel的实现，只有少量数据时使用DefaultTableModel，
 */
public class TableValues extends AbstractTableModel{
    private static final long serialVersionUID = -8430352919270533604L;
    public final static int NAME = 0;
    public final static int GENDER = 1;
    public final static String[] columnNames = {"姓名", "性别"};
    public Object[][] values = {
            {"Cannel_2020",true},
            {"Lucy",false},
            {"韩梅",false},
            {"李雷",true},
            {"Jim",true}
    };
    public int getColumnCount() {
        return  values[0].length;
    }
    public int getRowCount() {
        return values.length;
    }
    public Object getValueAt(int rowIndex, int columnIndex) {
        return values[rowIndex][columnIndex];
    }

    /**
     * 获取列名
     */
    public String getColumnName(int column){
        return columnNames[column];
    }
} 

