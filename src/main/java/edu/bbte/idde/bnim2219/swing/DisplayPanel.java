package edu.bbte.idde.bnim2219.swing;

import edu.bbte.idde.bnim2219.model.Chore;
import edu.bbte.idde.bnim2219.service.ChoreService;
import edu.bbte.idde.bnim2219.service.exceptions.ServiceNotAvailableException;

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
import java.util.Collection;
import java.util.Locale;

// panel that displays all the chores in a table
public class DisplayPanel extends JPanel {
    private transient ChoreService choreService = new ChoreService();
    private final transient JTable toDoList;
    private final transient DefaultTableModel tableModel;
    private final transient JFrame frame;
    private transient SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.US);

    public DisplayPanel(JFrame frame) {
        super();
        this.frame = frame;
        setBackground(Color.LIGHT_GRAY);

        // data is stored in a DefaultTableModel, which is not editable by hand
        String[] columnNames = { "ID", "Title", "Description", "Deadline", "Priority Level", "Done?" };
        String[][] chores = {};
        tableModel = new CustomTableModel(chores, columnNames);
        try {
            Collection<Chore> choresList = choreService.findAll();
            choresList.forEach(chore -> tableModel.addRow(new String[]{String.valueOf(chore.getId()),
                    chore.getTitle(), chore.getDescription(), simpleDateFormat.format(chore.getDeadline()),
                    String.valueOf(chore.getPriorityLevel()), String.valueOf(chore.getDone())}));
        } catch (ServiceNotAvailableException e) {
            new ErrorFrame(this.frame, "Couldn't load chores. Please refresh");
        }

        toDoList = new JTable(tableModel);
        toDoList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        TableColumnModel tableColumnModel = toDoList.getColumnModel();
        tableColumnModel.removeColumn(tableColumnModel.getColumn(0));

        JScrollPane scrollPane = new JScrollPane(toDoList);
        scrollPane.setPreferredSize(new Dimension(MainFrame.DEFAULT_WIDTH - 50, MainFrame.DEFAULT_HEIGHT - 110));
        add(scrollPane);
    }

    private static class CustomTableModel extends DefaultTableModel {
        public CustomTableModel(String[][] data, String... columnNames) {
            super(data, columnNames);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }

    // adds a new chore to the to-do list
    public void addNewChore() {
        // disables the main frame while getting data for the new chore
        frame.setEnabled(false);
        NewChoreFrame addChoreFrame = new NewChoreFrame();

        // re-enable main frame when new one closes
        addChoreFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                frame.setEnabled(true);
            }
        });

        // when user presses the Ok button, add the chore to the list and re-enable main frame
        addChoreFrame.getOkButton().addActionListener(e -> {
            Chore newChore = addChoreFrame.getNewChore();
            if (newChore != null) {
                Long newChoreId;
                try {
                    newChoreId = choreService.create(newChore);
                    tableModel.addRow(new String[]{newChoreId.toString(), newChore.getTitle(),
                            newChore.getDescription(), simpleDateFormat.format(newChore.getDeadline()),
                            newChore.getPriorityLevel().toString(), newChore.getDone().toString()});
                    frame.setEnabled(true);
                } catch (ServiceNotAvailableException ex) {
                    new ErrorFrame(this.frame, "Couldn't create new chore");
                } finally {
                    addChoreFrame.dispose();
                }
            }
        });

        // re-enable main frame when user cancels the operation
        addChoreFrame.getCancelButton().addActionListener(e -> {
            addChoreFrame.dispose();
            frame.setEnabled(true);
            frame.requestFocus();
        });

        addChoreFrame.setVisible(true);
    }

    public void refresh() {
        tableModel.setRowCount(0);
        try {
            Collection<Chore> choresList = choreService.findAll();
            choresList.forEach(chore -> tableModel.addRow(new String[]{String.valueOf(chore.getId()),
                    chore.getTitle(), chore.getDescription(), simpleDateFormat.format(chore.getDeadline()),
                    String.valueOf(chore.getPriorityLevel()), String.valueOf(chore.getDone())}));
        } catch (ServiceNotAvailableException e) {
            new ErrorFrame(this.frame, "Couldn't load chores. Please refresh");
        }
    }

    // updates the selected chore
    public void updateSelected() {
        int rowIndex = toDoList.getSelectedRow();

        // in case nothing was selected
        if (rowIndex == -1) {
            return;
        }

        // check if chore exists, if not then delete it from the visual interface
        Long id = Long.parseLong((String) tableModel.getValueAt(rowIndex, 0));
        Chore chore;
        try {
            chore = choreService.findById(id);
        } catch (ServiceNotAvailableException e) {
            new ErrorFrame(frame, "Chore doesn't exist or has been deleted");
            tableModel.removeRow(rowIndex);
            return;
        }

        // disables the main frame while getting updated data
        frame.setEnabled(false);
        UpdateChoreFrame updateChoreFrame = new UpdateChoreFrame(chore, simpleDateFormat);

        // re-enable main frame when new one closes
        updateChoreFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                frame.setEnabled(true);
            }
        });

        // when user presses the Ok button, update the chore on the list and re-enable main frame
        updateChoreFrame.getOkButton().addActionListener(e -> {
            Chore updatedChore = updateChoreFrame.getUpdatedChore();

            // in case chore doesn't exist, remove it from the visual interface
            if (updatedChore != null) {
                tableModel.removeRow(rowIndex);
                try {
                    choreService.update(id, updatedChore);
                } catch (ServiceNotAvailableException exception) {
                    new ErrorFrame(frame, "Chore doesn't exist or has been deleted");
                    return;
                }

                tableModel.insertRow(rowIndex, new String[]{String.valueOf(id), updatedChore.getTitle(),
                        updatedChore.getDescription(), simpleDateFormat.format(updatedChore.getDeadline()),
                        String.valueOf(updatedChore.getPriorityLevel()), String.valueOf(updatedChore.getDone())});
                updateChoreFrame.dispose();
                frame.setEnabled(true);
                frame.requestFocus();
            }
        });

        // re-enable main frame when user cancels the operation
        updateChoreFrame.getCancelButton().addActionListener(e -> {
            updateChoreFrame.dispose();
            frame.setEnabled(true);
            frame.requestFocus();
        });

        updateChoreFrame.setVisible(true);
    }

    // deletes the selected chore
    public void deleteSelected() {
        int rowIndex = toDoList.getSelectedRow();

        // in case nothing was selected
        if (rowIndex == -1) {
            return;
        }
        Long id = Long.parseLong((String) tableModel.getValueAt(rowIndex, 0));

        // if chore doesn't exist, remove it from the visual interface
        try {
            choreService.delete(id);
        } catch (ServiceNotAvailableException e) {
            new ErrorFrame(frame, "Chore already deleted");
        } finally {
            tableModel.removeRow(rowIndex);
        }
    }
}
