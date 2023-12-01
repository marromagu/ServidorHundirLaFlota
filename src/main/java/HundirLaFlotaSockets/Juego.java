/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HundirLaFlotaSockets;

import java.util.HashMap;

/**
 *
 * @author DAM_M
 */
public class Juego {

    private HashMap<String, Usuario> usuarios = new HashMap<>();

    public void crearUsuario(String usuario, String contraseña) {
        if (usuarios.get(usuario) == null) {
            usuarios.put(usuario, new Usuario(usuario, contraseña));
        }
    }

    public String mostrarUsuario(String usuario) {
        return String.valueOf(usuarios.get(usuario));

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Juego{");
        sb.append("usuarios=").append(usuarios);
        sb.append('}');
        return sb.toString();
    }

    public void menu(int op) {
        while (op != 0) {
            switch (op) {
                case 1 -> {

                }
                case 2 -> {

                }
                case 3 -> {

                }
                case 4 -> {

                }
                default ->
                    throw new AssertionError();
            }
            op = opcion();
        }

    }

    public int opcion() {

        return 0;
    }

    public String opciones() {
        String menu = ("1. Empezar nueva partida con otro usuario (verá una lista de los clientes a elegir)\n"
                + "2. Ver partidas ya terminadas y poder reproducirlas paso a paso.\n"
                + "3. Ver resto de partidas sin terminar en la que está y disparar en las que es su turno.\n"
                + "4. Ver resto de partidas sin terminar y renunciar en cualquier momento, dando la victoria\n"
                + "por abandono a su contrincante.");
        return menu;
    }
}
