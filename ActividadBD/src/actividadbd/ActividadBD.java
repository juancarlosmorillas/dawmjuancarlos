/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package actividadbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ActividadBD {
public Connection getConexion()
{
    Connection conexion = null;
   return conexion;
}
 
/**
* Método utilizado para establecer la conexión con la base de datos
* @return estado regresa el estado de la conexión, true si se estableció la conexión,
* falso en caso contrario
*/
public boolean crearConexion()
{
   try {
      Class.forName("com.mysql.jdbc.Driver");
       Connection conexion = DriverManager.getConnection("jdbc:mysql://www.db4free.net:3306/izv_dawm","izv_dawm","123456");
       String sqlcomercio="CREATE TABLE JMComercio"+
               "(idcomercio int Primary Key,"+
               "nombre varchar(50),"+
               "direccion varchar(100),"+
               "tipo varchar(50))";
       String sqlhorario="CREATE TABLE JMHorario"+
               "(idhorario int Primary Key,"+
               "dia varchar check(dia in ('lunes','martes','miercoles','jueves','viernes','sabado')),"+
               "hinicio int,"+
               "hfin int,"+
               "minicio int,"+
               "mfin int,"+
               "idcomercio int,"+
               "CONSTRAINT fk_idcomercio_jmcomercio FOREIGN KEY (idcomercio) REFERENCES JMComercio (idcomercio))";
       Statement stmt=null;
       try{
           stmt=conexion.createStatement();
           stmt.executeUpdate(sqlcomercio);
           stmt.executeUpdate(sqlhorario);
       }catch(SQLException e){
           System.out.println(e.getMessage());
           e.printStackTrace();
       }
               
   } catch (SQLException ex) {
       System.out.println(ex.getMessage());
      ex.printStackTrace();
      return false;
   } catch (ClassNotFoundException ex) {
       System.out.println(ex.getMessage());
      ex.printStackTrace();
      return false;
   }
 
   return true;
}
 
/**
*
*Método utilizado para realizar las instrucciones: INSERT, DELETE y UPDATE
*@param sql Cadena que contiene la instrucción SQL a ejecutar
*@return estado regresa el estado de la ejecución, true(éxito) o false(error)
*
*/
public boolean ejecutarSQL(String sql)
{
   try {
      Statement sentencia = conexion.createStatement();
      sentencia.executeUpdate(sql);
   } catch (SQLException ex) {
      ex.printStackTrace();
   return false;
   } 
 
   return true;
}
 
/**
*
*Método utilizado para realizar la instrucción SELECT
*@param sql Cadena que contiene la instrucción SQL a ejecutar
*@return resultado regresa los registros generados por la consulta
*
*/
public ResultSet ejecutarSQLSelect(String sql)
{
   ResultSet resultado;
   try {
      Statement sentencia = conexion.createStatement();
      resultado = sentencia.executeQuery(sql);
   } catch (SQLException ex) {
      ex.printStackTrace();
      return null;
   }
 
   return resultado;
}
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}