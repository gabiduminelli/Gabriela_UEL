/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Gabi
 */
@Entity
@Table(name = "aluno")
@NamedQueries({
    @NamedQuery(name = "Aluno.findAll", query = "SELECT a FROM Aluno a")})
public class Aluno implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ra_aluno")
    private Integer raAluno;
    @Basic(optional = false)
    @Column(name = "data_ingresso")
    @Temporal(TemporalType.DATE)
    private Date dataIngresso;
    @JoinColumn(name = "pessoa_cpf", referencedColumnName = "cpf")
    @ManyToOne(optional = false)
    private Pessoa pessoaCpf;

    public Aluno() {
    }

    public Aluno(Integer raAluno) {
        this.raAluno = raAluno;
    }

    public Aluno(Integer raAluno, Date dataIngresso) {
        this.raAluno = raAluno;
        this.dataIngresso = dataIngresso;
    }

    public Integer getRaAluno() {
        return raAluno;
    }

    public void setRaAluno(Integer raAluno) {
        this.raAluno = raAluno;
    }

    public Date getDataIngresso() {
        return dataIngresso;
    }

    public void setDataIngresso(Date dataIngresso) {
        this.dataIngresso = dataIngresso;
    }

    public Pessoa getPessoaCpf() {
        return pessoaCpf;
    }

    public void setPessoaCpf(Pessoa pessoaCpf) {
        this.pessoaCpf = pessoaCpf;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (raAluno != null ? raAluno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aluno)) {
            return false;
        }
        Aluno other = (Aluno) object;
        if ((this.raAluno == null && other.raAluno != null) || (this.raAluno != null && !this.raAluno.equals(other.raAluno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Aluno[ raAluno=" + raAluno + " ]";
    }
    
}
