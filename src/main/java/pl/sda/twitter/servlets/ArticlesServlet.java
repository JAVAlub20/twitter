package pl.sda.twitter.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pl.sda.twitter.constants.ArticleStatus;
import pl.sda.twitter.models.Article;
import pl.sda.twitter.persistance.entities.TbArticle;
import pl.sda.twitter.persistance.entities.TbUser;
import pl.sda.twitter.services.ArticleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

@WebServlet(urlPatterns = "/rest/articles/*")
public class ArticlesServlet extends HttpServlet {
    private ArticleService articleService = new ArticleService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            final List<TbArticle> articles = articleService.getArticles(ArticleStatus.VIEW);
            sendAsJson(articles, resp);
        } else {
            final int articleId1 = Integer
                    .parseInt(pathInfo.replace("/", ""));
            final TbArticle tbArticle = articleService
                    .getArticleById(articleId1);
            final Article article = fromTbArticle(tbArticle, req);
            sendAsJson(article, resp);
        }
    }

    private void sendAsJson(Object model, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding(UTF_8.name());
        final Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        final String jsonString = gson.toJson(model);
        final PrintWriter writer = response.getWriter();
        writer.print(jsonString);
        writer.flush();
    }

    private Article fromTbArticle(TbArticle tbArticle, HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final TbUser currentUser = (TbUser) session.getAttribute("user");
        boolean isOwner = currentUser != null && currentUser.getId() == tbArticle.getUser().getId();
        return Article.builder()
                .id(tbArticle.getId())
                .content(tbArticle.getContent())
                .user(tbArticle.getUser())
                .isOwner(isOwner)
                .build();
    }
}
