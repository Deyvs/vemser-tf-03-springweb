package br.com.dbc.vemser.ecommerce.utils;


import br.com.dbc.vemser.ecommerce.dto.cliente.ClienteDTO;
import br.com.dbc.vemser.ecommerce.dto.pedido.PedidoDTO;
import br.com.dbc.vemser.ecommerce.entity.Produto;
import br.com.dbc.vemser.ecommerce.service.EmailService;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.util.HashMap;

@Data
@Component
public class NotificacaoByEmail {
    private final EmailService emailService;
    public void notificarByEmailCliente(ClienteDTO clienteDTO, String statusDeNotificacao) throws MessagingException {
        HashMap<String, String> dados = new HashMap<>();
        dados.put("nome", clienteDTO.getNome());
        String response;

        if(statusDeNotificacao.equalsIgnoreCase("atualizado") ||
                statusDeNotificacao.equalsIgnoreCase("deletado")) {
            response = "Seu cadastro foi <b>" + statusDeNotificacao + "</b> com sucesso";
        }  else {
            response = "Estamos felizes em ter você em nosso sistema :)<br>" +
                    "Seu cadastro foi <b>" + statusDeNotificacao + "</b> com sucesso " +
                    "e seu identificador é: <b>" + clienteDTO.getIdCliente() + "</b>.";
        }

        dados.put("status", response);
        emailService.sendTemplateEmail(dados, clienteDTO.getEmail());
        System.out.println(clienteDTO.getEmail());
    }

    public void notificarByEmailEndereco(ClienteDTO clienteDTO, String statusDeNotificacao) throws MessagingException {
        HashMap<String, String> dados = new HashMap<>();
        dados.put("nome", clienteDTO.getNome());
        String response = "Seu endereço foi <b>" + statusDeNotificacao + "</b> com sucesso";
        dados.put("status", response);

        emailService.sendTemplateEmail(dados, clienteDTO.getEmail());

    }

    public void notificarByEmailPedidoCliente(ClienteDTO clienteDTO, PedidoDTO pedidoDTO) throws MessagingException {
        HashMap<String, String> dados = new HashMap<>();
        dados.put("nome", clienteDTO.getNome());
        String pedidos = "";
        for(int i = 0; i < pedidoDTO.getProdutos().size(); i++) {
            String concat = "<br>" + pedidoDTO.getProdutos().get(i).getModelo() +
                    " - " + pedidoDTO.getProdutos().get(i).getTamanho() +
                    ": R$ " +
                    String.format("%.2f", pedidoDTO.getProdutos().get(i).getValor());

            pedidos += concat;
        }

        String response = "Seu pedido foi <b>finalizado</b> com sucesso" +
                "<br>--------------------------------------------------" +
                pedidos +
                "<br>--------------------------------------------------" +
                "<br>Valor Total do Pedido | R$ " + String.format("%.2f", pedidoDTO.getValor()) +
                "<br>--------------------------------------------------";
        dados.put("status", response);

        emailService.sendTemplateEmail(dados, clienteDTO.getEmail());

    }
}

