package awsapiimporter.handlers;

import awsapiimporter.ApiEndpointsModel;
import burp.api.montoya.http.message.params.HttpParameterType;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.logging.Logging;

public class AwsEndpoint {    

    public static String getApiName(HttpRequest req, Logging logging) {
        ApiEndpointsModel apiEndpointsModel = ApiEndpointsModel.getInstance();
        String name;
        //Run through a series of different conditions that determine an AWS endpoint and
        //set the name.

        //Does the request have the action BODY parameter?
        if (apiEndpointsModel.getCaptureAction() && req.hasParameter("action", HttpParameterType.BODY)){
            name = req.parameterValue("action", HttpParameterType.BODY);
            return name;
        }
        //Does the request have the X-Amz-Target header?
        if (apiEndpointsModel.getCaptureTarget() && (name = req.headerValue("X-Amz-Target")) != null){
            name = name.split("\\.")[1];
            return name;         
        }
        //Is the User-Agent AWS CLI?
        if (apiEndpointsModel.getCaptureCLI() && req.hasHeader("User-Agent")) {
            String hv = req.headerValue("User-Agent");
            if (hv.contains("aws-cli/")){
                String[] parts;
                parts = hv.split(" ");
                for (String part : parts){
                    if (part.contains("command")){
                        name = part.split("\\.")[1];
                        return name;
                    }
                }                
            }
        }
        if (apiEndpointsModel.getCaptureURI() && req.method().matches("^GET|^POST|^PUT|^PATCH|^DELETE")) {            
            name = req.method().strip() + " - " + req.pathWithoutQuery();
            return name;          
        }     
        
        return null;
    }
}
