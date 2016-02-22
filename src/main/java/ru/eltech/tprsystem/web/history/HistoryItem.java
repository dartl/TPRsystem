package ru.eltech.tprsystem.web.history;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Semyon on 21.02.2016.
 */
public class HistoryItem {

    private UUID taskId = null;

    private String inputData;

    private String outputData;

    private String taskIdentifier;

    private Date timestamp;

    public HistoryItem(final UUID taskId, final String inputData, final String outputData, final String taskIdentifier) {
        this.inputData = inputData;
        this.outputData = outputData;
        this.taskIdentifier = taskIdentifier;
        this.timestamp = new Date();
        this.taskId = taskId;
    }

    public String getInputData() {
        return inputData;
    }

    public void setInputData(final String inputData) {
        this.inputData = inputData;
    }

    public String getOutputData() {
        return outputData;
    }

    public void setOutputData(final String outputData) {
        this.outputData = outputData;
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

    public UUID getTaskId() {
        return taskId;
    }

    public void setTaskId(final UUID taskId) {
        this.taskId = taskId;
    }

}
