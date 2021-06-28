package br.com.senai.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class    AuthenticationResponse {

    private String jwt;
}
