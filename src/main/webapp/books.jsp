<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>

<head>
    <title>Résultats</title>
    <meta charset="UTF-8" />

    <style>
        table,
        th,
        td {
            border: 1px solid;
        }
        
        table,
        th,
        td {
            border: 1px solid;
        }
        .author-image {
            max-width: 100px; /* Définir la largeur maximale de l'image */
        }

    </style>
</head>

<body>
    <h1>Liste des livres</h1>
    <fieldset>
        <legend>Livres</legend>
        <table>
            <tr>
                <th>Titre</th>
                <th>Auteur</th>
                <th>Nb notes</th>
                <th>Moyenne des notes</th>
                <th>1ere phrase du livre</th>
                <th>Photo</th>
                <th>Prix(£)</th>
            </tr>
            <c:forEach items="${requestScope.BOOKS}" var="book">
                <tr>
                    <td>${book.title}</td>
                    <td>${book.author}</td>
                    <td>${book.noteAvg}</td>
                    <td>${book.noteCount}</td>
                    <td>${book.firstSentence}</td>
                    <td><img class="author-image" src="${book.authorImageUrl}" alt="Image de l'auteur"></td>
                </tr>
            </c:forEach>
        </table>
    </fieldset>
</body>

</html>