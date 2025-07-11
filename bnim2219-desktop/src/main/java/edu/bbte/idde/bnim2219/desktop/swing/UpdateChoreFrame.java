package edu.bbte.idde.bnim2219.desktop.swing;

import edu.bbte.idde.bnim2219.backend.model.Chore;
import edu.bbte.idde.bnim2219.desktop.utils.Pair;
import lombok.Getter;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

// frame where user can update an existing chore
public class UpdateChoreFrame extends JFrame {
    @Getter
    private final JButton okButton = new JButton("Ok");
    @Getter
    private final JButton cancelButton = new JButton("Cancel");
    private final transient JTextArea titleText = new JTextArea();
    private final transient JTextArea descriptionText = new JTextArea();
    private final transient JTextArea dateText = new JTextArea();
    private final transient JTextArea priorityText = new JTextArea();
    private final transient JCheckBox doneBox = new JCheckBox();

    // set the text areas according to the input data
    public UpdateChoreFrame(Chore chore, SimpleDateFormat simpleDateFormat) {
        super();
        setSize(new Dimension(400, 400));
        setResizable(false);
        setTitle("Update chore");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        JLabel titleLabel = new JLabel("Title");
        add(titleLabel);
        titleText.setLineWrap(true);
        titleText.setText(chore.getTitle());
        add(new JScrollPane(titleText));

        JLabel descriptionLabel = new JLabel("Description");
        add(descriptionLabel);
        descriptionText.setLineWrap(true);
        descriptionText.setText(chore.getDescription());
        add(new JScrollPane(descriptionText));

        JLabel dateLabel = new JLabel("Date (YYYY/MM/DD)");
        add(dateLabel);
        dateText.setLineWrap(true);
        dateText.setText(simpleDateFormat.format(chore.getDeadline()));
        add(new JScrollPane(dateText));

        JLabel priorityLabel = new JLabel("Priority Level");
        add(priorityLabel);
        priorityText.setLineWrap(true);
        priorityText.setText(chore.getPriorityLevel().toString());
        add(new JScrollPane(priorityText));

        JLabel doneLabel = new JLabel("Done?");
        add(doneLabel);
        doneBox.setSelected(chore.getDone());
        add(doneBox);

        add(okButton);
        add(cancelButton);
    }

    // after validating the input, return a chore with the provided data
    public Chore getUpdatedChore() {
        Pair<Date, Integer> pair = validateInput();
        if (pair == null) {
            return null;
        }
        return new Chore(titleText.getText(), descriptionText.getText(), pair.getFirst(),
                pair.getSecond(), doneBox.isSelected());
    }

    // check if there is a title, date is in correct format and priority level is an integer
    // return the date and integer, so they don't need to parsed again
    private Pair<Date, Integer> validateInput() {
        if (titleText.getText().isEmpty()) {
            new ErrorFrame(this, "You must provide a title");
            return null;
        }

        Date date;
        try {
            date = new Date(new SimpleDateFormat("yyyy/MM/dd", Locale.US).parse(dateText.getText()).getTime());
        } catch (ParseException e) {
            new ErrorFrame(this, "Invalid date format");
            return null;
        }

        int priorityLevel;
        try {
            priorityLevel = Integer.parseInt(priorityText.getText());
        } catch (NumberFormatException e) {
            new ErrorFrame(this, "Invalid priority level. It must be an integer");
            return null;
        }
        return new Pair<>(date, priorityLevel);
    }
}
