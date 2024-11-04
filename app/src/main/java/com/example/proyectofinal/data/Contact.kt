package com.example.proyectofinal.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class Contact(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val lastName: String?, // Haciendo campos opcionales si es posible que sean nulos
    val phoneNumber: String,
    val email: String?,
    val nickname: String?,
    val address: String?
)
