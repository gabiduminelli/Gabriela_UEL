/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Gabi
 */
@Entity
@Table(name = "turma")
@NamedQueries({
    @NamedQuery(name = "Turma.findAll", query = "SELECT t FROM Turma t")})
public class Turma implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_turma")
    private Integer idTurma;
    @Column(name = "max_alunos")
    private Integer maxAlunos;
    @JoinColumn(name = "curso_codigo_curso", referencedColumnName = "codigo_curso")
    @ManyToOne(optional = false)
    private Curso cursoCodigoCurso;
    @JoinColumn(name = "materia_id_materia", referencedColumnName = "id_materia")
    @ManyToOne(optional = false)
    private Materia materiaIdMateria;
    @JoinColumn(name = "periodo_id_periodo", referencedColumnName = "id_periodo")
    @ManyToOne(optional = false)
    private Periodo periodoIdPeriodo;

    public Turma() {
    }

    public Turma(Integer idTurma) {
        this.idTurma = idTurma;
    }

    public Integer getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(Integer idTurma) {
        this.idTurma = idTurma;
    }

    public Integer getMaxAlunos() {
        return maxAlunos;
    }

    public void setMaxAlunos(Integer maxAlunos) {
        this.maxAlunos = maxAlunos;
    }

    public Curso getCursoCodigoCurso() {
        return cursoCodigoCurso;
    }

    public void setCursoCodigoCurso(Curso cursoCodigoCurso) {
        this.cursoCodigoCurso = cursoCodigoCurso;
    }

    public Materia getMateriaIdMateria() {
        return materiaIdMateria;
    }

    public void setMateriaIdMateria(Materia materiaIdMateria) {
        this.materiaIdMateria = materiaIdMateria;
    }

    public Periodo getPeriodoIdPeriodo() {
        return periodoIdPeriodo;
    }

    public void setPeriodoIdPeriodo(Periodo periodoIdPeriodo) {
        this.periodoIdPeriodo = periodoIdPeriodo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTurma != null ? idTurma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Turma)) {
            return false;
        }
        Turma other = (Turma) object;
        if ((this.idTurma == null && other.idTurma != null) || (this.idTurma != null && !this.idTurma.equals(other.idTurma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Turma[ idTurma=" + idTurma + " ]";
    }
    
}
