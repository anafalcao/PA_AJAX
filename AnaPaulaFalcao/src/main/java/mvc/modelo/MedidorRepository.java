package mvc.modelo;

import dto.Parametros;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
public class MedidorRepository {

    ResultSet resultSet;
    Statement stmt;
    Connection con;
    ArrayList<Parametros> parametros_lista;
    Parametros par = new Parametros();
    ArrayList<Parametros> medidores_lista;
    Parametros med = new Parametros();

    public JsonArrayBuilder getAllMedidores() throws SQLException {

        con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tempumidade", "tempumidade", "tempumidade");
        stmt = con.createStatement();

        String sql = "SELECT * FROM medidores";

        resultSet = stmt.executeQuery(sql);

        JsonArrayBuilder array = Json.createArrayBuilder();
        while (resultSet.next()) {

            JsonObjectBuilder obj = Json.createObjectBuilder();
            obj.add("serial", resultSet.getString("serialno_medidores"));
            obj.add("nome", resultSet.getString("nome"));
            obj.add("tabela", resultSet.getString("tabela"));
            array.add(obj);

        }

        return array;
    }

    public JsonArrayBuilder getMedidores(String serial, String nome, String tabela) throws SQLException {

        con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tempumidade", "tempumidade", "tempumidade");
        stmt = con.createStatement();

        String sql = "SELECT * FROM medidores ";

        resultSet = stmt.executeQuery(sql);
        parametros_lista = new ArrayList();
        JsonArrayBuilder array = Json.createArrayBuilder();
        while (resultSet.next()) {

            JsonObjectBuilder obj = Json.createObjectBuilder();
            obj.add("serial", resultSet.getString("serial"));
            obj.add("nome", resultSet.getString("nome"));
            obj.add("tabela", resultSet.getString("tabela"));
            array.add(obj);

        }

        return array;
    }

    public void updateMedidores(MedidorPOJO[] medidores) throws SQLException {

        con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tempumidade", "tempumidade", "tempumidade");
        PreparedStatement ps;

        String sql = "INSERT INTO medidores "
                + " (serialno_medidores, nome, tabela) "
                + "VALUES(?,?,?) "
                + "ON CONFLICT(serialno_medidores) DO"
                + " UPDATE SET nome=excluded.nome;";

        ps = con.prepareStatement(sql);

        for (MedidorPOJO medidor : medidores) {
            ps.setInt(1, medidor.serial);
            ps.setString(2, medidor.nome);
            ps.setString(3, medidor.tabela);
            ps.addBatch();
        }
        ps.executeBatch();
        ps.close();

    }
}
