package com.example.todolist;


import io.reactivex.rxjava3.core.Observable;

public class TodoService {
    private final TodoDao todoDao;

    public TodoService(TodoDao todoDao) {
        this.todoDao = todoDao;
    }

    public Observable<Object> deleteTask(Todo todo) {
        return Observable.create(emitter -> {
            todoDao.deleteTodoTask(todo.getId());
            emitter.onNext("deleted");
            emitter.onComplete();
        });
    }
}
