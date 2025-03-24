package awsapiimporter.handlers;

import awsapiimporter.ApiEndpointsModel;
import burp.api.montoya.MontoyaApi;
import burp.api.montoya.logging.Logging;
import burp.api.montoya.proxy.http.InterceptedRequest;
import burp.api.montoya.proxy.http.ProxyRequestHandler;
import burp.api.montoya.proxy.http.ProxyRequestReceivedAction;
import burp.api.montoya.proxy.http.ProxyRequestToBeSentAction;
import burp.api.montoya.repeater.Repeater;

public class ApiImporterRH implements ProxyRequestHandler {
    private final Repeater r;
    private final Logging logging;
    private ApiEndpointsModel apiEndpointsModel;    

    public ApiImporterRH(MontoyaApi api) {
        this.r = api.repeater();
        this.logging = api.logging();        
        //Get instance of singleton APIEndpointsList class..this holds an ArrayList<String> of
        //previously seen api endpoints in memory to prevent sending duplicate api endpoints
        //to Repeater().
        this.apiEndpointsModel = ApiEndpointsModel.getInstance();
    }

    @Override
    public synchronized ProxyRequestReceivedAction handleRequestReceived(InterceptedRequest interceptedRequest) {
        String api_name;
        if (apiEndpointsModel.getCaptureEnabled() && interceptedRequest.isInScope()){
            if ((api_name = AwsEndpoint.getApiName(interceptedRequest, logging)) != null){
                if (apiEndpointsModel.addRowIfNotExists(api_name)){
                    r.sendToRepeater(interceptedRequest, api_name);
                    logging.logToOutput(api_name);                    
                }

            }
        }  
        return ProxyRequestReceivedAction.continueWith(interceptedRequest);
    }

    @Override
    public ProxyRequestToBeSentAction handleRequestToBeSent(InterceptedRequest interceptedRequest) {
        return ProxyRequestToBeSentAction.continueWith(interceptedRequest);
    }

    

}
