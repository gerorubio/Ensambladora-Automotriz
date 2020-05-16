import java.util.*;

class ListaEnlazada {
    private Nodo inicio;
    private Nodo fin;

    public ListaEnlazada(){
        inicio=fin=null;
    }

    public synchronized void addNodo(Nodo temp){
        if(isEmpty()){ inicio=temp; }
        else{ fin.setNext(temp); }
        fin=temp;
        notifyAll();
    }

    public synchronized void addNodo(String descripcion, char tipo, int espacioCuenta){
        System.out.println("\nRobot Generador " + tipo + " termino pieza " + descripcion + "\n");
        String id = tipo + Integer.toString((Ensambladora.cuenta[espacioCuenta]++));
        String color = Ensambladora.colores.get(Ensambladora.cuenta[espacioCuenta] % 6);
        Nodo temp=new Nodo(id, color, descripcion);
        if(isEmpty()){ inicio=temp; }
        else{ fin.setNext(temp); }
        fin=temp;
        System.out.println("---------------GENERADOR - PINTOR-----------------");
        imprimirLista();
        System.out.println("--------------------------------------------------");
        notifyAll();
    }

    public synchronized void addNodo(int tipo,Nodo temp){
        if(isEmpty())
            inicio=temp; 
        else
            fin.setNext(temp); 
        fin=temp;
        System.out.println("--------------PINTOR - ENSAMBLADOR----------------");
        System.out.println("\nRobot Pintor " + tipo + " termino pieza " + temp.getDescripcion() + "\n");
        imprimirLista();
        System.out.println("--------------------------------------------------");
        notifyAll();
    }

    public synchronized Nodo removeNodo(int i){
        Nodo temp;

        while (isEmpty()) {
            try{
                wait();
            }catch (InterruptedException e) {}
        }

        temp=inicio;
        inicio=inicio.getNext();
        temp.setNext(null);
        System.out.println("El rbobot " + i + " envia a la seccion de  pintado la pieza: " + temp.getId());
        notifyAll();
        return temp;
    }

    public synchronized Nodo removeNodo(int index, int idPiezas[]){
        Nodo temp, anterior;
        boolean flag = false;
        while (isEmpty()) {
            try{
                wait();
            }catch (InterruptedException e) {}
        }

        temp = this.inicio;
        anterior = null;
        if (idPiezas[index] == 0) {
            while (temp != null) {
                int idNum = Integer.parseInt(temp.getId().substring(1));

                for (int n : idPiezas) {
                    if (idNum == n) {
                        flag = true;
                        break;
                    }else{
                        flag = false;
                    }
                }

                if (flag) {
                    anterior = temp;
                    temp = temp.getNext();
                }else {
                    idPiezas[index] = idNum;
                    if (temp == this.inicio) {
                        this.inicio = temp.getNext();
                    }else{
                        anterior.setNext(temp.getNext());
                    }
                    notifyAll();
                    return temp;
                }
            }
        }else{
            int conjunto = idPiezas[index];
            while (temp != null) {
                int idNum = Integer.parseInt(temp.getId().substring(1));
                if (idNum == conjunto) {
                    if(temp == this.inicio){
                        this.inicio = temp.getNext();
                    }else {
                        anterior.setNext(temp.getNext());
                    }
                    notifyAll();
                    return temp;
                }
                anterior = temp;
                temp = temp.getNext();
            }
        }
        notifyAll();
        return null;
    }

    public synchronized boolean isEmpty(){
        if(inicio==null){ return true; }
        else{ return false; }
    }

    public synchronized void imprimirLista(){
        if(isEmpty()){ System.out.println("La lista esta vacia"); }
        else{
            Nodo temp=inicio;
            while(temp!=null){
                System.out.println("Id: "+temp.getId()+"\tColor: "+temp.getColor()+"\tDescripcion: "+temp.getDescripcion());
                temp=temp.getNext();
            }
        }
    }
}
