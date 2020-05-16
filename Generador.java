import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Generador extends Thread {
	private char tipo;
	private int dormir;
	private volatile ListaEnlazada cinta;
    private Random rand = new Random();
    private Bodega bodega;

    public Generador(){}
    
	public Generador(char tipo, int dormir, ListaEnlazada cinta, Bodega bodega){
		this.tipo = tipo;
		this.dormir = dormir;
		this.cinta = cinta;
		this.bodega = bodega;
	}

	@Override
	public void run(){
		int espacioCuenta = Ensambladora.letras.get(this.tipo);
		String descripcion = Ensambladora.piezas.get(Ensambladora.letras.get(this.tipo) % 6);
		while(!bodega.isFull()){
			try{
				sleep(this.dormir);
			}catch(InterruptedException e){}

			cinta.addNodo(descripcion, this.tipo, espacioCuenta);
		}
	}

	public ListaEnlazada getCinta(){
		return this.cinta;
	}
}