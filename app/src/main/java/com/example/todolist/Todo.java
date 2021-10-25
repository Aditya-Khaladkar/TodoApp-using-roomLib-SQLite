package com.example.todolist;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "todo_task")
public class Todo {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "task_name")
    public String TaskName;

    @ColumnInfo(name = "other_field")
    public String TaskOtherField;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return TaskName;
    }

    public void setTaskName(String taskName) {
        TaskName = taskName;
    }

    public String getTaskOtherField() {
        return TaskOtherField;
    }

    public void setTaskOtherField(String taskOtherField) {
        TaskOtherField = taskOtherField;
    }
}
