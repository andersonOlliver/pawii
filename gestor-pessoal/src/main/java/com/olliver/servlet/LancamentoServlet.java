/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.olliver.servlet;

import com.google.gson.Gson;
import com.olliver.dao.LancamentoDAO;
import com.olliver.model.Lancamento;
import com.olliver.model.TipoLancamento;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author olliver
 */
@WebServlet(name = "LancamentoServlet", urlPatterns = {"/lancamento"})
public class LancamentoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Processes requests for both HTTP <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            LancamentoDAO lancamentoDAO = new LancamentoDAO();
            Gson gson = new Gson();
            List<Lancamento> lancamentos = lancamentoDAO.todos();

            response.setContentType("application/json;charset=UTF-8");

            out.println(gson.toJson(lancamentos));

            
        } catch (Exception e) {

        }
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        response.getWriter().println("<!DOCTYPE html>");
        response.getWriter().println("<html>");
        response.getWriter().println("<head>");
        response.getWriter().println("<title>Servlet LancamentoServlet</title>");
        response.getWriter().println("</head>");
        response.getWriter().println("<body>");
        try {
            if (request.getParameter("act").equals("inserir")) {
                Lancamento lancamento = new Lancamento();
                lancamento.setDataCadastro(this.stringToDate(request.getParameter("input-data")));
                lancamento.setDescricao(request.getParameter("input-descricao"));
                lancamento.setTipo(TipoLancamento.valueOf(request.getParameter("input-tipo")));
                String valor = request.getParameter("input-valor");
                if (valor.contains("R$")) {
                    valor = valor.substring(valor.indexOf(" "));
                }
                System.out.println("Valor = " + valor.indexOf("R$"));
                lancamento.setValor(Double.valueOf(valor.replaceAll(",", ".")));

                LancamentoDAO lancamentoDao = new LancamentoDAO();

                Lancamento aux = lancamentoDao.salvar(lancamento);

                response.getWriter().println("<p>Lancamento cadastrado com sucesso!</p>");

            }
        } catch (Exception ex) {
            response.getWriter().println("<p>Erro ao tentar cadastrar aluno!</p>");
        }
        response.getWriter().println("</body>");
        response.getWriter().println("</html>");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processGet(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processPost(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private Date stringToDate(String date) {
        return new Date();
    }
}
