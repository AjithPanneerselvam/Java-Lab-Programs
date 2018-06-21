package converter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Utility extends JFrame{

    public JPanel addUIComponents(int rows, int columns, JLabel[] labels, JTextField[] textFields, JButton[] buttons, String borderTitle){
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        for(int i = 0; i < rows; i++){
            constraints.gridy = i;
            for(int j = 0; j < columns; j++){
                constraints.gridx = j;
                if(j == 0)
                    panel.add(labels[i], constraints);
                else if(j == 1)
                    panel.add(textFields[i], constraints);
                else
                    panel.add(buttons[i], constraints);
            }
        }

        constraints.gridx = 0;
        constraints.gridy = rows;
        panel.add(buttons[buttons.length - 1], constraints);
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), borderTitle));

        return panel;
    }



    public void displayDialogueBox(double value, String message){
        if(value < 0.0)
            displayErrorMessage();
        else
            JOptionPane.showMessageDialog(this, value, message, JOptionPane.INFORMATION_MESSAGE);
    }

    public void displayErrorMessage(){
        JOptionPane.showMessageDialog(this, "Invalid Data", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
