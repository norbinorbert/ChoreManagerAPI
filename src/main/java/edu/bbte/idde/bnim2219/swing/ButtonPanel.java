package edu.bbte.idde.bnim2219.swing;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

// panel that has 3 buttons which can be used to crate, update and delete chores
public class ButtonPanel extends JPanel {
    private static final Integer BUTTON_HEIGHT = 50;

    public ButtonPanel(DisplayPanel displayPanel) {

        FlowLayout flowLayout = new FlowLayout();
        setLayout(flowLayout);
        setBackground(Color.LIGHT_GRAY);

        // buttons will fill the whole width
        JButton addButton = new JButton("Add new chore");
        add(addButton);
        addButton.setPreferredSize(new Dimension(MainFrame.DEFAULT_WIDTH / 4 - 3 * flowLayout.getHgap(),
                BUTTON_HEIGHT));
        addButton.addActionListener(e -> displayPanel.addNewChore());

        // buttons will fill the whole width
        JButton refreshButton = new JButton("Refresh to-do list");
        add(refreshButton);
        refreshButton.setPreferredSize(new Dimension(MainFrame.DEFAULT_WIDTH / 4 - 3 * flowLayout.getHgap(),
                BUTTON_HEIGHT));
        refreshButton.addActionListener(e -> displayPanel.refresh());

        JButton updateButton = new JButton("Update selected chore");
        add(updateButton);
        updateButton.setPreferredSize(new Dimension(MainFrame.DEFAULT_WIDTH / 4 - 3 * flowLayout.getHgap(),
                BUTTON_HEIGHT));
        updateButton.addActionListener(e -> displayPanel.updateSelected());

        JButton deleteButton = new JButton("Delete selected chores");
        add(deleteButton);
        deleteButton.setPreferredSize(new Dimension(MainFrame.DEFAULT_WIDTH / 4 - 3 * flowLayout.getHgap(),
                BUTTON_HEIGHT));
        deleteButton.addActionListener(e -> displayPanel.deleteSelected());
    }
}
