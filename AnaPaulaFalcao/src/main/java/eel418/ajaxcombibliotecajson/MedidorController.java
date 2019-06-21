package eel418.ajaxcombibliotecajson;

import com.google.gson.Gson;
import dto.Parametros;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.json.*;
import mvc.modelo.AcessoBD;
import mvc.modelo.MedidorPOJO;
import mvc.modelo.MedidorRepository;
import org.json.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MedidorController extends HttpServlet {

    protected void processGetRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        String serial = request.getParameter("serialno_medidores");
        String nome = request.getParameter("nome");
        String tabela = request.getParameter("tabela");

        try {

            JsonArrayBuilder resultado = new MedidorRepository().getAllMedidores();

            JsonObject resposta = Json.createObjectBuilder()
                    .add("data", resultado)
                    .build();

            out.print(resposta.toString());

        } catch (SQLException e) {
            out.print(e.getMessage());
        } finally {
            out.flush();
        }
    }

    protected void processPostRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        JSONObject jsonObject = null;
        MedidorPOJO[] medidores;
        PrintWriter out = response.getWriter();

        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
        } catch (Exception e) {
            /*report an error*/ }

        try {
            Gson g = new Gson();
            medidores = g.fromJson(jb.toString(), MedidorPOJO[].class);
        } catch (JSONException e) {
            // crash and burn
            throw new IOException("Error parsing JSON request string");
        }

        try {
            new MedidorRepository().updateMedidores(medidores);
        } catch (SQLException e) {
            response.sendError(500);
        } finally {
            out.flush();
        };
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processGetRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processPostRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private static JsonObject jsonFromString(String jsonObjectStr) {

        JsonReader jsonReader = Json.createReader(new StringReader(jsonObjectStr));
        JsonObject object = jsonReader.readObject();
        jsonReader.close();

        return object;
    }
}
