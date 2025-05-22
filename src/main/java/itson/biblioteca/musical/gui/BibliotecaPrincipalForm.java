package itson.biblioteca.musical.gui;

import itson.biblioteca.musical.persistencia.AlbumDAO;
import itson.biblioteca.musical.persistencia.ArtistaDAO;
import itson.biblioteca.musical.persistencia.CancionDAO;
import itson.biblioteca.musical.persistencia.FavoritoDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import org.bson.Document;
import java.util.List;

public class BibliotecaPrincipalForm extends JFrame {

    private String usuarioAutenticadoId = "123abc";  // ejemplo fijo
    private JTabbedPane tabbedPane;
    private JPanel panelBusqueda;
    private JTextField txtBuscar;
    private JComboBox<String> cmbTipoBusqueda;
    private JButton btnBuscar;

    private JTable tablaArtistas;
    private JTable tablaAlbumes;
    private JTable tablaCanciones;
    private JTable tablaFavoritos;

    private JButton btnFavoritoArtista;
    private JButton btnFavoritoAlbum;
    private JButton btnFavoritoCancion;
    private JButton btnQuitarFavorito;
    
    public BibliotecaPrincipalForm() {
        setTitle("Biblioteca Musical");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initComponentes();

        cargarArtistas(); // Carga inicial de artistas desde MongoDB
        cargarAlbumes(); // Carga inicial de albunes desde MongoDb
        cargarCanciones(); // carga inicial de canciones desde MongoDB
        cargarFavoritos(usuarioAutenticadoId);
    }

    private void initComponentes() {

        // Panel superior de búsqueda
        panelBusqueda = new JPanel();
        txtBuscar = new JTextField(30);
        cmbTipoBusqueda = new JComboBox<>(new String[]{"Todos", "Artista", "Álbum", "Canción"});
        btnBuscar = new JButton("Buscar");

        panelBusqueda.add(new JLabel("Buscar:"));
        panelBusqueda.add(txtBuscar);
        panelBusqueda.add(cmbTipoBusqueda);
        panelBusqueda.add(btnBuscar);

        add(panelBusqueda, BorderLayout.NORTH);

        // Pestañas
        tabbedPane = new JTabbedPane();

        tablaArtistas = new JTable();

        // 🔹 Panel de ARTISTAS
        tablaArtistas = new JTable();
        btnFavoritoArtista = new JButton(" Marcar/Desmarcar como Favorito");
        JPanel panelArtistas = new JPanel(new BorderLayout());
        panelArtistas.add(new JScrollPane(tablaArtistas), BorderLayout.CENTER);
        panelArtistas.add(btnFavoritoArtista, BorderLayout.SOUTH);
        tabbedPane.addTab("Artistas", panelArtistas);

        tablaAlbumes = new JTable();
        tablaCanciones = new JTable();
        tablaFavoritos = new JTable();

        //tabbedPane.addTab("Artistas", new JScrollPane(tablaArtistas));
        //tabbedPane.addTab("Álbumes", new JScrollPane(tablaAlbumes));
        ///  tabbedPane.addTab("Canciones", new JScrollPane(tablaCanciones));
        // Panel de Álbumes
        JPanel panelAlbumes = new JPanel(new BorderLayout());
        panelAlbumes.add(new JScrollPane(tablaAlbumes), BorderLayout.CENTER);
        btnFavoritoAlbum = new JButton(" Marcar/Desmarcar como Favorito");
        panelAlbumes.add(btnFavoritoAlbum, BorderLayout.SOUTH);
        tabbedPane.addTab("Álbumes", panelAlbumes);

        // Panel de Canciones
        JPanel panelCanciones = new JPanel(new BorderLayout());
        panelCanciones.add(new JScrollPane(tablaCanciones), BorderLayout.CENTER);
        btnFavoritoCancion = new JButton("️ Marcar/Desmarcar como Favorito");
        panelCanciones.add(btnFavoritoCancion, BorderLayout.SOUTH);
        tabbedPane.addTab("Canciones", panelCanciones);

        //Panel de Favoritos
        JPanel panelFavoritos = new JPanel(new BorderLayout());
        panelFavoritos.add(new JScrollPane(tablaFavoritos), BorderLayout.CENTER);
        btnQuitarFavorito = new JButton("Quitar Favorito"); 
        panelFavoritos.add(btnQuitarFavorito, BorderLayout.SOUTH);
        tabbedPane.addTab("Favoritos", panelFavoritos);
        //tabbedPane.addTab("Favoritos", new JScrollPane(tablaFavoritos));
        
        
        add(tabbedPane, BorderLayout.CENTER);

        // Cargar estructura de las tablas por defecto
        cargarEstructuraTablas();
        btnBuscar.addActionListener(e -> realizarBusqueda());

        // ARTISTAS
    btnFavoritoArtista.addActionListener (e -> {
    int fila = tablaArtistas.getSelectedRow();
        if (fila >= 0) {
            String nombre = tablaArtistas.getValueAt(fila, 0).toString();
            String id = new ArtistaDAO().obtenerIdArtistaPorNombre(nombre);
            alternarFavorito(usuarioAutenticadoId, "artista", id, nombre);
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un artista para marcar como favorito.");
        }
    }

    );

    // ÁLBUMES
    btnFavoritoAlbum.addActionListener (e -> {
    int fila = tablaAlbumes.getSelectedRow();
        if (fila >= 0) {
            String nombre = tablaAlbumes.getValueAt(fila, 0).toString();
            String id = new AlbumDAO().obtenerIdAlbumPorNombre(nombre);
            alternarFavorito(usuarioAutenticadoId, "álbum", id, nombre);
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un álbum para marcar como favorito.");
        }
    }

    );

    // CANCIONES
    btnFavoritoCancion.addActionListener (e -> {
    int fila = tablaCanciones.getSelectedRow();
        if (fila >= 0) {
            String titulo = tablaCanciones.getValueAt(fila, 0).toString();
            String id = new CancionDAO().obtenerIdCancionPorTitulo(titulo);
            alternarFavorito(usuarioAutenticadoId, "canción", id, titulo);
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona una canción para marcar como favorita.");
        }
    }

   );
    
    btnQuitarFavorito.addActionListener(e -> {
    int fila = tablaFavoritos.getSelectedRow();
    if (fila >= 0) {
        String tipo = tablaFavoritos.getValueAt(fila, 0).toString();
        String nombre = tablaFavoritos.getValueAt(fila, 1).toString();
        String id = buscarIdPorTipoYNombre(tipo, nombre);
        new FavoritoDAO().eliminarFavorito(usuarioAutenticadoId, tipo, id);
        cargarFavoritos(usuarioAutenticadoId);
    } else {
        JOptionPane.showMessageDialog(this, "Selecciona un favorito para quitar.");
    }
    });

        
    }
    

    private void alternarFavorito(String idUsuario, String tipo, String idElemento, String nombre) {
    FavoritoDAO dao = new FavoritoDAO();
    String fechaActual = java.time.LocalDate.now().toString();

    if (idElemento == null || idElemento.isEmpty()) {
        JOptionPane.showMessageDialog(this, "️No se pudo obtener el ID del elemento. Verifica si el nombre existe en MongoDB.");
        return;
    }
    
    if (dao.esFavorito(idUsuario, tipo, idElemento)) {
            dao.eliminarFavorito(idUsuario, tipo, idElemento);
            JOptionPane.showMessageDialog(this, tipo + " eliminado de favoritos.");
            } else {
            dao.agregarFavorito(idUsuario, tipo, idElemento, nombre, fechaActual);
            JOptionPane.showMessageDialog(this, tipo + " agregado a favoritos.");
        }

        cargarFavoritos(idUsuario);
    }


    
    private void cargarEstructuraTablas() {
        DefaultTableModel modeloArtistas = new DefaultTableModel(new String[]{"Nombre", "Género", "Tipo"}, 0);
        tablaArtistas.setModel(modeloArtistas);

        DefaultTableModel modeloAlbumes = new DefaultTableModel(new String[]{"Nombre", "Fecha", "Género"}, 0);
        tablaAlbumes.setModel(modeloAlbumes);

        DefaultTableModel modeloCanciones = new DefaultTableModel(new String[]{"Título", "Duración", "Álbum"}, 0);
        tablaCanciones.setModel(modeloCanciones);

        DefaultTableModel modeloFavoritos = new DefaultTableModel(new String[]{"Tipo", "Nombre", "Fecha"}, 0);
        tablaFavoritos.setModel(modeloFavoritos);
    }

    private void cargarArtistas() {
        ArtistaDAO dao = new ArtistaDAO();
        List<Document> artistas = dao.obtenerTodos();

        DefaultTableModel model = (DefaultTableModel) tablaArtistas.getModel();
        model.setRowCount(0); // limpia la tabla

        for (Document doc : artistas) {
            String nombre = doc.getString("nombre");
            String genero = doc.getString("genero");
            String tipo = doc.getString("tipo");
            model.addRow(new Object[]{nombre, genero, tipo});
        }
    }

    private void cargarFavoritos(String idUsuario) {
    FavoritoDAO dao = new FavoritoDAO();
    List<Document> favoritos = dao.obtenerFavoritosPorUsuario(idUsuario);

    DefaultTableModel model = (DefaultTableModel) tablaFavoritos.getModel();
    model.setRowCount(0); // Limpia la tabla

    for (Document doc : favoritos) {
        String tipo = doc.getString("tipo");
        String nombre = doc.getString("nombre");
        String fecha = doc.getString("fecha");

        model.addRow(new Object[]{tipo, nombre, fecha});
        }
    }
    
    private String buscarIdPorTipoYNombre(String tipo, String nombre) {
    switch (tipo.toLowerCase()) {
        case "artista" -> {
            return new ArtistaDAO().obtenerIdArtistaPorNombre(nombre);
        }
        case "álbum", "album" -> {
            return new AlbumDAO().obtenerIdAlbumPorNombre(nombre);
        }
        case "canción", "cancion" -> {
            return new CancionDAO().obtenerIdCancionPorTitulo(nombre);
        }
        default -> {
            JOptionPane.showMessageDialog(this, "Tipo no reconocido: " + tipo);
            return null;
            }
        }
    }


    
    private void realizarBusqueda() {
        String texto = txtBuscar.getText().trim().toLowerCase();
        String tipo = (String) cmbTipoBusqueda.getSelectedItem();

        if (texto.isEmpty()) {
            JOptionPane.showMessageDialog(this, " Por favor, escribe algo para buscar.", "Campo vacío", JOptionPane.WARNING_MESSAGE);

            return;
        }

        switch (tipo) {
            case "Artista" -> {
                System.out.println("tipo artistas");
                buscarArtistas(texto);
            }
            case "Álbum" -> {
                System.out.println("Tipo album");
                buscarAlbumes(texto);
            }
            case "Canción" -> {
                System.out.println("Tipo de cancion");
                buscarCanciones(texto);
            }
            case "Todos" -> {
                System.out.println("Tipo de Todos");
                buscarArtistas(texto);
                buscarAlbumes(texto);
                buscarCanciones(texto);
            }
        }
    }

    private void buscarArtistas(String filtro) {
        ArtistaDAO dao = new ArtistaDAO();
        List<Document> artistas = dao.obtenerTodos();

        DefaultTableModel model = (DefaultTableModel) tablaArtistas.getModel();
        model.setRowCount(0);

        for (Document doc : artistas) {
            String nombre = doc.getString("nombre").toLowerCase();
            if (nombre.contains(filtro.toLowerCase())) {
                model.addRow(new Object[]{
                    doc.getString("nombre"),
                    doc.getString("genero"),
                    doc.getString("tipo")
                });
            }
        }
    }

    private void buscarAlbumes(String filtro) {
        AlbumDAO dao = new AlbumDAO();
        List<Document> albumes = dao.obtenerTodos();

        DefaultTableModel model = (DefaultTableModel) tablaAlbumes.getModel();
        model.setRowCount(0);

        for (Document doc : albumes) {
            String nombre = doc.getString("nombre").toLowerCase();
            if (nombre.contains(filtro.toLowerCase())) {
                model.addRow(new Object[]{
                    doc.getString("nombre"),
                    doc.getString("fechaLanzamiento"),
                    doc.getString("genero")
                });
            }
        }
    }

    private void buscarCanciones(String filtro) {
        CancionDAO dao = new CancionDAO();
        List<Document> canciones = dao.obtenerTodos();

        DefaultTableModel model = (DefaultTableModel) tablaCanciones.getModel();
        model.setRowCount(0);

        for (Document doc : canciones) {
            String titulo = doc.getString("titulo").toLowerCase();
            if (titulo.contains(filtro.toLowerCase())) {
                model.addRow(new Object[]{
                    doc.getString("titulo"),
                    doc.getString("duracion"),
                    doc.getString("idAlbum")
                });
            }
        }
    }

    private void cargarAlbumes() {
        AlbumDAO dao = new AlbumDAO();
        List<Document> albumes = dao.obtenerTodos();

        DefaultTableModel model = (DefaultTableModel) tablaAlbumes.getModel();
        model.setRowCount(0);

        for (Document doc : albumes) {
            String nombre = doc.getString("nombre");
            String fecha = doc.getString("fechaLanzamiento");
            String genero = doc.getString("genero");
            model.addRow(new Object[]{nombre, fecha, genero});
        }
    }

    private void cargarCanciones() {
        CancionDAO dao = new CancionDAO();
        List<Document> canciones = dao.obtenerTodos();

        DefaultTableModel model = (DefaultTableModel) tablaCanciones.getModel();
        model.setRowCount(0);

        for (Document doc : canciones) {
            String titulo = doc.getString("titulo");
            String duracion = doc.getString("duracion");
            String idAlbum = doc.getString("idAlbum");
            model.addRow(new Object[]{titulo, duracion, idAlbum});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BibliotecaPrincipalForm().setVisible(true));
    }
}
