class Nodo {
    private String id;
    private String color;
    private String descripcion;
    private Nodo next; 

    public Nodo(){
        this.next=null;
    }

    public Nodo(String id, String color, String descripcion){
        this.id=id;
        this.color=color;
        this.descripcion=descripcion;
        this.next=null;
    }

    public void setInfo(String id, String color, String descripcion){
        this.id=id;
        this.color=color;
        this.descripcion=descripcion;
    }

    public void setNext(Nodo next){
        this.next=next;
    }

    public String getId(){
        return id;
    }

    public String getColor(){
        return color;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public Nodo getNext(){
        return next;
    }
    
}
