package pl.sda.twitter.servlets;

import pl.sda.twitter.services.ArticleService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/remove/article/*")
public class RemoveArticleServlet extends HttpServlet {
private ArticleService articleService = new ArticleService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO: 05.09.2020  sprawdzic czy user jest zalogowany czy zalogowany user jest wlascicielem artykulu
        final int articleId = Integer.parseInt(req.getPathInfo().replace("/", ""));// TODO: 05.09.2020 sprawdzić czy w ogole getpathInfo istnieje
        articleService.removeArticleById(articleId);
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }
}
