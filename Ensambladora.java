import java.util.Scanner;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;

public class Ensambladora {

    public static int cuenta[], idPiezas[];
    public static Map<Character, Integer> letras;
    public static Map<Integer, String> colores;
    public static Map<Integer, String> piezas;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        ListaEnlazada cintaGenerador = new ListaEnlazada();
        ListaEnlazada cintaPintor = new ListaEnlazada();
        ListaEnlazada cintaEnsamblador = new ListaEnlazada();
        
        
        setLetras();
        setColores();
        setPiezas();

        System.out.println("Cuantas piezas completan un producto");
        int numPiezas = sc.nextInt();

    	System.out.println("Cuantos robots generadores habra por producto?");
    	int numGen = sc.nextInt();

        System.out.println("Cuantos robots pintores habra?");
        int numPint = sc.nextInt();

        System.out.println("Cuantos robots ensambladores habra?");
        int numEnsam = sc.nextInt();

        System.out.println("Cual es el tiempo maximo para el tiempo de pintado (seg)?");
        int tPintado = sc.nextInt() * 1000;

        System.out.println("Cual es la capacidad de la bodega?");
        int tamabodega = sc.nextInt();

        Bodega bodega = new Bodega(tamabodega);
        int tamthr = ((numPiezas*numGen)+numPint+numEnsam);
        int count = 0;
        Thread[] thread = new Thread[tamthr];

        char c = 'A';
        cuenta = new int[numPiezas];
        for (int i = 0; i < numPiezas; i++) {
            cuenta[i] = 1;
        }
        
    	for (int i = 0; i < numPiezas; i++) {
            int dormir = rand.nextInt((1000 - 500) + 1) + 500;
            for (int j = 0; j < numGen; j++) {
                Generador g = new Generador(c, dormir, cintaGenerador,bodega);
                thread[count] = g;
                count++;
                g.start();
            }
            c++;
    	}

        for(int k = 0; k < numPint; k++){
            Pintor p = new Pintor(k,tPintado,cintaGenerador,cintaPintor,bodega);
            thread[count] = p;
            p.start();
            count++;
        }

        idPiezas = new int[numEnsam];
        for (int i = 0; i < numEnsam; i++) {
            idPiezas[i] = 0;
        }

        for(int k = 0; k < numEnsam; k++){
            Ensamblador ens = new Ensamblador(k, numPiezas,cintaPintor,cintaEnsamblador, idPiezas, bodega);
            thread[count] = ens;
            ens.start();
            count++;
        }

        for (int e = 0; e < tamthr ; e++){
            try{
                thread[e].join();
            } catch(InterruptedException ie){}
        }
        

        bodega.imprimirLista();
    }
    

    public static void setLetras(){
        letras = new HashMap<>();  
        letras.put('A', 0);
        letras.put('B', 1);
        letras.put('C', 2);
        letras.put('D', 3);
        letras.put('E', 4);
        letras.put('F', 5);
        letras.put('G', 6);
        letras.put('H', 7);
        letras.put('I', 8);
        letras.put('J', 9);
        letras.put('K', 10);
        letras.put('L', 11);
        letras.put('M', 12);
        letras.put('N', 13);
        letras.put('O', 14);
        letras.put('P', 15);
        letras.put('Q', 16);
        letras.put('R', 17);
        letras.put('S', 18);
        letras.put('T', 19);
        letras.put('U', 20);
        letras.put('V', 21);
        letras.put('W', 22);
        letras.put('X', 23);
        letras.put('Y', 24);
        letras.put('Z', 25);
    }
    
    public static void setColores(){
        colores = new HashMap<>();  
        colores.put(0, "Rojo");
        colores.put(1, "Verde");
        colores.put(2, "Negro");
        colores.put(3, "Azul");
        colores.put(4, "Blanco");
        colores.put(5, "Plata");
    }

    public static void setPiezas(){
        piezas = new HashMap<>();
        piezas.put(0, "Defensa");
        piezas.put(1, "Puerta");
        piezas.put(2, "Techo");
        piezas.put(3, "Maletero");
        piezas.put(4, "Paracoches");
        piezas.put(5, "Retrovisor");
    }

}