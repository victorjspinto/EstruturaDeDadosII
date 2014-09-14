/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dojointercalacao;

import java.io.*;
import java.util.List;

/**
 * Executa o algoritmo Intercalação Ótima
 *
 * @param nomeParticoes array com os nomes dos arquivos que contêm as partições
 * de entrada
 * @param nomeArquivoSaida nome do arquivo de saída resultante da execução do
 * algoritmo
 */
public class IntercalacaoOtima {

    public void executa(List<String> nomeParticoes, String nomeArquivoSaida) throws Exception {
        DataOutputStream out;
        DataInputStream arquivo1;
        DataInputStream arquivo2;
        DataInputStream arquivo3;
        Cliente aux1 = null;
        Cliente aux2 = null;
        Cliente aux3 = null;
        //out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(nomeArquivoSaida)));
        int particoes = 0;

        if (nomeParticoes.size() == 1) {
            File f = new File(nomeParticoes.get(0));
            f.renameTo(new File(nomeArquivoSaida));
        } else {
            File f = new File(nomeParticoes.get(1));
            f.renameTo(new File(nomeArquivoSaida));
        }
        /*else {
            if (nomeParticoes.size() == 2) {
            } else {
                arquivo1 = new DataInputStream(new BufferedInputStream(new FileInputStream(nomeParticoes.get(particoes))));
                arquivo2 = new DataInputStream(new BufferedInputStream(new FileInputStream(nomeParticoes.get(particoes++))));
                arquivo3 = new DataInputStream(new BufferedInputStream(new FileInputStream(nomeParticoes.get(particoes++))));

                aux1 = Cliente.le(arquivo1);
                aux2 = Cliente.le(arquivo2);
                aux3 = Cliente.le(arquivo3);
                if ((aux1.codCliente == Integer.MAX_VALUE && aux2.codCliente == Integer.MAX_VALUE) || (aux1.codCliente == Integer.MAX_VALUE && aux3.codCliente == Integer.MAX_VALUE) ||(aux2.codCliente == Integer.MAX_VALUE && aux3.codCliente == Integer.MAX_VALUE)) {
                    
                }
            }
        }*/

        //TODO: Inserir aqui o código do algoritmo de Intercalação Ótima

    }
}
