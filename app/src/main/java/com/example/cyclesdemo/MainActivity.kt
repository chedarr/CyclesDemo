package com.example.cyclesdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cyclesdemo.ui.theme.CyclesDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CyclesDemoTheme {
                CyclesScreen()
            }
        }
    }
}


@Composable
fun CustomImage(image: Int, modifier: Modifier = Modifier ){
    Image(
        painter = painterResource(image),
        contentDescription = null,
        modifier
    )
}


@Composable
fun CyclesScreen(modifier: Modifier = Modifier){
    var input by remember{ mutableStateOf("") }
    var result by remember { mutableStateOf("")}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = "Вычисление",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(5.dp))

        CustomImage(
            R.drawable.zadanie,
            Modifier
                .padding(8.dp)
                .width(270.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = input,
            onValueChange = {input = it},
            label = { Text("Введите натуральное число n") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            modifier = Modifier.width(270.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val n = input.toIntOrNull()

                if (n != null && n >= 1){
                    var sum = 0.0
                    var term = 1.0

                    for (i in 1..n){
                        term /= i
                        sum += term
                    }

                    result = "Результат: %.10f".format(
                            java.util.Locale.US,
                            sum
                    )
                }
            },
            modifier = Modifier
                .width(270.dp)
                .height(50.dp)
        ) {
            Text("Вычислить")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = if (result.isBlank()) {
                "Здесь появится результат"
            } else {
                result
            },
            style = MaterialTheme.typography.bodyLarge,
            color = Color(0xFF424242)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CyclesDemoTheme {
        CyclesScreen()
    }
}