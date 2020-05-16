import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

class Pintor extends Thread {
	private int dormir;
	private int tipo;
	private volatile ListaEnlazada cinta;
	private volatile ListaEnlazada cintaPintor;
	private volatile Nodo pieza;
    private Random rand = new Random();
    private Bodega bodega;

    public Pintor(){}
    
	public Pintor(int c, int dormir, ListaEnlazada cinta,ListaEnlazada cintaPintor, Bodega bodega){
		this.tipo = c;
		this.dormir = dormir;
		this.cinta = cinta;
		this.cintaPintor = cintaPintor;
		this.bodega = bodega;
	}

	@Override
	public void run(){
		int sleep = rand.nextInt((this.dormir));

		while(!bodega.isFull()){
			Nodo aux = cinta.removeNodo(this.tipo);
			if(aux != null){
				try{
					sleep(this.dormir-sleep);
				} catch (InterruptedException e){}

				cintaPintor.addNodo(this.tipo,aux);
			}else{
				System.out.println("Pintor " + this.tipo + "No hay piezas que pintar");
			}
		}
	}

	public synchronized void pintando(){
		
	}

	public ListaEnlazada getCinta(){
		return this.cinta;
	}
}