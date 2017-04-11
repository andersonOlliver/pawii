/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olliver.financas.controller;

import com.olliver.financas.model.Categoria;
import com.olliver.financas.model.Lancamento;
import com.olliver.financas.model.TipoLancamento;
import com.olliver.financas.model.Usuario;
import com.olliver.financas.repository.Categorias;
import com.olliver.financas.repository.Lancamentos;
import com.olliver.financas.repository.util.LancamentoCategoria;
import com.olliver.financas.service.CadastroLancamento;
import com.olliver.financas.service.NegocioException;
import com.olliver.financas.util.date.DateUtils;
import com.olliver.financas.util.jsf.FacesUtil;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author TABA - http://ceda.ic.ufmt.br
 * @version 1.0
 * @since 1.0
 */
@Named
@ViewScoped
public class GerenciaLancamentoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Lancamentos repositorioLancamento;
    @Inject
    private Categorias repositorioCategoria;
    @Inject
    private CadastroLancamento cadastro;
    @Inject
    private LoginBean autenticacao;

    private List<Lancamento> lancamentos;
    private List<Categoria> categorias;
    private Lancamento lancamento;
    private Usuario usuario;
    private Date inicio;
    private Date fim;
    private Double saldo;
    private Double gasto;
    
    /**
     * Executado na primeira renderização da página para o cliente
     * esse método irá invocar as ações para atribuir os valores 
     * iniciais para construção da página.
     */
    public void inicializar() {
        this.novoItem();
        this.setDefault();
        this.consultarSituacao();
        this.consultar();
    }

    /**
     * Busca os dados para a tabela
     */
    public void consultar() {
        this.lancamentos = repositorioLancamento.periodo(inicio, fim, usuario);
    }
    
    /**
     * Busca exatamente os dados para o gráfico
     */
    private void consultarSituacao() {
        this.saldo = 0.0;
        this.gasto = 0.0;
        try {
            this.saldo = this.repositorioLancamento.situacao(inicio, fim, usuario, TipoLancamento.RECEITA);
            this.gasto = this.repositorioLancamento.situacao(inicio, fim, usuario, TipoLancamento.DESPESA);
        } catch (NullPointerException ne) {
            this.saldo = this.saldo == null ? 0 : this.saldo;
            this.gasto = this.gasto == null ? 0 : this.gasto;
        }
    }

    
    public void salvar() {
        try {
            if (lancamento.getUsuario() == null) {
                lancamento.setUsuario(usuario);
            }
            System.out.println(lancamento);
            cadastro.salvar(lancamento);
            lancamento = null;
            this.consultar();
            this.consultarSituacao();
            FacesUtil.addInfoMessage("Lançamento inserido com sucesso!");
        } catch (NegocioException ex) {
            FacesUtil.addErrorMessage(ex.getMessage());
        }
    }

    /**
     * 
     * @param lancamento para ser excluído
     * 
     * Esse método exclui o lançamento recebido por parâmetro
     * e logo a seguir atualiza os dados para serem exebidos
     */
    public void excluir(Lancamento lancamento) {
        try {
            this.cadastro.excluir(lancamento);
            this.consultar();
            this.consultarSituacao();
            this.atualizarGauge();
            FacesUtil.addInfoMessage("Removido com sucesso!");
        } catch (NegocioException ex) {
            FacesUtil.addErrorMessage(ex.getMessage());
        }
    }
    
    /**
     * Método responsável por invocar o refresh() do gráfico justGauge
     * que irá atualizar após uma inclusão/edição/exclusão de dados
     */
    public void atualizarGauge(){
        RequestContext.getCurrentInstance().execute("g1.refresh(" + gasto + "," + saldo + ");");
    }

    /**
     * Prepara um novo objeto de lançamento para ser cadastrado
     * logo após busca as categorias no banco de dados
     */
    public void novoItem() {
        lancamento = new Lancamento();
        this.categorias = repositorioCategoria.todos();
    }

    /**
     * 
     * @param lancamento Lançamento a ser editado
     * prepara para o objeto lançamento para ser editado
     */
    public void editar(Lancamento lancamento) {
        this.lancamento = lancamento;
    }

    /**
     * Settar os valores padrões de usuário e data
     */
    private void setDefault() {
        this.usuario = autenticacao.getUsuario();
        LocalDate temp = LocalDate.now();
        int dia = temp.getDayOfMonth() - 1;
        LocalDate instant = LocalDate.of(temp.getYear(), temp.getMonth(), temp.withDayOfMonth(1).getDayOfMonth());
        inicio = DateUtils.asDate(instant);
        fim = new Date();
    }

    //GETTER SETTER
    public TipoLancamento[] getTipos() {
        return TipoLancamento.values();
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public List<Lancamento> getLancamentos() {
        return lancamentos;
    }

    public Lancamento getLancamento() {
        return lancamento;
    }

    public void setLancamento(Lancamento lancamento) {
        this.lancamento = lancamento;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFim() {
        return fim;
    }

    public void setFim(Date fim) {
        this.fim = fim;
    }

    public Double getSaldo() {
        return saldo;
    }

    public Double getGasto() {
        return gasto;
    }  
    
}
