package me.gagantk.todo.service;

import me.gagantk.todo.model.ToDo;
import me.gagantk.todo.model.ToDoResponse;

import java.util.List;

public interface ToDoService {

    ToDoResponse getToDos();

    ToDoResponse addToDo(ToDo toDo);

    ToDoResponse updateToDo(Long id, ToDo toDo);

    ToDoResponse deleteToDo(Long id);

}
