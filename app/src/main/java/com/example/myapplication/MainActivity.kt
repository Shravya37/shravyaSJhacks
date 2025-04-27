package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    MainScreen()
                }
            }
        }
    }
}

// --- Profile Data Model ---
data class ProfileData(
    val name: String,
    val location: String,
    val age: String,
    val housingSituation: String,
    val food: String,
    val major: String,
    val careerGoals: String,
    val physicalCapabilities: String,
    val advisor: String
)

// --- Main Screen ---
@Composable
fun MainScreen() {
    var currentScreen by remember { mutableStateOf("welcome") }
    var profile by remember { mutableStateOf<ProfileData?>(null) }

    when (currentScreen) {
        "welcome" -> WelcomeScreen(
            onNext = { currentScreen = "form" }
        )
        "form" -> ProfileForm(
            onSubmit = { filledProfile ->
                profile = filledProfile
                currentScreen = "profile"
            }
        )
        "profile" -> {
            profile?.let {
                ProfileView(
                    profile = it,
                    onJobsClick = { currentScreen = "jobs" },
                    onAdvisorsClick = { currentScreen = "advisors" },
                    onHomeClick = { currentScreen = "welcome" }
                )
            }
        }
        "jobs" -> {
            JobsScreen(
                onBack = { currentScreen = "profile" },
                onHomeClick = { currentScreen = "welcome" }
            )
        }
        "advisors" -> {
            AdvisorsScreen(
                onBack = { currentScreen = "profile" },
                onHomeClick = { currentScreen = "welcome" }
            )
        }
    }
}

// --- Welcome Screen ---
@Composable
fun WelcomeScreen(onNext: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome to SJSUConnect!",
            fontSize = 28.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "A safe place to find on-campus jobs, housing, and connect with other students!",
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Text(
            text = "Our goal is to help you succeed at SJSU through these difficult times.",
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(48.dp))

        Button(onClick = { onNext() }) {
            Text("Next")
        }
    }
}

// --- Profile Form ---
@Composable
fun ProfileForm(onSubmit: (ProfileData) -> Unit) {
    var name by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var housingSituation by remember { mutableStateOf("") }
    var food by remember { mutableStateOf("") }
    var major by remember { mutableStateOf("") }
    var careerGoals by remember { mutableStateOf("") }
    var physicalCapabilities by remember { mutableStateOf("") }
    var advisor by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Gray, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Photo", color = Color.White)
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = name,
            onValueChange = { name = it },
            placeholder = { Text("Name") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextField(
                value = location,
                onValueChange = { location = it },
                placeholder = { Text("Location") },
                modifier = Modifier.weight(1f).padding(end = 4.dp)
            )
            TextField(
                value = age,
                onValueChange = { age = it },
                placeholder = { Text("Age") },
                modifier = Modifier.weight(1f).padding(start = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = housingSituation,
            onValueChange = { housingSituation = it },
            placeholder = { Text("Housing situation") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )

        TextField(
            value = food,
            onValueChange = { food = it },
            placeholder = { Text("Food Plan") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )

        TextField(
            value = major,
            onValueChange = { major = it },
            placeholder = { Text("Major") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )

        TextField(
            value = careerGoals,
            onValueChange = { careerGoals = it },
            placeholder = { Text("Career goals") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )

        TextField(
            value = physicalCapabilities,
            onValueChange = { physicalCapabilities = it },
            placeholder = { Text("Physical capabilities") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )

        TextField(
            value = advisor,
            onValueChange = { advisor = it },
            placeholder = { Text("Advisor") },
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {
            val profile = ProfileData(
                name, location, age,
                housingSituation, food, major,
                careerGoals, physicalCapabilities, advisor
            )
            onSubmit(profile)
        }) {
            Text("Submit")
        }
    }
}

// --- Profile View ---
@Composable
fun ProfileView(profile: ProfileData, onJobsClick: () -> Unit, onAdvisorsClick: () -> Unit, onHomeClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Gray, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Photo", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Thank you for applying, we will contact you shortly",
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Name: ${profile.name}", fontSize = 20.sp)
        Text(text = "Location: ${profile.location}", fontSize = 16.sp)
        Text(text = "Age: ${profile.age}", fontSize = 16.sp)
        Text(text = "Housing: ${profile.housingSituation}", fontSize = 16.sp)
        Text(text = "Food Plan: ${profile.food}", fontSize = 16.sp)
        Text(text = "Major: ${profile.major}", fontSize = 16.sp)
        Text(text = "Career Goals: ${profile.careerGoals}", fontSize = 16.sp)
        Text(text = "Physical Capabilities: ${profile.physicalCapabilities}", fontSize = 16.sp)
        Text(text = "Advisor: ${profile.advisor}", fontSize = 16.sp)

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { onAdvisorsClick() }) {
                Text("Advisors")
            }
            Button(onClick = { onJobsClick() }) {
                Text("Job Positions")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { onHomeClick() }) {
            Text("Back to Home")
        }
    }
}

// --- Jobs Screen ---
@Composable
fun JobsScreen(onBack: () -> Unit, onHomeClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Available Job Positions", fontSize = 24.sp)

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "• Library Assistant")
        Text(text = "• Food Court Worker")
        Text(text = "• Campus Tour Guide")
        Text(text = "• Research Lab Helper")
        Text(text = "• Teacher Assistant")
        Text(text = "• Community Helper")
        Text(text = "• Part-Time Security")

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "To apply for these jobs, please check out SJSU Careers and fill out the application.")

        Spacer(modifier = Modifier.height(48.dp))

        Button(onClick = { onBack() }) {
            Text("Back")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { onHomeClick() }) {
            Text("Back to Home")
        }
    }
}

// --- Advisors Screen ---
@Composable
fun AdvisorsScreen(onBack: () -> Unit, onHomeClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Available Advisors", fontSize = 24.sp)

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "• John Doe - Academic Support")
        Text(text = "• Lynn Smith - Career Counseling")
        Text(text = "• Jeff Johnson - Housing Assistance")
        Text(text = "• Blake Anderson - Housing Assistance")
        Text(text = "• Sam Kim - Housing Assistance")
        Text(text = "• Andy Mason - Housing Assistance")
        Text(text = "• Drew Stock - Housing Assistance")

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Once our team contact you shortly, you will be able to speak to one of our advisors.")

        Spacer(modifier = Modifier.height(48.dp))

        Button(onClick = { onBack() }) {
            Text("Back")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { onHomeClick() }) {
            Text("Back to Home")
        }
    }
}
