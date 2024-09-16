package de.fspeer.cgn244springrecapprojecttodo.repository;

import de.fspeer.cgn244springrecapprojecttodo.models.ToDo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepository extends MongoRepository<ToDo, String> {

}
