# 📚 Literalura


Literalura es una aplicación de consola desarrollada en Java que funciona como un catálogo de libros. Permite a los usuarios buscar, registrar y consultar información sobre libros y autores, utilizando la base de datos GUTENDEX.com para la persistencia de los datos.

Este proyecto está enfocado en el consumo de una API externa y en la persistencia de datos mediante tecnologías del ecosistema Spring. 



# 🚀 Tecnologías Utilizadas


- Java

- Spring Boot

- Spring Data JPA

- PostgreSQL

- API externa de libros http://GUTENDEX.com/books/ (para consulta de información)

- Maven (gestión de dependencias)



# 🛠️ Requisitos Previos


Antes de ejecutar el proyecto, asegurate de contar con:

- Java 17 o superior

- PostgreSQL

- Maven

- Conexión a Internet (para el consumo de la API)



# 🎯 Objetivo del Proyecto


El objetivo principal de Literalura es:

- Consumir datos desde una API externa de libros.

- Convertir y mapear la información recibida.

- Persistir los datos en una base de datos relacional.

- Permitir consultas dinámicas desde una aplicación de consola.



# 📌 Funcionalidades


La aplicación cuenta con las siguientes funcionalidades disponibles desde el menú principal:

🔎 **Buscar libro por su título**

Permite buscar un libro en la API externa y registrarlo en la base de datos si no existe.

📖 **Listar libros registrados**

Muestra todos los libros almacenados en la base de datos.

✍️ **Listar autores registrados**

Devuelve la lista completa de autores guardados.

📅 **Listar autores vivos hasta un año determinado**

Permite ingresar un año y consultar qué autores estaban vivos hasta esa fecha.

🌍 **Listar libros por idioma**

Filtra los libros almacenados según el idioma seleccionado.

🚪 **Salir de la aplicación**

Finaliza la ejecución del programa.



# ⚙️ Configuración


1 - Clona este repositorio o descarga este repositorio en tu computadora.

2 - Abre el proyecto en tu IDE preferido (IntelliJ, Eclipse, VS Code, etc.).

3 - Configurar la base de datos en el archivo application.properties:

<img width="442" height="109" alt="image" src="https://github.com/user-attachments/assets/f757f547-2cad-48bd-8d8f-d98a2c5e7714" />



# ▶️ Uso de la Aplicación


1 - Ejecuta la aplicación.

2 - Elige entre las opciones 1 a 5.

3 - Para cada opción existe la posibilidad de otro menú a completar por el usuario.

5 - Se repetirá el menú principal cuántas veces necesite el usuario y saldrá de la aplicación eligiendo la opción 0.



# 👩‍💻 Autor


Proyecto realizado por Noelia Rementeria el cual forma parte del Challenge ONE - Literalura propuesto por Alura Latam en conjunto con Oracle dentro de la formación como principiante en Programación.



# 📜 Licencia


Este proyecto se distribuye con fines educativos. Puedes usarlo, modificarlo y distribuirlo libremente.
