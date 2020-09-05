package pl.sda.twitter.persistance.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pl.sda.twitter.constants.ArticleStatus;
import pl.sda.twitter.persistance.HibernateUtil;
import pl.sda.twitter.persistance.entities.TbArticle;
import pl.sda.twitter.persistance.entities.TbUser;

import java.util.List;

public class ArticleDao {

    public void addNewArticle(TbUser tbUser, String content, ArticleStatus articleStatus) {
        try (final Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();

            final TbArticle tbArticle = new TbArticle();
            tbArticle.setContent(content);
            tbArticle.setUser(tbUser);
            tbArticle.setStatus(articleStatus.name());
            session.save(tbArticle);
            session.getTransaction().commit();
        }
    }

    public List<TbArticle> getArticles() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            final Query q = session.createQuery("select o from "
                    + TbArticle.class.getName() + " o");
            session.getTransaction().commit();
            return q.getResultList();
        }
    }

    public List<TbArticle> getArticles(ArticleStatus status) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            final Query q = session.createQuery("select o from "
                    + TbArticle.class.getName() + " o where o.status =:" +
                    "articleStatus");
            q.setParameter("articleStatus", status.name());
            session.getTransaction().commit();
            return q.getResultList();
        }
    }

    public TbArticle getArticleById(Integer articleId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            final Query q = session.createQuery("select o from "
                    + TbArticle.class.getName() + " o where o.id =:id");
            q.setParameter("id", articleId);
            session.getTransaction().commit();
            return (TbArticle) q.getSingleResult();
        }
    }

    public void removeArticleById(Integer articleId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            final Transaction transaction = session.beginTransaction();
            final TbArticle tbArticle = session.load(TbArticle.class, articleId);
            tbArticle.setStatus(ArticleStatus.DELET.name());
            session.update(tbArticle);
            transaction.commit();
            session.close();
        }
    }
}
