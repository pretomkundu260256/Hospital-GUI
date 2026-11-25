import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Patient extends JFrame implements ActionListener {
    private static ArrayList<String[]> patients = new ArrayList<>();

    private JTextField id;
    private JTextField name;
    private JTextField problem;
    private JTextField wardId;
    private JTextField roomId;

    private JButton search;
    private JButton save;
    private JButton delete;
    private JTextArea displayArea;
    private JScrollPane scrollPane;

    public Patient() {
        setTitle("Patient");
        setSize(400, 400);
        setLayout(new BorderLayout(10, 10));
        ((JComponent) getContentPane()).setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

        JPanel gridPanel = new JPanel(new GridLayout(7, 2, 5, 10));

        gridPanel.add(new JLabel("ID:"));
        id = new JTextField(15);
        gridPanel.add(id);

        gridPanel.add(new JLabel("Name:"));
        name = new JTextField(15);
        gridPanel.add(name);

        gridPanel.add(new JLabel("Problem:"));
        problem = new JTextField(15);
        gridPanel.add(problem);

        gridPanel.add(new JLabel("Ward ID:"));
        wardId = new JTextField(15);
        gridPanel.add(wardId);

        gridPanel.add(new JLabel("Bed ID:"));
        roomId = new JTextField(15);
        gridPanel.add(roomId);

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
        displayArea.setText("Fill all the fields to add patient.\nFill only ID to Search and Delete.");
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
            String patientId = id.getText().trim();
            if (patientId.isEmpty()) {
                displayArea.setText("Enter a patient ID to search.");
                return;
            }
            int index = findPatient(patientId);
            if (index == -1) {
                displayArea.setText("Patient not found.");
            } else {
                displayPatient(patients.get(index));
            }
        }
        if (e.getSource() == save) {
            String[] patient = {id.getText(), name.getText(), problem.getText(), wardId.getText(), roomId.getText()};
            for (String value : patient) {
                if (value.isEmpty()) {
                    displayArea.setText("Fill all the fields to add patient.");
                    return;
                }
            }
            if (findPatient(patient[0]) != -1) {
                displayArea.setText("A patient with this ID already exists.");
                return;
            }
            patients.add(patient);
            displayPatient(patient);
            displayArea.append("\nPatient added.");
        }
        if (e.getSource() == delete) {
            String patientId = id.getText();
            if (patientId.isEmpty()) {
                displayArea.setText("Enter a patient ID to delete.");
                return;
            }
            int index = findPatient(patientId);
            if (index == -1) {
                displayArea.setText("Patient not found.");
            } else {
                patients.remove(index);
                displayArea.setText("Patient deleted.");
            }
        }
    }

    private int findPatient(String patientId) {
        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i)[0].equals(patientId)) {
                return i;
            }
        }
        return -1;
    }

    private void displayPatient(String[] patient) {
        displayArea.setText(String.format("ID : %s\nName : %s\nProblem : %s\nWard ID : %s\nRoom No : %s",
                patient[0], patient[1], patient[2], patient[3], patient[4]));
    }
}
