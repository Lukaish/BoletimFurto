package br.com.boletimFurto.BoletimFurto.service;

import java.util.List;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.boletimFurto.BoletimFurto.errors.BoletimNotFoundExceptions;
import br.com.boletimFurto.BoletimFurto.model.BoletimModel;
import br.com.boletimFurto.BoletimFurto.model.EmplacamentoModel;
import br.com.boletimFurto.BoletimFurto.model.EnderecoModel;
import br.com.boletimFurto.BoletimFurto.model.ParteModel;
import br.com.boletimFurto.BoletimFurto.model.VeiculoModel;
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
            if (boletimModel.getVeiculoModel().getEmplacamento().getPlaca().equalsIgnoreCase(placa)) {
                bs.add(boletimModel);
            }
        }

        return bs;
    }

    public List<BoletimModel> buscarCor(String cor) {

        List<BoletimModel> listaBoletimModels = boletimRepository.findAll();
        List<BoletimModel> bs = new ArrayList<>();

        for (BoletimModel boletimModel : listaBoletimModels) {
            if (boletimModel.getVeiculoModel().getCor().equalsIgnoreCase(cor)) {
                bs.add(boletimModel);
            }
        }

        return bs;
    }

    public List<BoletimModel> buscarTipoVeiculo(String tipoVeiculo) {

        List<BoletimModel> listaBoletimModels = boletimRepository.findAll();
        List<BoletimModel> bs = new ArrayList<>();

        for (BoletimModel boletimModel : listaBoletimModels) {
            if (boletimModel.getVeiculoModel().getTipoVeiculo().equalsIgnoreCase(tipoVeiculo)) {
                bs.add(boletimModel);
            }
        }

        return bs;
    }

    public List<BoletimModel> buscarCidade(String cidade) {

        List<BoletimModel> listaBoletimModels = boletimRepository.findAll();
        List<BoletimModel> bs = new ArrayList<>();

        for (BoletimModel boletimModel : listaBoletimModels) {
            if (boletimModel.getEnderecoModel().getCidade().equalsIgnoreCase(cidade)) {
                bs.add(boletimModel);
            }
        }

        return bs;
    }

    public List<BoletimModel> buscarPeriodoOcorrencia(String peridoOcorrencia) {


        List<BoletimModel> listaBoletimModels = boletimRepository.findAll();
        List<BoletimModel> bs = new ArrayList<>();

        for (BoletimModel boletimModel : listaBoletimModels) {
            if (boletimModel.getEnderecoModel().getCidade().equalsIgnoreCase(peridoOcorrencia)) {
                bs.add(boletimModel);
            }
        }

        return bs;
    }

    
    // public void upload(MultipartFile file) throws IOException {
    //     List<BoletimModel> listBoletinsModels = new ArrayList<>();
    //     InputStreamReader reader = new InputStreamReader(file.getInputStream());
    //     CSVFormat configCSV = CSVFormat.DEFAULT.builder()
    //     .setHeader("ANO_BO",	"NUM_BO",	"NUMERO_BOLETIM", "BO_INICIADO",	
    //         "BO_EMITIDO", "DATAOCORRENCIA",	"HORAOCORRENCIA",	"PERIDOOCORRENCIA",	"DATACOMUNICACAO",
    //         "DATAELABORACAO", "BO_AUTORIA",	"FLAGRANTE",	"NUMERO_BOLETIM_PRINCIPAL",	"LOGRADOURO",
    //         "NUMERO", "BAIRRO", "CIDADE", " UF LATITUDE",	"LONGITUDE",	"DESCRICAOLOCAL",	"EXAME",	"SOLUCAO",	"DELEGACIA_NOME",
    //         "DELEGACIA_CIRCUNSCRICAO",	"ESPECIE",	"RUBRICA", "DESDOBRAMENTO",	"STATUS", "TIPOPESSOA", 
    //         "VITIMAFATAL", "NATURALIDADE",	"NACIONALIDADE",	"SEXO",	"DATANASCIMENTO",	"IDADE",
    //         "ESTADOCIVIL",	"PROFISSAO", "GRAUINSTRUCAO",	"CORCUTIS",	"NATUREZAVINCULADA"	,"TIPOVINCULO",	"RELACIONAMENTO",	"PARENTESCO",
    //         "PLACA_VEICULO",	"UF_VEICULO",	"CIDADE_VEICULO",	"DESCR_COR_VEICULO",
    //         "DESCR_MARCA_VEICULO", "ANO_FABRICACAO", "ANO_MODELO","DESCR_TIPO_VEICULO","QUANT_CELULAR", 
    //         "MARCA_CELULAR")
    //     .setSkipHeaderRecord(true)
    //     .build();
    //       CSVParser csvParser = configCSV.parse(reader);
    //     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    //     LocalDate ld = null;

        
    //     List<CSVRecord> records = csvParser.getRecords();
    //     System.out.println(records.size());
    //     for (CSVRecord csvRecord : records) {
    //         System.out.println(csvRecord.get(1));
    //     }
    //     for (CSVRecord record : records) {
    //         BoletimModel b = new BoletimModel();
    //         ParteModel parteModel = new ParteModel();
    //         EmplacamentoModel eModel = new EmplacamentoModel();
    //         EnderecoModel enderecoModel = new EnderecoModel();
    //         VeiculoModel vModel = new VeiculoModel();

    //         ld = LocalDate.parse(record.get("DATAOCORRENCIA"), formatter);
    //         b.setDataOcorrencia(ld);

    //         var a = record.get("")

    //         b.setPeriodoOcorrencia(record.get("PERIDOOCORRENCIA"));
    //         parteModel.setNome(record.get("BO_AUTORIA"));
    //         parteModel.setEmail("xxxx@gmail.com");
    //         parteModel.setTelefone("(99)999999999");
    //         parteModel.setTipoEnvolvimento(record.get("RUBRICA"));

    //         enderecoModel.setBairro(record.get("BAIRRO"));
    //         enderecoModel.setCidade(record.get("CIDADE"));
    //         enderecoModel.setEstado(record.get("UF"));
    //         enderecoModel.setLogradouro(record.get("LOGRADOURO"));
    //         enderecoModel.setNumero(record.get("NUMERO"));

    //         vModel.setAnoFabricacao(record.get("ANO_FABRICACAO"));
    //         vModel.setCor(record.get("DESCR_COR_VEICULO"));
    //         vModel.setMarca(record.get("DESCR_MARCA_VEICULO"));
    //         vModel.setTipoVeiculo(record.get("DESCR_TIPO_VEICULO"));

    //         eModel.setCidade(record.get("CIDADE_VEICULO"));
    //         eModel.setEstado(record.get("UF_VEICULO"));
    //         eModel.setPlaca(record.get("PLACA_VEICULO"));

    //         vModel.setEmplacamento(eModel);

    //         b.setEnderecoModel(enderecoModel);
    //         b.setParteModel(parteModel);
    //         b.setVeiculoModel(vModel);              

    //         listBoletinsModels.add(b);
    //     }
    // }

}
