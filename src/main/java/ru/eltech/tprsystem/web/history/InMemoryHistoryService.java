package ru.eltech.tprsystem.web.history;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Semyon on 21.02.2016.
 */
@Service
public class InMemoryHistoryService implements HistoryService {

    private int max = 3;

    private final List<HistoryItem> historyItems = Collections.synchronizedList(new ArrayList<>());

    @Override
    public List<HistoryItem> getHistory() {
        List<HistoryItem> items = new ArrayList<>();
        synchronized (historyItems) {
            items.addAll(historyItems);
        }
        return items;
    }

    @Override
    public void addItem(final HistoryItem historyItem) {
        synchronized (historyItems) {
            historyItems.add(historyItem);
            if (historyItems.size() > max) {
                historyItems.remove(0);
            }
        }
    }

}
