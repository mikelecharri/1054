import java.awt.*;
import java.io.*;
import java.util.*;


public class Reader
{
   //data of file 
   private int[][] data;
   //side files
   private String[] nextdata = new String[4];
   //for scanning square position
   private int x;
   private int y;
   //row amount for 2d array 
   private int row;
   //column amount for 2d array
   private int column;
   
   public Reader( String name)
   {
      //scanner
      Scanner read = null;
      try
      {
         //set scanner
         read = new Scanner(new File(name));
         //while there is a next line in the file
         //scan 1 and 0
         x = read.nextInt();
         y = read.nextInt();
         row = read.nextInt();
         column = read.nextInt();
         data = new int[row][column];
         //read level with paramters given
         while(read.hasNextInt())
         {
            for(int i =0;i<row;i++)
            {
               for(int j=0;j<column;j++)
               {
                  data[i][j] = read.nextInt();   
               }
            }
         
         }
      }
      //catch file not found
      catch(FileNotFoundException fnfe)
      {
         System.out.println("File not found");   
      
      }

     
   
   
   }
   //returns total row
   public int getrow()
   {
      return row;
   }
   //returns total column
   public int getcolumn()
   {
      return column;
   }
   //get data of level
   public int[][] getlvl()
   {
   
      return data;
   }
   //get data of next files
   public String[] getarea()
   {
   
      return nextdata;
   }
   //gets position x
   public int getx()
   {
      return x;
   }
   //gets position y
   public int gety()
   {
      return y;
   }
}