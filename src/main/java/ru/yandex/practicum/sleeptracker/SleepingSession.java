package ru.yandex.practicum.sleeptracker;

import java.time.LocalDateTime;

/**
 * Класс реализует объект сессии сна
 */
public class SleepingSession {
    /**
     * Дата и время начала сна
     */
    private final LocalDateTime startSleep;
    /**
     * Дата и время окончания сна
     */
    private final LocalDateTime endSleep;
    /**
     * Качество сна
     */
    private final SleepQuality quality;

    public SleepingSession(LocalDateTime startSleep, LocalDateTime endSleep, SleepQuality quality) {
        this.startSleep = startSleep;
        this.endSleep = endSleep;
        this.quality = quality;
    }

    public LocalDateTime getStartSleep() {
        return startSleep;
    }

    public LocalDateTime getEndSleep() {
        return endSleep;
    }

    public SleepQuality getQuality() {
        return quality;
    }

    @Override
    public String toString() {
        return "SleepingSession{" +
                "startSleep=" + startSleep +
                ", endSleep=" + endSleep +
                ", quality=" + quality +
                '}';
    }
}
