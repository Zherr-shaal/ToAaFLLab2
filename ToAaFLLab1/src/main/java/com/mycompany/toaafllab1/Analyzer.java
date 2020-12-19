/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.toaafllab1;

import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;

/**
 *
 * @author Matt
 */
public class Analyzer {
    private HashMap<String,AnalyzerState> analyzer;
    private AnalyzerState cur;
    
    private Stack<String> out=new Stack<>();
    private char head(String x){
        if(x!="") return x.charAt(0);
        else return 'e';
    }
    private String tail(String x){
        if (x.length()>1) return x.substring(1);
        else return "error";
    }
    private void initAnalyzer(){
        analyzer=new HashMap<>();
        cur=new AnalyzerState(new String[]{"S->aAB","",""},new String[]{"a ","b ","error"});
        analyzer.put("S", cur);
        analyzer.put("a ", new AnalyzerState(new String[]{"A->aA","A->bA",""},new String[]{"aa","ab","error"}));
        analyzer.put("b ", new AnalyzerState(new String[]{"","",""},new String[]{"ba2","bb2","error"}));
        analyzer.put("ba2", new AnalyzerState(new String[]{"S->bAB A->aA A->aA","S->bAB A->aA A->bA","S->bB B->a"},new String[]{"aa","ab","true"}));
        analyzer.put("bb2", new AnalyzerState(new String[]{"S->bAB A->bA A->aA","S->bAB A->bA A->bA","S->bB B->b"},new String[]{"ba","bb","true"}));
        analyzer.put("aa", new AnalyzerState(new String[]{"A->aA","A->bA",""},new String[]{"aa","ab","error"}));
        analyzer.put("ab", new AnalyzerState(new String[]{"A->aA","A->bA",""},new String[]{"ba","bb","error"}));
        
        analyzer.put("ba", new AnalyzerState(new String[]{"A->aA","A->bA","B->a"},new String[]{"aa","ab","true"}));
        analyzer.put("bb", new AnalyzerState(new String[]{"A->aA","A->bA","B->b"},new String[]{"ba","bb","true"}));
        analyzer.put("error", new AnalyzerState(new String[]{"","",""},new String[]{}));
        analyzer.put("true", new AnalyzerState(new String[]{"","",""},new String[]{}));
    }
    public void analyze(String s){
        if(cur==analyzer.get("error")){
            out=new Stack<>();
            out.push("Ошибка! Строка не принадлежит грамматике!");
        }
        else{
            if(cur==analyzer.get("true")) return;
            char a=head(s);
            out.push(cur.getOut(a));
            cur=analyzer.get(cur.nextState(a));
            analyze(tail(s));
        }
        
    }
    public void reInit(){
        cur=analyzer.get("S");
    }
    public boolean getState(){
       return cur==analyzer.get("error");
    }
    public String printStack(){
       if(cur==analyzer.get("true")){
           String temp=out.pop();
           out.pop();
           out.pop();
           out.push("A->b");
           out.push(temp);
        }
       Collections.reverse(out);
       return print();
    }
    private String print(){
        if(out.empty()) return "";
        else return out.pop()+" " +print();
    }
    public Analyzer(){
       initAnalyzer();
        
    }
}
