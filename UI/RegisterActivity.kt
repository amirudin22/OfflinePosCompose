// RegisterActivity.kt
package com.udin.kasir.ui.register

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.udin.kasir.databinding.ActivityRegisterBinding
import com.udin.kasir.data.database.DatabaseHelper
import com.udin.kasir.encryption.EncryptionUtils

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var hwId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DatabaseHelper(this)
        hwId = getHardwareId(this)
        binding.tvHwId.text = hwId // Tampilkan HW ID agar bisa disalin

        binding.btnRegister.setOnClickListener {
            val username = binding.etUsername.text.toString().trim()
            val password = binding.etPassword.text.toString()
            val activationCode = binding.etActivationCode.text.toString().trim()

            if (username.isEmpty() || password.isEmpty() || activationCode.isEmpty()) {
                Toast.makeText(this, "Isi semua data dengan lengkap", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!EncryptionUtils.validateActivationCode(hwId, activationCode)) {
                Toast.makeText(this, "Kode aktivasi salah", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Simpan user baru sebagai owner
            val db = dbHelper.writableDatabase
            val sql = "INSERT INTO ${DatabaseHelper.TABLE_USER} (${DatabaseHelper.COLUMN_USER_USERNAME}, ${DatabaseHelper.COLUMN_USER_PASSWORD}, ${DatabaseHelper.COLUMN_USER_ROLE}) VALUES (?, ?, ?)"
            val stmt = db.compileStatement(sql)
            stmt.bindString(1, username)
            stmt.bindString(2, password) // In production, hash password!
            stmt.bindString(3, "Owner")
            try {
                stmt.executeInsert()
                Toast.makeText(this, "Registrasi berhasil", Toast.LENGTH_SHORT).show()
                finish()
            } catch (e: Exception) {
                Toast.makeText(this, "Registrasi gagal: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Contoh sederhana mendapatkan hardware ID, bisa diganti sesuai device
    private fun getHardwareId(context: Context): String {
        return android.provider.Settings.Secure.getString(
            context.contentResolver,
            android.provider.Settings.Secure.ANDROID_ID
        ) ?: "UNKNOWN_HWID"
    }
}
