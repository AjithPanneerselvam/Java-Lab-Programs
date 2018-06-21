package converter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DistanceConverter extends JFrame{
    private JLabel meterToKmLabel = new JLabel("Meter to Km");
    private JLabel milesToKmLabel = new JLabel("Miles to Km");
    private JLabel kmToMeter = new JLabel("Km to Meter");
    private JLabel kmToMiles = new JLabel("Km to Miles");

    private JTextField meterToKmTF = new JTextField(5);
    private JTextField milesToKmTF = new JTextField(5);
    private JTextField kmToMeterTF = new JTextField(5);
    private JTextField kmToMilesTF = new JTextField(5);

    private JButton meterToKmButton = new JButton("Convert");
    private JButton milesToKmButton = new JButton("Convert");
    private JButton kmToMeterButton = new JButton("Convert");
    private JButton kmToMilesButton = new JButton("Convert");

    ConvertButtonListener convertButtonListener = new ConvertButtonListener();

    JPanel panel = new JPanel(new GridBagLayout());

    private JButton backButton = new JButton("Back");

    private JLabel[] labels = { meterToKmLabel, milesToKmLabel, kmToMeter, kmToMiles};
    private JTextField[] textFields = { meterToKmTF, milesToKmTF, kmToMeterTF, kmToMilesTF};
    private JButton[] buttons = { meterToKmButton, milesToKmButton, kmToMeterButton, kmToMilesButton, backButton};

    public DistanceConverter(){
        this.prepareGUI();
    }

    public void prepareGUI() {
        this.setTitle("Converter Application");

        addUIComponents();

        this.add(panel);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    public void addUIComponents(){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        addListenersToButtons();

        for(int i = 0; i < 4; i++){
            constraints.gridy = i;
            for(int j = 0; j < 3; j++){
                constraints.gridx = j;
                if(j == 0)
                    this.panel.add(this.labels[i], constraints);
                else if(j == 1)
                    this.panel.add(this.textFields[i], constraints);
                else
                    this.panel.add(this.buttons[i], constraints);
            }
        }

        constraints.gridx = 0;
        constraints.gridy = 4;
        this.panel.add(this.backButton, constraints);

        this.panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Distance Converter"));
    }

    public void addListenersToButtons(){
        for(int i = 0; i < this.buttons.length; i++){
            this.buttons[i].addActionListener(this.convertButtonListener);
        }
    }

    private class ConvertButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Utility utility = new Utility();

            try{
                if(e.getSource() == meterToKmButton){
                    Double kms = Double.parseDouble(DistanceConverter.this.meterToKmTF.getText()) * 0.01;
                    utility.displayDialogueBox(kms, "Meter to Km");
                } else if(e.getSource() == milesToKmButton){
                    Double kms = Double.parseDouble(DistanceConverter.this.milesToKmTF.getText()) * 1.61;
                    utility.displayDialogueBox(kms, "Miles to Km");
                } else if(e.getSource() == kmToMeterButton){
                    Double meters = Double.parseDouble(DistanceConverter.this.kmToMeterTF.getText()) * 1000;
                    utility.displayDialogueBox(meters, "Km To Meter");
                } else if(e.getSource() == kmToMilesButton){
                    Double miles = Double.parseDouble(DistanceConverter.this.kmToMilesTF.getText()) * 0.621;
                    utility.displayDialogueBox(miles, "Km to Mile");
                } else{
                    new Converter();
                    DistanceConverter.this.dispose();
                }
            }catch (NumberFormatException excep){
                utility.displayErrorMessage();
            }
        }
    }
}
