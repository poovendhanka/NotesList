package com.notes.NotesList.Controller;

import com.notes.NotesList.TaskService;
import com.notes.NotesList.model.Task;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    public String getTask(){
        List<Task> tasks=taskService.getAllTasks();
        return "tasks";

    }
}
