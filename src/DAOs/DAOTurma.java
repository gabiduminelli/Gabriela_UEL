package DAOs;

import Entidades.Turma;
import java.util.ArrayList;
import java.util.List;
import static DAOs.DAOGenerico.em;
import java.text.SimpleDateFormat;

public class DAOTurma extends DAOGenerico<Turma> {

    public DAOTurma() {
        super(Turma.class);
    }

    public int autoIdTurma() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.idTurma) FROM Turma e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Turma> listByIdTurma(int idTurma) {
        return em.createQuery("SELECT e FROM Turma e WHERE e.idTurma = :idTurma").setParameter("idTurma", idTurma).getResultList();
    }

    public List<Turma> listByCursoCodigoCurso(String cursoCodigoCurso) {
        return em.createQuery("SELECT e FROM Turma e WHERE e.cursoCodigoCurso LIKE :cursoCodigoCurso").setParameter("cursoCodigoCurso", "%" + cursoCodigoCurso + "%").getResultList();
    }

    public List<Turma> listInOrderIdTurma() {
        return em.createQuery("SELECT e FROM Turma e ORDER BY e.idTurma").getResultList();
    }

    public List<Turma> listInOrderCursoCodigoCurso() {
        return em.createQuery("SELECT e FROM Turma e ORDER BY e.cursoCodigoCurso").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Turma> lf;
        if (qualOrdem.equals("idTurma")) {
            lf = listInOrderIdTurma();
        } else {
            lf = listInOrderCursoCodigoCurso();
        }

        List<String> ls = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getIdTurma() + ";" + lf.get(i).getCursoCodigoCurso() + ";" + lf.get(i).getPeriodoIdPeriodo() + ";" + lf.get(i).getMateriaIdMateria() + ";" + lf.get(i).getMaxAlunos() + ";");
        }
        return ls;
    }
}

