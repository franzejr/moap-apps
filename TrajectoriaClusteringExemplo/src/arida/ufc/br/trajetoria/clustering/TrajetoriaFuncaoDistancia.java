/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arida.ufc.br.trajetoria.clustering;

import arida.ufc.br.moap.core.beans.Trajectory;
import arida.ufc.br.moap.distance.spi.IDistanceFunction;

/**
 *
 * @author igobrilhante
 * 
 * Exemplo de funcao de distancia entre duas trajetorias. Retorna a diferença de tamanho entre as trajetórias.
 */
public class TrajetoriaFuncaoDistancia implements IDistanceFunction<Trajectory> {

    /**
     * Computa a distancia entre duas trajetorias baseado no tamanho das mesmas
     * @param t
     * @param t1
     * @return distancia
     */
    @Override
    public Double evaluate(Trajectory t, Trajectory t1) {
        return Math.abs(t.getPointCount() - t1.getPointCount())*1.0;
    }

    @Override
    public String getName() {
        return "Trajetoria Funcao de Distancia";
    }
    
}
