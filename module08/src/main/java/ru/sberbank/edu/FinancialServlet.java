package ru.sberbank.edu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;
import java.io.IOException;

/**
 * Финансовый калькулятор. Сервлет
 *
 */
@WebServlet(value = "/finance/*", loadOnStartup = 1)
public class FinancialServlet extends HttpServlet
{
    /**
     * запускающий метод - инициализация Web сервера
     * @param args - аргументы коммандной строки
     * @throws LifecycleException
     */
    public static void main( String[] args ) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir("finance");
        tomcat.setPort(8080);

        StandardContext context = (StandardContext) tomcat.addWebapp("", new File("module08/src/main/webapp").getAbsolutePath());
        WebResourceRoot resources = new StandardRoot(context);
        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes", Thread.currentThread().getContextClassLoader().getResource(".").getPath(), "/"));
        context.setResources(resources);

        tomcat.getConnector();
        tomcat.start();
        tomcat.getServer().await();
    }

    /**
     * Обработка GET запросов
     * @param req - запрос
     * @param resp - ответ
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/calc.jsp").forward(req, resp);
    }

    /**
     * Обработка POST запросов
     * @param req - запрос
     * @param resp - ответ
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int sum;
        int percent;
        int years;
        try {
            sum = Integer.parseInt(req.getParameter("sum"));
            percent = Integer.parseInt(req.getParameter("percent"));
            years = Integer.parseInt(req.getParameter("years"));
        }
        catch (NumberFormatException e){
            req.setAttribute("error", "Неверный формат данных.\n" + "Скорректируйте значения");
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
            return;
        }
        if (sum<50000){
            req.setAttribute("error", "Минимальная сумма на момент открытия вклада 50 000 рублей");
            getServletContext().getRequestDispatcher("/error.jsp").forward(req, resp);
            return;
        }
        Long total = Math.round(sum * Math.pow(1f + (percent/1200f), years*12f));
        req.setAttribute("total", total);
        getServletContext().getRequestDispatcher("/total.jsp").forward(req, resp);
    }
}
