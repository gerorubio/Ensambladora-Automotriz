import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Ensamblador extends Thread {

    private int numPiezas, index, idPiezas[];
    private ListaEnlazada cintaPintor, cintaEnsamble;
    private Nodo auto[];
    private Nodo pieza;
    private Bodega bodega;
    
    
    public Ensamblador(int k, int numGen, ListaEnlazada cinta, ListaEnlazada cintaEnsamble, int idPiezas[], Bodega bodega){
        this.index = k;
        this.numPiezas = numGen;
        this.cintaPintor = cinta;
        this.cintaEnsamble = cintaEnsamble;
        this.idPiezas = idPiezas;
        this.bodega = bodega;
        auto = new Nodo[numPiezas];
        pieza  = null;
    }

    @Override
    public void run(){
        while(!bodega.isFull()){
            pieza = null;
            for (int i = 0; i < numPiezas; i++) {
                while (pieza == null && !bodega.isFull()) {
                    pieza = cintaPintor.removeNodo(index, idPiezas);
                    if (pieza != null) {
                        auto[i] = pieza;
                    }
                }
                pieza = null;
            }
            if (bodega.isFull()) {
                break;
            }
            idPiezas[index] = 0;
            System.out.println("Nuevo auto");

            char c = 'A';
            String aux = Character.toString(c);
            Nodo[] armado = new Nodo[numPiezas];

            for(int j = 0; j < numPiezas; j++){
                for (int k = 0; k < numPiezas; k++) {
                    if(auto[k].getId().substring(0).contains(aux) ){ 
                        armado[j] = auto[k];
                        c++;
                        aux = Character.toString(c);
                        break;
                    }
                }
            }

            for (Nodo n : auto) {
                System.out.println("El robot de ensamble " + this.index + " esta ensamblando la pieza" + n.getId());
            }
            System.out.println("El robot de ensamble " + this.index + "termino el automovil:" + auto[0].getId().substring(1));

            bodega.addAuto(new Auto(armado));
            bodega.imprimirLista();
            System.out.println("");
        }
    }
        
}

