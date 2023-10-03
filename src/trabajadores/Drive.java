package trabajadores;

public class Drive {
    public int script;
    public int level;
    public int sprite;
    public int system;
    public int dlc;

    public Drive() {
        this.script = 0;
        this.level = 0;
        this.sprite = 0;
        this.system = 0;
        this.dlc = 0;
    }
    
    public void addToDrive(String type, int cantidadPartes){
        switch(type){
            case "guion": 
                if((this.script + cantidadPartes)<=25){
                    this.script +=cantidadPartes;
                    System.out.println("guiones hechos: " + cantidadPartes);
                }else{
                    
                    System.out.println("Excede la capacidad del drive de guiones: " + this.script);
                }
                break;
                
            case "sprite": 
                
                if((this.sprite + cantidadPartes)<=55){
                    this.sprite +=cantidadPartes;
                    System.out.println("sprites hechos: " + cantidadPartes);
                }else{
                    
                    System.out.println("Excede la capacidad del drive de sprites: " + this.sprite);
                }
                break;
                
            case "nivel":
                
                if((this.level + cantidadPartes)<=20){
                    this.level +=cantidadPartes;
                    System.out.println("Niveles hechos: " + cantidadPartes);
                }else{
                    
                    System.out.println("Excede la capacidad del drive de niveles: " + this.level);
                }
                break;
                
            case "programador":
                if((this.system + cantidadPartes)<=35){
                    this.system +=cantidadPartes;
                    System.out.println("Sistemas hechos: " + cantidadPartes);
                }else{
                    
                    System.out.println("Excede la capacidad del drive de sistemas: " + this.system);
                }
                break;
                
            case "dlc":
                if((this.dlc + cantidadPartes)<=10){
                    this.dlc +=cantidadPartes;
                    System.out.println("DLCs hechos: " + cantidadPartes);
                }else{
                    
                    System.out.println("Excede la capacidad del drive de DLCs: " + this.dlc);
                }
                break;
                
        }
        
    }
}
