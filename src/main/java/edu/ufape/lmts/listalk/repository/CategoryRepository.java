package edu.ufape.lmts.listalk.repository;

import java.util.List;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edu.ufape.lmts.listalk.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	

}