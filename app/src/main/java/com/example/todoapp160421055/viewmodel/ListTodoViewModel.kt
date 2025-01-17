package com.example.todoapp160421055.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.todoapp160421055.model.Todo
import com.example.todoapp160421055.model.TodoDatabase
import com.example.todoapp160421055.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ListTodoViewModel(application: Application)
    : AndroidViewModel(application), CoroutineScope {

    val todoLD = MutableLiveData<List<Todo>>()
    val todoLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
    fun refresh() {
        loadingLD.postValue(true)
        todoLoadErrorLD.postValue(false)
        launch(Dispatchers.IO) {
            val db = TodoDatabase.buildDatabase(getApplication())
            val pendingTodos = db.todoDao().selectPendingTodo()
            todoLD.postValue(pendingTodos) // Memperbarui LiveData
            loadingLD.postValue(false)
        }
    }

    fun markAsDone(todoId: Int) {
        launch(Dispatchers.IO) {
            val db = TodoDatabase.buildDatabase(getApplication())
            db.todoDao().markAsDone(todoId)
            refresh() // untuk refresh list dari task yang ditampilkan
        }
    }


    fun clearTask(todo: Todo) {
        launch {
            val db = buildDb(getApplication())

            db.todoDao().deleteTodo(todo)
            todoLD.postValue(db.todoDao().selectPendingTodo())
        }
    }

}

