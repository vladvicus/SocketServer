package com.epam.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.*;

@XmlType(name = "book", propOrder = { "id", "language", "edition", "author", "date" })
@XmlAccessorType(XmlAccessType.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
@XmlRootElement(name = "book")
public class Book {

    @XmlElement(required = true, name = "id")
    @JsonProperty("id")
    private int id;

    @XmlElement(required = true, name = "language")
    @JsonProperty("language")
    private String language;

    @XmlElement(required = true, name = "edition")
    @JsonProperty("edition")
    private String edition;

    @XmlElement(required = true, name = "author")
    @JsonProperty("author")
    private String author;

    @XmlElement(required = true, name = "date")
    @JsonProperty("date")
    private String date;

    public Book() {
    }

    public Book(int id, String language, String edition, String author, String date) {
	this.id = id;
	this.language = language;
	this.edition = edition;
	this.author = author;
	this.date = date;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", language='" + language + '\'' +
                ", edition='" + edition + '\'' +
                ", author='" + author + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getLanguage() {
	return language;
    }

    public void setLanguage(String language) {
	this.language = language;
    }

    public String getEdition() {
	return edition;
    }

    public void setEdition(String edition) {
	this.edition = edition;
    }

    public String getAuthor() {
	return author;
    }

    public void setAuthor(String author) {
	this.author = author;
    }

    public String getDate() {
	return date;
    }

    public void setDate(String date) {
	this.date = date;
    }

}
