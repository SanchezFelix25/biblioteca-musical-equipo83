package itson.biblioteca.musical.gui;

import itson.biblioteca.musical.persistencia.AlbumDAO;
import itson.biblioteca.musical.persistencia.ArtistaDAO;
import itson.biblioteca.musical.persistencia.CancionDAO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import org.bson.Document;
import java.util.List;

public class BibliotecaPrincipalForm extends JFrame {

    private JTabbedPane tabbedPane;
    private JPanel panelBusqueda;
    private JTextField txtBuscar;
    private JComboBox<String> cmbTipoBusqueda;
    private JButton btnBuscar;

    private JTable tablaArtistas;
    private JTable tablaAlbumes;
    private JTable tablaCanciones;
    private JTable tablaFavoritos;

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
        tablaAlbumes = new JTable();
        tablaCanciones = new JTable();
        tablaFavoritos = new JTable();

        tabbedPane.addTab("Artistas", new JScrollPane(tablaArtistas));
        tabbedPane.addTab("Álbumes", new JScrollPane(tablaAlbumes));
        tabbedPane.addTab("Canciones", new JScrollPane(tablaCanciones));
        tabbedPane.addTab("Favoritos", new JScrollPane(tablaFavoritos));

        add(tabbedPane, BorderLayout.CENTER);

        // Cargar estructura de las tablas por defecto
        cargarEstructuraTablas();
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
