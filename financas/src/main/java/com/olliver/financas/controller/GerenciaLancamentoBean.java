/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olliver.financas.controller;

import com.olliver.financas.model.Categoria;
import com.olliver.financas.model.Lancamento;
import com.olliver.financas.model.TipoLancamento;
import com.olliver.financas.repository.Categorias;
import com.olliver.financas.repository.Lancamentos;
import com.olliver.financas.service.CadastroLancamento;
import com.olliver.financas.service.NegocioException;
import com.olliver.financas.util.date.DateUtils;
import com.olliver.financas.util.jsf.FacesUtil;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.model.chart.MeterGaugeChartModel;

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
    private Date inicio;
    private Date fim;
    private Double saldo;
    private Double gasto;
    private MeterGaugeChartModel graficoGauge;

    private void inicializarGrafico() {
        this.graficoGauge = this.inicializarValoresGrafico();
        graficoGauge.setTitle("Situação Atual");
        graficoGauge.setSeriesColors("66cc66,93b75f,E7E658,cc6666,cc3535");
        graficoGauge.setGaugeLabel("R$");
        graficoGauge.setGaugeLabelPosition("bottom");
        graficoGauge.setShowTickLabels(true);
    }

    private MeterGaugeChartModel inicializarValoresGrafico() {
        List<Number> intervalos = new ArrayList<Number>() {
            {
                add(saldo * 0.2);
                add(saldo * 0.4);
                add(saldo * 0.6);
                add(saldo * 0.8);
                add(saldo);
            }
        };
        return new MeterGaugeChartModel(gasto, intervalos);
    }

    public void inicializar() {
        this.novoItem();
        this.setDefault();
        this.consultarSituacao();
        this.consultar();
    }

    public void consultar() {
        this.lancamentos = repositorioLancamento.periodo(inicio, fim, autenticacao.getUsuario());
    }

    private void consultarSituacao() {
        try {
            this.saldo = this.repositorioLancamento.situacao(inicio, fim, autenticacao.getUsuario(), TipoLancamento.RECEITA);
            this.gasto = this.repositorioLancamento.situacao(inicio, fim, autenticacao.getUsuario(), TipoLancamento.DESPESA);
        } catch (NullPointerException ne) {
            this.saldo = this.saldo == null ? 0 : this.saldo;
            this.gasto = this.gasto == null ? 0 : this.gasto;
        }
        System.out.println("Saldo = "+saldo);
        System.out.println("Gasto = "+gasto);

        this.inicializarGrafico();
    }

    public void salvar() {
        try {
            if (lancamento.getUsuario() == null) {
                lancamento.setUsuario(autenticacao.getUsuario());
            }
            System.out.println(lancamento);
            cadastro.salvar(lancamento);
            this.novoItem();
            this.consultar();
            this.consultarSituacao();
            FacesUtil.addInfoMessage("Lançamento inserido com sucesso!");
        } catch (NegocioException ex) {
            FacesUtil.addErrorMessage(ex.getMessage());
        }
    }

    public void excluir() {
        try {
            this.cadastro.excluir(lancamento);
            this.consultar();
            this.consultarSituacao();
//            RequestContext.getCurrentInstance().execute("loadValues()");
            FacesUtil.addInfoMessage("Removido com sucesso!");
        } catch (NegocioException ex) {
            FacesUtil.addErrorMessage(ex.getMessage());
        }
    }

    public void novoItem() {
        lancamento = new Lancamento();
        this.categorias = repositorioCategoria.todos();
    }

    public void editar(Lancamento lancamento) {
        this.lancamento = lancamento;
        System.out.println("Editar = " + lancamento);
    }

    private void setDefault() {
        LocalDate temp = LocalDate.now();
        int dia = temp.getDayOfMonth() - 1;
        LocalDate instant = LocalDate.of(temp.getYear(), temp.getMonth(), temp.withDayOfMonth(1).getDayOfMonth());
        inicio = DateUtils.asDate(instant);
        System.out.println("Inicio = " + inicio);
        System.out.println("Fim = " + fim);
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

    public MeterGaugeChartModel getGraficoGauge() {
        return graficoGauge;
    }

}
