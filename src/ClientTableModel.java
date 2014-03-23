
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClientTableModel implements TableModel {
	
	private final String[] columns = { "Room Number", "Floor", "Beds", "Category", "Reserved" };

    private ArrayList<RoomInfo> clientsRowSet;

    public ArrayList<RoomInfo> getUsersRowSet() {
        return clientsRowSet;
    }

    public ClientTableModel(ArrayList<RoomInfo> rowSet) {

        clientsRowSet = rowSet;
    }

    public int getColumnCount() {
        return 5;
    }

    public int getRowCount() {
        return clientsRowSet.size();
    }

    public String getColumnName(int column) {
        return columns[column];
    }

    public Class getColumnClass(int column) {
        return String.class;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
    	RoomInfo user = clientsRowSet.get(rowIndex);
    	
    	switch (columnIndex) {
		case 0:
			return user.getRoom_number();
		case 1:
			return user.getRoom_floor();
		case 2:
			return user.getBeds();
		case 3:
			return user.getCategory();
		case 4:
			return user.getReserved();
	
		default:
			return null;
		}
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public void setValueAt(Object value, int row, int column) {
    }

    public void addTableModelListener(TableModelListener l) {
    }

    public void removeTableModelListener(TableModelListener l) {
    }

}
