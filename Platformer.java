import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Platformer extends JFrame
{

   
   //make contents
   Container contents;
   
   
   
   public Platformer()
   {
      //super
      super("Platformer");
      //set contents
      contents = getContentPane();
      //name of starting file
      String name = "1.txt";
      //instance of levelPanel
      GamePanel g = new GamePanel(name);
      //adding instance to frame
      contents.add(g);
      //size
      setSize(827,650);
      //visibility
      setVisible(true);
      //focus on object
      g.requestFocus();
       
   
   }
   
   public static void main(String[] args)
   {
      //frame
      Platformer theFrame = new Platformer();
      //exit upon clicking X
      theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
   }
}