package com.lojaDeGames.boomka.repositorys;

import com.lojaDeGames.boomka.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    public Optional<Pedido> findById(Long id);
}
