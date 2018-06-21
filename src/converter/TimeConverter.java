package converter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;

public class TimeConverter extends JFrame {
    private JLabel hoursToMinutesLabel = new JLabel("Hours to Minutes");
    private JLabel hoursToSecondsLabel = new JLabel("Hours to Seconds");
    private JLabel minutesToHoursLabel = new JLabel("Minutes to Hours");
    private JLabel secondsToHoursLabel = new JLabel("Seconds to Hours");

    private JTextField hoursToMinutesTF = new JTextField(5);
    private JTextField hoursToSecondsTF = new JTextField(5);
    private JTextField minutesToHoursTF = new JTextField(5);
    private JTextField secondsToHoursTF = new JTextField(5);

    private JButton hoursToMinutesButton = new JButton("Convert");
    private JButton hoursToSecondsButton = new JButton("Convert");
    private JButton minutesToHoursButton = new JButton("Convert");
    private JButton secondsToHoursButton = new JButton("Convert");

    ConvertButtonListener convertButtonListener = new ConvertButtonListener();

    Utility utility = new Utility();

    private JButton backButton = new JButton("Back");

    private JLabel[] labels = {hoursToMinutesLabel, hoursToSecondsLabel, minutesToHoursLabel, secondsToHoursLabel};
    private JTextField[] textFields = {hoursToMinutesTF, hoursToSecondsTF, minutesToHoursTF, secondsToHoursTF};
    private JButton[] buttons = {hoursToMinutesButton, hoursToSecondsButton, minutesToHoursButton, secondsToHoursButton, backButton};

    public TimeConverter() {
        this.prepareGUI();
    }

    public void prepareGUI() {
        this.setTitle("Converter Application");
        addListenersToButtons();
        JPanel panel = utility.addUIComponents(4, 3, labels, textFields, buttons, "Distance Converter");

        this.add(panel);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

    }

    public void addListenersToButtons() {
        for (int i = 0; i < this.buttons.length; i++) {
            this.buttons[i].addActionListener(this.convertButtonListener);
        }
    }

    private class ConvertButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Utility utility = new Utility();

            try{
              if(e.getSource() == hoursToMinutesButton){
                  int minutes = Integer.parseInt(hoursToMinutesTF.getText()) * 60;
                  utility.displayDialogueBox(minutes, "Hours to Minutes");
              }
              else if(e.getSource() == hoursToSecondsButton){
                  int seconds = Integer.parseInt(hoursToSecondsTF.getText()) * 60 * 60;
                  utility.displayDialogueBox(seconds, "Hours to Seconds");
              }
              else if(e.getSource() == minutesToHoursButton){
                  double hours = Double.parseDouble(minutesToHoursTF.getText()) / 60;
                  utility.displayDialogueBox(hours, "Minutes to Hours");
              }else if(e.getSource() == secondsToHoursButton){
                    double seconds = Double.parseDouble(secondsToHoursTF.getText()) / 3600;
                    utility.displayDialogueBox(seconds, "Seconds to Hours");
              }else{
                  new Converter();
                  TimeConverter.this.dispose();
              }
            } catch (NumberFormatException excep){
                utility.displayErrorMessage();
            }
        }
    }

}