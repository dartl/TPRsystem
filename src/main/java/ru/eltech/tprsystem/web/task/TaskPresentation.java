package ru.eltech.tprsystem.web.task;

import java.util.Date;

/**
 * Created by Semyon on 21.02.2016.
 */
public class TaskPresentation {

    private String inputData;

    private String taskIdentifier;

    private Date timestamp;

    public TaskPresentation(final String taskIdentifier, final String inputData, final Date timestamp) {
        this.inputData = inputData;
        this.taskIdentifier = taskIdentifier;
        this.timestamp = timestamp;
    }

    public String getInputData() {
        return inputData;
    }

    public void setInputData(final String inputData) {
        this.inputData = inputData;
    }

    public String getTaskIdentifier() {
        return taskIdentifier;
    }

    public void setTaskIdentifier(final String taskIdentifier) {
        this.taskIdentifier = taskIdentifier;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final Date timestamp) {
        this.timestamp = timestamp;
    }

}
