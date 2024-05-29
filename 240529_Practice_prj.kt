package com.example.ml_kit_practice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ml_kit_practice.ui.theme.ML_Kit_PracticeTheme
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.Translator
import com.google.mlkit.nl.translate.TranslatorOptions

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ML_Kit_PracticeTheme {
                Main()
            }
        }
    }
}

@Composable
fun Main() {
    var inputText by remember { mutableStateOf("입력") }
    var tranText by remember { mutableStateOf("") }
    var textCount by remember { mutableIntStateOf(0) }
    val textSize by remember { mutableIntStateOf(20) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(80.dp))
        TextField(
            value = inputText,
            onValueChange = { newText ->
                inputText = newText
                textCount = inputText.length
            },
            modifier = Modifier
                .fillMaxWidth()
                .size(200.dp),
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ),
            textStyle = TextStyle(fontSize = textSize.sp)
        )
        Spacer(modifier = Modifier.size(30.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .size(200.dp)
                .background(
                    color = Color(0xfffaf2e6),
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(15.dp)
        ) {
            Text(
                text = tranText,
                fontSize = textSize.sp
            )
        }
        Spacer(modifier = Modifier.size(30.dp))
        var isDownloaded by remember {
            mutableStateOf(false)
        }
        val koenTranslator = remember {
            val options = TranslatorOptions.Builder()
                .setSourceLanguage(TranslateLanguage.KOREAN)
                .setTargetLanguage(TranslateLanguage.ENGLISH)
                .build()
            Translation.getClient(options)
        }
        DownloadModel(koenTranslator, onSuccess = { isDownloaded = true })
        Button(
            onClick = {
                koenTranslator.translate(inputText)
                    .addOnSuccessListener { translatedText ->
                        tranText = translatedText
                    }
            },
            enabled = isDownloaded,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFd69f47)
            )
        ) {
            Text(text = "번역")
        }
    }
}


@Composable
fun DownloadModel(
    input: Translator,
    onSuccess: () -> Unit
) {
    LaunchedEffect(key1 = input) {
        val conditions = DownloadConditions.Builder()
            .requireWifi()
            .build()
        input.downloadModelIfNeeded(conditions)
            .addOnSuccessListener {
                onSuccess()
            }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ML_Kit_PracticeTheme {
        Main()
    }
}
