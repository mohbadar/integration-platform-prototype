/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.gov.nsia.datahub.odkservice.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 *
 * @author hp 2018
 */
public class FormSubmissionException extends RuntimeException{

    @Override
    public void setStackTrace(StackTraceElement[] stackTrace) {
        super.setStackTrace(stackTrace);
    }

    @Override
    public StackTraceElement[] getStackTrace() {
        return super.getStackTrace(); 
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return super.fillInStackTrace(); 
    }

    @Override
    public void printStackTrace(PrintWriter s) {
        super.printStackTrace(s);
    }

    @Override
    public void printStackTrace(PrintStream s) {
        super.printStackTrace(s); 
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace(); 
    }

    @Override
    public String toString() {
        return super.toString(); 
    }

    @Override
    public synchronized Throwable initCause(Throwable cause) {
        return super.initCause(cause); 
    }

    @Override
    public synchronized Throwable getCause() {
        return super.getCause(); 
    }

    @Override
    public String getLocalizedMessage() {
        return super.getLocalizedMessage(); 
    }

    @Override
    public String getMessage() {
        return super.getMessage(); 
    }
    
    
    
}
