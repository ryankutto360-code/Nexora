package com.nexora.tv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Midnight = Color(0xFF080B18)
private val Panel = Color(0xFF11182B)
private val Blue = Color(0xFF4B8DFF)
private val Violet = Color(0xFF9B72FF)
private val Muted = Color(0xFF9AA8C5)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = android.graphics.Color.rgb(8, 11, 24)
        window.navigationBarColor = android.graphics.Color.rgb(8, 11, 24)
        setContent { NexoraApp() }
    }
}

@Composable
private fun NexoraApp() {
    var page by remember { mutableStateOf("Home") }
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = Midnight) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.linearGradient(
                            listOf(Color(0xFF101B39), Midnight, Color(0xFF160F2C))
                        )
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 54.dp, vertical = 30.dp)
                ) {
                    Header(page = page, onNavigate = { page = it })
                    Spacer(Modifier.height(28.dp))
                    AnimatedContent(
                        targetState = page,
                        transitionSpec = { fadeIn() togetherWith fadeOut() },
                        label = "page-transition"
                    ) { target ->
                        when (target) {
                            "Home" -> HomePage()
                            "Movies" -> CategoryPage("Movies", "Your next great story starts here.", listOf("Featured premieres", "Continue watching", "New this week"))
                            "Series" -> CategoryPage("Series", "Binge-worthy worlds, all in one place.", listOf("Popular series", "New episodes", "Recommended"))
                            "Apps" -> CategoryPage("Apps", "Your entertainment apps will appear here.", listOf("Installed apps", "Recently used", "Discover"))
                            "Settings" -> SettingsPage()
                            else -> HomePage()
                        }
                    }
                }
                Text(
                    "NEXORA  •  CONCEPT BUILD 0.1",
                    color = Muted.copy(alpha = 0.55f),
                    fontSize = 10.sp,
                    letterSpacing = 2.sp,
                    modifier = Modifier.align(Alignment.BottomEnd).padding(22.dp)
                )
            }
        }
    }
}

@Composable
private fun Header(page: String, onNavigate: (String) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
        Column {
            Text("NEXORA", color = Color.White, fontSize = 25.sp, fontWeight = FontWeight.Black, letterSpacing = 4.sp)
            Text("YOUR WORLD. ONE SCREEN.", color = Blue, fontSize = 9.sp, letterSpacing = 2.sp)
        }
        Spacer(Modifier.weight(1f))
        listOf("Home", "Movies", "Series", "Apps", "Settings").forEach { item ->
            NavItem(item, selected = page == item, onClick = { onNavigate(item) })
            Spacer(Modifier.width(7.dp))
        }
    }
}

@Composable
private fun NavItem(label: String, selected: Boolean, onClick: () -> Unit) {
    var focused by remember { mutableStateOf(false) }
    val color by animateColorAsState(
        if (selected || focused) Color.White else Muted,
        label = "nav-color"
    )
    Box(
        modifier = Modifier
            .onFocusChanged { focused = it.isFocused }
            .background(if (selected || focused) Blue.copy(alpha = 0.18f) else Color.Transparent, RoundedCornerShape(14.dp))
            .border(if (focused) 2.dp else 1.dp, if (selected || focused) Blue.copy(alpha = 0.8f) else Color.Transparent, RoundedCornerShape(14.dp))
            .clickable(onClick = onClick)
            .focusable()
            .padding(horizontal = 14.dp, vertical = 10.dp)
    ) {
        Text(label, color = color, fontSize = 12.sp, fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium)
    }
}

@Composable
private fun HomePage() {
    Column {
        Text("MONDAY  •  YOUR ENTERTAINMENT HUB", color = Muted, fontSize = 10.sp, letterSpacing = 2.sp)
        Spacer(Modifier.height(13.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text("A universe of", color = Color.White, fontSize = 34.sp, fontWeight = FontWeight.Light)
                Text("possibilities.", color = Color.White, fontSize = 42.sp, fontWeight = FontWeight.ExtraBold)
                Spacer(Modifier.height(12.dp))
                Text("Entertainment, apps and experiences — beautifully together.", color = Muted, fontSize = 14.sp)
                Spacer(Modifier.height(22.dp))
                FocusTile("EXPLORE NEXORA", "Discover your home screen", Blue, modifier = Modifier.width(220.dp))
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(205.dp)
                    .background(
                        Brush.linearGradient(listOf(Color(0xFF2859B8), Color(0xFF633BC1), Color(0xFF141B3A))),
                        RoundedCornerShape(28.dp)
                    )
                    .border(1.dp, Color.White.copy(alpha = 0.18f), RoundedCornerShape(28.dp)),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("N", color = Color.White, fontSize = 92.sp, fontWeight = FontWeight.Black)
                    Text("THE FUTURE OF TV", color = Color.White.copy(alpha = 0.85f), fontSize = 10.sp, letterSpacing = 4.sp)
                }
            }
        }
        Spacer(Modifier.height(27.dp))
        Text("QUICK ACCESS", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold, letterSpacing = 2.sp)
        Spacer(Modifier.height(12.dp))
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(14.dp),
            contentPadding = PaddingValues(end = 10.dp)
        ) {
            items(
                listOf(
                    Triple("Movies", "Your cinema", Color(0xFF254D91)),
                    Triple("Series", "Stories to follow", Color(0xFF563A91)),
                    Triple("Apps", "All your apps", Color(0xFF176D78)),
                    Triple("Settings", "Make it yours", Color(0xFF35445F))
                )
            ) { item ->
                FocusTile(item.first, item.second, item.third, modifier = Modifier.width(190.dp).height(92.dp))
            }
        }
    }
}

@Composable
private fun FocusTile(
    title: String,
    subtitle: String,
    accent: Color,
    modifier: Modifier = Modifier
) {
    var focused by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(if (focused) 1.035f else 1f, label = "tile-scale")
    Column(
        modifier = modifier
            .scale(scale)
            .onFocusChanged { focused = it.isFocused }
            .background(
                Brush.linearGradient(listOf(accent.copy(alpha = 0.48f), Panel)),
                RoundedCornerShape(18.dp)
            )
            .border(if (focused) 2.dp else 1.dp, if (focused) Color.White else Color.White.copy(alpha = 0.10f), RoundedCornerShape(18.dp))
            .focusable()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(title, color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(5.dp))
        Text(subtitle, color = Muted, fontSize = 10.sp)
    }
}

@Composable
private fun CategoryPage(title: String, subtitle: String, sections: List<String>) {
    Column {
        Text(title, color = Color.White, fontSize = 38.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        Text(subtitle, color = Muted, fontSize = 14.sp)
        Spacer(Modifier.height(30.dp))
        sections.forEachIndexed { index, section ->
            Text(section.uppercase(), color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold, letterSpacing = 2.sp)
            Spacer(Modifier.height(10.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                repeat(3) { item ->
                    FocusTile("Coming soon", "Nexora content library", if (index % 2 == 0) Blue else Violet, Modifier.weight(1f).height(92.dp))
                }
            }
            Spacer(Modifier.height(22.dp))
        }
    }
}

@Composable
private fun SettingsPage() {
    Column {
        Text("Settings", color = Color.White, fontSize = 38.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        Text("Nexora preferences and device information", color = Muted, fontSize = 14.sp)
        Spacer(Modifier.height(24.dp))
        listOf(
            "Appearance" to "Midnight • Electric blue • Violet",
            "Display" to "Designed for Full HD television screens",
            "Remote" to "Directional navigation enabled",
            "About" to "Nexora TV concept build 0.1.0"
        ).forEach { (heading, detail) ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
                    .background(Panel.copy(alpha = 0.9f), RoundedCornerShape(16.dp))
                    .border(1.dp, Color.White.copy(alpha = 0.08f), RoundedCornerShape(16.dp))
                    .padding(18.dp)
            ) {
                Text(heading, color = Color.White, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                Spacer(Modifier.height(5.dp))
                Text(detail, color = Muted, fontSize = 12.sp)
            }
        }
        Text("System controls and voice assistant are planned features, not connected in this prototype.", color = Muted, fontSize = 11.sp)
    }
}
