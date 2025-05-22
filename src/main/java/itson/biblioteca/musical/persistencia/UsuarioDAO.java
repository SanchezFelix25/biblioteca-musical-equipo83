/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.biblioteca.musical.persistencia;

import com.google.gson.Gson;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import itson.biblioteca.musical.modelo.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

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

    public void actualizarGenerosNoDeseados(String correo, List<String> generos) {
        coleccion.updateOne(
                Filters.eq("correo", correo),
                Updates.set("generosNoDeseados", generos)
        );
    }

    public Usuario obtenerUsuarioPorCorreoYContrasena(String correo, String contrasena) {
        Document doc = coleccion.find(
                Filters.and(
                        Filters.eq("correo", correo),
                        Filters.eq("password", contrasena) // en producción deberías usar hash
                )
        ).first();

        if (doc != null) {
            return new Usuario(
                    doc.getString("usuario"),
                    doc.getString("correo"),
                    doc.getString("password"),
                    doc.getString("imagen"),
                    doc.getList("favoritos", String.class),
                    doc.getList("generosNoDeseados", String.class)
            );
        }
        return null;
    }

}
