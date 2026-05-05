package ru.yandex.practicum.sleeptracker;

/**
 * Перечисление возможных хронотипов пользователя
 */
public enum Chronotype {
    OWL(1,"Сова"),
    LARK(2,"Жаворонок"),
    PIGEON(3,"Голубь");
    /**
     * Числовой код хронотипа
     */
    private final int code;
    /**
     * Описание хронотипа
     */
    private final String description;

    Chronotype(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
