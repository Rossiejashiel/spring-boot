package com.exampleweb2.demoweb2;

import com.exampleweb2.demoweb2.model.Usuario;
import com.exampleweb2.demoweb2.repo.IUsuarioRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class Demoweb2ApplicationTests {
	@Autowired
	private IUsuarioRepo repo;

	private BCryptPasswordEncoder encoder;

	@Test
	public void crearUsuarioTest() {
		Usuario us=new Usuario();
		us.setId(5);
		us.setNombre("dara3");
		us.setClave(encoder.encode("123"));
		Usuario retorno=repo.save(us);
		assertTrue(retorno.getClave().equalsIgnoreCase(us.getClave()));
	}

}
