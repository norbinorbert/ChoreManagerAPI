package edu.bbte.idde.bnim2219.swing;

import edu.bbte.idde.bnim2219.model.Chore;
import edu.bbte.idde.bnim2219.service.ChoreService;
import edu.bbte.idde.bnim2219.service.NotFoundServiceException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;

public class DisplayPanel extends JPanel {
    private final ChoreService choreService = new ChoreService();
    private final JTable toDoList;
    private final DefaultTableModel defaultTableModel;
    private final JFrame frame;
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");

    public DisplayPanel(JFrame frame){
        this.frame = frame;
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
        frame.setEnabled(false);
        NewChoreFrame addChoreFrame = new NewChoreFrame();
        addChoreFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                frame.setEnabled(true);
            }
        });
        addChoreFrame.getOkButton().addActionListener(e -> {
            Chore newChore = addChoreFrame.getNewChore();
            if(newChore != null) {
                Long newChoreId = choreService.create(newChore);
                defaultTableModel.addRow(new String[]{newChoreId.toString(), newChore.getTitle(),
                        newChore.getDescription(), simpleDateFormat.format(newChore.getDeadline()),
                        newChore.getPriorityLevel().toString(), newChore.getDone().toString()});
                addChoreFrame.dispose();
                frame.setEnabled(true);
                frame.requestFocus();
            }
        });
        addChoreFrame.getCancelButton().addActionListener(e -> {
            addChoreFrame.dispose();
            frame.setEnabled(true);
            frame.requestFocus();
        });
        addChoreFrame.setVisible(true);
    }

    public void updateSelected(){
        int rowIndex = toDoList.getSelectedRow();
        if(rowIndex == -1){
            return;
        }
        Long id = Long.parseLong((String) defaultTableModel.getValueAt(rowIndex, 0));
        Chore chore;
        try {
            chore = choreService.findById(id);
        }
        catch (NotFoundServiceException e){
            new ErrorFrame(frame,"Chore doesn't exist or has been deleted");
            defaultTableModel.removeRow(rowIndex);
            return;
        }

        frame.setEnabled(false);
        UpdateChoreFrame updateChoreFrame = new UpdateChoreFrame(chore, simpleDateFormat);
        updateChoreFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                frame.setEnabled(true);
            }
        });
        updateChoreFrame.getOkButton().addActionListener(e -> {
            Chore updatedChore = updateChoreFrame.getUpdatedChore();
            if(updatedChore != null) {
                defaultTableModel.removeRow(rowIndex);
                try {
                    choreService.update(id, updatedChore);
                }
                catch (NotFoundServiceException exception){
                    new ErrorFrame(frame,"Chore doesn't exist or has been deleted");
                    return;
                }
                defaultTableModel.insertRow(rowIndex, new String[]{id.toString(), updatedChore.getTitle(),
                        updatedChore.getDescription(), simpleDateFormat.format(updatedChore.getDeadline()),
                        updatedChore.getPriorityLevel().toString(), updatedChore.getDone().toString()});
                updateChoreFrame.dispose();
                frame.setEnabled(true);
                frame.requestFocus();
            }
        });
        updateChoreFrame.getCancelButton().addActionListener(e -> {
            updateChoreFrame.dispose();
            frame.setEnabled(true);
            frame.requestFocus();
        });
        updateChoreFrame.setVisible(true);
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
            new ErrorFrame(frame,"Chore already deleted");
        }
        finally {
            defaultTableModel.removeRow(rowIndex);
        }
    }
}
