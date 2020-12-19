/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.toaafllab1;

/**
 *
 * @author Matt
 */
public class Tree {

    private Tree left;
    private Tree right;
    private String data;
    public Tree(String d){
        data=d;
    }
    public void insert_left(String d){
        if(left==null) {
            left=new Tree(d);
           
        }
        else left.insert_left(d);
    }
    public void insert_right(String d){
         if(right==null) {
             right=new Tree(d);
            
         }
        else right.insert_left(d);
    }
    private boolean has_error(){
        if(data=="error") return true;
        if(!(left==null))
            if(left.has_error()) return true;
        if(!(right==null))
            if(right.has_error()) return true;
        return false;
    }
    public String print_tree(){
        if(has_error()) return "Ошибка, слово не может быть выведено по правилам грамматики";
        else{
            String answer = "(S["+data;
            if(!(left==null)) answer+="["+left.print_left()+"]";
            if(!(right==null)) answer+="["+right.print_right()+"]";
            return answer+"])";
        }
        
    }
    private String print_left(){
        if(!(left==null)) return "["+data+"["+left.print_left()+"]]";
        else return data;
    }
    private String print_right(){
        if(!(right==null)) return "["+data+"["+right.print_right()+"]]";
        else return data;
    }
}
