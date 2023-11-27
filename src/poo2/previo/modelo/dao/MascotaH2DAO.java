package poo2.previo.modelo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import poo2.previo.db.H2JDBCUtils;
//scritp de crae tabla CREATE TABLE MASCOTA (identificador INT PRIMARY KEY, nombre VARCHAR(20), raza VARCHAR(20), edad INT)
public class MascotaH2DAO {
    
        public String agregarMascota(int identificador, String nombre, String raza, int edad) throws SQLException {
        String res="";
        Connection conn = null;
	Statement statementOb = null;

		try {
			conn = H2JDBCUtils.getConnection();
			statementOb = conn.createStatement();

			StringBuffer sb = new StringBuffer();
			sb.append("INSERT INTO MASCOTA (identificador, nombre, raza, edad) ");
			sb.append("VALUES ("+identificador+", '"+nombre+"', '"+raza+"', "+edad+")");

			statementOb.executeUpdate(sb.toString());
                        res="Mascota "+identificador+" - "+nombre+" agregada";
		} catch (SQLException e) {
			// print SQL exception information
                        res="Ha occurrido un error";
			e.printStackTrace();
		} finally {
			// Close the connection
			try {
                                statementOb.close();
				conn.close();
			} catch (SQLException e) {
				// print SQL exception information
				e.printStackTrace();
			}
		}
        return res;
    }
        
        public ArrayList<String> listarMascotas(String atributo, String comparador, String valorAComparar) throws SQLException{
        ArrayList<String> res=null;
	Connection conn = null;
        Statement statementOb = null;

		try {
			conn = H2JDBCUtils.getConnection();
			statementOb = conn.createStatement();

			String sql = this.crearConsulta( atributo,  comparador, valorAComparar);
                        if(sql.equals("no valido")){
                            return res;
                        }
			ResultSet rs = statementOb.executeQuery(sql);
			res=new ArrayList();
			while(rs.next()) { 
                            // Retrieve by column name 
                            int identificador  = rs.getInt("identificador"); 
                            String nombre = rs.getString("nombre");  
                            // creacion de la lista
                            res.add(identificador+" - "+ nombre);
                        } 
			rs.close();
		} catch (SQLException e) {
			// print SQL exception information
			e.printStackTrace();
		} finally {
			// Close the connection
			try {
				statementOb.close();
				conn.close();
			} catch (SQLException e) {
				// print SQL exception information
				e.printStackTrace();
			}

		}
        return res;
    }
        
        private String crearConsulta(String atributo, String comparador, String valorAComparar){
            String r="SELECT id, nombre FROM MASCOTA WHERE ";
            if(atributo.equals("identificador")){
                r+="identificador "+comparador+" "+valorAComparar;
            }else if (atributo.equals("nombre")){
                if(!comparador.equals(">")&&!comparador.equals("<")){
                    r+="nombre "+comparador+" '"+valorAComparar+"'";
                }else{
                    r="no valido";
                }
            }else if (atributo.equals("raza")){
                if(!comparador.equals(">")&&!comparador.equals("<")){
                    r+="raza "+comparador+" '"+valorAComparar+"'";
                }else{
                    r="no valido";
                }
            }else if (atributo.equals("edad")){
                r+="edad "+comparador+" "+valorAComparar;
            }
            return r;
        }

        public String buscarMascota(int identificador) throws SQLException{
        String res="";
        Connection conn = null;
        Statement statementOb = null;

		try {
			conn = H2JDBCUtils.getConnection();
			statementOb = conn.createStatement();

			String sql = "SELECT nombre FROM MASCOTA WHERE identificador="+identificador;
			ResultSet rs = statementOb.executeQuery(sql);
			
			while(rs.next()) { 
                            // Retrieve by column name 
                            String nombre = rs.getString("nombre");  
                            res+=nombre;
                        } 
			rs.close();
		} catch (SQLException e) {
			// print SQL exception information
			e.printStackTrace();
		} finally {
			// Close the connection
			try {
				statementOb.close();
				conn.close();
			} catch (SQLException e) {
				// print SQL exception information
				e.printStackTrace();
			}

		}
        return res;
    }
}
