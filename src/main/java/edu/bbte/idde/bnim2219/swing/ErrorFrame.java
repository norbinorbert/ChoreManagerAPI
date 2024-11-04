package edu.bbte.idde.bnim2219.swing;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

// error frame, disables parent frame and displays a message
public class ErrorFrame extends JFrame {

    public ErrorFrame(JFrame parentFrame, String message) {
        super();
        parentFrame.setEnabled(false);

        setTitle("Error");
        setSize(300, 100);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel errorLabel = new JLabel(message);
        errorLabel.setForeground(Color.RED);
        add(errorLabel);
        errorLabel.setVerticalAlignment(SwingConstants.CENTER);
        errorLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // re-enables parent frame when closed
        addWindowListener(new MyWindowAdapter(parentFrame));
        setVisible(true);
    }

    private static class MyWindowAdapter extends WindowAdapter {
        private transient JFrame frame;

        public MyWindowAdapter(JFrame frame) {
            super();
            this.frame = frame;
        }

        @Override
        public void windowClosing(WindowEvent e) {
            super.windowClosing(e);
            frame.setEnabled(true);
        }
    }
}
