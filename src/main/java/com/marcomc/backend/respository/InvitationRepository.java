package com.marcomc.backend.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marcomc.backend.entity.Invitation;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, String>  {
}
