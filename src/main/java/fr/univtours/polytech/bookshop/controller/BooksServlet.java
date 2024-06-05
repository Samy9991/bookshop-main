package fr.univtours.polytech.bookshop.controller;

import java.io.IOException;
import java.util.List;

import fr.univtours.polytech.bookshop.business.BookBusiness;
import fr.univtours.polytech.bookshop.business.BookShopBusiness;
import fr.univtours.polytech.bookshop.model.BookBean;
import fr.univtours.polytech.bookshop.model.book.Doc;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("books")
public class BooksServlet extends HttpServlet {

    @Inject
    private BookBusiness bookBusiness;
    @Inject 
    BookShopBusiness bookShopBusiness;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<BookBean> books = this.bookBusiness.getBooks();
        String recherche;
        //Integer ratingsCount;

        for(BookBean bookBean : books){
            recherche= bookBean.getTitle()+" "+bookBean.getAuthor();
            List<Doc> doc=bookShopBusiness.getBookShops(recherche);
            //System.out.println("ratings count: " +doc.get(0).getRatingsCount());
            if(!doc.isEmpty()){
                bookBean.setNoteCount(doc.get(0).getRatings_count());
                bookBean.setNoteAvg(doc.get(0).getRatings_average());
                bookBean.setFirstSentence(doc.get(0).getFirst_sentence());
                String authorKey = doc.get(0).getAuthor_key();
                if (authorKey != null) {
                    String authorImageUrl = "https://covers.openlibrary.org/a/olid/" + authorKey + ".jpg";
                    bookBean.setAuthorImageUrl(authorImageUrl);
                }
    
                //List<String> authorkey=doc.get(0).getAuthor_key();
                /**if (authorkey != null && !authorkey.isEmpty()) {
                    List<String> cleanedAuthorKey = doc.get(0).getAuthor_key();
                    cleanedAuthorKey.replaceAll("[\\[\\]]");
                    bookBean.setAuthor_key(cleanedAuthorKey);
                }
                bookBean.setAuthor_key(doc.get(0).getAuthor_key());**/
                
            }
            
        }

        request.setAttribute("BOOKS", books);

        request.getRequestDispatcher("books.jsp").forward(request, response);
    }
}
