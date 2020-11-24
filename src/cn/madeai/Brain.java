package cn.madeai;

import java.util.Random;

/**
 * Created by <a href="mailto:huangyebiaoke@outlook.com">huang</a> on 2020/11/24 15:51
 */
public class Brain {
    int step=0;
    Vector[] directions;

    public Brain(int size) {
        directions = new Vector[size];
        randomize();
    }
    private void randomize(){
        for (int i = 0; i < directions.length; i++) {
            directions[i]=Vector.fromAngle(Math.random()*Math.PI*2);
        }
    }
    @Override
    public Brain clone(){
        Brain clone=new Brain(directions.length);
        for (int i = 0; i < directions.length; i++) {
            clone.directions[i]=directions[i];
        }
        return clone;
    }
    public void mutate(){
        double mutationRate=.01;
        for (int i = 0; i < directions.length; i++) {
            if (Math.random()<mutationRate){
                directions[i]=Vector.fromAngle(Math.random()*Math.PI*2);
            }
        }
    }
}
