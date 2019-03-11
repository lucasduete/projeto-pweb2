package io.github.ifpb.pw2.apigateway.controller;

import io.github.ifpb.pw2.apigateway.feingClients.CoordenadorClient;
import io.github.pw2.coordenadorservice.models.Coordenador;
import org.springframework.stereotype.Service;

@Service
public class CoordenadorController {

    private CoordenadorClient client;

    public CoordenadorController(CoordenadorClient client) {
        this.client = client;
    }

    public Coordenador login(Coordenador coordenador) {

        Coordenador buscado = client.recuperar(coordenador.getMatricula()).getBody();
        if ( buscado != null && buscado.getSenha().equals(coordenador.getSenha())){
            return buscado;
        }
        return null;
    }
}
