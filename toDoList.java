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
        doneListModel = new DefaultListModel<>();
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
        JButton changeThemeButton = new JButton("Dark theme");

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
        changeThemeButton.addActionListener(e ->{
            changeTheme(addButton, doneButton, removeButton, changeThemeButton, frame, toDoLabel, doneLabel, panel, titlePanel, clearButton, toDoScrollPane, doneScrollPane);
        });
        
        panel.add(changeThemeButton);
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

    public void changeTheme(JButton addButton, JButton doneButton, JButton removeButton, JButton changeThemeButton, JFrame frame, JLabel toDoLabel, JLabel doneLabel, JPanel panel, JPanel titlePanel, JButton clearButton, JScrollPane toDoScrollPane, JScrollPane doneScrollPane){

        if(changeThemeButton.getText() == "Dark theme"){
            frame.getContentPane().setBackground(Color.BLACK);

            toDoScrollPane.getViewport().getView().setBackground(Color.GRAY);
            doneScrollPane.getViewport().getView().setBackground(Color.GRAY);

            toDoLabel.setBackground(Color.BLACK);
            toDoLabel.setForeground(Color.RED);

            doneLabel.setOpaque(true);
            doneLabel.setBackground(Color.BLACK);
            doneLabel.setForeground(Color.WHITE);

            addButton.setBackground(Color.BLACK);
            addButton.setForeground(Color.WHITE);

            doneButton.setBackground(Color.BLACK);
            doneButton.setForeground(Color.WHITE);

            removeButton.setBackground(Color.BLACK);
            removeButton.setForeground(Color.WHITE);

            clearButton.setBackground(Color.BLACK);
            clearButton.setForeground(Color.WHITE);

            changeThemeButton.setBackground(Color.BLACK);
            changeThemeButton.setForeground(Color.WHITE);
            changeThemeButton.setText("Light theme");

            panel.setBackground(Color.BLACK);

            titlePanel.setBackground(Color.BLACK);
            } else {
            frame.getContentPane().setBackground(Color.WHITE);

            toDoScrollPane.getViewport().getView().setBackground(Color.WHITE);
            doneScrollPane.getViewport().getView().setBackground(Color.WHITE);

            toDoLabel.setBackground(Color.WHITE);
            toDoLabel.setForeground(Color.RED);

            doneLabel.setBackground(Color.WHITE);
            doneLabel.setForeground(Color.BLACK);

            addButton.setBackground(Color.WHITE);
            addButton.setForeground(Color.BLACK);

            doneButton.setBackground(Color.WHITE);
            doneButton.setForeground(Color.BLACK);

            removeButton.setBackground(Color.WHITE);
            removeButton.setForeground(Color.BLACK);

            clearButton.setBackground(Color.WHITE);
            clearButton.setForeground(Color.BLACK);

            changeThemeButton.setBackground(Color.WHITE);
            changeThemeButton.setForeground(Color.BLACK);
            changeThemeButton.setText("Dark theme");

            panel.setBackground(Color.WHITE);

            titlePanel.setBackground(Color.WHITE);

        }

    }
    
        public static void main(String[] args) {
            new toDoList();   
    }
}