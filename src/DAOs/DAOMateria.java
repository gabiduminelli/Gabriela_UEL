package DAOs;

import Entidades.Materia;
import java.util.ArrayList;
import java.util.List;
import static DAOs.DAOGenerico.em;
import java.text.SimpleDateFormat;

public class DAOMateria extends DAOGenerico<Materia> {

    public DAOMateria() {
        super(Materia.class);
    }

    public int autoIdMateria() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idMateria) FROM Materia e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Materia> listByIdMateria(int idMateria) {
        return em.createQuery("SELECT e FROM Materia e WHERE e.idMateria = :idMateria").setParameter("idMateria", idMateria).getResultList();
    }

    public List<Materia> listByNomeMateria(String nomeMateria) {
        return em.createQuery("SELECT e FROM Materia e WHERE e.nomeMateria LIKE :nomeMateria").setParameter("nomeMateria", "%" + nomeMateria + "%").getResultList();
    }

    public List<Materia> listInOrderIdMateria() {
        return em.createQuery("SELECT e FROM Materia e ORDER BY e.idMateria").getResultList();
    }

    public List<Materia> listInOrderNomeMateria() {
        return em.createQuery("SELECT e FROM Materia e ORDER BY e.nomeMateria").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Materia> lf;
        if (qualOrdem.equals("idMateria")) {
            lf = listInOrderIdMateria();
        } else {
            lf = listInOrderNomeMateria();
        }

        List<String> ls = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdMateria() + ";" + lf.get(i).getNomeMateria() + ";");
        }
        return ls;
    }
}

