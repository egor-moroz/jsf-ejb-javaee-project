package com.jsf.util;

import com.jsf.entity.Author;
import com.jsf.ejb.BooksAuthorsEJB;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;


@Named
@ApplicationScoped
public class AuthorConverter implements Converter   {

    @Inject
    private BooksAuthorsEJB bookEJB;


    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {

            Author author = bookEJB.findAuthorById(Long.parseLong(s));
        if (author != null) {
            return author;
        } else {
            FacesMessage facesMessage = new FacesMessage("Conversession error", "error");
            facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ConverterException(facesMessage);
        }
    }


    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {

        Long id = (value instanceof Author) ? ((Author) value).getId() : null;
        return (id != null) ? String.valueOf(id) : null;
    }

}
