package com.example.proyectofinal.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.proyectofinal.data.Contact
import com.example.proyectofinal.data.ContactDatabase
import com.example.proyectofinal.data.ContactRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers

class ContactsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ContactRepository
    val allContacts: LiveData<List<Contact>>

    init {
        val contactDao = ContactDatabase.getDatabase(application).contactDao()
        repository = ContactRepository(contactDao)
        allContacts = repository.allContacts
    }

    fun insert(contact: Contact) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(contact)
    }

    fun update(contact: Contact) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(contact)
    }

    fun delete(contact: Contact) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(contact)
    }

    fun getContactById(id: Int): LiveData<Contact> {
        return repository.getContactById(id)
    }
}
