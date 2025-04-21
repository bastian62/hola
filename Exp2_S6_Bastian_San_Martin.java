/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tarea;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Exp2_S6_Bastian_San_Martin {

    private static Map<Integer, Timer> temporizadores = new HashMap<>(); // <Asiento, Timer>
    private static int precioEntrada = 5000;
    private static Map<Integer, String> ventas = new HashMap<>(); 
    private static List<String> boletas = new ArrayList<>(); 
    private static int totalEntradasVendidas = 0;
    private static int totalReservas = 0;
    private static int ingresosTotales = 0;
    private static Scanner sc = new Scanner(System.in);
    private static int[] asientos = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

    public static void main(String[] args) {
        boolean continuar = true;

        while (continuar) { //menu
            System.out.println("=== compras de entradas teatro moro ===");
            System.out.println("1. Reservar asiento");
            System.out.println("2. Comprar asiento");
            System.out.println("3. cancelar reserva");
            System.out.println("4. Imprimir boleta");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opcion: ");
            int opcion = sc.nextInt();

            switch (opcion) { //switch para elegir opciones del menu
                case 1:
                    reservarAsiento();
                    break;
                case 2:
                    comprarAsiento();
                    break;
                case 3:
                    CancelarVenta();
                    break;
                case 4:
                    imprimirBoletas();
                    break;
                case 5:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
   //mostrar asiento como vendido[x] o reservado[R]
    public static void mostrarAsientos() { 
        System.out.println("Asientos disponibles:");
        for (int i : asientos) {
            if (ventas.containsKey(i)) {
                if (ventas.get(i).equals("vendido")) {
                    System.out.print("[X] ");
                } else if (ventas.get(i).equals("reservado")) {
                    System.out.print("[R] ");
                }
            } else {
                System.out.print("[" + i + "] ");
            }
        }
        System.out.println();
    }
    // marca el asiento elegido como reservado
    public static void reservarAsiento() { 
        mostrarAsientos();
        System.out.print("Ingrese numero de asiento a reservar: ");
        int asiento = sc.nextInt();

        if (validarAsientoDisponible(asiento)) {
            ventas.put(asiento, "reservado");
            totalReservas++;
            System.out.println("Asiento " + asiento + " reservado correctamente.");
            System.out.println("(su reservacion caduca en 30 segundos");
            // Depuración
            System.out.println("[DEBUG] Total reservas: " + totalReservas);
            //temporizador una vez ya reservado el asiento 
            Timer timer = new Timer();
            temporizadores.put(asiento, timer); 
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (ventas.containsKey(asiento) && ventas.get(asiento).equals("reservado")) {
                        ventas.remove(asiento);
                        totalReservas--;
                        System.out.println(" La reserva del asiento " + asiento + " ha caducado.");
                    }
                }
            }, 30000); // 30 segundos
        
        } else {
            System.out.println("Asiento no disponible.");   
        }
    }
    
    public static void comprarAsiento() {
        mostrarAsientos();
        System.out.print("Ingrese número de asiento a comprar: ");
        int asiento = sc.nextInt();

        if (ventas.containsKey(asiento) && ventas.get(asiento).equals("reservado")) {
            ventas.put(asiento, "vendido");
            totalEntradasVendidas++;
            totalReservas--;
            ingresosTotales += precioEntrada;
            String boleta = "Asiento: " + asiento + ", Precio: $" + precioEntrada;
            boletas.add(boleta);
            cancelarTemporizador(asiento);
            System.out.println("Compra realizada correctamente.");
            // Depuración
            System.out.println("[DEBUG] Entradas vendidas: " + totalEntradasVendidas + ", Ingresos: $" + ingresosTotales);
        } else if (!ventas.containsKey(asiento)) {
            ventas.put(asiento, "vendido");
            totalEntradasVendidas++;
            ingresosTotales += precioEntrada;
            String boleta = "Asiento: " + asiento + ", Precio: $" + precioEntrada;
            boletas.add(boleta);
            System.out.println("Compra realizada correctamente.");
        } else {
            System.out.println("Asiento no disponible para compra.");
        }
    }

    public static void CancelarVenta() {
        mostrarAsientos();
        System.out.print(" que reservacion desea cancelar ");
        int asiento = sc.nextInt();

        if (ventas.containsKey(asiento) && ventas.get(asiento).equals("reservado")) {
            ventas.remove(asiento);
            totalReservas--;
            cancelarTemporizador(asiento);
            System.out.println("reserva del asiento" + asiento + "cancelada con exito");
            
        } else {
            System.out.println("El asiento no ha sido reservado");
        }
    }

    public static void imprimirBoletas() {
        System.out.println(" ==Boletas Generadas==");
        for (String boleta : boletas) {
            System.out.println(boleta);
        }
        // Depuración
        System.out.println("[DEBUG] Total ingresos: $" + ingresosTotales + ", Total entradas vendidas: " + totalEntradasVendidas);
    }

    public static boolean validarAsientoDisponible(int asiento) {
        for (int i : asientos) {
            if (i == asiento && !ventas.containsKey(asiento)) {
                return true;
            }
        }
        return false;
    }
    public static void cancelarTemporizador(int asiento) {
        if (temporizadores.containsKey(asiento)) {
            temporizadores.get(asiento).cancel();
            temporizadores.remove(asiento);
        }
}
}