
import java.util.Scanner;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;




public class Exp2_S6_Bastian_San_Martin {
 
    
   


 public static void main(String[] args) {
        
        
        
        Scanner sc = new Scanner(System.in);
        int menu;
        int[] zona1 = {1,2,3,4};
        int[] zona2 = {5,6,7,8};
        int[] zona3 = {9,10,11,12};
       do {
         
     
            System.out.println("--Menu de compras--");
            System.out.println("1. Comprar");
            System.out.println("2. carrito");
            System.out.println("3. salir");
            System.out.print("Seleccione una opcion: ");
            menu = sc.nextInt();
            switch (menu){
                case 1:
                    System.out.println("Zonas disponibles:");
                    
                    System.out.println("1. Zona A: ");
                    mostrarZona(zona1);
                    System.out.println("2. Zona B: ");
                    mostrarZona(zona2);
                    System.out.println("3. Zona C: ");
                    mostrarZona(zona3);
                    System.out.print("Elija la zona (1-3): ");
                    int zona = sc.nextInt();
                    switch (zona){
                        case 1:
                            
                        case 2:
                            
                        case 3:
                            
                        default: System.out.println("opcion no disponible");
                            
                    }
                    
                case 2:
                case 3:
                default: System.out.println("opcion no disponible");
                    
            }
       } while (menu == 3);
            
    }

    private static void mostrarZona(int[] zona1) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
        
    
    

