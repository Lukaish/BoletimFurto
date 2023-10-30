package br.com.boletimFurto.BoletimFurto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.boletimFurto.BoletimFurto.model.BoletimModel;

@Repository
public interface BoletimRepository extends JpaRepository<BoletimModel, Long> {
    
}
