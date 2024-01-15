package ru.sberbank.edu.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

/**
 * Глобальный обработчик особых ситуаций
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Обработчик ошибок запроса
     *
     * @param req   - запрос
     * @param e     - особая ситуация
     * @param model - параметры
     * @return - страница ошибки
     */
    @ExceptionHandler(ResponseStatusException.class)
    public String defaultErrorHandler(HttpServletRequest req, Exception e, Model model) {
        model.addAttribute("url", req.getRequestURL());
        model.addAttribute("message", e.getMessage());
        return "error/404";
    }

    /**
     * Обработчик пользовательской особой ситуации
     *
     * @param req   - запрос
     * @param e     - особая ситуация
     * @param model - параметры
     * @return - страница ошибки
     */
    @ExceptionHandler(AppException.class)
    public String appExceptionHandler(HttpServletRequest req, Exception e, Model model) {
        model.addAttribute("message", e.getMessage());
        return "error/message";
    }
}
