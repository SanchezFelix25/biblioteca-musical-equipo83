/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.biblioteca.musical.persistencia;

import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import itson.biblioteca.musical.modelo.Artista;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author Felix_isq
 */
public class ArtistaDAO {

    private final MongoCollection<Document> coleccion;

    public ArtistaDAO() {
        this.coleccion = MongoDBConnection.getDatabase().getCollection("artistas");
    }

    public void insertarArtista(Artista artista) {
        Gson gson = new Gson();
        String json = gson.toJson(artista);
        Document doc = Document.parse(json);
        coleccion.insertOne(doc);
        System.out.println("Artista guardado en MongoDB.");
    }

    public String obtenerIdArtistaPorNombre(String nombre) {
        Document doc = coleccion.find(Filters.eq("nombre", nombre)).first();
        if (doc != null && doc.containsKey("_id")) {
            return doc.getObjectId("_id").toHexString();
        }
        return null;
    }

    public List<Document> obtenerTodos() {
        List<Document> artistas = new ArrayList<>();
        FindIterable<Document> resultados = coleccion.find().sort(Sorts.ascending("nombre"));
        for (Document doc : resultados) {
            artistas.add(doc);
        }
        return artistas;
    }

}
