/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arida.ufc.br.trajetoria.clustering;

import arida.ufc.br.moap.clustering.api.ICluster;
import arida.ufc.br.moap.core.beans.Trajectory;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author igobrilhante
 * 
 * Exemplo de um objeto cluster para o algoritmo TrajetoriaCLustering.
 */
public class TrajetoriaCluster implements ICluster<Trajectory> {

    /*
     * Id do cluster
     */
    private final int id;
    /**
     * Lista das trajetorias que pertencem a este cluster
     */
    private final List<Trajectory> membros;
    
    public TrajetoriaCluster(int id){
        this.id=id;
        this.membros = new ArrayList();
    }
    
    @Override
    public int getId() {
        return id;
    }
    
    public boolean addMembro(Trajectory traj){
        return this.membros.add(traj);
    }

    @Override
    public List<Trajectory> getObjects() {
        return this.membros;
    }
    
    /*
     * Representação do cluster
     */
    public double averageSize(){
        double avg = 0;
        
        for(Trajectory t : membros){
            avg += t.getPointCount();
        }
        
        return (double)avg/membros.size();
    }
    
    @Override
    public String toString(){
        return "Cluster "+id+" "+"Membros "+membros.size()+" AVG Size "+averageSize();
    }
    
}
