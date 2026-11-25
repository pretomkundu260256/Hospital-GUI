import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Nurse extends JFrame implements ActionListener {
    private static ArrayList<String[]> nurses = new ArrayList<>();

    private JTextField id;
    private JTextField name;
    private JTextField ward;
    private JTextField shift;

    private JButton search;
    private JButton save;
    private JButton delete;
    private JTextArea displayArea;
    private JScrollPane scrollPane;

    public Nurse() {
        setTitle("Nurse");
        setSize(400, 400);
        setLayout(new BorderLayout(10, 10));
        ((JComponent) getContentPane()).setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

        JPanel gridPanel = new JPanel(new GridLayout(6, 2, 5, 10));

        gridPanel.add(new JLabel("ID:"));
        id = new JTextField(15);
        gridPanel.add(id);

        gridPanel.add(new JLabel("Name:"));
        name = new JTextField(15);
        gridPanel.add(name);

        gridPanel.add(new JLabel("Ward:"));
        ward = new JTextField(15);
        gridPanel.add(ward);

        gridPanel.add(new JLabel("Shift:"));
        shift = new JTextField(15);
        gridPanel.add(shift);

        save = new JButton("Add");
        save.addActionListener(this);
        gridPanel.add(save);

        delete = new JButton("Delete");
        delete.addActionListener(this);
        gridPanel.add(delete);

        search = new JButton("Search");
        search.addActionListener(this);
        gridPanel.add(search);

        inputPanel.add(gridPanel);
        add(inputPanel, BorderLayout.WEST);

        displayArea = new JTextArea(10, 40);
        displayArea.setText("Fill all the fields to add nurse.\nFill only ID to Search and Delete.");
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        displayArea.setMargin(new Insets(10, 10, 10, 10));

        scrollPane = new JScrollPane(displayArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Submitted Details"));
        add(scrollPane, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search) {
            String nurseId = id.getText().trim();
            if (nurseId.isEmpty()) {
                displayArea.setText("Enter a nurse ID to search.");
                return;
            }
            int index = findNurse(nurseId);
            if (index == -1) {
                displayArea.setText("Nurse not found.");
            } else {
                displayNurse(nurses.get(index));
            }
        }
        if (e.getSource() == save) {
            String[] nurse = {id.getText(), name.getText(), ward.getText(), shift.getText()};
            for (String s : nurse) {
                if (s.isEmpty()) {
                    displayArea.setText("Fill all the fields to add nurse.");
                    return;
                }
            }
            if (findNurse(nurse[0]) != -1) {
                displayArea.setText("A nurse with this ID already exists.");
                return;
            }
            nurses.add(nurse);
            displayNurse(nurse);
            displayArea.append("\nNurse added.");
        }
        if (e.getSource() == delete) {
            String nurseId = id.getText();
            if (nurseId.isEmpty()) {
                displayArea.setText("Enter a nurse ID to delete.");
                return;
            }
            int index = findNurse(nurseId);
            if (index == -1) {
                displayArea.setText("Nurse not found.");
            } else {
                nurses.remove(index);
                displayArea.setText("Nurse deleted.");
            }
        }
    }

    private int findNurse(String nurseId) {
        for (int i = 0; i < nurses.size(); i++) {
            if (nurses.get(i)[0].equals(nurseId)) {
                return i;
            }
        }
        return -1;
    }

    private void displayNurse(String[] nurse) {
        displayArea.setText(String.format("ID : %s\nName : %s\nWard : %s\nShift : %s",
                nurse[0], nurse[1], nurse[2], nurse[3]));
    }
}
