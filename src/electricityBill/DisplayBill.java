package electricityBill;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisplayBill extends JFrame {
    private JButton backButton = new JButton("Go Back");

    public DisplayBill(ElectricityBill electricityBill){
        prepareGUI(electricityBill);
    }

    public void prepareGUI(ElectricityBill electricityBill){
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Electricity Bill");
        this.setVisible(true);
        displayData(electricityBill);
    }

    public void displayData(ElectricityBill electricityBill) {
        Box displayBox = Box.createVerticalBox();
        JLabel consumerNumberLabel = new JLabel("Consumer Number: \t\t" + electricityBill.getConsumerNumber());
        displayBox.add(consumerNumberLabel);
        JLabel consumerNameLabel = new JLabel("Consumer Name: \t\t" + electricityBill.getConsumerName());
        displayBox.add(consumerNameLabel);
        JLabel previousMonthRecordingLabel = new JLabel("Previous Month Recording: \t\t" + electricityBill.getPreviousMonthReading());
        displayBox.add(previousMonthRecordingLabel);
        JLabel currentMonthRecordingLabel = new JLabel("Current Month Recording: \t\t" + electricityBill.getCurrentMonthReading());
        displayBox.add(currentMonthRecordingLabel);
        JLabel consumerTypeLabel = new JLabel("Consumer Type: \t\t" + electricityBill.getConsumerType());
        displayBox.add(consumerTypeLabel);
        JLabel amountLabel = new JLabel("Amount to be paid: \t\t" + electricityBill.getAmount());
        displayBox.add(amountLabel);

        BackButtonListener backButtonListener = new BackButtonListener();
        backButton.addActionListener(backButtonListener);
        displayBox.add(backButton);

        this.add(displayBox);
    }

    private class BackButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == backButton){
                new ElectricityBill();
                DisplayBill.this.dispose();
            }
        }
    }
}
