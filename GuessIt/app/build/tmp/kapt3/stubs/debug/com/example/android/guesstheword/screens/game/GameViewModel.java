package com.example.android.guesstheword.screens.game;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010!\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u0011H\u0014J\u0006\u0010\u0013\u001a\u00020\u0011J\u0006\u0010\u0014\u001a\u00020\u0011J\b\u0010\u0015\u001a\u00020\u0011H\u0002R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR \u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\u0007\"\u0004\b\r\u0010\tR\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000fX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/example/android/guesstheword/screens/game/GameViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "score", "Landroidx/lifecycle/MutableLiveData;", "", "getScore", "()Landroidx/lifecycle/MutableLiveData;", "setScore", "(Landroidx/lifecycle/MutableLiveData;)V", "word", "", "getWord", "setWord", "wordList", "", "nextWord", "", "onCleared", "onCorrect", "onSkip", "resetList", "app_debug"})
public final class GameViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.MutableLiveData<java.lang.String> word;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.MutableLiveData<java.lang.Integer> score;
    private java.util.List<java.lang.String> wordList;
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.String> getWord() {
        return null;
    }
    
    public final void setWord(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<java.lang.String> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<java.lang.Integer> getScore() {
        return null;
    }
    
    public final void setScore(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<java.lang.Integer> p0) {
    }
    
    /**
     * Resets the list of words and randomizes the order
     */
    private final void resetList() {
    }
    
    /**
     * Moves to the next word in the list
     */
    private final void nextWord() {
    }
    
    /**
     * Methods for buttons presses
     */
    public final void onSkip() {
    }
    
    public final void onCorrect() {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    public GameViewModel() {
        super();
    }
}