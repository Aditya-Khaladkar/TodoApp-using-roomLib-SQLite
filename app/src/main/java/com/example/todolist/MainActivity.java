package com.example.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    TodoAdapter todoAdapter;
    TextInputLayout delete_task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final TodoDB todoDB = initDb();
        final TodoDao todoDao = todoDB.todoDao();
        final TodoService service = new TodoService(todoDao);

        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<Todo> todoList = todoDao.getAllTask();
                todoAdapter = new TodoAdapter(todoList);
                recyclerView.setAdapter(todoAdapter);
            }
        }).start();

        findViewById(R.id.btn_add).setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), AddTask.class));
            finish();
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                service.deleteTask(todoAdapter.myTodoList.get(viewHolder.getAdapterPosition()))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.newThread())
                        .subscribe();
            }
        }).attachToRecyclerView(recyclerView);
    }

    public TodoDB initDb() {
        return Room
                .databaseBuilder(getApplicationContext(), TodoDB.class, "todolist")
                .build();
    }
}