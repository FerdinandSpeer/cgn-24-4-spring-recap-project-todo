package de.fspeer.cgn244springrecapprojecttodo.controller;


import de.fspeer.cgn244springrecapprojecttodo.models.ToDo;
import de.fspeer.cgn244springrecapprojecttodo.models.ToDoDTO;
import de.fspeer.cgn244springrecapprojecttodo.repository.ToDoRepository;
import de.fspeer.cgn244springrecapprojecttodo.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class ToDoController {

    public final ToDoService toDoService;

    @GetMapping
    public List<ToDo> getAllToDos(){
        return toDoService.getAllToDos();
    }

    @PostMapping
    public ToDo createToDo(@RequestBody ToDoDTO toDoDTO){
        return toDoService.createToDo(toDoDTO);
    }

    @GetMapping("/{id}")
    public ToDo getDetailsToDoById(@PathVariable String id){
        return toDoService.getDetailsToDoById(id);
    }

    @PutMapping("/{id}")
    public ToDo editToDo(@PathVariable String id, @RequestBody ToDo editedToDo){
        return toDoService.editToDo(editedToDo);
    }

    @DeleteMapping("/{id}")
    public String deleteToDo(@PathVariable String id){
        return toDoService.deleteToDo(id);
    }


}
