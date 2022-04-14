import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel 
{
   //2d array for squares
   int [][] box;
   //file name
   String file;
   //file reader instance
   Reader r;
   //player instance
   Player p = new Player();
   //gravity
   private int countfall;
   //counter
   private int count;
   public GamePanel(String name)
   {
      //starting file name
      file = name;
      //setting 2d array
      
      //keylistenerfor keyboard
      addKeyListener(new KeyEventDemo());
      //preferred size
      setPreferredSize(new Dimension(800,600));
      //focus
      setFocusable(true);
      //timer for smooth movement
      Timer t = new Timer(10, new TimeListener());
      //instance reader file
      r = new Reader(file);
      //square position translated from 2d array position
      squarePositionX = ((squarePositionX+r.getx()*25)+12);
      squarePositionY = ((squarePositionY+r.gety()*25)+12);
      
      
      //start timer
      t.start();
      
   }
   
   //x coordinate
   int squarePositionX=5;
   //y coordinate
   int squarePositionY=5;
   //instance victory
   victoryblock vic = new victoryblock();
   
   public void paintComponent(Graphics g)
   {
      
      //paint component
      super.paintComponent(g);
      //adding level instance
      
      //Reader r = new Reader(file);
      box = new int[r.getrow()][r.getcolumn()];
      //get 2d array
      box = r.getlvl();
      //background
      g.setColor(Color.BLACK);
      g.fillRect(0,0,815,615);
      g.setColor(Color.GRAY);
      g.fillRect(5,5,800,600);
      //populate map
      GameObject go = new GameObject();
      for(int i=0; i<r.getcolumn(); i++)
         {
            for(int j=0; j<r.getrow(); j++)
            {
               int x = i;
               int y = j;
               x = x*25;
               y = y*25;
               //if 0 color = cyan
               
               if(go.draw(box[j][i])==0)
               {
                  g.setColor(Color.GRAY);
                  g.drawRect(x+5, y+5, 25, 25);
                  g.fillRect(x+5, y+5, 25, 25);
                  
               }
               //if 1 color = black
               if(go.draw(box[j][i]) == 1)
               {
                  g.setColor(Color.CYAN);
                  g.drawRect(x+5, y+5, 25, 25);
                  g.fillRect(x+5, y+5, 25, 25);
                  g.setColor(Color.BLACK);
                  g.fillRect(x+5+6, y+5+6, 12, 12);
                  
                  
               }
               //if 2 victory block
               if(go.draw(box[j][i]) == 2)
               {
                  g.setColor(Color.MAGENTA);
                  g.drawRect(x+5, y+5, 25, 25);
                  g.fillRect(x+5, y+5, 25, 25);
                  
               }
               
                  
            }
         }
     
      count=count+1;
      //if player is not on ground
      if(!p.isOnGround(box, squarePositionX, squarePositionY))
      {
         //already jumped variable
         jumpedonce=true;
         //falling starts
         falling=true;
      }
      //if player is on ground
      if(p.isOnGround(box, squarePositionX, squarePositionY))
      {
            //already jumped = false
            jumpedonce=false;
            //countfall=7;
      }
      //if player is not on ground
      if(!p.isOnGround(box, squarePositionX, squarePositionY) && !jumping)
      {
         //start falling
         falling=true;
         //if gravity is between two numbers keep increasing
         if(countfall<1 && countfall>-7)
         {
            //add to position
            squarePositionY=squarePositionY-countfall;
         }
         //if gravity more than 7 gravity dont go any higher
         else
         {
            //gravity set to 7
            countfall=-7;
            //add to position
            squarePositionY=squarePositionY-countfall;
         }
      }
      //else if player is on ground
      else
      {
         //cannot move down with s key
         down=false;
         //check to see if went through floor and if yes fix position
         if(box[(squarePositionY+6)/25][(squarePositionX-16)/25]==1 || box[(squarePositionY+6)/25][(squarePositionX-5)/25]==1 || box[(squarePositionY+6)/25][(squarePositionX+6)/25]==1)
         {
            squarePositionY=squarePositionY-3;
         }
         //set gravity to 0    
         countfall=0;
      }
      //if player is not hitting roof
      if(!p.isOnRoof(box, squarePositionX, squarePositionY))
      {
         //jumped once = true
         jumpedonce=true;
         
      }
      //if player hit roof
      if(p.isOnRoof(box, squarePositionX, squarePositionY))
      {
         //jumped once = false
         jumpedonce=false;
         //start falling
         falling=true;
         //cannot jump
         jumping=false;
         //jump reset to 7
         jump=7;
           
      }
      //check to see if player can move left
      if(p.left(box, squarePositionX, squarePositionY))
      {
         //if true dont allow a key to move left
         left=false;
      }
      //check to see if player can move right
      if(p.right(box, squarePositionX, squarePositionY))
      {
         //if true dont allow d key to move right
         right=false;
      }
      
      //gold square player
      g.setColor(Color.YELLOW);
      //check to see if on ground
      if(p.isOnGround(box, squarePositionX, squarePositionY) || jump>20)
      {
         //can press w again
         wagain=true;
         //if is jumping
         if(jumping)
         {
            //if jump > 0
            if(jump>0)
            {
               //subtract jump from position
               squarePositionY=squarePositionY-(int)jump;
               
            
            }
            //of jumpless than or equal to 0
            else
            {
               //can press w again
               wagain=true;
               
               
            }
        }
      //reset gravity 
      countfall=0;    
               
               
     }
     //if jump is not 5(which is equivalent to 0) & is jumping
     else if(jump!=5 && jumping)
     { 
         //set gravity to 0
         countfall=0;
         //subtract jump from position
         squarePositionY=squarePositionY-(int)jump;
         //cannot press w again
         wagain=false;
     }
     //check to see if won and hasn't declared victory already
     if(victorycount==0 && vic.areYouWinningSon(box, squarePositionX, squarePositionY))
     {
         //gravity set to 0
         countfall=0;
         //jumpt to 0
         jump=0;
         //cannot press up
         up=false;
         //cannot press down
         down=false;
         //is not jumping
         jumping=false;
         //cannot press w again
         wagain=false;
         //add to victory count
         victorycount++;
         //joptionpane
         JOptionPane.showInternalMessageDialog(null, "You won!!!",
   
         "Who won???", JOptionPane.INFORMATION_MESSAGE);
         //exit system
         System.exit(1);
         
     }
     //draw player
     g.fillRect(squarePositionX-12,squarePositionY-13,25,25);
   }
   //int counter for victory
   int victorycount=0;
   //key listener
   public class KeyEventDemo implements KeyListener
   {
      public void keyTyped(KeyEvent e) {}
      //key when released
      public void keyReleased(KeyEvent e) 
      {
      //when key released make specific boolean false
      if(e.getKeyCode() == KeyEvent.VK_W)
      {
         
            up = false;   
      }
      
      if(e.getKeyCode() == KeyEvent.VK_S)
      {
         down = false;
         
      }
      if(e.getKeyCode() == KeyEvent.VK_A)
      {
         left = false;
         
      }
      if(e.getKeyCode() == KeyEvent.VK_D)
      {
         right = false;
         
      }
    }
    //when key is pressed
    public void keyPressed(KeyEvent e) 
    {
      //when key pressed make specific boolean true
      if(e.getKeyCode() == KeyEvent.VK_W)
      {
         //check to see if on ground before allowing up = true
         if(p.isOnGround(box, squarePositionX, squarePositionY) && !p.isOnRoof(box, squarePositionX, squarePositionY) )
         { 
            up=true;
            //set jump to 7
            jump=7;
         }
         else
         {
            up=false;
         }
            
         
         
      }
      
      if(e.getKeyCode() == KeyEvent.VK_S)
      {
         //if not on ground can press
         if(!p.isOnGround(box, squarePositionX, squarePositionY))
         {
            
            down = true;
         }
         
      }
      if(e.getKeyCode() == KeyEvent.VK_A)
      {
         //if no wall on left
         if(!p.left(box, squarePositionX, squarePositionY))
         {
            
            left = true;
         }
         
      }
      if(e.getKeyCode() == KeyEvent.VK_D)
      {
         //if no wall on right
         if(!p.right(box, squarePositionX, squarePositionY))
         {
            
            right = true;
         }
      }
    }

      
    
   }
   //booleans for key pressed
   boolean up;
   boolean down;
   boolean left;
   boolean right;
   //jumping variable
   float jump=7;
   //checks if jumping
   boolean jumping;
   //checks if falling
   boolean falling=true;
   //checked if already jumped
   boolean jumpedonce=false;
   //if w can be pressed again
   boolean wagain;
   //timer
   public class TimeListener implements ActionListener
   {
      //when boolean = true change position of square
      public void actionPerformed(ActionEvent e)
      {
         //if player falling
         if(falling)
         {
            countfall--;
         }
         //if countfall=-1 turn to -2 to be uniform
         if(countfall==-1)
         {
            countfall=-2;
         }
         //if is jumping
         if(jumping)
         {
            jump-=.1;
         }
         //if jump is 5 cannot jump anymore
         if(jump==5.0)
         {
            jumping=false;
         }
         //if jump somehow is less than 5 then reset
         if(jump<=5.0)
         {
            jump=7;
            jumping = false;
         }
         //if jump is 5 and cannot jump
         if(jump==5.0 && !p.canjump())
         {
            //is jumping make false
            jumping=false;
            //reset jump
            jump=7;
         }
         //if up = true
         if(up)
         {
            //if jump is 7 and w can bepressed
            if(jump==7 && wagain)
            {
               jumping=true;  
            }  
            
         }
         //if down = true
         if(down)
         {
            squarePositionY++;   
            
         }
         //if left is true
         if(left)
         {
            squarePositionX--;
         }
         //if right is true
         if(right)
         {
            squarePositionX++;
         }
         //calls the paint component
         repaint(); 
      }
   }
}