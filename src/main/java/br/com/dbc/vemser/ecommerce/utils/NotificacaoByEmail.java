package br.com.dbc.vemser.ecommerce.utils;

import br.com.dbc.vemser.ecommerce.dto.cliente.ClienteDTO;
import br.com.dbc.vemser.ecommerce.dto.pedido.PedidoDTO;
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
        String response = "Seu pedido foi <b>finalizado</b> com sucesso" +
                "Valor Total do Pedido: R$ " + pedidoDTO.getValor();
        dados.put("status", response);

        emailService.sendTemplateEmail(dados, clienteDTO.getEmail());

    }

//    public void notificarByEmailPedidoCliente(ClienteDTO clienteDTO, PedidoDTO pedidoDTO) throws MessagingException {
//        HashMap<String, String> dados = new HashMap<>();
//        dados.put("nome", clienteDTO.getNome());
//        String response = "Seu pedido foi <b>" + statusDeNotificacao + "</b> com sucesso";
//        dados.put("status", response);
//
//        emailService.sendTemplateEmail(dados, clienteDTO.getEmail());
//    }

}

