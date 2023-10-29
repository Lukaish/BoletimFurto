package br.com.boletimFurto.BoletimFurto.controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.boletimFurto.BoletimFurto.errors.BoletimNotFoundExceptions;
import br.com.boletimFurto.BoletimFurto.model.BoletimModel;
import br.com.boletimFurto.BoletimFurto.service.BoletimService;
import jakarta.validation.Valid;

@Controller
public class BoletimController {

    @Autowired
    BoletimService boletimService;

    @GetMapping("/")
    public String listarBoletins(Model model) {
        List<BoletimModel> listaBoletins = boletimService.buscarBoletins();
        model.addAttribute("listaBoletins", listaBoletins);
        return "lista-boletins";
    }

    @GetMapping("/novo")
    public String novoBoletim(Model model) {
        BoletimModel boletimModel = new BoletimModel();
        model.addAttribute("novoBoletim", boletimModel);
        return "novo-boletim";
    }

    @PostMapping("/gravar")
    public String gravarBoletim(@ModelAttribute("novoBoletim") @Valid BoletimModel boletimModel,
            BindingResult errors,
            RedirectAttributes redirectAttributes) {

        if (errors.hasErrors()) {
            return "/novo-boletim";
        }

        boletimService.criarBoletim(boletimModel);
        redirectAttributes.addFlashAttribute("mensagem", "Boletim realizado com sucesso!");
        return "redirect:/novo";
    }

    @GetMapping("/apagar/{id}")
    public String apagarBoletim(@PathVariable("id") Long id, RedirectAttributes att) {

        try {
            boletimService.apagarBoletim(id);
        } catch (BoletimNotFoundExceptions e) {
            att.addFlashAttribute("mensagemErro", e.getMessage());
        }
        return "redirect:/";
    }

    @GetMapping("/editar/{id}")
    public String editarBoletim(@PathVariable("id") Long id, RedirectAttributes att, Model model) {

        try {
            BoletimModel boletimModel = boletimService.buscarBoletim(id);
            model.addAttribute("boletim", boletimModel);
            return "editar-boletim";
        } catch (BoletimNotFoundExceptions e) {
            att.addFlashAttribute("mensagemErro", e.getMessage());
        }
        return "redirect:/";
    }

    @PostMapping("/editar/{id}")
    public String edicaoBoletim(@PathVariable("id") Long id,
            @ModelAttribute("boletim") @Valid BoletimModel boletimModel,
            BindingResult errors,
            RedirectAttributes redirectAttributes) {

        if (errors.hasErrors()) {
            return "/editar-boletim";
        }

        boletimService.alterarBoletim(boletimModel);
        redirectAttributes.addFlashAttribute("mensagem", "Boletim aletarado com sucesso!");
        return "redirect:/";
    }

    @PostMapping("/buscar/placa")
    public String buscarFiltroPlaca(Model model, @Param("placa") String placa) {
        if (placa == null) {
            return "redirect:/";
        }

        List<BoletimModel> lBoletimModels = boletimService.buscarPlacas(placa);
        model.addAttribute("listaBoletins", lBoletimModels);
        return "/lista-boletins";
    }

    @PostMapping("/buscar/cor")
    public String buscarFiltroCor(Model model, @Param("cor") String cor) {
        if (cor == null) {
            return "redirect:/";
        }

        List<BoletimModel> lBoletimModels = boletimService.buscarCor(cor);
        model.addAttribute("listaBoletins", lBoletimModels);
        return "/lista-boletins";
    }

    @PostMapping("/buscar/tipoVeiculo")
    public String buscarFiltrotipoVeiculo(Model model, @Param("tipoVeiculo") String tipoVeiculo) {
        if (tipoVeiculo == null) {
            return "redirect:/";
        }

        List<BoletimModel> lBoletimModels = boletimService.buscarTipoVeiculo(tipoVeiculo);
        model.addAttribute("listaBoletins", lBoletimModels);
        return "/lista-boletins";
    }

    @PostMapping("/buscar/cidade")
    public String buscarFiltroCidade(Model model, @Param("cidade") String cidade) {
        if (cidade == null) {
            return "redirect:/";
        }

        List<BoletimModel> lBoletimModels = boletimService.buscarCidade(cidade);
        model.addAttribute("listaBoletins", lBoletimModels);
        return "/lista-boletins";
    }

    @PostMapping("/buscar/peridoOcorrencia")
    public String buscarFiltroPerioOcorrencia(Model model, @Param("peridoOcorrencia") String peridoOcorrencia) {
        if (peridoOcorrencia == null) {
            return "redirect:/";
        }

        List<BoletimModel> lBoletimModels = boletimService.buscarPeriodoOcorrencia(peridoOcorrencia);
        model.addAttribute("listaBoletins", lBoletimModels);
        return "/lista-boletins";
    }

    @GetMapping("/upload")
    public String index() {
        return "upload-boletim";
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                // Salvar o arquivo no diretório especificado
                File destinationFile = new File("uploads/" + file.getOriginalFilename());
                file.transferTo(destinationFile);
                return "redirect:/success";
            } catch (IOException e) {
                // Lida com exceções
            }
        }
        return "redirect:/error";
    }

}
