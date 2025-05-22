/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.biblioteca.musical.persistencia;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import itson.biblioteca.musical.modelo.Usuario;
import org.bson.Document;

/**
 *
 * @author Felix_isq
 */
public class UsuarioDAO {

    private final MongoCollection<Document> coleccion;

    public UsuarioDAO() {
        this.coleccion = MongoDBConnection.getDatabase().getCollection("usuarios");
    }

    public void insertarUsuario(Usuario usuario) {
        Gson gson = new Gson();
        String json = gson.toJson(usuario);
        Document doc = Document.parse(json);
        coleccion.insertOne(doc);
        System.out.println("Usuario guardado en MongoDB.");
    }
    
    public boolean existeCorreo(String correo) {
    return coleccion.find(Filters.eq("correo", correo)).first() != null;
    }
}
