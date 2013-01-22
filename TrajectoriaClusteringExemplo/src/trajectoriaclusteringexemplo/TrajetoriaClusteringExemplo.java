/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package trajectoriaclusteringexemplo;

import arida.ufc.br.moap.core.beans.MovingObject;
import arida.ufc.br.moap.core.beans.Trajectory;
import arida.ufc.br.moap.datamodelapi.imp.ListModelImpl;
import arida.ufc.br.moap.datamodelapi.imp.TrajectoryModelImpl;
import arida.ufc.br.trajetoria.clustering.TrajetoriaCluster;
import arida.ufc.br.trajetoria.clustering.TrajetoriaClustering;
import arida.ufc.br.trajetoria.clustering.TrajetoriaFuncaoDistancia;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.joda.time.DateTime;

/**
 *
 * @author igobrilhante
 * 
 * Exemplo de um algoritmo que clusteriza trajetórias.
 */
public class TrajetoriaClusteringExemplo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        /*
         * Algoritmo
         */
        TrajetoriaClustering clustering = new TrajetoriaClustering();
        
        /*
         * Funcao de distancia
         */
        TrajetoriaFuncaoDistancia fd = new TrajetoriaFuncaoDistancia();
        clustering.setDistanceFunction(fd);
       
        /*
         * Gerar trajetorias artificiais
         */
        int tamanho_max = 100;
        int n_de_trajetorias = 20;
        TrajectoryModelImpl model = gerarDados(tamanho_max, n_de_trajetorias);
        
        /*
         * Executar o algoritmo e recuperar o resultado. Este algoritmo não requer parametros
         */
        ListModelImpl<TrajetoriaCluster> res = clustering.execute(model, null);
        
        /*
         * Imprimindo o resultado
         */
        System.out.println("Resultado");
        for(TrajetoriaCluster cluster : res.getInstances()){
            System.out.println(cluster);
        }
    }
    
    /**
     * Função que auxilia na criação de trajetórias artificais
     * 
     * @param max tamanho máximo das trajetórias
     * @param n_de_trajetorias número de trajetórias a serem geradas
     * @return 
     */
    public static TrajectoryModelImpl gerarDados(int max,int n_de_trajetorias){
        Random random = new Random();
        
        TrajectoryModelImpl model = new TrajectoryModelImpl();
        MovingObject mo = model.factory().newMovingObject("exemplo");
        
        for(int i=0;i<n_de_trajetorias;i++){
            int size = random.nextInt(max)+2;
            
            List<Integer> points = new ArrayList();
            List<DateTime> times = new ArrayList();
            
            for(int j=0;j<size;j++){
                points.add(j);
                times.add(new DateTime());
            }
            
            Trajectory traj = model.factory().newTrajectory(Integer.toString(i), mo);
            traj.setPoints(points);
            traj.setTimes(times);
            
            model.addTrajectory(traj);
            
        }
        
        System.out.println("Número de trajetorias geradas: "+model.getTrajectoryCount());
        
        return model;
        
        
    }
}
