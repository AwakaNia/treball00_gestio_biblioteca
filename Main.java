import java.util.Scanner;


public class Main {
    

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String opcio;

        /*MENU PRINCIPAL */
        do {
            System.out.println("Gestió de biblioteca ");
            System.out.println("1. LLibres");
            System.out.println("2. Usuaris");
            System.out.println("3. Préstecs");
            System.out.println("0. Sortir");
            opcio = scanner.nextLine();
            opcio= opcio.trim().toLowerCase();
            switch (opcio) {
                /*MENU LLIBRES */
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
                            System.out.println("2. En prestec");
                            System.out.println("3. Per autor");
                            System.out.println("4. Cercar titol");
                            System.out.println("0. Tornar al menú de llibres");
                            opcio = scanner.nextLine();
                            opcio= opcio.trim().toLowerCase();
                            switch (opcio) {
                                case "1":
                                case "Tots":
                                    
                                    break;
                                case "2":
                                case "en prestec":
                                    
                                    break;
                                case "3":
                                case "per autor":
                                    
                                    break;
                                case "4":
                                case "per títol":
                                        break;
                                
                                    
                            }
                            break;
                    
                        default:
                            System.out.println("Opció no vàlida. Intenta de nou.");
                            break;
                    }
                } while (!opcio.equals("0")|| (opcio.equals("sortir")));

                    break;
                /*MENU  USUARIS*/
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
                            System.out.println("Llistar usuaris");
                            System.out.println("1. Tots");
                            System.out.println("2. Per id");
                            System.out.println("3. Per nom");
                            System.out.println("4. Per cognom");
                            System.out.println("5. Per telefon");
                            System.out.println("0. Tornar al menú d' usuaris");
                            opcio = scanner.nextLine();
                            opcio= opcio.trim().toLowerCase();
                            switch (opcio) {
                                case "1":
                                case "Tots":
                                    
                                    break;
                                case "2":
                                case "per id":
                                    
                                    break;
                                case "3":
                                case "per nom":
                                    
                                    break;
                                case "4":
                                case "per cognom":
                                        break;
                                case "5":
                                case "per telefon":
                                        break;
                                    
                            }
                            break;
                    
                        default:
                            System.out.println("Opció no vàlida. Intenta de nou.");
                            break;
                    }
                } while (!opcio.equals("0")|| (opcio.equals("sortir")));
                    break;
                /*MENU PRESTECS */
                case ("3"):
                case ("prestecs"):
                do {
                    System.out.println("Gestió de prestecs ");
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
                            System.out.println("Llistar prestecs");
                            System.out.println("1. Tots");
                            System.out.println("2. Per id");
                            System.out.println("3. Per id  llibre");
                            System.out.println("4. Per id usuari");
                            System.out.println("5. Per data prestec");
                            System.out.println("6. Per data devolucio");
                            System.out.println("0. Tornar al menú de prestec");
                            opcio = scanner.nextLine();
                            opcio= opcio.trim().toLowerCase();
                            switch (opcio) {
                                case "1":
                                case "Tots":
                                    
                                    break;
                                case "2":
                                case "per id":
                                    
                                    break;
                                case "3":
                                case "per id de llibre":
                                    
                                    break;
                                case "4":
                                case "per id usuari":
                                        break;
                                case "5":
                                case "per data prestec":
                                        break;
                                case "6":
                                case "data devolucio":
                                        break;
                                case "0": 
                                System.out.println("Tornant al menú de llibres...");
                                break;
                            }break;
                    
                        default:
                         System.out.println("Opció no vàlida. Intenta de nou.");
                            break;
                    }
                } while (!opcio.equals("0")|| (opcio.equals("sortir")));
                    
                    break;
        
                default:
                    System.out.println("Opció no vàlida. Intenta de nou.");
                    break;
            }
            

        } while (opcio=="0" || opcio=="sortir");
        scanner.close();
    }
}