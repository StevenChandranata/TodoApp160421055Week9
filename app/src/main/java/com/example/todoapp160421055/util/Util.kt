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
        .addMigrations(MIGRATION_2_3) //ganti migration dengan migration baru
        .build()

    return db
}

val MIGRATION_2_3 = object : Migration(2, 3) { //buat migration baru untuk menambahkan column baru untuk is_done
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE todo ADD COLUMN is_done INTEGER DEFAULT 0 NOT NULL")
    }
}

