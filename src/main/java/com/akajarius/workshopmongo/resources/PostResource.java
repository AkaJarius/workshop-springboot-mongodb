package com.akajarius.workshopmongo.resources;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.akajarius.workshopmongo.domain.Post;
import com.akajarius.workshopmongo.resources.util.URL;
import com.akajarius.workshopmongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	private PostService service;
	private ResponseEntity<List<Post>> defaultValue;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id) {
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(obj);

	}

	@GetMapping(value = "/titlesearch")
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(defaultValue = "") String text) {
		text = URL.decodeParam(text);
		List<Post> list = service.findByTitle(text);
		return ResponseEntity.ok().body(list);

	}

	@GetMapping("/fullsearch")
	public ResponseEntity<List<Post>> fullSearch(

			@RequestParam(defaultValue = "") String text,
			@RequestParam(defaultValue = "") String minDate,
			@RequestParam(defaultValue = "") String maxDate) {

		text = URL.decodeParam(text);
		
		
		
		Date min;
		try {
			min = URL.convertDate(minDate, new Date(0L));
		} catch (ParseException e) {
			return defaultValue;
		}
		Date max;
		try {
			max = URL.convertDate(maxDate, new Date());
		} catch (ParseException e) {
			return defaultValue;
		}

		List<Post> list = service.fullSearch(text, min, max);

		return ResponseEntity.ok().body(list);

	}

}