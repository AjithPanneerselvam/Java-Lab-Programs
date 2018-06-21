package converter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Converter extends JFrame {
    private JButton currencyConverterButton = new JButton("Currency Converter");
    private JButton distanceConverterButton = new JButton("Distance Converter");
    private JButton timerConverterButton = new JButton("Time Converter");


    public Converter() {
        this.prepareGUI();
    }

    public void prepareGUI(){
        this.setTitle("Converter Application");

        JPanel panel = new JPanel(new GridBagLayout());
        ButtonListener buttonListener = new ButtonListener();
        currencyConverterButton.addActionListener(buttonListener);
        distanceConverterButton.addActionListener(buttonListener);
        timerConverterButton.addActionListener(buttonListener);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(currencyConverterButton, constraints);

        constraints.gridy = 1;
        panel.add(distanceConverterButton, constraints);

        constraints.gridy = 2;
        panel.add(timerConverterButton, constraints);

        this.add(panel);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }


    public static void main(String[] args){
        new Converter();
    }


    private class ButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == currencyConverterButton){
                new CurrencyConverter();
                Converter.this.dispose();
            }
            else if(e.getSource() == distanceConverterButton){
                new DistanceConverter();
                Converter.this.dispose();
            }
            else{
                new TimeConverter();
                Converter.this.dispose();
            }
        }
    }
}
