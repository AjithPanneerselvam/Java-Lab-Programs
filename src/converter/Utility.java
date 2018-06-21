package converter;

import javax.swing.*;

public class Utility extends JFrame{
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
