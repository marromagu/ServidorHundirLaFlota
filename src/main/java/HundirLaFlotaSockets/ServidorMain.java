/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package HundirLaFlotaSockets;

import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DAM_M
 */
public class ServidorMain extends Thread {//Extendemos la clase thread para poder atender a multiples clientes mediante lo hilos

    /**
     * Arquitectura cliente-servidor Al servidor se le podra conectar multiples
     * clientes. Pedir usuario y contraseña, cerrar conexion si no es valida. Un
     * mismo usuario solo puede estar conectado una vez al servidor, se debe
     * tratar esta situación.
     */
    /**
     * Creamos una constante estatica del Puerto por donde se concestaran los
     * clientes y declaramos el Socket.
     */
    Socket skCliente;
    static final int Puerto = 2001;

    /**
     *
     * @param sCliente se debe proporcionar al constructor ServidorMain como
     * Objeto Socket, cada instancia del Servidor tendra su propio socket.
     */
    public ServidorMain(Socket sCliente) {
        skCliente = sCliente;
    }

    public static void main(String[] args) {
        try {
            //Inicializamos el servidor en el puerto
            ServerSocket skServidor = new ServerSocket(Puerto);
            System.out.println("Escuchando en el puerto " + Puerto);

            while (true) {
                //Se conecta un cliente
                Socket skCliente = skServidor.accept();
                System.out.println("Cliente conectado");
                //Atendemos al cliente con un thread
                new ServidorMain(skCliente).start();
            }
        } catch (Exception e) {
            System.out.println("Ups");
        }

    }

    @Override
    public void run() {
        try {
            //Creamos los flujos de entrada y salida
            DataInputStream flujo_entrada = new DataInputStream(skCliente.getInputStream());
            DataOutputStream flujo_salida = new DataOutputStream(skCliente.getOutputStream());
            //Atendemos la peticion del cliente
            flujo_salida.writeUTF("Se ha conectado correctamente");
            pedirDatos(flujo_salida, flujo_entrada);
            //Cerramos conexion
            skCliente.close();
            System.out.println("Cliente desconectado");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void pedirDatos(DataOutputStream flujo_salida, DataInputStream flujo_entrada) {
        try {
            String usuario = "";
            String contraseña = "";
            Juego miJuego = null;
            flujo_salida.writeUTF("1- Log in");
            flujo_salida.writeUTF("2- Sign up");
            int op = flujo_entrada.readInt();
            switch (op) {
                case 1:
                    flujo_salida.writeUTF("Usuario:");
                    usuario = flujo_entrada.readUTF();
                    flujo_salida.writeUTF("Contraseña:");
                    contraseña = flujo_entrada.readUTF();
                    flujo_salida.writeUTF("Usuario Creado");
                    miJuego.crearUsuario(usuario, contraseña);
                    break;
                case 2:
                    flujo_salida.writeUTF("Por favor introduzca su usario y contraseña");
                    usuario = flujo_entrada.readUTF();
                    contraseña = flujo_entrada.readUTF();
                    flujo_salida.writeUTF("Entrando...");
                    System.out.println(miJuego.mostrarUsuario(usuario));
                    break;
                default:
                    throw new AssertionError();
            }

            flujo_salida.writeUTF(miJuego.opciones());
        } catch (IOException ex) {
            Logger.getLogger(ServidorMain.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
