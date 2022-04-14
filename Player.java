
public class Player extends GameObject
{
   //check if can jump or not
   private boolean canjump;

   public Player()
   {
      super();
   
   
   }
   //checking if player able to go to left
   public boolean left(int box[][],int squarePositionX, int squarePositionY)
   {
         //easier variable names
         int px=0;
         int py=0;
         //set to position
         px=squarePositionX;
         py=squarePositionY;
         //check if left ofplayer in array is 1
         if(box[(py-16)/25][(px-18)/25]==1 || box[(py-5)/25][(px-18)/25]==1 || box[(py+5)/25][(px-18)/25]==1)
         {
            
            return true;
            
         }
         else
         {
            return false;
         }
         
   }
   //checking if player able to go to right
   public boolean right(int box[][],int squarePositionX, int squarePositionY)
   {
         //easier variable names
         int px=0;
         int py=0;
         //set to position
         px=squarePositionX;
         py=squarePositionY;
         //check if right of player is 1
         if(box[(py-16)/25][(px+8)/25]==1 || box[(py-5)/25][(px+8)/25]==1 || box[(py+5)/25][(px+8)/25]==1)
         {
            
            return true;
            
         }
         else
         {
            return false;
         }
         
   }
   //checking if player is on ground
   public boolean isOnGround(int box[][],int squarePositionX, int squarePositionY)
   {
         //easier variable names
         int px=0;
         int py=0;
         //set to position
         px=squarePositionX;
         py=squarePositionY;
         //check if below player is 1
         if(box[(py+7)/25][(px-16)/25]==1 || box[(py+7)/25][(px-5)/25]==1 || box[(py+7)/25][(px+6)/25]==1)
         {
            //allowed to jump
            canjump=true;
            return true;
            
         }
         else
         {
            canjump=false;         
            return false;
         }
      
     
   }
   //checking if player is hitting roof
   public boolean isOnRoof(int box[][],int squarePositionX, int squarePositionY)
   {
         //easier variable names
         int px=0;
         int py=0;
         //set position
         px=squarePositionX;
         py=squarePositionY;
         //check to see if above player is 1
         if(box[(py-20)/25][(px-16)/25]==1 || box[(py-20)/25][(px-5)/25]==1 || box[(py-20)/25][(px+6)/25]==1)
         {
            //not allowed to jump
            canjump=false;
            return true;
            
         }
         else
         {
            return false;
         }
      
     
   }

   //return if can jump
   public boolean canjump()
   {
      return canjump;
   }
   
}