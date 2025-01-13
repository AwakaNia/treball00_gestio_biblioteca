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
                    System.out.println("Llistant tots els llibres...");
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
    // FUNCIONES PARA CADA ACCIÓN
    public static void afegirLlibre() {
        System.out.println("Afegint llibre...");
        // Aquí puedes agregar la lógica para añadir un libro
    }


    public static void modificarLlibre() {
        System.out.println("Modificant llibre...");
        // Aquí puedes agregar la lógica para modificar un libro
    }

    public static void eliminarLlibre() {
        System.out.println("Eliminant llibre...");
        // Aquí puedes agregar la lógica para eliminar un libro
    }
}
