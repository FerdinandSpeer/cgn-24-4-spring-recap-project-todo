package de.fspeer.cgn244springrecapprojecttodo.controller;


import de.fspeer.cgn244springrecapprojecttodo.service.ToDoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class ToDoController {

    public final ToDoService toDoService;

    @GetMapping
    public


}
