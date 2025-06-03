package app.entity;

import java.util.List;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import app.auth.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    
    @NotBlank(message = "O nome do médico é obrigatório")
    private String nome;

    @NotBlank(message = "O número do CRM é obrigatório")
    @Pattern(regexp = "^(\\d{2}[.-]?\\d{2}[.-]?\\d{2}|\\d{6})$", message = "O CRM deve estar no formato 12.34.56, 12-34-56 ou 123456")
    private String numeroCrm;

    @NotBlank(message = "O CPF do médico é obrigatório")
    @Pattern(regexp = "^(\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2})$", message = "O CPF deve estar no formato 000.000.000-00 ou 00000000000")
    private String cpf;

    @NotBlank(message = "A especialidade do médico é obrigatória")
    private String especialidade;

    @NotBlank(message = "O telefone do médico é obrigatório")
    private String telefone;

    @OneToMany(mappedBy = "medico")
    @JsonIgnoreProperties({"medico", "animal", "animais", "consultas"})
    private List<Consulta> consultas;

    @ManyToMany(mappedBy = "medicos")
    @JsonIgnoreProperties({"medicos", "consultas"})
    private List<Animal> animais;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}