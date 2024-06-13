package com.example.todoapp160421055.model

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg todo:Todo)
    @Query("UPDATE Todo SET is_done = 1 WHERE uuid = :id") //untuk mengupdate bahwa task telah selesai
    fun markAsDone(id: Int)
    @Query("SELECT * FROM Todo WHERE is_done = 0 ORDER BY priority DESC") //untuk menampilkan hanya task yang belum selesai
    fun selectPendingTodo(): List<Todo>
    @Query("SELECT * FROM todo ORDER BY priority DESC")
    fun selectAllTodo(): List<Todo>
    @Query("SELECT * FROM todo WHERE uuid= :id")
    fun selectTodo(id:Int): Todo

    @Query("UPDATE todo SET title=:title, notes=:notes, priority=:priority WHERE uuid = :id")
    fun update(title:String, notes:String, priority:Int, id:Int)

    @Database(entities = arrayOf(Todo::class),version = 4)
    abstract class TodoDatabase:RoomDatabase(){
        abstract fun todoDao():TodoDao
    }
    @Delete
    fun deleteTodo(todo:Todo)
}
