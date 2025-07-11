//AuthenticationService.java
package app.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import app.config.JwtServiceGenerator;
import app.entity.Medico;
import app.repository.MedicoRepository;
import app.service.MedicoService;

@Service
public class LoginService {
	

	@Autowired
	private MedicoService medicoService;
	@Autowired
	private LoginRepository repository;
	@Autowired
	private JwtServiceGenerator jwtService;
	@Autowired
	private AuthenticationManager authenticationManager;


	
	public String logar(Login login) {

		String token = this.gerarToken(login);
		return token;

	}



	public String gerarToken(Login login) {
	    authenticationManager.authenticate(
	            new UsernamePasswordAuthenticationToken(
	                    login.getUsername(),
	                    login.getPassword()
	            )
	    );
	    Usuario user = repository.findByUsername(login.getUsername()).get();

	    Medico medico = medicoService.findByUsuarioId(user.getId());
	    Long medicoId = medico != null ? medico.getId() : null;

	    String jwtToken = jwtService.generateToken(user, medicoId);
	    return jwtToken;
	}


}
