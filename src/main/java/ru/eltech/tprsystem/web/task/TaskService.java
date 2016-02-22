package ru.eltech.tprsystem.web.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.eltech.tprsystem.core.tasking.InputStreamHandler;
import ru.eltech.tprsystem.core.tasking.TaskRunner;
import ru.eltech.tprsystem.web.history.HistoryItem;
import ru.eltech.tprsystem.web.history.HistoryService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Semyon on 21.02.2016.
 */
@Service
public class TaskService {

    @Autowired
    private HistoryService historyService;

    private final Lock lock = new ReentrantLock();

    private List<TaskRunner> pendingTask = Collections.synchronizedList(new ArrayList<>());

    public void startTask(final String taskName, final String taskPath, final String data) {
        final TaskRunner taskRunner = new TaskRunner(taskName, taskPath, () -> data);
        wrapLock(() -> {
            pendingTask.add(taskRunner);
            taskRunner.setInputHandler(new Callback() {
                @Override
                public void onFinish() {
                    wrapLock(() -> pendingTask.remove(taskRunner));
                    HistoryItem historyItem = new HistoryItem(taskRunner.getTaskId(), data, toString(), taskRunner.getTaskName());
                    historyService.addItem(historyItem);
                }
            });
            taskRunner.start();
        });
    }

    public List<TaskPresentation> getTasks() {
        List<TaskPresentation> tasks = new ArrayList<>();
        wrapLock(() -> {
            for (TaskRunner taskRunner : pendingTask) {
                tasks.add(new TaskPresentation(taskRunner.getTaskId(), taskRunner.getTaskName(), taskRunner.getDataProvider().getData(), taskRunner.getTimestamp()));
            }
        });
        return tasks;
    }

    private void wrapLock(final Runnable runnable) {
        try {
            lock.lock();
            runnable.run();
        } finally {
            lock.unlock();
        }
    }

    private abstract class Callback implements InputStreamHandler.InputHandler {

        private StringBuilder sb = new StringBuilder();

        @Override
        public void onInput(final String input) {
            sb = sb.append(input);
        }

        @Override
        public String toString() {
            return sb.toString();
        }

    }

}
