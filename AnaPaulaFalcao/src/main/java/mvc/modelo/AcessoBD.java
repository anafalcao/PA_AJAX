package mvc.modelo;

import dto.Parametros;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.json.JsonArrayBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import javax.json.*;

/**
 *
 * @author User
 */
public class AcessoBD {
    ResultSet resultSet;
    Statement stmt;
    Connection con;
    ArrayList<Parametros> parametros_lista;
    Parametros par = new Parametros();
    ArrayList<Parametros> medidores_lista;
    Parametros med = new Parametros();
    

    public JsonArrayBuilder getParametros(String medidor, String periodo, String datahora) throws SQLException{
        
        con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tempumidade", "tempumidade", "tempumidade");
        stmt=con.createStatement();
        
        
        String where = "";
        String ano = datahora.split("-")[0];
        String mes;
        String dia;
        
       
        switch(periodo) {
            case "ano":
                 where = "datahora >= '" + ano + "-01-01'::date " +
                          "AND datahora < ('" + ano +"-12-31 23:59:59') LIMIT 10;";
                 break;
            case "mes": 
                mes = datahora.split("-")[1];
                dia = datahora.split("-")[2];
                where = "datahora >= ('" + ano + "-"+ mes +"-"+ dia +"'::date - '1 month'::interval) " +
                        "AND datahora < ('" + ano +"-"+ mes +"-"+ dia +" 23:59:59') LIMIT 10;";
                break;
            case "dia": 
                mes = datahora.split("-")[1];
                dia = datahora.split("-")[2];
                where = "datahora >= '" + ano + "-"+ mes +"-"+ dia +"'::date " +
                          "AND datahora < ('" + ano +"-"+ mes +"-"+ dia +" 23:59:59') LIMIT 10;";
                break;
            case "semana": 
                mes = datahora.split("-")[1];
                dia = datahora.split("-")[2];
                where = "datahora >= ('" + ano + "-"+ mes +"-"+ dia +"'::date - '7 days'::interval) " +
                          "AND datahora < ('" + ano +"-"+ mes +"-"+ dia +" 23:59:59') LIMIT 10;";
                break;
        }
    
        
        String sql = "SELECT * FROM " + medidor + " where " + where;
        
        resultSet = stmt.executeQuery(sql);
        parametros_lista = new ArrayList();
        JsonArrayBuilder array= Json.createArrayBuilder() ;
            while(resultSet.next()){
                
            
                JsonObjectBuilder obj = Json.createObjectBuilder();
                obj.add("datahora",resultSet.getString("datahora"));
                obj.add("temperatura",resultSet.getString("temperatura"));
                obj.add("umidade",resultSet.getString("umidade"));
                array.add(obj);
            //
           
        }
            
            //par = new Parametros();
                                
            //par.setMedidor(resultSet.getString("medidor")); 
            //par.setTemperatura(resultSet.getString("temperatura")); 
            //par.setDataHora(resultSet.getString("datahora")); 
            //par.setUmidade(resultSet.getString("umidade")); 
            //parametros_lista.add(par);
            //}
    //return parametros_lista;
    return array;
    }   
}


