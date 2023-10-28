package br.com.boletimFurto.BoletimFurto.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.boletimFurto.BoletimFurto.errors.BoletimNotFoundExceptions;
import br.com.boletimFurto.BoletimFurto.model.BoletimModel;
import br.com.boletimFurto.BoletimFurto.repository.BoletimRepository;

@Service
public class BoletimService {

    @Autowired
    private BoletimRepository boletimRepository;


    public BoletimModel criarBoletim(BoletimModel boletimModel) {
        return boletimRepository.save(boletimModel);
    }

    public List<BoletimModel> buscarBoletins() {
        return boletimRepository.findAll();
    }

    public BoletimModel buscarBoletim(Long id) throws BoletimNotFoundExceptions {
        Optional<BoletimModel> opt = boletimRepository.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        } else {
            throw new BoletimNotFoundExceptions("Boletim não encontrado!");
        }
    }

    public void apagarBoletim(Long id) throws BoletimNotFoundExceptions {
        BoletimModel boletimModel = buscarBoletim(id);
        boletimRepository.delete(boletimModel);
    }

    public BoletimModel alterarBoletim(BoletimModel boletimModel) {
        return boletimRepository.save(boletimModel);
    }

    public List<BoletimModel> buscarPlacas(String placa) {
        
        List<BoletimModel> listaBoletimModels = boletimRepository.findAll();
        List<BoletimModel> bs = new ArrayList<>();

        for (BoletimModel boletimModel : listaBoletimModels) {
            if(boletimModel.getVeiculoModel().getEmplacamento().getPlaca().equalsIgnoreCase(placa)){
                bs.add(boletimModel);
            }
        }

        return bs;
    }
}
