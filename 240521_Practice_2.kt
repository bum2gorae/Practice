package com.example.a240521_practice_2

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.edit
import com.example.a240521_practice_2.ui.theme._240521_Practice_2Theme
import java.time.LocalDateTime
import kotlin.math.abs


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _240521_Practice_2Theme {
                Main()
            }
        }
    }
}


@Composable
fun Main() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val activity = LocalContext.current as? Activity
        val sharedPref = remember {
            activity?.getPreferences(Context.MODE_PRIVATE)
        }
        var clickCount1 by remember {
            mutableLongStateOf(0)
        }
        var clickCount2 by remember {
            val saveCount = sharedPref?.getLong("count1", 0)
            mutableLongStateOf((saveCount ?: 0).toLong())
        }
        var currentTime by remember {
            val saveTime = sharedPref?.getString("clicktime", "-")
            mutableStateOf(saveTime ?: "-")
        }
        Row(modifier = Modifier.padding(10.dp)) {
            Button(
                onClick = {
                    clickCount1++
                    clickCount2++
                    sharedPref?.edit {
                        putLong("count1", clickCount2)
                    }
                    currentTime = setTime(sharedPref)
                },
                enabled = if (clickCount2 > 0) bigNumWarning(clickCount2) else true
            ) {
                Text(text = "클릭")
            }
            Spacer(modifier = Modifier.size(width = 20.dp, height = 10.dp))
            Button(onClick = {
                clickCount1--
                clickCount2--
                sharedPref?.edit {
                    putLong("count1", clickCount2)
                }
                currentTime = setTime(sharedPref)
            }, enabled = if (clickCount2 < 0) bigNumWarning(clickCount2) else true) {
                Text(text = "감소")
            }
            Spacer(modifier = Modifier.size(width = 20.dp, height = 10.dp))
            Button(onClick = { 
                clickCount1 = 0
                currentTime = setTime(sharedPref)
            }) {
                Text(text = "리셋")
            }
        }
        Text(text = "클릭 횟수 : $clickCount1")
        Text(text = "클릭 횟수 : $clickCount2")
        Spacer(modifier = Modifier.size(width = 20.dp, height = 10.dp))
        Row {
            Button(
                onClick = {
                    clickCount1 *= 2;
                    clickCount2 *= 2
                    currentTime = setTime(sharedPref)
                },
                enabled = bigNumWarning(clickCount2),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFac6de4)
                )
            ) {
                Text(text = "2배")
            }
            Spacer(modifier = Modifier.size(20.dp))
            Button(
                onClick = {
                    clickCount1 = (clickCount1 * 0.5).toLong();
                    clickCount2 = (clickCount2 * 0.5).toLong()
                    currentTime = setTime(sharedPref)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFac6de4)
                )
            ) {
                Text(text = "0.5배")
            }
        }
        Spacer(modifier = Modifier.size(20.dp))
        Button(
            onClick = {
                clickCount1 = 0;
                clickCount2 = 0
                currentTime = setTime(sharedPref)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFac6de4)
            )
        ) {
            Text(text = "완전 리셋")
        }
        Spacer(modifier = Modifier.size(20.dp))
        if (bigNumWarning(clickCount2)) {
            Text(text = "")
        } else {
            Text(text = "한계치 도달")
        }
        Spacer(modifier = Modifier.size(20.dp))
        Button(onClick = {
            currentTime = setTime(sharedPref)
        }) {
            Text(text = "시간 저장")
        }
        Spacer(modifier = Modifier.size(20.dp))
        Text(text = currentTime)
    }

}

fun bigNumWarning(input: Long): Boolean {
    if (abs(input) > 100000) {
        return false
    } else return true
}

fun setTime(sharedPrefe: SharedPreferences?) :String {
    val timeNow = LocalDateTime.now().toString()
    sharedPrefe?.edit {
        putString("clicktime", timeNow)
    }
    return timeNow
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    _240521_Practice_2Theme {
        Main()
    }
}
