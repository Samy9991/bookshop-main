package fr.univtours.polytech.bookshop.business;

import java.util.List;

import fr.univtours.polytech.bookshop.model.book.Doc;

public interface BookShopBusiness {
    public List<Doc> getBookShops(String search);

}
