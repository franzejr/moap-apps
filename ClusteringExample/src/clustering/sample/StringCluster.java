/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clustering.sample;

import arida.ufc.br.moap.clustering.api.ICluster;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que define um cluster para o algorimo StringClustering.
 * 
 * Implementa a interface de cluster Icluster<T> onde <T> é o tipo dos objetos a serem clusterizados.
 * Neste exemplo <T> é String
 * 
 * @author igobrilhante
 */
public class StringCluster implements ICluster<String> {

    private final int id;
    private List<String> members = new ArrayList<String>();
    
    public StringCluster(int id){
        this.id = id;
    }
    
    @Override
    public int getId() {
       return this.id;
    }
    
    public void addObject(String object){
        this.members.add(object);
    }

    @Override
    public List<String> getObjects() {
        return this.members;
    }
    
}
