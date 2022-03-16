package academy.devdojo.Myspringboot2essentials.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import academy.devdojo.Myspringboot2essentials.domain.Anime;

public interface AnimeRepository extends JpaRepository<Anime, Long> {
    
	//Nao precisamos do listall pois o jparepository ja o contem
	//List<Anime> listAll();
}