package com.example.myapp;

/** Representa um Funcionário */
/** Usada para a leitura do .json */

public class Funcionario {
    public final int id;
    public final String nome;
    public final String sobrenome;
    public final double salario;
    public final String area;

    public Funcionario(int id, String nome, String sobrenome, double salario, String area){
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.salario = salario;
        this.area = area;
    }
}
