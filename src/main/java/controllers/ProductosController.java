package controllers;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Productos;
import models.ProductosDao;
import Errores.Errores;

@WebServlet(name = "ProductosController", urlPatterns = {"/api"})
public class ProductosController extends HttpServlet {

    private final Gson gson = new Gson();
    ProductosDao productos = new ProductosDao();
    
    // Métodos GET, POST, faltan PUT y DELETE
    
    @Override
    protected void doGet(
            HttpServletRequest req, 
            HttpServletResponse resp) 
            throws ServletException, IOException {
    
        String listaJson = gson.toJson(productos.listarProductos());
        enviar(resp, listaJson);
    } 

    @Override
    protected void doPost(
            HttpServletRequest req, 
            HttpServletResponse resp) 
            throws ServletException, IOException {
       
        String body = inputStreamToString(req.getInputStream());
        Productos producto = gson.fromJson(body, Productos.class); 
        
        boolean exito = productos.agregarProducto(producto);
        String error = obtenerError(exito);
        enviar(resp, error);
    }
    
    // Métodos privados de utilidad

    private static String inputStreamToString(InputStream inputStream){
        Scanner scanner = new Scanner(inputStream, "UTF-8");
        return scanner.hasNext()
                ? scanner.useDelimiter("\\A").next()
                : "";
    }
    
    private String obtenerError(boolean encontrado){
                Errores error = encontrado 
                ? new Errores(0) 
                : new Errores(1);        
        return gson.toJson(error);
    }
    
    private void enviar(
            HttpServletResponse resp, String objeto) 
            throws IOException{
        PrintWriter out = resp.getWriter();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        out.print(objeto);
        out.flush();
    }
    
}
