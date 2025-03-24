package awsapiimporter;

import javax.swing.table.DefaultTableModel;

public final class ApiEndpointsModel extends DefaultTableModel {
    private static ApiEndpointsModel INSTANCE;
    private Boolean captureEnabled = true;
    private Boolean captureAction = true;
    private Boolean captureTarget = true;
    private Boolean captureCLI = true;
    private Boolean captureURI = false;

    private ApiEndpointsModel(){
        this.addColumn("API/URI");
    }

    //Make this a singleton clase by returning a single instance to all callers.
    public static ApiEndpointsModel getInstance() {
        if(INSTANCE == null){
            INSTANCE = new ApiEndpointsModel();            
        }
        return INSTANCE;
    }
    
    public boolean addRowIfNotExists(String api) {
        boolean exists = false;
        for (int i = 0; i < this.getRowCount(); i++) {
            if (this.getValueAt(i, 0).equals(api)) {
                exists = true;
                break;
            }
        }

        if (!exists) {
            this.addRow(new Object[]{api});
            return true;
        }

        return false;
    }
    
    public void setCaptureEnabled(Boolean val){
        captureEnabled = val;
    }    

    public void setCaptureAction(Boolean val){
        captureAction = val;
    }

    public void setCaptureTarget(Boolean val){
        captureTarget = val;
    }

    public void setCaptureCLI(Boolean val){
        captureCLI = val;
    }

    public void setCaptureURI(Boolean val){
        captureURI = val;
    }

    public Boolean getCaptureEnabled(){
        return captureEnabled;
    }

    public Boolean getCaptureAction(){
        return captureAction;
    }
    
    public Boolean getCaptureTarget(){
        return captureTarget;
    }

    public Boolean getCaptureCLI(){
        return captureCLI;
    }

    public Boolean getCaptureURI(){
        return captureURI;
    }
}
