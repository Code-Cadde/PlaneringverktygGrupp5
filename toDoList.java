import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.GridLayout;

public class toDoList {
    private DefaultListModel<String> toDoListModel;
    private DefaultListModel<String> doneListModel;

    public toDoList() {
        JFrame frame = new JFrame("To-Do List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setResizable(true);

        toDoListModel = new DefaultListModel<>();
        JList<String> toDoList = new JList<>(toDoListModel);
        doneListModel=new DefaultListModel<>();
        JList<String> doneList = new JList<>(doneListModel);

        Font biggerFont = new Font(toDoList.getFont().getName(), Font.PLAIN, 25);
        toDoList.setFont(biggerFont);
        doneList.setFont(biggerFont);

        JScrollPane toDoScrollPane = new JScrollPane(toDoList);
        JScrollPane doneScrollPane = new JScrollPane(doneList);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, toDoScrollPane, doneScrollPane);
        splitPane.setResizeWeight(0.5);

        JPanel panel = new JPanel();

        JLabel toDoLabel = new JLabel("To do", JLabel.CENTER);
        toDoLabel.setLayout(new BorderLayout());
        toDoLabel.setOpaque(true);
        toDoLabel.setForeground(Color.RED);

        JLabel doneLabel = new JLabel("Done", JLabel.CENTER);
        doneLabel.setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();

        JTextField taskField = new JTextField(20);
        JButton addButton = new JButton("Add");
        JButton doneButton = new JButton("Done");
        JButton clearButton = new JButton("Clear");
        JButton removeButton = new JButton("Remove");

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String task = taskField.getText();
                if (!task.isEmpty()) {
                    toDoListModel.addElement(task);
                    taskField.setText("");
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

        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex=0;
                if (selectedIndex != -1) {
                    doneListModel.removeAllElements();
                }
            }
        });
        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int toDoIndex=toDoList.getSelectedIndex();
                int doneIndex=doneList.getSelectedIndex();
                if (toDoIndex != -1) {
                    toDoListModel.remove(toDoIndex);              
                }
                if (doneIndex!=-1){
                    doneListModel.remove(doneIndex);
            }
        }
        });
        
        panel.add(taskField);
        panel.add(addButton);
        panel.add(doneButton);
        panel.add(removeButton);
        panel.add(clearButton);

        titlePanel.setLayout(new GridLayout(1,2));
        
        titlePanel.add(toDoLabel);
        titlePanel.add(doneLabel);

        frame.getContentPane().add(titlePanel, BorderLayout.NORTH);
        frame.getContentPane().add(splitPane, BorderLayout.CENTER);
        frame.getContentPane().add(panel, BorderLayout.SOUTH);
        frame.setVisible(true);
        
    }
    public static void main(String[] args) {
        new toDoList();   
    }
}
