package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Comentario;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

}
