package com.insurance.system.shared.filestorage.context;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileDao extends JpaRepository<File, Long> {
  Optional<File> findByName(String paramString);
  
  void deleteAllByName(String paramString);
}
