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
public class Tree
{

    public Cliente buildBottomUp(ArrayList<Cliente> clientes)
    {
        ArrayList<Cliente> listaPais = new ArrayList<>();

        do
        {
            while (clientes.size() > 1)
            {
                Cliente aux1, aux2, parent;
                aux1 = clientes.get(0);
                clientes.remove(0);
                aux2 = clientes.get(0);
                clientes.remove(0);
                parent = buildParent(aux1, aux2);
                listaPais.add(parent);
            }
            
            if (clientes.size() == 1)
            {
                listaPais.add(clientes.get(0));
            }
            
            clientes.clear();
            ArrayList aux = clientes;
            clientes = listaPais;
            listaPais = aux;
        } while (listaPais.size() + clientes.size() > 1);

        return listaPais.size() > clientes.size() ? listaPais.get(0) : clientes.get(0);
    }

    static public Cliente buildParent(Cliente aClient, Cliente anotherClient)
    {

        Cliente parent = null;
        try
        {
            if (aClient.codCliente <= anotherClient.codCliente)
            {
                parent = aClient.deepCopy();
            } else
            {
                parent = anotherClient.deepCopy();
            }
        } catch (Exception ex)
        {
            System.err.printf("Não foi possível criar nós pais: %s\n", ex.toString());
        }

        parent.leftCliente = aClient;
        parent.rightCliente = anotherClient;

        return parent;
    }
}
