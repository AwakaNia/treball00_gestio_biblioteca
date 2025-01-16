package com.biblioteca;

import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        menuPrincipal(); // Inicia el programa desde el menú principal
    }

    // MENÚ PRINCIPAL
    public static void menuPrincipal() {
        Scanner scanner = new Scanner(System.in);
        String opcio;

        do {
            System.out.println("Gestió de biblioteca ");
            System.out.println("1. LLibres");
            System.out.println("2. Usuaris");
            System.out.println("3. Préstecs");
            System.out.println("0. Sortir");
            
            opcio = scanner.nextLine().trim().toLowerCase();

            switch (opcio) {
                case "1":
                case "llibre":
                    menuLlibres(); // Llama al submenú de libros
                    break;
                case "2":
                case "usuaris":
                    menuUsuaris(); // Llama al submenú de usuarios
                    break;
                case "3":
                case "prestecs":
                    menuPrestecs(); // Llama al submenú de préstamos
                    break;
                case "0":
                    System.out.println("Sortint del programa...");
                    break;
                default:
                    System.out.println("Opció no vàlida. Intenta de nou.");
                    break;
            }
        } while (!opcio.equals("0"));

        scanner.close(); // Cierra el scanner al final del programa
    }

    // MENÚ LLIBRES
    public static void menuLlibres() {
        Scanner scanner = new Scanner(System.in);
        String opcio;

        do {
            System.out.println("Gestió de llibres ");
            System.out.println("1. Afegir");
            System.out.println("2. Modificar");
            System.out.println("3. Eliminar");
            System.out.println("4. Llistar");
            System.out.println("0. Tornar al menú principal");

            opcio = scanner.nextLine().trim().toLowerCase();

            switch (opcio) {
                case "1":
                case "afegir":
                    afegirLlibre(); // Llama a la función para agregar un libro
                    break;
                case "2":
                case "modificar":
                    modificarLlibre(); // Llama a la función para modificar un libro
                    break;
                case "3":
                case "eliminar":
                    eliminarLlibre(); // Llama a la función para eliminar un libro
                    break;
                case "4":
                case "llistar":
                    llistarLlibres(); // Llama al submenú de listar libros
                    break;
                case "0":
                    System.out.println("Tornant al menú principal...");
                    break;
                default:
                    System.out.println("Opció no vàlida. Intenta de nou.");
                    break;
            }
        } while (!opcio.equals("0"));
    }

    // SUBMENÚ LLISTAR LLIBRES
    public static void llistarLlibres() {
        Scanner scanner = new Scanner(System.in);
        String opcio;

        do {
            System.out.println("Llistar llibres ");
            System.out.println("1. Tots");
            System.out.println("2. En préstec");
            System.out.println("3. Per autor");
            System.out.println("4. Cercar títol");
            System.out.println("0. Tornar al menú de llibres");

            opcio = scanner.nextLine().trim().toLowerCase();

            switch (opcio) {
                case "1":
                    System.out.println(" ");
                    llistarLlibresID();
                    System.out.println("Presiona Enter para continuar...");
                    scanner.nextLine();
                    break;
                case "2":
                    System.out.println("Llistant llibres en préstec...");
                    break;
                case "3":
                    System.out.println("Llistant llibres per autor...");
                    break;
                case "4":
                    System.out.println("Cercant llibres per títol...");
                    break;
                case "0":
                    System.out.println("Tornant al menú de llibres...");
                    break;
                default:
                    System.out.println("Opció no vàlida. Intenta de nou.");
                    break;
            }
        } while (!opcio.equals("0"));
    }

    // MENÚ USUARIS
    public static void menuUsuaris() {
        Scanner scanner = new Scanner(System.in);
        String opcio;

        do {
            System.out.println("Gestió d'usuaris ");
            System.out.println("1. Afegir");
            System.out.println("2. Modificar");
            System.out.println("3. Eliminar");
            System.out.println("4. Llistar");
            System.out.println("0. Tornar al menú principal");

            opcio = scanner.nextLine().trim().toLowerCase();

            switch (opcio) {
                case "1":
                    System.out.println("Afegint usuari...");
                    break;
                case "2":
                    System.out.println("Modificant usuari...");
                    break;
                case "3":
                    System.out.println("Eliminant usuari...");
                    break;
                case "4":
                    System.out.println("Llistant usuaris...");
                    break;
                case "0":
                    System.out.println("Tornant al menú principal...");
                    break;
                default:
                    System.out.println("Opció no vàlida. Intenta de nou.");
                    break;
            }
        } while (!opcio.equals("0"));
    }

    // SUBMENÚ LLISTAR USUARIS
    public static void llistarUsuaris() {
        Scanner scanner = new Scanner(System.in);
        String opcio;

        do {
            System.out.println("Llistar usuaris ");
            System.out.println("1. Tots");
            System.out.println("2. Per id");
            System.out.println("3. Per nom");
            System.out.println("4. Per cognom");
            System.out.println("4. Per telefon");
            System.out.println("0. Tornar al menú de llibres");

            opcio = scanner.nextLine().trim().toLowerCase();

            switch (opcio) {
                case "1":
                    System.out.println("Llistant tots els usuaris...");
                    break;
                case "2":
                    System.out.println("Llistant usuaris per id...");
                    break;
                case "3":
                    System.out.println("Llistant  usuaris per nom...");
                    break;
                case "4":
                    System.out.println("Llistant  usuaris per cognom...");
                    break;
                case "5":
                System.out.println("Llistant usuaris per telefon...");
                break;
                case "0":
                    System.out.println("Tornant al menú de llibres...");
                    break;
                default:
                    System.out.println("Opció no vàlida. Intenta de nou.");
                    break;
            }
        } while (!opcio.equals("0"));
    }
    
    // MENÚ PRÉSTECS
    public static void menuPrestecs() {
        Scanner scanner = new Scanner(System.in);
        String opcio;

        do {
            System.out.println("Gestió de préstecs ");
            System.out.println("1. Afegir");
            System.out.println("2. Modificar");
            System.out.println("3. Eliminar");
            System.out.println("4. Llistar");
            System.out.println("0. Tornar al menú principal");

            opcio = scanner.nextLine().trim().toLowerCase();

            switch (opcio) {
                case "1":
                    System.out.println("Afegint préstec...");
                    break;
                case "2":
                    System.out.println("Modificant préstec...");
                    break;
                case "3":
                    System.out.println("Eliminant préstec...");
                    break;
                case "4":
                    System.out.println("Llistant préstecs...");
                    break;
                case "0":
                    System.out.println("Tornant al menú principal...");
                    break;
                default:
                    System.out.println("Opció no vàlida. Intenta de nou.");
                    break;
            }
        } while (!opcio.equals("0"));
    }
   
    // SUBMENÚ LLISTAR USUARIS
    public static void llistarPrestecs() {
        Scanner scanner = new Scanner(System.in);
        String opcio;

        do {
            System.out.println("Llistar prestecs ");
            System.out.println("1. Tots");
            System.out.println("2. Per id");
            System.out.println("3. Per id llibres");
            System.out.println("4. Per id usuaris");
            System.out.println("4. Per data prestec");
            System.out.println("4. Per data devolucio");
            System.out.println("0. Tornar al menú de llibres");

            opcio = scanner.nextLine().trim().toLowerCase();

            switch (opcio) {
                case "1":
                    System.out.println("Llistant tots els prestecs...");
                    break;
                case "2":
                    System.out.println("Llistant prestecs per id...");
                    break;
                case "3":
                    System.out.println("Llistant  prestecs per id de llibre...");
                    break;
                case "4":
                    System.out.println("Llistant prestecs per id usuari...");
                    break;
                case "5":
                    System.out.println("Llistant prestecs per data prestec...");
                    break;
                case "6":
                    System.out.println("Llistant prestecs per data devolucio...");
                    break;
                case "0":
                    System.out.println("Tornant al menú de llibres...");
                    break;
                default:
                    System.out.println("Opció no vàlida. Intenta de nou.");
                    break;
            }
        } while (!opcio.equals("0"));
    }
    // FUNCIONES 
    public static void afegirUsuari() {
        System.out.println("Afegint ...");
        
    }
    public static void afegirPrestec() {
        System.out.println("Afegint ...");
        
    }

    public static void modificarUsuari() {
        System.out.println("Modificant ...");
        
    }
    public static void modificarPrestec() {
        System.out.println("Modificant ...");
        
    }

    public static void eliminarUsuari() {
        System.out.println("Eliminant llibre...");
        
    }
    public static void eliminarPrestec() {
        System.out.println("Eliminant llibre...");
        
    }


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
}    