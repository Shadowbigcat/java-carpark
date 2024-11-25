import javax.swing.*;
import java.awt.*;


public class AdminScreenGUI extends JFrame {
    private JPanel mainPanel;
    private JScrollPane scrollPane;
    private HandleCSV csvHandler;
    private String[] carparkData;

    public AdminScreenGUI() {
        setTitle("Admin Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        
        scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane);
        
        csvHandler = new HandleCSV();
        loadCSVData();
    }

    private void loadCSVData() {
        csvHandler.read("carpark");
        carparkData = App.carparkData;
        
        for (String row : carparkData) {
            addRowPanel(row);
        }
    }

    private void addRowPanel(String rowData) {
        JPanel rowPanel = new JPanel();
        rowPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        rowPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        
        JLabel dataLabel = new JLabel(rowData);
        rowPanel.add(dataLabel);
        
        JButton editButton = new JButton("Edit");
        editButton.addActionListener(e -> {
            String newData = JOptionPane.showInputDialog(this, "Edit data:", rowData);
            if (newData != null) {
                dataLabel.setText(newData);
                updateData();
            }
        });
        
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            mainPanel.remove(rowPanel);
            mainPanel.revalidate();
            mainPanel.repaint();
            updateData();
        });
        
        rowPanel.add(editButton);
        rowPanel.add(deleteButton);
        mainPanel.add(rowPanel);
        mainPanel.revalidate();
    }

    private void updateData() {
        String[] newData = new String[mainPanel.getComponentCount()];
        for (int i = 0; i < mainPanel.getComponentCount(); i++) {
            JPanel panel = (JPanel) mainPanel.getComponent(i);
            JLabel label = (JLabel) panel.getComponent(0);
            newData[i] = label.getText();
        }
        csvHandler.update(newData);
    }
}
