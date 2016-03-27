package ru.eltech.tprsystem.web.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import ru.eltech.tprsystem.web.history.HistoryService;
import ru.eltech.tprsystem.web.task.TaskDefinition;
import ru.eltech.tprsystem.web.task.TaskListService;
import ru.eltech.tprsystem.web.task.TaskService;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping("/")
public class HelloController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private TaskListService taskListService;

	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(final ModelMap model) {
        model.addAttribute("taskDefinitions", taskListService.getTasks());
		return "hello";
	}

    @RequestMapping(value = "/solveForm/{taskId}",
            method = RequestMethod.POST,
            headers = {"content-type=application/x-www-form-urlencoded"},
            produces = "application/json")
    @ResponseBody
    public ResponseEntity<Map> test(final ModelMap model, @PathVariable final int taskId, @RequestBody final MultiValueMap<String, String> values) {

        Map<String, String> vals =  values.entrySet()
                .stream()
                .collect(HashMap::new, (stringStringHashMap, e) -> stringStringHashMap.put(e.getKey(), e.getValue().get(0)), HashMap::putAll);

        TaskDefinition taskDefinition = taskListService.getTasks().get(taskId);
        JSONObject jsonObject = new JSONObject(vals);
        taskService.startTask("Task" + (new Random().nextInt(1000)), taskDefinition.getPath(), jsonObject.toString());
        Map json = new HashMap<>();
        json.put("result", "success");
        return new ResponseEntity<Map>(json, HttpStatus.OK);
    }

    @RequestMapping(value = "/status", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ResponseEntity<Map> getSystemStatus(final ModelMap modelMap) {
        Map json = new HashMap<>();
        json.put("result", "success");
        json.put("history", historyService.getHistory());
        json.put("running", taskService.getTasks());
        return new ResponseEntity<Map>(json, HttpStatus.OK);
    }

}