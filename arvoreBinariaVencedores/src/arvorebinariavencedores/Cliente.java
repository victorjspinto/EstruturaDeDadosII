package arvorebinariavencedores;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/*
 * Classe que implementa um cliente com codigo, nome e dataNascimento
 */
public class Cliente implements Entidade, Cloneable, Serializable
{
    public int codCliente;
    public String nome;
    public String dataNascimento;
    public Cliente leftCliente;
    public Cliente rightCliente;
    DataInputStream d;

    /*
     * Construtor do Cliente
     */
    public Cliente(String filename) throws FileNotFoundException, IOException
    {
        d = new DataInputStream(new BufferedInputStream(new FileInputStream(filename)));
        this.le();
    }

    public Cliente(int codCliente, String nome, String dataNascimento)
    {
        this.codCliente = codCliente;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    /**
     * Salva um Cliente no arquivo out, na posição atual do cursor
     *
     * @param out aquivo onde os dados serão gravados
     */
    public void salva(DataOutputStream out) throws IOException
    {
        out.writeInt(codCliente);
        out.writeUTF(nome);
        out.writeUTF(dataNascimento);
    }

    /**
     * Lê um Cliente do arquivo in na posição atual do cursor e retorna uma
     * instância de Cliente populada com os dados lidos do arquivo
     *
     * @param in Arquivo de onde os dados serão lidos
     * @return instância de Cliente populada com os dados lidos
     */
//    public static Cliente le(DataInputStream in) throws IOException
//    {
//        return new Cliente(in.readInt(), in.readUTF(), in.readUTF());
//    }
    public void le() throws IOException
    {
        this.codCliente = d.readInt();
        this.nome = d.readUTF();
        this.dataNascimento = d.readUTF();
    }

    /**
     * Gera uma String com uma representação de um Cliente
     */
    @Override
    public String toString()
    {
        return this.codCliente + ", " + this.nome + ", " + this.dataNascimento;
    }

    /*
     * Compara o cliente atual com outro cliente
     * retorna true se os valores dos atributos de ambos os clientes forem iguais,
     * e falso caso contrário
     * @param obj Cliente a ser comparado
     * @return True se clientes forem iguais, False caso contrário
     */
    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (this.codCliente != other.codCliente)
        {
            return false;
        }
        if ((this.nome == null) ? (other.nome != null) : !this.nome.equals(other.nome))
        {
            return false;
        }
        if ((this.dataNascimento == null) ? (other.dataNascimento != null) : !this.dataNascimento.equals(other.dataNascimento))
        {
            return false;
        }
        return true;
    }

    /*
     * Gera o hashCode para uma instância de Cliente
     * @return hashCode gerado
     */
    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 71 * hash + this.codCliente;
        hash = 71 * hash + (this.nome != null ? this.nome.hashCode() : 0);
        hash = 71 * hash + (this.dataNascimento != null ? this.dataNascimento.hashCode() : 0);
        return hash;
    }

    public static int encontra(ArrayList<Cliente> clientes, int b)
    {
        int i = -1;
        for (int j = 0; j < clientes.size(); j++)
        {
            if (clientes.get(j).codCliente == b)
            {
                i = j;
            }
        }
        return i;
    }
}
