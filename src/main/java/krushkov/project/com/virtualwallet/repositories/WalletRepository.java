package krushkov.project.com.virtualwallet.repositories;

import jakarta.persistence.LockModeType;
import krushkov.project.com.virtualwallet.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository
        extends JpaRepository<Wallet, Long>, JpaSpecificationExecutor<Wallet> {

    Optional<Wallet> findByUserId(Long userId);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("select w from Wallet w where w.id = :id")
    Optional<Wallet> findByIdForUpdate(@Param("id") Long id);

}
