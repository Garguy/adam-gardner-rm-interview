package com.gardner.adam_gardner_rm_interview.ui.screens.gamelist

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.gardner.adam_gardner_rm_interview.BuildConfig
import com.gardner.adam_gardner_rm_interview.data.ApiResult
import com.gardner.adam_gardner_rm_interview.data.remote.dto.Game
import com.gardner.adam_gardner_rm_interview.data.remote.dto.ImageResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameListScreen(
    viewModel: GameListViewModel,
    navController: NavController
) {
    val gamesState = viewModel.games.collectAsState()
    
    var query by remember { mutableStateOf("") }
    
    Column {
        SearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            hint = "Search for games",
            onSearch = { query = it.trim() },
            delay = 500
        )
        
        when (val result = gamesState.value) {
            is ApiResult.Success -> {
                LazyColumn {
                    items(result.data) { game ->
                        GameListItem(
                            game = game,
                            onItemClick = {
                                navController.navigate("game_details/${game.id}") {
                                    launchSingleTop = true
                                }
                            })
                    }
                }
            }
            
            is ApiResult.Failure -> {
                Text(
                    text = result.message,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )
            }
            
            is ApiResult.Loading -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }
            }
        }
    }
    
    LaunchedEffect(query) {
        withContext(Dispatchers.IO) {
            Log.d("API KEY", BuildConfig.API_KEY)
            viewModel.searchGames(apiKey = "9d45436f87d3848d2bdcce810bacb6df57dfd134", query)
        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    delay: Long,
    onSearch: (String) -> Unit = {}
) {
    var text by remember {
        mutableStateOf("")
    }
    var isHintDisplayed by remember {
        mutableStateOf(hint != "")
    }
    
    LaunchedEffect(text) {
        delay(delay)
        onSearch(text.trim())
    }
    
    Box(modifier = modifier) {
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                onSearch(it)
                Log.d("SearchBar", "Search query: $it")
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = MaterialTheme.colorScheme.onBackground),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(8.dp, shape = CircleShape)
                .background(color = Color.White, shape = CircleShape)
                .padding(horizontal = 20.dp, vertical = 20.dp)
                .onFocusChanged {
                    isHintDisplayed = !it.isFocused && text.isEmpty()
                }
        )
        if (isHintDisplayed) {
            Text(
                text = hint,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp)
            )
        }
    }
}

@Composable
fun GameListItem(game: Game, onItemClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onItemClick)
                .padding(2.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            )
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = game.image?.originalUrl,
                    contentDescription = game.name,
                    modifier = Modifier
                        .size(96.dp)
                        .padding(16.dp)
                        .aspectRatio(1f),
                    contentScale = ContentScale.Crop,
                )
                
                Text(
                    text = game.name,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(start = 16.dp),
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GameListItemPreview() {
    val game = Game(
        id = 1,
        name = "Game Name",
        image = ImageResult("https://via.placeholder.com/150"),
        description = "Game Description"
    )
    GameListItem(game = game) {}
}