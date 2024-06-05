package fr.univtours.polytech.bookshop.dao;

public class PriceExchangeDAOImpl implements PriceExchangeDAO {

    
   /** * private static String URL = "https://openlibrary.org";
    @Override
    public List<Doc> getPrice(String search ){
        
    // Instanciation du client.
    Client client = ClientBuilder.newClient();
    Doc doc;

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

    }**/
}
