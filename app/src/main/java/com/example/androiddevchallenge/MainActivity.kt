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

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.purple500
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp { cat ->
                    val intent = Intent(this, DetailActivity::class.java)
                    intent.putExtra("cat", Gson().toJson(cat))
                    startActivity(intent)
                }
            }
        }
    }
}

// Start building your app here!
@Composable
private fun MyApp(onclick: ((cat: Cat) -> Unit)) {
    Column(Modifier.fillMaxSize().background(Color.White)) {
        Box(modifier = Modifier.height(64.dp).fillMaxWidth().background(purple500)) {
            Text(
                "Puppy adoption", modifier = Modifier.align(Alignment.CenterStart).padding(start = 20.dp),
                style = TextStyle(color = Color.White, fontSize = 20.sp)
            )
        }
        CreatePreviewList(catList = createCats(), onclick)
    }
}

@Composable
private fun CreatePreviewList(catList: ArrayList<Cat>, onclick: ((cat: Cat) -> Unit)) {
    val modifier = Modifier.fillMaxSize().padding(10.dp)
    LazyColumn(modifier) {
        items(catList.size) { index ->
            CreatePreviewItem(catList[index], onclick)
        }
    }
}

@Composable
private fun CreatePreviewItem(cat: Cat, onclick: ((cat: Cat) -> Unit)) {
    Column(Modifier.fillMaxWidth()) {
        Row(
            Modifier.fillMaxWidth()
                .clip(shape = RoundedCornerShape(10.dp))
                .background(Color.LightGray)
                .clickable { onclick.invoke(cat) }
                .padding(10.dp)
        ) {

            Image(
                modifier = Modifier
                    .padding(10.dp)
                    .height(100.dp)
                    .width(100.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .align(Alignment.CenterVertically),
                painter = painterResource(cat.icon),
                contentDescription = "icon"
            )
            Spacer(Modifier.width(10.dp))
            Column(modifier = Modifier.fillMaxWidth().align(Alignment.CenterVertically)) {
                Text(
                    cat.name, modifier = Modifier.align(Alignment.CenterHorizontally),
                    style = TextStyle(color = Color.Black, fontSize = 20.sp)
                )
                Spacer(Modifier.height(10.dp))
                Text(
                    cat.sex, modifier = Modifier.align(Alignment.CenterHorizontally),
                    style = TextStyle(color = Color.Black, fontSize = 15.sp)
                )
                Spacer(Modifier.height(10.dp))
                Text(
                    cat.age, modifier = Modifier.align(Alignment.CenterHorizontally),
                    style = TextStyle(color = Color.Black, fontSize = 15.sp)
                )
                Spacer(Modifier.height(10.dp))
                Text(
                    cat.location, modifier = Modifier.align(Alignment.CenterHorizontally),
                    style = TextStyle(color = Color.Black, fontSize = 15.sp)
                )
            }
        }
        Spacer(Modifier.height(10.dp))
    }
}

fun createCats(): ArrayList<Cat> {
    val cats = ArrayList<Cat>()
    cats.add(
        Cat(
            icon = R.drawable.lyle,
            name = "Lyle",
            breed = "Domestic Short Hair",
            sex = "Male",
            age = "7 years 10 months",
            weight = "8.7 lbs",
            size = "Medium",
            coat = "Short, Smooth",
            color = "Black, White",
            location = "In Foster",
            about = "At this time, community members aren't able to visit the shelter to browse adoption kennels, and adoptions are limited to the virtual process. Lyle here! I am a very quiet and non invasive kind of boy. Even my adorable meow is quiet. I am good with other cats and would love to have a home where I could be a feline companion. I have been in a foster home for quite some time now and have learned to trust my foster mom. I love a good pet session and will even let you pet my tummy when I am in the right mood. I am very accustom to my indoor life and would love to live out my days in that fashion. I am FIV positive but the shelter can talk with you about what that means-I promise my needs are easy. I am asking for a new home that is mellow and has a feline friend. I will need some time to adjust to my new home but I am SO worth it. Please fill out an application for me!"
        )
    )
    cats.add(
        Cat(
            icon = R.drawable.tyson,
            name = "Tyson",
            breed = "Domestic Short Hair",
            sex = "Male",
            age = "4 years 8 months",
            weight = "11 lbs",
            size = "Large",
            coat = "SShort, Smooth",
            color = "Black, White",
            location = "In Foster",
            about = "At this time, community members aren't able to visit the shelter to browse adoption kennels, and adoptions are limited to the virtual process Tyson here! I am a 4 year old male kitty who has had a rough start in life. I have left that life behind me and I am now embracing the life of an indoor kitty. My foster mom has lots of good things to say about me. I seem to enjoy other kitties and think that another friend in my new home might be awesome! I am great with my manners and use the litter box perfectly. I started out a bit shy about people petting me but have since learned how great it is and now I demand that you pet me before I will allow you to pass! I am going to need a special home though. I like the quieter pace of a home without kiddos and I need an understanding family who will go slow with me and get to know me. I do come with crinkled ears which my foster mom and shelter staff think makes me more endearing but it is a reminder of the painful ear infections I had when I came in. They are all healed now but my ears are still scarred. I do also have FIV which will mean that I should be kept inside only to keep me healthy. Please submit an application to learn more about me today!"
        )
    )
    cats.add(
        Cat(
            icon = R.drawable.heathcliff,
            name = "Heathcliff",
            breed = "Domestic Short Hair",
            sex = "Male",
            age = "2 years",
            weight = "10.1 lbs",
            size = "Medium",
            coat = "Short, Smooth",
            color = "Short, Smooth",
            location = "Troutdale Shelter",
            about = "<strong>At this time, community members aren't able to visit the shelter to browse adoption kennels, and adoptions are limited to the virtual process.</strong> My name is Heathcliff and I am ready to find my new family! I came to the shelter as a stray with an injury to my foot. Not much is known about my history or how I received this injury, but I am quickly on the mend with the help of the great staff here at the shelter. I was a little shy when I first arrived here, but I have quickly come out of my shell now that I'm feeling better and have a wonderful purr motor! If you think we could make a great match, please fill out an adoption application for me! I can't wait to meet my new family!"
        )
    )
    cats.add(
        Cat(
            icon = R.drawable.cali,
            name = "Cali",
            breed = "Domestic Longhair",
            sex = "Female",
            age = "13 years",
            weight = "7.1 lbs",
            size = "Medium",
            coat = "Long, Smooth",
            color = "Unspecified",
            location = "Troutdale Shelter",
            about = "<strong>At this time, community members aren't able to visit the shelter to browse adoption kennels, and adoptions are limited to the virtual process.</strong> Cats like me are really sweet with people when we feel at home, especially when we know them well. In new places, we tend to need a little time to figure out the coast is clear. Once I feel at home, I will be your bosom buddy. We usually are not the best in busy households, but we tend to like the company of other cats and make really good movie marathon buddies because one of the safest places we know is with our people!"
        )
    )
    cats.add(
        Cat(
            icon = R.drawable.cinnabunn,
            name = "Cinnabunn",
            breed = "Other",
            sex = "Female",
            age = "1 year 2 months",
            weight = "7 lbs",
            size = "Medium",
            coat = "Short, Smooth",
            color = "Brown",
            location = "Troutdale Shelter » Ask about me at the shelter",
            about = "At this time, community members aren't able to visit the shelter to browse adoption kennels, and adoptions are limited to the virtual process. Cinnabunn was probably not been handled much in her past but is learning that good things can happen like delicious, leafy greens when people gently pick them up. Like many smaller animals, she is happiest with all four feet firmly on the ground. She permits handling but can be anxious when being held. She is curious and friendly and really enjoys her x-pen playtime. Cinnabunn would be best as an only rabbit as she does have some medical concerns that an adoption counselor would be happy to speak with you about. Please ask about her today."
        )
    )
    cats.add(
        Cat(
            icon = R.drawable.bubbles,
            name = "Bubbles",
            breed = "Other",
            sex = "Female",
            age = "7 months",
            weight = "3.7 lbs",
            size = "Medium",
            coat = "Short, Smooth",
            color = "White, Brown",
            location = "Troutdale Shelter » Small Animal 10",
            about = "At this time, community members aren't able to visit the shelter to browse adoption kennels, and adoptions are limited to the virtual process. Bubbles Bubbles is a sweet young bunny who is looking for her new forever home. She enjoys being pet and is learning how to use the litterbox. Apply for this cutie today."
        )
    )
    cats.add(
        Cat(
            icon = R.drawable.sandy,
            name = "Sandy",
            breed = "American Pit Bull Terrier",
            sex = "Female",
            age = "5 years",
            weight = "83.2 lbs",
            size = "Medium",
            coat = "Short, Smooth",
            color = "White, Brown",
            location = "Troutdale Shelter",
            about = "At this time, community members aren't able to visit the shelter to browse adoption kennels, and adoptions are limited to the virtual process. Dogs like me are really sweet with people when we feel at home, especially when we know them well. In new or stressful situations I may bark and make a fuss until I know the coast is clear. Once I trust you we will be bosom buddies. If you are looking for a loyal companion and best friend apply to adopt me today!I need to go to a home with no cats or other small animals."
        )
    )
    return cats
}
