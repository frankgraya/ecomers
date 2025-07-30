package com.ecomers.ecomerskafka.controllers;

import com.ecomers.ecomerskafka.model.Persona;
import com.ecomers.ecomerskafka.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ConexionController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/verificar-bd")
    public Map<String, String> verificarConexion() {
        Map<String, String> respuesta = new HashMap<>();

        try (Connection conn = dataSource.getConnection()) {
            DatabaseMetaData metaData = conn.getMetaData();

            respuesta.put("estado", "✅ Conexión exitosa");
            respuesta.put("base_datos", metaData.getDatabaseProductName());
            respuesta.put("version", metaData.getDatabaseProductVersion());
            respuesta.put("url", metaData.getURL());
            respuesta.put("usuario_conectado", metaData.getUserName());

        } catch (Exception e) {
            respuesta.put("estado", "❌ Error de conexión");
            respuesta.put("error", e.getMessage());
        }

        return respuesta;
    }
}