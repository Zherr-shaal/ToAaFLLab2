/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.toaafllab1;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map.Entry;

/**
 *
 * @author Matt
 */
public class AnalyzerState {
    private final ArrayList<Character> input=new ArrayList<>(Arrays.asList(new Character[]{'a','b','e'}));
    private ArrayList<String> out;
    private ArrayList<String> next;
    public AnalyzerState(String[] o,String[] ne){
        out=new ArrayList<>(Arrays.asList(o));
        next=new ArrayList<>(Arrays.asList(ne));
    }
    public String nextState(char a){
        return next.get(input.indexOf(a));
    }
    public String getOut(char a){
        return out.get(input.indexOf(a));
    }
}
