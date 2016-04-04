package ru.eltech.tprsystem.web.task;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by Semyon on 27.03.2016.
 */
public class TaskDefinition {

    private List<String> variables;

    private String definition;

    private String title;

    private String path;

    public TaskDefinition(final List<String> variables, final String definition, final String title, final String path) {
        this.variables = variables;
        this.definition = definition;
        this.title = title;
        this.path = path;
    }

    public static TaskDefinition parseJson(final String path, final JSONObject jsonObject) {
        JSONArray variables = jsonObject.optJSONArray("variables");
        String definition = jsonObject.optString("definition");
        String title = jsonObject.optString("title");

        Iterable<Object> iterable = variables::iterator;
        Stream<Object> targetStream = StreamSupport.stream(iterable.spliterator(), false);
        ArrayList<String> vars = targetStream.collect(ArrayList<String>::new, (strings, o) -> strings.add(o.toString()), ArrayList::addAll);

        return new TaskDefinition(vars, definition, title, path);
    }

    public List<String> getVariables() {
        return variables;
    }

    public void setVariables(final List<String> variables) {
        this.variables = variables;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(final String definition) {
        this.definition = definition;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(final String path) {
        this.path = path;
    }

}
