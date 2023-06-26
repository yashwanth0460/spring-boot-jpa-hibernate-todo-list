/*
 * You can use the following import statements
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.*;
 *
 */

// Write your code here
package com.example.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

import com.example.todo.model.Todo;
import com.example.todo.repository.TodoRepository;
import com.example.todo.repository.TodoJpaRepository;

@Service
public class TodoJpaService implements TodoRepository {

    @Autowired
    private TodoJpaRepository todoJpaRepository;

    @Override
    public ArrayList<Todo> getTodos() {

        List<Todo> todoList1 = todoJpaRepository.findAll();
        ArrayList<Todo> todos = new ArrayList<>(todoList1);
        return todos;
    }

    @Override
    public Todo getTodoById(int id) {

        try {
            Todo todo1 = todoJpaRepository.findById(id).get();
            return todo1;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Todo addTodo(Todo todo1) {

        todoJpaRepository.save(todo1);
        return todo1;
    }

    @Override
    public Todo updateTodo(int id,Todo todo1) {

        try {
            Todo newTodo = todoJpaRepository.findById(id).get();
            if (todo1.getTodo() != null) {
                newTodo.setTodo(todo1.getTodo());
            }
            if (todo1.getPriority() != null) {
                newTodo.setPriority(todo1.getPriority());
            }
            if (todo1.getStatus() != null) {
                newTodo.setStatus(todo1.getStatus());
            }

            todoJpaRepository.save(newTodo);
            return newTodo;

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteTodo(int id) {

        try {
            todoJpaRepository.deleteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}