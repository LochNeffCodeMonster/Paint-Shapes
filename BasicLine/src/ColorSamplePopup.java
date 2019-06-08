import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;

public class ColorSamplePopup {

  public static void main(String args[]) {

    JFrame frame = new JFrame("JColorChooser Sample Popup");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    final JButton button = new JButton("Pick to Change Background");

    ActionListener actionListener = new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        Color initialBackground = button.getBackground();
        Color background = JColorChooser.showDialog(null, "Change Button Background",
            initialBackground);
        if (background != null) {
          button.setBackground(background);
        }
      }
    };
    button.addActionListener(actionListener);
    frame.add(button, BorderLayout.CENTER);

    frame.setSize(300, 100);
    frame.setVisible(true);
  }
}