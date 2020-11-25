package cn.madeai;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by <a href="mailto:huangyebiaoke@outlook.com">huang</a> on 2020/11/24 15:51
 */
public class Main extends JFrame implements KeyListener {
//    public static Vector goal=new Vector(Config.width/2,0+Config.goalRadius);
    Population population;
//    boolean exit=false;
    public Main() throws HeadlessException {
        population=new Population(1000);

        this.setTitle("SmartDot");
        this.setBounds(300,300,Config.width,Config.height);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        align center to screen
        this.setLocationRelativeTo(null);
        DrawPanel dp=new DrawPanel();
        this.getContentPane().add(dp);

        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                dp.repaint();
//                if (exit){
//                    timer.cancel();
//                }
//                30fps
            }
        },0,1000/30);


    }

    class DrawPanel extends JPanel{
        @Override
        public void paint(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
//            super.paint(g);
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
//            paint goal
            g.setColor(Color.RED);
            g.fillOval((int)Config.goalPosition.x-Config.goalRadius,(int)Config.goalPosition.y-Config.goalRadius,Config.goalRadius*2,Config.goalRadius*2);
//            paint some info massage
            g.drawString("Gen:"+Population.generation+", prev min step:"+Population.minStep,0,10);
//            paint obstacles
            for (int[] obs:Config.obstacles) {
                g.fillRect(obs[0],obs[1],obs[2]-obs[0],obs[3]-obs[1]);
            }

            if (population.isAllDotsDead()){
                population.calculateFitness();
                population.naturalSelection();
                population.mutateAllBabies();
            }else{
                population.update();
                population.show(g);
            }
        }
    }

    public static void main(String[] args) {
        Main main=new Main();
        main.addKeyListener(main);
//        Vector a=new Vector(1,2);
//        a.add(new Vector(2,2));
//        System.out.println(a.x+" "+a.y);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_SPACE){
//            todo: pause the game
            Config.goalPosition.x=Math.random()*Config.width;
            Config.goalPosition.y=0+Config.goalRadius+Math.random()*50;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
