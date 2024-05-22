//android -> project 변경 후 build.gradle.kts의 dependencies에 implementation("io.coil-kt:coil-compose:2.6.0") 추가해야함

package com.example.a240522_practice_1

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.a240522_practice_1.ui.theme._240522_Practice_1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _240522_Practice_1Theme {
                MainScreen()
            }
        }
    }
}


@Composable
fun MainScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val iconArray = listOf<Int>(R.drawable.bum_icon2, R.drawable.bum_icon3)
        var imgID = R.drawable.bum_icon2
        var iconIndex by remember {
            mutableIntStateOf(0)
        }
        var imageUri by remember {
            mutableStateOf<Uri?>(null)
        }
        val pickMedia = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            imageUri=uri
        }
        Image(painter = rememberAsyncImagePainter(imageUri),
            contentDescription = "bumicon2",
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(250.dp)
                .clip(CircleShape))
        Spacer(modifier = Modifier.size(20.dp))
        Button(onClick = {
            pickMedia.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
            )
        },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xff3b9acc))) {
            Text(text = "변경")
        }
        Text(text = "$iconIndex")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    _240522_Practice_1Theme {
        MainScreen()
    }
}
