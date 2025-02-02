package com.notes.NotesList.Controller;

import com.notes.NotesList.Service.TaskService;
import com.notes.NotesList.model.Task;
import com.notes.NotesList.model.User;
import com.notes.NotesList.repository.TaskRepo;
import com.notes.NotesList.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RequestMapping("/NotesList")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    private TaskRepo taskRepo;
    @Autowired
    private UserRepo userRepo;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/tasks")
    public String getTask(Model model, Authentication authentication) {
        String username = authentication.getName();
        User user = userRepo.findByUsername(username).orElseThrow();
        List<Task> tasks = taskRepo.findByUser(user);
        model.addAttribute("tasks", tasks);
        return "tasks";
    }



    @PostMapping("/tasks")
    public String createTask(@RequestParam String title, Authentication authentication) {
        String username = authentication.getName();
        User user = userRepo.findByUsername(username).orElseThrow();
        taskService.createTask(title, user); // Pass the user to the service
        return "redirect:/tasks";
    }

    @GetMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);

        return "redirect:/tasks";
    }

    @GetMapping("/{id}/toggle")
    public String toggleTask(@PathVariable Long id){
        taskService.toggleTask(id);

        return "redirect:/tasks";
    }

}