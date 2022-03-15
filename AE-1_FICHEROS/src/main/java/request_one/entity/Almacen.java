package request_one.entity;

import java.io.*;
import java.util.ArrayList;

public class Almacen implements Serializable {

	private static final long serialVersionUID = -1856120338726783342L;
	private static String NOMBRE_FICHERO;
	private static String FICHERO_EXPORTAR = "datos_exportados.txt";

	// Almacena los coches en una lista.
	private ArrayList <Coche> stock;

	// Constructor
	public Almacen (String NOMBRE_FICHERO) {
		super();
		Almacen.NOMBRE_FICHERO = NOMBRE_FICHERO;
		// Crea una lista al inicializarse
		this.setStock(new ArrayList<Coche>());
	}

	// Getters y setters
	public static String getNombreFichero() {
		return NOMBRE_FICHERO;
	}

	public ArrayList<Coche> getStock() {
		return stock;
	}

	public void setStock(ArrayList<Coche> stock) {
		this.stock = stock;
	}
	
	// Metodos sobreescritos

	@Override
	public String toString() {
		return "Almacen{" +
				"stock=" + stock +
				'}';
	}


	// Metodos
	
	/**
	* Añade un coche al almacen
	*
	* @param  coche
	**/
	public void addItem(Coche coche) {
		stock.add(coche);
	}



	/**
	* Busca en la lista un coche por el id
	*
	* @param id del coche 
	**/
	public void getById(int id) {
		try {
			Coche c = null;
			for(Coche n: stock) {
				if (id == n.getId()) {
					c = n;
					break;
				} 
			} 
			if(c != null) {
				System.out.println(c);
			}else {
				System.out.println("Vehiculo no encontrado");
			}
			
		} catch (IndexOutOfBoundsException e) {
			System.out.println("ERROR" + e);
		}
	}
	
	
	/**
	 * Busca en la lista un coche por matricula
	 * 
	 * @param matricula
	 * @return false si existe un vehiculo con esa matricula o true si no existe.
	 */
	public Boolean getByMatricula(String matricula) {
		try {
			Coche c = null;
			for(Coche n: stock) {
				if (matricula.equalsIgnoreCase(n.getMatricula())) {
					c = n;
					break;
				} 
			} 
			if(c != null) {
				System.out.println("Ya existe un vehiculo con esta matricula");
				return false;
			}else {
				return true;
			}
			
		} catch (IndexOutOfBoundsException e) {
			System.out.println( "ERROR" + e);
			return false;
		}
	}
	
	/**
	* Busca si hay un coche con el Id pasado por parametro y si existe lo elimina de la lista
	*
	* @param id del vehiculo
	**/
	public void delByID(int id) {
		 Coche c = null;
	        for(Coche n : stock){
	            if(n.getId() == id){
	                c = n;
	            }
	        }
	        if(c != null){
	        	System.out.println("Vehiculo borrado");
	            stock.remove(c);
	        }else {
	        	System.out.println("No existe el vehiculo indicado");
	        }
	}

	/**
	* Leemos el fichero y añadimos los coches a nuestro stock.
	*
	* 
	**/
	public void initFile() {

		try (FileInputStream file = new FileInputStream(NOMBRE_FICHERO);
			 ObjectInputStream buffer = new ObjectInputStream(file);){
			boolean eof = false;
			Coche c;
			int lastId = 0;
			while (!eof) {
				try {
					c = (Coche) buffer.readObject();
					if (c.getId() > lastId){lastId = c.getId();}
					stock.add(c);
				} catch (EOFException e1) {
					eof = true;
				} catch (IOException e2) {
					System.out.println("Error al leer los coches del almacen" + e2.getMessage());
				} catch (ClassNotFoundException e3) {
					System.out.println("La clase Coche no esta cargada en memoria" + e3.getMessage());
				}
			}
			Coche.setContadorId(lastId);
			System.out.println("ARCHIVO ENCONTRADO, LISTA DE COCHES ACTIVA");
		} catch (IOException e) {
			System.out.println("No existe un fichero de datos");
		}

	}
	
	/**
	* Abrimos fichero en modo false para que se sobrescriba la informacion.
	*
	* 
	**/
	public void updateFile() {
		try(FileOutputStream file = new FileOutputStream(NOMBRE_FICHERO, false);
			ObjectOutputStream buffer = new ObjectOutputStream(file)) {
			for(Coche c : stock) {
				buffer.writeObject(c);
			}
			System.out.println("INFORMACION ACTUALIZADA EN FICHERO\n"
					+ "CERRANDO APLICACION"); 
		} catch (IOException e) {
			System.out.println("FALLO AL ACTUALIZAR" + e.getMessage());
		}
	}

	/**
	 * Abrimos fichero en modo false para que se sobrescriba la información.
	 *
	 *
	 **/
	public void exportData() {
		try(FileWriter file = new FileWriter(FICHERO_EXPORTAR, false);
				BufferedWriter buffer = new BufferedWriter(file);) {
			for(Coche c : stock) {
				buffer.write(c.toString());
				buffer.newLine();
			}
			System.out.println("INFORMACION ACTUALIZADA EN FICHERO\n");
		} catch (IOException e) {
			System.out.println("FALLO AL ACTUALIZAR" + e.getMessage());
		}
	}
}

