package ejer7Socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	// Arranque servidor
	public static void main(String args[]) {
		ServerSocket servidor;
		Socket conexion;
		DataOutputStream salida;
		DataInputStream entrada;
		int num = 0;

		try {
			// Creamos un ServerSocket en el puerto especificado
			servidor = new ServerSocket(5000);
			System.out.println("Servidor Arrancado correctamente");
			while (true) {
				// Se espera la conexión de algún cliente
				conexion = servidor.accept();
				num++;
				System.out.println("Conexión número" + num + " desde: "
						+ conexion.getInetAddress().getHostName());
				// Abrimos los canales de entrada y salida
				entrada = new DataInputStream(conexion.getInputStream());
				salida = new DataOutputStream(conexion.getOutputStream());
				// Leemos el mensaje del cliente
				String mensaje = entrada.readUTF();
				int num1 = entrada.readInt();
				System.out.println("Conexión n." + num + " mensaje: "
						+ mensaje);
				// Le respondemos al cliente
				salida.writeUTF("Buenos días " + mensaje
						+ "  numeros primos de ");
      				salida.writeInt(primo(num1));
				
				// Se cierra la conexión
				conexion.close();
			}
		} catch (IOException e) {
		}
	}

	public static int  primo(int num1) {

		for (int i = 1; i < num1; i++) {

			if (esPrimo(i)) {
				System.out.println("Numero primo: " + i);
				
			}
		}
		return num1;
	}

	public static boolean esPrimo(int numero) {

		int aux;
		for (int cont = 2; cont < numero; cont++) {

			aux = numero % cont;
			if (aux == 0) {
				return false;
			}
		}
		return true;

	}
}