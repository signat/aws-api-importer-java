package awsapiimporter.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class APIImporterUIHelpers {
    public static class PanelSeperator extends JPanel {
        public PanelSeperator(){
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            this.add(Box.createRigidArea(new Dimension(0, 20)));
            this.add(new JSeparator());
            this.add(Box.createRigidArea(new Dimension(0, 20)));
            this.setAlignmentX(LEFT_ALIGNMENT);        
        }
    }

    public static class DescriptionPanel extends JPanel {
        public DescriptionPanel(String description){
            super(new FlowLayout(FlowLayout.LEFT, 0, 0));
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            JLabel description_label = new JLabel(description);
            description_label.setFont(description_label.getFont().deriveFont(Font.BOLD));
            panel.add(description_label);
            panel.add(Box.createRigidArea(new Dimension(0, 15)));
            this.add(panel);
        }
    }
}
