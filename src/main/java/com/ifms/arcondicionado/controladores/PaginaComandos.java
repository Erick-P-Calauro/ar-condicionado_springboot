package com.ifms.arcondicionado.controladores;
import com.ifms.arcondicionado.modelos.Comando;
import com.ifms.arcondicionado.modelos.Equipamento;
import com.ifms.arcondicionado.repositorios.EquipamentoRep;
import com.ifms.arcondicionado.repositorios.ModeloRep;
import com.ifms.arcondicionado.repositorios.SalaRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;
import com.ifms.arcondicionado.modelos.Modelo;

@Controller
@RequestMapping("/comandos")
public class PaginaComandos {

    @Autowired
    EquipamentoRep equipamentoRep;

    @Autowired
    SalaRep salaRep;

    @Autowired
    ModeloRep modeloRep;

    @GetMapping("/executar")
    String executar(@RequestParam("comando") String comandoString, @RequestParam("ip") String ipString,
                    @RequestParam("modelo") String modeloString, Model model) throws Exception {

        Modelo modelo = modeloRep.findByNome(modeloString);
        Comando comando = modelo.getComando();

        switch(comandoString) {
            case "L20":
                comandoString = comando.getL20();
                break;
            case "L21":
                comandoString = comando.getL21();
                break;
            case "L22":
                comandoString = comando.getL22();
                break;
            case "L23":
                comandoString = comando.getL23();
                break;
            case "L24":
                comandoString = comando.getL24();
                break;
            case "L25":
                comandoString = comando.getL25();
                break;
            case "OFF":
                comandoString = comando.getOFF();
                break;

            default :
                break;
        }

        HttpClient client = HttpClient.newBuilder()
                        .build();

        HttpRequest request = HttpRequest.newBuilder()
                        .uri(new URI("http://"+ipString+"/comandos?comando="+comandoString+"&modelo="+modeloString))
                                .build();

        System.out.print("Requisição lançada, aguardando resposta...");

        CompletableFuture<HttpResponse<String>> response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        String corpoResponse = response.join().body();
        System.out.print("Resposta :" + corpoResponse);

        model.addAttribute("equipamento", new Equipamento());
        model.addAttribute("equipamentos", equipamentoRep.findAll());
        model.addAttribute("salas", salaRep.findAll());
        model.addAttribute("modelos", modeloRep.findAll());

        return "pCadastroEquipamento";
    }
}
