
package itson.biblioteca.musical.persistencia;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;


/**
 *
 * @author Felix_isq
 */
public class FavoritoDAO {

    private final MongoCollection<Document> coleccion;
    
    public FavoritoDAO(){
        this.coleccion = MongoDBConnection.getDatabase().getCollection("favoritos");
    }
    
    public void agregarFavorito(String idUsuario, String tipo, String idElemento, String nombre, String fecha) {
        Document doc = new Document("idUsuario", idUsuario)
                .append("tipo", tipo)
                .append("idElemento", idElemento)
                .append("nombre", nombre)
                .append("fecha", fecha);
        coleccion.insertOne(doc);
    }

    public void eliminarFavorito(String idUsuario, String tipo, String idElemento) {
        coleccion.deleteOne(Filters.and(
                Filters.eq("idUsuario", idUsuario),
                Filters.eq("tipo", tipo),
                Filters.eq("idElemento", idElemento)
        ));
    }

    public boolean esFavorito(String idUsuario, String tipo, String idElemento) {
        return coleccion.find(Filters.and(
                Filters.eq("idUsuario", idUsuario),
                Filters.eq("tipo", tipo),
                Filters.eq("idElemento", idElemento)
        )).first() != null;
    }
    
    public List<Document> obtenerFavoritosPorUsuario(String idUsuario) {
        return coleccion.find(Filters.eq("idUsuario", idUsuario))
                        .into(new ArrayList<>());
    }
    
    public int eliminarFavoritosPorGenero(String correo, String genero) {
    // Primero busca todos los favoritos del usuario
    List<Document> favoritos = coleccion.find(Filters.eq("idUsuario", correo)).into(new ArrayList<>());
    int eliminados = 0;
    for (Document fav : favoritos) {
        String tipo = fav.getString("tipo");
        String id = fav.getString("idElemento");

        Document referencia = null;
        if (tipo.equals("artista")) {
            referencia = MongoDBConnection.getDatabase().getCollection("artistas").find(Filters.eq("_id", new ObjectId(id))).first();
        } else if (tipo.equals("álbum")) {
            referencia = MongoDBConnection.getDatabase().getCollection("albumes").find(Filters.eq("_id", new ObjectId(id))).first();
        } else if (tipo.equals("canción")) {
            referencia = MongoDBConnection.getDatabase().getCollection("canciones").find(Filters.eq("_id", new ObjectId(id))).first();
        }

        if (referencia != null && genero.equalsIgnoreCase(referencia.getString("genero"))) {
            coleccion.deleteOne(Filters.eq("_id", fav.getObjectId("_id")));
            eliminados++;
        }
    }
    return eliminados;
}

}
