package com.jsf.controller;

import com.jsf.entity.Author;
import com.jsf.ejb.BooksAuthorsEJB;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
@RequestScoped
public class AuthorController {

    @Inject
    private BooksAuthorsEJB bookEJB;

    private Author author = new Author();

    public String doCreateAuthor() {
        bookEJB.createAuthor(author);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "com.jsf.entity.Author created",
                "The author" + author.getName() + " has been created with id=" + author.getId()));

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        request.setAttribute("edit", "edit");

        return "newBook.xhtml";
    }

    public void doFindAuthorById() {
        author = bookEJB.findAuthorById(author.getId());
    }

    public void doDeleteAuthor(Long id) {
        author = bookEJB.findAuthorById(id);
        bookEJB.deleteBook(author);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "com.jsf.entity.Author ",
                "The author" + author.getName() + " has been deleted with id=" + author.getId()));

    }

    public String doEditAuthor() {
        bookEJB.updateAuthor(author);
        author = new Author();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Author updated",
                "The author" + author.getName() + " has been updated with id=" + author.getId()));
        return "newAuthor.xhtml";
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
