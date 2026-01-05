package com.example.datemanager.ui.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.example.datemanager.R
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning

@Composable
fun EANField(context: Context, eanInput: String ,onValueChange: (String) -> Unit ) {
    //var eanInput by remember { mutableStateOf("") }


    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = stringResource(R.string.ean)) },
        value = eanInput,
        onValueChange = onValueChange,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = MaterialTheme.colorScheme.tertiary,
            focusedTrailingIconColor = MaterialTheme.colorScheme.primary,
            focusedTextColor = MaterialTheme.colorScheme.primary),
        trailingIcon = {
            IconButton(
                onClick = {

                        barCodeProcess(
                            context = context,
                            success = onValueChange ,
                            canceled = {
                                Toast.makeText(context,
                                    context.getString(R.string.viivakoodin_luku_peruttu), Toast.LENGTH_LONG).show()
                            } ,
                            failed = {
                                Toast.makeText(context, it.message.toString(), Toast.LENGTH_LONG).show()
                            }
                        )

                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.scan_barcode)
                )
            }
        }
    )
}



private fun barCodeProcess(
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

