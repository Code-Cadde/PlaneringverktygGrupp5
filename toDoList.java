import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.GridLayout;


public class toDoList {
    private DefaultListModel<String> toDoListModel;
    private DefaultListModel<String> doneListModel;
    private int taskCount = 1;

    public toDoList() {
        JFrame frame = new JFrame("To-Do List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550, 300);
        frame.setResizable(false);

        toDoListModel = new DefaultListModel<>();
        JList<String> toDoList = new JList<>(toDoListModel);
        doneListModel=new DefaultListModel<>();
        JList<String> doneList = new JList<>(doneListModel);

        Font biggerFont = new Font(toDoList.getFont().getName(), Font.PLAIN, 25);
        toDoList.setFont(biggerFont);
        doneList.setFont(biggerFont);


        JScrollPane toDoScrollPane = new JScrollPane(toDoList);
        JScrollPane doneScrollPane = new JScrollPane(doneList);

        JPanel panel = new JPanel();

        JLabel toDoLabel = new JLabel("To Do");

        JLabel doneLabel = new JLabel("  Done");

        JPanel titlePanel = new JPanel();

        JTextField taskField = new JTextField(20);
        JButton addButton = new JButton("Add");
        JButton doneButton = new JButton("Done");
        JButton removeButton = new JButton("Remove");

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String task = taskField.getText();
                if (!task.isEmpty()) {
                    toDoListModel.addElement(taskCount + ". " + task);
                    taskField.setText("");
                    taskCount++;
                }
            }
        });

        doneButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = toDoList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String selectedTask = toDoListModel.getElementAt(selectedIndex);
                    doneListModel.addElement(selectedTask); 
                    toDoListModel.remove(selectedIndex);
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex=0;
                if (selectedIndex != -1) {
                    doneListModel.removeAllElements();
                }
            }
        });
        
        panel.add(taskField);
        panel.add(addButton);
        panel.add(doneButton);
        panel.add(removeButton);

        titlePanel.setLayout(new GridLayout(1,2));
        
        titlePanel.add(toDoLabel);
        titlePanel.add(doneLabel);

        frame.getContentPane().add(titlePanel, BorderLayout.NORTH);
        frame.getContentPane().add(toDoScrollPane, BorderLayout.WEST);      
        frame.getContentPane().add(doneScrollPane, BorderLayout.EAST);
        frame.getContentPane().add(panel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new toDoList();   
    }
}
