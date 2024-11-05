package mk.ukim.finki.wpaud.web.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wpaud.model.Category;
import mk.ukim.finki.wpaud.model.Result;
import mk.ukim.finki.wpaud.model.helper.ErrorHandler;
import mk.ukim.finki.wpaud.model.helper.WebContextBuilder;
import mk.ukim.finki.wpaud.service.CategoriesService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "categories", urlPatterns = "/servlet/categories")
public class CategoriesServlet extends HttpServlet
{
    private final CategoriesService categoriesService;
    private final SpringTemplateEngine engine;

    public CategoriesServlet(CategoriesService categoriesService, SpringTemplateEngine engine)
    {
        this.categoriesService = categoriesService;
        this.engine = engine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        ServletContext servletContext = this.getServletContext();
        WebContext context = WebContextBuilder.getContext(req, resp, servletContext);

        String categoryName = req.getParameter("name");
        String add = req.getParameter("add");

        if (add != null)
        {
            engine.process("categories-create.html", context, resp.getWriter());
            return;

        }

        ErrorHandler.handleError(req, context);

        List<Category> categories = categoriesService.find(categoryName);
        String categoriesPageVisitsName = "categoriesPageVisits";
        Integer categoriesPageVisits = (Integer) servletContext.getAttribute(categoriesPageVisitsName);

        context.setVariable("categories", categories);
        context.setVariable("visits", categoriesPageVisits);

        servletContext.setAttribute(categoriesPageVisitsName, categoriesPageVisits + 1);

        engine.process("categories.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String name = req.getParameter("name");
        String description = req.getParameter("description");

        Result result = categoriesService.tryCreate(name, description);

        if (!result.isSuccessful())
        {
            String errorQuery = String.format("error=%s", result.getErrorMessage());
            resp.sendRedirect(String.format("/servlet/categories?%s", errorQuery));
            return;
        }
        resp.sendRedirect("/servlet/categories");

    }
}
