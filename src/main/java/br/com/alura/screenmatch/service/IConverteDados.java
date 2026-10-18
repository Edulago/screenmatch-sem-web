package br.com.alura.screenmatch.service;

import java.io.IOException;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}
