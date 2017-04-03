package com.olliver.financas.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author olliver
 */
@Entity
@Table(name = "lancamento")
public class Lancamento implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue
    private Long id;
    
    @Column(precision = 20, scale = 2, nullable = false)
    private Double valor;
    
    
    @Column(length = 140)
    private String descricao;
    
    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "data_cadastro", nullable = false)
    private Date dataCadastro;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name="usuario", nullable = false) 
    private Usuario usuario;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name="categoria", nullable = false) 
    private Categoria categoria;
        
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoLancamento tipo;

    public Lancamento() {
//        this.tipo = TipoLancamento.DESPESA;
    }

    public Lancamento(Long id) {
        this.id = id;
    }
    
    public Lancamento(Long id, Double valor, String descricao, Date dataCadastro, TipoLancamento tipo) {
        this.id = id;
        this.valor = valor;
        this.descricao = descricao;
        this.dataCadastro = dataCadastro;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public TipoLancamento getTipo() {
        return tipo;
    }

    public void setTipo(TipoLancamento tipo) {
        this.tipo = tipo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Lancamento other = (Lancamento) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Lancamento{" + "id=" + id + ", valor=" + valor + ", descricao=" + descricao + ", dataCadastro=" + dataCadastro + ", usuario=" + usuario + ", categoria=" + categoria + ", tipo=" + tipo + '}';
    }
}
