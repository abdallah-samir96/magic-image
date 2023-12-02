package com.example.magicimage.service.impl;

import org.im4java.process.ProcessEvent;
import org.im4java.process.ProcessEventListener;

public class FileListenerImpl implements ProcessEventListener {

    private Process iProcess     = null;
    private boolean isTerminated = false;
    @Override
    public void processInitiated(ProcessEvent processEvent) {
        System.out.println("processInitiated");
    }

    @Override
    public void processStarted(ProcessEvent processEvent) {
        System.out.println("processStarted");
        isTerminated = false;
        iProcess = processEvent.getProcess();
    }

    @Override
    public void processTerminated(ProcessEvent processEvent) {
        System.err.println("process terminated");
        isTerminated = true;
        if (processEvent.getException() != null) {
            Exception e = processEvent.getException();
            System.err.println("Process terminated with: " + e.getMessage());
        } else {
            System.out.println("async process terminated with code: " + processEvent.getReturnCode());
        }

    }

    public boolean isRunning() {
        return !isTerminated;
    }

    public void destroy() {
        try {
            synchronized(iProcess) {
                iProcess.destroy();
            }
        } catch (Exception e) {
            System.out.println("From destroy!!!!");
            e.printStackTrace();
        }
    }
}
