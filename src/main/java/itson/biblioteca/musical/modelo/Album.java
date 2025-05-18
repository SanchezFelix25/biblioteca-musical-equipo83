/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.biblioteca.musical.modelo;

import java.util.List;

/**
 *
 * @author Felix_isq
 */
public class Album {

    private String id;
    private String nombre;
    private String fechaLanzamiento;
    private String genero;
    private String imagen;
    private String idArtista;
    private List<String> canciones;

    public Album() {
    }

    public Album(String nombre, String fechaLanzamiento, String genero, String imagen, String idArtista, List<String> canciones) {
        this.nombre = nombre;
        this.fechaLanzamiento = fechaLanzamiento;
        this.genero = genero;
        this.imagen = imagen;
        this.idArtista = idArtista;
        this.canciones = canciones;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(String fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(String idArtista) {
        this.idArtista = idArtista;
    }

    public List<String> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<String> canciones) {
        this.canciones = canciones;
    }

}
