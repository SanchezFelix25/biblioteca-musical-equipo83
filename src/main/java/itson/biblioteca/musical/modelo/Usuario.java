package itson.biblioteca.musical.modelo;

import java.util.List;

public class Usuario {
    private String id;
    private String usuario;
    private String correo;
    private String password;
    private String imagen;
    private List<String> favoritos;
    private List<String> generosNoDeseados;

    public Usuario() {}

    public Usuario(String usuario, String correo, String password, String imagen,
                   List<String> favoritos, List<String> generosNoDeseados) {
        this.usuario = usuario;
        this.correo = correo;
        this.password = password;
        this.imagen = imagen;
        this.favoritos = favoritos;
        this.generosNoDeseados = generosNoDeseados;
    }

    // Getters y setters
    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getImagen() { return imagen; }
    public void setImagen(String imagen) { this.imagen = imagen; }

    public List<String> getFavoritos() { return favoritos; }
    public void setFavoritos(List<String> favoritos) { this.favoritos = favoritos; }

    public List<String> getGenerosNoDeseados() { return generosNoDeseados; }
    public void setGenerosNoDeseados(List<String> generosNoDeseados) { this.generosNoDeseados = generosNoDeseados; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
}
