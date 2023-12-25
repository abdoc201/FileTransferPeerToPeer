package com;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) throws Exception {
        Login LoginFrame = new Login();
        setVisibility(LoginFrame);
        
    }
    
    public static <T extends JFrame> void setVisibility(T obj){
        obj.setVisible(true);
        obj.setResizable(false);
        obj.setLocationRelativeTo(null);
    }
}