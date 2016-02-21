package ru.eltech.tprsystem.web.controller;

import ru.eltech.tprsystem.core.tasking.SolveTask;
import ru.eltech.tprsystem.core.tasking.TaskRunner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class HelloController {
    private static final String PATH = "E:\\development\\qtdev\\build-dme-test-Desktop_Qt_5_5_1_MinGW_32bit-Debug\\debug\\dme-test.exe";

	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {


		return "hello";
	}

    @RequestMapping(value = "/solveForm",method = RequestMethod.GET)
    public String test(@RequestParam("name") String param, ModelMap model) {
        final Object monitor = new Object();
        SolveTask task = new SolveTask(monitor);
        TaskRunner taskRunner = new TaskRunner(PATH, () -> "Say hello to my little", task);
        taskRunner.start();
        try {
            while (!task.isFinished()) {
                synchronized (monitor) {
                    if (!task.isFinished()) {
                        monitor.wait();
                    }
                }
            }
            String result = task.getResult();
            model.addAttribute("message", result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "result";
    }
}