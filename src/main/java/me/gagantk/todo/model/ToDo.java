package me.gagantk.todo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "todo")
@Getter
@Setter
public class ToDo {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    @Size(min = 5, max = 25, message = "Name should be minimum 5 characters and maximum 25 characters")
    @Pattern(regexp = "[A-Za-z]\\w*", message = "Name should be alphanumeric only and should not start with a number")
    private String name;

    @NotNull
    private String description;

    @NotNull
    private String status;

    @NotNull
    private String priority;

}
