package com.ifms.arcondicionado.controladores;

import com.ifms.arcondicionado.modelos.Comando;
import com.ifms.arcondicionado.modelos.Equipamento;
import com.ifms.arcondicionado.modelos.Modelo;
import com.ifms.arcondicionado.modelos.Sala;
import jakarta.validation.Valid;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.ifms.arcondicionado.repositorios.ModeloRep;
import com.ifms.arcondicionado.repositorios.SalaRep;
import com.ifms.arcondicionado.repositorios.ComandoRep;
import com.ifms.arcondicionado.repositorios.EquipamentoRep;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cadastro")
public class PaginaCadastro {

    @Autowired
    private ModeloRep modeloRep;

    @Autowired
    private SalaRep salaRep;

    @Autowired
    private EquipamentoRep equipamentoRep;

    @Autowired
    private ComandoRep comandoRep;

    // Seção das sala ===============================================================================
    @GetMapping("/sala")
    String pCadastroSala(Model model) {
        model.addAttribute("salas", salaRep.findAll());
        model.addAttribute("sala", new Sala());
        return "pCadastroSala";
    }

    @PostMapping("/salvarSala")
    String salvarSala(@Valid Sala sala, BindingResult result, Model model, RedirectAttributes attributes) {
        if(result.hasErrors()) {
            return "redirect:/cadastro/sala";
        }

        salaRep.save(sala);
        attributes.addFlashAttribute("menssagem", "Sala criada com sucesso");
        return "redirect:/cadastro/sala";
    }

    @GetMapping("/editarSala/{id}")
    String editarSalaGet(@PathVariable("id") long id, Model model) {
        Optional<Sala> velhaSala = salaRep.findById(id);

        if(!velhaSala.isPresent()) {
            throw new IllegalArgumentException("Sala Inválida"+ id);
        }

        Sala sala = velhaSala.get();
        model.addAttribute("sala", sala);
        model.addAttribute("salas",salaRep.findAll());

        return "pCadastroSala";
    }

    @Transactional
    @PostMapping("/editarSala/{id}")
    String editarSalaPost(@PathVariable("id") long id, @Valid Sala sala, BindingResult result) {
        if(result.hasErrors()) {
            sala.setId(id);
            return "pCadastroSala";
        }

        salaRep.save(sala);

        return "redirect:/cadastro/sala";
    }

    @GetMapping("/apagarSala/{id}")
    public String apagarSala(@PathVariable("id") long id, Model model) {
        Sala s = salaRep.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id inválido:" + id));

        if(!(s.getEquipamento_sala().isEmpty())) {
            model.addAttribute("messageError", "Existem equipamentos relacionados com esta sala");
            return "redirect:/cadastro/sala";
        }

        salaRep.delete(s);
        return "redirect:/cadastro/sala";
    }

    // Seção dos modelos ============================================================================
    @GetMapping("/modelo")
    String pCadastroModelo(Model model) {
        model.addAttribute("modelo", new Modelo());
        model.addAttribute("modelos", modeloRep.findAll());
        return "pCadastroModelo";
    }

    @PostMapping("/salvarModelo")
    String salvarModelo(@Valid Modelo modelo, BindingResult result, Model model, RedirectAttributes attributes) {
        if(result.hasErrors()) {
            return "redirect:/cadastro/modelo";
        }

        modeloRep.save(modelo);
        attributes.addFlashAttribute("mensagem", "Modelo criado com sucesso");
        return "redirect:/cadastro/modelo";
    }

    @GetMapping("/editarModelo/{id}")
    String editarModeloGet(@PathVariable("id") long id, Model model) {
        Optional<Modelo> velhoModelo = modeloRep.findById(id);

        if(!velhoModelo.isPresent()) {
            throw new IllegalArgumentException("Modelo Inválido"+ id);
        }

        Modelo modelo = velhoModelo.get();
        model.addAttribute("modelo", modelo);
        model.addAttribute("modelos", modeloRep.findAll());

        return "pCadastroModelo";
    }

    @Transactional
    @PostMapping("/editarModelo/{id}")
    String editarEquipamentoPost(@PathVariable("id") long id, @Valid Modelo modelo, BindingResult result) {
        if(result.hasErrors()) {
            modelo.setId(id);
            return "pCadastroModelo";
        }

        modeloRep.save(modelo);

        return "redirect:/cadastro/modelo";
    }

    @GetMapping("/apagarModelo/{id}")
    public String apagarModelo(@PathVariable("id") long id, Model model) {
        Modelo m = modeloRep.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id inválido:" + id));

        if(!(m.getEquipamento_modelo().isEmpty())) {
            model.addAttribute("messageError", "Existem equipamentos relacionados com este modelo");
            return "redirect:/cadastro/modelo";
        }

        modeloRep.delete(m);
        return "redirect:/cadastro/modelo";
    }

    // Seção dos Equipamentos =======================================================================
    @GetMapping("/equipamento")
    String pCadastroEquipamento(Model model) {
        model.addAttribute("equipamento", new Equipamento());
        model.addAttribute("equipamentos", equipamentoRep.findAll());
        model.addAttribute("salas", salaRep.findAll());
        model.addAttribute("modelos", modeloRep.findAll());
        return "pCadastroEquipamento";
    }

    @PostMapping("/salvarEquipamento")
    String salvarEquipamento(@Valid Equipamento equipamento, BindingResult result, Model model, RedirectAttributes attributes) {
        if(result.hasErrors()) {
            return "redirect:/cadastro/equipamento";
        }

        equipamentoRep.save(equipamento);
        attributes.addFlashAttribute("menssagem", "Modelo criado com sucesso");
        return "redirect:/cadastro/equipamento";
    }

    @GetMapping("/editarEquipamento/{id}")
    String editarEquipamentoGet(@PathVariable("id") long id, Model model) {
        Optional<Equipamento> velhoEquipamento = equipamentoRep.findById(id);

        if(!velhoEquipamento.isPresent()) {
            throw new IllegalArgumentException("Equipamento Inválido"+ id);
        }

        Equipamento equipamento = velhoEquipamento.get();
        model.addAttribute("equipamento", equipamento);
        model.addAttribute("equipamentos", equipamentoRep.findAll());
        model.addAttribute("salas", salaRep.findAll());
        model.addAttribute("modelos", modeloRep.findAll());

        return "pCadastroEquipamento";
    }

    @Transactional
    @PostMapping("/editarEquipamento/{id}")
    String editarModeloPost(@PathVariable("id") long id, @Valid Equipamento equipamento, BindingResult result) {
        if(result.hasErrors()) {
            equipamento.setId(id);
            return "pCadastroModelo";
        }

        equipamentoRep.save(equipamento);

        return "redirect:/cadastro/equipamento";
    }

    @GetMapping("/apagarEquipamento/{id}")
    public String apagarEquipamento(@PathVariable("id") long id, Model model) {
        Equipamento e = equipamentoRep.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Id inválido:" + id));
        equipamentoRep.delete(e);
        return "redirect:/cadastro/equipamento";
    }

    // Seção dos comandos ===============================================

    @GetMapping("/comando")
    String pCadastroComando(Model model) {
        model.addAttribute("comando", new Comando());
        model.addAttribute("comandos", comandoRep.findAll());
        model.addAttribute("modelos", modeloRep.findAll());

        return "pCadastroComando";
    }
    
    @PostMapping("/salvarComando")
    String salvarComando(@Valid Comando comando, BindingResult result, Model model, RedirectAttributes attributes) {
        if(result.hasErrors()) {
            return "redirect:/cadastro/comando";
        }

        comandoRep.save(comando);
        attributes.addFlashAttribute("menssagem", "Comando criado com sucesso");
        return "redirect:/cadastro/comando";
    }

}
