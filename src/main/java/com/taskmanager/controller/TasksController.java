package com.taskmanager.controller;

import com.taskmanager.model.Tasks;
import com.taskmanager.services.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
@CrossOrigin
public class TasksController {

  @Autowired
  private TaskServiceImpl taskService;
   @GetMapping("/task")
    public List<Tasks> getTasks(){
       return taskService.getTasks();
   }

    @GetMapping("/task/{id}")
    public ResponseEntity<?> getTasksById(@PathVariable Long id){

       Optional<Tasks> tasks= taskService.getTaskById(id);
       if(tasks.isPresent()){
           return new ResponseEntity<>(tasks.get(),HttpStatus.OK);
       } else{
           return new ResponseEntity<>("Task not found",HttpStatus.NOT_FOUND);
       }
    }

   @PostMapping("/task")
    public ResponseEntity<?> addTask(@RequestBody Tasks task){
      taskService.saveTask(task);
      return new ResponseEntity<>("Task added successfully",HttpStatus.OK);
   }

    @PutMapping("/task/{id}")
    public ResponseEntity<?> modifyTask(@RequestBody Tasks task,@PathVariable Long id){
        if(taskService.isExistingById(id)){
            Optional<Tasks> taskFound=taskService.getTaskById(id);
            if(taskFound.isEmpty()){
                throw new EntityNotFoundException("Tasks not found");

            } else{
                Tasks t = taskFound.get();
                t.setTitle(task.getTitle());
                t.setDueDate(task.getDueDate());
                t.setDescription(task.getDescription());
                t.setType(task.getType());
                taskService.saveTask(t);
                return new ResponseEntity<>("Task modified successfully",HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Task not found",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/task/{id}")
    public ResponseEntity<?> deleteTasksById(@PathVariable Long id) {
        if(taskService.isExistingById(id)){
            taskService.deleteTask(id);
            return new ResponseEntity<>("Task deleted successfully",HttpStatus.OK);
        } else{
            return new ResponseEntity<>("Task not found",HttpStatus.OK);

        }
    }

}
