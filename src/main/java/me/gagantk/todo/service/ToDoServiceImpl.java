package me.gagantk.todo.service;

import me.gagantk.todo.model.ToDo;
import me.gagantk.todo.model.ToDoResponse;
import me.gagantk.todo.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.swing.text.html.Option;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class ToDoServiceImpl implements ToDoService {

    @Autowired
    ToDoRepository toDoRepository;

    @Override
    public ToDoResponse getToDos() {
        ToDoResponse response = new ToDoResponse();
        response.setToDos(toDoRepository.findAll());
        return response;
    }

    @Override
    public ToDoResponse addToDo(ToDo toDo) {
        ToDo duplicateNameToDo = toDoRepository.findByName(toDo.getName());
        ToDoResponse response = new ToDoResponse();
        if (duplicateNameToDo != null) {
            response.setError("Duplicate To-Do name");
            return response;
        }
        ToDo savedToDo = toDoRepository.save(toDo);
        response.setToDo(savedToDo);
        return response;
    }

    @Override
    public ToDoResponse updateToDo(Long id, ToDo toDo) {
        Optional<ToDo> res = toDoRepository.findById(id);
        ToDoResponse response = new ToDoResponse();
        if (!res.isPresent()) {
            response.setError("To-Do item not found");
            return response;
        }
        ToDo existingToDo = res.get();
        existingToDo.setName(toDo.getName());
        existingToDo.setDescription(toDo.getDescription());
        existingToDo.setStatus(toDo.getStatus());
        existingToDo.setPriority(toDo.getPriority());
        existingToDo = toDoRepository.save(existingToDo);

        response.setToDo(existingToDo);
        return response;
    }

    @Override
    public ToDoResponse deleteToDo(Long id) {
        Optional<ToDo> res = toDoRepository.findById(id);
        ToDoResponse response = new ToDoResponse();
        if (!res.isPresent()) {
            response.setError("To-Do item not found");
            return response;
        }
        toDoRepository.deleteById(id);
        return response;
    }

}
