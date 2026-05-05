package ru.yandex.practicum.sleeptracker.analiticfunctions;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepQuality;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SleepingSessionTest {

    private static List<SleepingSession> testSleepingSessions;

    @BeforeAll
    static void beforeAll() {
        testSleepingSessions = List.of(
                new SleepingSession(LocalDateTime.now(), LocalDateTime.now().plusMinutes(10), SleepQuality.BAD),
                new SleepingSession(LocalDateTime.now(), LocalDateTime.now().plusMinutes(20), SleepQuality.GOOD),
                new SleepingSession(LocalDateTime.now(), LocalDateTime.now().plusMinutes(30), SleepQuality.NORMAL));
    }
    
    @Test
    void testSessionCountWhenThreeSessions() {
        SleepAnalysisResult sleepAnalysisResult =  new SessionsCount().apply(testSleepingSessions);
        assertEquals(3,sleepAnalysisResult.getResult(),
                "Неверный результат количества сессий при заполненном логе. (3)");
    }

    @Test
    void testSessionCountWhenEmptySessionList() {
        List<SleepingSession> emptyList = new LinkedList<>();
        SleepAnalysisResult sleepAnalysisResult =  new SessionsCount().apply(emptyList);
        assertEquals(0L,sleepAnalysisResult.getResult(),
                "Неверный результат количества сессий при пустом логе. (0)");
    }

    @Test
    void testMinimumSessionDurationWhenTenMinutes() {
        SleepAnalysisResult sleepAnalysisResult =  new MinimumSessionDuration().apply(testSleepingSessions);
        assertEquals(10,sleepAnalysisResult.getResult(),
                "Неверный результат минимального периода сессии при наличии сессий в списке.");
    }

    @Test
    void testMinimumSessionDurationWhenEmptySessionList() {
        List<SleepingSession> emptyList = new LinkedList<>();
        SleepAnalysisResult sleepAnalysisResult =  new MinimumSessionDuration().apply(emptyList);
        assertEquals(0L,sleepAnalysisResult.getResult(),
                "Неверный результат минимального периода без сессий в исходном списке");
    }

    @Test
    void testMaximumSessionDurationWhenThirtyMinutes() {
        SleepAnalysisResult sleepAnalysisResult =  new MaximumSessionDuration().apply(testSleepingSessions);
        assertEquals(30,sleepAnalysisResult.getResult(),
                "Неверный результат максимального периода сессии при наличии сессий в списке.");
    }

    @Test
    void testMaximumSessionDurationWhenEmptySessionList() {
        List<SleepingSession> emptyList = new LinkedList<>();
        SleepAnalysisResult sleepAnalysisResult =  new MaximumSessionDuration().apply(emptyList);
        assertEquals(0L,sleepAnalysisResult.getResult(),
                "Неверный результат максимального периода без сессий в исходном списке");
    }

    @Test
    void testAverageSessionDurationWhenTwentyMinutes() {
        SleepAnalysisResult sleepAnalysisResult =  new AverageSessionDuration().apply(testSleepingSessions);
        assertEquals(20,sleepAnalysisResult.getResult(),
                "Неверный результат средней продолжительности сессии при наличии сессий в списке.");
    }

    @Test
    void testAverageSessionDurationWhenEmptySessionList() {
        List<SleepingSession> emptyList = new LinkedList<>();
        SleepAnalysisResult sleepAnalysisResult =  new AverageSessionDuration().apply(emptyList);
        assertEquals(0L,sleepAnalysisResult.getResult(),
                "Неверный результат средней продолжительности без сессий в исходном списке");
    }

    @Test
    void testBadSessionCountWhenOne() {
        SleepAnalysisResult sleepAnalysisResult =  new BadSessionCount().apply(testSleepingSessions);
        assertEquals(1,sleepAnalysisResult.getResult(),
                "Неверный результат количества сессий с плохим качеством сна при наличии сессий в списке.");
    }

    @Test
    void testBadSessionCountWhenEmptySessionList() {
        List<SleepingSession> emptyList = new LinkedList<>();
        SleepAnalysisResult sleepAnalysisResult =  new BadSessionCount().apply(emptyList);
        assertEquals(0L,sleepAnalysisResult.getResult(),
                "Неверный результат количества сессий с плохим качеством сна без сессий в исходном списке");
    }
}