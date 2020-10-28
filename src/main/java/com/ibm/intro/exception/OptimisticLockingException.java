package com.ibm.intro.exception;

/**
 * Is thrown when a dirty write was about to be performed
 */
public class OptimisticLockingException extends Exception {

    /**
     * Instantiates a OptimisticLockingException with a message
     * @param message the exception message
     */
    public OptimisticLockingException(String message){
        super(message);
    }
}
