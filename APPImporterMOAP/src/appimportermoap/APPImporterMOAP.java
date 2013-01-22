package appimportermoap;

import arida.ufc.br.moap.datamodelapi.imp.TrajectoryModelImpl;
import arida.ufc.br.moap.datamodelapi.instances.api.IInstance;
import arida.ufc.br.moap.datamodelapi.instances.imp.Instance;
import arida.ufc.br.moap.datamodelapi.instances.imp.InstancesBasedModelImpl;
import java.util.Iterator;
/**
 *Aplicacao Exemplo cujo objetivo eh importar dados de um banco de dados.
 * 
 * No primeiro exemplo trabalhamos com trajetorias simples na qual ha apenas
 * latitude, longitude e timestamp da trajetoria.
 * 
 * No segundo exemplo pegamos dados de qualquer tipo de tabela.
 * No exemplo fizemos com a tabela pessoa. 
 * 
 * 
 * @author franzejr
 */
public class APPImporterMOAP {

    public static void main(String[] args) {
        
        TrajectoryModelImpl model = new TrajectoryModelImpl();
        /*
         * Setando as configuracos do banco
         */
        PostgresqlProvider provider = new PostgresqlProvider("postgres","postgres", "jdbc:postgresql://localhost/postgres");
        /*
         * Dizendo qual vai ser a query
         */
        String query = "select id,lat,lon,datetime as time from traj";
        /*
         * Chamando o metodo do PostgresqlProvider que vai pegar as instancias e
         * também vai fazer a traducao para o modelo de trajetoria
         */
        model = (TrajectoryModelImpl) provider.retrieveInstances(query, model);
        System.out.println(model.getMovingObject(0));
        System.out.println(model.getTrajectory(0).getPoint(0));
        /*
         * Verificando as instancias que foram encontradas
         */
        for(Object o : model.getInstances()){
            System.out.println(o);
        }
        
        /*
         * instanciando um modelo geral para trabalhar com os dados
         */
        InstancesBasedModelImpl model2 = new InstancesBasedModelImpl();
        /*
         * Fazendo uma consulta
         */
        String query2 = "select * from pessoa";
        model2 = (InstancesBasedModelImpl) provider.retrieveInstances(query2, model2);
        System.out.println("Quantidade de Atributos: "+model2.attributesCount());
        System.out.println("Primeiro atributo: "+model2.getAttribute(1));
        System.out.println("Segundo atributo: "+model2.getAttribute(2));
        
        /*
         * Iterando sobre os valores
         */
        for (Iterator<IInstance> it = model2.getInstances().iterator(); it.hasNext();) {
            Instance o = (Instance) it.next();
            
            System.out.println(o.getValue(0));
            System.out.println(o.getValue(1));
            System.out.println(o.getValue(2));
        }
        
    }
    
}