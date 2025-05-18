/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.biblioteca.musical.modelo;

/**
 *
 * @author Felix_isq
 */
public class Cancion {

    private String id;
    private String titulo;
    private String duracion;
    private String idAlbum;

    public Cancion() {
    }

    public Cancion(String titulo, String duracion, String idAlbum) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.idAlbum = idAlbum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(String idAlbum) {
        this.idAlbum = idAlbum;
    }
    
    
}
