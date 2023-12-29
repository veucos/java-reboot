package ru.sberbank.edu.entity;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * Вклад (депозит)
 */
public class Deposit {
    @NotNull(message = "обязательно к заполнению")
    @Min(value = 1, message = "сумма должна быть больше нуля")
    private Integer sum;

    @NotNull(message = "обязательно к заполнению")
    @Min(value = 1, message = "проценты должна быть больше нуля")
    @Max(value = 100, message = "проценты не должны превышать 100%")
    private Integer percent;

    @NotNull(message = "обязательно к заполнению")
    @Min(value = 1, message = "срок вклада должен быть больше нуля")
    @Max(value = 30, message = "срок вклада не должен превышать 30 лет")
    private Integer years;

    private Long total;

    /**
     * Конструктор
     *
     * @param sum     - сумма вклада
     * @param percent - процент
     * @param years   - скро вклада
     */
    public Deposit(Integer sum, Integer percent, Integer years) {
        this.sum = sum;
        this.percent = percent;
        this.years = years;
    }

    /*
    Сеттеры - геттеры
     */
    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
