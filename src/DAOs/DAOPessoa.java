package DAOs;

import Entidades.Pessoa;
import java.util.ArrayList;
import java.util.List;
import static DAOs.DAOGenerico.em;
import java.text.SimpleDateFormat;

public class DAOPessoa extends DAOGenerico<Pessoa> {

    public DAOPessoa() {
        super(Pessoa.class);
    }

    public int autoIdPessoa() {
        Integer a = (Integer) em.createQuery("SELECT MAX(e.cpf) FROM Pessoa e ").getSingleResult();
        if (a != null) {
            return a + 1;
        } else {
            return 1;
        }
    }

    public List<Pessoa> listByCpf(int cpf) {
        return em.createQuery("SELECT e FROM Pessoa e WHERE e.cpf = :cpf").setParameter("cpf", cpf).getResultList();
    }

    public List<Pessoa> listByNomePessoa(String nomePessoa) {
        return em.createQuery("SELECT e FROM Pessoa e WHERE e.nomePessoa LIKE :nomePessoa").setParameter("nomePessoa", "%" + nomePessoa + "%").getResultList();
    }

    public List<Pessoa> listInOrderCpf() {
        return em.createQuery("SELECT e FROM Pessoa e ORDER BY e.cpf").getResultList();
    }

    public List<Pessoa> listInOrderNomePessoa() {
        return em.createQuery("SELECT e FROM Pessoa e ORDER BY e.nomePessoa").getResultList();
    }

    public List<String> listInOrderNomeStrings(String qualOrdem) {
        List<Pessoa> lf;
        if (qualOrdem.equals("cpf")) {
            lf = listInOrderCpf();
        } else {
            lf = listInOrderNomePessoa();
        }

        List<String> ls = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (int i = 0; i < lf.size(); i++) {
            ls.add(lf.get(i).getCpf() + ";" + lf.get(i).getNomePessoa() + ";" + lf.get(i).getIdadePessoa() + ";" + lf.get(i).getEmail() + ";");
        }
        return ls;
    }
}

