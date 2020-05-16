class Bodega {
    private Auto inicio;
    private Auto fin;
    private int capacidad;
    private int ocupado;

    public Bodega(int capacidad){
        this.capacidad=capacidad;
        ocupado=0;        
        inicio=fin=null;
    }

    public synchronized void addAuto(Auto coche){
        if(isEmpty()){
            inicio=coche; 
            fin=coche;
            ocupado++;
        } 
        else{
            if(ocupado<capacidad){
                fin.setNext(coche);
                fin=coche;
                ocupado++;
            } 
            else{
                System.out.println("No se puede almacenar mas autos, la bodega esta llena");
            }
        }
    }

    public boolean isFull(){
        if(inicio == null)
            return false; 
        else{
            if(this.ocupado == this.capacidad)
                return true;
            else
                return false;
        }
    }

    public boolean isEmpty(){
        if(inicio==null){ return true; }
        else{ return false; }
    }

    public synchronized void imprimirLista(){
        if(isEmpty()){ System.out.println("La bodega esta vacia"); }
        else{
            Auto temp=inicio;
            System.out.println("En bodega: ");
            while(temp!=null){
                System.out.print("Automovil: ");
                for(Nodo a : temp.getAutomovil()){
                    System.out.print(a.getId() + " ");
                }
                System.out.println(" ");
                temp=temp.getNextAuto();
            }
        }
    }

}
