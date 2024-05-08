package com.example.cursomc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.example.cursomc.domain.PagamentoComBoleto;

@Service
public class BoletoService {
    
    // Em uma situação real esse seria o servico que consumiria um WebService que gera o boleto
    public void preencherPagementoComBoleto(PagamentoComBoleto pagto, Date instanteDoPedido) {
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(instanteDoPedido);
        cal.add(Calendar.DAY_OF_MONTH, 7);
        pagto.setDataVencimento(cal.getTime());
    }
}
