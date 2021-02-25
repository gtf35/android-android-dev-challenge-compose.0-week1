/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.purple500
import com.google.gson.Gson

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val cat = Gson().fromJson(intent.getStringExtra("cat"), Cat::class.java)
        setContent {
            MyTheme {
                DetailScreen(cat)
            }
        }
    }
}

@Composable
private fun DetailScreen(cat: Cat) {
    Column(Modifier.fillMaxSize().background(Color.White)) {
        Box(modifier = Modifier.height(64.dp).fillMaxWidth().background(purple500)) {
            Text(
                cat.name, modifier = Modifier.align(Alignment.CenterStart).padding(start = 20.dp),
                style = TextStyle(color = Color.White, fontSize = 20.sp)
            )
        }
        Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
            Image(
                modifier = Modifier
                    .padding(10.dp)
                    .width(250.dp).height(250.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .align(Alignment.CenterHorizontally),
                painter = painterResource(cat.icon),
                contentDescription = "icon"
            )

            CreateHeader("Information")
            CreateLine(key = "Breed", value = cat.breed)
            CreateLine(key = "Sex", value = cat.sex)
            CreateLine(key = "Age", value = cat.age)
            CreateLine(key = "Weight", value = cat.weight)
            CreateLine(key = "Size", value = cat.size)
            CreateLine(key = "Coat", value = cat.coat)
            CreateLine(key = "Color", value = cat.color)
            CreateLine(key = "Location", value = cat.location)

            CreateHeader("About")
            Text(text = cat.about, modifier = Modifier.fillMaxWidth().padding(10.dp), textAlign = TextAlign.Start)
        }
    }
}

@Composable
private fun CreateHeader(title: String) {
    Box(modifier = Modifier.height(30.dp).fillMaxWidth().background(Color.LightGray)) {
        Text(
            title, modifier = Modifier.align(Alignment.CenterStart).padding(start = 15.dp),
            style = TextStyle(color = Color.Black, fontSize = 15.sp)
        )
    }
}

@Composable
private fun ColumnScope.CreateLine(key: String, value: String) {
    Column(Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)) {
        Spacer(modifier = Modifier.height(10.dp))
        Row {
            Spacer(modifier = Modifier.width(20.dp))
            Text("$key:$value", style = TextStyle(color = Color.Black, fontSize = 15.sp))
        }
    }
}
