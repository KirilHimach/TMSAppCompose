package com.example.tmsappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tmsappcompose.ui.theme.TMSAppComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TMSAppComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    FirstScreen()
                }
            }
        }
    }
}

@Preview
@Composable
fun FirstScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome to the app",
            fontSize = 26.sp,
            fontStyle = FontStyle.Italic
        )
        val username = inputUsername()
        val password =  inputPassword()
        Button(
            onClick = { /*TODO*/ },
            enabled = username.isNotEmpty() && password.length >= 8,
            modifier = Modifier.padding(vertical = 12.dp)
        ) {
            Text(text = "Log in")
        }
    }
}

@Composable
fun inputUsername() : String {
    var username by remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        value = username,
        onValueChange = { username = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 34.dp),
        label = { Text(text = "Username") },
        placeholder = { Text(text = "Username or password") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        leadingIcon = { Icon(imageVector = Icons.Filled.Email, null) },
        trailingIcon = {
            IconButton(onClick = { username = "" }) {
                Icon(imageVector = Icons.Filled.Clear, null)
            }
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Green,
            focusedLabelColor = Color.Blue,
            unfocusedBorderColor = Color.Gray,
            unfocusedLabelColor = Color.Gray,
            leadingIconColor = Color.Black
        )
    )
    return username
}

@Composable
fun inputPassword() : String {
    var password by remember {
        mutableStateOf("")
    }
    var visibilityPassword by remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        value = password,
        onValueChange = { password = it },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 34.dp),
        label = { Text(text = "Password") },
        placeholder = { Text(text = "Password") },
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        leadingIcon = { Icon(imageVector = Icons.Filled.Lock, null) },
        trailingIcon = {
            val image = if (visibilityPassword)
                Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff

            IconButton(onClick = { visibilityPassword = !visibilityPassword }) {
                Icon(imageVector = image, null)
            }
        },
        visualTransformation = if (visibilityPassword)
            VisualTransformation.None
        else PasswordVisualTransformation(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Green,
            focusedLabelColor = Color.Cyan,
            unfocusedBorderColor = Color.Gray,
            unfocusedLabelColor = Color.Gray,
            leadingIconColor = Color.Black
        )
    )
    return password
}