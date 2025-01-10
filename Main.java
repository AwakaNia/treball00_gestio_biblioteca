import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Ruta del archivo JSON
        String filePath = "./llibres.json";

        // Leer el contenido del archivo JSON
        String content = readFile(filePath);

        // Convertir el contenido a un JSONArray
        JSONArray jsonArray = new JSONArray(content);

        // Crear un escáner para pedir datos al usuario
        Scanner scanner = new Scanner(System.in);

        // Pedir el nombre del libro
        System.out.print("Introduce el título del libro: ");
        String titol = scanner.nextLine();

        // Pedir el autor del libro
        System.out.print("Introduce el autor del libro: ");
        String autor = scanner.nextLine();

        // Crear un nuevo objeto JSON para el nuevo libro
        JSONObject nuevoLibro = new JSONObject();
        nuevoLibro.put("id", jsonArray.length() + 1);  // Asignar un ID único (siguiente número)
        nuevoLibro.put("titol", titol);  // Asignar el título
        nuevoLibro.put("autor", new JSONArray().put(autor));  // Asignar el autor

        // Añadir el nuevo libro al JSONArray
        jsonArray.put(nuevoLibro);

        // Escribir el JSONArray actualizado en el archivo
        writeFile(filePath, jsonArray);

        System.out.println("¡Libro añadido exitosamente!");
    }

    // Método para leer un archivo y devolver su contenido como String
    private static String readFile(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            System.out.println("Error leyendo el archivo: " + e.getMessage());
            return "[]";  // Si el archivo no existe o hay error, retornar un JSON vacío
        }
    }

    // Método para escribir el contenido en el archivo
    private static void writeFile(String filePath, JSONArray jsonArray) {
        try {
            Files.write(Paths.get(filePath), jsonArray.toString(4).getBytes());  // Escribe con indentación de 4 espacios
        } catch (IOException e) {
            System.out.println("Error escribiendo en el archivo: " + e.getMessage());
        }
    }
}

