package org.sportradar;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.sportradar.Wordle.checkIfGuessedWordMatches;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WordleTest {

    @Test
    void testCheckIfGuessedWordMatchesRepeatingChar() {
        Map<Integer,Status> map = checkIfGuessedWordMatches("WATER","OTTER");
        Map<Integer,Status> expectedResults = new HashMap<Integer,Status>();
        expectedResults.put(2,Status.GREEN);
        expectedResults.put(3,Status.GREEN);
        expectedResults.put(4,Status.GREEN);
        assertEquals(map,expectedResults);
    }
    @Test
    void testCheckIfGuessedWordMatchesRepeatingCharThrice() {
        Map<Integer,Status> map = checkIfGuessedWordMatches("WATET","TOTET");
        Map<Integer,Status> expectedResults = new HashMap<Integer,Status>();
        expectedResults.put(2,Status.GREEN);
        expectedResults.put(3,Status.GREEN);
        expectedResults.put(4,Status.GREEN);
        assertEquals(map,expectedResults);
    }

    @Test
    void testCheckIfGuessedWordMatches() {
        Map<Integer,Status> map = checkIfGuessedWordMatches("WATER","PUTAR");
        Map<Integer,Status> expectedResults = new HashMap<Integer,Status>();
        expectedResults.put(2,Status.GREEN);
        expectedResults.put(3,Status.YELLOW);
        expectedResults.put(4,Status.GREEN);
        assertEquals(map,expectedResults);
    }
    @Test
    void testCheckIfGuessedWordMatchesGreenOnlyCheck() {
        Map<Integer,Status> map = checkIfGuessedWordMatches("HOUND","HOUND");
        Map<Integer,Status> expectedResults = new HashMap<Integer,Status>();
        expectedResults.put(0,Status.GREEN);
        expectedResults.put(1,Status.GREEN);
        expectedResults.put(2,Status.GREEN);
        expectedResults.put(3,Status.GREEN);
        expectedResults.put(4,Status.GREEN);
        assertEquals(map,expectedResults);
    }

    @Test
    void testCheckIfGuessedWordMatchesYellowOnlyCheck() {
        Map<Integer,Status> map = checkIfGuessedWordMatches("HOUND","DNHOU");
        Map<Integer,Status> expectedResults = new HashMap<Integer,Status>();
        expectedResults.put(0,Status.YELLOW);
        expectedResults.put(1,Status.YELLOW);
        expectedResults.put(2,Status.YELLOW);
        expectedResults.put(3,Status.YELLOW);
        expectedResults.put(4,Status.YELLOW);
        assertEquals(map,expectedResults);
    }
}