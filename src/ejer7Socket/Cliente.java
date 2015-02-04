package ejer7Socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
	public static void main(String args[]) {

		cliente();
	}

	public static void cliente() {

		Socket cliente;
		ObjectOutputStream output;
		String mensaje;
		ObjectInputStream input = null;
		int num1;
		try {
			Scanner teclado = new Scanner(System.in);
			System.out.println("Introduzca numero1:");
			num1 =  teclado.nextInt();

			// Creamos el socket para conectarnos al puerto 5000 del servidor
			cliente = new Socket(InetAddress.getLocalHost(), 5000);
			// Creamos los canales de entrada/salida
			output = new ObjectOutputStream(new DataOutputStream(cliente.getOutputStream()));
			input = new ObjectInputStream(new DataInputStream(cliente.getInputStream()));
			mensaje = "Cliente 1";
			// Enviamos un mensaje al servidor
			output.writeUTF(mensaje);
			output.writeInt(num1);

			// Leemos la respuesta

			System.out.println(input.readUTF() + "  primo?"
					+ input.readObject());
			// Se cierra la conexión
			cliente.close();
			teclado.close();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Error: " + e.getMessage());
		}

	}

}
