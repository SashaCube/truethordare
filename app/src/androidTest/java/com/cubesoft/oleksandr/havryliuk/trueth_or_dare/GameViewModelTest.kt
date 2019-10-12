package com.cubesoft.oleksandr.havryliuk.trueth_or_dare

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.test.platform.app.InstrumentationRegistry
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.data.content.ContentRepository
import com.cubesoft.oleksandr.havryliuk.trueth_or_dare.data.player.PlayerRepository
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.mock

@RunWith(JUnit4::class)
class GameViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var gameViewModel: GameViewModel
    lateinit var contentRepository: ContentRepository
    lateinit var playerRepository: PlayerRepository
    lateinit var lifecycle: LifecycleRegistry

    @Before
    fun setUp() {
        lifecycle = LifecycleRegistry(mock(LifecycleOwner::class.java))
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)

        contentRepository = ContentRepository()
        playerRepository =
            PlayerRepository(InstrumentationRegistry.getInstrumentation().targetContext)
        gameViewModel = GameViewModel(playerRepository, contentRepository)
    }

    @Test
    fun observe_players() {
        playerRepository.addPlayer("sasha")
        playerRepository.addPlayer("dasha")
        gameViewModel.players.observe({ lifecycle }) {
            assertEquals(listOf("sasha", "dasha"), it)
        }
    }

    @Test
    fun observe_state() {
        gameViewModel.repositoryState.value = STATE_EMPTY
        gameViewModel.repositoryState.observe({ lifecycle }) {
            assertEquals(STATE_EMPTY, it)
        }
    }

    @Test
    fun observe_questions() {
        gameViewModel.questions.value = listOf("question")
        gameViewModel.questions.observe({ lifecycle }) {
            assertEquals(listOf("question"), it)
        }
    }

    @Test
    fun observe_actions() {
        gameViewModel.actions.value = listOf("actions")
        gameViewModel.actions.observe({ lifecycle }) {
            assertEquals(listOf("actions"), it)
        }
    }

    @After
    fun afterTest() {
    }
}