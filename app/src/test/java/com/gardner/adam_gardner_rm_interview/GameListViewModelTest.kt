package com.gardner.adam_gardner_rm_interview

import com.gardner.adam_gardner_rm_interview.data.ApiResult
import com.gardner.adam_gardner_rm_interview.data.GameRepository
import com.gardner.adam_gardner_rm_interview.data.remote.dto.Game
import com.gardner.adam_gardner_rm_interview.data.remote.dto.ImageResult
import com.gardner.adam_gardner_rm_interview.ui.screens.gamelist.GameListViewModel
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@OptIn(ExperimentalCoroutinesApi::class)
class GameListViewModelTest {
    
    @Mock
    lateinit var repository: GameRepository
    
    private lateinit var viewModel: GameListViewModel
    
    @Before
    fun setup() {
        Dispatchers.setMain(TestCoroutineDispatcher())
        viewModel = GameListViewModel(repository)
    }
    
    @After
    fun end() {
        Dispatchers.resetMain()
    }
    
    @Test
    fun `searchGames returns list of games`() = runBlocking {
        
        val expectedGames = listOf(
            Game(1L, "Game 1", ImageResult("https://example.com/image1.jpg"), "Description 1"),
            Game(2L, "Game 2", ImageResult("https://example.com/image2.jpg"), "Description 2")
        )
        Mockito.`when`(repository.searchGames("API_KEY", "query"))
            .thenReturn(ApiResult.Success(expectedGames))
        
        viewModel.searchGames("API_KEY", "query")
        
        // Wait for the API to "complete"
        delay(500)
        
        val actualResult = viewModel.games.first()
        
        assertTrue(actualResult is ApiResult.Success)
        
        val actualGames = (actualResult as ApiResult.Success).data
        
        assertEquals(expectedGames, actualGames)
    }
    
    @Test
    fun `searchGames returns empty list on API error`() = runBlocking {
        val expectedErrorMessage = "API error occurred"
        val expectedFailureResult = ApiResult.Failure(expectedErrorMessage)
        Mockito.`when`(repository.searchGames("API_KEY", "query")).thenReturn(expectedFailureResult)
        
        viewModel.searchGames("API_KEY", "query")
        
        val actualResult = viewModel.games.first()
        val actualGames = actualResult as ApiResult.Failure
        
        assertTrue(actualGames.message.isNotEmpty())
        assertTrue(actualGames.message == expectedErrorMessage)
    }
}