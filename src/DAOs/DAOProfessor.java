package DAOs;

import Entidades.Professor;
import java.util.ArrayList;
import java.util.List;
import static DAOs.DAOGenerico.em;
import java.text.SimpleDateFormat;

public class DAOProfessor extends DAOGenerico<Professor> {

    public DAOProfessor() {
        super(Professor.class);
    }

    public int autoIdProfessor() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.raProfessor) FROM Professor e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Professor> listByRaProfessor(int raProfessor) {
        return em.createQuery("SELECT e FROM Professor e WHERE e.raProfessor = :raProfessor").setParameter("raProfessor", raProfessor).getResultList();
    }

    public List<Professor> listByDataInicio(String dataInicio) {
        return em.createQuery("SELECT e FROM Professor e WHERE e.dataInicio LIKE :dataInicio").setParameter("dataInicio", "%" + dataInicio + "%").getResultList();
    }

    public List<Professor> listInOrderRaProfessor() {
        return em.createQuery("SELECT e FROM Professor e ORDER BY e.raProfessor").getResultList();
    }

    public List<Professor> listInOrderDataInicio() {
        return em.createQuery("SELECT e FROM Professor e ORDER BY e.dataIngressoProfessor").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Professor> lf;
        if (qualOrdem.equals("raProfessor")) {
            lf = listInOrderRaProfessor();
        } else {
            lf = listInOrderDataInicio();
        }

        List<String> ls = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getRaProfessor() + ";" 
                    + sdf.format(lf.get(i).getDataIngressoProfessor()) 
                    ////////////////////////////aula
                    + ";" + lf.get(i).getPessoaCpf()+";");
        }
        return ls;
    }
}

