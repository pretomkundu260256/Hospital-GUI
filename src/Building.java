import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Building extends JFrame implements ActionListener {
    private static ArrayList<String[]> buildings = new ArrayList<>();

    private JTextField name;
    private JTextField number;
    private JTextField capacity;

    private JButton search;
    private JButton save;
    private JButton delete;
    private JTextArea displayArea;
    private JScrollPane scrollPane;

    public Building() {
        setTitle("Building");
        setSize(400, 400);
        setLayout(new BorderLayout(10, 10));
        ((JComponent) getContentPane()).setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

        JPanel gridPanel = new JPanel(new GridLayout(5, 2, 5, 10));

        gridPanel.add(new JLabel("Name:"));
        name = new JTextField(15);
        gridPanel.add(name);

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

        displayArea = new JTextArea(10, 50);
        displayArea.setText("Fill all the fields to add building.\nFill only Number to Search and Delete.");
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
            String buildingNumber = number.getText();
            if (buildingNumber.isEmpty()) {
                displayArea.setText("Enter a building number to search.");
                return;
            }
            int index = findBuilding(buildingNumber);
            if (index == -1) {
                displayArea.setText("Building not found.");
            } else {
                displayBuilding(buildings.get(index));
            }
        }
        if (e.getSource() == save) {
            String[] building = {number.getText(), name.getText(), capacity.getText()};
            for (String value : building) {
                if (value.isEmpty()) {
                    displayArea.setText("Fill all the fields to add building.");
                    return;
                }
            }
            if (findBuilding(building[0]) != -1) {
                displayArea.setText("A building with this number already exists.");
                return;
            }
            buildings.add(building);
            displayBuilding(building);
            displayArea.append("\nBuilding added.");
        }
        if (e.getSource() == delete) {
            String buildingNumber = number.getText();
            if (buildingNumber.isEmpty()) {
                displayArea.setText("Enter a building number to delete.");
                return;
            }

            int index = findBuilding(buildingNumber);
            if (index == -1) {
                displayArea.setText("Building not found.");
            } else {
                buildings.remove(index);
                displayArea.setText("Building deleted.");
            }
        }
    }

    private int findBuilding(String buildingNumber) {
        for (int i = 0; i < buildings.size(); i++) {
            if (buildings.get(i)[0].equals(buildingNumber)) {
                return i;
            }
        }
        return -1;
    }

    private void displayBuilding(String[] building) {
        displayArea.setText(String.format("Number : %s\nName : %s\nCapacity : %s",
                building[0], building[1], building[2]));
    }
}
