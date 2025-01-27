package com.biblioteca;

import java.util.ArrayList;
import java.util.List;
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
                    funcionsLlibres.afegirLlibre(); // Llama a la función para agregar un libro
                    break;
                case "2":
                case "modificar":
                    funcionsLlibres.modificarLlibre(); // Llama a la función para modificar un libro
                    break;
                case "3":
                case "eliminar":
                    funcionsLlibres.eliminarLlibre(); // Llama a la función para eliminar un libro
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
                    funcionsLlibres.llistarLlibresID();
                    System.out.println("Presiona Enter para continuar...");
                    scanner.nextLine();
                    break;
                case "2":
                    System.out.println("Llistant llibres en préstec...");
                    funcionsPrestecs.llistarPrestecs();
                    System.out.println("Presiona Enter para continuar...");
                    scanner.nextLine();
                    break;
                case "3":
                    System.out.println("Llistant llibres per autor...");
                    funcionsLlibres.llistarLlibresPerAutor();
                    System.out.println("Presiona Enter para continuar...");
                    scanner.nextLine();
                    break;
                case "4":
                    System.out.println("Cercant llibres per títol...");
                    funcionsLlibres.llistarLlibresPerTitol();
                    System.out.println("Presiona Enter para continuar...");
                    scanner.nextLine();
                    break;
                case "0":
                    System.out.println("Tornant al menú de llibres...");
                    break;
                default:
                    System.out.println("Opció no vàlida. Intenta de nou.");
                    System.out.println("Presiona Enter para continuar...");
                    scanner.nextLine();
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
                    funcionsUsuaris.afegirUsuari();
                    break;
                case "2":
                    System.out.println("Modificant usuari...");
                    funcionsUsuaris.modificarUsuari();
                    break;
                case "3":
                    System.out.println("Eliminant usuari...");
                    funcionsUsuaris.eliminarUsuari();
                    break;
                case "4":
                    System.out.println(" ");
                    llistarUsuaris();
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
            System.out.println("2. Usuaris Prestecs Actiu");
            System.out.println("3. Usuaris Prestecs Fora Termini");
            System.out.println("0. Tornar al menú de llibres");

            opcio = scanner.nextLine().trim().toLowerCase();

            switch (opcio) {
                case "1":
                    funcionsUsuaris.llistarUsuarisID();
                    System.out.println("Llistant tots els usuaris...");
                    System.out.println("Presiona Enter para continuar...");
                    scanner.nextLine();
                    break;
                case "2":
                    funcionsUsuaris.llistarUsuariPrestecsActius();
                    System.out.println("Llistant usuaris Prestecs Actiu...");
                    System.out.println("Presiona Enter para continuar...");
                    scanner.nextLine();
                    break;
                case "3":
                    funcionsUsuaris.llistarUsuariPrestecsNoActius();
                    System.out.println("Llistant  usuaris Prestecs Fora Termini...");
                    System.out.println("Presiona Enter para continuar...");
                    scanner.nextLine();
                    break;
                
                case "0":
                    System.out.println("Tornant al menú de llibres...");
                    
                    break;
                default:
                    System.out.println("Opció no vàlida. Intenta de nou.");
                    System.out.println("Presiona Enter para continuar...");
                    scanner.nextLine();
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
                    funcionsPrestecs.afegirPrestec();
                    System.out.println("Afegint préstec...");
                    break;
                case "2":
                    funcionsPrestecs.modificarPrestec();
                    System.out.println("Modificant préstec...");
                    break;
                case "3":
                    funcionsPrestecs.eliminarPrestec();
                    System.out.println("Eliminant préstec...");
                    break;
                case "4":
                    llistarPrestecs();
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
            System.out.println("2. Per usuari");
            System.out.println("3. Per préstecs actius");
            System.out.println("4. Per préstecs fora de termini");
            
            System.out.println("0. Tornar al menú de llibres");

            opcio = scanner.nextLine().trim().toLowerCase();

            switch (opcio) {
                case "1":
                    funcionsPrestecs.mostrarTodosPrestecs();
                    System.out.println("Llistant tots els prestecs...");
                    System.out.println("Presiona Enter para continuar...");
                    scanner.nextLine();
                    break;
                case "2":
                    funcionsPrestecs.mostrarPrestecsPerUsuari();
                    System.out.println("Llistant prestecs per id d'usuari...");
                    System.out.println("Presiona Enter para continuar...");
                    scanner.nextLine();
                    break;
                case "3":
                    funcionsPrestecs.mostrarPrestecsActius();
                    System.out.println("Presiona Enter para continuar...");
                    scanner.nextLine();
                    break;
                case "4":
                    funcionsPrestecs.mostrarPrestecsForaTermini();
                    System.out.println("Presiona Enter para continuar...");
                    scanner.nextLine();
                    break;
                
                case "0":
                    System.out.println("Tornant al menú de llibres...");

                    break;
                default:
                    System.out.println("Opció no vàlida. Intenta de nou.");
                    System.out.println("Presiona Enter para continuar...");
                    scanner.nextLine();
                    break;
            }
        } while (!opcio.equals("0"));
    }
    
}    