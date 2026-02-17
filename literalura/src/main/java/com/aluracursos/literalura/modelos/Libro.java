package com.aluracursos.literalura.modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "autor_id")
    private Autor autor;
    private String idioma;
    private Double numeroDeDescargas;

    public Libro(){}

    public Libro(DatosLibro datosLibro, Autor autor) {
        this.titulo = datosLibro.titulo();
        this.idioma = datosLibro.idioma().isEmpty() ? "Desconocido" : datosLibro.idioma().get(0);
        this.numeroDeDescargas = (datosLibro.numeroDeDescargas());
        this.autor = autor;
    }

    @Override
    public String toString() {
        return
                "Título del libro: '" + titulo + '\'' +
                ", autor: '" + autor + '\'' +
                ", idioma: '" + idioma + '\'' +
                ", número de descargas: " + numeroDeDescargas;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = this.autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
    }
}
