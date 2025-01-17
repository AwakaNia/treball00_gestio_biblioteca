package com.biblioteca;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class funcionsUsuaris {

    // FUNCIONES

    // Función para añadir un usuario
    public static void afegirUsuari() {
        Scanner scanner = new Scanner(System.in);

        // Obtener el nombre, apellido y teléfono del usuario
        System.out.print("Introduce el nombre del usuario: ");
        String nom = scanner.nextLine();

        System.out.print("Introduce el apellido del usuario: ");
        String cognom = scanner.nextLine();

        System.out.print("Introduce el teléfono del usuario: ");
        String telefon = scanner.nextLine();

        // Leer el archivo JSON existente y cargarlo en una estructura de datos
        JsonArray usuaris = leerUsuarisJson();

        // Obtener el próximo id disponible (es el siguiente número en la lista)
        int nextId = usuaris.size() + 1;

        // Crear el objeto JSON para el nuevo usuario
        JsonObject nouUsuari = new JsonObject();
        nouUsuari.addProperty("id", nextId);
        nouUsuari.addProperty("nom", nom);
        nouUsuari.addProperty("cognom", cognom);
        nouUsuari.addProperty("telefon", telefon);

        // Añadir el nuevo usuario al array de usuarios
        usuaris.add(nouUsuari);

        // Guardar el array de usuarios actualizado en el archivo JSON
        guardarUsuarisJson(usuaris);

        System.out.println("El usuario ha sido añadido correctamente.");
    }

    // Función para leer el archivo JSON
    public static JsonArray leerUsuarisJson() {
        JsonArray usuaris = new JsonArray();
        File archivo = new File("data/usuaris.json");

        if (archivo.exists()) {
            try {
                FileReader reader = new FileReader("data/usuaris.json");
                Gson gson = new Gson();
                usuaris = gson.fromJson(reader, JsonArray.class);
                reader.close();
            } catch (IOException e) {
                System.out.println("Error al leer el archivo JSON.");
            }
        }

        return usuaris;
    }

    // Función para guardar el archivo JSON
    public static void guardarUsuarisJson(JsonArray usuaris) {
        try {
            FileWriter writer = new FileWriter("data/usuaris.json");
            Gson gson = new Gson();
            gson.toJson(usuaris, writer);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo JSON.");
        }
    }

    // Función para listar los usuarios con sus ID
    public static void llistarUsuarisID() {
        JsonArray usuaris = leerUsuarisJson();

        if (usuaris.size() == 0) {
            System.out.println("No hay usuarios registrados.");
        } else {
            System.out.println("Llistant els usuaris disponibles:");
            for (int i = 0; i < usuaris.size(); i++) {
                JsonObject usuari = usuaris.get(i).getAsJsonObject();
                int id = usuari.get("id").getAsInt();
                String nom = usuari.get("nom").getAsString();
                System.out.println("ID: " + id + " - Nom: " + nom);
            }
        }
    }

    // Función para eliminar un usuario por ID
    public static void eliminarUsuari() {
        Scanner scanner = new Scanner(System.in);

        // Listar los usuarios
        llistarUsuarisID();

        // Pedir al usuario el ID del usuario a eliminar
        System.out.print("Introduce el ID del usuario que quieres eliminar: ");
        int idEliminar = Integer.parseInt(scanner.nextLine().trim());

        // Leer el archivo JSON
        JsonArray usuaris = leerUsuarisJson();
        boolean encontrado = false;

        // Buscar y eliminar el usuario con el ID dado
        for (int i = 0; i < usuaris.size(); i++) {
            JsonObject usuari = usuaris.get(i).getAsJsonObject();
            int id = usuari.get("id").getAsInt();

            if (id == idEliminar) {
                usuaris.remove(i);
                encontrado = true;
                System.out.println("El usuario con ID " + idEliminar + " ha sido eliminado.");
                break;
            }
        }

        if (!encontrado) {
            System.out.println("No se ha encontrado un usuario con ese ID.");
        } else {
            // Guardar el archivo actualizado
            guardarUsuarisJson(usuaris);
        }
    }

    public static void modificarUsuari() {
        Scanner scanner = new Scanner(System.in);
        JsonArray usuaris = leerUsuarisJson();

        if (usuaris.size() == 0) {
            System.out.println("No hi ha usuaris disponibles per modificar.");
            return;
        }

        System.out.println("Llistat d'usuaris:");
        for (int i = 0; i < usuaris.size(); i++) {
            JsonObject usuari = usuaris.get(i).getAsJsonObject();
            System.out.println("ID: " + usuari.get("id").getAsInt() + " - Nom: " + usuari.get("nom").getAsString());
        }

        System.out.print("Introdueix l'ID de l'usuari a modificar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea

        JsonObject usuariAModificar = null;
        for (int i = 0; i < usuaris.size(); i++) {
            JsonObject usuari = usuaris.get(i).getAsJsonObject();
            if (usuari.get("id").getAsInt() == id) {
                usuariAModificar = usuari;
                break;
            }
        }

        if (usuariAModificar == null) {
            System.out.println("No s'ha trobat cap usuari amb l'ID indicat.");
            return;
        }

        System.out.print("Introdueix el nou nom de l'usuari (deixa en blanc per mantenir-lo): ");
        String nouNom = scanner.nextLine();
        if (!nouNom.isEmpty()) {
            usuariAModificar.addProperty("nom", nouNom);
        }

        System.out.print("Introdueix el nou cognom (deixa en blanc per mantenir-lo): ");
        String nouCognom = scanner.nextLine();
        if (!nouCognom.isEmpty()) {
            usuariAModificar.addProperty("cognom", nouCognom);
        }

        System.out.print("Introdueix el nou telèfon (deixa en blanc per mantenir-lo): ");
        String nouTelefon = scanner.nextLine();
        if (!nouTelefon.isEmpty()) {
            usuariAModificar.addProperty("telefon", nouTelefon);
        }

        guardarUsuarisJson(usuaris);

        System.out.println("Usuari modificat correctament.");
    }




    //Llistat d'usuaris amb préstecs actius
    public static void llistarUsuariPrestecsActius()  {
        try {
            // Leer los archivos JSON
            JSONArray usuarisJson = readJsonArray("data/usuaris.json");
            JSONArray prestecsJson = readJsonArray("data/prestecs.json");
            JSONArray llibresJson = readJsonArray("data/llibres.json");

            // Obtener la fecha actual
            LocalDate fechaActual = LocalDate.now();

            // Filtrar préstamos activos
            List<JSONObject> prestamosActivos = new ArrayList<>();
            for (int i = 0; i < prestecsJson.length(); i++) {
                JSONObject prestec = prestecsJson.getJSONObject(i);

                // Comprobar si la fecha de devolución está vacía o posterior a la fecha actual
                String dataDevolucio = prestec.optString("DataDevolucio", null);
                if (dataDevolucio == null || dataDevolucio.isEmpty() ||
                        LocalDate.parse(dataDevolucio, DateTimeFormatter.ISO_DATE).isAfter(fechaActual)) {
                    prestamosActivos.add(prestec);
                }
            }

            // Generar listado de usuarios con préstamos activos
            List<JSONObject> usuariosConPrestamosActivos = new ArrayList<>();
            for (int i = 0; i < usuarisJson.length(); i++) {
                JSONObject usuari = usuarisJson.getJSONObject(i);

                // Comprobar si el usuario tiene préstamos activos
                int idUsuario = usuari.getInt("id");
                boolean tienePrestamosActivos = prestamosActivos.stream()
                        .anyMatch(prestec -> prestec.getInt("Id d'usuari") == idUsuario);

                if (tienePrestamosActivos) {
                    usuariosConPrestamosActivos.add(usuari);
                }
            }

            // Mostrar el listado en consola
            System.out.println("Llistat d'usuaris amb préstecs actius:");
            for (JSONObject usuario : usuariosConPrestamosActivos) {
                System.out.println(usuario.getString("nom") + " " +
                        usuario.getString("cognom") + " (Telèfon: " +
                        usuario.getString("telefon") + ")");

                // Mostrar información de los libros asociados a los préstamos activos del usuario
                int idUsuario = usuario.getInt("id");
                prestamosActivos.stream()
                        .filter(prestec -> prestec.getInt("Id d'usuari") == idUsuario)
                        .forEach(prestec -> {
                            int idLlibre = prestec.getInt("Id_llibres");

                            // Buscar el libro en la lista de libros
                            for (int j = 0; j < llibresJson.length(); j++) {
                                JSONObject llibre = llibresJson.getJSONObject(j);
                                if (llibre.getInt("id") == idLlibre) {
                                    System.out.println("\t- Llibre ID: " + llibre.getInt("id") +
                                            ", Títol: " + llibre.getString("titol"));
                                }
                            }
                        });
}


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para leer un archivo JSON y devolver un JSONArray
    public static JSONArray readJsonArray(String filePath) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        return new JSONArray(content);
    }


       // Llistat d'usuaris amb préstecs no actius
public static void llistarUsuariPrestecsNoActius() {
    try {
        // Leer los archivos JSON
        JSONArray usuarisJson = readJsonArray("data/usuaris.json");
        JSONArray prestecsJson = readJsonArray("data/prestecs.json");
        JSONArray llibresJson = readJsonArray("data/llibres.json");

        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();

        // Filtrar préstamos no activos (aquellos con fecha de devolución pasada o sin devolución)
        List<JSONObject> prestamosNoActivos = new ArrayList<>();
        for (int i = 0; i < prestecsJson.length(); i++) {
            JSONObject prestec = prestecsJson.getJSONObject(i);

            // Comprobar si la fecha de devolución está pasada o es nula
            String dataDevolucio = prestec.optString("DataDevolucio", null);
            if (dataDevolucio == null || dataDevolucio.isEmpty() ||
                    LocalDate.parse(dataDevolucio, DateTimeFormatter.ISO_DATE).isBefore(fechaActual)) {
                prestamosNoActivos.add(prestec);
            }
        }

        // Obtener los usuarios que no tienen préstamos activos
        List<JSONObject> usuariosSinPrestamosActivos = new ArrayList<>();
        for (int i = 0; i < usuarisJson.length(); i++) {
            JSONObject usuari = usuarisJson.getJSONObject(i);

            // Comprobar si el usuario tiene préstamos activos en prestecsJson
            int idUsuario = usuari.getInt("id");
            boolean tienePrestamosActivos = prestamosNoActivos.stream()
                    .anyMatch(prestec -> prestec.getInt("Id d'usuari") == idUsuario);

            // Si el usuario no tiene préstamos activos, lo agregamos a la lista
            if (!tienePrestamosActivos) {
                usuariosSinPrestamosActivos.add(usuari);
            }
        }

        // Mostrar el listado en consola
        System.out.println("Llistat d'usuaris sense préstecs actius:");
        for (JSONObject usuario : usuariosSinPrestamosActivos) {
            System.out.println(usuario.getString("nom") + " " +
                    usuario.getString("cognom") + " (Telèfon: " +
                    usuario.getString("telefon") + ")");

            // Mostrar información de los libros asociados a los préstamos no activos del usuario
            int idUsuario = usuario.getInt("id");
            prestamosNoActivos.stream()
                    .filter(prestec -> prestec.getInt("Id d'usuari") == idUsuario)
                    .forEach(prestec -> {
                        int idLlibre = prestec.getInt("Id_llibres");

                        // Buscar el libro en la lista de libros
                        for (int j = 0; j < llibresJson.length(); j++) {
                            JSONObject llibre = llibresJson.getJSONObject(j);
                            if (llibre.getInt("id") == idLlibre) {
                                System.out.println("\t- Llibre ID: " + llibre.getInt("id") +
                                        ", Títol: " + llibre.getString("titol"));
                            }
                        }
                    });
        }

    } catch (Exception e) {
        e.printStackTrace();
    }
}






    
}




