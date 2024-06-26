package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Usuario;
import com.example.demo.repositories.UsuarioRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioRepository repository;
	
	@GetMapping("/listar")
	public List<Usuario> findAll() {
		List<Usuario> result = repository.findAll();
		return result;
	}
	
	
	
	@PostMapping("/cadastrar")
	public String insert(@Valid @RequestBody Usuario usuario, BindingResult bindingResult) {
		
		String mensagem_erro = "algo de errado nao esta certo";
		String mensagem_sucesso = "Usuario cadastrado com sucesso";
		
		Usuario result = new Usuario();
		if (bindingResult.hasErrors()) {			
			for (ObjectError obj : bindingResult.getAllErrors()) {
				System.out.println(obj.getDefaultMessage());
			}
			return mensagem_erro;
		} else {
			usuario.setUsuario_id(0l);
			result = repository.save(usuario);
			return mensagem_sucesso;
		}
		
	}
	
	
	@DeleteMapping(value = "/deletar")
	@ResponseBody
	public String delete(@PathVariable @Valid @RequestParam Long usuario_id)	{
		
		Optional<Usuario> existingEntity = repository.findById(usuario_id);
        if (existingEntity.isPresent()) {
           
        	repository.delete(existingEntity.get());
        	return "usuario deletado com sucesso";
        } else {
        	return "usuario nao encontrado";
        }
		
		
	}
	
	@PutMapping(value = "/atualizar/{id}")
	@ResponseBody
	public ResponseEntity<Object>atualizar(@PathVariable Long id, @Valid @RequestBody Usuario usuario, BindingResult bindingResult) {
		
		Optional<Usuario> existingEntity = repository.findById(id);
		 if (existingEntity.isPresent()) {
			 if (bindingResult.hasErrors()) {
				 List<String> erros = new ArrayList<>();
				 for(ObjectError obj:bindingResult.getAllErrors()) {
					 erros.add(obj.getDefaultMessage());
				 }
			
				 return ResponseEntity.badRequest().body(erros);
	        } else {
				Usuario existingUsuario = existingEntity.get();
				 existingUsuario.setNome(usuario.getNome());
				 existingUsuario.setEmail(usuario.getEmail());
				 existingUsuario.setSenha(usuario.getSenha()); 
				 existingUsuario.setSobre(usuario.getSobre());
				 repository.save(existingUsuario);
				
				}
			 
	        } 
		 else {
			 return ResponseEntity.ok("Usuario não encontrado");
		 }
		 return ResponseEntity.ok("Usuario atualizado com sucesso");
	    
	}
	
	
		
	/* 
	@GetMapping(value = "/encontratPorId")
	@ResponseBody
	public ResponseEntity<Usuario> atualizar(@RequestParam(name = "usuario_id") Long usuario_id)	{
		
		Usuario usuario = repository.findById(usuario_id).get();
		
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
			
	*/
	
	/*
	@PostMapping("/cadastrar2")
	public String cadastrar2(@Valid @RequestBody Usuario dados, BindingResult bindingResult) {
//		ProfessorResponseDTO response = new ProfessorResponseDTO();
//		response.setStatusCode("200");
		if (bindingResult.hasErrors()) {
//			response.setStatusCode("199");
			for (ObjectError obj : bindingResult.getAllErrors()) {
				System.out.println(obj.getDefaultMessage());
			}
			return "Erros";
		} else {
			return "Validou";
		}
	}
	
	*/
	
}
