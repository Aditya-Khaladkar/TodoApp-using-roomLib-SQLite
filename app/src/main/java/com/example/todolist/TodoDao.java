package com.example.todolist;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TodoDao {

    @Insert
    void saveTodoTask(Todo todo);

    @Query("select * from todo_task")
    List<Todo> getAllTask();

    @Query("delete from todo_task where id = :id")
    void deleteTodoTask(int id);
}
