package com.example.proyectofinal.data

import androidx.lifecycle.LiveData

class ContactRepository(private val contactDao: ContactDao) {
    val allContacts: LiveData<List<Contact>> = contactDao.getAllContacts()

    suspend fun insert(contact: Contact) {
        contactDao.insert(contact)
    }

    suspend fun update(contact: Contact) {
        contactDao.update(contact)
    }

    suspend fun delete(contact: Contact) {
        contactDao.delete(contact)
    }

    fun getContactById(id: Int): LiveData<Contact> {
        return contactDao.getContactById(id)
    }
}
