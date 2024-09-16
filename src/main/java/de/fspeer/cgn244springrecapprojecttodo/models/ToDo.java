package de.fspeer.cgn244springrecapprojecttodo.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


public record ToDo
 (String id,
 String description,
 String status){
}
