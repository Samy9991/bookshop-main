package fr.univtours.polytech.bookshop.model;

import java.io.Serializable;
import java.util.Base64;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.xml.bind.annotation.XmlTransient;

@Entity(name = "BOOK")
public class BookBean implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String author;
    private Float price;
    private String currency;
    @Transient
    private Integer noteCount;
    @Transient
    private Double noteAvg;
    @Transient
    private byte[] picture;
    @Transient
    private List<String> firstSentence;
    @Transient
    private List<String> author_key;

    private String AuthorImageUrl;
    

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    @XmlTransient
	@JsonIgnore
	public String getBase64Image() {
		if (null != this.picture && !"".equals(this.picture)) {
			return Base64.getEncoder().encodeToString(this.picture);
		} else {
			return "";
		}
	}

    public Integer getNoteCount() {
        return noteCount;
    }

    public void setNoteCount(Integer noteCount) {
        this.noteCount = noteCount;
    }

    public Double getNoteAvg() {
        return noteAvg;
    }

    public void setNoteAvg(Double noteAvg) {
        this.noteAvg = noteAvg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<String> getFirstSentence() {
        return firstSentence;
    }

    public void setFirstSentence(List<String> firstSentence) {
        this.firstSentence = firstSentence;
    }

    public List<String> getAuthor_key() {
        return author_key;
    }

    public void setAuthor_key(List<String> author_key) {
        this.author_key = author_key;
    }

    public String getAuthorImageUrl() {
        return AuthorImageUrl;
    }

    public void setAuthorImageUrl(String authorImageUrl) {
        AuthorImageUrl = authorImageUrl;
    }

}
