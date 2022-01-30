package com.taskmanager.services;

import com.taskmanager.model.Tasks;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    public List<Tasks> getTasks();
    public Tasks saveTask(Tasks task);
    public boolean isExistingById(Long id);
    public Optional<Tasks> getTaskById(Long id);
    public void deleteTask(Long id);
}
