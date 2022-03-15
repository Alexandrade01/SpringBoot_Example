package academy.devdojo.Myspringboot2essentials.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import academy.devdojo.Myspringboot2essentials.domain.Anime;
import academy.devdojo.Myspringboot2essentials.service.AnimeService;
import academy.devdojo.Myspringboot2essentials.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("animes")
@Log4j2
@RequiredArgsConstructor
public class AnimeController {
	
	private final DateUtil dateUtil;
    private final AnimeService animeService;

    @GetMapping
    public ResponseEntity<List<Anime>> list(){
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(animeService.listAll(), HttpStatus.OK);
        //return ResponseEntity.ok(animeService.ListAll)
    }
    
    @GetMapping(path = "/{id}")
    public ResponseEntity<Anime> findById(@PathVariable long id){
    	
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        
        return new ResponseEntity<>(animeService.findById(id), HttpStatus.OK);
        //return ResponseEntity.ok(animeService.ListAll)
    }
    
    @PostMapping
    public ResponseEntity<Anime> save(@RequestBody Anime animeBody){
    	
    	// log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
    	 
    	 return new ResponseEntity<>(animeService.save(animeBody),HttpStatus.CREATED);
    }
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
    	
    	log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
    	log.info("Deleted Sucess!");
    	animeService.delete(id);
    	
    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody Anime animeBody){
    	
    	log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
    	animeService.replace(animeBody);
    	
    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    

}
