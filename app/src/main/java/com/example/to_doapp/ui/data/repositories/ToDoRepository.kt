package com.example.to_doapp.ui.data.repositories

import com.example.to_doapp.ui.data.TodoDao
import com.example.to_doapp.ui.data.models.ToDoTask
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class TodoRepository @Inject constructor(private val todoDao: TodoDao) {

    val getAllTasks: Flow<List<ToDoTask>> = todoDao.getAllTasks()
    val sortByLowPriority: Flow<List<ToDoTask>> = todoDao.sortByLowPriority()
    val sortByHighPriority: Flow<List<ToDoTask>> = todoDao.sortByHighPriority()

    fun getSelectedTask(taskId: Int): Flow<ToDoTask> {
        return todoDao.getSelectedTask(taskId = taskId)
    }

    suspend fun addTask(todoTask: ToDoTask) {
        todoDao.addTask(todoTask = todoTask)
    }

    suspend fun updateTask(todoTask: ToDoTask) {
        todoDao.updateTask(todoTask = todoTask)
    }

    suspend fun deleteTask(todoTask: ToDoTask) {
        todoDao.deleteTask(todoTask = todoTask)
    }

    suspend fun deleteAllTasks() {
        todoDao.deleteAllTasks()
    }

    fun searchDatabase(searchQuery: String): Flow<List<ToDoTask>> {
        return todoDao.searchDatabase(searchQuery = searchQuery)
    }
}