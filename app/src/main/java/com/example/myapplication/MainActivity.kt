package com.example.myapplication

//import android.R.id
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
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.R

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
    val studentID: String,
    val email: String,
    val housingSituation: String,
    val food: String,
    val major: String,
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
            onAbout = { currentScreen = "about" },
            onNext = { currentScreen = "form" }
        )
        "about" -> AboutScreen(
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
fun WelcomeScreen(onAbout: () -> Unit, onNext: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF9C4))
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.san_jose_state_spartans),
            contentDescription = "Sample Image",
            modifier = Modifier
                .size(200.dp)
                .padding(bottom = 16.dp)
        )

        Text(
            text = "Welcome to SJSUConnect!",
            fontSize = 28.sp
        )

        Spacer(modifier = Modifier.height(48.dp))

        Button(onClick = { onAbout() }) {
            Text("About")
        }

        Button(onClick = { onNext() }) {
            Text("Next")
        }
    }
}

// --- About Screen ---
@Composable
fun AboutScreen(onNext: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF9C4))
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
            text = "Our program provides housing through the dorms at SJSU in exchange for community service and a job, allowing you to have a steady income, a place to live, something to put on your resume, while serving your community and helping make San Jose a more beautiful place.",
            fontSize = 18.sp,
            lineHeight = 30.sp,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        Text(
            text = "Simply input your information and you will be able to find specific job positions available for you to apply. You will be matched with an advisor nearby, and will receive help with your journey continuing your education with a focus on helping you grow as a person!",
            fontSize = 18.sp,
            lineHeight = 30.sp
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
    var studentID by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var housingSituation by remember { mutableStateOf("") }
    var food by remember { mutableStateOf("") }
    var major by remember { mutableStateOf("") }
    var physicalCapabilities by remember { mutableStateOf("") }
    var advisor by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF9C4))
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextField(
                value = location,
                onValueChange = { location = it },
                placeholder = { Text("Location") },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 4.dp)
            )
            TextField(
                value = age,
                onValueChange = { age = it },
                placeholder = { Text("Age") },
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 4.dp)
            )
        }

        TextField(
            value = studentID,
            onValueChange = { studentID = it },
            placeholder = { Text("Student ID") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        TextField(
            value = email,
            onValueChange = { email = it },
            placeholder = { Text("SJSU Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = housingSituation,
            onValueChange = { housingSituation = it },
            placeholder = { Text("Housing Situation") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        TextField(
            value = food,
            onValueChange = { food = it },
            placeholder = { Text("Are you experiencing food insecurity?") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        TextField(
            value = major,
            onValueChange = { major = it },
            placeholder = { Text("Major") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        TextField(
            value = physicalCapabilities,
            onValueChange = { physicalCapabilities = it },
            placeholder = { Text("Do you have any physical incapabilities?") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        TextField(
            value = advisor,
            onValueChange = { advisor = it },
            placeholder = { Text("Advisor") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {
            val profile = ProfileData(
                name, location, age, studentID,
                email, housingSituation, food, major,
                physicalCapabilities, advisor
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
            .background(Color(0xFFFFF9C4))
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
            fontSize = 15.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Name: ${profile.name}", fontSize = 20.sp)
        Text(text = "Location: ${profile.location}", fontSize = 16.sp)
        Text(text = "Age: ${profile.age}", fontSize = 16.sp)
        Text(text = "Student ID: ${profile.studentID}", fontSize = 16.sp)
        Text(text = "Housing: ${profile.housingSituation}", fontSize = 16.sp)
        Text(text = "Are you experiencing food insecurity? ${profile.food}", fontSize = 16.sp)
        Text(text = "Major: ${profile.major}", fontSize = 16.sp)
        Text(text = "Do you have any physical incapabilities? ${profile.physicalCapabilities}", fontSize = 16.sp)
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
            .background(Color(0xFFFFF9C4))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Available Job Positions", fontSize = 24.sp)

        Spacer(modifier = Modifier.height(24.dp))

        Row {
            Text(text = "Library Assistant")
            Spacer(modifier = Modifier.width(22.dp))
            Text(text = "Food Court Worker")
        }

        Row {
            Text(text = "Campus Tour Guide")
            Spacer(modifier = Modifier.width(22.dp))
            Text(text = "Research Lab Helper")
        }

        Row {
            Text(text = "Teacher Assistant")
            Spacer(modifier = Modifier.width(22.dp))
            Text(text = "Community Helper")
        }

        Row {
            Text(text = "Ice Cream Server")
            Spacer(modifier = Modifier.width(22.dp))
            Text(text = "Garden Center Helper")
        }

        Row {
            Text(text = "Cashier")
            Spacer(modifier = Modifier.width(22.dp))
            Text(text = "Dog Walker")
        }

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
            .background(Color(0xFFFFF9C4))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Available Advisors", fontSize = 24.sp)

        Spacer(modifier = Modifier.height(24.dp))



        Text(text = "John Doe - Academic Support")
        Text(text = "Lynn Smith - Career Counseling")
        Text(text = "Jeff Johnson - Housing Assistance")
        Text(text = "Blake Anderson - Housing Assistance")
        Text(text = "Sam Kim - Housing Assistance")
        Text(text = "Andy Mason - Housing Assistance")
        Text(text = "Drew Stock - Housing Assistance")

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
