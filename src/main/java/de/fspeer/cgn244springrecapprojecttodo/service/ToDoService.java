package de.fspeer.cgn244springrecapprojecttodo.service;

import de.fspeer.cgn244springrecapprojecttodo.models.ToDo;
import de.fspeer.cgn244springrecapprojecttodo.models.ToDoDTO;
import de.fspeer.cgn244springrecapprojecttodo.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ToDoService {

    private final IdService idService;
    private final ToDoRepository toDoRepository;

    public List<ToDo> getAllToDos() {
        return toDoRepository.findAll();
    }

    public ToDo createToDo(ToDoDTO toDoDTO) {
        return toDoRepository.save(new ToDo(idService.generateId(), toDoDTO.description(), toDoDTO.status()));
    }
}
