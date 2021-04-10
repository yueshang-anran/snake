package com.xk;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

/**
 * @author 书
 * @date 2021/4/8 - 10:38
 */
public class GamePanel extends JPanel implements KeyListener, ActionListener {
    int length;
    int[] snakeX = new int[600];
    int[] snakeY = new int[600];
    /**
     * 默认向右走
     * R:右边
     * L:左边
     * U：上边
     * D:下边
     */
    String fx = "R";
    /**
     * 游戏是否开始
     */
    boolean isStart = false;
    /**
     * 100毫秒监听一次
     * 1秒十次，定时器
     */
    Timer timer = new Timer(100,this);

    /**
     * 定义食物
     */
    int foodx;
    int foody;
    Random random = new Random();

    /**
     * 死亡判断
     */
    boolean isFail = false;

    /**
     * 积分
     */
    int score;

    public GamePanel() {
        init();
        //实现键盘监听
        this.setFocusable(true);
        this.addKeyListener(this);
        timer.start();
        //初始化食物数据
        foodx = 25 + 25* random.nextInt(34);
        foody = 75 + 25* random.nextInt(24);
    }

    /**
     * 初始化方法
     */
    public void init(){
        length = 3;
        //头部位置
        snakeX[0] = 100;  snakeY[0] = 100;
        //第一节身体
        snakeX[1] = 75;   snakeY[1] = 100;
        //第二节身体
        snakeX[2] = 50;   snakeY[2] = 100;

        score = 0;
    }

    /**
     * 画板:画界面与蛇
     */
    @Override
    protected void paintComponent(Graphics g) {
        //清屏
        super.paintComponent(g);
        this.setBackground(Color.WHITE);

        //头部广告栏
        Data.header.paintIcon(this,g,25,11);
        //游戏区域
        g.fillRect(25,75,850,600);

        //画一条静态的蛇
        if("R".equals(fx)){
            Data.right.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if("L".equals(fx)){
            Data.left.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if("U".equals(fx)){
            Data.up.paintIcon(this,g,snakeX[0],snakeY[0]);
        }else if("D".equals(fx)){
            Data.down.paintIcon(this,g,snakeX[0],snakeY[0]);
        }

        //蛇的长度for循环处理
        for (int i = 1;i < length;i++){
            Data.body.paintIcon(this,g,snakeX[i],snakeY[i]);
        }

        //画积分
        g.setColor(Color.WHITE);
        g.setFont(new Font("微软雅黑",Font.BOLD,18));
        g.drawString("长度:"+length,750,35);
        g.drawString("分数:"+score,750,55);

        //食物的位置
        Data.food.paintIcon(this,g,foodx,foody);

        //游戏是否开始
        if(isStart == false){
            //设置字体
            g.setColor(Color.WHITE);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("按下空格开始",300,300);
        }
        //失败提醒
        if(isFail){
            //设置字体
            g.setColor(Color.RED);
            g.setFont(new Font("微软雅黑",Font.BOLD,40));
            g.drawString("游戏失败,按下空格重新开始",200,300);
        }
    }


    /**
     * 监听键盘输入
     * keyTyped:    键盘按下弹起
     * keyPressed:  键盘按下，未释放
     * keyReleased: 键盘按下，释放后
     */
    @Override
    public void keyPressed(KeyEvent e) {
        //获取按下是哪个键位
        int keyCode =  e.getKeyCode();

        //键盘控制走向
        switch (keyCode){
            case KeyEvent.VK_SPACE:
                if(isFail){
                    isFail = false;
                    init();
                }else {
                    isStart = !isStart;
                }
                //刷新界面
                repaint();
                break;
            case KeyEvent.VK_LEFT: fx = "L";
                break;

            case KeyEvent.VK_RIGHT: fx = "R";
                break;

            case KeyEvent.VK_UP: fx = "U";
                break;

            case KeyEvent.VK_DOWN: fx = "D";
                break;
            default:break;
        }
    }

    /**
     * 监听时间,执行定时操作
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(isStart && isFail==false){
            //开始右移
            for(int i = length - 1;i > 0;i--){
                snakeX[i] = snakeX[i-1];
                snakeY[i] = snakeY[i-1];
            }

            switch (fx){
                case "R": snakeX[0] = snakeX[0] + 25;
                    //判定界面
                    if(snakeX[0] > 850){
                        snakeX[0] = 25;
                    }
                    break;
                case "L":
                    snakeX[0] = snakeX[0]-25;
                    if (snakeX[0]<25){
                        snakeX[0] = 850;
                    }
                    break;
                case "U":
                    snakeY[0] = snakeY[0]-25;
                    if (snakeY[0]<75) {
                        snakeY[0] = 650;
                    }
                    break;
                case "D":
                    snakeY[0] = snakeY[0]+25;
                    if (snakeY[0]>650){
                        snakeY[0] = 75;
                    }
                    break;
                default:break;
            }

            //小蛇吃食物
            if(snakeX[0]==foodx && snakeY[0]==foody){
                length++;
                score += 1;
                //重新生成食物
                foodx = 25 + 25* random.nextInt(34);
                foody = 75 + 25* random.nextInt(24);
            }
            //游戏失败判断
            for(int i = 1;i < length;i++){
                if(snakeX[i]==snakeX[0] && snakeY[i] == snakeY[0]){
                    isFail = true;
                }
            }
            //刷新界面
            repaint();
        }
        timer.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
}
