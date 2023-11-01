package source.main.java.ui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;


public class DiscoveryUI {
    // Main UI Components
    private JFrame mainFrame;
    private JButton startScanButton;
    private JButton stopScanButton;
    private JLabel scanStatusLabel;
    private JProgressBar scanProgressBar;
    private JTable deviceTable;
    private JScrollPane deviceTableScrollPane;

    // For Settings/Options
    private JTextField startIPField;
    private JTextField endIPField;
    private JComboBox<String> scanSpeedComboBox;
    private JTextField portRangeField;
    private JCheckBox httpCheckBox;

    // For Threading Controls
    private JLabel threadCountLabel;
    private JSpinner maxThreadsSpinner;

    // For Logs/Output Console
    private JTextArea logTextArea;
    private JScrollPane logScrollPane;
    private JButton exportLogsButton;



    // Constructor
    public DiscoveryUI() {
        initializeUI();
    }

    private void initializeUI() {

        // Main Frame
        mainFrame = new JFrame("Network Discovery Tool");
        mainFrame.setSize(600, 400);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // Settings
        JPanel settingsPanel = new JPanel();
        settingsPanel.setBorder(new TitledBorder("Settings"));

        startIPField = new JTextField(10);
        endIPField = new JTextField(10);
        scanSpeedComboBox = new JComboBox<>(new String[] {"Fast", "Normal", "Thorough"});
        portRangeField = new JTextField(10);
        httpCheckBox = new JCheckBox("HTTP");

        // Layout the settings
        settingsPanel.add(new JLabel("IP Range"));
        settingsPanel.add(startIPField);
        settingsPanel.add(new JLabel("to"));
        settingsPanel.add(endIPField);
        settingsPanel.add(new JLabel("Scan Speed"));
        settingsPanel.add(scanSpeedComboBox);
        settingsPanel.add(new JLabel("Port Range: "));
        settingsPanel.add(portRangeField);
        settingsPanel.add(httpCheckBox);

        // Threading Controls
        JPanel threadingPanel = new JPanel();
        threadingPanel.setBorder(new TitledBorder("Threading Controls"));

        threadCountLabel = new JLabel("Threads Running: 0");
        maxThreadsSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));  // Sample values

        threadingPanel.add(threadCountLabel);
        threadingPanel.add(new JLabel("Max Threads:"));
        threadingPanel.add(maxThreadsSpinner);

        // Logs/Output Console
        JPanel logPanel = new JPanel();
        logPanel.setLayout(new BorderLayout());

        logTextArea = new JTextArea(10, 50);
        logScrollPane = new JScrollPane(logTextArea);
        exportLogsButton = new JButton("Export Logs");
        logPanel.add(logScrollPane, BorderLayout.CENTER);
        logPanel.add(exportLogsButton, BorderLayout.SOUTH);


        // Scan Controls
        JPanel scanControlsPanel = new JPanel();
        startScanButton = new JButton("Start Scan");
        stopScanButton = new JButton("Stop Scan");
        scanStatusLabel = new JLabel("Status: Ready");
        scanProgressBar = new JProgressBar();
        // Adding scan controls to the panel
        scanControlsPanel.add(startScanButton);
        scanControlsPanel.add(stopScanButton);
        scanControlsPanel.add(scanStatusLabel);
        scanControlsPanel.add(scanProgressBar);



        // Network Display
        String[] columnNames = {"IP Address", "Hostname", "MAC Address"};
        Object[][] data = {}; // Placeholder data; this will be filled dynamically
        deviceTable = new JTable(data, columnNames);
        deviceTableScrollPane = new JScrollPane(deviceTable);


        JPanel westPanel = new JPanel();
        westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));
        westPanel.add(settingsPanel);
        westPanel.add(threadingPanel);

// Logs/Output Console
        logPanel.setLayout(new BorderLayout());
        logPanel.add(logScrollPane, BorderLayout.CENTER);
        logPanel.add(exportLogsButton, BorderLayout.SOUTH);

// Adding components to the main frame
        mainFrame.add(scanControlsPanel, BorderLayout.NORTH);
        mainFrame.add(deviceTableScrollPane, BorderLayout.CENTER);
        mainFrame.add(westPanel, BorderLayout.WEST);
        mainFrame.add(logPanel, BorderLayout.SOUTH);

        mainFrame.setVisible(true);


    }

}