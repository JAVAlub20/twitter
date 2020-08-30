package pl.sda.twitter.services;

import pl.sda.twitter.persistance.dao.ArticleDao;
import pl.sda.twitter.persistance.entities.TbUser;

import java.util.List;

public class ArticleService {
    private final ArticleDao articleDao = new ArticleDao();

    public void addArticle(TbUser tbUser, String content) {
        articleDao.addNewArticle(tbUser, content);
    }

    public List getArticles() {
        return articleDao.getArticles();
    }
}
