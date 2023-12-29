package ru.sberbank.edu.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.sberbank.edu.entity.Deposit;
import ru.sberbank.edu.exception.AppException;

/**
 * Сервис Депозит (Вклад)
 */
@Service
public class DepositService {
    private Long total;
    @Value("${minSumDeposit:50000}")
    private int minSumDeposit;

    /**
     * Расчет итоговой суммы вклада
     *
     * @param deposit - итоговая сумма
     */
    public void calcTotal(Deposit deposit) {
        if (deposit.getSum() < minSumDeposit) {
            throw new AppException("Минимальная сумма на момент открытия вклада " + minSumDeposit + " рублей");
        }
        total = Math.round(deposit.getSum() * Math.pow(1f + (deposit.getPercent() / 1200f), deposit.getYears() * 12f));
    }

    /**
     * Геттер итоговой суммы вклада
     *
     * @return - итоговая сумма
     */
    public Long getTotal() {
        return total;
    }
}

