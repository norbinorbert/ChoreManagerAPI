package edu.bbte.idde.bnim2219.swing;

import edu.bbte.idde.bnim2219.model.Chore;
import edu.bbte.idde.bnim2219.model.Triple;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewChoreFrame extends JFrame {
    private final JButton okButton = new JButton("Ok");
    private final JButton cancelButton = new JButton("Cancel");
    private final JTextArea titleText = new JTextArea();
    private final JTextArea descriptionText = new JTextArea();
    private final JTextArea dateText = new JTextArea();
    private final JTextArea priorityText = new JTextArea();

    public NewChoreFrame(){
        setSize(new Dimension(400, 300));
        setResizable(false);
        setTitle("New chore");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5, 5));

        JLabel titleLabel = new JLabel("Title");
        add(titleLabel);
        titleText.setLineWrap(true);
        add(new JScrollPane(titleText));

        JLabel descriptionLabel = new JLabel("Description");
        add(descriptionLabel);
        descriptionText.setLineWrap(true);
        add(new JScrollPane(descriptionText));

        JLabel dateLabel = new JLabel("Date (YYYY/MM/DD)");
        add(dateLabel);
        dateText.setLineWrap(true);
        add(new JScrollPane(dateText));

        JLabel priorityLabel = new JLabel("Priority Level");
        add(priorityLabel);
        priorityText.setLineWrap(true);
        add(new JScrollPane(priorityText));

        add(okButton);
        add(cancelButton);
    }

    public Chore getNewChore(){
        Triple<Boolean, Date, Integer> triple = validateInput();
        if(!triple.getLeft()){
            return null;
        }
        return new Chore(9L, titleText.getText(), descriptionText.getText(), triple.getMiddle(),
                triple.getRight(), false);
    }

    private Triple<Boolean, Date, Integer> validateInput(){
        Date date;
        try{
            date = new SimpleDateFormat("yyyy/MM/dd").parse(dateText.getText());
        } catch (ParseException e) {
            new ErrorFrame(this, "Invalid date format");
            return new Triple<>(false, null, null);
        }

        int priorityLevel;
        try {
            priorityLevel = Integer.parseInt(priorityText.getText());
        }
        catch (NumberFormatException e){
            new ErrorFrame(this, "Invalid priority level. It must be an integer");
            return new Triple<>(false, null, null);
        }
        return new Triple<>(true, date, priorityLevel);
    }

    public JButton getOkButton() {
        return okButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }
}
