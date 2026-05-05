package ru.yandex.practicum.sleeptracker.analiticfunctions;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepQuality;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CountSleeplessNightsTest {

    @Test
    void testCountSleeplessWhenEmptySessionList() {
        List<SleepingSession> emptyList = new LinkedList<>();
        SleepAnalysisResult sleepAnalysisResult = new CountSleeplessNights().apply(emptyList);
        assertNull(sleepAnalysisResult.getResult(),
                "Неверный результат количества бессонных ночей при пустом списке.");
    }

    @Test
    void testCountSleeplessWhenOneSleeplessNight() {
        List<SleepingSession> oneSleeplessNight = new LinkedList<>();
        oneSleeplessNight.add(new SleepingSession(
                LocalDateTime.of(2026,5,4,7,14,0),
                LocalDateTime.of(2026,5,4,14,14,0),
                SleepQuality.BAD));
        SleepAnalysisResult sleepAnalysisResult = new CountSleeplessNights().apply(oneSleeplessNight);
        assertEquals(1L, sleepAnalysisResult.getResult(),
                "Неверный результат количества бессонных ночей при одной бессонной ночи.");
    }

    @Test
    void testCountSleeplessWhenFewSleeplessNight() {
        List<SleepingSession> sleepingSessions = new LinkedList<>();
        sleepingSessions.add(new SleepingSession(
                LocalDateTime.of(2026,5,4,7,14,0),
                LocalDateTime.of(2026,5,4,14,14,0),
                SleepQuality.BAD));
        sleepingSessions.add(new SleepingSession(
                LocalDateTime.of(2026,5,5,23,14,0),
                LocalDateTime.of(2026,5,6,14,14,0),
                SleepQuality.BAD));
        sleepingSessions.add(new SleepingSession(
                LocalDateTime.of(2026,5,6,22,14,0),
                LocalDateTime.of(2026,5,7,4,14,0),
                SleepQuality.BAD));
        sleepingSessions.add(new SleepingSession(
                LocalDateTime.of(2026,5,9,23,59,0),
                LocalDateTime.of(2026,5,10,8,0,0),
                SleepQuality.BAD));
        sleepingSessions.add(new SleepingSession(
                LocalDateTime.of(2026,5,12,9,14,0),
                LocalDateTime.of(2026,5,12,18,0,0),
                SleepQuality.BAD));
        SleepAnalysisResult sleepAnalysisResult = new CountSleeplessNights().apply(sleepingSessions);
        assertEquals(6L, sleepAnalysisResult.getResult(),
                "Неверный результат количества бессонных ночей при нескольких бессонных ночах.");
    }

    @Test
    void testCountSleeplessWhenAllSleeplessNight() {
        List<SleepingSession> sleepingSessions = new LinkedList<>();
        sleepingSessions.add(new SleepingSession(
                LocalDateTime.of(2026,5,4,7,14,0),
                LocalDateTime.of(2026,5,4,14,14,0),
                SleepQuality.BAD));
        sleepingSessions.add(new SleepingSession(
                LocalDateTime.of(2026,5,6,6,14,0),
                LocalDateTime.of(2026,5,6,14,14,0),
                SleepQuality.BAD));
        sleepingSessions.add(new SleepingSession(
                LocalDateTime.of(2026,5,7,22,14,0),
                LocalDateTime.of(2026,5,7,23,14,0),
                SleepQuality.BAD));
        sleepingSessions.add(new SleepingSession(
                LocalDateTime.of(2026,5,10,7,59,0),
                LocalDateTime.of(2026,5,10,8,0,0),
                SleepQuality.BAD));
        sleepingSessions.add(new SleepingSession(
                LocalDateTime.of(2026,5,12,9,14,0),
                LocalDateTime.of(2026,5,12,18,0,0),
                SleepQuality.BAD));
        SleepAnalysisResult sleepAnalysisResult = new CountSleeplessNights().apply(sleepingSessions);
        assertEquals(9L, sleepAnalysisResult.getResult(),
                "Неверный результат количества бессонных ночей для случая когда все ночи бессонные.");
    }
}