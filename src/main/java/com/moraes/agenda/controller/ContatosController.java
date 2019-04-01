package com.moraes.agenda.controller;

import com.moraes.agenda.models.Contato;
import com.moraes.agenda.services.ContatoServices;
import com.moraes.agenda.utils.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.WebAsyncTask;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(value =  "/contatos")
public class ContatosController {

    @Autowired
    private ContatoServices service;

    @PostMapping()
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Void> insert(@Valid @RequestBody Contato contato) throws URISyntaxException {
        contato = service.insert(contato);
        UriComponents uriComponents = UriComponentsBuilder.fromUriString("/contatos/{id}").buildAndExpand(contato.getId());return ResponseEntity.created(new URI(uriComponents.toString())).build();
    }

    @PostMapping("upload")
    public WebAsyncTask<ResponseEntity<Void>> upload(@RequestParam("file") MultipartFile file){
        return new WebAsyncTask<ResponseEntity<Void>>(10000, () -> {
            UploadUtils.teste(file.getOriginalFilename(), file.getInputStream(), file.getContentType());
           return new ResponseEntity(null, HttpStatus.OK);
        });
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contato> get(@Valid @PathVariable("id") Long id){
        Contato contato = service.findById(id);
        return ResponseEntity.ok(contato);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<Contato>> list(Pageable pageable){
        Page<Contato> lista = service.listPage(pageable);
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contato> update(@Valid @RequestBody Contato contato, @PathVariable("id") Long id){
        contato.setId(id);
        contato = service.update(contato);

        return ResponseEntity.ok(contato);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
