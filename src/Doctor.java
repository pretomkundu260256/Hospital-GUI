import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Doctor extends JFrame implements ActionListener {
    private static ArrayList<String[]> doctors = new ArrayList<>();

    private JTextField id;
    private JTextField name;
    private JTextField expertise;
    private JTextField qualifications;

    private JButton search;
    private JButton save;
    private JButton delete;


    private JTextArea displayArea;
    private JScrollPane scrollPane;

    public Doctor() {
        setTitle("Doctor");
        setSize(400,400);

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

        gridPanel.add(new JLabel("Expertise:"));
        expertise = new JTextField(15);
        gridPanel.add(expertise);

        gridPanel.add(new JLabel("Qualifications:"));
        qualifications = new JTextField(15);
        gridPanel.add(qualifications);

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
        displayArea.setText("Fill all the fields to add doctor.\nFill only ID to Search and Delete.");

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
            String doctorId = id.getText();
            if (doctorId.isEmpty()) {
                displayArea.setText("Enter a doctor ID to search.");
                return;
            }
            int index = findDoctor(doctorId);
            if (index == -1) {
                displayArea.setText("Doctor not found.");
            } else {
                displayDoctor(doctors.get(index));
            }
        }
        if (e.getSource() == save) {
            String[] doctor = {id.getText(), name.getText(), expertise.getText(), qualifications.getText()};
            for(String s : doctor){
                if(s.isEmpty()){
                    displayArea.setText("Fill all the fields to add doctor.");
                }
            }
            if(findDoctor(doctor[0]) != -1){
                displayArea.setText("A Doctor with this ID already exists.");
                return;
            }
            doctors.add(doctor);
            displayDoctor(doctor);
            displayArea.append("\nDoctor added successfully.");
        }
        if (e.getSource() == delete) {
            String doctorId = id.getText();
            if (doctorId.isEmpty()) {
                displayArea.setText("Enter a doctor ID to delete.");
                return;
            }
            int index = findDoctor(doctorId);
            if (index == -1) {
                displayArea.setText("Doctor not found.");
            } else {
                doctors.remove(index);
                displayArea.setText("Doctor deleted successfully.");
            }
        }
    }

    private int findDoctor(String doctorId) {
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i)[0].equals(doctorId)) {
                return i;
            }
        }
        return -1;
    }

    private void displayDoctor(String[] doctor) {
        displayArea.setText(String.format("ID : %s\nName : %s\nExpertise : %s\nQualifications : %s\n",
                doctor[0], doctor[1], doctor[2], doctor[3]));
    }
}
