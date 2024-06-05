package fr.univtours.polytech.bookshop.model.rest;

import java.util.ArrayList;
import java.util.List;

import fr.univtours.polytech.bookshop.business.BookBusiness;
import fr.univtours.polytech.bookshop.business.BookShopBusiness;
import fr.univtours.polytech.bookshop.model.BookBean;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("v1")
public class BookRest {
    @Inject
    private BookShopBusiness bookShopBusiness;
    @Inject
    private BookBusiness bookBusiness;

    @Path("books")
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<BookBean> getBooksList(@QueryParam("filtre") String filtre) {
        List<BookBean> books = this.bookBusiness.getBooks();
        List<BookBean> results = new ArrayList<BookBean>();
        if (filtre == null || filtre.trim().isEmpty()) {
            return bookBusiness.getBooks();
        } else {

            if (null != filtre && !"".equals(filtre)) {
                // Un filtre est indiqué. On l'applique sur le champ "city".
                for (BookBean bookBean : books) {
                    if (bookBean.getAuthor().contains(filtre) || bookBean.getTitle().contains(filtre)) {
                        results.add(bookBean);
                    }
                }
            } else {
                results = books;
            }

        }
        return results;
    }

    @Path("books/{id}")
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public BookBean getBook(@PathParam("id") Integer id) {
        BookBean bookBean = bookBusiness.getBook(id);

        return bookBean;
    }

    @Path("books/{id}")
    @DELETE
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response deleteBook(@PathParam("id") Integer idBook,
            @HeaderParam(HttpHeaders.AUTHORIZATION) String autorisation) {

        if (autorisation == null || autorisation.isEmpty()) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Il manque une autorisation dans le header")
                    .build();
        }

        if (!"42".equals(autorisation)) {
            return Response.status(Response.Status.FORBIDDEN).entity("Token d'autorisation invalide").build();
        }

        bookBusiness.removeBook(idBook);
        return Response.ok().build();
    }

    @Path("books")
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })

    public Response createBook(BookBean bookBean, @HeaderParam(HttpHeaders.AUTHORIZATION) String autorisation) {

        if (autorisation == null || autorisation.isEmpty()) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Il manque une autorisation dans le header")
                    .build();
        }

        if (!"42".equals(autorisation)) {
            return Response.status(Response.Status.FORBIDDEN).entity("Token d'autorisation invalide").build();
        }

        bookBusiness.createBook(bookBean);

        return Response.status(Response.Status.CREATED).entity(bookBean).build();
    }

    @Path("books/{bookId}")
    @PUT
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response updateBook(@PathParam("bookId") int bookId, BookBean updatedBook,
            @HeaderParam(HttpHeaders.AUTHORIZATION) String autorisation) {

        if (autorisation == null || autorisation.isEmpty()) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Il manque une autorisation dans le header")
                    .build();
        }

        if (!"42".equals(autorisation)) {
            return Response.status(Response.Status.FORBIDDEN).entity("Token d'autorisation invalide").build();
        }
        BookBean existingBook = bookBusiness.getBook(bookId);
        BookBean odlBookBean = this.bookBusiness.getBook(bookId);
        if (null == odlBookBean) {
            return Response.status(Status.NOT_FOUND).build();
        }

        // On met à jour tous les champs :
        odlBookBean.setAuthor(updatedBook.getAuthor());;
        odlBookBean.setTitle(updatedBook.getTitle());
        odlBookBean.setPrice(updatedBook.getPrice());
        odlBookBean.setAuthorImageUrl(updatedBook.getAuthorImageUrl());
        odlBookBean.setCurrency(updatedBook.getCurrency());

        this.bookBusiness.updateBook(odlBookBean);
        return Response.status(Status.ACCEPTED).build();

    }

    @Path("books/{bookId}")
    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    public Response partialUpdateBook(@PathParam("bookId") int bookId, BookBean updatedBook,
            @HeaderParam(HttpHeaders.AUTHORIZATION) String autorisation) {

        if (autorisation == null || autorisation.isEmpty()) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Il manque une autorisation dans le header")
                    .build();
        }

        if (!"42".equals(autorisation)) {
            return Response.status(Response.Status.FORBIDDEN).entity("Token d'autorisation invalide").build();
        }
        BookBean existingBook = bookBusiness.getBook(bookId);
        if (existingBook != null) {
            // Mise à jour partielle des champs modifiés uniquement
            if (updatedBook.getTitle() != null) {
                existingBook.setTitle(updatedBook.getTitle());
            }
            if (updatedBook.getAuthor() != null) {
                existingBook.setAuthor(updatedBook.getAuthor());
            }
            if (updatedBook.getCurrency() != null) {
                existingBook.setCurrency(updatedBook.getCurrency());
            }
            if (updatedBook.getPrice() != null) {
                existingBook.setPrice(updatedBook.getPrice());
            }
            if (updatedBook.getAuthorImageUrl() != null) {
                existingBook.setAuthorImageUrl(updatedBook.getAuthorImageUrl());
            }


            bookBusiness.updateBook(existingBook);
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
