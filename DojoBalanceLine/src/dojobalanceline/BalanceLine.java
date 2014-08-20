package dojobalanceline;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class BalanceLine {
    /**
     * Executa o algoritmo Balance Line
     * @param nomeArquivoMestre arquivo mestre
     * @param nomeArquivoTransacao arquivo de transação
     * @param nomeArquivoErros arquivo de erros
     * @param nomeArquivoSaida arquivo de saída
     */
    public void executa(String nomeArquivoMestre, String nomeArquivoTransacao,
            String nomeArquivoSaida, String nomeArquivoErros) throws Exception {
            
            ArrayList<Cliente> clientes= new ArrayList<Cliente>();
            ArrayList<Transacao> erros= new ArrayList<Transacao>();
            DataOutputStream out1 = null; 
            DataOutputStream out2 = null;
            DataInputStream in1 = null;
            DataInputStream in2 = null;
                    
            try{
                
                out1 = new DataOutputStream( new BufferedOutputStream( new FileOutputStream(nomeArquivoSaida)));
                out2 = new DataOutputStream( new BufferedOutputStream( new FileOutputStream(nomeArquivoErros)));
                in1 = new DataInputStream( new BufferedInputStream( new FileInputStream(nomeArquivoMestre)));
                in2 = new DataInputStream( new BufferedInputStream( new FileInputStream(nomeArquivoTransacao)));
                
                Cliente Send = new Cliente(Integer.MAX_VALUE, "    ", "          ");
                Transacao Eend = new TransacaoExclusao(Integer.MAX_VALUE);
                int IniMes = in1.readInt(), IniTran = in2.readInt();
                
                while(IniTran != Integer.MAX_VALUE){
                    
                    while(IniMes < IniTran){
                        clientes.add(new Cliente(IniMes,in1.readUTF(),in1.readUTF()));
                        IniMes = in1.readInt();
                    }
                    
                    char aux = in2.readChar();
                    
                    switch(aux){
                        case 'I':
                            clientes.add(new Cliente(IniTran,in2.readUTF(),in2.readUTF()));
                            break;
                        case 'M':
                            if(IniMes == IniTran){
                                String modificado = in2.readUTF();
                                switch(modificado){
                                    case "nome":
                                        String ad = in1.readUTF();
                                        clientes.add(new Cliente(IniMes,in2.readUTF(),in1.readUTF()));
                                        break;
                                    case"dataNascimento":
                                        clientes.add(new Cliente(IniMes,in1.readUTF(),in2.readUTF()));
                                        in1.readUTF();
                                }
                            }else{
                                erros.add(new TransacaoModificacao(IniTran,in2.readUTF(),in2.readUTF()));
                            }
                            break;
                        case 'E':
                            if(IniMes != IniTran)
                                erros.add(new TransacaoExclusao(IniTran));
                            else{
                                in1.readUTF(); in1.readUTF();
                                IniMes = in1.readInt();
                            }
                    }
                    IniTran = in2.readInt();
                }
                
                while(IniMes != Integer.MAX_VALUE){
                    clientes.add(new Cliente(IniMes,in1.readUTF(),in1.readUTF()));
                    IniMes = in1.readInt();
                }
                
                for(Cliente cliente : clientes)
                    cliente.salva(out1);
                
                Send.salva(out1);
                
                for(Transacao erro : erros)
                    erro.salva(out2);
                
                Eend.salva(out2);
                /*boolean masterEnded = false, transactionEnded = false;
                
                while(!masterEnded || !transactionEnded){
                    if(IniTran == Integer.MAX_VALUE)
                        transactionEnded = false;
                }
                
                if((IniMes == Integer.MAX_VALUE) && (IniTran == Integer.MAX_VALUE)){
                    
                    Send.salva(out1);
                    Eend.salva(out2);
                    
                }else if(IniTran == Integer.MAX_VALUE){
                    clientes.add(new Cliente(IniMes,in1.readUTF(),in1.readUTF()));
                    for (int i = 0; clientes.get(i).codCliente != Integer.MAX_VALUE; i++) 
                        clientes.add(Cliente.le(in1));
                    
                    for (Cliente cliente : clientes) {
                        cliente.salva(out1);                        
                    }
                    
                    Eend.salva(out2);
                    
                }else if(IniMes == Integer.MAX_VALUE){
                    while(IniTran != Integer.MAX_VALUE){
                        char aux = in2.readChar();
                        
                        switch(aux){
                            case 'I':
                                clientes.add(new Cliente(IniTran,in2.readUTF(),in2.readUTF()));
                                break;
                                
                            case 'E':
                                erros.add(new TransacaoExclusao(IniTran));
                                break; 
                                
                            case 'M':
                                erros.add(new TransacaoModificacao(IniTran,in2.readUTF(),in2.readUTF()));
                                break;
                        }
                        
                        IniTran = in2.readInt();
                    }
                    for (Cliente cliente : clientes) 
                        cliente.salva(out1);
                    
                    for (Transacao erro : erros) 
                        erro.salva(out2);
                        
                    Eend.salva(out2);
                    Send.salva(out1);
                }else{
                    
                }
                */
                
            }finally{
                if(out1 != null && out2 != null){
                    out1.close();   
                    out2.close();
                }
            }      


    }
    
}