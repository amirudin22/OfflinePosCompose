package com.udin.kasir.util

import android.content.Context
import android.os.Build
import android.provider.Settings

object DeviceFingerprint {
    /**
     * Combine several Build attributes. Try Build.SERIAL first (may be restricted) else fallback to ANDROID_ID.
     * Returns a deterministic string you can show to user for manual code generation.
     */
    fun getDeviceFingerprint(context: Context): String {
        val parts = mutableListOf<String>()

        val serial = try {
            if (!Build.SERIAL.isNullOrBlank()) Build.SERIAL else null
        } catch (e: Throwable) { null }

        if (!serial.isNullOrBlank()) parts.add("SERIAL:$serial")
        else {
            val aid = try { Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID) } catch (e: Exception) { null }
            if (!aid.isNullOrBlank()) parts.add("AID:$aid")
        }

        parts.add("MFG:${Build.MANUFACTURER}")
        parts.add("MODEL:${Build.MODEL}")
        parts.add("BRAND:${Build.BRAND}")
        parts.add("DEVICE:${Build.DEVICE}")
        parts.add("PRODUCT:${Build.PRODUCT}")

        return parts.joinToString("|")
    }
}
