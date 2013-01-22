/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clustering.sample;

import arida.ufc.br.moap.clustering.api.ICluster;
import arida.ufc.br.moap.clustering.api.IClusteringAlgorithm;
import arida.ufc.br.moap.core.imp.Parameters;
import arida.ufc.br.moap.core.spi.IDataModel;
import arida.ufc.br.moap.datamodelapi.imp.ListModelImpl;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author igobrilhante
 * 
 * Esse é um exemplo de como criar um algoritmo de clusterização seguindo o modelo.
 * 
 * A classe abstrata IClusteringAlgorithm<T> requer a definição de T que é o tipo a ser clusterizado. Neste exemplo utilizamos
 * String. Ex. IClusteringAlgorithm<String>.
 * 
 * Esse algoritmo é bem simples. Ele simplesmente junta Strings que possuem o mesmo tamanho. Neste caso, nenhuma função de distância
 * é necessária.
 * 
 * 
 */
public class StringClustering extends IClusteringAlgorithm<String> {
    
    /*
     * Construtor padrão
     */
    public StringClustering(){}
    
    /**
     * Este é o método que executa o algoritmo. 
     * O algoritmo recebe dados de entrada e retorna um modelo com clusters StringCluster
     * 
     * @param data Dados de entrada cujo tipo é T (String)
     * @param prmtrs Paramêtros para o algoritmo (não utilizado nesse exemplo)
     * @return ListModelImpl<StringCluster> modelo de dados com os cluster
     */
    @Override
    public ListModelImpl<StringCluster> execute(IDataModel<String> data, Parameters prmtrs) {
        System.out.println("Clustering Name com "+data.getInstances().size()+" instancias");
        Map<Integer,StringCluster> mapping = new HashMap<Integer, StringCluster>();

        for(String string : data.getInstances()){
            StringCluster cluster;
            int size = string.length();
            
            if(!mapping.containsKey(size)){
                cluster = new StringCluster(size);
                mapping.put(size, cluster);
            }
            else{
                cluster = mapping.get(size);
            }

            cluster.addObject(string);
            
        }
        mapping.values();
        ListModelImpl<StringCluster> res = new ListModelImpl<StringCluster>();
        res.addAll(mapping.values());
        
        this.result = res;
        
        return res;
    }

    /**
     * 
     * @return Nome do algoritmo
     */
    @Override
    public String getName() {
        return "Clustering Example";
    }
    
}
