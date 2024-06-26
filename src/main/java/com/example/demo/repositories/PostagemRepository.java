package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Postagem;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> {

}
