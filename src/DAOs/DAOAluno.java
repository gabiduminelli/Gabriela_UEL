package DAOs;

import Entidades.Aluno;
import java.util.ArrayList;
import java.util.List;
import static DAOs.DAOGenerico.em;
import java.text.SimpleDateFormat;

public class DAOAluno extends DAOGenerico<Aluno> {

    public DAOAluno() {
        super(Aluno.class);
    }

    public int autoIdAluno() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.raAluno) FROM Aluno e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Aluno> listByRaAluno(int raAluno) {
        return em.createQuery("SELECT e FROM Aluno e WHERE e.raAluno = :raAluno").setParameter("raAluno", raAluno).getResultList();
    }

    public List<Aluno> listByDataIngresso(String dataIngresso) {
        return em.createQuery("SELECT e FROM Aluno e WHERE e.dataIngresso LIKE :dataIngresso").setParameter("dataIngresso", "%" + dataIngresso + "%").getResultList();
    }

    public List<Aluno> listInOrderRaAluno() {
        return em.createQuery("SELECT e FROM Aluno e ORDER BY e.raAluno").getResultList();
    }

    public List<Aluno> listInOrderDataIngresso() {
        return em.createQuery("SELECT e FROM Aluno e ORDER BY e.dataIngresso").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Aluno> lf;
        if (qualOrdem.equals("raAluno")) {
            lf = listInOrderRaAluno();
        } else {
            lf = listInOrderDataIngresso();
        }

        List<String> ls = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getRaAluno() + ";" + sdf.format(lf.get(i).getDataIngresso()) + ";" + lf.get(i).getPessoaCpf() + ";");
        }
        return ls;
    }
}

