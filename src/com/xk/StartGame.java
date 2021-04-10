package com.xk;

import javax.swing.*;

/**
 * @author 书
 * @date 2021/4/8 - 10:39
 */
public class StartGame {
    public static void main(String[] args) {
        /**
         * 生成一个静态窗口
         */
        JFrame frame = new JFrame("贪吃蛇");
        //设置界面大小，前两位是窗口显示位置，后两位窗口大小
        frame.setBounds(500,190,900,720);
        //设置界面不可变大变小
        frame.setResizable(false);
        //设置窗口可见
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /**
         * 面板 JPanel
         */
        frame.add(new GamePanel());
    }

}
