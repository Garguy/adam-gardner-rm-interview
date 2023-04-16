package com.gardner.adam_gardner_rm_interview.ui.screens.gamedetails

import android.annotation.SuppressLint
import android.text.Html
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.gardner.adam_gardner_rm_interview.data.GameViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GameDetailScreen(
    gameId: Int,
    viewModel: GameViewModel,
    navController: NavController
) {
    val game = remember { viewModel.getGameById(gameId) }
    
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        item {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = { navController.popBackStack() }
                ) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
                }
                
                Text(
                    text = game?.name ?: "",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .weight(1f)
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
            }
            
            Spacer(modifier = Modifier.height(56.dp))
        }
        item {
            AsyncImage(
                model = game?.image?.originalUrl ?: "https://via.placeholder.com/150",
                contentDescription = game?.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .border(
                        1.dp,
                        MaterialTheme.colorScheme.onSurface,
                        shape = RoundedCornerShape(8.dp)
                    )
            )
        }
        item {
            Spacer(modifier = Modifier.height(56.dp))
        }
        item {
            HtmlText(
                text = game?.description ?: "",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
    
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun HtmlText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current
) {
    val spanned = remember(text) {
        Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT)
    }
    
    val annotatedString = buildAnnotatedString {
        append(spanned)
    }
    
    Text(
        text = annotatedString,
        modifier = modifier,
        style = style
    )
}



