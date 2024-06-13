package com.example.todoapp160421055.util

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.todoapp160421055.model.TodoDatabase


val DB_NAME = "newtododb"


fun buildDb(context: Context):TodoDatabase {
    val db = Room.databaseBuilder(context,
        TodoDatabase::class.java, DB_NAME)
        .addMigrations(MIGRATION_3_4) //ganti migration dengan migration baru
        .build()

    return db
}
val MIGRATION_3_4 = object : Migration(3, 4) { //buat migration baru untuk menambahkan column baru untuk is_done
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE todo ADD COLUMN todo_date INTEGER DEFAULT 0 NOT NULL")
    }
}

