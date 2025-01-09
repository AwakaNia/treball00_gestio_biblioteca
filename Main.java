import java.util.Scanner;


public class Main {
    

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String opcio;


        do {
            System.out.println("Gestió de biblioteca ");
            System.out.println("1. LLibres");
            System.out.println("2. Usuaris");
            System.out.println("3. Préstecs");
            System.out.println("0. Sortir");
            opcio = scanner.nextLine();
            opcio= opcio.trim().toLowerCase();
            switch (opcio) {
                case ("1"):
                case ("llibre"):
                do {
                    System.out.println("Gestió de llibres ");
                    System.out.println("1. Afegir");
                    System.out.println("2. Modificar");
                    System.out.println("3. Eliminar");
                    System.out.println("4. Llistar");
                    System.out.println("0. Sortir");
                    opcio = scanner.nextLine();
                    opcio= opcio.trim().toLowerCase();
                    switch (opcio) {
                        case "1":
                        case "afegir":
                            
                            break;
                        case "2":
                        case "modificar":
                            
                            break;
                        case "3":
                        case "eliminar":
                            
                            break;
                        case "4":
                        case "llistar":
                            System.out.println("Llistar llibres");
                            System.out.println("1. Tots");
                            System.out.println("2. En préstec");
                            System.out.println("3. Per autor");
                            System.out.println("4. Cercar títol");
                            System.out.println("0. Tornar al menú de llibres");
                            opcio = scanner.nextLine();
                            opcio= opcio.trim().toLowerCase();
                        
                            break;
                    
                        default:
                            break;
                    }
                } while (opcio=="0"|| opcio=="sortir");
                    break;
                case ("2"):
                case ("usuaris"):
                do {
                    System.out.println("Gestió d'usuaris ");
                    System.out.println("1. Afegir");
                    System.out.println("2. Modificar");
                    System.out.println("3. Eliminar");
                    System.out.println("4. Llistar");
                    System.out.println("0. Sortir");
                    opcio = scanner.nextLine();
                    opcio= opcio.trim().toLowerCase();
                    
                } while (opcio=="0"|| opcio=="sortir");
                    break;
                case ("3"):
                case ("prestecs"):
                do {
                    System.out.println("Gestió d'usuaris ");
                    System.out.println("1. Afegir");
                    System.out.println("2. Modificar");
                    System.out.println("3. Eliminar");
                    System.out.println("4. Llistar");
                    System.out.println("0. Sortir");
                    opcio = scanner.nextLine();
                    opcio= opcio.trim().toLowerCase();
                    
                } while (opcio=="0"|| opcio=="sortir");
                    break;
        
                default:
                    break;
            }
            

        } while (opcio=="0" || opcio=="sortir");
        scanner.close();
    }
}