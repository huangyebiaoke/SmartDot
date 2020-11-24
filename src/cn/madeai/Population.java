package cn.madeai;

import java.awt.*;

/**
 * Created by <a href="mailto:huangyebiaoke@outlook.com">huang</a> on 2020/11/24 15:51
 */
public class Population {
    Dot[] dots;
    double fitnessSum=0;
    int generation=1;
//    the index of best dot in dots arr;
    int bestDot=0;
    int minStep=1000;

    public Population(int size) {
        this.dots = new Dot[size];
        for (int i = 0; i < size; i++) {
            dots[i]=new Dot();
        }
    }

    public void show(Graphics g){
        for (int i = 0; i < dots.length; i++) {
            dots[i].show(g);
        }
    }

    public void update(){
        for (int i = 0; i < dots.length; i++) {
            if (dots[i].brain.step>minStep){
                dots[i].dead=true;
            }else {
                dots[i].update();
            }
        }
    }
    public void calculateFitness(){
        for (int i = 0; i < dots.length; i++) {
            dots[i].calculateFitness();
        }
    }

    public boolean isAllDotsDead(){
        for (int i = 0; i < dots.length; i++) {
            if (!dots[i].dead&&!dots[i].reachGoal){
                return false;
            }
        }
        return true;
    }

    public void calculateFitnessSum(){
        for (int i = 0; i < dots.length; i++) {
            fitnessSum+=dots[i].fitness;
        }
    }


}
