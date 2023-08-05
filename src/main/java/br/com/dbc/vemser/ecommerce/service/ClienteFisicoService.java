package br.com.dbc.vemser.ecommerce.service;

import org.springframework.stereotype.Service;

@Service
public class ClienteFisicoService {

}

//    private ClienteFisicoRepository clienteFisicoRepository;
//    public ClienteFisicoService() {
//        clienteFisicoRepository = new ClienteFisicoRepository();
//    }
//    public void listar() {
//        try {
//            for (ClienteFisico clienteFisico : clienteFisicoRepository.listar() ) {
//                clienteFisico.imprimir();
//            }
//
//        } catch (BancoDeDadosException e) {
//            e.printStackTrace();
//        }
//    }
//    public ClienteFisico buscarCliente(int idCliente) {
//        try {
//            ClienteFisico cliente = clienteFisicoRepository.buscarCliente(idCliente);
//            if (cliente == null) {
//                System.out.println("Cliente não encontrado");
//            } return cliente;
//        } catch (BancoDeDadosException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public Integer getMaximoID() {
//        try {
//            return clienteFisicoRepository.getMaximoId();
//        } catch (BancoDeDadosException e) {
//            e.printStackTrace();
//            return 0;
//        }
//    }