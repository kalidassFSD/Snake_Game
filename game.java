import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;



public class game extends JPanel implements ActionListener , KeyListener {
    int height;
    int width;

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
            if(gameOver){
                loop.stop();
            }
    }
    public void move(){
        if(join(snakeStart,food)){
            snakeGrow.add(new box(food.x, food.y));
            randomFood();
        }
        for(int i=snakeGrow.size()-1;i>=0;i--){
            box Part = snakeGrow.get(i);
            if(i==0){
                Part.x =snakeStart.x;
                Part.y = snakeStart.y;
            }
            else{
                box prePart = snakeGrow.get(i-1);
                Part.x=prePart.x ;
                 Part.y=prePart.y ;
            }
        }
        snakeStart.x +=vX;
        snakeStart.y +=vY;
        for(int i=0;i<snakeGrow.size();i++){
            box Part =  snakeGrow.get(i);
            if(join(snakeStart,Part)){
                gameOver=true;
            }
        }
        if(snakeStart.x*tsize<0 ||snakeStart.x*tsize>width ||snakeStart.y*tsize<0 ||snakeStart.y*tsize>height ){
            gameOver=true;
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_UP && vY!=1)
        {
            vX=0;
            vY=-1;
        }
        else if(e.getKeyCode()==KeyEvent.VK_DOWN && vY!=-1){
            vX=0;
            vY=1;
        } else if (e.getKeyCode()==KeyEvent.VK_LEFT && vX!=1) {
            vX=-1;
            vY=0;
        }
        else if (e.getKeyCode()==KeyEvent.VK_RIGHT && vX!=-1){
            vX=1;
            vY=0;
        }
    }



    @Override
    public void keyTyped(KeyEvent e) {}



    @Override
    public void keyReleased(KeyEvent e) {}


    private class box{
        int x;
        int y;
        box(int x,int y){
            this.x=x;
            this.y=y;
        }
    }


    box snakeStart;
    box food;
    ArrayList<box> snakeGrow;
    Random random;

    Timer loop;
    int vX;
    int vY;
    boolean gameOver=false;
    int tsize = 20;
     game(int height,int width){
        this.width=width;
        this.height=height;
         setPreferredSize(new Dimension(this.width,this.height));
         setBackground(Color.black);

         addKeyListener(this);
         setFocusable(true);
         snakeStart = new box(7,7);
         food= new box(14,14);
         snakeGrow = new ArrayList<box>();

         vX=0;
         vY=0;
         random = new Random();
         randomFood();
         loop = new Timer(100,this);
         loop.start();

    }


     public void paint(Graphics g){
         super.paint(g);
         color(g);
     }
     public void color(Graphics g){
       /*  for (int i = 0; i < width/tsize; i++) {
                g.drawLine(0,i*tsize,width,i*tsize);
                g.drawLine(i*tsize,0,i*tsize,height);
         }*/
         g.setColor(Color.blue);
       //  g.fillRect(snakeStart.x*tsize,snakeStart.y*tsize,tsize,tsize);
         g.fill3DRect(snakeStart.x*tsize,snakeStart.y*tsize,tsize,tsize,true);

         g.setColor(Color.ORANGE);
         //g.fillRect(food.x*tsize,food.y*tsize,tsize,tsize);
         g.fill3DRect(food.x*tsize,food.y*tsize,tsize,tsize,true);

         for (int i = 0; i < snakeGrow.size(); i++) {
             box snakePart = snakeGrow.get(i);
             g.setColor(Color.magenta);
            // g.fillRect(snakePart.x*tsize,snakePart.y*tsize,tsize,tsize);
             g.fill3DRect(snakePart.x*tsize,snakePart.y*tsize,tsize,tsize,true);

         }

         g.setFont(new Font("Times New Roman",Font.LAYOUT_LEFT_TO_RIGHT,25));

         if(gameOver){
             g.setColor(Color.RED);

             g.drawString("Game Over :: " + String.valueOf(snakeGrow.size()),tsize-11 ,tsize);
         }
         else{
             g.drawString("Game Score :: " + String.valueOf(snakeGrow.size()),tsize-11 ,tsize);
         }


     }
public void randomFood(){
         food.x= random.nextInt(width/tsize);
         food.y =random.nextInt(height/tsize);
}
    public boolean join(box t1 ,box t2){
        return t1.x == t2.x &&  t1.y == t2.y ;
    }


}
