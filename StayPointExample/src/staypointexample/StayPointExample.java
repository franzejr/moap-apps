
package staypointexample;

import arida.ufc.br.moap.core.beans.MovingObject;
import arida.ufc.br.moap.core.beans.Trajectory;
import arida.ufc.br.moap.core.imp.Parameters;
import arida.ufc.br.moap.datamodelapi.imp.TrajectoryModelImpl;
import arida.ufc.br.moap.datamodelapi.spi.ITrajectoryModel;
import arida.ufc.br.moap.importer.csv.imp.RawTrajectoryCSVImporter;
import arida.ufc.br.moap.stop.imp.StayPoint;
import arida.ufc.br.moap.stop.imp.StayPointDetection;
import org.joda.time.Interval;

/**
 *
 * @author franzejr
 */
public class StayPointExample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        RawTrajectoryCSVImporter importer = new RawTrajectoryCSVImporter();
        TrajectoryModelImpl model = new TrajectoryModelImpl();
        Parameters parameters = new Parameters();
        parameters.addParam(RawTrajectoryCSVImporter.PARAMETER_FILE, "/Users/franzejr/Desktop/paradas.csv");
        try {
            importer.buildImport(model, parameters);
            System.out.println("Trajectories: "+model.getTrajectoryCount());
            System.out.println("Moving Object Count: "+model.getMovingObjectCount());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        
        parameters.addParam("timeThreshold", 300000.0);
        parameters.addParam("spatialThreshold", 0.003);
        
        StayPointDetection s = new StayPointDetection();
        ITrajectoryModel<StayPoint,Interval> result = s.execute(model, parameters);
        System.out.println("RESULTADO:" +result.getTrajectoryCount());
        
        for(MovingObject mo : result.getMovingObjects()){
            System.out.println("MOVING OBJECT ID:"+mo.toString());
        }
        
        
        for (Trajectory t : result.getTrajectories()) {
            System.out.println("STAY POINT: " + t.getPoint(0));
            System.out.println("PONTOS:"+t.getPoints());
            System.out.println(t.getMovingObject().getId());

        }
       
        
    }
}
