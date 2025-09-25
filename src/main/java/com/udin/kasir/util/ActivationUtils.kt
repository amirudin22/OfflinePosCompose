package com.udin.kasir.util

import java.security.MessageDigest
import java.util.Locale

object ActivationUtils {
    private fun toHex(bytes: ByteArray): String {
        val sb = StringBuilder(bytes.size * 2)
        for (b in bytes) sb.append(String.format("%02x", b))
        return sb.toString()
    }

    private fun sha256Hex(input: String): String {
        val md = MessageDigest.getInstance("SHA-256")
        return toHex(md.digest(input.toByteArray(Charsets.UTF_8)))
    }

    /**
     * Protocol:
     * - compute sha256 hex of fingerprint
     * - activation code = first 2 chars of hex + last 4 chars of hex (6 chars total, lowercase)
     */
    fun generateActivationFromFingerprint(fingerprint: String): String {
        val h = sha256Hex(fingerprint)
        val first2 = if (h.length >= 2) h.substring(0,2) else h
        val last4 = if (h.length >= 4) h.substring(h.length - 4) else h
        return (first2 + last4).lowercase(Locale.ROOT)
    }

    fun verifyActivationFromFingerprint(fingerprint: String, provided: String): Boolean {
        val expected = generateActivationFromFingerprint(fingerprint)
        return expected.equals(provided.trim().lowercase(Locale.ROOT))
    }
}
