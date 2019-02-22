package com.moraes.agenda.services;

import com.moraes.agenda.exceptions.NotFoundItemException;
import com.moraes.agenda.models.Contato;
import com.moraes.agenda.models.Endereco;
import com.moraes.agenda.repository.ContatoRepository;
import com.moraes.agenda.repository.EnderecoRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Log
public class ContatoServices {
    @Autowired
    private ContatoRepository contatoRep;

    @Autowired
    private EnderecoRepository endRepo;

    public Contato insert(Contato contato){
       this.putContatoOnEnderecos(contato);
        log.info("Contato criado" + contato.toString());
        return contatoRep.save(contato);
    }

    public Contato findById(Long id){
        Optional<Contato> contato = contatoRep.findById(id);
        return contato.orElseThrow(() -> new NotFoundItemException(id));
    }

    public Page<Contato> listPage(Pageable pageable){
        Page<Contato> pageItems = contatoRep.findAll(pageable);

        return pageItems;
    }

    public Contato update(Contato contato){
        log.info(contato.getEnderecos().toString());
        Contato contatoAtual = this.findById(contato.getId());
        this.removeEnderecos(contatoAtual.getEnderecos());
        this.putContatoOnEnderecos(contato);
        return contatoRep.save(contato);
    }

    public void delete(Long id){
        Contato contato = this.findById(id);

        contatoRep.delete(contato);
    }

    private void removeEnderecos(List<Endereco> enderecos){
        for(Endereco end: enderecos){
            end.setContato(null);
        }
        endRepo.deleteAll(enderecos);
    }


    private void putContatoOnEnderecos(Contato contato){
        for(Endereco end : contato.getEnderecos()){
            end.setContato(contato);
        }
    }
}
