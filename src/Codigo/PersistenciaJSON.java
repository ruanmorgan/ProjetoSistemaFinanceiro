package Codigo;

import Codigo.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class PersistenciaJSON {

    public static void salvar(Usuario usuario) {

        ObjectMapper mapper = new ObjectMapper();

        try {

            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File("usuario.json"), usuario);

            System.out.println("JSON salvo!");

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public static Usuario carregar() {

        ObjectMapper mapper = new ObjectMapper();

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