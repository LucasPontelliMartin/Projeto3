/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafio3_compiladores;

/**
 *
 * @author Pc
 */
public class Pilha {
   int top;
   String a[] ; // Define tamanho máximo da pilha   

   // Construtor
   public Pilha(int valor) {
      top = -1;
      a = new String [valor];
   }

   // Métodos da pilha
   public boolean isEmpty() {
     return (top < 0);
   }
   
   public void push(String x) {
      top = top +1;
      a[top] = x;
   }
   
    public String pop() {
      if (top < 0) {
         return ("Pilha Vazia!");
      }
      else {
         String x = a[top];
         top --;
         return x;
      }
   }
   public String peek() {
      if (top < 0) {
         return ("Pilha Vazia!");
      }
      else {
         return a[top];
      }
   }
}