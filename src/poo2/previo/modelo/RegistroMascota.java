package poo2.previo.modelo;

import java.sql.SQLException;
import java.util.ArrayList;
import poo2.previo.factory.MascotaH2DAOFactory;


import poo2.previo.modelo.dao.MascotaH2DAO;

/**
 * Los atributos de la tabla Mascota deben llamarse así:
 * identificador llave primaria numerico
 * nombre varchar
 * raza varhcar
 * edad numerico
 * 
 * NO CAMBIE ESOS NOMBRES
 * 
 * @author borisperezg
 *
 */
public class RegistroMascota {

	// Se debe implementar usando el patrón
	// Abstract Factory
        private MascotaH2DAO MascotaH2;
	private MascotaH2DAOFactory Mh2;
	// Recuerden que las mascotas se almacerán en la BD. 
	// Se podrán tener en un ArrayList de forma temporal para retornarlo
	// al controlador, pero nunca se almacenará en memoria (ArrayList).
	private ArrayList<String> listaRazas, listaAtributos, listaComparacion;
	
	public RegistroMascota() {
		
		// Se cargan los ArrayList con los elementos del ComboBox
		cargarRazas();
		cargarAtributos();
		cargarComparacion();

		// Programe el siguiente método si quiere que se carguen 6 mascotas de forma automática
		// Este metodo supone que el metodo agregarMascota está bien implementado.
		// cargarMascotas();
		
		
		// Use esta parte para obtener la instancia correspondiente al DAO 
		// de H2.
		this.Mh2 =new MascotaH2DAOFactory();
                this.MascotaH2= this.Mh2.createMascotaH2DAO();
		// iMascotaDAO = ...;
	}
	
	public String agregarMascota(String identificador, String nombre, String raza, String edad) throws SQLException {
                String m="";
		m= this.MascotaH2.buscarMascota(Integer.parseInt(identificador));
                if(m.equals("")){
                  m=  this.MascotaH2.agregarMascota(Integer.parseInt(identificador), nombre, raza, Integer.parseInt(edad));
                }
		return m;
	}

	public ArrayList<String> listar(String atributo, String comparador, String valorAComparar) throws SQLException {
		 ArrayList<String> res=new ArrayList();
             if (atributo.equals("nombre")||atributo.equals("raza")){
                if(!comparador.equals(">")&&!comparador.equals("<")){
                    res.add("listado no valido");
                    return res;
                }
            }else{
                res= this.MascotaH2.listarMascotas(atributo, comparador, valorAComparar);
            }
             return res;
	}
	
	// ==============================
	// ==============================
	// METODOS GET DE LOS ARRAYLISTS
	
	public ArrayList<String> getListaRazas(){
		return listaRazas;
	}
	
	public ArrayList<String> getListaAtributos(){
		return listaAtributos;
	}
	
	public ArrayList<String> getListaComparacion(){
		return listaComparacion;
	}
	
	// ==============================
	// ==============================
	// METODOS PRIVADOS
	
	/**
	 * Este metodo inserta 6 mascotas para evitar que el programador
	 * deba registrarlas todas las veces por pantalla.
	 * Este metodo supone que el metodo agregarMascota está bien implementado.
	 * Sirve para probar los listados.
	 */
	private void cargarMascotas() throws SQLException {
		agregarMascota("1", "Pepita", "Pitbull", "6");
		agregarMascota("2", "Maracho", "Pastor Aleman", "9");
		agregarMascota("3", "Simon", "French Poodle", "2");
		agregarMascota("4", "Perla", "Doberman", "4");
		agregarMascota("5", "Demonio", "Pastor Aleman", "5");
		agregarMascota("6", "Manito", "Pitbull", "11");
	}
	
	private void cargarRazas() {
		listaRazas = new ArrayList<String>();
		listaRazas.add("Pitbull");
		listaRazas.add("Doberman");
		listaRazas.add("French Poodle");
		listaRazas.add("Pastor Aleman");
	}
	
	private void cargarAtributos() {
		listaAtributos = new ArrayList<String>();
		listaAtributos.add("identificador");
		listaAtributos.add("nombre");
		listaAtributos.add("raza");
		listaAtributos.add("edad");
	}
	
	private void cargarComparacion() {
		listaComparacion = new ArrayList<String>();
		listaComparacion.add(">");
		listaComparacion.add("<");
		listaComparacion.add("=");
		listaComparacion.add("!=");
	}
	
	
}
