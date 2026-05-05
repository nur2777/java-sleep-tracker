package ru.yandex.practicum.sleeptracker.analiticfunctions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.Chronotype;
import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepQuality;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserChronotypeAnaliseTest {

    private static final List<SleepingSession> testSleepingSessions = new LinkedList<>();

    @BeforeEach
    void beforeEach() {
        testSleepingSessions.clear();
        testSleepingSessions.add(new SleepingSession(
                LocalDateTime.of(2026,5,4,23,14,0),
                LocalDateTime.of(2026,5,5,14,14,0),
                SleepQuality.BAD));
        testSleepingSessions.add(new SleepingSession(
                LocalDateTime.of(2026,5,6,2,14,0),
                LocalDateTime.of(2026,5,6,14,14,0),
                SleepQuality.BAD));
        testSleepingSessions.add(new SleepingSession(
                LocalDateTime.of(2026,5,7,7,14,0),
                LocalDateTime.of(2026,5,7,14,14,0),
                SleepQuality.BAD));
        testSleepingSessions.add(new SleepingSession(
                LocalDateTime.of(2026,5,7,22,14,0),
                LocalDateTime.of(2026,5,8,8,14,0),
                SleepQuality.BAD));
        testSleepingSessions.add(new SleepingSession(
                LocalDateTime.of(2026,5,8,22,40,0),
                LocalDateTime.of(2026,5,9,7,36,0),
                SleepQuality.BAD));
        testSleepingSessions.add(new SleepingSession(
                LocalDateTime.of(2026,5,9,12,20,0),
                LocalDateTime.of(2026,5,9,18,16,0),
                SleepQuality.BAD));
        testSleepingSessions.add(new SleepingSession(
                LocalDateTime.of(2026,5,10,21,14,0),
                LocalDateTime.of(2026,5,11,6,14,0),
                SleepQuality.BAD));
        testSleepingSessions.add(new SleepingSession(
                LocalDateTime.of(2026,5,11,20,40,0),
                LocalDateTime.of(2026,5,12,5,36,0),
                SleepQuality.BAD));
    }

    @Test
    void testOwlChronotype() {
        // добавляем одну сессию Совы
        testSleepingSessions.add(new SleepingSession(
                LocalDateTime.of(2026,5,15,0,40,0),
                LocalDateTime.of(2026,5,15,9,36,0),
                SleepQuality.BAD));
        SleepAnalysisResult sleepAnalysisResult = new UserChronotypeAnalise().apply(testSleepingSessions);
        assertEquals(Chronotype.OWL.getCode(), sleepAnalysisResult.getResult(),
                "Неверный результат хронотипа для типа Сова.");
    }

    @Test
    void testLarkChronotype() {
        // добавляем одну сессию Жаворонка
        testSleepingSessions.add(new SleepingSession(
                LocalDateTime.of(2026,5,14,20,40,0),
                LocalDateTime.of(2026,5,15,4,36,0),
                SleepQuality.BAD));
        SleepAnalysisResult sleepAnalysisResult = new UserChronotypeAnalise().apply(testSleepingSessions);
        assertEquals(Chronotype.LARK.getCode(), sleepAnalysisResult.getResult(),
                "Неверный результат хронотипа для типа Жаворонок.");
    }

    @Test
    void testPigeonChronotype() {
        SleepAnalysisResult sleepAnalysisResult = new UserChronotypeAnalise().apply(testSleepingSessions);
        assertEquals(Chronotype.PIGEON.getCode(), sleepAnalysisResult.getResult(),
                "Неверный результат хронотипа для типа Голубь.");
    }

    @Test
    void testChronotypeWhenEmptySessionList() {
        List<SleepingSession> emptyList = new LinkedList<>();
        SleepAnalysisResult sleepAnalysisResult =  new UserChronotypeAnalise().apply(emptyList);
        assertEquals(-1,sleepAnalysisResult.getResult(),
                "Неверный результат количества сессий при заполненном логе. (3)");
    }
}