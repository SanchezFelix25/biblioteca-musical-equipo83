
package itson.biblioteca.musical.persistencia;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;


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

}
