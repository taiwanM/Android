package com.example.tmoraes.myapplication;

/**
 * Created by tmoraes on 07/12/2017.
 */

class User {
    private int ID;
    private String nome;
    private String senha;

    public int getID() {
        return ID;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setID(int ID) {

        this.ID = ID;
    }
}
