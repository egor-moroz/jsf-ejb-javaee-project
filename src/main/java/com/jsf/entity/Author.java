package com.jsf.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "AUTHOR")
public class Author implements Serializable {

    private Long id;
    private String name;
    private Set<Book> books = new HashSet<Book>();

    public Author() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NAME")
    @Pattern(regexp = "^([а-яА-ЯёЁa-zA-Z]+[,.]?[ ]?|[а-яА-ЯёЁa-zA-Z]+['-]?)+$", message = "{validation.author.name.pattern.message}")
    @NotEmpty(message = "{validation.author.name.NotEmpty.message}")
    @Size(min = 3, max = 60, message = "{validation.author.name.Size.message}")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany
    @JoinTable(name = "BOOK_AUTHOR",
            joinColumns = @JoinColumn(name = "AUTHOR_ID"),
            inverseJoinColumns = @JoinColumn(name = "BOOK_ID"))

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
         return String.format("com.jsf.entity.Author[%d, %s]", id, name);
    }

    @Override
    public boolean equals(Object object) {
        return (object instanceof Author) && (id != null)
                ? id.equals(((Author) object).id)
                : (object == this);
    }

    @Override
    public int hashCode() {
        return (id != null)
                ? (Author.class.hashCode() + id.hashCode())
                : super.hashCode();
    }

}
