package com.akajarius.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.akajarius.workshopmongo.domain.Post;
import com.akajarius.workshopmongo.domain.User;
import com.akajarius.workshopmongo.dto.AuthorDTO;
import com.akajarius.workshopmongo.dto.CommentDTO;
import com.akajarius.workshopmongo.repository.PostRepository;
import com.akajarius.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postReposiroty;

	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postReposiroty.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("21/03/2025"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AuthorDTO (maria));
		Post post2 = new Post(null, sdf.parse("23/03/2025"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO (maria));
		
		CommentDTO c1 = new CommentDTO("Boa viagem Maria!", sdf.parse("21/03/2025"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Aproveite a Terra da Garoa", sdf.parse("22/03/2025"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Tenha um ótimo dia!", sdf.parse("23/03/2025"), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postReposiroty.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
	}

}
