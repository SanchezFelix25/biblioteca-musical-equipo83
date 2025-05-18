/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.biblioteca.musical.persistencia;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import itson.biblioteca.musical.modelo.Cancion;
import org.bson.Document;

/**
 *
 * @author Felix_isq
 */
public class CancionDAO {

    private final MongoCollection<Document> coleccion;

    public CancionDAO() {
        this.coleccion = MongoDBConnection.getDatabase().getCollection("canciones");
    }

    public void insertarCancion(Cancion cancion) {
        Gson gson = new Gson();
        String json = gson.toJson(cancion);
        Document doc = Document.parse(json);
        coleccion.insertOne(doc);
        System.out.println("Canción insertada: " + cancion.getTitulo());
    }

}
