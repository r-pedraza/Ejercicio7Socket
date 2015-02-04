package ejer7Socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente2 {
	public static void main(String args[]) {
		cliente2();
	}
	
	public static void cliente2(){
		
		Socket cliente;
		DataInputStream entrada;
		DataOutputStream salida;
		String mensaje, respuesta;
		int num1,num2,resultado;
		try {
			Scanner teclado = new Scanner(System.in);
			System.out.println("Introduzca numero1:");
			num1=teclado.nextInt();
			System.out.println("Introduzca numero2:");
			num2=teclado.nextInt();
			// Creamos el socket para conectarnos al puerto 5000 del servidor
			cliente = new Socket(InetAddress.getLocalHost(), 5000);
			entrada = new DataInputStream(cliente.getInputStream());
			// Creamos los canales de entrada/salida
			salida = new DataOutputStream(cliente.getOutputStream());
			mensaje = "Cliente 2";
			// Enviamos un mensaje al servidor
			salida.writeUTF(mensaje);
			salida.writeInt(num1);
			salida.writeInt(num2);
			// Leemos la respuesta
			respuesta = entrada.readUTF();
			resultado = entrada.readInt();
			System.out.println("Id Cliente" + mensaje);
			System.out.println("Respuesta del Servidor: ");
			System.out.println(respuesta+resultado);
			// Se cierra la conexión
			cliente.close();
			teclado.close();
		} catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}