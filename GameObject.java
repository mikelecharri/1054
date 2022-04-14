import java.awt.*;
import java.io.*;
import java.util.*;


public class GameObject
{
   //data of file 
   private int[][] data = new int[9][9];
   //positions
   private int x;
   private int y;
   
   
   public GameObject()
   {
        
     
   
   
   }
   //get data of level
   public int[][] getlvl()
   {
   
      return data;
   }
   //check to see if particular square given what will be defined as
   public int draw(int data)
   {
     //background
     if(data==0)
     {
      return 0;
     }
     //wall
     if(data==1)
     {
      return 1;
     }
     //victory
     if(data==2)
     {
      return 2;
     }
     //else
     return 1;
            
   }
   
}