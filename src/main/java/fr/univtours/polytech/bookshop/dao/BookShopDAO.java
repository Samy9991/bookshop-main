package fr.univtours.polytech.bookshop.dao;

import java.util.List;

import fr.univtours.polytech.bookshop.model.book.Doc;

public interface BookShopDAO {
    public List<Doc> getBookShops(String search);

}
