/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clusteringexample;

import arida.ufc.br.moap.datamodelapi.imp.ListModelImpl;
import clustering.sample.FuncaoDistanciaExemplo;
import clustering.sample.StringCluster;
import clustering.sample.StringClustering;

/**
 *
 * @author igobrilhante
 * 
 * Exemplo de execução de um simples algoritmo de clusterização
 */
public class ClusteringExample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /* 
         * Instanciação do algoritmo
         */
        StringClustering clustering = new StringClustering();
        
        /**
         * Indica qual a função de distância. Para o algoritmo neste exemplo, nós não utilizamos nenhuma função
         * 
         **/
        clustering.setDistanceFunction(null);
        
        /*
         * Criando um modelo simples de dados utilizando ListModelImpl que é igual a uma lista de elementos, no caso,
         * strings
         */
        ListModelImpl<String> data = new ListModelImpl<String>();
        data.add("maça");
        data.add("banana");
        data.add("pera");
        data.add("uva");
        data.add("abacaxi");
        data.add("melão");
        
        /*
         * Execução do algoritmo.
         * Tem como entrada os dados (data) e parametros(null). Para esse algoritmo, nenhuma parâmetro foi necessário
         * 
         * O resultado é armazenado em ListModelImpl<StringCluster>
         */
        ListModelImpl<StringCluster> res = clustering.execute(data, null);
        
        /*
         * Ver o resultado. Iterar nos clusters encontrados
         */
        for(StringCluster cluster : res.getInstances()){
            System.out.println("Cluster "+cluster.getId());
            System.out.println("Objetos:\n"+cluster.getObjects());
        }
        
        /*
         * Exemplo de uma função de distância
         */
        FuncaoDistanciaExemplo funcao = new FuncaoDistanciaExemplo();
        System.out.println("Distancia "+funcao.evaluate("maca", "banana"));
    }
}
