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
//    the bestDot's step
    int minStep=1000;

    public Population(int size) {
        this.dots = new Dot[size];
        for (int i = 0; i < size; i++) {
            dots[i]=new Dot();
        }
    }

    void show(Graphics g){
        for (int i = 1; i < dots.length; i++) {
            dots[i].show(g);
        }
//        avoid the bestDot covered by other dots
        dots[0].show(g);
    }

    void update(){
        for (int i = 0; i < dots.length; i++) {
            if (dots[i].brain.step>minStep){
                dots[i].dead=true;
            }else {
                dots[i].update();
            }
        }
    }
    void calculateFitness(){
        for (int i = 0; i < dots.length; i++) {
            dots[i].calculateFitness();
        }
    }

    boolean isAllDotsDead(){
        for (int i = 0; i < dots.length; i++) {
            if (!dots[i].dead&&!dots[i].reachGoal){
                return false;
            }
        }
        return true;
    }

    void calculateFitnessSum(){
        for (int i = 0; i < dots.length; i++) {
            fitnessSum+=dots[i].fitness;
        }
    }

    Dot selectParent(){
        double rand=Math.random()*fitnessSum;
        double runningFitnessSum=0;
        for (int i = 0; i < dots.length; i++) {
            runningFitnessSum+=dots[i].fitness;
            if (runningFitnessSum>rand){
                return dots[i];
            }
        }
        return null;
    }

    void mutateAllBabies(){
        for (int i = 0; i < dots.length; i++) {
            dots[i].brain.mutate();
        }
    }
    void setBestDot(){
        double maxFitness=0;
        for (int i = 0; i < dots.length; i++) {
            if (dots[i].fitness>maxFitness){
                maxFitness=dots[i].fitness;
                bestDot=i;
            }
        }
        if (dots[bestDot].reachGoal){
            minStep=dots[bestDot].brain.step;
            System.out.println("minStep:"+minStep);
        }
    }

    void naturalSelection(){
        Dot[] babyDots=new Dot[dots.length];
        setBestDot();
        calculateFitnessSum();

        babyDots[0]=dots[bestDot].cloneBaby();
        babyDots[0].isBest=true;
        for (int i = 1; i < babyDots.length; i++) {
            Dot parent=selectParent();
            babyDots[i]=parent.cloneBaby();
        }
        dots=babyDots.clone();
        generation++;
    }


}
