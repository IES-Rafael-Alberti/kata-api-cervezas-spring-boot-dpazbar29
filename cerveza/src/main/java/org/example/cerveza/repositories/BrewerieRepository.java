package org.example.cerveza.repositories;

import org.example.cerveza.entities.Brewerie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrewerieRepository extends JpaRepository<Brewerie, Long> {}
