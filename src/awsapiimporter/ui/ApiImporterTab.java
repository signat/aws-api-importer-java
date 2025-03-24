package awsapiimporter.ui;

import awsapiimporter.ui.APIImporterUIHelpers.*;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class ApiImporterTab extends JPanel {
    private ApiEndpointPanel endpoint_panel;
    private ApiImporterConfigPanel config_panel;    

    public ApiImporterTab () {
        super(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JPanel mainPanel = new JPanel();
        endpoint_panel = new ApiEndpointPanel();
        config_panel = new ApiImporterConfigPanel();

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        
        endpoint_panel.setAlignmentX(LEFT_ALIGNMENT);        
        config_panel.setAlignmentX(LEFT_ALIGNMENT);

        mainPanel.add(endpoint_panel);
        mainPanel.add(new PanelSeperator());       
        mainPanel.add(config_panel);
        
        this.add(mainPanel);
            
    }

    
}
