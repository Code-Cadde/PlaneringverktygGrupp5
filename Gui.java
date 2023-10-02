import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Gui {
    private DefaultListModel<String> listModel;
    private int taskCount = 1;

    public Gui() {
        JFrame frame = new JFrame("To-Do List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);// storlek

        listModel = new DefaultListModel<>();// Skapar ett nytt listmodellobjekt
        JList<String> toDoList = new JList<>(listModel);

        Font biggerFont = new Font(toDoList.getFont().getName(), Font.PLAIN, 25);
        toDoList.setFont(biggerFont);

        JScrollPane scrollPane = new JScrollPane(toDoList);

        JPanel panel = new JPanel();

        JTextField taskField = new JTextField(20);
        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Remove");

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String task = taskField.getText();
                if (!task.isEmpty()) {

                    listModel.addElement(taskCount + ". " + task);
                    taskField.setText("");
                    taskCount++;
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = toDoList.getSelectedIndex();
                if (selectedIndex != -1) {
                    listModel.remove(selectedIndex);
                }
            }
        });

        panel.add(taskField);
        panel.add(addButton);
        panel.add(removeButton);

        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(panel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        new Gui();

    }
}
