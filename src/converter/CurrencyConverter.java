package converter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CurrencyConverter extends JFrame {
    private JLabel dollarToINRLabel = new JLabel("Dollar to INR");
    private JLabel euroToINRLabel = new JLabel("Euro to INR");
    private JLabel yenToINRLabel = new JLabel("Yen to INR");
    private JLabel inrToDollarLabel = new JLabel("INR to Dollar");
    private JLabel inrToEuroLabel = new JLabel("INR to Euro");
    private JLabel inrToYenLabel = new JLabel("INR to Yen");

    private JTextField dollarTF = new JTextField(5);
    private JTextField euroTF = new JTextField(5);
    private JTextField yenTF = new JTextField(5);
    private JTextField inrToDollarTF = new JTextField(5);
    private JTextField inrToEuroTF = new JTextField(5);
    private JTextField inrToYenTF = new JTextField(5);

    private JButton dollarToINRButton = new JButton("Convert");
    private JButton euroToINRButton = new JButton("Convert");
    private JButton yenToINRButton = new JButton("Convert");
    private JButton inrToDollarButton = new JButton("Convert");
    private JButton inrToEuroButton = new JButton("Convert");
    private JButton inrToYenButton = new JButton("Convert");

    private JButton backButton = new JButton("Back");

    public CurrencyConverter(){
        this.prepareGUI();
    }

    public void prepareGUI() {
        this.setTitle("Converter Application");

        ConvertButtonListener convertButtonListener = new ConvertButtonListener();
        JPanel panel = new JPanel(new GridBagLayout());

        dollarToINRButton.addActionListener(convertButtonListener);
        euroToINRButton.addActionListener(convertButtonListener);
        yenToINRButton.addActionListener(convertButtonListener);
        inrToDollarButton.addActionListener(convertButtonListener);
        inrToEuroButton.addActionListener(convertButtonListener);
        inrToYenButton.addActionListener(convertButtonListener);
        backButton.addActionListener(convertButtonListener);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        // Add Dollar to INR UI Components
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(dollarToINRLabel, constraints);

        constraints.gridx = 1;
        panel.add(dollarTF, constraints);

        constraints.gridx = 2;
        panel.add(dollarToINRButton, constraints);

        // Add Euro to INR UI Components
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(euroToINRLabel, constraints);

        constraints.gridx = 1;
        panel.add(euroTF, constraints);

        constraints.gridx = 2;
        panel.add(euroToINRButton, constraints);

        // Add Yen to INR UI Components
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(yenToINRLabel, constraints);

        constraints.gridx = 1;
        panel.add(yenTF, constraints);

        constraints.gridx = 2;
        panel.add(yenToINRButton, constraints);

        // Add INR to Dollar UI Components
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(inrToDollarLabel, constraints);

        constraints.gridx = 1;
        panel.add(inrToDollarTF, constraints);

        constraints.gridx = 2;
        panel.add(inrToDollarButton, constraints);

        // Add INR to Euro UI Components
        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(inrToEuroLabel, constraints);

        constraints.gridx = 1;
        panel.add(inrToEuroTF, constraints);

        constraints.gridx = 2;
        panel.add(inrToEuroButton, constraints);

        // Add INR to Yen UI Components
        constraints.gridx = 0;
        constraints.gridy = 5;
        panel.add(inrToYenLabel, constraints);

        constraints.gridx = 1;
        panel.add(inrToYenTF, constraints);

        constraints.gridx = 2;
        panel.add(inrToYenButton, constraints);

        // Add Back button UI Component
        constraints.gridx = 0;
        constraints.gridy = 6;
        panel.add(backButton, constraints);


        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Currency Converter"));
        this.add(panel);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    private class ConvertButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Utility utility = new Utility();
            try {
                if (e.getSource() == dollarToINRButton) {
                    Double rupees = CurrencyConverter.this.dollarToINR(Double.parseDouble(dollarTF.getText()));
                    utility.displayDialogueBox(rupees, "Dollar to INR");
                } else if (e.getSource() == euroToINRButton) {
                    Double rupees = CurrencyConverter.this.euroToINR(Double.parseDouble(euroTF.getText()));
                    utility.displayDialogueBox(rupees, "Euro to INR");
                } else if (e.getSource() == yenToINRButton) {
                    Double rupees = CurrencyConverter.this.yenToINR(Double.parseDouble(yenTF.getText()));
                    utility.displayDialogueBox(rupees, "Yen to INR");
                } else if (e.getSource() == inrToDollarButton) {
                    Double dollar = CurrencyConverter.this.inrToDollar(Double.parseDouble(inrToDollarTF.getText()));
                    utility.displayDialogueBox(dollar, "INR to Dollar");
                } else if (e.getSource() == inrToEuroButton) {
                    Double euro = CurrencyConverter.this.inrToEuro(Double.parseDouble(inrToEuroTF.getText()));
                    utility.displayDialogueBox(euro, "INR to Euro");
                } else if (e.getSource() == inrToYenButton) {
                    Double yen = CurrencyConverter.this.inrToYen(Double.parseDouble(inrToYenTF.getText()));
                    utility.displayDialogueBox(yen, "INR to Yen");
                } else if (e.getSource() == backButton) {
                    new Converter();
                    CurrencyConverter.this.dispose();
                }
            }catch (NumberFormatException excep){
                utility.displayErrorMessage();
            }
        }
    }

    public double dollarToINR(double dollars){
        return dollars * 67.82;
    }

    public double euroToINR(double euros){
        return euros * 78.72;
    }

    public double yenToINR(double yen){
        return yen * 0.62;
    }

    public double inrToDollar(double rupees){
        return rupees * 0.015;
    }

    public double inrToEuro(double rupees){
        return rupees * 0.013;
    }

    public double inrToYen(double rupees){
        return rupees * 1.62;
    }

}
