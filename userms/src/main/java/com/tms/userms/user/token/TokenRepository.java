package com.tms.userms.user.token;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TokenRepository extends JpaRepository<Token, Long>
{
    @Query("""
            SELECT t
            FROM Token t
            INNER JOIN User u
            ON t.user.id = u.id
            WHERE u.id = :id
            AND (t.expired = false OR t.revoked = false)
            """)
    List<Token> findAllValidTokensByUser(Long id);

    Optional<Token> findByToken(String token);
}
