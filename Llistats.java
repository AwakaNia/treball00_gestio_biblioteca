import java.io.*;
import java.nio.file.*;
import java.text.*;
import java.util.*;
import com.google.gson.*;

public class Main {
    static List<Llibre> llibres = new ArrayList<>();
    static List<Usuari> usuaris = new ArrayList<>();
    static List<Prestec> prestecs = new ArrayList<>();
    static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public static void main(String[] args) {
        carregarDades();
        Scanner scanner = new Scanner(System.in);
        int opcio;

        do {
            mostrarMenu();
            opcio = scanner.nextInt();
            scanner.nextLine(); // Consumir línia nova
            switch (opcio) {
                case 1 -> Llistats.llistarLlibres(llibres);
                case 2 -> Llistats.llistarLlibresEnPrestec(prestecs, llibres);
                case 3 -> {
                    System.out.print("Autor: ");
                    String autor = scanner.nextLine();
                    Llistats.llistarLlibresPerAutor(llibres, autor);
                }
                case 4 -> {
                    System.out.print("Paraula al títol: ");
                    String paraula = scanner.nextLine();
                    Llistats.llistarLlibresPerParaula(llibres, paraula);
                }
                case 5 -> Llistats.llistarUsuaris(usuaris);
                case 6 -> Llistats.llistarUsuarisAmbPrestecsActius(prestecs, usuaris);
                case 7 -> Llistats.llistarUsuarisForaDeTermini(prestecs, usuaris);
                case 8 -> Llistats.llistarPrestecs(prestecs);
                case 9 -> {
                    System.out.print("Id de l'usuari: ");
                    int idUsuari = scanner.nextInt();
                    Llistats.llistarPrestecsPerUsuari(prestecs, idUsuari);
                }
                case 10 -> Llistats.llistarPrestecsActius(prestecs);
                case 11 -> Llistats.llistarPrestecsForaDeTermini(prestecs);
                case 0 -> System.out.println("Sortint del programa...");
                default -> System.out.println("Opció no vàlida!");
            }
        } while (opcio != 0);
    }

    static void mostrarMenu() {
        System.out.println("\n--- Menú Biblioteca ---");
        System.out.println("1. Llistat de llibres");
        System.out.println("2. Llistat de llibres en préstec");
        System.out.println("3. Llistat de llibres d'un autor");
        System.out.println("4. Llistat de llibres per paraules al títol");
        System.out.println("5. Llistat d'usuaris");
        System.out.println("6. Llistat d'usuaris amb préstecs actius");
        System.out.println("7. Llistat d'usuaris amb préstecs fora de termini");
        System.out.println("8. Llistat de préstecs");
        System.out.println("9. Llistat de préstecs d'un usuari");
        System.out.println("10. Llistat de préstecs actius");
        System.out.println("11. Llistat de préstecs fora de termini");
        System.out.println("0. Sortir");
        System.out.print("Opció: ");
    }

    static void carregarDades() {
        try {
            Gson gson = new Gson();
            String llibresJson = Files.readString(Paths.get("data/llibres.json"));
            String usuarisJson = Files.readString(Paths.get("data/usuaris.json"));
            String prestecsJson = Files.readString(Paths.get("data/prestecs.json"));

            llibres = gson.fromJson(llibresJson, new TypeToken<List<Llibre>>() {}.getType());
            usuaris = gson.fromJson(usuarisJson, new TypeToken<List<Usuari>>() {}.getType());
            prestecs = gson.fromJson(prestecsJson, new TypeToken<List<Prestec>>() {}.getType());

            System.out.println("Dades carregades correctament.");
        } catch (IOException e) {
            System.out.println("Error carregant les dades: " + e.getMessage());
        }
    }
}

// Llibre.java
class Llibre {
    private int id;
    private String titol;
    private List<String> autor;

    public int getId() { return id; }
    public String getTitol() { return titol; }
    public List<String> getAutors() { return autor; }

    @Override
    public String toString() {
        return "Llibre [id=" + id + ", titol=" + titol + ", autors=" + autor + "]";
    }
}

// Usuari.java
class Usuari {
    private int id;
    private String nom;
    private String cognom;
    private String telefon;

    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getCognom() { return cognom; }
    public String getTelefon() { return telefon; }

    @Override
    public String toString() {
        return "Usuari [id=" + id + ", nom=" + nom + ", cognom=" + cognom + ", telefon=" + telefon + "]";
    }
}

// Prestec.java
class Prestec {
    private int Id;
    private int Id_llibres;
    private int Id_usuari;
    private String Data_de_prestec;
    private String Data_de_devolucio;

    public int getId() { return Id; }
    public int getIdLlibre() { return Id_llibres; }
    public int getIdUsuari() { return Id_usuari; }
    public Date getDataPrestec() throws Exception { return Main.dateFormat.parse(Data_de_prestec); }
    public Date getDataDevolucio() throws Exception { 
        return Data_de_devolucio == null ? null : Main.dateFormat.parse(Data_de_devolucio); 
    }

    @Override
    public String toString() {
        return "Prestec [id=" + Id + ", idLlibre=" + Id_llibres + ", idUsuari=" + Id_usuari + 
               ", dataPrestec=" + Data_de_prestec + ", dataDevolucio=" + Data_de_devolucio + "]";
    }
}