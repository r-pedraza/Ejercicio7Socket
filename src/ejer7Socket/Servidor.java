package ejer7Socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Servidor {
	// Arranque servidor
	public static void main(String args[]) {
		ServerSocket servidor;
		Socket conexion;
		ObjectInputStream input=null;
		ObjectOutputStream output = null;
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
				input = new ObjectInputStream(new DataInputStream(conexion.getInputStream()));
				output = new ObjectOutputStream(new DataOutputStream(conexion.getOutputStream()));
				// Leemos el mensaje del cliente
				String mensaje = input.readUTF();
				int num1 = input.readInt();
				System.out.println("Conexión n." + num + " mensaje: "
						+ mensaje);
				// Le respondemos al cliente
				output.writeUTF("Buenos días " + mensaje
						+ "  numeros primos de ");
				output.writeObject(primo(num1));
				// Se cierra la conexión
				conexion.close();
			}
		} catch (IOException e) {
		}
	}

	public static ArrayList<Integer> primo(int num1) {
		ArrayList<Integer> array=new ArrayList<>();
		for (int i = 1; i < num1; i++) {
			if (esPrimo(i)) {
				array.add(i);
			} else {
			}
		}
		return array;

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