import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

public class Hospital extends JFrame implements ActionListener {
    private JButton doctor;
    private JButton patient;
    private JButton nurse;
    private JButton building;
    private JButton ward;
    private JButton room;

    public Hospital() {
        setTitle("Hospital Management System");
        setSize(600, 300);



        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        ((JComponent) getContentPane()).setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

        titlePanel.add(new JLabel("Welcome to Hospital Management System"));
        titlePanel.add(new JLabel("Select an Option to continue"));

        add(titlePanel,BorderLayout.NORTH);

        JPanel selectPanel = new JPanel();
        selectPanel.setLayout(new BoxLayout(selectPanel, BoxLayout.Y_AXIS));

        JPanel gridPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        building = new JButton("Building");
        building.addActionListener(this);
        gridPanel.add(building);
        ward = new JButton("Ward");
        ward.addActionListener(this);
        gridPanel.add(ward);
        room = new JButton("Room");
        room.addActionListener(this);
        gridPanel.add(room);
        doctor = new JButton("Doctor");
        doctor.addActionListener(this);
        gridPanel.add(doctor);
        patient = new JButton("Patient");
        patient.addActionListener(this);
        gridPanel.add(patient);
        nurse = new JButton("Nurse");
        nurse.addActionListener(this);
        gridPanel.add(nurse);



        selectPanel.add(gridPanel);
        add(selectPanel, BorderLayout.CENTER);



        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == doctor) {
            JFrame frame = new Doctor();
            frame.show();

        }
        else if (e.getSource() == ward) {
            JFrame frame = new Ward();
            frame.show();
        }
        else if (e.getSource() == patient) {
            JFrame frame = new Patient();
            frame.show();
        }
        else if (e.getSource() == nurse) {
            JFrame frame = new Nurse();
            frame.show();
        }
        else if (e.getSource() == building) {
            JFrame frame = new Building();
            frame.show();
        }
        else if (e.getSource() == room) {
            JFrame frame = new Room();
            frame.show();
        }

    }
}
