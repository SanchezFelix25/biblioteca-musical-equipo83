/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package itson.biblioteca.musical.modelo;

/**
 *
 * @author Felix_isq
 */
public class IntegranteBanda {

    private String nombre;
    private String rol;
    private String fechaIngreso;
    private String fechaSalida;
    private boolean activo;

    public IntegranteBanda() {
    }

    public IntegranteBanda(String nombre, String rol, String fechaIngreso, String fechaSalida, boolean activo) {
        this.nombre = nombre;
        this.rol = rol;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
        this.activo = activo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

}
