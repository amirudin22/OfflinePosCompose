package com.udin.kasir.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.compose.material.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.udin.kasir.util.DeviceFingerprint
import com.udin.kasir.util.ActivationUtils

@Composable
fun RegisterScreen(navController: NavHostController) {
    val ctx = LocalContext.current
    val fingerprint = remember { DeviceFingerprint.getDeviceFingerprint(ctx) }
    var code by remember { mutableStateOf(TextFieldValue("")) }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Device fingerprint (copy & send to owner to get activation code):")
        Spacer(modifier = Modifier.height(8.dp))
        Text(fingerprint)
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            val cm = ctx.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            cm.setPrimaryClip(ClipData.newPlainText("fp", fingerprint))
            Toast.makeText(ctx, "Fingerprint copied", Toast.LENGTH_SHORT).show()
        }) {
            Text("Copy fingerprint")
        }
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(value = code, onValueChange = { code = it }, label = { Text("Activation code") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = {
            val ok = ActivationUtils.verifyActivationFromFingerprint(fingerprint, code.text)
            if (ok) {
                Toast.makeText(ctx, "Activation valid — proceed to create Owner account (TODO)", Toast.LENGTH_LONG).show()
                navController.navigate("dashboard")
            } else {
                Toast.makeText(ctx, "Invalid activation code", Toast.LENGTH_LONG).show()
            }
        }, modifier = Modifier.fillMaxWidth()) {
            Text("Verify & Register")
        }
    }
}
