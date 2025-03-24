package awsapiimporter.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import awsapiimporter.ApiEndpointsModel;
import awsapiimporter.ui.APIImporterUIHelpers.*;

public class ApiEndpointPanel extends JPanel {
    private final String description = "Captured API Endpoints";
    private ApiEndpointsModel model;
    private JTable table;

    public ApiEndpointPanel() {
        
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(new DescriptionPanel(description));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));

        //Create button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        mJButton removeSelButton = new mJButton("Remove Selected APIs");        
        mJButton removeAllButton = new mJButton("Remove All APIs");

        removeSelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] selectedRows = table.getSelectedRows();
                if (selectedRows.length > 0) {
                    Arrays.sort(selectedRows);
                    for (int i = selectedRows.length - 1; i >= 0; i--) {
                        model.removeRow(selectedRows[i]);
                    }
                }
            }
        });

        removeAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.setRowCount(0);
            }
        });

        buttonPanel.add(removeSelButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 5)));       
        buttonPanel.add(removeAllButton);

        //Create table panel
        model = ApiEndpointsModel.getInstance();
        this.table = new JTable(model) {            
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        JScrollPane tableScrollPane = new JScrollPane(table);

        //Add panels to this panel
        buttonPanel.setAlignmentY(TOP_ALIGNMENT);
        mainPanel.add(buttonPanel);
        tableScrollPane.setAlignmentY(TOP_ALIGNMENT);
        mainPanel.add(Box.createRigidArea(new Dimension(5, 0)));
        mainPanel.add(tableScrollPane);
        this.add(mainPanel);
    }

    private class mJButton extends JButton {
        private Integer w = 160;
        private Integer h = 30;
        private mJButton(String name) {
            this.setText(name);
            this.setMinimumSize(new Dimension(w, h));
            this.setMaximumSize(new Dimension(w, h));
            this.setPreferredSize(new Dimension(w, h));            
        }
    }
}
