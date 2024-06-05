package fr.univtours.polytech.bookshop.business;

import java.util.List;

import fr.univtours.polytech.bookshop.dao.BookShopDAO;
import fr.univtours.polytech.bookshop.model.book.Doc;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class BookShopBusinessImpl implements BookShopBusiness {

    @Inject
    private BookShopDAO bookShopDAO;

    @Override
    public List<Doc> getBookShops(String search){

        return bookShopDAO.getBookShops(search);
    }
}
