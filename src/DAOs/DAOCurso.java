package DAOs;

import Entidades.Curso;
import java.util.ArrayList;
import java.util.List;
import static DAOs.DAOGenerico.em;
import java.text.SimpleDateFormat;

public class DAOCurso extends DAOGenerico<Curso> {

    public DAOCurso() {
        super(Curso.class);
    }

    public int autoIdCurso() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.codigoCurso) FROM Curso e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Curso> listByCodigoCurso(int codigoCurso) {
        return em.createQuery("SELECT e FROM Curso e WHERE e.codigoCurso = :codigoCurso").setParameter("codigoCurso", codigoCurso).getResultList();
    }

    public List<Curso> listByNomeCurso(String nomeCurso) {
        return em.createQuery("SELECT e FROM Curso e WHERE e.nomeCurso LIKE :nomeCurso").setParameter("nomeCurso", "%" + nomeCurso + "%").getResultList();
    }

    public List<Curso> listInOrderCodigoCurso() {
        return em.createQuery("SELECT e FROM Curso e ORDER BY e.codigoCurso").getResultList();
    }

    public List<Curso> listInOrderNomeCurso() {
        return em.createQuery("SELECT e FROM Curso e ORDER BY e.nomeCurso").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Curso> lf;
        if (qualOrdem.equals("codigoCurso")) {
            lf = listInOrderCodigoCurso();
        } else {
            lf = listInOrderNomeCurso();
        }

        List<String> ls = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getCodigoCurso() + ";" + lf.get(i).getNomeCurso() + ";" + lf.get(i).getAtivo() + ";");
        }
        return ls;
    }
}

