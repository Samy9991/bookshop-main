package fr.univtours.polytech.bookshop.dao;

import java.util.List;

import fr.univtours.polytech.bookshop.model.book.BookShopResult;
import fr.univtours.polytech.bookshop.model.book.Doc;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;

@Stateless
public class BookShopDAOImpl implements BookShopDAO {

    private static String URL = "https://openlibrary.org";
    @Override
    public List<Doc> getBookShops(String search ){
        
    // Instanciation du client.
    Client client = ClientBuilder.newClient();

    // On indique l'URL du Web Service.
    WebTarget target = client.target(URL);

    // On indique le "end point" (on aurait aussi pu directement le mettre dans
    // l'URL).
    // C'est également avec cette méthode qu'on pourrait ajouter des "path
    // parameters" si besoin.
    target = target.path("search");
    // On précise (lorsqu'il y en a) les "query parameters".
    target = target.queryParam("q", search);
    target = target.queryParam("limit", 1);

    // On appelle le WS en précisant le type de l'objet renvoyé, ici un
    // booksResult.
    System.out.println(target.getUri());
    BookShopResult booksResult = target.request(MediaType.APPLICATION_JSON).get(BookShopResult.class);
    return booksResult.getDocs();

    }
}
