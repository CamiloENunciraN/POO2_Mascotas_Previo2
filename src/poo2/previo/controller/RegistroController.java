package poo2.previo.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import poo2.previo.modelo.RegistroMascota;

public class RegistroController implements Initializable {

	@FXML private TextField txtIdentificador;
	@FXML private TextField txtNombre;
	@FXML private TextField txtEdad;
	@FXML private TextField txtResultado;
	@FXML private TextField txtValor;
	
	@FXML private ComboBox<String> cmbRaza; 
	@FXML private ComboBox<String> cmbAtributo;
	@FXML private ComboBox<String> cmbComparacion;
	
	@FXML private ListView<String> lvMascotas;
	
	private RegistroMascota registro;
	
	public RegistroController() {
		registro = new RegistroMascota();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Se llenan los combos con la información del Modelo.
		cmbRaza.getItems().addAll(registro.getListaRazas());
		cmbAtributo.getItems().addAll(registro.getListaAtributos());
		cmbComparacion.getItems().addAll(registro.getListaComparacion());
	} 
	
	@FXML public void agregar(ActionEvent e) throws SQLException {
		String identificador = txtIdentificador.getText();
		String nombre = txtNombre.getText();
		String raza = cmbRaza.getValue();
		String edad = txtEdad.getText();
		
		if(!identificador.isEmpty() && !nombre.isEmpty() && !raza.isEmpty() && !edad.isEmpty()) {
			String mensaje = registro.agregarMascota(identificador, nombre, raza, edad);
			
			// Limpiar campos si el resultado fue exitoso
			
			txtResultado.setText(mensaje);
		}
	}
	
	@FXML public void listar(ActionEvent e) throws SQLException {
		String atributo = cmbAtributo.getValue();
		String comparador = cmbComparacion.getValue();
		String valorAComparar = txtValor.getText();
		
		ArrayList<String> listaMascotas = registro.listar(atributo, comparador, valorAComparar);
		if(listaMascotas!=null && listaMascotas.size()>0) {
			lvMascotas.getItems().clear();
			lvMascotas.getItems().addAll(listaMascotas);
		}else {
			lvMascotas.getItems().clear();
			lvMascotas.getItems().add("No se encontraron registros");
		}
		
	}
	
	
	
}
