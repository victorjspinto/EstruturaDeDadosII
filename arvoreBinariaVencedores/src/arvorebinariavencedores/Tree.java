/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvorebinariavencedores;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author
 */
public class Tree
{
    Cliente inicial;
    
    public Tree(List<Cliente> listaClientes)
    {
        inicial = buildBottomUp(listaClientes);
    }
    
    private Cliente buildBottomUp(List<Cliente> clientes)
    {
        List<Cliente> listaPais = new ArrayList<>();

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
            List aux = clientes;
            clientes = listaPais;
            listaPais = aux;
        } while (listaPais.size() + clientes.size() > 1);
        
        return listaPais.size() > clientes.size() ? listaPais.get(0) : clientes.get(0);
    }

    private Cliente buildParent(Cliente aClient, Cliente anotherClient)
    {
        Cliente parent;

        if (aClient.codCliente <= anotherClient.codCliente)
        {
            parent = new Cliente(aClient.codCliente, aClient.nome, aClient.dataNascimento);
        } else
        {
            parent = new Cliente(anotherClient.codCliente, anotherClient.nome, anotherClient.dataNascimento);
        }

        parent.leftCliente = aClient;
        parent.rightCliente = anotherClient;

        return parent;
    }

    private static void setNo(Cliente c)
    {
        if (c.leftCliente.codCliente < c.rightCliente.codCliente)
        {
            c.codCliente = c.leftCliente.codCliente;
            c.dataNascimento = c.leftCliente.dataNascimento;
            c.nome = c.leftCliente.nome;
        } else
        {
            c.codCliente = c.rightCliente.codCliente;
            c.dataNascimento = c.rightCliente.dataNascimento;
            c.nome = c.rightCliente.nome;
        }
    }

    private void proxVencedor(Cliente c) throws IOException
    {
        if (c.leftCliente != null & c.rightCliente != null)
        {
            proxVencedor(c.leftCliente.codCliente < c.rightCliente.codCliente ? c.leftCliente : c.rightCliente);
            setNo(c);
        } else
        {
            c.le();
        }
    }

    public static void createTestFile(String filename, int numArquivos) throws FileNotFoundException, IOException
    {
        for (int i = 0; i < numArquivos; i++)
        {
            DataOutputStream o = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(i + ".txt")));
            o.writeInt(i);
            o.writeUTF("teta");
            o.writeUTF("teta");
            o.writeInt(Integer.MAX_VALUE);
            o.writeUTF(" ");
            o.writeUTF("  ");
            o.close();
        }
    }
    
    /**
     * Retorna o atual vencedor e lê a proxima entrada no arquivo do atual vencedor.
     * 
     * @return Cliente que foi lido
     * @throws TreeEndException Lança exceção quando os arquivos a serem intercalados chegam ao fim
     * @throws IOException Problemas na formatação do arquivo
     */
    public Cliente intercala() throws TreeEndException, IOException
    {   
        Cliente retorno;
        if(this.inicial.codCliente == Integer.MAX_VALUE)
            throw new TreeEndException("Não há mais registros para serem intercalados");
        else
            retorno = new Cliente(this.inicial.codCliente, this.inicial.dataNascimento, this.inicial.nome);
        
        this.proxVencedor(this.inicial);
        return retorno;
    }
}
