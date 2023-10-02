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
                this.script +=cantidadPartes;
                System.out.println("guiones hechos: " + cantidadPartes);
                break;
            case "sprite": 
                this.sprite +=cantidadPartes;
                System.out.println("sprites hechos: " + cantidadPartes);
                break;
            case "nivel":
                this.level +=cantidadPartes;
                break;
            case "programador":
                this.system +=cantidadPartes;
                break;
            case "dlc":
                this.dlc +=cantidadPartes;
                break;
                
        }
        
    }
}
