/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvorebinariavencedores;

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
    public static void main(String[] args)
    {
        ArrayList<Cliente> clientes = new ArrayList<>();

        clientes.add(new Cliente(1, "a", "b"));
        clientes.add(new Cliente(2, "b", "c"));
        clientes.add(new Cliente(3, "c", "d"));
        clientes.add(new Cliente(4, "d", "e"));
        clientes.add(new Cliente(5, "e", "f"));
        clientes.add(new Cliente(6, "f", "g"));
//        clientes.add(new Cliente(1, "g", "h"));
//        clientes.add(new Cliente(1, "h", "i"));
//        clientes.add(new Cliente(1, "i", "j"));
//        clientes.add(new Cliente(1, "j", "k"));

        Tree t = new Tree();
        Cliente c = t.buildBottomUp(clientes);

    }

}
