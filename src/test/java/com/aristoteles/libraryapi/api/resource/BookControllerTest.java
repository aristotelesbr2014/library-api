package com.aristoteles.libraryapi.api.resource;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * BookControllerTest
 */
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
public class BookControllerTest {

  static String BOOK_API = "/api/books";

  @Autowired
  MockMvc mvc;

  @Test
  @DisplayName("Must`be create a book.")
  public void createBookTest() throws Exception {
    String json = new ObjectMapper().writeValueAsString(null);

    MockHttpServletRequestBuilder request = MockMvcRequestBuilders
      .post(BOOK_API)
      .contentType(MediaType.APPLICATION_JSON)
      .accept(MediaType.APPLICATION_JSON)
      .content(json);

    mvc
      .perform(request)
      .andExpect(MockMvcResultMatchers.status().isCreated())
      .andExpect(MockMvcResultMatchers.jsonPath("id").isNotEmpty())
      .andExpect(MockMvcResultMatchers.jsonPath("title").value("Meu Livro"))
      .andExpect(MockMvcResultMatchers.jsonPath("author").value("Autor"))
      .andExpect(MockMvcResultMatchers.jsonPath("isbn").value("123123"))
    ;
  }

  @Test
  @DisplayName("Must`be returns errors when pass invalid params.")
  public void createInvalidBookTest() {

  }
}
