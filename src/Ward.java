import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ward extends JFrame implements ActionListener {
    private JTextField number;
    private JTextField name;
    private JTextField description;

    private JButton search;
    private JButton save;
    private JButton delete;
    private JTextArea displayArea;
    private JScrollPane scrollPane;

    public Ward() {
        setTitle("Ward");
        setSize(400, 400);
        setLayout(new BorderLayout(10, 10));
        ((JComponent) getContentPane()).setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

        JPanel gridPanel = new JPanel(new GridLayout(5, 2, 5, 10));

        gridPanel.add(new JLabel("Number:"));
        number = new JTextField(15);
        gridPanel.add(number);

        gridPanel.add(new JLabel("Name:"));
        name = new JTextField(15);
        gridPanel.add(name);

        gridPanel.add(new JLabel("Description:"));
        description = new JTextField(15);
        gridPanel.add(description);

        save = new JButton("Save");
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
        displayArea.setText("Fill all the fields to Save the ward.\nFill only Number to Search and Delete.");
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

        }
        if (e.getSource() == save) {

        }
        if (e.getSource() == delete) {

        }
    }
}
