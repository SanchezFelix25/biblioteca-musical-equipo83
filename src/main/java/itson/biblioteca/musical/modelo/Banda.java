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
public class Banda extends Artista{

    private List<IntegranteBanda> integrantes;

    public Banda() {
        this.tipo = "banda";
    }

    public Banda(String nombre, String genero, String imagen, List<IntegranteBanda> integrantes) {
        super(nombre, genero, imagen, "banda");
        this.integrantes = integrantes;
    }

    public List<IntegranteBanda> getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(List<IntegranteBanda> integrantes) {
        this.integrantes = integrantes;
    }
}
