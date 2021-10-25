package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

public class AddTask extends AppCompatActivity {
    TextInputLayout task_name, other_field;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        final TodoDB todoDB = initDb();
        final TodoDao todoDao = todoDB.todoDao();

        task_name = findViewById(R.id.task_name);
        other_field = findViewById(R.id.other_field);

        findViewById(R.id.btn_add_task).setOnClickListener(v -> {
            String taskName = task_name.getEditText().getText().toString();
            String otherField = other_field.getEditText().getText().toString();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    final Todo todo = new Todo();
                    todo.setTaskName(taskName);
                    todo.setTaskOtherField(otherField);
                    todoDao.saveTodoTask(todo);
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }).start();
        });
    }

    public TodoDB initDb() {
        return Room
                .databaseBuilder(getApplicationContext(), TodoDB.class, "todolist")
                .build();
    }
}