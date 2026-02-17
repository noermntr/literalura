package com.aluracursos.literalura.principal;

import com.aluracursos.literalura.modelos.Autor;
import com.aluracursos.literalura.modelos.Datos;
import com.aluracursos.literalura.modelos.DatosLibro;
import com.aluracursos.literalura.modelos.Libro;
import com.aluracursos.literalura.repository.AutorRepository;
import com.aluracursos.literalura.repository.LibroRepository;
import com.aluracursos.literalura.servicios.ConsumoAPI;
import com.aluracursos.literalura.servicios.ConvierteDatos;

import java.util.*;

public class Principal {
    public Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;
    private String lineaDivisoria = "----------------------------";

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    ----------------------------
                    Ingrese el número de opción deseada:
                    
                    1 - Buscar libro por su título
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos hasta un año determinado
                    5 - Listar libros por su idioma
                    
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivos();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println(lineaDivisoria);
                    System.out.println("Cerrando aplicación...");
                    break;
                default:
                    System.out.println(lineaDivisoria);
                    System.out.println("Opción inválida.");
            }
        }
    }

    private Datos getDatosLibro() {
        System.out.println(lineaDivisoria);
        System.out.println("Ingrese el nombre del libro que desea buscar: ");
        var nombreLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + nombreLibro.replace(" ", "+"));
        return conversor.obtenerDatos(json, Datos.class);
    }

    private void buscarLibroPorTitulo() {
        Datos datos = getDatosLibro();
        if (!datos.resultados().isEmpty()) {
            DatosLibro primerLibro = datos.resultados().get(0);
            Optional<Libro> libroRegistrado = libroRepository.findByTituloContainsIgnoreCase(primerLibro.titulo());

            if (libroRegistrado.isPresent()) {
                System.out.println(lineaDivisoria);
                System.out.println("El libro ya está registrado: [" + libroRegistrado.get() + "] \n");
            } else {
                var datosAutor = primerLibro.autor().get(0);
                Autor autor = autorRepository.findByNombreContainsIgnoreCase(datosAutor.nombre())
                        .orElseGet(() -> {
                            Autor nuevoAutor = new Autor(datosAutor);
                            return autorRepository.save(nuevoAutor);
                        });
                Libro libro = new Libro(primerLibro, autor);
                libroRepository.save(libro);
                System.out.println(lineaDivisoria);
                System.out.println("Libro registrado!");
                System.out.println(libro);
            }
        } else {
            System.out.println(lineaDivisoria);
            System.out.println("Libro no encontrado.");
        }
    }

    private void listarLibrosRegistrados() {
        List<Libro> libros = libroRepository.findAll();
        if (libros.isEmpty()) {
            System.out.println(lineaDivisoria);
            System.out.println("Todavía no hay libros registrados.");
        } else {
            System.out.println(lineaDivisoria);
            System.out.println("Aquí está su lista de libros registrados hasta el momento: \n");
            libros.forEach(System.out::println);
        }
    }

    private void listarAutoresRegistrados() {
        List<Autor> autores = autorRepository.findAll();
        System.out.println(lineaDivisoria);
        System.out.println("Aquí está su lista de autores registrados hasta el momento: \n");
        autores.forEach(System.out::println);
    }

    private void listarAutoresVivos() {
        System.out.println(lineaDivisoria);
        System.out.println("Ingrese el año que desea consultar:");
        try {
            var fecha = Integer.parseInt(teclado.nextLine());

            if (fecha < 0 || fecha > 2025) {
                System.out.println(lineaDivisoria);
                System.out.println("Por favor, ingrese un año válido (entre 0 y 2025).");
                return;
            }

            List<Autor> autoresVivos = autorRepository.buscarAutoresVivosPorFechaDeterminada(fecha);

            if (autoresVivos.isEmpty()) {
                System.out.println(lineaDivisoria);
                System.out.println("No se encontraron autores vivos en ese año.");
            } else {
                System.out.println(lineaDivisoria);
                System.out.println("Autores vivos en " + fecha + ": \n");
                autoresVivos.forEach(System.out::println);
            }
        } catch (NumberFormatException e) {
            System.out.println(lineaDivisoria);
            System.out.println("ERROR! Para elegir el año debe utilizar números sin coma.");
        }
    }

    private void listarLibrosPorIdioma() {
        System.out.println("""
                ----------------------------
                Ingrese las siglas del idioma del libro que desea buscar: 
                
                en -> inglés
                es -> español
                fr -> francés
                pt -> portugués
                
                """);
        var idioma = teclado.nextLine();

        List<Libro> librosPorIdioma = libroRepository.findByIdioma(idioma);

        if (librosPorIdioma.isEmpty()) {
            System.out.println(lineaDivisoria);
            System.out.println("No se encontraron libros en ese idioma en nuestra base de datos. ");
        } else {
            System.out.println(lineaDivisoria);
            System.out.println("Libros encontrados! \n");
            librosPorIdioma.forEach(System.out::println);
        }
    }
}