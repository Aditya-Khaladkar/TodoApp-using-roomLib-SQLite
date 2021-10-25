package com.example.todolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {

    List<Todo> myTodoList;

    public TodoAdapter(List<Todo> myTodoList) {
        this.myTodoList = myTodoList;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.task_list,parent,false);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position) {
        Todo todo = myTodoList.get(position);
        holder.txt_task_name.setText(todo.getTaskName());
        holder.txt_sub_task.setText(todo.getTaskOtherField());
    }

    @Override
    public int getItemCount() {
        return myTodoList.size();
    }

    public class TodoViewHolder extends RecyclerView.ViewHolder {
        TextView txt_task_name, txt_sub_task;
        CardView todo_card;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_task_name = itemView.findViewById(R.id.txt_task_name);
            txt_sub_task = itemView.findViewById(R.id.txt_sub_task);
            todo_card = itemView.findViewById(R.id.todo_card);
        }
    }
}
