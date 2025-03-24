package awsapiimporter.ui;

import awsapiimporter.ApiEndpointsModel;
import awsapiimporter.ui.APIImporterUIHelpers.*;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ApiImporterConfigPanel extends JPanel {
    private ApiEndpointsModel apiEndpointsModel;
    private final String description = "API Import Settings";

    public ApiImporterConfigPanel() {

        this.apiEndpointsModel = ApiEndpointsModel.getInstance();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(new DescriptionPanel(description));
        
        JCheckBox cb_enabled = new JCheckBox();
        JPanel enabled_panel = createRowPanel(cb_enabled, "API Importer Enabled");
        cb_enabled.setSelected(apiEndpointsModel.getCaptureEnabled());
        cb_enabled.addActionListener(e -> {
            apiEndpointsModel.setCaptureEnabled(cb_enabled.isSelected());
        });

        JCheckBox cb_param = new JCheckBox();
        JPanel param_panel = createRowPanel(cb_param, "Import APIs by action parameter.");
        cb_param.setSelected(apiEndpointsModel.getCaptureAction());

        JCheckBox cb_header = new JCheckBox();
        JPanel header_panel = createRowPanel(cb_header, "Import APIs by X-Amz-Target header.");
        cb_header.setSelected(apiEndpointsModel.getCaptureTarget());

        JCheckBox cb_ua = new JCheckBox();
        JPanel ua_panel = createRowPanel(cb_ua, "Import APIs by aws/cli User-Agent.");
        cb_ua.setSelected(apiEndpointsModel.getCaptureCLI());

        JCheckBox cb_uri = new JCheckBox();
        JPanel uri_panel = createRowPanel(cb_uri, "Import APIs by any GET, POST, PUT, PATCH, DELETE from in-scope endpoints.");
        cb_uri.setSelected(apiEndpointsModel.getCaptureURI());

        cb_enabled.addActionListener(e -> {
            apiEndpointsModel.setCaptureEnabled(cb_enabled.isSelected());
            cb_param.setEnabled(cb_enabled.isSelected());
            cb_header.setEnabled(cb_enabled.isSelected());
            cb_ua.setEnabled(cb_enabled.isSelected());
            cb_uri.setEnabled(cb_enabled.isSelected());            
        });

        this.add(enabled_panel);
        this.add(param_panel);
        this.add(header_panel);
        this.add(ua_panel);
        this.add(uri_panel);
    }

    private static JPanel createRowPanel(JCheckBox mCheckBox, String labelText) {

        JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        JLabel mLabel = new JLabel(labelText);
        mCheckBox.setPreferredSize(new Dimension(30, 25));
        rowPanel.add(mCheckBox);
        rowPanel.add(mLabel);
        
        return rowPanel;
    }
    
}
