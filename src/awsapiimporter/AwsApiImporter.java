package awsapiimporter;

import javax.swing.JScrollPane;

import awsapiimporter.handlers.ApiImporterRH;
import awsapiimporter.ui.ApiImporterTab;
import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;
import burp.api.montoya.proxy.Proxy;

//Burp will auto-detect and load any class that extends BurpExtension.
public class AwsApiImporter implements BurpExtension
{
    //Burp will call initialize() and pass it an instance of MontoyaApi().
    @Override
    public void initialize(MontoyaApi api)
    {
        api.extension().setName("AWS Api Importer");
        Proxy proxy = api.proxy();        
        //Register our custom proxy request handler.     
        proxy.registerRequestHandler(new ApiImporterRH(api));         
        
        ApiImporterTab mTab = new ApiImporterTab();
        api.userInterface().applyThemeToComponent(mTab);
        api.userInterface().registerSuiteTab("API Importer", new JScrollPane(mTab));
    }
}