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
import itson.biblioteca.musical.modelo.Cancion;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

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

    public List<Document> obtenerTodos() {
        List<Document> canciones = new ArrayList<>();
        FindIterable<Document> resultados = coleccion.find().sort(Sorts.ascending("titulo"));
        for (Document doc : resultados) {
            canciones.add(doc);
        }
        return canciones;
    }
    
    public String obtenerIdCancionPorTitulo(String titulo) {
        Document doc = coleccion.find(Filters.eq("titulo", titulo)).first();
        if (doc != null && doc.containsKey("_id")) {
            ObjectId id = doc.getObjectId("_id");
            return id.toHexString(); // Regresa el ID como cadena
        }
        return null;
    }

}
