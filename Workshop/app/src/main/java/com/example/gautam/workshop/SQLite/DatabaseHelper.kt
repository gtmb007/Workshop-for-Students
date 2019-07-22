package com.example.gautam.workshop.SQLite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.gautam.workshop.model.User

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    private val CREATE_USER_TABLE = ("CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT" + ")")


    private val DROP_USER_TABLE = "DROP TABLE IF EXISTS $TABLE_USER"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_USER_TABLE)
    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(DROP_USER_TABLE)
        onCreate(db)
    }

    fun addUser(user: User) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_USER_NAME, user.name)
        values.put(COLUMN_USER_EMAIL, user.email)
        values.put(COLUMN_USER_PASSWORD, user.password)
        db.insert(TABLE_USER, null, values)
        db.close()
    }

    fun getCurrentUser(): MutableList<User>{
        var list : MutableList<User> = ArrayList()

        val db = this.readableDatabase
        val query = "Select * from " + DatabaseHelper.TABLE_USER
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()){
            do{
                var user = User()
                user.id = result.getString(result.getColumnIndex(DatabaseHelper.COLUMN_USER_ID)).toInt()
                user.name = result.getString(result.getColumnIndex(DatabaseHelper.COLUMN_USER_NAME))
                user.email = result.getString(result.getColumnIndex(DatabaseHelper.COLUMN_USER_EMAIL))
                user.password = result.getString(result.getColumnIndex(DatabaseHelper.COLUMN_USER_PASSWORD))
                list.add(user)
            } while (result.moveToNext())
        }
        result.close()
        db.close()
        return list
    }

    fun checkUser(email: String): Boolean {

        val columns = arrayOf(COLUMN_USER_ID)
        val db = this.readableDatabase

        val selection = "$COLUMN_USER_EMAIL = ?"

        val selectionArgs = arrayOf(email)

        val cursor = db.query(
            TABLE_USER,
            columns,
            selection,
            selectionArgs, null, null, null
        )

        val cursorCount = cursor.count
        cursor.close()
        db.close()

        return if (cursorCount > 0) {
            true
        } else false

    }

    fun checkUser(email: String, password: String): Boolean {

        val columns = arrayOf(COLUMN_USER_ID)
        val db = this.readableDatabase

        val selection = "$COLUMN_USER_EMAIL = ? AND $COLUMN_USER_PASSWORD = ?"

        val selectionArgs = arrayOf(email, password)

        val cursor = db.query(
            TABLE_USER,
            columns,
            selection,
            selectionArgs, null, null, null
        )

        val cursorCount = cursor.count

        cursor.close()
        db.close()
        return if (cursorCount > 0) {
            true
        } else false

    }


    companion object {

        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "UserManager.db"
        private val TABLE_USER = "user"
        private val COLUMN_USER_ID = "user_id"
        private val COLUMN_USER_NAME = "user_name"
        private val COLUMN_USER_EMAIL = "user_email"
        private val COLUMN_USER_PASSWORD = "user_password"

    }
}
