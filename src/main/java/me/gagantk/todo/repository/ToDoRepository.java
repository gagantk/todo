package me.gagantk.todo.repository;

import me.gagantk.todo.model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {

    ToDo findByName(String name);

}
