package com.biblioteca;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.time.format.DateTimeFormatter;


public class funcionsPrestecs {
    public static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    // Función para añadir un préstamo

    public static void afegirPrestec() {
        Scanner scanner = new Scanner(System.in);

        // Solicitar datos del préstamo
        System.out.print("Introduce el ID del libro: ");
        int idLlibre = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Introduce el ID del usuario: ");
        int idUsuari = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Introduce la fecha de préstamo (dd-MM-yyyy): ");
        String dataPrestec = scanner.nextLine().trim();

        System.out.print("Introduce la fecha de devolución (dd-MM-yyyy): ");
        String dataDevolucio = scanner.nextLine().trim();

        // Leer el archivo JSON existente y cargarlo en una estructura de datos
        JsonArray prestecs = leerPrestecsJson();

        // Obtener el próximo ID disponible
        int nextId = prestecs.size() + 1;

        // Crear el objeto JSON para el nuevo préstamo
        JsonObject nouPrestec = new JsonObject();
        nouPrestec.addProperty("Id", nextId);
        nouPrestec.addProperty("Id_llibres", idLlibre);
        nouPrestec.addProperty("Id d'usuari", idUsuari);
        nouPrestec.addProperty("Data de prestec", dataPrestec);
        nouPrestec.addProperty("Data de devolucio", dataDevolucio);

        // Añadir el nuevo préstamo al array
        prestecs.add(nouPrestec);

        // Guardar el array actualizado en el archivo JSON
        guardarPrestecsJson(prestecs);

        System.out.println("El préstec ha sido añadido correctamente.");
    }

    // Función para leer el archivo JSON de préstamos
    public static JsonArray leerPrestecsJson() {
        JsonArray prestecs = new JsonArray();
        File archivo = new File("data/prestecs.json");

        if (archivo.exists()) {
            try {
                FileReader reader = new FileReader("data/prestecs.json");
                Gson gson = new Gson();
                prestecs = gson.fromJson(reader, JsonArray.class);
                reader.close();
            } catch (IOException e) {
                System.out.println("Error al leer el archivo JSON.");
            }
        }

        return prestecs;
    }

    // Función para guardar el archivo JSON de préstamos
    public static void guardarPrestecsJson(JsonArray prestecs) {
        try {
            FileWriter writer = new FileWriter("data/prestecs.json");
            Gson gson = new Gson();
            gson.toJson(prestecs, writer);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo JSON.");
        }
    }

    // Función para listar todos los préstamos
    public static void llistarPrestecs() {
        JsonArray prestecs = leerPrestecsJson();

        if (prestecs.size() == 0) {
            System.out.println("No hay préstamos registrados.");
        } else {
            System.out.println("Llistant tots els préstecs:");
            for (int i = 0; i < prestecs.size(); i++) {
                JsonObject prestec = prestecs.get(i).getAsJsonObject();
                int id = prestec.get("Id").getAsInt();
                int idLlibres = prestec.get("Id_llibres").getAsInt();
                int idUsuari = prestec.get("Id d'usuari").getAsInt();
                String dataPrestec = prestec.get("Data de prestec").getAsString();
                String dataDevolucio = prestec.get("Data de devolucio").getAsString();

                System.out.println("ID: " + id + " - ID Llibres: " + idLlibres + " - ID Usuari: " + idUsuari + " - Data de Préstec: " + dataPrestec + " - Data de Devolució: " + dataDevolucio);
            }
        }
    }

    // Función para eliminar un préstamo por ID
    public static void eliminarPrestec() {
        Scanner scanner = new Scanner(System.in);

        // Listar los préstamos
        llistarPrestecs();

        // Solicitar el ID del préstamo a eliminar
        System.out.print("Introduce el ID del préstec que quieres eliminar: ");
        int idEliminar = Integer.parseInt(scanner.nextLine().trim());

        // Leer el archivo JSON
        JsonArray prestecs = leerPrestecsJson();
        boolean encontrado = false;

        // Buscar y eliminar el préstamo con el ID dado
        for (int i = 0; i < prestecs.size(); i++) {
            JsonObject prestec = prestecs.get(i).getAsJsonObject();
            int id = prestec.get("Id").getAsInt();

            if (id == idEliminar) {
                prestecs.remove(i);
                encontrado = true;
                System.out.println("El préstec con ID " + idEliminar + " ha sido eliminado.");
                break;
            }
        }

        if (!encontrado) {
            System.out.println("No se ha encontrado un préstec con ese ID.");
        } else {
            // Guardar el archivo actualizado
            guardarPrestecsJson(prestecs);
        }
    }

    public static void modificarPrestec() {
        Scanner scanner = new Scanner(System.in);
        JsonArray prestecs = leerPrestecsJson();
    
        if (prestecs.size() == 0) {
            System.out.println("No hi ha préstecs disponibles per modificar.");
            return;
        }
    
        // Listar los préstamos
        llistarPrestecs();
    
        System.out.print("Introdueix l'ID del préstec a modificar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
    
        JsonObject prestecAModificar = null;
        for (int i = 0; i < prestecs.size(); i++) {
            JsonObject prestec = prestecs.get(i).getAsJsonObject();
            if (prestec.get("Id").getAsInt() == id) {
                prestecAModificar = prestec;
                break;
            }
        }
    
        if (prestecAModificar == null) {
            System.out.println("No s'ha trobat cap préstec amb l'ID indicat.");
            return;
        }
    
        // Modificar el ID del libro
        System.out.print("Introdueix el nou ID del llibre (deixa en blanc per mantenir-lo): ");
        String nouIdLlibre = scanner.nextLine();
        if (!nouIdLlibre.isEmpty()) {
            prestecAModificar.addProperty("Id_llibres", Integer.parseInt(nouIdLlibre));
        }
    
        // Modificar el ID del usuario
        System.out.print("Introdueix el nou ID de l'usuari (deixa en blanc per mantenir-lo): ");
        String nouIdUsuari = scanner.nextLine();
        if (!nouIdUsuari.isEmpty()) {
            prestecAModificar.addProperty("Id d'usuari", Integer.parseInt(nouIdUsuari));
        }
    
        // Modificar la fecha de préstamo
        System.out.print("Introdueix la nova data de préstec (dd-MM-yyyy, deixa en blanc per mantenir-la): ");
        String novaDataPrestec = scanner.nextLine();
        if (!novaDataPrestec.isEmpty()) {
            prestecAModificar.addProperty("Data de prestec", novaDataPrestec);
        }
    
        // Modificar la fecha de devolución
        System.out.print("Introdueix la nova data de devolució (dd-MM-yyyy, deixa en blanc per mantenir-la): ");
        String novaDataDevolucio = scanner.nextLine();
        if (!novaDataDevolucio.isEmpty()) {
            prestecAModificar.addProperty("Data de devolucio", novaDataDevolucio);
        }
    
        guardarPrestecsJson(prestecs);
        System.out.println("Préstec modificat correctament.");
    }


    //--------------------
    public static void llistarPrestecsPerId() {
        JsonArray prestecs = leerPrestecsJson();
        List<JsonObject> prestecsOrdenats = new ArrayList<>();
    
        // Añadir todos los préstecs a la lista para ordenarlos
        for (int i = 0; i < prestecs.size(); i++) {
            JsonObject prestec = prestecs.get(i).getAsJsonObject();
            prestecsOrdenats.add(prestec);
        }
    
        // Ordenar los préstecs por ID
        prestecsOrdenats.sort((o1, o2) -> {
            int id1 = o1.get("Id").getAsInt();
            int id2 = o2.get("Id").getAsInt();
            return Integer.compare(id1, id2);
        });
    
        // Mostrar los préstecs ordenados
        if (prestecsOrdenats.isEmpty()) {
            System.out.println("No hi ha préstecs disponibles.");
        } else {
            System.out.println("Llistant tots els préstecs ordenats per ID:");
            for (JsonObject prestec : prestecsOrdenats) {
                int id = prestec.get("Id").getAsInt();
                int idLlibres = prestec.get("Id_llibres").getAsInt();
                int idUsuari = prestec.get("Id d'usuari").getAsInt();
                String dataPrestec = prestec.get("Data de prestec").getAsString();
                String dataDevolucio = prestec.get("Data de devolucio").getAsString();
    
                System.out.println("ID: " + id + " - ID Llibre: " + idLlibres + " - ID Usuari: " + idUsuari
                        + " - Data de préstec: " + dataPrestec + " - Data de devolució: " + dataDevolucio);
            }
        }
    }
    
    // Método para listar préstamos por ID de usuario
    public static void llistarPrestecsPerIdUsuari() {
        JsonArray prestecs = leerPrestecsJson();
        System.out.print("Introdueix l'ID d'usuari: ");
        Scanner scanner = new Scanner(System.in);
        int idUsuari = scanner.nextInt();

        boolean trobat = false;
        System.out.println("Préstecs de l'usuari amb ID " + idUsuari + ":");
        for (int i = 0; i < prestecs.size(); i++) {
            JsonObject prestec = prestecs.get(i).getAsJsonObject();
            if (prestec.get("Id d'usuari").getAsInt() == idUsuari) {
                mostrarPrestec(prestec);
                trobat = true;
            }
        }

        if (!trobat) {
            System.out.println("No s'ha trobat cap préstec per a l'usuari amb ID " + idUsuari);
        }
    }


    //submenu
    public static void llistarPrestecsOpcion() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Selecciona com vols llistar els préstecs:");
        System.out.println("1. Per ID d'usuari");
        System.out.println("2. Per nom d'usuari");
        System.out.print("Opció: ");
        int opcio = scanner.nextInt();
        scanner.nextLine(); // Consumir salto de línea
    
        switch (opcio) {
            case 1:
                llistarPrestecsPerIdUsuari();
                break;
            case 2:
              //  llistarPrestecsPerNomUsuari();
                break;
            default:
                System.out.println("Opció no vàlida. Torna a intentar-ho.");
        }
    }

    // Método para listar préstamos por nombre de usuario


        // Mostrar todos los préstamos
    public static void mostrarTodosPrestecs() {
        JsonArray prestecs = leerPrestecsJson();
        if (prestecs.size() == 0) {
            System.out.println("No hi ha préstecs registrats.");
        } else {
            System.out.println("Tots els préstecs:");
            for (int i = 0; i < prestecs.size(); i++) {
                mostrarPrestec(prestecs.get(i).getAsJsonObject());
            }
        }
    }

    // Mostrar préstamos por usuario
    public static void mostrarPrestecsPerUsuari() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introdueix l'ID de l'usuari: ");
        int idUsuari = scanner.nextInt();

        JsonArray prestecs = leerPrestecsJson();
        boolean trobat = false;

        System.out.println("Préstecs de l'usuari amb ID " + idUsuari + ":");
        for (int i = 0; i < prestecs.size(); i++) {
            JsonObject prestec = prestecs.get(i).getAsJsonObject();
            if (prestec.get("Id d'usuari").getAsInt() == idUsuari) {
                mostrarPrestec(prestec);
                trobat = true;
            }
        }
        if (!trobat) {
            System.out.println("No s'ha trobat cap préstec per a l'usuari amb ID " + idUsuari);
        }
    }

    // Mostrar préstamos activos
    public static void mostrarPrestecsActius() {
        JsonArray prestecs = leerPrestecsJson();
        LocalDate avui = LocalDate.now();
        boolean trobat = false;

        System.out.println("Préstecs actius:");
        for (int i = 0; i < prestecs.size(); i++) {
            JsonObject prestec = prestecs.get(i).getAsJsonObject();
            LocalDate dataDevolucio = LocalDate.parse(prestec.get("Data de devolucio").getAsString(), DATE_FORMAT);
            if (!dataDevolucio.isBefore(avui)) {
                mostrarPrestec(prestec);
                trobat = true;
            }
        }
        if (!trobat) {
            System.out.println("No hi ha préstecs actius.");
        }
    }

    // Mostrar préstamos fuera del término (vencidos)
    public static void mostrarPrestecsForaTermini() {
        JsonArray prestecs = leerPrestecsJson();
        LocalDate avui = LocalDate.now();
        boolean trobat = false;

        System.out.println("Préstecs fora del termini:");
        for (int i = 0; i < prestecs.size(); i++) {
            JsonObject prestec = prestecs.get(i).getAsJsonObject();
            LocalDate dataDevolucio = LocalDate.parse(prestec.get("Data de devolucio").getAsString(), DATE_FORMAT);
            if (dataDevolucio.isBefore(avui)) {
                mostrarPrestec(prestec);
                trobat = true;
            }
        }
        if (!trobat) {
            System.out.println("No hi ha préstecs fora del termini.");
        }
    }

    // Método auxiliar para mostrar un préstamo
    public static void mostrarPrestec(JsonObject prestec) {
        int id = prestec.get("Id").getAsInt();
        int idLlibres = prestec.get("Id_llibres").getAsInt();
        int idUsuari = prestec.get("Id d'usuari").getAsInt();
        String dataPrestec = prestec.get("Data de prestec").getAsString();
        String dataDevolucio = prestec.get("Data de devolucio").getAsString();

        System.out.println("ID: " + id + " - ID Llibre: " + idLlibres + " - ID Usuari: " + idUsuari
                + " - Data de préstec: " + dataPrestec + " - Data de devolució: " + dataDevolucio);
    }
}

