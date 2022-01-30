package com.taskmanager.services;

import com.taskmanager.model.Tasks;
import com.taskmanager.repository.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements  TaskService{

    @Autowired
    private TasksRepository tasksRepository;

    public List<Tasks> getTasks(){
        return tasksRepository.findAll();
    }

    public Tasks saveTask(Tasks task){
        return tasksRepository.save(task);
    }
    public boolean isExistingById(Long id){
        return tasksRepository.existsById(id);
    }

    public Optional<Tasks> getTaskById(Long id) {
        return tasksRepository.findById(id);
    }

    public void deleteTask(Long id) {
        tasksRepository.deleteById((id));
    }
}
