package com.example.todoapp160421055.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todo(
    @ColumnInfo(name="title")
    var title:String,
    @ColumnInfo(name="notes")
    var notes:String,
    @ColumnInfo(name="priority")
    var priority:Int,
    @ColumnInfo(name="is_done") // field yang diguanakan untuk menentukan task sudah atau belum selesai
    var isDone: Int, // untuk value awal bahwa task belum selesai
    @ColumnInfo(name="todo_date")
    var todo_date:Int
)  {
    @PrimaryKey(autoGenerate = true)
    var uuid:Int =0

}
