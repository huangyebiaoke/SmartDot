package cn.madeai;

import java.util.Map;

/**
 * Created by <a href="mailto:huangyebiaoke@outlook.com">huang</a> on 2020/11/24 15:53
 */
public class Vector {
    public double x;
    public double y;
    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public static Vector fromAngle(double angle){
        return new Vector(Math.cos(angle), Math.sin(angle));
    }
    public static double dist(Vector a,Vector b){
        return Math.sqrt(Math.pow(a.x - b.x,2) + Math.pow(a.y - b.y,2));
    }
    public double abs(){
        return Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
    }
    public void add(Vector other){
        this.x+=other.x;
        this.y+=other.y;
    }

    public void limit(double length) {
        double abs=abs();
        if (abs>length){
            x=length*x/abs;
            y=length*y/abs;
        }
    }
}
