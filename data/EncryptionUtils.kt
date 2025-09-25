// EncryptionUtils.kt
package com.udin.kasir.encryption

import java.security.MessageDigest

object EncryptionUtils {
    fun sha256(input: String): String {
        val bytes = MessageDigest.getInstance("SHA-256").digest(input.toByteArray())
        return bytes.joinToString("") { "%02x".format(it) }
    }

    fun generateActivationCode(hwId: String): String {
        val hash = sha256(hwId)
        // Gabungkan 2 karakter depan + 4 karakter belakang (rahasia)
        return hash.substring(0, 2) + hash.takeLast(4)
    }

    fun validateActivationCode(hwId: String, code: String): Boolean {
        return code == generateActivationCode(hwId)
    }
}
