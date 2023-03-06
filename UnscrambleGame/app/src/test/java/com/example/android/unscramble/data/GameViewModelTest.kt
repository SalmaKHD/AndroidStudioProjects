package com.example.android.unscramble.data

import com.example.android.unscramble.ui.GameViewModel
import junit.framework.TestCase.*
import org.junit.Assert.assertNotEquals
import org.junit.Test

class GameViewModelTest {
    // define the object the test will be running against
    private val viewModel = GameViewModel()

    // Write a function for the success path
    @Test
    fun gameViewModel_CorrectWordGuessed_ScoreUpdatedAndErrorFlagUnset()  {
        var currentGameUiState = viewModel.uiState.value
        val correctPlayerWord = getUnscrambledWord(currentGameUiState.currentScrambledWord)
        viewModel.updateUserGuess(correctPlayerWord)
        viewModel.checkUserGuess()

        currentGameUiState = viewModel.uiState.value

        // create a test that checks if the isGuessWordWrong property's value is actually false
        assertFalse(currentGameUiState.isGuessedWordWrong)
        // create a test that checks if the current score is the expected score
        assertEquals(20, currentGameUiState.score)
    }

    // write a function for the error path
    @Test
    fun gameViewModel_IncorrectGuess_ErrorFlagSet() {
        // define an incorrect guess
        val incorrectPlayerWord = "and"
        // set the userCheck to the incorrect guess
        viewModel.updateUserGuess(incorrectPlayerWord)
        // carry out all the operations for checking if the guess is correct or not
        // modify state as necessary
        viewModel.checkUserGuess()

        // fetch the current UI state (the ViewModel will have an initial state
        // this ViewModel is a new object
        val uiState = viewModel.uiState.value

        // check if this property in the ViewModel class has been set to false in fact
        assertTrue(uiState.isGuessedWordWrong)

        // check if the current score has not changed
        assertEquals(uiState.score, 0)
    }

    // run test for boundary path
    // this will check if the initial state of the ViewModel is correct
    // upon being initialized
    @Test
    fun gameViewModel_Initialization_FirstWordLoaded() {
        val uiState = viewModel.uiState.value

        val unScrambledWord = getUnscrambledWord(uiState.currentScrambledWord)
        // Assert that current word is scrambled.
        assertNotEquals(unScrambledWord, uiState.currentScrambledWord)
        // check if the initial value of the currentWordCount is 1
        assertEquals(1, uiState.currentWordCount)
        // same logic applies
        assertEquals(0, uiState.score)
        assertFalse(uiState.isGuessedWordWrong)
        assertFalse(uiState.isGameOver)
    }

    // write test for boundary case2
    @Test
    fun gameViewModel_AllWordsGuessed_UiStateUpdatedCorrectly() {
        // this function assumes the user guesses all the words correctly
        var expectedScore = 0
        // get the initial UI state
        var currentGameUiState = viewModel.uiState.value

        // get the unscrambled word using a utility function in WordsData
        var correctPlayerWord = getUnscrambledWord(currentGameUiState.currentScrambledWord)

        // define a repeat block to simulate running multiple guesses
        repeat(MAX_NO_OF_WORDS) {
            // increase the score number
            expectedScore += SCORE_INCREASE
            // update the user guess to the correct guess
            viewModel.updateUserGuess(correctPlayerWord)
            // check the user guess in the ViewModel
            viewModel.checkUserGuess()
            // update the values of the local properties
            currentGameUiState = viewModel.uiState.value
            correctPlayerWord = getUnscrambledWord(currentGameUiState.currentScrambledWord)
            // check if the current value of the score is as expected
            assertEquals(expectedScore, currentGameUiState.score)
        }
        // check if the current number of games played is equal to the MAX number allowed
        assertEquals(MAX_NO_OF_WORDS, currentGameUiState.currentWordCount)

        assertTrue(currentGameUiState.isGameOver)
    }

    // test the case in which the user presses the skip button
    @Test
    fun gameViewModel_WordSkipped_ScoreUnchangedAndWordCountIncreased() {
        var currentGameUiState = viewModel.uiState.value
        val correctPlayerWord = getUnscrambledWord(currentGameUiState.currentScrambledWord)
        viewModel.updateUserGuess(correctPlayerWord)
        viewModel.checkUserGuess()

        currentGameUiState = viewModel.uiState.value
        val lastWordCount = currentGameUiState.currentWordCount
        viewModel.skipWord()
        currentGameUiState = viewModel.uiState.value
        // Assert that score remains unchanged after word is skipped.
        assertEquals(SCORE_AFTER_FIRST_CORRECT_ANSWER, currentGameUiState.score)
        // Assert that word count is increased by 1 after word is skipped.
        assertEquals(lastWordCount + 1, currentGameUiState.currentWordCount)
    }

    companion object {
        private val SCORE_AFTER_FIRST_CORRECT_ANSWER = SCORE_INCREASE
    }

}