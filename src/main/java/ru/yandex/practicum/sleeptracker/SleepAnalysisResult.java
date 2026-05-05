package ru.yandex.practicum.sleeptracker;

/**
 * Класс описывает объект результата анализа сна
 */
public class SleepAnalysisResult {
    /**
     * Числовой результат анализа
     */
    private final Long result;
    /**
     * Текстовое описание результата
     */
    private final String resultDescription;

    public SleepAnalysisResult(Long result, String resultDescription) {
        this.result = result;
        this.resultDescription = resultDescription;
    }

    public SleepAnalysisResult(Chronotype chronotype) {
        this.result = (long) chronotype.getCode();
        this.resultDescription = "Пользователь по хронотипу является: " + chronotype.getDescription();
    }

    public String getResultDescription() {
        return resultDescription;
    }

    public Long getResult() {
        return result;
    }
}
