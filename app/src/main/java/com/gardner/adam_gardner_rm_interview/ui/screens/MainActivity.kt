package com.gardner.adam_gardner_rm_interview.ui.screens

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.gardner.adam_gardner_rm_interview.ui.Routes
import com.gardner.adam_gardner_rm_interview.ui.screens.gamedetails.GameDetailScreen
import com.gardner.adam_gardner_rm_interview.ui.screens.gamelist.GameListScreen
import com.gardner.adam_gardner_rm_interview.data.GameViewModel
import com.gardner.adam_gardner_rm_interview.ui.theme.AdamgardnerrminterviewTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: GameViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNav(viewModel)
        }
    }
}

@Composable
fun AppNav(viewModel: GameViewModel) {
    val navController = rememberNavController()
    
    
    NavHost(
        navController = navController,
        startDestination = Routes.GameList.route
    ) {
        composable(route = Routes.GameList.route) {
            GameListScreen(viewModel = viewModel, navController = navController)
        }
        composable(
            route = Routes.GameDetails.route,
            arguments = listOf(navArgument("gameId") { type = NavType.IntType })
        ) { backStackEntry ->
            val gameId = backStackEntry.arguments?.getInt("gameId")
            if (gameId != null) {
                GameDetailScreen(
                    gameId = gameId,
                    viewModel = viewModel,
                    navController = navController
                )
            } else {
                Toast.makeText(
                    LocalContext.current,
                    "We're sorry, something went wrong",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}