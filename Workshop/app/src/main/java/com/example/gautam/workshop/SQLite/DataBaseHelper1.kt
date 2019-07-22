package com.example.gautam.workshop.SQLite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.gautam.workshop.model.Workshop

class DatabaseHelper1(context: Context) : SQLiteOpenHelper(context,
    Companion.DATABASE_NAME2, null,
    Companion.DATABASE_VERSION2
) {
    private val CREATE_WORKSHOP_TABLE = ("CREATE TABLE " + TABLE_WORKSHOP + "("
            + COLUMN_WORKSHOP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_WORKSHOP_NAME + " TEXT,"
            + COLUMN_ORGANISATION_NAME + " TEXT " + ")")

    private val DROP_WORKSHOP_TABLE = "DROP TABLE IF EXISTS $TABLE_WORKSHOP"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_WORKSHOP_TABLE)
    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(DROP_WORKSHOP_TABLE)
        onCreate(db)
    }

    fun addWorkshop(workshop: Workshop){
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_WORKSHOP_NAME, workshop.workshopName)
        values.put(COLUMN_ORGANISATION_NAME, workshop.organisationName)
        db.insert(TABLE_WORKSHOP, null, values)
        db.close()
    }

    fun deleteAllWorkshops(){
        val db = this.writableDatabase
        db.delete(TABLE_WORKSHOP, null, null)
    }

    fun getAllWorkshops(): MutableList<Workshop>{
        var list : MutableList<Workshop> = ArrayList()

        val db = this.readableDatabase
        val query = "Select * from " + TABLE_WORKSHOP
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()){
            do{
                var workshop = Workshop()
                workshop.workshopId = result.getString(result.getColumnIndex(COLUMN_WORKSHOP_ID)).toInt()
                workshop.workshopName = result.getString(result.getColumnIndex(COLUMN_WORKSHOP_NAME))
                workshop.organisationName = result.getString(result.getColumnIndex(COLUMN_ORGANISATION_NAME))
                list.add(workshop)
            } while (result.moveToNext())
        }
        result.close()
        db.close()
        return list
    }

    fun checkWorkshop(workshopName: String, organisationName: String): Boolean {

        val columns = arrayOf(COLUMN_WORKSHOP_ID)
        val db = this.readableDatabase

        val selection = "${COLUMN_WORKSHOP_NAME} = ? AND ${COLUMN_ORGANISATION_NAME} = ?"

        val selectionArgs = arrayOf(workshopName, organisationName)

        val cursor = db.query(
            TABLE_WORKSHOP,
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

        private val DATABASE_VERSION2 = 2
        private val DATABASE_NAME2 = "WorkshhopManager.db"
        private val TABLE_WORKSHOP = "user"
        private val COLUMN_WORKSHOP_ID = "user_id"
        private val COLUMN_WORKSHOP_NAME = "user_name"
        private val COLUMN_ORGANISATION_NAME = "user_email"
    }
}