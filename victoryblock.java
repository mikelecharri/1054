import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
//import java.util.Random;


public class victoryblock extends GameObject
{
   private static int victorycount=0;
   public victoryblock()
   {
   
      
   
   }
   public boolean areYouWinningSon(int box[][],int squarePositionX, int squarePositionY)
   {
      int px=0;
      int py=0;
      
      //calculator of square
      
         px=squarePositionX;
         py=squarePositionY;
         //left
         if(box[(py-16)/25][(px-17)/25]==2 || box[(py-5)/25][(px-17)/25]==2 || box[(py+5)/25][(px-17)/25]==2 && victorycount==0)
         {
            victorycount++;
            
            return true;
            
         }
         //right
         else if(box[(py-16)/25][(px+7)/25]==2 || box[(py-5)/25][(px+7)/25]==2 || box[(py+5)/25][(px+7)/25]==2 && victorycount==0)
         {
            victorycount++;
            /*JOptionPane.showInternalMessageDialog(null, "You won!!!",
   
           "Who won???", JOptionPane.INFORMATION_MESSAGE);
           System.exit(1);*/
            return true;
            
         }
         //bottom
         else if(box[(py+8)/25][(px-16)/25]==2 || box[(py+8)/25][(px-5)/25]==2 || box[(py+8)/25][(px+6)/25]==2 && victorycount==0)
         {
            victorycount++;
            /*JOptionPane.showInternalMessageDialog(null, "You won!!!",
   
           "Who won???", JOptionPane.INFORMATION_MESSAGE);
           System.exit(1);*/
            return true;
         }
         //top
         else if(box[(py-18)/25][(px-16)/25]==2 || box[(py-18)/25][(px-5)/25]==2 || box[(py-18)/25][(px+6)/25]==2 && victorycount==0)
         {
            victorycount++;
           /* JOptionPane.showInternalMessageDialog(null, "You won!!!",
   
           "Who won???", JOptionPane.INFORMATION_MESSAGE);
           System.exit(1);*/
            return true;
         }
         else
         {
            return false;
         }
         
         
   }
}