package DAOs;

import Entidades.Periodo;
import java.util.ArrayList;
import java.util.List;
import static DAOs.DAOGenerico.em;
import java.text.SimpleDateFormat;

public class DAOPeriodo extends DAOGenerico<Periodo> {

    public DAOPeriodo() {
        super(Periodo.class);
    }

    public int autoIdPeriodo() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idPeriodo) FROM Periodo e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Periodo> listByIdPeriodo(int idPeriodo) {
        return em.createQuery("SELECT e FROM Periodo e WHERE e.idPeriodo = :idPeriodo").setParameter("idPeriodo", idPeriodo).getResultList();
    }

    public List<Periodo> listByDataInicio(String dataInicio) {
        return em.createQuery("SELECT e FROM Periodo e WHERE e.dataInicio LIKE :dataInicio").setParameter("dataInicio", "%" + dataInicio + "%").getResultList();
    }

    public List<Periodo> listInOrderIdPeriodo() {
        return em.createQuery("SELECT e FROM Periodo e ORDER BY e.idPeriodo").getResultList();
    }

    public List<Periodo> listInOrderDataInicio() {
        return em.createQuery("SELECT e FROM Periodo e ORDER BY e.dataInicio").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Periodo> lf;
        if (qualOrdem.equals("idPeriodo")) {
            lf = listInOrderIdPeriodo();
        } else {
            lf = listInOrderDataInicio();
        }

        List<String> ls = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdPeriodo() + ";" + sdf.format(lf.get(i).getDataInicio()) + ";" + sdf.format(lf.get(i).getDataFinal()) + ";");
        }
        return ls;
    }
}

