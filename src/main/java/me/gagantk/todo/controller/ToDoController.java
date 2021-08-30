package me.gagantk.todo.controller;

import me.gagantk.todo.model.ToDo;
import me.gagantk.todo.model.ToDoResponse;
import me.gagantk.todo.service.ToDoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/todos")
public class ToDoController {

    private final ToDoService toDoService;

    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @GetMapping
    public ResponseEntity<ToDoResponse> getToDos() {
        return new ResponseEntity<>(toDoService.getToDos(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ToDoResponse> addToDo(@Valid @RequestBody ToDo toDo) {
        HttpStatus status = HttpStatus.CREATED;
        ToDoResponse response = toDoService.addToDo(toDo);
        if (response.getError() != null) {
            status = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity(response, status);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ToDoResponse> updateToDo(@PathVariable Long id, @Valid @RequestBody ToDo toDo) {
        HttpStatus status = HttpStatus.OK;
        ToDoResponse response = toDoService.updateToDo(id, toDo);
        if (response.getError() != null) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(response, status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ToDoResponse> deleteToDo(@PathVariable Long id) {
        HttpStatus status = HttpStatus.OK;
        ToDoResponse response = toDoService.deleteToDo(id);
        if (response.getError() != null) {
            status = HttpStatus.NOT_FOUND;
        }
        return new ResponseEntity<>(response, status);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, List<String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, List<String>> errors = new HashMap<>();
        List<String> errorsList = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String errorMessage = error.getDefaultMessage();
            errorsList.add(errorMessage);
        });
        errors.put("errors", errorsList);
        return errors;
    }

}
