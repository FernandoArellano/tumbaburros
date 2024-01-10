package com.example.tumbaburros.designpatterns.behavioral.chainofresponsability;

/*
    the object in the chain will decide who will be processing the request and wheter the request is required
    to be sent to the next object in the chain or not
    Atm, bills of 50 then 20 then 10
 */
public class ChainofResponsibilityClient {

    public static void line(){
        System.out.println("----------------------------------------");
    }

    private static Logger doChaining(){
        Logger consoleLogger = new ConsoleBasedLogger(Logger.OUTPUTINFO);

        Logger errorLogger = new ErrorBasedLogger(Logger.ERRORINFO);
        consoleLogger.setNextLevelLogger(errorLogger);

        Logger debugLogger = new DebugBasedLogger(Logger.DEBUGINFO);
        errorLogger.setNextLevelLogger(debugLogger);

        return consoleLogger;
    }
    public static void main(String args[]){
        Logger chainLogger= doChaining();

        chainLogger.logMessage(Logger.OUTPUTINFO, "Enter the sequence of values ");
        chainLogger.logMessage(Logger.ERRORINFO, "An error is occured now");
        chainLogger.logMessage(Logger.DEBUGINFO, "This was the error now debugging is compeled");

        line();

        AtmDispenseChain atm = new AtmDispenseChain();
        atm.getC1().dispense(new Currency(1810)); line();
    }
}