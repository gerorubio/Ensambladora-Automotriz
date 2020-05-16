class Auto {
    private Nodo[] auto;
    private Auto next; 

    public Auto(){
        this.next=null;
    }

    public Auto(Nodo[] auto){
        this.auto=auto;
        this.next=null;
    }

    public void setAuto(Nodo[] automovil){
        this.auto=automovil;
    }

    public void setNext(Auto next){
        this.next=next;
    }

    public Nodo[] getAutomovil(){
        return auto;
    }

    public Auto getNextAuto(){
        return next;
    }
    
}