package ru.eltech.tprsystem.web.task;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Semyon on 27.03.2016.
 */
@Service
public class TaskListService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskListService.class);

    public static final String PATH = "E:\\development\\modules\\";

    private List<TaskDefinition> tasks = new ArrayList<>();

    @PostConstruct
    public void onLoad() throws IOException {
        File modulesDir = new File(PATH);
        File[] modules = modulesDir.listFiles(File::isDirectory);
        Stream.of(modules)
                .map(file -> file.getAbsolutePath() + "\\" + file.list((dir, name) -> "module.json".equals(name))[0])
                .forEach(file -> {
                    try {
                        StringBuilder sb = new StringBuilder();
                        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(file)));) {
                            sb.append(bufferedReader.readLine());
                        }
                        String json = sb.toString();
                        JSONObject jsonObject = new JSONObject(json);
                        File dir = new File(file).getParentFile();
                        String exe = dir.list((unused, name) -> name.endsWith(".exe"))[0];
                        tasks.add(TaskDefinition.parseJson(dir.getAbsolutePath() + "\\" + exe, jsonObject));
                    } catch (IOException e) {
                        LOGGER.error("Error during module initialization: " + e.getMessage(), e);
                    }
                });
    }

    public List<TaskDefinition> getTasks() {
        return tasks;
    }

}