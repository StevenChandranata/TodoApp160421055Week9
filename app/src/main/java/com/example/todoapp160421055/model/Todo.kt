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
    @ColumnInfo(name="is_done")
    var isDone: Int = 0 // artinya isDone == false
    // karena Room (bagian dari android jetpack yang digunakan untuk menyederhanakan  penggunaan SQLite dalam aplikasi Android) tidak mendukung tipe BOOLEAN,sehingga nilai boolean dibuat dalam bentuk INTEGER yang mana nilai 0 sebagai false dan nilai 1 sebagai true didalam database.
)  {
    @PrimaryKey(autoGenerate = true)
    var uuid:Int =0

    fun markAsDone() {
        isDone = 1 //artinya isDone == True
    }
}
