/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.olliver.financas.controller;

import java.io.Serializable;
import java.util.Random;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author TABA - http://ceda.ic.ufmt.br
 * @version 1.0
 * @since 1.0
 */
@Named
@SessionScoped
public class CounterBean implements Serializable {
    private static final long serialVersionUID = 1L;

	private int count;
        private int gaugeCount;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	public void increment() {
		count++;
	}
        
        public void incrementGauges() {
            Random r = new Random(System.nanoTime());
            gaugeCount = r.nextInt(101);
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("gauge1.refresh(" + gaugeCount + ")");
            requestContext.execute("gauge2.setValue(" + gaugeCount + ")");            
        }

}
