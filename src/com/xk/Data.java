package com.xk;

import javax.swing.*;
import java.net.URL;

/**
 * @author 书
 * @date 2021/4/8 - 10:38
 */
public class Data {
    /**
     * 顶部图片
     */
    public static URL headreURL =  Data.class.getResource("/static/header.png");
    public static ImageIcon header =  new ImageIcon(headreURL);

    /**
     * 头部图片，上下左右
     * 身体和事物
     */
    public static URL upURL =  Data.class.getResource("/static/up.png");
    public static ImageIcon up =  new ImageIcon(upURL);

    public static URL downURL =  Data.class.getResource("/static/down.png");
    public static ImageIcon down =  new ImageIcon(downURL);

    public static URL leftURL =  Data.class.getResource("/static/left.png");
    public static ImageIcon left =  new ImageIcon(leftURL);

    public static URL rightURL =  Data.class.getResource("/static/right.png");
    public static ImageIcon right =  new ImageIcon(rightURL);

    public static URL bodyURL =  Data.class.getResource("/static/body.png");
    public static ImageIcon body =  new ImageIcon(bodyURL);

    public static URL foodURL =  Data.class.getResource("/static/food.png");
    public static ImageIcon food =  new ImageIcon(foodURL);
}
