package pl.sda.twitter.services;

import pl.sda.twitter.constants.ArticleStatus;
import pl.sda.twitter.persistance.dao.ArticleDao;
import pl.sda.twitter.persistance.entities.TbArticle;
import pl.sda.twitter.persistance.entities.TbUser;

import java.util.List;

public class ArticleService {
    private final ArticleDao articleDao = new ArticleDao();

    public void addArticle(TbUser tbUser, String content) {
        articleDao.addNewArticle(tbUser, content);
    }

    public List<TbArticle> getArticles() {
        return articleDao.getArticles();
    }

    public List<TbArticle> getArticles(ArticleStatus status){
        return articleDao.getArticles(status);
    }

    public TbArticle getArticleById(Integer articleId){
        return articleDao.getArticleById(articleId);
    }
}
