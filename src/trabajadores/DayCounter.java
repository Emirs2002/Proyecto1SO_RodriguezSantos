/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajadores;

/**
 *
 * @author emirs
 */
public class DayCounter {
    private final int deadline;
    private int daysLeft;
    public javax.swing.JTextField deadlineField;

    public DayCounter(int deadline,javax.swing.JTextField deadlineField) {
        this.deadline = deadline;
        this.daysLeft = deadline;
        this.deadlineField = deadlineField;
    }
    
     public void updateCounter(String type){
        
        if (this.daysLeft == 0 && type.equals("director")){
            
            System.out.println("");
            System.out.println("director entra al counter");
            this.daysLeft = this.deadline; 
            System.out.println("NUEVA DEADLINE: " + this.daysLeft);
            
        }else if(this.daysLeft > 0 && type.equals("manager")){
            
            System.out.println("");
            System.out.println("manager entra al counter");
            this.daysLeft--;
            System.out.println("DIAS QUE FALTAN: " + this.daysLeft);
            
        }
        this.deadlineField.setText(Integer.toString(daysLeft));
    }

    public int getDaysLeft() {
        return daysLeft;
    }

    public void setDaysLeft(int daysLeft) {
        this.daysLeft = daysLeft;
    }
     
     
    
}
