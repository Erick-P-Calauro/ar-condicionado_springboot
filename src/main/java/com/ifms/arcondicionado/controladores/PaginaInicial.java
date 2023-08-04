package com.ifms.arcondicionado.controladores;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PaginaInicial {
    @RequestMapping("/")
    String pInicial(Model model) {
        return "pInicialIndex";
    }
}
