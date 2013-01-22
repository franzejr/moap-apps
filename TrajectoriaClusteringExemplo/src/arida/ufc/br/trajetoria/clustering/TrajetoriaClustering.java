/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arida.ufc.br.trajetoria.clustering;

import arida.ufc.br.moap.clustering.api.ICluster;
import arida.ufc.br.moap.clustering.api.IClusteringAlgorithm;
import arida.ufc.br.moap.core.beans.Trajectory;
import arida.ufc.br.moap.core.imp.Parameters;
import arida.ufc.br.moap.core.spi.IDataModel;
import arida.ufc.br.moap.datamodelapi.imp.ListModelImpl;
import java.util.HashMap;

/**
 *
 * @author igobrilhante
 * 
 * Exemplo de algoritmo de clusterização sobre Trajectories (<Trajectory>).
 * Este algoritmo junta trajetórias que possuem tamanho parecido de acordo com a funcao de distancia <TrajetoriaFuncaoDistance>
 */
public class TrajetoriaClustering extends IClusteringAlgorithm<Trajectory> {
    
    /**
     * 
     * @param data modelo de dados contendo trajetórias (<ITrajectoryModel>)
     * @param prmtrs parametros para o modelo (neste exemplo não utilizamos parametros)
     * @return um modelo com os clusters
     */
    @Override
    public ListModelImpl<TrajetoriaCluster> execute(IDataModel<Trajectory> data, Parameters prmtrs) {
        
        HashMap<Integer,TrajetoriaCluster> map = new HashMap<Integer,TrajetoriaCluster>();
        
        Trajectory[] trajs = data.getInstances().toArray(new Trajectory[data.getInstances().size()]);
        int size = trajs.length;
        int clusterID = 0;
        for(int i=0;i<size;i++){
            double minimumDistance = Double.MAX_VALUE;
            int idx = -1;
            for(int j=i+1;j<size;j++){
                /*
                 * Utiliza a funcao de distancia para computar a distancia entre duas trajetorias
                 */
                double distance = this.distanceFunction.evaluate(trajs[i], trajs[j]);
                if(distance < minimumDistance){
                    minimumDistance = distance;
                    idx = j;
                }
            }
            TrajetoriaCluster idxCluster = map.get(idx);
            if(idxCluster!=null){
                idxCluster.addMembro(trajs[i]);
            }
            else{
                if(idx != -1){
                    TrajetoriaCluster newCluster = new TrajetoriaCluster(clusterID);
                    newCluster.addMembro(trajs[i]);
                    newCluster.addMembro(trajs[idx]);

                    map.put(i, newCluster);
                    map.put(idx, newCluster);
 
                }
                else{
                    TrajetoriaCluster cluster = map.get(i);
                    if(cluster==null){
                        TrajetoriaCluster newCluster = new TrajetoriaCluster(clusterID);
                        newCluster.addMembro(trajs[i]);
                        map.put(i, newCluster);
                    }
                }
                
                clusterID++;
            }
  
        }
       
        /*
         * Os resultados são armazenados e retornados aqui
         */
        ListModelImpl<TrajetoriaCluster> res = new ListModelImpl<TrajetoriaCluster>();
        res.addAll(map.values());
        
        this.result = res;
        
        return res;
    }

    /**
     * 
     * @return nome do algoritmo
     */
    @Override
    public String getName() {
        return "Trajetoria clustering";
    }
    
}
