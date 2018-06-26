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
@Table(name = "professor")
@NamedQueries({
    @NamedQuery(name = "Professor.findAll", query = "SELECT p FROM Professor p")})
public class Professor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ra_professor")
    private Integer raProfessor;
    @Basic(optional = false)
    @Column(name = "data_ingresso_professor")
    @Temporal(TemporalType.DATE)
    private Date dataIngressoProfessor;
    @JoinColumn(name = "pessoa_cpf", referencedColumnName = "cpf")
    @ManyToOne(optional = false)
    private Pessoa pessoaCpf;

    public Professor() {
    }

    public Professor(Integer raProfessor) {
        this.raProfessor = raProfessor;
    }

    public Professor(Integer raProfessor, Date dataIngressoProfessor) {
        this.raProfessor = raProfessor;
        this.dataIngressoProfessor = dataIngressoProfessor;
    }

    public Integer getRaProfessor() {
        return raProfessor;
    }

    public void setRaProfessor(Integer raProfessor) {
        this.raProfessor = raProfessor;
    }

    public Date getDataIngressoProfessor() {
        return dataIngressoProfessor;
    }

    public void setDataIngressoProfessor(Date dataIngressoProfessor) {
        this.dataIngressoProfessor = dataIngressoProfessor;
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
        hash += (raProfessor != null ? raProfessor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Professor)) {
            return false;
        }
        Professor other = (Professor) object;
        if ((this.raProfessor == null && other.raProfessor != null) || (this.raProfessor != null && !this.raProfessor.equals(other.raProfessor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Professor[ raProfessor=" + raProfessor + " ]";
    }
    
}
