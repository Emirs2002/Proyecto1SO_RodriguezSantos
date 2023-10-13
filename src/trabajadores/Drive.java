package trabajadores;

/**
 *
 * @author emirs
 */
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
    public int hasStandard;
    public int gameParts;
    public int standardPrice;
    public int dlcPrice;
    public InterfazDrive interfaz;

    public Drive(int standardPrice, int dlcPrice, javax.swing.JTextField guiones, javax.swing.JTextField sprites, javax.swing.JTextField niveles, javax.swing.JTextField sistemas, javax.swing.JTextField dlcs, javax.swing.JTextField standardGames, javax.swing.JTextField dlcGames) {
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
        this.hasStandard = 0;
        this.gameParts = 0;
        this.standardPrice = standardPrice;
        this.dlcPrice = dlcPrice;
        this.interfaz = new InterfazDrive(guiones, sprites, niveles, sistemas, dlcs, standardGames, dlcGames);
        
    }
    
    public void addToDrive(String type, int cantidadPartes){
        switch(type){
            case "guion": 
                if((this.script + cantidadPartes)<=25){
                    this.script +=cantidadPartes;
                    this.interfaz.guiones.setText(Integer.toString(this.script));
                }else{
                    
                    System.out.println("Excede la capacidad del drive de guiones: " + this.script);
                }
                break;
                
            case "sprite": 
                
                if((this.sprite + cantidadPartes)<=55){
                    this.sprite +=cantidadPartes;
                    this.interfaz.sprites.setText(Integer.toString(this.sprite));
                }else{
                    
                    System.out.println("Excede la capacidad del drive de sprites: " + this.sprite);
                }
                break;
                
            case "nivel":
                
                if((this.level + cantidadPartes)<=20){
                    this.level +=cantidadPartes;
                    this.interfaz.niveles.setText(Integer.toString(this.level));
                }else{
                    
                    System.out.println("Excede la capacidad del drive de niveles: " + this.level);
                }
                break;
                
            case "programador":
                if((this.system + cantidadPartes)<=35){
                    this.system +=cantidadPartes;
                    this.interfaz.sistemas.setText(Integer.toString(this.system));
                }else{
                    
                    System.out.println("Excede la capacidad del drive de sistemas: " + this.system);
                }
                break;
                
            case "dlc":
                if((this.dlc + cantidadPartes)<=10){
                    this.dlc +=cantidadPartes;
                    this.interfaz.dlcs.setText(Integer.toString(this.dlc));
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
                    this.interfaz.dlcGame.setText(Integer.toString(this.dlcGame));
                    
                    System.out.println("Juegos DLC hechos: " + this.dlcGame);
                }else if(cantidadPartes == 4)
                {
                    this.standardGame+= 1;
                    this.gameParts = 0;
                    this.hasScripts = false;
                    this.hasLevels = false;
                    this.hasSprites = false;
                    this.hasSystems = false;
                    this.hasDLCs = false;
                    this.interfaz.standardGame.setText(Integer.toString(this.standardGame));
                    this.hasStandard += 1;
                    System.out.println("Juegos standard hechos: " + this.standardGame);
                }
        }
    
    }
    
//para integrador

    public int glueParts(Game game) //requerimientos de cada recurso para crear el juego
    {
        if(this.hasScripts == false)
        {
            if(this.script >= game.getNeededScripts())
            {
                this.gameParts += 1;
                this.script -= game.getNeededScripts();
                this.hasScripts = true;
            }
        }
        
        if(this.hasLevels == false)
        {
            if(this.level >= game.getNeededLevels())
            {
                this.gameParts += 1;
                this.level -= game.getNeededLevels();
                this.hasLevels = true;
            }
        }
        
        if(this.hasSprites == false)
        {
            if(this.sprite >= game.getNeededSprites())
            {
                this.gameParts += 1;
                this.sprite -= game.getNeededSprites();
                this.hasSprites = true;
            }
        }
        
        if(this.hasSystems == false)
        {
            if(this.system >= game.getNeededSystems())
            {
                this.gameParts += 1;
                this.system -= game.getNeededSystems();
                this.hasSystems = true;
            }
        }
        
        if(this.hasDLCs == false)
        {
            if(this.hasStandard >= game.getNeededStandardGames() && this.dlc >= game.getNeededDLCs() ) //restriccion numero de juegos standard necesitados antes de anadir dlc
            {
                this.gameParts += 1;
                this.dlc -= game.getNeededDLCs();
                this.hasDLCs = true;
                this.hasStandard = 0;
               
            }
        }
        
        return this.gameParts;
    }   
    

//para director
public int launchGames(){ 
    
    //calcular ganancia de los juegos
    
    int standardGameBenefit = this.standardGame * this.standardPrice;
    int dlcGameBenefit = this.dlcGame * this.dlcPrice;
    int totalBenefit = standardGameBenefit + dlcGameBenefit;
    
    //enviar juegos a tiendas
    this.standardGame = 0;
    this.dlcGame = 0;
    
    return totalBenefit;
}


}
            

   
