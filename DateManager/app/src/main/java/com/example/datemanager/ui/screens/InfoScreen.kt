package com.example.datemanager.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withLink
import androidx.compose.ui.unit.dp

@Composable
fun InfoScreen(modifier: Modifier) {
    Column (
        modifier = modifier.fillMaxSize().padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        Row {
            Text(text = "App developed by:")
        }
        Row {
            Text("Tero Asilainen")
        }

        Row {
            Text("API Data Provided by:")
        }
        Row {

            Text(
                buildAnnotatedString {
                    withLink(LinkAnnotation.Url(
                        "https://openfoodfacts.org/",
                        TextLinkStyles(style = SpanStyle(color = Color.Blue))
                    )) {
                        append("openfoodfacts.org")
                    }
                },
                )
        }
    }
}