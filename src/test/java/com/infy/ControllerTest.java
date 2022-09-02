package com.infy;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infy.controller.AuthorController;
import com.infy.entity.Author;
import com.infy.entity.Book;
import com.infy.service.AuthorService;

public class ControllerTest {

	MockMvc mockMvc;

	ObjectMapper mapper = new ObjectMapper();

	@Mock
	AuthorService authorService;

	@InjectMocks
	AuthorController authorController;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(authorController).build();

	}

	@Test
	public void getAuthorTest() throws Exception {

		Book b1 = new Book(1, "k1");
		Book b2 = new Book(2, "k2");

		Author author = new Author(1, "kartik", Arrays.asList(b1, b2));

		when(authorService.getAuthorById(1)).thenReturn(author);

		mockMvc.perform(MockMvcRequestBuilders.get("/getAuthorById/{id}", 1).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.name", is("kartik")));

	}

	@Test
	public void getAllRecordsSuccess() throws Exception {

		Book b1 = new Book(1, "k1");
		Book b2 = new Book(2, "k2");

		Author a1 = new Author(1, "kartik", Arrays.asList(b1, b2));
		Author a2 = new Author(2, "vennela", Arrays.asList(b1, b2));

		List<Author> author = new ArrayList<Author>(Arrays.asList(a1, a2));

		Mockito.when(authorService.getAuthors()).thenReturn(author);

		mockMvc.perform(MockMvcRequestBuilders.get("/getAuthors").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[1].name", is("vennela")));
	}

	@Test
	public void addAuthorSuccess() throws Exception {

		Book b1 = new Book(1, "k1");
		Book b2 = new Book(2, "k2");

		Author author = new Author(1, "kartik", Arrays.asList(b1, b2));

		Mockito.when(authorService.addAuthor(Mockito.any(Author.class))).thenReturn(author);

		mockMvc.perform(MockMvcRequestBuilders.post("/addAuthor").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(author)))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.name", is("kartik")));

	}

	@Test
	public void getBookTest() throws Exception {

		Book book = new Book(1, "k1");

		when(authorService.getBookById(1)).thenReturn(book);

		mockMvc.perform(MockMvcRequestBuilders.get("/getBookById/{id}", 1).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.title", is("k1")));

	}

}
