package com.biblioteca;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class funcionsLlibres {

    // FUNCIONES

    // Función para añadir un libro
    public static void afegirLlibre() {
        Scanner scanner = new Scanner(System.in);
        
        // Obtener el título y el autor del libro
        System.out.print("Introduce el título del libro: ");
        String titol = scanner.nextLine();
        
        System.out.print("Introduce el autor del libro (puedes ingresar varios autores separados por comas): ");
        String autoresInput = scanner.nextLine();
        
        // Convertir los autores en un array de strings
        String[] autores = autoresInput.split(",");
        
        // Leer el archivo JSON existente y cargarlo en una estructura de datos
        JsonArray llibres = leerLlibresJson();
        
        // Obtener el próximo id disponible (es el siguiente número en la lista)
        int nextId = llibres.size() + 1;
        
        // Crear el objeto JSON para el nuevo libro
        JsonObject nouLlibre = new JsonObject();
        nouLlibre.addProperty("id", nextId);
        nouLlibre.addProperty("titol", titol);
        
        // Añadir los autores al libro
        JsonArray autoresArray = new JsonArray();
        for (String autor : autores) {
            autoresArray.add(autor.trim());
        }
        nouLlibre.add("autor", autoresArray);
        
        // Añadir el nuevo libro al array de libros
        llibres.add(nouLlibre);
        
        // Guardar el array de libros actualizado en el archivo JSON
        guardarLlibresJson(llibres);
        
        System.out.println("El libro ha sido añadido correctamente.");
    }
    
    // Función para leer el archivo JSON
    public static JsonArray leerLlibresJson() {
        JsonArray llibres = new JsonArray();
        File archivo = new File("data/llibres.json");

        if (archivo.exists()) {
            try {
                FileReader reader = new FileReader("data/llibres.json");
                Gson gson = new Gson();
                llibres = gson.fromJson(reader, JsonArray.class);
                reader.close();
            } catch (IOException e) {
                System.out.println("Error al leer el archivo JSON.");
            }
        }
        
        return llibres;
    }
    
    // Función para guardar el archivo JSON
    public static void guardarLlibresJson(JsonArray llibres) {
        try {
            FileWriter writer = new FileWriter("data/llibres.json");
            Gson gson = new Gson();
            gson.toJson(llibres, writer);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo JSON.");
        }
    }

    // Función para listar los libros con sus ID
    public static void llistarLlibresID() {
        JsonArray llibres = leerLlibresJson();
        
        if (llibres.size() == 0) {
            System.out.println("No hay libros en la biblioteca.");
        } else {
            System.out.println("Llistant els llibres disponibles:");
            for (int i = 0; i < llibres.size(); i++) {
                JsonObject llibre = llibres.get(i).getAsJsonObject();
                int id = llibre.get("id").getAsInt();
                String titol = llibre.get("titol").getAsString();
                System.out.println("ID: " + id + " - Títol: " + titol);
            }
        }
    }

    // Función para eliminar un libro por ID
    public static void eliminarLlibre() {
        Scanner scanner = new Scanner(System.in);
        
        // Listar los libros
        llistarLlibresID();
        
        // Pedir al usuario el ID del libro a eliminar
        System.out.print("Introduce el ID del libro que quieres eliminar: ");
        int idEliminar = Integer.parseInt(scanner.nextLine().trim());
        
        // Leer el archivo JSON
        JsonArray llibres = leerLlibresJson();
        boolean encontrado = false;
        
        // Buscar y eliminar el libro con el ID dado
        for (int i = 0; i < llibres.size(); i++) {
            JsonObject llibre = llibres.get(i).getAsJsonObject();
            int id = llibre.get("id").getAsInt();
            
            if (id == idEliminar) {
                llibres.remove(i);
                encontrado = true;
                System.out.println("El libro con ID " + idEliminar + " ha sido eliminado.");
                break;
            }
        }
        
        if (!encontrado) {
            System.out.println("No se ha encontrado un libro con ese ID.");
        } else {
            // Guardar el archivo actualizado
            guardarLlibresJson(llibres);
        }
    }

    public static void modificarLlibre() {
        Scanner scanner = new Scanner(System.in);
        JsonArray llibres = leerLlibresJson(); // Asegúrate de tener esta función implementada para leer el JSON.
    
        if (llibres.size() == 0) {
            System.out.println("No hi ha llibres disponibles per modificar.");
            return;
        }
    
        System.out.println("Llistat de llibres:");
        for (int i = 0; i < llibres.size(); i++) {
            JsonObject llibre = llibres.get(i).getAsJsonObject();
            System.out.println("ID: " + llibre.get("id").getAsInt() + " - Títol: " + llibre.get("titol").getAsString());
        }
    
        System.out.print("Introdueix l'ID del llibre a modificar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
    
        JsonObject llibreAModificar = null;
        for (int i = 0; i < llibres.size(); i++) {
            JsonObject llibre = llibres.get(i).getAsJsonObject();
            if (llibre.get("id").getAsInt() == id) {
                llibreAModificar = llibre;
                break;
            }
        }
    
        if (llibreAModificar == null) {
            System.out.println("No s'ha trobat cap llibre amb l'ID indicat.");
            return;
        }
    
        System.out.print("Introdueix el nou títol del llibre (deixa en blanc per mantenir-lo): ");
        String nouTitol = scanner.nextLine();
        if (!nouTitol.isEmpty()) {
            llibreAModificar.addProperty("titol", nouTitol);
        }
    
        System.out.print("Introdueix els nous autors (separats per comes, deixa en blanc per mantenir-los): ");
        String nousAutors = scanner.nextLine();
        if (!nousAutors.isEmpty()) {
            String[] autors = nousAutors.split(",");
            JsonArray arrayAutors = new JsonArray();
            for (String autor : autors) {
                arrayAutors.add(autor.trim());
            }
            llibreAModificar.add("autors", arrayAutors);
        }
    
        guardarLlibresJson(llibres); // Utiliza la función que ya tienes.
        System.out.println("Llibre modificat correctament.");
    }

    public static void llistarLlibresPerAutor() {
    JsonArray llibres = leerLlibresJson();
    List<JsonObject> llibresOrdenats = new ArrayList<>();

    // Añadir todos los libros a la lista para ordenarlos
    for (int i = 0; i < llibres.size(); i++) {
        JsonObject llibre = llibres.get(i).getAsJsonObject();
        llibresOrdenats.add(llibre);
    }

    // Ordenar los libros por el nombre del autor (primer autor en la lista) y luego por título
    llibresOrdenats.sort((o1, o2) -> {
        String autor1 = o1.getAsJsonArray("autor").get(0).getAsString().toLowerCase();
        String autor2 = o2.getAsJsonArray("autor").get(0).getAsString().toLowerCase();
        if (autor1.equals(autor2)) {
            // Si los autores son iguales, ordenar por título
            String titol1 = o1.get("titol").getAsString().toLowerCase();
            String titol2 = o2.get("titol").getAsString().toLowerCase();
            return titol1.compareTo(titol2);
        }
        return autor1.compareTo(autor2);
    });

    // Mostrar los libros ordenados
    if (llibresOrdenats.isEmpty()) {
        System.out.println("No hi ha llibres disponibles.");
    } else {
        System.out.println("Llistant tots els llibres ordenats per autor:");
        for (JsonObject llibre : llibresOrdenats) {
            int id = llibre.get("id").getAsInt();
            String titol = llibre.get("titol").getAsString();
            JsonArray autores = llibre.getAsJsonArray("autor");
            String autoresString = autores.toString();

            System.out.println("ID: " + id + " - Títol: " + titol + " - Autors: " + autoresString);
        }
    }
}

public static void llistarLlibresPerTitol() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Introduce parte o todo el título del libro: ");
    String titolBuscado = scanner.nextLine().trim().toLowerCase();

    JsonArray llibres = leerLlibresJson();
    boolean hayResultados = false;

    System.out.println("Cercant llibres pel títol: " + titolBuscado);
    for (int i = 0; i < llibres.size(); i++) {
        JsonObject llibre = llibres.get(i).getAsJsonObject();
        String titol = llibre.get("titol").getAsString().toLowerCase();

        if (titol.contains(titolBuscado)) {
            hayResultados = true;
            int id = llibre.get("id").getAsInt();
            System.out.println("ID: " + id + " - Títol: " + llibre.get("titol").getAsString());
        }
    }

    if (!hayResultados) {
        System.out.println("No s'han trobat llibres amb aquest títol.");
    }
}

}    
    

