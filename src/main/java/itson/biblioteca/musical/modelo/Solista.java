/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.biblioteca.musical.modelo;

/**
 *
 * @author Felix_isq
 */
public class Solista extends Artista{
        public Solista(String nombre, String genero, String imagen) {
        super(nombre, genero, imagen, "solista");
    }

    public Solista() {
        this.tipo = "solista";
    }
}
