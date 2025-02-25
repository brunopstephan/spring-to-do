package com.piffer.first_spring_app.services;

import com.piffer.first_spring_app.entities.ToDoEntity;
import com.piffer.first_spring_app.repositories.ToDoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import java.util.HashSet;
import java.util.Set;

@Service
public class ToDoService {

    @Autowired
    private ToDoRepository toDoRepository;

    public ToDoEntity createToDo(ToDoEntity toDo) {
        return toDoRepository.save(toDo);
    }

    public List<ToDoEntity> getAllToDo() {
        return toDoRepository.findAll();
    }

    //define que meu metodo vai ser executado dentro de uma transação e se uma falhar
    // irá dar rollback nas outras
    @Transactional
    public ToDoEntity updateToDo(UUID id, ToDoEntity toDo) {
        ToDoEntity existingToDo = toDoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrado"));

        // faz o merge dos dados novos no antigos ignorando os valores nulls, pois o copyProperties
        // faz validação por base na entity, no meu caso o name tem NotNull
        BeanUtils.copyProperties(toDo, existingToDo, getNullPropertyNames(toDo));

        return toDoRepository.save(existingToDo);
    }

    private String[] getNullPropertyNames(Object source) {


        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public void deleteToDo(UUID id) {
        toDoRepository.deleteById(id);
    }
}
