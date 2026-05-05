package ru.yandex.practicum.sleeptracker.analiticfunctions;

import ru.yandex.practicum.sleeptracker.Chronotype;
import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Класс для классификации пользователя по хронотипу
 */
public class UserChronotypeAnalise implements Function<List<SleepingSession>, SleepAnalysisResult> {

    /**
     * Нижняя граница времени для хронотипа Сова согласно ТЗ 23 часа 0 минут
     */
    private static final LocalTime minLocalTimeForOwl = LocalTime.of(23, 0);
    /**
     * Верхняя граница времени для хронотипа Сова согласно ТЗ 9 часов 0 минут
     */
    private static final LocalTime maxLocalTimeForOwl = LocalTime.of(9, 0);
    /**
     * Верхняя граница бессонной ночи согласно ТЗ равна 6 часам утра 0 минутам
     */
    private static final LocalTime maxLocalTimeInSleeplessNight = LocalTime.of(6,0);
    /**
     * Нижняя граница времени для хронотипа Жаворонок согласно ТЗ 22 часов 0 минут
     */
    private static final LocalTime minLocalTimeForLark = LocalTime.of(22, 0);
    /**
     * Верхняя граница времени для хронотипа Жаворонок согласно ТЗ 7 часов 0 минут
     */
    private static final LocalTime maxLocalTimeForLark = LocalTime.of(7, 0);
    /**
     * Предикат для определения хронотипа Сова
     */
    private final Predicate<SleepingSession> isOwl = sleepingSession -> {
        LocalTime startSleepTime = sleepingSession.getStartSleep().toLocalTime();
        LocalTime endSleepTime = sleepingSession.getEndSleep().toLocalTime();

        if ((startSleepTime.isAfter(minLocalTimeForOwl) && endSleepTime.isAfter(maxLocalTimeForOwl)
                && sleepingSession.getStartSleep().toLocalDate().isBefore(sleepingSession.getEndSleep().toLocalDate()))
            ||
            (startSleepTime.isBefore(maxLocalTimeInSleeplessNight) && endSleepTime.isAfter(maxLocalTimeForOwl)
                && sleepingSession.getStartSleep().toLocalDate().isEqual(sleepingSession.getEndSleep().toLocalDate()))
        ) {
            return true;
        } else {
            return false;
        }
    };

    /**
     * Предикат для определения хронотипа Жаворонок
     */
    private final Predicate<SleepingSession> isLark = sleepingSession -> {
        LocalTime startSleepTime = sleepingSession.getStartSleep().toLocalTime();
        LocalTime endSleepTime = sleepingSession.getEndSleep().toLocalTime();

        if (startSleepTime.isBefore(minLocalTimeForLark) && endSleepTime.isBefore(maxLocalTimeForLark)
            && sleepingSession.getStartSleep().toLocalDate().isBefore(sleepingSession.getEndSleep().toLocalDate())) {
            return true;
        } else {
            return false;
        }
    };

    /**
     * Предикат для определения хронотипа Голубь
     */
    private final Predicate<SleepingSession> isPigeon = sleepingSession -> {
        LocalTime startSleepTime = sleepingSession.getStartSleep().toLocalTime();
        LocalTime endSleepTime = sleepingSession.getEndSleep().toLocalTime();
        if ((startSleepTime.isBefore(minLocalTimeForOwl) && endSleepTime.isAfter(minLocalTimeForOwl)
             && sleepingSession.getStartSleep().toLocalDate().isBefore(sleepingSession.getEndSleep().toLocalDate()))
           ||
            (startSleepTime.isAfter(minLocalTimeForOwl) && endSleepTime.isBefore(minLocalTimeForOwl))
           ||
            (startSleepTime.isBefore(minLocalTimeForLark) && endSleepTime.isAfter(maxLocalTimeForLark)
             && sleepingSession.getStartSleep().toLocalDate().isBefore(sleepingSession.getEndSleep().toLocalDate()))
           ||
            (startSleepTime.isAfter(minLocalTimeForLark) && endSleepTime.isBefore(maxLocalTimeForLark))
        ) {
            return true;
        } else {
            return false;
        }
    };

    /** Метод определения хронотипа пользователя
     * @param sleepingSessions список сессий сна
     * @return хронотип пользователя
     */
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        Chronotype chronotype;
        if (!sleepingSessions.isEmpty()) {
            long isOwlNightCounts = sleepingSessions.stream()
                    .filter(isOwl)
                    .count();
            long isLarkNightCounts = sleepingSessions.stream()
                    .filter(isLark)
                    .count();
            long isPigeonCounts = sleepingSessions.stream()
                    .filter(isPigeon)
                    .count();
            if ((isOwlNightCounts > isLarkNightCounts) && (isOwlNightCounts > isPigeonCounts)) {
                chronotype = Chronotype.OWL;
            } else if ((isOwlNightCounts < isLarkNightCounts) && (isLarkNightCounts > isPigeonCounts)) {
                chronotype = Chronotype.LARK;
            } else {
                chronotype = Chronotype.PIGEON;
            }
            return new SleepAnalysisResult(chronotype);
        } else {
            return new SleepAnalysisResult(null,"Список сессий пуст, анализ невозможен!");
        }
    }
}
