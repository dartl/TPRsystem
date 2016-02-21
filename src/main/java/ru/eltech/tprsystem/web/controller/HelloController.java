package ru.eltech.tprsystem.web.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.eltech.tprsystem.core.tasking.SolveTask;
import ru.eltech.tprsystem.core.tasking.TaskRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.eltech.tprsystem.web.history.HistoryService;
import ru.eltech.tprsystem.web.task.TaskService;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping("/")
public class HelloController {

    private static final String PATH = "E:\\development\\qtdev\\build-dme-test-Desktop_Qt_5_5_1_MinGW_32bit-Debug\\debug\\dme-test.exe";

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(final ModelMap model) {
		return "hello";
	}

    @RequestMapping(value = "/solveForm", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public ResponseEntity<Map> test(final @RequestParam("name") String param, final ModelMap model) {
        taskService.startTask("Task" + (new Random().nextInt(1000)), PATH, "Say hello to my little");
        Map json = new HashMap<>();
        json.put("result", "success");
        return new ResponseEntity<Map>(json, HttpStatus.OK);
    }

    @RequestMapping(value = "/status", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Map> getSystemStatus(final ModelMap modelMap) {
        Map json = new HashMap<>();
        json.put("result", "success");
        json.put("history", historyService.getHistory());
        json.put("running", taskService.getTasks());
        return new ResponseEntity<Map>(json, HttpStatus.OK);
    }

}