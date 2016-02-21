package ru.eltech.tprsystem.web.history;

import java.util.List;

/**
 * Created by Semyon on 21.02.2016.
 */
public interface HistoryService {

    List<HistoryItem> getHistory();

    void addItem(final HistoryItem historyItem);

}
