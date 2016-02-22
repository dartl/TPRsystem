package ru.eltech.tprsystem.core.tasking;

import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.io.*;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Semyon on 13.02.2016.
 */
public class TaskRunner extends Thread {

    @NotNull
    private UUID taskId = UUID.randomUUID();

    @NotNull
    private String taskLoc;

    @Nullable
    private InputStreamHandler.InputHandler inputHandler;

    @NotNull
    private DataProvider dataProvider;

    @NotNull
    private String taskName;

    @NotNull
    private Date timestamp;

    public TaskRunner(@NotNull final String taskName, @NotNull final String taskLoc, @NotNull final DataProvider dataProvider) {
        this.taskName = taskName;
        this.taskLoc = taskLoc;
        this.dataProvider = dataProvider;
        this.timestamp = new Date();
    }

    public void setInputHandler(@Nullable final InputStreamHandler.InputHandler inputHandler) {
        this.inputHandler = inputHandler;
    }

    @Override
    public void run() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Thread.sleep(1000);
            Process process = runtime.exec(taskLoc);

            InputStream inputStream = process.getInputStream();
            OutputStream outputStream = process.getOutputStream();

            InputStreamHandler handler = new InputStreamHandler(inputStream, inputHandler);
            handler.start();

            String data = dataProvider.getData();
            OutputStreamWriter writer = new OutputStreamWriter(outputStream);
            writer.write(data + "\n");
            writer.flush();
            while (process.isAlive()) {

            }
            handler.setRunning(false);

            handler.join();
            Thread.sleep(3000);
            inputHandler.onFinish();
        } catch (IOException e) {
            System.err.println("Error occured: ");
            e.printStackTrace(new PrintStream(System.err));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getTaskName() {
        return taskName;
    }

    public DataProvider getDataProvider() {
        return dataProvider;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public UUID getTaskId() {
        return taskId;
    }

    public void setTaskId(final UUID taskId) {
        this.taskId = taskId;
    }

}
