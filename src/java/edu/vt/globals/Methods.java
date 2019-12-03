/*
 * Created by Justin Kennedy on 2019.11.16  * 
 * Copyright © 2019 Justin Kennedy. All rights reserved. * 
 */
package edu.vt.globals;

import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/*
This class is created to provide Convenience Class Methods (typed with the "static" keyword")
for all classes in the project to use. This is in support of the Separation of Concerns software
engineering design principle and it prevents verboseness and cluttering in the code. The class
methods provided herein are called as Methods.methodName in any class in the project.
- Balci
 */
public final class Methods {

    /*
    Tell JSF that you want to keep the FacesMessage messages in the Flash scope. 
    --------------------------------------------------------------
    *** This is required when you redirect to show a JSF page. ***
    --------------------------------------------------------------
    Redirecting to show a JSF page involves more than one subsequent requests and
    the messages would die from one request to another if not kept in the Flash scope.
    
    You can invoke this method in any class as: Methods.preserveMessages()
     -Balci 
     */
    public static void preserveMessages() {
        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
    }

    /*
    We display FacesMessage messages using p:growl in an overlay on all JSF pages by including the 
    content of the growlMessages.xhtml within the editableContent of the siteTemplate.xhtml.
    
    In growlMessages.xhtml, we specify showDetail="false" for the SEVERITY_ERROR messages 
    to prevent duplicate display of the same error message generated by the system. 
    Therefore, you should set messageDetail to "" for the SEVERITY_ERROR messages.
    
    You can invoke this method in any class as follows:

        Methods.showMessage("Information", "messageSummary", "messageDetail");
        Methods.showMessage("Warning", "messageSummary", "messageDetail");
        Methods.showMessage("Error", "messageSummary", "");
        Methods.showMessage("Fatal Error", "messageSummary", "messageDetail");
    
    For exception (ex) errors:
        Methods.showMessage("Fatal Error", "messageSummary", "See: " + ex.getMessage());
     */
    public static void showMessage(String messageType, String messageSummary, String messageDetail) {

        switch (messageType) {
            case "Information":
                FacesMessage infoMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, messageSummary, messageDetail);
                FacesContext.getCurrentInstance().addMessage(null, infoMessage);
                break;
            case "Warning":
                FacesMessage warningMessage = new FacesMessage(FacesMessage.SEVERITY_WARN, messageSummary, messageDetail);
                FacesContext.getCurrentInstance().addMessage(null, warningMessage);
                break;
            case "Error":
                FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, messageSummary, messageDetail);
                FacesContext.getCurrentInstance().addMessage(null, errorMessage);
                break;
            case "Fatal Error":
                FacesMessage fatalErrorMessage = new FacesMessage(FacesMessage.SEVERITY_FATAL, messageSummary, messageDetail);
                FacesContext.getCurrentInstance().addMessage(null, fatalErrorMessage);
                break;
            default:
                System.out.print("Message Type is Out of Range!");
        }
    }

    /*
    Return a mutable Map representing the session scope attributes for the current application.
    You can invoke this method in any class as: Methods.sessionMap()
     */
    public static Map sessionMap() {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
    }

}
