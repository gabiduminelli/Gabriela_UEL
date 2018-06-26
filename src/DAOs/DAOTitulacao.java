package DAOs;

import Entidades.Titulacao;
import java.util.ArrayList;
import java.util.List;
import static DAOs.DAOGenerico.em;
import java.text.SimpleDateFormat;

public class DAOTitulacao extends DAOGenerico<Titulacao> {

    public DAOTitulacao() {
        super(Titulacao.class);
    }

    public int autoIdTitulacao() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idTitulacao) FROM Titulacao e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Titulacao> listByIdTitulacao(int idTitulacao) {
        return em.createQuery("SELECT e FROM Titulacao e WHERE e.idTitulacao = :idTitulacao").setParameter("idTitulacao", idTitulacao).getResultList();
    }

    public List<Titulacao> listByNomeTitulacao(String nomeTitulacao) {
        return em.createQuery("SELECT e FROM Titulacao e WHERE e.nomeTitulacao LIKE :nomeTitulacao").setParameter("nomeTitulacao", "%" + nomeTitulacao + "%").getResultList();
    }

    public List<Titulacao> listInOrderIdTitulacao() {
        return em.createQuery("SELECT e FROM Titulacao e ORDER BY e.idTitulacao").getResultList();
    }

    public List<Titulacao> listInOrderNomeTitulacao() {
        return em.createQuery("SELECT e FROM Titulacao e ORDER BY e.nomeTitulacao").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Titulacao> lf;
        if (qualOrdem.equals("idTitulacao")) {
            lf = listInOrderIdTitulacao();
        } else {
            lf = listInOrderNomeTitulacao();
        }

        List<String> ls = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdTitulacao() + ";" + lf.get(i).getNomeTitulacao() + ";");
        }
        return ls;
    }
}

