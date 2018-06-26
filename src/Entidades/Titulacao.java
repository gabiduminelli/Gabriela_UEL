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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Gabi
 */
@Entity
@Table(name = "titulacao")
@NamedQueries({
    @NamedQuery(name = "Titulacao.findAll", query = "SELECT t FROM Titulacao t")})
public class Titulacao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_titulacao")
    private Integer idTitulacao;
    @Column(name = "nome_titulacao")
    private String nomeTitulacao;

    public Titulacao() {
    }

    public Titulacao(Integer idTitulacao) {
        this.idTitulacao = idTitulacao;
    }

    public Integer getIdTitulacao() {
        return idTitulacao;
    }

    public void setIdTitulacao(Integer idTitulacao) {
        this.idTitulacao = idTitulacao;
    }

    public String getNomeTitulacao() {
        return nomeTitulacao;
    }

    public void setNomeTitulacao(String nomeTitulacao) {
        this.nomeTitulacao = nomeTitulacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTitulacao != null ? idTitulacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Titulacao)) {
            return false;
        }
        Titulacao other = (Titulacao) object;
        if ((this.idTitulacao == null && other.idTitulacao != null) || (this.idTitulacao != null && !this.idTitulacao.equals(other.idTitulacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entidades.Titulacao[ idTitulacao=" + idTitulacao + " ]";
    }
    
}
