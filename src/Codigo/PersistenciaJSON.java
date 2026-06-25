package Codigo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class PersistenciaJSON {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static void salvar(Usuario usuario) {

        try {

            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File("usuario.json"), usuario);

            System.out.println("JSON salvo!");

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public static Usuario carregar() {

        try {

            return mapper.readValue(
                    new File("usuario.json"),
                    Usuario.class
            );

        } catch (IOException e) {

            e.printStackTrace();

            return null;
        }
    }
}