package trabajadores;
import trabajadores.Game;

public class Drive {
    public int script;
    public int level;
    public int sprite;
    public int system;
    public int dlc;
    public int standardGame;
    public int dlcGame;
    public boolean hasScripts;
    public boolean hasLevels;
    public boolean hasSprites;
    public boolean hasSystems;
    public boolean hasDLCs;
    public int gameParts;
    

    public Drive() {
        this.script = 0; 
        this.level = 0;
        this.sprite = 0;
        this.system = 0;
        this.dlc = 0;
        this.standardGame = 0;
        this.dlcGame = 0;
        this.hasScripts = false;
        this.hasLevels = false;
        this.hasSprites = false;
        this.hasSystems = false;
        this.hasDLCs = false;
        this.gameParts = 0;
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
            case "integrador":
                if(cantidadPartes == 5)
                {
                    this.dlcGame += 1;
                    this.gameParts = 0;
                    this.hasScripts = false;
                    this.hasLevels = false;
                    this.hasSprites = false;
                    this.hasSystems = false;
                    this.hasDLCs = false;
                    System.out.println("Juegos DLC: " + this.dlcGame);
                }else if(cantidadPartes == 4)
                {
                    this.standardGame+= 1;
                    this.gameParts = 0;
                    this.hasScripts = false;
                    this.hasLevels = false;
                    this.hasSprites = false;
                    this.hasSystems = false;
                    this.hasDLCs = false;
                    System.out.println("Juegos standard: " + this.standardGame);
                }
        }
    
    }
    

    public int glueParts(Game game)
    {
        if(this.hasScripts == false)
        {
            if(this.script >= game.getNeededScripts())
            {
                this.gameParts += 1;
                this.script -= game.getNeededScripts();
                this.hasScripts = true;
                System.out.println("Scripts left " + this.script);
            }
        };
        
        if(this.hasLevels == false)
        {
            if(this.level >= game.getNeededLevels())
            {
                this.gameParts += 1;
                this.level -= game.getNeededLevels();
                this.hasLevels = true;
            }
        };
        
        if(this.hasSprites == false)
        {
            if(this.sprite >= game.getNeededSprites())
            {
                this.gameParts += 1;
                this.sprite -= game.getNeededSprites();
                this.hasSprites = true;
            }
        };
        
        if(this.hasSystems == false)
        {
            if(this.system >= game.getNeededSystems())
            {
                this.gameParts += 1;
                this.system -= game.getNeededSystems();
                this.hasSystems = true;
            }
        };
        
        if(this.hasDLCs == false)
        {
            if(this.standardGame >= game.getNeededStandardGames())
            {
                this.gameParts += 1;
                this.dlc -= game.getNeededDLCs();
                this.hasDLCs = true;
            }
        };
        
        return this.gameParts;
    }   
    





}
            

   
