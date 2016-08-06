package com.jsf.entity;/*import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.NotEmpty;*/

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "BOOK")
public class Book {

  private Long id;
  private String title;
  private Float price;
  private String description;
  private Integer nbOfPage;
  private Set<Author> authors = new HashSet<Author>();

  public Book() {
  }

  public Book(String title, Float price, String description, Integer nbOfPage) {
    this.title = title;
    this.price = price;
    this.description = description;
    this.nbOfPage = nbOfPage;
  }

  public Book(Long id, String title, Float price, String description, Integer nbOfPage, Set<Author> authors) {
    this.id = id;
    this.title = title;
    this.price = price;
    this.description = description;
    this.nbOfPage = nbOfPage;
    this.authors = authors;
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

  @Column(name="TITLE")
  @NotEmpty(message = "{validation.book.title.NotEmpty.message}")
  @Size(min=3, max=60, message = "{validation.book.title.Size.message}")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Column(name = "PRICE")
  @NotNull(message = "{validation.book.price.NotNull.message}")
  @Min(value = 1, message = "{validation.book.price.Min.message}" )
  @Max(value = 10000000, message = "{validation.book.price.Max.message}")
  public Float getPrice() {
    return price;
  }

  public void setPrice(Float price) {
    this.price = price;
  }

  @Column(name = "DESCRIPTION")
  @NotEmpty(message = "{validation.book.description.NotEmpty.message}")
  @Size(min=3, max=200, message = "{validation.book.description.Size.message}")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Column(name = "NBOFPAGE")
  @NotNull(message = "{validation.book.nbOfPage.NotNull.message}")
  @Min(value = 1, message = "{validation.book.nbOfPage.Min.message}")
  @Max(value = 10000000, message = "{validation.book.nbOfPage.Max.message}")
  public Integer getNbOfPage() {
    return nbOfPage;
  }

  public void setNbOfPage(Integer nbOfPage) {
    this.nbOfPage = nbOfPage;
  }

  @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH })
  @JoinTable(name = "BOOK_AUTHOR",
          joinColumns = @JoinColumn(name = "BOOK_ID"),
          inverseJoinColumns = @JoinColumn(name = "AUTHOR_ID"))
  public Set<Author> getAuthors() {
    return authors;
  }

  public void setAuthors(Set<Author> authors) {
    this.authors = authors;
  }

  @Override
  public boolean equals(Object object) {
    return (object instanceof Book) && (id != null)
            ? id.equals(((Book) object).id)
            : (object == this);
  }

  @Override
  public int hashCode() {
    return (id != null)
            ? (Book.class.hashCode() + id.hashCode())
            : super.hashCode();
  }
}