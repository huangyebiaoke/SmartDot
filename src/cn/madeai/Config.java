package cn.madeai;

/**
 * Created by <a href="mailto:huangyebiaoke@outlook.com">huang</a> on 2020/11/24 15:51
 */
public class Config {
    final static int barHeight=40;
    final static int width=600;
    final static int height=600;
    final static int dotRadius=4;
    final static int goalRadius=5;
    static Vector goalPosition=new Vector(Config.width/2,0+Config.goalRadius);
    final static int[][] obstacles={
            {0,200-barHeight,400,200-barHeight+20},
            {200,400-barHeight,600,400-barHeight+20}
    };
}
