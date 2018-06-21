package electricityBill;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;


public class ElectricityBill extends JFrame{
    private JButton generateButton, clearButton;
    private JLabel headerLabel, consumerNumberLabel, consumerNameLabel, previousMonthReadingLabel, currentMonthReadingLabel, consumerTypeLabel;
    private JTextField consumerNumberTF, consumerNameTF, previousMonthReadingTF, currentMonthReadingTF;
    private JRadioButton domesticRadioButton, commercialRadioButton;
    private double[] domesticPrice = {1.0, 2.50, 4, 6};
    private double[] commercialPrice = {2, 4.50, 6, 7};
    private double amount = 0.0;

    public static void main(String[] args){
        new ElectricityBill();
    }

    public ElectricityBill(){
        prepareGUI();
    }

    public  void prepareGUI(){
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Electricity Bill Generator");

        JPanel panel = new JPanel();

        Box consumerNumberBox = Box.createHorizontalBox();
        consumerNumberLabel = new JLabel("Consumer Number: ");
        consumerNumberBox.add(consumerNumberLabel);
        consumerNumberTF = new JTextField("", 10);
        consumerNumberBox.add(consumerNumberTF);
        consumerNumberBox.add(Box.createHorizontalStrut(4));
        consumerNumberBox.add(Box.createRigidArea(new Dimension(30, 20)));
        panel.add(consumerNumberBox);

        Box consumerNameBox = Box.createHorizontalBox();
        consumerNameLabel = new JLabel("Consumer Name: ");
        consumerNameBox.add(consumerNameLabel);
        consumerNameTF = new JTextField("", 10);
        consumerNameBox.add(consumerNameTF);
        consumerNameBox.add(Box.createHorizontalStrut(4));
        consumerNameBox.add(Box.createRigidArea(new Dimension(5, 20)));
        panel.add(consumerNameBox);

        Box previousMonthReadingBox = Box.createHorizontalBox();
        previousMonthReadingLabel = new JLabel("Previous Month Reading: ");
        previousMonthReadingBox.add(previousMonthReadingLabel);
        previousMonthReadingTF = new JTextField("", 5);
        previousMonthReadingBox.add(previousMonthReadingTF);
        previousMonthReadingBox.add(Box.createHorizontalStrut(4));
        panel.add(previousMonthReadingBox);

        Box currentMonthReadingBox = Box.createHorizontalBox();
        currentMonthReadingLabel = new JLabel("Current Month Reading: ");
        currentMonthReadingBox.add(currentMonthReadingLabel);
        currentMonthReadingTF = new JTextField("", 5);
        currentMonthReadingBox.add(currentMonthReadingTF);
        currentMonthReadingBox.add(Box.createHorizontalStrut(4));
        panel.add(currentMonthReadingBox);

        domesticRadioButton = new JRadioButton("Domestic");
        commercialRadioButton = new JRadioButton("Commercial");

        ButtonGroup radioButtonGroup = new ButtonGroup();
        radioButtonGroup.add(domesticRadioButton);
        radioButtonGroup.add(commercialRadioButton);

        JPanel radioButtonPanel = new JPanel();
        Border radioButtonBorder = BorderFactory.createTitledBorder("Consumer Type");
        radioButtonPanel.setBorder(radioButtonBorder);
        radioButtonPanel.add(domesticRadioButton);
        radioButtonPanel.add(commercialRadioButton);
        domesticRadioButton.setSelected(true);
        panel.add(radioButtonPanel);

        Box buttonBox = Box.createHorizontalBox();
        generateButton = new JButton("Generate");
        buttonBox.add(generateButton);
        clearButton = new JButton("Clear");
        buttonBox.add(clearButton);

        GenerateButtonListener generateButtonListener = new GenerateButtonListener();
        generateButton.addActionListener(generateButtonListener);
        ClearButtonListener clearButtonListener = new ClearButtonListener();
        clearButton.addActionListener(clearButtonListener);
        buttonBox.add(Box.createHorizontalStrut(4));
        panel.add(buttonBox);

        this.add(panel);
        this.setVisible(true);
        consumerNumberTF.requestFocus();
    }


    private class GenerateButtonListener implements  ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String consumerName = "", consumerNumber = "";
            double previousMonthReading = -1.0, currentMonthReading = -1.0;
            int priceIndex;

            if(e.getSource() == generateButton) {
                try {
                    consumerNumber = consumerNumberTF.getText();
                    consumerName = consumerNameTF.getText();
                    previousMonthReading = Double.parseDouble(previousMonthReadingTF.getText());
                    currentMonthReading = Double.parseDouble(currentMonthReadingTF.getText());

                    if (consumerName.isEmpty() || consumerNumber.isEmpty() || previousMonthReading == -1.0 || currentMonthReading == -1.0) {
                        JOptionPane.showMessageDialog(ElectricityBill.this, "Some text fields looks to be empty", "Error", JOptionPane.ERROR_MESSAGE);
                        System.exit(0);
                    }else if(previousMonthReading > currentMonthReading){
                        JOptionPane.showMessageDialog(ElectricityBill.this, "Previous Month Recording cannot be greater than Current Month Recording", "Error", JOptionPane.ERROR_MESSAGE);
                        System.exit(0);
                    }
                }
                catch (NumberFormatException excep) {
                    JOptionPane.showMessageDialog(ElectricityBill.this, "Invalid Data", "Error", JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                }

                double units = currentMonthReading - previousMonthReading;
                priceIndex = getPriceIndex(units);

                if (priceIndex == -1)
                    JOptionPane.showMessageDialog(ElectricityBill.this, "Invalid Data", "Error", JOptionPane.ERROR_MESSAGE);

                if (domesticRadioButton.isSelected())
                    ElectricityBill.this.amount = domesticPrice[priceIndex] * units;
                else
                    ElectricityBill.this.amount = commercialPrice[priceIndex] * units;

                JOptionPane.showMessageDialog(ElectricityBill.this, ElectricityBill.this.amount, "Electricity Bill", JOptionPane.INFORMATION_MESSAGE);
                new DisplayBill(ElectricityBill.this);
                ElectricityBill.this.dispose();
            }
        }


        public int getPriceIndex(double units){
            int index;

            if (units < 1)
                index = -1;
            else if(units >= 1 && units <= 100)
                index = 0;
            else if(units >= 101 && units <= 200)
                index = 1;
            else if(units >= 201 && units <= 500)
                index = 2;
            else
                index = 3;

            return index;
        }
    }


    private class ClearButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == clearButton) {
                consumerNumberTF.setText("");
                consumerNameTF.setText("");
                previousMonthReadingTF.setText("");
                currentMonthReadingTF.setText("");
            }
        }
    }

    public String getConsumerNumber(){
        return consumerNumberTF.getText();
    }

    public String getConsumerName(){
        return consumerNameTF.getText();
    }

    public Double getPreviousMonthReading(){
        return Double.parseDouble(previousMonthReadingTF.getText());
    }

    public Double getCurrentMonthReading(){
        return Double.parseDouble(currentMonthReadingTF.getText());
    }

    public String getConsumerType(){
        if(domesticRadioButton.isSelected())
            return "Domestic";
        else
            return "Commercial";
    }

    public Double getAmount(){
        return amount;
    }
}