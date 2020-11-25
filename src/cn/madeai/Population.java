package cn.madeai;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Created by <a href="mailto:huangyebiaoke@outlook.com">huang</a> on 2020/11/24 15:51
 */
public class Population {
    Dot[] dots;
    double fitnessSum=0;
    public static int generation=1;
//    the index of best dot in dots arr;
    int bestDot=0;
//    the bestDot's step
    public static int minStep=1000;

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
//        set fitnessSum to zero, or it will be max
        fitnessSum=0;
        for (int i = 0; i < dots.length; i++) {
            fitnessSum+=dots[i].fitness;
        }
    }

    Dot selectParent(){
//        rand between 0-1
//        double rand=new Random((int)fitnessSum).nextDouble();
        double rand=Math.random()*fitnessSum;
        double runningFitnessSum=0;
        for (int i = 0; i < dots.length; i++) {
            runningFitnessSum+=dots[i].fitness;
            if (runningFitnessSum>rand){
                return dots[i];
            }
        }
        //            System.out.println("runningFitnessSum:"+runningFitnessSum+" randFitnessSum:"+rand);
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
//            if (generation<50){
//                try {
//                    WriteFile.write("D:\\2.txt",minStep+"\n");
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            System.out.println("minStep:"+minStep);
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
