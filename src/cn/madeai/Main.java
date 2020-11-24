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
    Vector goal=new Vector(Config.width/2,0+Config.goalRadius);
    Population population;
    boolean exit=false;
    public Main() throws HeadlessException {
        this.setTitle("SmartDot");
        this.setBounds(300,300,Config.width,Config.height);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        align center to screen
        this.setLocationRelativeTo(null);
        DrawPanel dp=new DrawPanel();
        this.getContentPane().add(dp);

        population=new Population(1000);

        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                dp.repaint();
                if (exit){
                    timer.cancel();
                }
            }
        },0,40);


    }

    class DrawPanel extends JPanel{
        @Override
        public void paint(Graphics g) {
//            super.paint(g);
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
//            paint goal
            g.setColor(Color.RED);
            g.fillOval((int)goal.x-Config.goalRadius,(int)goal.y-Config.goalRadius,Config.goalRadius*2,Config.goalRadius*2);

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

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
