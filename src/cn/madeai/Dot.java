package cn.madeai;

import java.awt.*;

/**
 * Created by <a href="mailto:huangyebiaoke@outlook.com">huang</a> on 2020/11/24 15:51
 */
public class Dot {
    boolean dead=false;
    boolean reachGoal=false;
    boolean isBest=false;
    double fitness=.0;
    Vector position;
    Vector accelerate;
    Vector velocity;
    Brain brain;

    public Dot() {
        brain=new Brain(1000);
        position = new Vector(Config.width/2,Config.height-Config.dotRadius-Config.barHeight);
        accelerate=new Vector(0,0);
        velocity=new Vector(0,0);
    }

    public void move(){
        if (brain.step<brain.directions.length){
            accelerate=brain.directions[brain.step];
            brain.step++;
        }else {
            dead=true;
        }
        velocity.add(accelerate);
        velocity.limit(5);
        position.add(velocity);
    }

    public void show(Graphics g){
        g.setColor(isBest?Color.CYAN:Color.BLACK);
        g.fillOval((int)position.x-Config.dotRadius,(int)position.y-Config.dotRadius,Config.dotRadius*2,Config.dotRadius*2);
    }

    public void update(){
        if (!dead&&!reachGoal){
            move();
            if (position.x<2|| position.y<2|| position.x>Config.width-2|| position.y>Config.height-2){
                dead=true;
            }else if (Vector.dist(position,new Vector(2.0,2.0))<Config.dotRadius+Config.dotRadius){
                reachGoal=true;
            }
//            todo: add some obstacles
        }
    }
    public void calculateFitness(){
        if (reachGoal){
            fitness=1/16.0+10000/Math.pow(brain.step,2);
        }else {
            double distanceToGoal=Vector.dist(position,new Vector(2.0,2.0));
            fitness=1/ Math.pow(distanceToGoal,2);
        }
    }

    public Dot cloneBaby(){
        Dot baby=new Dot();
        baby.brain=brain.clone();
        return baby;
    }
}
