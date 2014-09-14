/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvorebinariavencedores;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author
 */
public class ArvoreBinariaVencedores
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, TreeEndException
    {
        ArrayList<Cliente> clientes = new ArrayList<>();

        clientes.add(new Cliente("0.txt"));
        clientes.add(new Cliente("1.txt"));
        clientes.add(new Cliente("2.txt"));
        clientes.add(new Cliente("3.txt"));
        clientes.add(new Cliente("4.txt"));
        
        Tree t = new Tree(clientes);
        System.out.println(t.intercala().codCliente);
        System.out.println(t.intercala().codCliente);
        System.out.println(t.intercala().codCliente);
        System.out.println(t.intercala().codCliente);
        System.out.println(t.intercala().codCliente);
    }
}
