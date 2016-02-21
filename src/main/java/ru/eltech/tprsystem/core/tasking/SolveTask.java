package ru.eltech.tprsystem.core.tasking;

public class SolveTask implements InputStreamHandler.InputHandler {
    private String result;
    private boolean finished;
    private final Object monitor;

    public SolveTask(final Object monitor) {
        this.monitor = monitor;
    }

    @Override
    public void onInput(final String input) {
        this.result = input;
        synchronized (monitor) {
            this.finished = true;
            monitor.notifyAll();
        }
    }

    public String getResult() {
        return result;
    }

    public boolean isFinished() {
        return finished;
    }
}
