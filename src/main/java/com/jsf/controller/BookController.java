package com.jsf.controller;

import com.jsf.entity.Book;
import com.jsf.ejb.BooksAuthorsEJB;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

@Named
@RequestScoped
public class BookController {

    @Inject
    private BooksAuthorsEJB bookEJB;
    private Book book = new Book();

    public String doCreateBook() {
        bookEJB.createBook(book);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "com.jsf.entity.Book created",
                "The book" + book.getTitle() + " has been created with id=" + book.getId()));

        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        request.setAttribute("edit", "edit");

        return "newBook.xhtml";
    }

    public void doFindBookById() {
        book = bookEJB.findBookById(book.getId());
    }

    public void doDeleteBook(Long id) {
        book = bookEJB.findBookById(id);
        bookEJB.deleteBook(book);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "com.jsf.entity.Book ",
                "The book" + book.getTitle() + " has been created with id=" + book.getId()));

    }

    public String doEditBook() {
        bookEJB.updateBook(book);
        book = new Book();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Book updated",
                "The book" + book.getTitle() + " has been created with id=" + book.getId()));
        return "newBook.xhtml";
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

}