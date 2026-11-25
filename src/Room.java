import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Room extends JFrame implements ActionListener {
    private static ArrayList<String[]> rooms = new ArrayList<>();

    private JTextField number;
    private JTextField capacity;

    private JButton search;
    private JButton save;
    private JButton delete;
    private JTextArea displayArea;
    private JScrollPane scrollPane;

    public Room() {
        setTitle("Room");
        setSize(400, 400);
        setLayout(new BorderLayout(10, 10));
        ((JComponent) getContentPane()).setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

        JPanel gridPanel = new JPanel(new GridLayout(4, 2, 5, 10));

        gridPanel.add(new JLabel("Number:"));
        number = new JTextField(15);
        gridPanel.add(number);

        gridPanel.add(new JLabel("Capacity:"));
        capacity = new JTextField(15);
        gridPanel.add(capacity);

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
        displayArea.setText("Fill all the fields to add room.\nFill only Number to Search and Delete.");
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
            String roomNumber = number.getText();
            if (roomNumber.isEmpty()) {
                displayArea.setText("Enter a room number to search.");
                return;
            }
            int index = findRoom(roomNumber);
            if (index == -1) {
                displayArea.setText("Room not found.");
            } else {
                displayRoom(rooms.get(index));
            }
        }
        if (e.getSource() == save) {
            String[] room = {number.getText(), capacity.getText()};
            for (String value : room) {
                if (value.isEmpty()) {
                    displayArea.setText("Fill all the fields to add room.");
                    return;
                }
            }
            if (findRoom(room[0]) != -1) {
                displayArea.setText("A room with this number already exists.");
                return;
            }
            rooms.add(room);
            displayRoom(room);
            displayArea.append("\nRoom added.");
        }
        if (e.getSource() == delete) {
            String roomNumber = number.getText();
            if (roomNumber.isEmpty()) {
                displayArea.setText("Enter a room number to delete.");
                return;
            }

            int index = findRoom(roomNumber);
            if (index == -1) {
                displayArea.setText("Room not found.");
            } else {
                rooms.remove(index);
                displayArea.setText("Room deleted.");
            }
        }
    }

    private int findRoom(String roomNumber) {
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i)[0].equals(roomNumber)) {
                return i;
            }
        }
        return -1;
    }

    private void displayRoom(String[] room) {
        displayArea.setText(String.format("Number : %s\nCapacity : %s", room[0], room[1]));
    }
}
