import javax.swing.*;
import java.awt.*;


public class AdminScreenGUI extends JFrame {
    private JPanel mainPanel;
    private JPanel contentPanel;
    private JScrollPane scrollPane;
    private JTextField searchField;
    private HandleCSV csvHandler;
    private String[] carparkData;

    public AdminScreenGUI() {
        setTitle("Admin Dashboard");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchField = new JTextField(30);
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> performSearch());
        searchPanel.add(new JLabel("Search: "));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // Content panel with scroll
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        mainPanel.add(searchPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        
        add(mainPanel);
        
        csvHandler = new HandleCSV();
        loadCSVData();
    }

    private void performSearch() {
        String searchTerm = searchField.getText().toLowerCase();
        contentPanel.removeAll();
        
        for (String row : carparkData) {
            if (row.toLowerCase().contains(searchTerm)) {
                addRowPanel(row);
            }
        }
        
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    private void loadCSVData() {
        csvHandler.read("carpark");
        carparkData = App.carparkData;
        
        for (String row : carparkData) {
            addRowPanel(row);
        }
    }

    private void addRowPanel(String rowData) {
        JPanel rowPanel = new JPanel(new BorderLayout());
        rowPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        rowPanel.setPreferredSize(new Dimension(scrollPane.getViewport().getWidth(), 50));
        
        JPanel dataPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel dataLabel = new JLabel(rowData);
        dataPanel.add(dataLabel);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");
        
        editButton.addActionListener(e -> {
            String newData = JOptionPane.showInputDialog(this, "Edit data:", rowData);
            if (newData != null) {
                dataLabel.setText(newData);
                updateData();
            }
        });
        
        deleteButton.addActionListener(e -> {
            contentPanel.remove(rowPanel);
            contentPanel.revalidate();
            contentPanel.repaint();
            updateData();
        });
        
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        
        rowPanel.add(dataPanel, BorderLayout.CENTER);
        rowPanel.add(buttonPanel, BorderLayout.EAST);
        
        contentPanel.add(rowPanel);
        contentPanel.revalidate();
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
