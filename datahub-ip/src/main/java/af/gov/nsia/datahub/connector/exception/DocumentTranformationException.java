/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.gov.nsia.datahub.connector.exception;

import javax.xml.transform.SourceLocator;
import javax.xml.transform.TransformerException;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author hp 2018
 */
public class DocumentTranformationException extends TransformerException {

    public DocumentTranformationException(String message) {
        super(message);
    }

    @Override
    public String getLocationAsString() {
        return super.getLocationAsString();
    }

    @Override
    public String getMessageAndLocation() {
        return super.getMessageAndLocation();
    }

    @Override
    public synchronized Throwable initCause(Throwable cause) {
        return super.initCause(cause);
    }

    @Override
    public Throwable getCause() {
        return super.getCause();
    }

    @Override
    public Throwable getException() {
        return super.getException();
    }

    @Override
    public SourceLocator getLocator() {
        return super.getLocator();
    }

}
