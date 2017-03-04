package com.jsf.ejb;

import com.jsf.entity.Author;
import com.jsf.entity.Book;

import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Named
@Stateless
@DataSourceDefinition(name = "java:global/jdbc/h2Datasource",
        className = "org.h2.Driver",
        url = "jdbc:h2:tcp://localhost/~/test;DB_CLOSE_DELAY=-1;user=root;password=root"
)
public class BooksAuthorsEJB {

  @Inject
  private EntityManager em;

  public List<Book> findBooks() {
    TypedQuery<Book> query = em.createQuery("SELECT b from Book b", Book.class);
    return query.getResultList();
  }

  public Book findBookById(Long id) {
    return em.find(Book.class, id);
  }

  public Author findAuthorById(Long id) {
    return em.find(Author.class, id);
  }

  public Book createBook(Book book) {
    em.persist(book);
    return book;
  }

  public Author createAuthor(Author author) {
    em.persist(author);
    return author;
  }

  public void deleteBook(Book book) {
    em.remove(em.merge(book));
  }

  public void deleteBook(Author author) {
    em.remove(em.merge(author));
  }

  public Book updateBook(Book book) {
    return em.merge(book);
  }

  public Author updateAuthor(Author author) {
    return em.merge(author);
  }

  public List<Author> findAllAuthors(){
    TypedQuery<Author> query = em.createQuery("SELECT a from Author a", Author.class);
    return query.getResultList();
  }
}