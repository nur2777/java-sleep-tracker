package ru.yandex.practicum.sleeptracker;

import java.time.LocalDateTime;

/**
 * Класс реализует объект сессии сна
 */
public class SleepingSession {

    /**
     * Дата и время начала сна
     */
    private LocalDateTime startSleep;
    /**
     * Дата и время окончания сна
     */
    private LocalDateTime endSleep;
    /**
     * Качество сна
     */
    private SleepQuality quality;

    public SleepingSession(LocalDateTime startSleep, LocalDateTime endSleep, SleepQuality quality) {
        this.startSleep = startSleep;
        this.endSleep = endSleep;
        this.quality = quality;
    }

    public LocalDateTime getStartSleep() {
        return startSleep;
    }

    public void setStartSleep(LocalDateTime startSleep) {
        this.startSleep = startSleep;
    }

    public LocalDateTime getEndSleep() {
        return endSleep;
    }

    public void setEndSleep(LocalDateTime endSleep) {
        this.endSleep = endSleep;
    }

    public SleepQuality getQuality() {
        return quality;
    }

    public void setQuality(SleepQuality quality) {
        this.quality = quality;
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
