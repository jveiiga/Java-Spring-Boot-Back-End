package com.example.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {

    //Tira o espaço da string
    public static String decodeParam(String s) {
        try {

            return URLDecoder.decode(s, "UTF-8");
        } 
        catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    //Método converte a lista quem vem por parametro na URL como string no método GET para inteiro
    public static List<Integer> decodeIntList(String s) {
        
        String[] vet = s.split(",");

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < vet.length; i++) {

            list.add(Integer.parseInt(vet[i]));
        }
        return list;
    }
}
