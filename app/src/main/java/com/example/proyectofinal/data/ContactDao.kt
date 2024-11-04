package com.example.proyectofinal.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ContactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contact: Contact): Long

    @Update
    fun update(contact: Contact): Int

    @Delete
    fun delete(contact: Contact): Int

    @Query("SELECT * FROM contacts WHERE id = :id")
    fun getContactById(id: Int): LiveData<Contact>

    @Query("SELECT * FROM contacts ORDER BY name ASC")
    fun getAllContacts(): LiveData<List<Contact>>
}
