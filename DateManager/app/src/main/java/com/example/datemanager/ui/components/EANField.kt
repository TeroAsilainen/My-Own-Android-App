package com.example.datemanager.ui.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning

@Composable
fun EANField(context: Context, eanInput: String ,onValueChange: (String) -> Unit ) {
    //var eanInput by remember { mutableStateOf("") }


    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = "EAN") },
        value = eanInput,
        onValueChange = onValueChange,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        trailingIcon = {
            IconButton(
                onClick = {

                        bacCodeProcess(
                            context = context,
                            success = onValueChange ,
                            canceled = {
                                Toast.makeText(context, "Scan Canceled", Toast.LENGTH_LONG).show()
                            } ,
                            failed = {
                                Toast.makeText(context, it.message.toString(), Toast.LENGTH_LONG).show()
                            }
                        )

                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = ""
                )
            }
        }
    )
}



private fun bacCodeProcess(
    context: Context,
    success: (String) -> Unit,
    canceled: () -> Unit,
    failed: (Exception) -> Unit
) {
    val options = GmsBarcodeScannerOptions.Builder()
        .setBarcodeFormats(
            Barcode.FORMAT_EAN_8,
            Barcode.FORMAT_EAN_13,
        )
        .enableAutoZoom()
        .build()

    val scanner = GmsBarcodeScanning.getClient(context, options)

    scanner.startScan()
                    .addOnSuccessListener { barcode ->
                        val displayValue: String? = barcode.displayValue
                        if (displayValue != null) {
                            success(displayValue)
                        }
                        //Log.d("callBack", displayValue.toString())
                    }
                    .addOnCanceledListener {
                        canceled()
                        //Log.d("callBack", "Canceled")
                    }
                    .addOnFailureListener { e ->
                        failed(e)
                        //Log.d("callBack", e.message.toString())
                    }
}

