package edu.bbte.idde.bnim2219.swing;

import edu.bbte.idde.bnim2219.service.ChoreService;
import edu.bbte.idde.bnim2219.service.NotFoundServiceException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.Color;
import java.awt.Dimension;

public class DisplayPanel extends JPanel {
    private final ChoreService choreService = new ChoreService();
    private final JTable toDoList;
    private final DefaultTableModel defaultTableModel;

    public DisplayPanel(){
        setBackground(Color.LIGHT_GRAY);

        String[] columnNames = { "ID", "Title", "Description", "Deadline", "Priority Level", "Done?" };
        String[][] chores = {};
        defaultTableModel = new DefaultTableModel(chores, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        toDoList = new JTable(defaultTableModel);
        toDoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        TableColumnModel tableColumnModel = toDoList.getColumnModel();
        tableColumnModel.removeColumn(tableColumnModel.getColumn(0));

        JScrollPane jScrollPane = new JScrollPane(toDoList);
        jScrollPane.setPreferredSize(new Dimension(MainFrame.DEFAULT_WIDTH - 50, MainFrame.DEFAULT_HEIGHT - 110));
        add(jScrollPane);
    }

    public void addNewChore(){
        // TODO/idea: new frame pops up that asks for data regarding the new chore
    }

    public void updateSelected(){
        // TODO/idea: new frame pops up where the user can change the data of the selected chore
    }

    public void deleteSelected(){
        int rowIndex = toDoList.getSelectedRow();
        if(rowIndex == -1){
            return;
        }
        Long id = Long.parseLong((String) defaultTableModel.getValueAt(rowIndex, 0));
        try {
            choreService.delete(id);
        }
        catch (NotFoundServiceException e){
            System.out.println("Chore not found");
        }
        defaultTableModel.removeRow(rowIndex);
    }
}
