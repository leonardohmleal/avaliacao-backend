package br.com.leonardohmleal.avaliacao_final.dto;
import br.com.leonardohmleal.avalicao_final.dto.BookDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CustomerDto {

    private long id;

    private String fullName;

    private String gender;

    private BookDto book;

}