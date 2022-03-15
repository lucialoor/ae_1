package request_one;


import request_one.entity.Almacen;
import request_one.entity.Coche;
import java.util.Scanner;

public class Server {

    // Ruta fichero
    public static final String NOMBRE_FICHERO = "coches.dat";

    public static void main(String[] args) {
        System.out.println("-----------------------------------");
        System.out.println("-----------------------------------");
        System.out.println("        ALMACEN TARTANACAR      ");
        System.out.println("-----------------------------------");

        // Bloque try, abre el escaner y lo cierra al terminar el bloque.
        try (Scanner sc = new Scanner(System.in)){

            System.out.println("BUSCANDO FICHERO DE DATOS");

            Almacen alm = new Almacen(NOMBRE_FICHERO);
            alm.initFile();



            //--------------------- MENU --------------------------//

            // boolean para controlar la salida del do while.
            boolean flag = true;

            do {
                // Solicita al usuario que elija una opcion por consola.
                System.out.println(
                        "-----------------------------------\n"
                        + "Tiene que elegir una de estas opciones:"
                        + "\n 1-. Anadir nuevo coche"
                        + "\n 2-. Borrar coche por id"
                        + "\n 3-. Consulta coche por id"
                        + "\n 4-. Listado de coches"
                        + "\n 5-. Exportar coches a archivo de texto"
                        + "\n 6-. Salir de la aplicacion"
                        + "\n-----------------------------------");

                // Lee la respuesta del usuario por consola.
                String answer = sc.nextLine();

                // Bucle que solicita un numero hasta que el valor introducido sea 1, 2, 3, 4 , 5 o 6.
                while (!answer.equals("1") && !answer.equals("2") && !answer.equals("3") && !answer.equals("4")
                        && !answer.equals("5") && !answer.equals("6")) {

                    System.out.println("Tiene que escoger una de las 6 opciones");

                    // Lee la respuesta del usuario por consola.
                    answer = sc.nextLine();
                }

                // Respuesta del programa segun la eleccion del usuario.
                switch (answer) {

                    case "1":  // Ingresar nuevo coche

                        // Informa de la eleccion del cliente
                        System.out.println("Ha seleccionado dar de alta un vehiculo");
                        System.out.println("---------------------------------------");

                        // Creamos un nuevo objeto de la clase Coche sin argumentos
                        Coche c1 = new Coche();

                        // Solicita por consola informacion del vehiculo.
                        System.out.println("Introduce la matricula del vehiculo");
                        Boolean continuar = true;
                        do {
                        	String matricula = sc.nextLine();
                        	if(alm.getByMatricula(matricula)) {
                        		c1.setMatricula(matricula);
                        		continuar = false;
                        	}else {
                        		System.out.println("Vuelve a introducir la matricula");
                        	}	
                        }while(continuar);
                        System.out.println("Introduce la marca del vehiculo");
                        c1.setMarca(sc.nextLine());
                        System.out.println("Introduce el modelo del vehiculo");
                        c1.setModelo(sc.nextLine());
                        System.out.println("Introduce el color del vehiculo");
                        c1.setColor(sc.nextLine());
                        Coche.setContadorId(Coche.getContadorId() + 1);
                        c1.setId(Coche.getContadorId());

                        alm.addItem(c1);

                        System.out.println("VEHICULO DADO DE ALTA --> " + c1); 
                        // Salida del Switch.
                        break;

                    case "2": // Borrar un coche por Id
                    	
                        // Solicita por consola el ID del coche
                        System.out.println("Introduce el ID del vehiculo que deseas borrar: ");

                        // Lee el ID introducido por consola.
                        try {
                          alm.delByID(Integer.parseInt(sc.nextLine()));	
                        } catch (NumberFormatException e) {
                			System.out.println("ERROR de formato, introduzca un numero");
                		}
                        

                        // Salida del Switch.
                        break;

                    case "3": //Consultar coche por Id
                    	
                        // Solicita por consola el ID del coche
                        System.out.println("Introduce el ID del vehiculo que deseas encontrar: ");

                        // Lee el ID introducido por consola.
                        try {
                           alm.getById(Integer.parseInt(sc.nextLine()));
                        } catch (NumberFormatException e) {
                			System.out.println("ERROR de formato, dato no válido");
                		}
                    

                        // Salida del Switch.
                        break;


                    case "4":  // Listar todos los coches
                    	
                        // Devuelve la lista de coches.
                    	if (alm.getStock().isEmpty()) {
                    		System.out.println("No hay vehiculos en el almacen");
                    	}else {
                    		System.out.println(alm.getStock().toString());
                    	}
                       

                        // Salida del Switch.
                        break;

                    case "5": // Exportar coches a archivo de texto
                    	
                        alm.exportData();

                        // Salida del Switch.
                        break;
                      
                    case "6": // Salir del programa y guardar datos en coches.dat
                    	
                    	// Cambia el boolean para salir del do while.
                        flag = false;
                        alm.updateFile();
                    	
                    	break;
                }

                        
            }while(flag); // Fin del do while, comprueba si el boolean es true.

            // Captura las posibles excepciones.
        } catch (Exception e) {
            System.err.println("CLIENTE: Error -> " + e);
            e.printStackTrace();
        }

    }

}
