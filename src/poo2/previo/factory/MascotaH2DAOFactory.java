
package poo2.previo.factory;

import poo2.previo.modelo.dao.MascotaH2DAO;

/**
 *
 * @author Estudiante
 */
public class MascotaH2DAOFactory {
    
    public MascotaH2DAO createMascotaH2DAO(){
        MascotaH2DAO m=new MascotaH2DAO();
        return m;
    }
}
