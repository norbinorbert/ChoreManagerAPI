package edu.bbte.idde.bnim2219.swing;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanel extends JPanel implements ActionListener {
    private final JButton addButton = new JButton("Add new chore");
    private final JButton updateButton = new JButton("Update selected chore");
    private final JButton deleteButton = new JButton("Delete selected chores");
    private final DisplayPanel displayPanel;
    private static final Integer BUTTON_HEIGHT = 50;

    public ButtonPanel(DisplayPanel displayPanel){
        this.displayPanel = displayPanel;

        FlowLayout flowLayout = new FlowLayout();
        setLayout(flowLayout);
        setBackground(Color.LIGHT_GRAY);

        add(addButton);
        addButton.setPreferredSize(new Dimension(MainFrame.DEFAULT_WIDTH / 3 - 3 * flowLayout.getHgap(),
                BUTTON_HEIGHT));
        addButton.addActionListener(this);

        add(updateButton);
        updateButton.setPreferredSize(new Dimension(MainFrame.DEFAULT_WIDTH / 3 - 3 * flowLayout.getHgap(),
                BUTTON_HEIGHT));
        updateButton.addActionListener(this);

        add(deleteButton);
        deleteButton.setPreferredSize(new Dimension(MainFrame.DEFAULT_WIDTH / 3 - 3 * flowLayout.getHgap(),
                BUTTON_HEIGHT));
        deleteButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(addButton)){
            displayPanel.addNewChore();
        }
        if(e.getSource().equals(updateButton)){
            displayPanel.updateSelected();
        }
        if(e.getSource().equals(deleteButton)){
            displayPanel.deleteSelected();
        }
    }
}
