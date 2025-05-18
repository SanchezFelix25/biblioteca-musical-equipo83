/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.biblioteca.musical.persistencia;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import itson.biblioteca.musical.modelo.Album;
import org.bson.Document;

/**
 *
 * @author Felix_isq
 */
public class AlbumDAO {

    private final MongoCollection<Document> coleccion;

    public AlbumDAO() {
        this.coleccion = MongoDBConnection.getDatabase().getCollection("albumes");
    }

    public void insertarAlbum(Album album) {
        Gson gson = new Gson();
        String json = gson.toJson(album);
        Document doc = Document.parse(json);
        coleccion.insertOne(doc);
        System.out.println("Álbum guardado en MongoDB.");
    }
}
