package com.example.todolist;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Todo.class}, version = 1)
public abstract class TodoDB extends RoomDatabase {
    public abstract TodoDao todoDao();
}
