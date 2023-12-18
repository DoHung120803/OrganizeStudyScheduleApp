package app.controller.view_controller;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test {
    public static void main(String[] args) {
        JFrame frame = new JFrame("TextField to TextArea Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        JTextField textField = new JTextField(20);
        JButton addButton = new JButton("Add to TextArea");
        JTextArea textArea = new JTextArea(10, 20);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = textField.getText();
                textArea.append(text + "\n");
                textField.setText(""); // Clear the JTextField after adding to JTextArea
            }
        });

        panel.add(textField);
        panel.add(addButton);

        frame.add(panel, BorderLayout.NORTH);
        frame.add(textArea, BorderLayout.CENTER);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
