package ru.sberbank.edu.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.sberbank.edu.entity.Deposit;
import ru.sberbank.edu.exception.AppException;
import ru.sberbank.edu.service.DepositService;

/**
 * Контроллер
 */
@Controller
public class AppController {
    private final DepositService service;

    /**
     * Конструктор
     *
     * @param service - сервис обработки вклада
     */
    public AppController(DepositService service) {
        this.service = service;
    }

    /**
     * Обработчик рутовой страницы
     *
     * @param model - модель
     * @return - страница
     */
    @GetMapping("/")
    public String index(Model model) {
        return "index";
    }

    /**
     * Обработчик страницы финансового калькулятора
     *
     * @param model - модель
     * @return - страница
     */
    @GetMapping("/finance")
    public String finance(Model model) {
        model.addAttribute("deposit", new Deposit(100000, 10, 5));
        return "calc";
    }

    /**
     * Обработчик формы на страницы финансового калькулятора
     *
     * @param deposit - вклад
     * @param result  - страница результата работы финансового калькулятора
     * @return
     */
    @PostMapping("/finance")
    public String calc(@Valid @ModelAttribute("deposit") Deposit deposit, BindingResult result) {
        if (result.hasErrors()) {
            throw new AppException("Неверный формат данных. Скорректируйте значения");
        }
        service.calcTotal(deposit);
        return "redirect:/result";
    }

    /**
     * Обработчик страницы результата работы финансового калькулятора
     *
     * @param model - модель
     * @return - страница
     */
    @GetMapping("/result")
    public String result(Model model) {
        model.addAttribute("total", service.getTotal());
        return "result";
    }
}
